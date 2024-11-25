package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.UtilityBean;
import com.ongc.liferay.reports.service.EDirectoryService;
import com.ongc.liferay.reports.service.Impl.EDirectoryServiceImpl;

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
			"mvc.command.name=search_utility"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchUtilityActionCommand  extends BaseMVCActionCommand {

	private EDirectoryService eDirectoryService=new EDirectoryServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		String department=ParamUtil.getString(actionRequest, "department");
		String location=ParamUtil.getString(actionRequest, "location");
		String utility=ParamUtil.getString(actionRequest, "utility");
		String subUtility=ParamUtil.getString(actionRequest, "subUtility");
		
		
		UtilityBean org =new UtilityBean();
		org.setDepartment(department);
		org.setLocation(location);
		org.setUtility(utility);
		org.setSubUtility(subUtility);
		
		List<UtilityBean> searchUtility = eDirectoryService.searchUtility(org );
		actionRequest.setAttribute("searchUtility", searchUtility);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchUtilityResult.jsp");	
	}

}
