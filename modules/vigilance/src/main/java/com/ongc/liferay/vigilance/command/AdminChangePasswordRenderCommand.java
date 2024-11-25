package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

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
				"mvc.command.name=adminChangePassword"
		}, service = MVCRenderCommand.class
		)
public class AdminChangePasswordRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		VigilanceAdminUser userData = VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(login);
		renderRequest.setAttribute("userData", userData);
		return "/jsp/AdminChangePassword.jsp";
	}

}
