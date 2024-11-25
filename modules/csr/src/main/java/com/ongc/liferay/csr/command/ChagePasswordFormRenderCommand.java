package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.csr.constants.CsrPortletKeys;

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
		 "javax.portlet.name="+CsrPortletKeys.CSR,
		 "mvc.command.name=openChagePasswordForm"
		 }, service = MVCRenderCommand.class
		 )
public class ChagePasswordFormRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		return "/csr/changepasswordForm.jsp";
	}

}
