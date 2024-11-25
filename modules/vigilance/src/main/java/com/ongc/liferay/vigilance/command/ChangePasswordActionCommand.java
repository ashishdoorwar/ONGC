package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.exception.UserChangePasswordException;
import com.ongc.liferay.vigilance.model.VigilanceUser;
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
				"javax.portlet.name=" +VigilancePortletKeys.UserVigilance,
				"mvc.command.name=change_password"
		},
		service = MVCActionCommand.class
		)
public class ChangePasswordActionCommand extends BaseMVCActionCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(ChangePasswordActionCommand.class);
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String newPassword=ParamUtil.getString(actionRequest, "newPassword");
		String oldPassword=ParamUtil.getString(actionRequest, "oldPassword");
		String confirmPassword=ParamUtil.getString(actionRequest, "confirmPassword");
		String userName=ParamUtil.getString(actionRequest, "loginId");
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

			VigilanceUser vigilanceUser = VigilanceFactory.getUserServiceInstance().getUserByEmailId(userName);

			String oldEndPass = TEA.encrypt(oldPassword, vigilanceUser.getEmailId() );
			String dbPass = vigilanceUser.getPassword();
			LOGGER.info(newPassword);
			LOGGER.info(confirmPassword);
			LOGGER.info(oldPassword);
			if (newPassword.equals(confirmPassword)) {

				if (!oldEndPass.equals(dbPass)) { 
					LOGGER.info("Old Password Not Match");
					SessionErrors.add(actionRequest, "Old Password Not Match");
				} 
				int i = PasswordValidator.getInstance().validatePassword(newPassword, vigilanceUser.getEmailId());
				LOGGER.info(i);
				if (i != 0 && i > 0 ) {
					SessionErrors.add(actionRequest, PasswordValidator.getInstance().getMessage(i));
				} else {
					VigilanceFactory.getUserServiceInstance().changePassword(vigilanceUser.getRegistrationId() , vigilanceUser.getEmailId(),newPassword);

					LOGGER.info("password change");
					//				VigilanceUser user=	VigilanceFactory.getUserServiceInstance().getUserByEmailId(userName);
				}

			} else {
				LOGGER.info("Password not match");
				SessionErrors.add(actionRequest, "Password not match");
			}		
		}

	}

}
