package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.WorkplaceBean;
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
			"mvc.command.name=search_workplace"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchWorkPlaceActionCommand extends BaseMVCActionCommand{

	private EDirectoryService eDirectoryService=new EDirectoryServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String department=ParamUtil.getString(actionRequest, "department");
		String location=ParamUtil.getString(actionRequest, "location");
		String workplace=ParamUtil.getString(actionRequest, "workplace");
		String subCategory=ParamUtil.getString(actionRequest, "subCategory");
		
		WorkplaceBean workplaceBean =new WorkplaceBean();
		workplaceBean.setDepartment(department);
		workplaceBean.setLocation(location);
		workplaceBean.setWorkplace(workplace);
		workplaceBean.setSubCategory(subCategory);
		
		List<WorkplaceBean> searchWorkplace = eDirectoryService.searchWorkplace(workplaceBean );
		actionRequest.setAttribute("searchWorkplace", searchWorkplace);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchWorkPlaceResult.jsp");	
	}

}
