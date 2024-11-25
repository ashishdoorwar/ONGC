package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.OrganizationBean;
import com.ongc.liferay.reports.service.EDirectoryService;
import com.ongc.liferay.reports.service.Impl.EDirectoryServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		 property = {
		 "javax.portlet.name="+ReportsPortletKeys.EDirectory,
		 "mvc.command.name=search_organization"
		 }, service = MVCRenderCommand.class
		 )
public class EDirectorySearchOrganizationRenderCommand implements MVCRenderCommand{

	private EDirectoryService eDirectoryService=new EDirectoryServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String organizationName=ParamUtil.getString(renderRequest, "organizationName");
		
		OrganizationBean organizationBean =new OrganizationBean();
		organizationBean.setSubsiDaryId(organizationName);
		List<OrganizationBean> searchOrganization = eDirectoryService.searchOrganization(organizationBean );
		renderRequest.setAttribute("searchOrganization", searchOrganization);
		return "/eDirectory/searchOrgResult.jsp";
	}
	

}
