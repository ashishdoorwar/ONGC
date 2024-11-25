package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;

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
				"javax.portlet.name="+SponsorshipPortletKeys.SPONSORSHIP,
				"mvc.command.name=userLogout"
		}, service = MVCRenderCommand.class
		)
public class UserLogoutRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		final PortletSession psession = renderRequest.getPortletSession();
		psession.removeAttribute("loginId");
		
		return "/view.jsp";
	}
}
