package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +SponsorshipPortletKeys.SPONSORSHIP,
			"mvc.command.name=delete_sponsorship"
		},
		service = MVCActionCommand.class
	)
public class DeleteSponsorshipActionCommnad extends BaseMVCActionCommand {
	private SponsorshipService sponsorshipService=new SponsorshipServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		int id=ParamUtil.getInteger(actionRequest, "id");
		SponsorshipBean sponBean = new SponsorshipBean();
		sponBean.setSponid(id);
		FilterBean filterBean=new FilterBean();
		PortletSession psession = actionRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		actionRequest.setAttribute("loginId", login);
		List<SponsorshipBean> sponsorshipListData =sponsorshipService.displayAllSponship(filterBean);
		actionRequest.setAttribute("sponsorshipListData", sponsorshipListData);
		List<SubjectBean> subjectBeans= sponsorshipService.getSubjectList();
		actionRequest.setAttribute("subjectBeans", subjectBeans);
		
		List<FilterBean> filterBeans= sponsorshipService.getStatusList() ;
		actionRequest.setAttribute("filterBeans", filterBeans);
		List<SubjectBean> createdbyList= sponsorshipService.getLocation();
		actionRequest.setAttribute("createdbyList", createdbyList);
		sponsorshipService.deleteSponsorshipDetails(sponBean);
	}

}
