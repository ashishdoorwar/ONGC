package com.ongc.liferay.vigilance.command;

import com.liferay.captcha.simplecaptcha.SimpleCaptchaImpl;
import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + VigilancePortletKeys.UserVigilance,
				"mvc.command.name=/userLogin/captcha/image"
		},
		service = MVCResourceCommand.class
		)
public class VigilanceCaptchaResourceCommand implements MVCResourceCommand{

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			SimpleCaptchaImpl ss;
			CaptchaUtil.serveImage(resourceRequest, resourceResponse);


		}catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;

	}

}
