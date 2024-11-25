package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.AskExpert,
		 "mvc.command.name=getAskExpertData"
		 }, service = MVCRenderCommand.class
		 )
public class AskExpertViewRenderCommand implements MVCRenderCommand{
	
	private AskExpertService askExpertService = new AskExpertServiceImpl();
	private UserService userService = new UserServiceImpl();
	public final static String renderingAskExpertPath = "/askExpert/viewAskExpert.jsp";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		AskExpertSearch askExpertSearch = new AskExpertSearch();
		String startDate = ParamUtil.getString(renderRequest, "startDate");
		String endDate = ParamUtil.getString(renderRequest, "endDate");
		String domain = ParamUtil.getString(renderRequest, "domain");
		String queryId = ParamUtil.getString(renderRequest, "queryId");
		String keyword = ParamUtil.getString(renderRequest, "keyword");
		askExpertSearch.setDomainId(domain);
		askExpertSearch.setEndDate(endDate);
		askExpertSearch.setStartDate(startDate);
		askExpertSearch.setQueryid(queryId);
		askExpertSearch.setKeyword(keyword);
		
		try {
			List<AskExpert> qlist = askExpertService.getAllQueryList(askExpertSearch);
			List<Domain> domainList = askExpertService.getDomainList();			
			User user = userService.getUser();
//			setRoleCheck(askExpertService.checkExpertRole(cpfNo));
			renderRequest.setAttribute("domainList", domainList);
			renderRequest.setAttribute("qlist",qlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderingAskExpertPath;
	}

}
