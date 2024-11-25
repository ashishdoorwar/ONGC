package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.FeedbackCategory;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.service.FeedbackCategoryService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.Impl.FeedbackCategoryServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.util.ReportConstant;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=searchFeedbackPost"
		 }, service = MVCRenderCommand.class
		 )

public class FeedbackPostSearchRenderCommand implements MVCRenderCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(FeedbackPostSearchRenderCommand.class);
	
	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();;
	private FeedbackCategoryService feedbackCategoryService =new FeedbackCategoryServiceImpl();
	
	public final static String renderingFeedbackSearchPagePath = "/feedback/viewSearchFeedback.jsp";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int catId = ParamUtil.getInteger(renderRequest,"category");
		String startDate = ParamUtil.getString(renderRequest,"startDate");
		String endDate = ParamUtil.getString(renderRequest,"endDate");
		String postId = ParamUtil.getString(renderRequest,"postID");
		FeedbackPost post = null;
		FeedbackCategory category = new FeedbackCategory();
		category.setCategoryId(catId);
		category.setStartDate(startDate);
		category.setEndDate(endDate);
		category.setPostId(postId);
		int page = 1;
		int recordsPerPage = 20;
		List<FeedbackPost> postList = feedbackPostService.searchPostList(category);
		int noOfRecords = postList.size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//		if (post.getPageId() > 0)
//			page = post.getPageId();
//		int start = (page - 1) * recordsPerPage;
//		int end = start + recordsPerPage;
		List<FeedbackCategory> categoryList = feedbackCategoryService.getCategoryListByFeedbakId(category.getFeedbackId());
		renderRequest.setAttribute("postList", postList);
		renderRequest.setAttribute("categoryList", categoryList);
		renderRequest.setAttribute("noOfPages", noOfPages);
		renderRequest.setAttribute("currentPage", page);
		renderRequest.setAttribute("startDate", startDate);
		renderRequest.setAttribute("endDate", endDate);
		renderRequest.setAttribute("countRow", noOfRecords);
		
		return renderingFeedbackSearchPagePath;
	}

}
