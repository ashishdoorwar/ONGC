package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.MscBean;
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
			"mvc.command.name=search_msc"
		},
		service = MVCActionCommand.class
	)
public class EDirectorySearchMscActionCommand extends BaseMVCActionCommand  {

	private TelecomDirService telecomDirService=new TelecomDirServiceImpl(); 
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String network=ParamUtil.getString(actionRequest, "network");
		String circle=ParamUtil.getString(actionRequest, "circle");
		String operator=ParamUtil.getString(actionRequest, "operator");
		String mncCode=ParamUtil.getString(actionRequest, "mncCode");
		String codeDetails=ParamUtil.getString(actionRequest, "codeDetails");

		
		MscBean msc =new MscBean();
		msc.setNetwork(network);
		msc.setCircle(circle);
		msc.setMobileOperator(operator);
		msc.setMncCode(mncCode);
		msc.setCodeDetails(codeDetails);
		List<MscBean> mscBeans = telecomDirService.searchMscCode(msc );
		actionRequest.setAttribute("mscBeans", mscBeans);
		actionResponse.setRenderParameter(
				"mvcPath","/eDirectory/searchMscResult.jsp");	
	}

}
