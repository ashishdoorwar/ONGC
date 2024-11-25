package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=adminLogout"
		}, service = MVCRenderCommand.class
		)
public class AdminLogoutRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		final PortletSession psession = renderRequest.getPortletSession();
		psession.removeAttribute("loginId");
		return "/jsp/adminLogin.jsp";
	}

}
