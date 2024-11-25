package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;

import java.util.List;

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
		 "mvc.command.name=search_sponsorship"
		 }, service = MVCRenderCommand.class
		 )
public class SearchSponsorshipRenderCommand implements MVCRenderCommand {

	private SponsorshipService  sponsorshipService= new SponsorshipServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		int subName =ParamUtil.getInteger(renderRequest, "subName");
		String publicationname =ParamUtil.getString(renderRequest, "publicationname");
		String recommendedby =ParamUtil.getString(renderRequest, "recommendedby");
		String purpose =ParamUtil.getString(renderRequest, "purpose");
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String status =ParamUtil.getString(renderRequest, "status");
		
		FilterBean filterBean=new FilterBean();
		filterBean.setSubjectid(subName);
		filterBean.setPublicationname(publicationname);
		filterBean.setPurpose(purpose);
		filterBean.setCreatedBy(createdBy);
		filterBean.setRefNo(refNo);
		filterBean.setStatus(status);
		filterBean.setRecommendedby(recommendedby);
		
		List<SponsorshipBean> sponsorshipListData =sponsorshipService.displayAllSponship(filterBean);
		renderRequest.setAttribute("sponsorshipListData", sponsorshipListData);
		List<SubjectBean> subjectBeans= sponsorshipService.getSubjectList();
		renderRequest.setAttribute("subjectBeans", subjectBeans);
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		List<FilterBean> filterBeans= sponsorshipService.getStatusList() ;
		renderRequest.setAttribute("filterBeans", filterBeans);
		List<SubjectBean> createdbyList= sponsorshipService.getLocation();
		renderRequest.setAttribute("createdbyList", createdbyList);
		
		
		return "/sponsorship/listSponsorship.jsp";
	}

}
