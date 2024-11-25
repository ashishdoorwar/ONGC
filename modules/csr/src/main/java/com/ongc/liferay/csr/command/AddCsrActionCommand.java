package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;
import com.ongc.liferay.csr.model.StatusBean;
import com.ongc.liferay.csr.model.SubjectBean;
import com.ongc.liferay.csr.service.CsrService;
import com.ongc.liferay.csr.service.StatusService;
import com.ongc.liferay.csr.service.SubjectService;
import com.ongc.liferay.csr.service.Impl.CsrServiceImpl;
import com.ongc.liferay.csr.service.Impl.StatusServiceImpl;
import com.ongc.liferay.csr.service.Impl.SubjectServiceImpl;
import com.ongc.liferay.csr.util.CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +CsrPortletKeys.CSR,
				"mvc.command.name=add_csr"
		},
		service = MVCActionCommand.class
		)
public class AddCsrActionCommand extends BaseMVCActionCommand {

	private CsrService csrService =new CsrServiceImpl();
	private StatusService statusService =new StatusServiceImpl();
	private SubjectService subjectService =new SubjectServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		File releaseOrderDoc = uploadPortletRequest.getFile("releaseOrderDoc");
		String releaseOrderDocFileName=uploadPortletRequest.getFileName("releaseOrderDoc");
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		String filePath=szResBundl.getString("file-path");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/csr/editCsr.jsp");
		}
		else {
			File to = new File(filePath+releaseOrderDoc.getName());
			if(!Validator.isBlank(releaseOrderDocFileName)) {
				copy(releaseOrderDoc, to);
			}

			File proposalDoc = uploadPortletRequest.getFile("proposalDoc");
			String proposalDocFileName=uploadPortletRequest.getFileName("proposalDoc");
			to = new File(filePath+proposalDoc.getName());
			if(!Validator.isBlank(proposalDocFileName)) {
				copy(proposalDoc, to);
			}

			File eightyGDoc = uploadPortletRequest.getFile("eightyGDoc");
			String eightyGDocFileName=uploadPortletRequest.getFileName("eightyGDoc");
			to = new File(filePath+eightyGDoc.getName());
			if(!Validator.isBlank(eightyGDocFileName)) {
				copy(eightyGDoc, to);
			}

			File twelveAADoc = uploadPortletRequest.getFile("twelveAADoc");
			String twelveAADocFileName=uploadPortletRequest.getFileName("twelveAADoc");
			to = new File(filePath+twelveAADoc.getName());
			if(!Validator.isBlank(twelveAADocFileName)) {
				copy(twelveAADoc, to);
			}

			File receiptsDoc = uploadPortletRequest.getFile("receiptsDoc");
			String receiptsDocFileName=uploadPortletRequest.getFileName("receiptsDoc");
			to = new File(filePath+receiptsDoc.getName());
			if(!Validator.isBlank(receiptsDocFileName)) {
				copy(receiptsDoc, to);
			}
			PortletSession psession = actionRequest.getPortletSession();
			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			actionRequest.setAttribute("loginId", login);
			int id=ParamUtil.getInteger(actionRequest, "id");
			String subName=ParamUtil.getString(actionRequest, "subId");
			String programName=ParamUtil.getString(actionRequest, "programName");
			String fundingFrmOngc=ParamUtil.getString(actionRequest, "fundingFrmOngc");
			String proposalReciptDate=ParamUtil.getString(actionRequest, "proposalReciptDate");
			String programDateTo=ParamUtil.getString(actionRequest, "programDateTo");
			String programNature=ParamUtil.getString(actionRequest, "programNature");
			String programNatureBDP=ParamUtil.getString(actionRequest, "programNatureBDP");
			String programDate=ParamUtil.getString(actionRequest, "programDate");
			String programCost=ParamUtil.getString(actionRequest, "programCost");
			String email=ParamUtil.getString(actionRequest, "email");
			String contactPerson=ParamUtil.getString(actionRequest, "contactPerson");
			String company=ParamUtil.getString(actionRequest, "company");
			String exemption80G=ParamUtil.getString(actionRequest, "exemption80G");
			String legalStatus=ParamUtil.getString(actionRequest, "legalStatus");
			String applicableGst=ParamUtil.getString(actionRequest, "applicableGst");
			String implementingAgencyName=ParamUtil.getString(actionRequest, "implementingAgencyName");
			String filecurrentlywith=ParamUtil.getString(actionRequest, "filecurrentlywith");
			String remark=ParamUtil.getString(actionRequest, "remark");
			String status=ParamUtil.getString(actionRequest, "status");
			String trackingNumber=ParamUtil.getString(actionRequest, "trackingNumber");
			String frNumber=ParamUtil.getString(actionRequest, "frNumber");
			String dishaFileNumber=ParamUtil.getString(actionRequest, "dishaFileNumber");
			String approvingAuthorityAsPerBDM=ParamUtil.getString(actionRequest, "approvingAuthorityAsPerBDM");
			String activityNature=ParamUtil.getString(actionRequest, "activityNature");
			String mobileNo=ParamUtil.getString(actionRequest, "mobileNo");
			String designarionInAgency=ParamUtil.getString(actionRequest, "designarionInAgency");
			CSRProgramModel csrProgramModel=new CSRProgramModel();
			csrProgramModel.setId(id);
			csrProgramModel.setSubject(subName);
			csrProgramModel.setFundingFrmOngc(fundingFrmOngc);
			csrProgramModel.setProposalReciptDate(proposalReciptDate);
			csrProgramModel.setProgramDateTo(programDateTo);
			csrProgramModel.setProgramName(programName);
			csrProgramModel.setProgramNature(programNature);
			csrProgramModel.setProgramDate(programDate);
			csrProgramModel.setProgramCost(programCost);
			csrProgramModel.setReceiptsDocFileName(filePath+receiptsDoc.getName());
			csrProgramModel.setTwelveAADocFileName(filePath+twelveAADoc.getName());
			csrProgramModel.setEightyGDocFileName(filePath+eightyGDoc.getName());
			csrProgramModel.setReleaseOrderDocFileName(filePath+releaseOrderDoc.getName());
			csrProgramModel.setEmail(email);
			csrProgramModel.setContactPerson(contactPerson);
			csrProgramModel.setCompany(company);
			csrProgramModel.setExemption80G(exemption80G);
			csrProgramModel.setLegalStatus(legalStatus);
			csrProgramModel.setApplicableGst(applicableGst);
			csrProgramModel.setImplementingAgencyName(implementingAgencyName);
			csrProgramModel.setFilecurrentlywith(filecurrentlywith);
			csrProgramModel.setRemark(remark);
			csrProgramModel.setStatus(status);
			csrProgramModel.setProposalDocFileName(filePath+proposalDoc.getName());
			csrProgramModel.setTrackingNumber(trackingNumber);
			csrProgramModel.setFrNumber(frNumber);
			csrProgramModel.setDishaFileNumber(dishaFileNumber);
			csrProgramModel.setActivityNature(activityNature);
			csrProgramModel.setApprovingAuthorityAsPerBDM(approvingAuthorityAsPerBDM);
			csrProgramModel.setActivityNature(activityNature);
			csrProgramModel.setMobileNo(mobileNo);
			csrProgramModel.setDesignarionInAgency(designarionInAgency);
			csrProgramModel.setApprovingAuthorityAsPerBDM(programNatureBDP);
			csrProgramModel.setCreatedBy(login);
			if(id==0) {
				csrService.save(csrProgramModel);
			}else {
				csrService.update(csrProgramModel);
			}
		}
		FilterBean filterBean=new FilterBean();

		List<CSRProgramModel> cSRProgramModels =csrService.findAll(filterBean);
		actionRequest.setAttribute("cSRProgramModels", cSRProgramModels);

		List<StatusBean>  statusBeans= statusService.getAllStatus();
		List<SubjectBean>  subjectBeans= subjectService.getSubjectList();
		actionRequest.setAttribute("statusBeans", statusBeans);
		actionRequest.setAttribute("subjectBeans", subjectBeans);


	}

	public void copy(File src, File dest) throws IOException { 
		InputStream is = null; 
		OutputStream os = null; 
		try { 
			is = new FileInputStream(src); 
			os = new FileOutputStream(dest); 

			byte[] buf = new byte[10240];
			int bytesRead; 
			while ((bytesRead = is.read(buf)) > 0){
				os.write(buf, 0, bytesRead); 
			} 
		} finally {
			is.close();
			os.close();
		} 
	}

}
