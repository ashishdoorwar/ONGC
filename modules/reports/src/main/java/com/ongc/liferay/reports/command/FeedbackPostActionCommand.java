package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.FeedbackCategory;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.FeedbackCategoryService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackCategoryServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.CommonUtil;
import com.ongc.liferay.reports.util.ReportConstant;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.AskExpert,
		 "mvc.command.name=feedbackPostCommentFormm"
		 }, service = MVCActionCommand.class
		 )

public class FeedbackPostActionCommand extends BaseMVCActionCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(FeedbackPostActionCommand.class);
	private UserService userService = new UserServiceImpl();
	private AskExpertService askExpertService = new AskExpertServiceImpl();
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		int domainId = ParamUtil.getInteger(actionRequest, "domain");
		String message = ParamUtil.getString(actionRequest, "message");
		String priority = ParamUtil.getString(actionRequest, "priority");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			}else {
		AskExpert askExpert = new AskExpert();
		User user= userService.getUser();
		askExpert.setDomainId(domainId);
		askExpert.setMessage(message);
		askExpert.setPriority(priority);
		askExpert.setCpfno(user.getCpfNo());
		askExpertService.saveAskExpert(askExpert);
		}
		actionResponse.sendRedirect("mvcPath", "/askExpert/askExpertForm.jsp");
	}
	
	

}
