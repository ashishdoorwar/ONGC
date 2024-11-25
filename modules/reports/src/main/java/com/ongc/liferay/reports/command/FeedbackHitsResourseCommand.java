package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.PageLikeService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCommentServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.PageLikeServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=hitsFeedback"
		 }, service = MVCResourceCommand.class
		 )
public class FeedbackHitsResourseCommand implements MVCResourceCommand{

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private PageLikeService pageLikeService = new PageLikeServiceImpl();

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		String postid = ParamUtil.getString(resourceRequest, "postid");
		String cpfnum = ParamUtil.getString(resourceRequest, "cpfnum");
		boolean flag = false;
		int feedhitcount=0;
		if (postid != null && cpfnum != null) {
		        flag = feedbackPostService.saveFeedbackHits(postid,cpfnum);
				feedhitcount = feedbackPostService.getFeedbackHitCount(postid);
			}
		resourceRequest.setAttribute("feedhitcount",feedhitcount);
		return false;
	}
}
