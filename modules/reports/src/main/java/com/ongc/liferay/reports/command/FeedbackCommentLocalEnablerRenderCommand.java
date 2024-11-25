package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=commentByLocal"
		 }, service = MVCRenderCommand.class
		 )
public class FeedbackCommentLocalEnablerRenderCommand implements MVCRenderCommand{


	public final static String renderingCommentByLocalPagePath = "/feedback/commentByLocal.jsp";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String postId = ParamUtil.getString(renderRequest, "postId");
		renderRequest.setAttribute("postId", postId);
		
		return renderingCommentByLocalPagePath;
	}

}
