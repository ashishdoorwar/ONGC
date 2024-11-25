package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;

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
		 "mvc.command.name=edit_sponsorship_program_model"
		 }, service = MVCRenderCommand.class
		 )
public class EditSponsorshipRenderCommand implements MVCRenderCommand {
	private SponsorshipService sponsorshipService=new SponsorshipServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int id=ParamUtil.getInteger(renderRequest, "id");
		SponsorshipBean sponsorshipBean=sponsorshipService.selectSponsorshipById(id);
		renderRequest.setAttribute("sponsorshipBean", sponsorshipBean);
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		return "/sponsorship/editSponsorship.jsp";
	}

}
