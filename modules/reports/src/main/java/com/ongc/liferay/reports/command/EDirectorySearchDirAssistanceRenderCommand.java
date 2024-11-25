package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.DirAssistanceBean;
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
		 "mvc.command.name=search_dir_assistance"
		 }, service = MVCRenderCommand.class
		 )
public class EDirectorySearchDirAssistanceRenderCommand implements MVCRenderCommand {

	 private EDirectoryService eDirectoryService=new EDirectoryServiceImpl();;
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String directoryAssistanceName=ParamUtil.getString(renderRequest, "directoryAssistanceName");
		DirAssistanceBean org=new DirAssistanceBean();
		org.setLocation(directoryAssistanceName);
		List<DirAssistanceBean> searchAssisDir = eDirectoryService.searchAssisDir(org);
		renderRequest.setAttribute("searchAssisDir", searchAssisDir);
		return "/eDirectory/searchDirAssistanceResult.jsp";
	}

}
