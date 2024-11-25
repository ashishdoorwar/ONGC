package com.ongc.liferay.reports.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.User;
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
			"mvc.command.name=search_location"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchLocationActionCommand  extends BaseMVCActionCommand {

	private EDirectoryService eDirectoryService=new EDirectoryServiceImpl();
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		
		String cPFNumber=ParamUtil.getString(actionRequest, "cPFNumber");
		String empName=ParamUtil.getString(actionRequest, "empName");
		String department=ParamUtil.getString(actionRequest, "department");
		String location=ParamUtil.getString(actionRequest, "location");
		
		
		
		
		User user =new User();
		user.setCpfNo(cPFNumber);
		user.setEmpName(empName);
		user.setDepartment(department);
		user.setPlacePostingTrsfr(location);
		
		List<User> searchLocation = eDirectoryService.searchLocation(user);
		actionRequest.setAttribute("searchLocation", searchLocation);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchLocationResult.jsp");	
	}

}
