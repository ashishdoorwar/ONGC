package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.TEA;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.util.Enumeration;
import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import nl.captcha.servlet.CaptchaServletUtil;


/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+VigilancePortletKeys.UserVigilance,
				"mvc.command.name=userLogin"
		}, service = MVCRenderCommand.class
		)
public class UserLoginRenderCommand implements MVCRenderCommand{
	
	private static final Log LOGGER = LogFactoryUtil.getLog(UserLoginRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		String userName=ParamUtil.getString(renderRequest, "username");
//		String otpChoice=ParamUtil.getString(renderRequest, "group1");
		LOGGER.info(userName);
		if (userName.trim().equals("") ) {
			SessionErrors.add(renderRequest, "Enter UserName");
		} 

		String answer= 	ParamUtil.getString(renderRequest,"captchText");
		if(! CaptchaServletUtil.checkCaptcha(answer, renderRequest)) {
			SessionErrors.add(renderRequest, "errorMsg");
			return "/jsp/userLogin.jsp";
		}
		VigilanceUser user = VigilanceFactory.getUserServiceInstance().getUserByEmailId(userName);
		if(user==null) {
			SessionErrors.add(renderRequest, "Incorrect Username");	
			return "/jsp/userLogin.jsp";
		} else {
			final PortletSession psession = renderRequest.getPortletSession();
			psession.setAttribute("loginId", userName, PortletSession.APPLICATION_SCOPE);
//			String otp=ONGCUtil.getInstance().generateOTP();
			int nextInt = new Random().nextInt(999999);
			String otp = String.valueOf(nextInt);
//			VigilanceFactory.getComplaintServiceInstance().sendOPT(user.getEmailId() , otp);
			if(user.getMobile().substring(0, 2).equals("91")){
				ONGCUtil.getInstance().sendOTPOnSMS(user.getMobile(), otp , VigilanceConstant.OPT_TYPE_LOGIN);
				VigilanceFactory.getOptManagementInstance().insertLoginOPT(otp, user.getEmailId());
			}
			renderRequest.setAttribute("loginId", userName);
			renderRequest.setAttribute("otp", otp);
			renderRequest.setAttribute("userData", user);
			
			return "/jsp/UserOpt.jsp";
		}
	}

}
