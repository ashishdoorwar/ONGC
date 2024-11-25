package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.StdBean;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.EDirectoryService;
import com.ongc.liferay.reports.service.TelecomDirService;
import com.ongc.liferay.reports.service.Impl.EDirectoryServiceImpl;
import com.ongc.liferay.reports.service.Impl.TelecomDirServiceImpl;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +ReportsPortletKeys.EDirectory,
			"mvc.command.name=search_std"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchStdActionCommnand extends BaseMVCActionCommand  {

	private TelecomDirService telecomDirService=new TelecomDirServiceImpl(); 
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String stateName=ParamUtil.getString(actionRequest, "stateName");
		String lcda=ParamUtil.getString(actionRequest, "lcda");
		String sdcaname=ParamUtil.getString(actionRequest, "sdcaname");
		String sdca=ParamUtil.getString(actionRequest, "sdca");

		
		StdBean std=new StdBean();
		std.setCircleName(stateName);
		std.setLdcsName(lcda);
		std.setSdcaName(sdcaname);
		std.setSdcaCode(sdca);
		List<StdBean> stdBeans = telecomDirService.searchStdCode(std);
		actionRequest.setAttribute("stdBeans", stdBeans);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchStdResult.jsp");	
	}

}
