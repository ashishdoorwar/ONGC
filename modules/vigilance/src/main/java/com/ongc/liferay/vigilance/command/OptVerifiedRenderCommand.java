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
				"mvc.command.name=verify_otp"
		}, service = MVCRenderCommand.class
		)
public class OptVerifiedRenderCommand implements MVCRenderCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(OptVerifiedRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String otp=ParamUtil.getString(renderRequest, "otp");
//		String otpChoice=ParamUtil.getString(renderRequest, "otpChoice");
		String generatedOtp=ParamUtil.getString(renderRequest, "generatedOtp");
		LOGGER.info(otp+"--------"+generatedOtp);
		final PortletSession psession = renderRequest.getPortletSession();
		String userName=(String) psession.getAttribute("loginId",PortletSession.APPLICATION_SCOPE);
		psession.setAttribute("loginId", userName, PortletSession.APPLICATION_SCOPE);
		String verificationTime = VigilanceFactory.getOptManagementInstance().getLoginOtpVerified(userName);
		System.out.println("Otp Validity Timing=========================>"+verificationTime);
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d,d1 = null,d2,currentTime = null;
		 long seconds = 0;
			try {
				d = df.parse(verificationTime);

				 Calendar cal1 = Calendar.getInstance();
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(d);
				 cal.add(Calendar.MINUTE, 10);
				 String newTime = df.format(cal.getTime());
				 d1=df.parse(newTime);
				 d2= cal.getTime();
				 long diff = d2.getTime() - d.getTime();
//				 long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); 
//				 seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
//				 System.out.println("d=================================>"+d);
				currentTime = df.parse(df.format(cal1.getTime()));
				 System.out.println("Current Timing =====================>"+ df.format(cal1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
				 System.out.println("newTime Difference=================================>"+seconds+"d1========?"+d1);
		if(d1.after(currentTime)) {
			System.out.println("Inside checking timing is greater then");
		if(generatedOtp!=null && otp!=null && generatedOtp.equals(otp)) {

			System.out.println("generatedOtp!=null && otp!=null && generatedOtp.equals(otp)=====>"+generatedOtp!=null && otp!=null && generatedOtp.equals(otp));
			//LOGGER.info(userName);
			VigilanceUser user = VigilanceFactory.getUserServiceInstance().getUserByEmailId(userName);
//			if(!otpChoice.equalsIgnoreCase("Mobile"))
//			{
//				VigilanceFactory.getComplaintServiceInstance().sendOPT(user.getEmailId() , otp);
//			}
//			else{
//				ONGCUtil.getInstance().sendOTPOnSMS(user.getMobile(), otp , VigilanceConstant.OPT_TYPE_LOGIN);
//			}
			renderRequest.setAttribute("userData", user);
			return "/jsp/userHome.jsp";
		}
		SessionErrors.add(renderRequest, "Incorrect OTP");	
		renderRequest.setAttribute("otp", generatedOtp);
		renderRequest.setAttribute("d1", d1);
		return "/jsp/UserOpt.jsp";
		}else {
			System.out.println("Inside Else===============================>");
			SessionErrors.add(renderRequest, "OTPExceeded");
			String OTPExceeded="OTPExceeded";
			renderRequest.setAttribute("OTPExceeded", OTPExceeded);
			return "/jsp/UserOpt.jsp";
		}
		
	}
}
