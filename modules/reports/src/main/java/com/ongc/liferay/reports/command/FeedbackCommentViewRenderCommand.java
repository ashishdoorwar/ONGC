package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.FeedbackComment;
import com.ongc.liferay.reports.model.FeedbackHrCategory;
import com.ongc.liferay.reports.model.FeedbackHrEnablers;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.model.FeedbackReason;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCommentServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=getCommentInfo"
		 }, service = MVCRenderCommand.class
		 )
public class FeedbackCommentViewRenderCommand implements MVCRenderCommand{

	public final static String renderingCommentPagePath = "/feedback/feedbackCommentInfo.jsp";

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private FeedbackCommentService feedbackCommentService = new FeedbackCommentServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		int postId = ParamUtil.getInteger(renderRequest, "postId");
		String backURL=ParamUtil.getString(renderRequest,"backURL");
		//String kword = null;
		FeedbackPost post = new FeedbackPost();
		post.setPostId(postId);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
		List<FeedbackComment> commentList = new ArrayList<FeedbackComment>();
		List<FeedbackReason> reasonList = new ArrayList<FeedbackReason>();
		List<FeedbackHrCategory> hrCategoryList = new ArrayList<FeedbackHrCategory>();
		List<FeedbackHrEnablers> HREnablers = new ArrayList<FeedbackHrEnablers>();
		//int hits = 0;		int likes = 0;		int dislikes = 0;
		User user = userService.getUser();
		post = feedbackPostService.getFeedbackPost(post);
		post.setPostDate(post.getPostDate());
		//post.setPostDateText(post.getPostDate().toString());
		if (user != null && (user.getCpfNo().equals("122385") || user.getCpfNo().equals("57392")
			|| user.getCpfNo().equals("121229")	|| user.getCpfNo().equals("76121")|| user.getCpfNo().equals("78619") 
			|| user.getCpfNo().equals("testb"))) {
			commentList = feedbackCommentService.getAllCommentListByPostId(postId);
		} else {
			commentList = feedbackCommentService.getCommentListByPostId(postId);
		}
		for (int i = 0; i < commentList.size(); i++) {
			commentList.get(i).setPostTextDate(sdf.format(commentList.get(i).getPostDate()));
		}
		reasonList = feedbackCommentService.getReasonsListByPostId(post.getPostId());
		hrCategoryList = feedbackPostService.getHRCategoryList();
		HREnablers = feedbackPostService.getHREnablersList();
		//this.pName = request.getParameter("pageName");
		//kword = request.getParameter("category.keyword");
		renderRequest.setAttribute("HREnablersList", HREnablers);
		renderRequest.setAttribute("user", user);
		renderRequest.setAttribute("hrCategoryList", hrCategoryList);
		renderRequest.setAttribute("commentList", commentList);
		renderRequest.setAttribute("reasonList", reasonList);
		renderRequest.setAttribute("post", post);
		renderRequest.setAttribute("backURL", backURL);
		int likecount = 0;
		int dislikecount = 0;
		int feedhitcount=0;
		feedhitcount = feedbackPostService.getFeedbackHitCount(String.valueOf(postId));
		likecount = feedbackPostService.getFeedbackLikeCount(String.valueOf(postId));
		dislikecount = feedbackPostService.getFeedbackDisLikeCount(String.valueOf(postId));
		renderRequest.setAttribute("likecount", likecount);
		renderRequest.setAttribute("dislikecount", dislikecount);
		renderRequest.setAttribute("feedhitcount", feedhitcount);
		return renderingCommentPagePath;
	}

}
