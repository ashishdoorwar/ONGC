package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.FeedbackComment;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.PageLikeService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCommentServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.PageLikeServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.ReportConstant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=archive"
		 }, service = MVCResourceCommand.class
		 )
public class FeedbackCommentArchiveResourseCommand implements MVCResourceCommand{

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private FeedbackCommentService feedbackCommentService = new FeedbackCommentServiceImpl();

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		int postId = ParamUtil.getInteger(resourceRequest, "postId");
		int commentId = ParamUtil.getInteger(resourceRequest, "commentId");
		JSONArray jsonArr =null;
		try {
			PrintWriter out=resourceResponse.getWriter();
		
		System.out.println(postId+"/"+commentId);
		boolean archiveFeedbackComment = feedbackCommentService.archiveFeedbackComment(ReportConstant.COMMENT_STATUS_INACTIVE, commentId);
		boolean updateNoOfComment = feedbackPostService.updateNoOfComment(postId, false);
		if(archiveFeedbackComment) {
			jsonArr = JSONFactoryUtil.createJSONArray();
			JSONObject json = JSONFactoryUtil.createJSONObject();
			 json.put("flageKey", archiveFeedbackComment);
			 jsonArr.put(json);
			 resourceResponse.getWriter().write(jsonArr.toString());
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<FeedbackComment> commentList = feedbackCommentService.getCommentListByPostId(postId);
		//post = feedbackPostService.getFeedbackPost(postId);
		
		return false;
	}
}
