package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.exception.UserChangePasswordException;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.util.CommonUtil;
import com.ongc.liferay.vigilance.util.PasswordValidator;
import com.ongc.liferay.vigilance.util.TEA;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.util.ArrayList;
import java.util.List;

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
				"javax.portlet.name=" +VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=adminChangePassDo"
		},
		service = MVCActionCommand.class
		)
public class AdminChangePasswordActionCommand  extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {


		String oldPassword=ParamUtil.getString(actionRequest, "oldPassword");
		String newPassword=ParamUtil.getString(actionRequest, "newPassword");
		String confirmPasswrod=ParamUtil.getString(actionRequest, "confirmPasswrod");

		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/addNewGroup.jsp");
		}
		else {

			if (oldPassword == null || oldPassword.trim().equals("")) {
				SessionErrors.add(actionRequest, "Enter Old Password");	
			}

			if (newPassword == null || newPassword.trim().equals("")) {
				SessionErrors.add(actionRequest, "Enter New Password");	
			}
			PortletSession psession = actionRequest.getPortletSession();
			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			actionRequest.setAttribute("loginId", login);
			VigilanceAdminUser vigilanceUser =VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(login);

			String oldEndPass = TEA.encrypt(oldPassword, vigilanceUser.getEmailId() );

			String dbPass = vigilanceUser.getPassword();

			if (newPassword.equals(confirmPasswrod)) {

				if (!oldEndPass.equals(dbPass)) {
					SessionErrors.add(actionRequest, "Old Password Not Match");	
				} 
				int i = PasswordValidator.getInstance().validatePassword(newPassword, vigilanceUser.getEmailId());
				if (i != 0 && i > 0 ) {
					SessionErrors.add(actionRequest, PasswordValidator.getInstance().getMessage(i));	
				} else {
					VigilanceFactory.getAdminUserServiceInstance().changePassword(vigilanceUser.getId() , vigilanceUser.getEmailId(),newPassword);
				}

			} else {
				SessionErrors.add(actionRequest, "Password not match");	
			}		
		}
	}


}
