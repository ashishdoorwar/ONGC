package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.ExpertReply;
import com.ongc.liferay.reports.model.FeedbackComment;
import com.ongc.liferay.reports.model.FeedbackHrCategory;
import com.ongc.liferay.reports.model.FeedbackHrEnablers;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.model.FeedbackReason;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;
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
		 "javax.portlet.name="+ ReportsPortletKeys.AskExpert,
		 "mvc.command.name=viewAskExpert"
		 }, service = MVCRenderCommand.class
		 )
public class AskExpertReplyViewRenderCommand implements MVCRenderCommand{

	public final static String renderingAskExpertReplyPagePath = "/askExpert/viewAskExpertComment.jsp";

	private AskExpertService askExpertService=new AskExpertServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		int queryId = ParamUtil.getInteger(renderRequest, "queryId");
		String backURL = ParamUtil.getString(renderRequest, "backURL");
		AskExpert askExpert = new AskExpert();
		askExpert=askExpertService.viewAskExpert(queryId);
		List<ExpertReply> replyList = askExpertService.getAskExpertReplyList(queryId);
		renderRequest.setAttribute("replyList", replyList);
		renderRequest.setAttribute("askExpert", askExpert);
		renderRequest.setAttribute("backURL", backURL);
		User user = userService.getUser();
//		setRoleCheck(dao.checkExpertRoleForDomain(cpfNo,askExpert.getDomainId()) || cpfNo.equalsIgnoreCase(askExpert.getCpfno()));
//		setRoleCheck(askExpertService.checkExpertRoleForDomain(user.getCpfNo(),askExpert.getDomainId()));
		
		return renderingAskExpertReplyPagePath;
	}

}
