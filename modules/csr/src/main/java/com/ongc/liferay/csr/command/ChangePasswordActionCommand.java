package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.ChangePasswordBean;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.service.UserService;
import com.ongc.liferay.csr.service.Impl.UserServiceImpl;
import com.ongc.liferay.csr.util.CSRConstant;
import com.ongc.liferay.csr.util.CommonUtil;

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
				"mvc.command.name=change_password"
		},
		service = MVCActionCommand.class
		)
public class ChangePasswordActionCommand  extends BaseMVCActionCommand{
	private UserService	uDaoObj=new UserServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String flag = "SUCCESS";

		String loginId=ParamUtil.getString(actionRequest, "loginId");
		String oldPassword=ParamUtil.getString(actionRequest, "oldPassword");
		String newPassword=ParamUtil.getString(actionRequest, "newPassword");
		String confirmPassword=ParamUtil.getString(actionRequest, "confirmPassword");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/csr/editCsr.jsp");
		}
		else {

			ChangePasswordBean cpBeans=new ChangePasswordBean();
			PortletSession psession = actionRequest.getPortletSession();
			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			actionRequest.setAttribute("loginId", login);
			cpBeans.setOldPassword(oldPassword);
			cpBeans.setNewPassword(newPassword);
			cpBeans.setConfirmPassword(confirmPassword);
			try {
				//			User user = (User) actionRequest.getPortletSession().getAttribute(CSRConstant.USERDTL);

				if (loginId != null) {

					if (!uDaoObj.checkOldPassword(oldPassword,loginId)) {
					} else if (!(newPassword.length() > 5 && newPassword.length() <= 12)) {
					} else if (!confirmPassword.equals(confirmPassword)) {
					}

					if (uDaoObj.changePassword(cpBeans, loginId)) {
						//					flag = getAll();
					} else {
						flag = "input";
					}

				} else {
					//				flag = logoutHome();
				}
			} catch (Exception e) {
			}

		}
	}

}
