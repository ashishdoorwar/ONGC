package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +ReportsPortletKeys.EDirectory,
				"mvc.command.name=update_user"
		},
		service = MVCActionCommand.class
		)
public class EditUserProfileActionCommand extends BaseMVCActionCommand{

	private UserService userService=new UserServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Reports");				
		String filePath = szResBundl.getString("profileFilePath").toString().trim();

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("uploadedFile");
		File file = uploadPortletRequest.getFile("uploadedFile");
		//String title = fileName;
		String cpfNum=ParamUtil.getString(actionRequest, "cpfNum");
		
		File generatedFileName = new File(filePath+cpfNum+file.getName().substring(file.getName().lastIndexOf(".")));
		copy(file, generatedFileName);
		String empName=ParamUtil.getString(actionRequest, "empname");
		String designation=ParamUtil.getString(actionRequest, "emplevel");
		String placePosting=ParamUtil.getString(actionRequest, "placeposting");
		Date dob=ParamUtil.getDate(actionRequest, "dob", DateFormat.getTimeInstance());
		Date doj=ParamUtil.getDate(actionRequest, "doj", DateFormat.getTimeInstance());
		String workPlace=ParamUtil.getString(actionRequest, "workplace");
		String department=ParamUtil.getString(actionRequest, "department");
		String mobileNo=ParamUtil.getString(actionRequest, "mobileno");
		String faxNo=ParamUtil.getString(actionRequest, "faxno");
		String emailIdOff=ParamUtil.getString(actionRequest, "emailidoff");
		String emailIdPer=ParamUtil.getString(actionRequest, "emailidper");
		String phoneOff=ParamUtil.getString(actionRequest, "phoneoff");
		String boardOff=ParamUtil.getString(actionRequest, "boardoff");
		String extOff=ParamUtil.getString(actionRequest, "extoff");
		String phoneRes=ParamUtil.getString(actionRequest, "phoneres");
		String officeAddr=ParamUtil.getString(actionRequest, "officeaddr");
		String residenceAddr=ParamUtil.getString(actionRequest, "residenceaddr");
		String aboutMe=ParamUtil.getString(actionRequest, "aboutMe");
		//String captchaVal=ParamUtil.getString(actionRequest, "captchaVal");
		
		User user = new User();
		user.setCpfNo(cpfNum);
		user.setEmpName(empName);
		user.setDesignation(designation);
		user.setPlacePosting(placePosting);
		user.setDateOfBirth(dob);
		user.setDateOfJoining(doj);
		user.setWorkPlace(workPlace);
		user.setDepartment(department);
		user.setMobileNo(mobileNo);
		user.setFaxNumber(faxNo);
		user.setEmailIdOfficial(emailIdOff);
		user.setEmailIdPersonal(emailIdPer);
		user.setPhoneNumberOffice(phoneOff);
		user.setBoardNumber(boardOff);
		user.setExtensionNumber(extOff);
		user.setPhoneNumberHome(phoneRes);
		user.setOfficeAddress(officeAddr);
		user.setResidenceAddress(residenceAddr);
		user.setAboutMe(aboutMe);
		//user.setCaptchaVal(captchaVal);
		user.setProfilePicPath(filePath+file.getName());
		userService.updateUserProfile(user);
	}

	public static void copy(File src, File dest) throws IOException { 
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
