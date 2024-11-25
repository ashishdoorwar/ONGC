package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.model.ComplaintAttachment;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.CommonUtil;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.apache.commons.io.FileUtils;
import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +VigilancePortletKeys.UserVigilance,
				"mvc.command.name=registerComplaint"
		},
		service = MVCActionCommand.class
		)
public class RegisterComplaintActionCommand extends BaseMVCActionCommand{
	private static final Log LOGGER = LogFactoryUtil.getLog(RegisterComplaintActionCommand.class);
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		VigilanceComplaint complaint=new VigilanceComplaint();
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/vigilance");	
		final String notificationEmail=ResourceBundle.getBundle("/content/vigilance").getString("notificationEmail").toString().trim();			
		String filePath = szResBundl.getString("complaintFilePath").toString().trim();

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("dataFile");
		File file = uploadPortletRequest.getFile("dataFile");
		if(file.exists()) {
		String ext =fileName.substring(fileName.lastIndexOf("."));}
		String complaintSubject=ParamUtil.getString(actionRequest, "complaintSubject");
		String tenderNumber=ParamUtil.getString(actionRequest, "tenderNumber");
		String complaintDetail=ParamUtil.getString(actionRequest, "complaintDetail");
		String title=ParamUtil.getString(actionRequest, "title");
		String firstName=ParamUtil.getString(actionRequest, "firstName");
		String middleName=ParamUtil.getString(actionRequest, "middleName");
		String lastName=ParamUtil.getString(actionRequest, "lastName");
		String departmetn=ParamUtil.getString(actionRequest, "departmetn");
		String country=ParamUtil.getString(actionRequest, "location");
		String workCentre=ParamUtil.getString(actionRequest, "workCentre");
		String designation=ParamUtil.getString(actionRequest, "designation");
		//String dataFile=ParamUtil.getString(actionRequest, "dataFile");
		String TermsCondition=ParamUtil.getString(actionRequest, "TermsCondition");

		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/addNewGroup.jsp");
		}
		else {
			PortletSession psession = actionRequest.getPortletSession();
			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			actionRequest.setAttribute("loginId", login);
			VigilanceUser vigilanceUser = VigilanceFactory.getUserServiceInstance().getUserByEmailId(login);
			complaint.setUserId(vigilanceUser.getRegistrationId());
			complaint.setComplaintSubject(complaintSubject);
			complaint.setTenderNumber(tenderNumber);
			complaint.setComplaintDetail(complaintDetail);
			complaint.setTitle(title);
			complaint.setFirstName(firstName);
			complaint.setMiddleName(middleName);
			complaint.setLastName(lastName);
			complaint.setDepartmetn(departmetn);
			complaint.setCountry(country);
			complaint.setWorkCentre(workCentre);
			complaint.setDesignation(designation);
			complaint.setAttachment(fileName);
			ComplaintAttachment attachment;
			List<ComplaintAttachment> attachments = null;
			if(file.exists()) {
			attachment=new ComplaintAttachment();
			attachment.setFileName(fileName);
			attachments = new ArrayList<ComplaintAttachment>();
			attachments.add(attachment);
			complaint.setAttachmentList(attachments);}else {complaint.setAttachmentList(attachments);}
			/*
			 * if (dataFile != null && dataFile.length > 0) { for (int i = 0; i <
			 * dataFile.length; i++) { attachment = new ComplaintAttachment(); try {
			 * ////system.out.println("File data ::"+FileUtils.readFileToByteArray(dataFile[
			 * i])); attachment.setFileData(FileUtils.readFileToByteArray(dataFile[i]));
			 * ////system.out.println("File name ::"+dataFileFileName[i]);
			 * attachment.setFileName(dataFileFileName[i]); attachments.add(attachment); }
			 * catch (IOException e) { log.info("Error in reading file "+ e); } } }
			 */		
			
			
			String submitComplaintDetail = VigilanceFactory.getComplaintServiceInstance().submitComplaintDetail(complaint , ONGCUtil.getInstance().generateOTP(),"",vigilanceUser);
			int fileUpload = Integer.parseInt(submitComplaintDetail);
			String fileTitle = "VIG0"+submitComplaintDetail+"_"+fileName;
			System.out.println("======================================="+submitComplaintDetail+"====================================");
			if(fileUpload>1 && file.exists()) {
				FileUtils.copyFile(uploadPortletRequest.getFile("dataFile"), new File(filePath,fileTitle));
			}
			String otp = ONGCUtil.getInstance().generateOTP();
			//system.out.println("Submit Complaint MobileNo. :: "+vigilanceUser.getMobile()+" >>  OTP::: "+otp);
			VigilanceFactory.getOptManagementInstance().insertOPT(vigilanceUser.getMobile(), otp , VigilanceConstant.OPT_TYPE_COMPLAINT);
			VigilanceFactory.getComplaintServiceInstance().sendOTP(notificationEmail , otp,complaint,submitComplaintDetail,vigilanceUser);
//			if(!vigilanceUser.getMobile().substring(0, 2).equals("91"))
//			{
//				VigilanceFactory.getComplaintServiceInstance().sendOPT(vigilanceUser.getEmailId() , otp);
//			}
//			else{
//				ONGCUtil.getInstance().sendOTPOnSMS(vigilanceUser.getMobile(), otp , VigilanceConstant.OPT_TYPE_COMPLAINT);
//			}
			
			actionRequest.setAttribute("userData", vigilanceUser);

		}

	}

}
