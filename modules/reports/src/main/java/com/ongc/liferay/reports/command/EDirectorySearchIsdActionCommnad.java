package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.IsdBean;
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
			"mvc.command.name=search_isd"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchIsdActionCommnad extends BaseMVCActionCommand  {

	private TelecomDirService telecomDirService=new TelecomDirServiceImpl();; 
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String country=ParamUtil.getString(actionRequest, "country");
		String isdCode=ParamUtil.getString(actionRequest, "isdCode");
		String iddCode=ParamUtil.getString(actionRequest, "iddCode");

		IsdBean isd =new IsdBean();
		isd.setCountry(country);
		isd.setIsdCode(isdCode);
		isd.setIddCode(iddCode);
		List<IsdBean> isdBeans = telecomDirService.searchIsdCode(isd );
		actionRequest.setAttribute("isdBeans", isdBeans);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchIsdResult.jsp");	
	}

}
