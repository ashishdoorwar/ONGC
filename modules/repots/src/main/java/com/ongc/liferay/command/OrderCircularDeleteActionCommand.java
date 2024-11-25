package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

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
			"javax.portlet.name=" +RepotsPortletKeys.Admin,
			"mvc.command.name=delete_order_circular"
		},
		service = MVCActionCommand.class
	)
public class OrderCircularDeleteActionCommand extends BaseMVCActionCommand{

	private OrderAndCircularService orderAndCircularService =new OrderAndCircularServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		int id=ParamUtil.getInteger(actionRequest, "id");
		orderAndCircularService.deleteOrderCircularById(id);
	}

	
}
