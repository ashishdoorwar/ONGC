package com.ongc.liferay.vigilance.portlet;

import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;

import java.security.SecureRandom;
import java.util.Random;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;

/**
 * @author jiten
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Vigilance",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + VigilancePortletKeys.VIGILANCE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VigilancePortlet extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
		String act=ParamUtil.getString(resourceRequest, "captchaAct");
		if(act!=null && act.contentEquals("image")) {
			captchaImg(resourceRequest, resourceResponse);	
		}else {
			audioCaptcha(resourceRequest, resourceResponse);
		}
		}catch (Exception e) {
			e.printStackTrace();
				}
	}


	public void captchaImg(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			resourceResponse.setContentType("image/png");
			int x=0;
			String text="";
			Random rend = new SecureRandom();
			while(x<6) {
				text+=rend.nextInt(10)+"";
				x++;
			}
			
			Captcha captcha = new Captcha.Builder(200, 50)
					 .addText(text)
					 .addNoise()
					 .addNoise().addNoise()
					 .addNoise()
					 .addBackground()
					 .build();
		CaptchaServletUtil.writeImage(resourceResponse.getPortletOutputStream(), captcha.getImage());
		PortletSession sess=resourceRequest.getPortletSession(true);
		sess.setAttribute("ongc.captchaObj", captcha);
		}catch (Exception e) {
			e.printStackTrace();
				}
	}
	
	private boolean audioCaptcha(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			PortletSession sess=resourceRequest.getPortletSession();
			resourceResponse.setContentType("audio/wave");
			Captcha capt=(Captcha)	sess.getAttribute("ongc.captchaObj");
				AudioCaptcha captcha = new AudioCaptcha.Builder().addAnswer(capt.getAnswer())
						 .addVoice()
						  .addNoise()
						  .build();
				
				nl.captcha.servlet.CaptchaServletUtil.writeAudio(resourceResponse.getPortletOutputStream(),  captcha.getChallenge());
		}catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
}