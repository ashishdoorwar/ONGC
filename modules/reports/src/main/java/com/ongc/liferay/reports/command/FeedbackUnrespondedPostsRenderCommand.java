package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.FeedbackCategory;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.service.FeedbackCategoryService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCategoryServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"javax.portlet.name="+ ReportsPortletKeys.Feedback,
				"mvc.command.name=unrespondedPosts"
		}, service = MVCRenderCommand.class
		)

public class FeedbackUnrespondedPostsRenderCommand implements MVCRenderCommand{

	public final static String renderingFeedbackSearchPagePath = "/feedback/viewpendingForm.jsp";

	private static final Log log = LogFactoryUtil.getLog(FeedbackUnrespondedPostsRenderCommand.class);
	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private FeedbackCategoryService feedbackCategoryService =new FeedbackCategoryServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		FeedbackPost post = new FeedbackPost();
		int noOfRecords = 0;
		int page = 1;

		int recordsPerPage = 20;
		if (post.getPageId() > 0)
			page = post.getPageId();

		int start = (page - 1) * recordsPerPage;
		int end = start + recordsPerPage;
		List<FeedbackCategory> categoryList = feedbackCategoryService.getCategoryListByFeedbakId(1);
		com.ongc.liferay.reports.model.User user = userService.getUser();
		String username = user.getCpfNo();
		String role = feedbackCategoryService.getHREnablersRole(username);
		List<FeedbackPost> postList = new ArrayList<FeedbackPost>();
		if (role.trim().equalsIgnoreCase("L1")) {
//			 String location =categoryDao.getLocation(username);
//			 System.out.println("openViewpendingForm1 "+location);
			String sublocation = feedbackCategoryService.getsubLocation(username);
			if (username.equalsIgnoreCase("76121") || username.equalsIgnoreCase("78619")) {
				postList = feedbackPostService.getfeedbackList("All", start, end);
				noOfRecords = feedbackPostService.getNoOfLocalFeedbacks("All");
			} else {
				postList = feedbackPostService.getfeedbackList(sublocation, start, end);
				noOfRecords = feedbackPostService.getNoOfLocalFeedbacks(sublocation);
			}
		}
//		 if(enablers.getRole().trim().equalsIgnoreCase("L2") && enablers.getHrCatId()==feedbackPost.getHrCatId()){
		if (role.trim().equalsIgnoreCase("L2")) {
			postList = feedbackPostService.getfeedbackListCorp(username, start, end);
			noOfRecords = feedbackPostService.getNoOfCorpFeedbacks(username);
		}
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		renderRequest.setAttribute("postList", postList);
		renderRequest.setAttribute("categoryList", categoryList);
		renderRequest.setAttribute("noOfPages", noOfPages);
		renderRequest.setAttribute("pageStart", page);
		renderRequest.setAttribute("noOfRecords", noOfRecords);
		renderRequest.setAttribute("currentPage", page);
		//renderRequest.setSessionAttribute(ReportConstant.FEEDBACKPOST_LIST_ATTR_NAME,postList);
		return renderingFeedbackSearchPagePath;
	}

}
