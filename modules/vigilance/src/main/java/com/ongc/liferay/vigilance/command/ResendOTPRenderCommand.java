package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.ws.rs.core.Request;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+VigilancePortletKeys.UserVigilance,
				"mvc.command.name=resend_otp"
		}, service = MVCRenderCommand.class
		)
public class ResendOTPRenderCommand implements MVCRenderCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(ResendOTPRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int nextInt = new Random().nextInt(999999);
		String otp = String.valueOf(nextInt);
		final PortletSession psession = renderRequest.getPortletSession();
		String userName=(String) psession.getAttribute("loginId",PortletSession.APPLICATION_SCOPE);
		psession.setAttribute("loginId", userName, PortletSession.APPLICATION_SCOPE);
		VigilanceUser user = VigilanceFactory.getUserServiceInstance().getUserByEmailId(userName);
		if(user==null) {
			SessionErrors.add(renderRequest, "Incorrect Username");	
			return "/jsp/userLogin.jsp";
		}else {
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
