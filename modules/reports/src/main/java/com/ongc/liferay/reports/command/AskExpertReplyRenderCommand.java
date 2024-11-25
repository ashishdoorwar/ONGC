package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.AskExpert,
		 "mvc.command.name=askExpertReply"
		 }, service = MVCRenderCommand.class
		 )

public class AskExpertReplyRenderCommand implements MVCRenderCommand{
	
	public final static String renderingEmployeePagePath = "/askExpert/askExpertReplyForm.jsp";
	private AskExpertService askExpertService = new AskExpertServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		int queryId=ParamUtil.getInteger(renderRequest,"queryId");
		String backURL=ParamUtil.getString(renderRequest,"backURL");
		AskExpert viewAskExpert = askExpertService.viewAskExpert(queryId);
		renderRequest.setAttribute("viewAskExpert", viewAskExpert);
		renderRequest.setAttribute("backURL", backURL);
		
		return renderingEmployeePagePath;
	}
}
