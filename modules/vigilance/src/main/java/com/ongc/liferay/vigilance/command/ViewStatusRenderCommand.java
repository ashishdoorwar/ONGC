package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
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
				"javax.portlet.name="+VigilancePortletKeys.UserVigilance,
				"mvc.command.name=viewComplaintStatus"
		}, service = MVCRenderCommand.class
		)
public class ViewStatusRenderCommand implements MVCRenderCommand {

	private static final Log LOGGER = LogFactoryUtil.getLog(ViewStatusRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		LOGGER.info(login);
		VigilanceUser userData = VigilanceFactory.getUserServiceInstance().getUserByEmailId(login);
		
		LOGGER.info(userData);
		
		renderRequest.setAttribute("userData", userData);
		return "/jsp/ViewComplaintStatus.jsp";
	}
	

}
