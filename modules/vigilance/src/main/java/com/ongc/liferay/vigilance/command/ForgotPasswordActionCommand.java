package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.model.VigilanceContent;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.CommonUtil;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+VigilancePortletKeys.UserVigilance,
				"mvc.command.name=forgot_password"
		}, service = MVCRenderCommand.class
		)
public class ForgotPasswordActionCommand implements MVCRenderCommand{


	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String username=ParamUtil.getString(renderRequest, "username");
		String securityQuestion=ParamUtil.getString(renderRequest, "securityQuestion");
		String securityAnswer=ParamUtil.getString(renderRequest, "securityAnswer");

		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
		}
		else {
		VigilanceUser vigilanceUser = VigilanceFactory.getUserServiceInstance().getUserByEmailId(username);

		List<VigilanceContent> constants = VigilanceFactory.getContentManagementInstance().getContentByType(VigilanceConstant.VIGILANCE_CONTENT_TYPE_QUESTION);


		if (vigilanceUser != null && securityQuestion.equalsIgnoreCase(vigilanceUser.getSecurityQuestion()) && securityAnswer.equalsIgnoreCase(
				vigilanceUser.getSecurityAnswer())) {

			String pass = ONGCUtil.getInstance().generatePassword();
			String msg="";
			if(!vigilanceUser.getMobile().substring(0, 2).equals("91")){
				ONGCUtil.getInstance().sendPasswordOnEmail(vigilanceUser.getEmailId() , pass);
				msg="Your new password has been sent to your registered email ID";
			}
			else{
				ONGCUtil.getInstance().sendOTPOnSMS(vigilanceUser.getMobile(), pass , "pass");
				msg="Your new password has been sent to your registered mobile number";
			}

			VigilanceFactory.getUserServiceInstance().changePassword(vigilanceUser.getRegistrationId() , vigilanceUser.getEmailId(), pass);

		} else {
			if (vigilanceUser == null) {
				SessionErrors.add(renderRequest, "user_error");	
				return "/jsp/VigilanceForgotPassword.jsp";
			}else if (!securityQuestion.equalsIgnoreCase(vigilanceUser.getSecurityQuestion())) {
				SessionErrors.add(renderRequest, "question_error");
				return "/jsp/VigilanceForgotPassword.jsp";
			} else if (!securityAnswer.equalsIgnoreCase(vigilanceUser.getSecurityAnswer())) {
				SessionErrors.add(renderRequest, "answer_error");
				return "/jsp/VigilanceForgotPassword.jsp";
			}
			//			return INPUT;
		}
		}
		return "/jsp/userLogin.jsp";
	}

}
