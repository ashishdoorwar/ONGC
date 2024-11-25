package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ContentManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ContentManagementDaoImpl;
import com.ongc.liferay.vigilance.model.Country;
import com.ongc.liferay.vigilance.model.VigilanceContent;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.CommonUtil;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.PasswordValidator;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.util.List;

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
				"javax.portlet.name=" +VigilancePortletKeys.UserVigilance,
				"mvc.command.name=registerUser"
		},
		service = MVCActionCommand.class
		)
public class RegisterUserActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String emailId=ParamUtil.getString(actionRequest, "emailId");
//		String password=ParamUtil.getString(actionRequest, "password");
//		String confirmPass=ParamUtil.getString(actionRequest, "confirmPass");
//		String securityQuestion=ParamUtil.getString(actionRequest, "securityQuestion");
//		String answer=ParamUtil.getString(actionRequest, "answer");
		String userTitle=ParamUtil.getString(actionRequest, "userTitle");
		String firstName=ParamUtil.getString(actionRequest, "firstName");
		String middleName=ParamUtil.getString(actionRequest, "middleName");
		String lastName=ParamUtil.getString(actionRequest, "lastName");
		String dob=ParamUtil.getString(actionRequest, "dob");
		String phoneNumber=ParamUtil.getString(actionRequest, "phoneNumber");
		String mobile=ParamUtil.getString(actionRequest, "mobile");
		String address1=ParamUtil.getString(actionRequest, "address1");
		String address2=ParamUtil.getString(actionRequest, "address2");
		int countryID=ParamUtil.getInteger(actionRequest, "countryID");
		String state=ParamUtil.getString(actionRequest, "state");
		String pincode=ParamUtil.getString(actionRequest, "pincode");
		VigilanceUser user = VigilanceFactory.getUserServiceInstance().getUserByEmailId(emailId);
		if(user!=null) {
			SessionErrors.add(actionRequest,"Email already exist");
			actionResponse.sendRedirect("mvcPath", "/jsp/VigilanceRegistration.jsp");
		}else {

			boolean checkParameter = CommonUtil.checkParameter(actionRequest);
			if(checkParameter) {
				SessionErrors.add(actionRequest,"error");
				actionResponse.setRenderParameter("mvcPath", "/jsp/addNewGroup.jsp");
			}
			else {
//				int i = PasswordValidator.getInstance().validatePassword(password, emailId);
				ContentManagementDao cont=new ContentManagementDaoImpl();

				List<VigilanceContent> list=cont.getContentByType("securityQuestion");
				List<Country> country = VigilanceFactory.getCountryManagementInstance().getAllCountry();
				actionRequest.setAttribute("constants", list); 
				actionRequest.setAttribute("country", country);


//				if (i != 0 && i > 0 ) {
//					//			addActionError(PasswordValidator.getInstance().getMessage(i));
//					//			return INPUT;
//				}
				user=new VigilanceUser();

				user.setEmailId(emailId);
//				user.setPassword(password);
//				user.setConfirmPasswrod(confirmPass);
//				user.setSecurityQuestion(securityQuestion);
//				user.setSecurityAnswer(answer);
				user.setTitle(userTitle);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMiddleName(middleName);
				user.setDob(dob);
				user.setMobile(mobile);
				user.setFirstAddress(address1);
				user.setSecondAddress(address2);
				user.setCountry(String.valueOf(countryID));
				user.setState(state);
				user.setPincode(pincode);

				VigilanceUser user1 = VigilanceFactory.getUserServiceInstance().getUserByEmailId(emailId);

				VigilanceUser user2 = VigilanceFactory.getUserServiceInstance().getUserByMobile(mobile.substring(1));
				//system.out.println("user1="+user1);
				//system.out.println(" for mob user2 ="+user2);

				if(user2!=null)
				{
					//			addActionError("Mobile number already exists");
					//			openRegForm();
					//			return INPUT;
				}

				if (user1 == null ) {
					user.setOtp(ONGCUtil.getInstance().generateOTP());

					//			setSessionAttribute(VigilanceConstant.REGISTRATION_USER, user);

					//system.out.println("New generate "+user.getMobile()+ "  OTP :: "+user.getOtp());
					//system.out.println("1");
					//			if(0<VigilanceFactory.getOptManagementInstance().insertOPT(mobile,user.getOtp(), VigilanceConstant.OPT_TYPE_REGISTRATION)){
					//				//system.out.println(" insertOPT successfully Generated");
					//			}else{
					//				//system.out.println("insertOPT problem");	
					//			}
					//system.out.println("2");

					if(!mobile.substring(0, 3).equals("+91") || !mobile.substring(0, 2).equals("91"))
					{
						ONGCUtil.getInstance().sendOTPOnEmail(emailId, user.getOtp(),VigilanceConstant.REGISTRATION);
					}
					else
					{
						ONGCUtil.getInstance().sendOTPOnSMS(mobile, user.getOtp(), VigilanceConstant.REGISTRATION);
					}

					VigilanceFactory.getUserServiceInstance().registerVigilanceUser(user , "");
					//			return SUCCESS;	

				} else {
					//			addActionError("Username already exists");
					//			//system.out.println("else 1");
					//			return INPUT;
				}
			}
		}
	}

}
