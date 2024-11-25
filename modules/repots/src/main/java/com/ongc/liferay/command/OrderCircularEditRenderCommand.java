package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Admin,
		 "mvc.command.name=editOrderCircularData"
		 }, service = MVCRenderCommand.class
		 )
public class OrderCircularEditRenderCommand implements MVCRenderCommand{
	private OrderAndCircularService orderAndCircularDao=new OrderAndCircularServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		int id=ParamUtil.getInteger(renderRequest, "id");
		OrderCircular orderCircular = orderAndCircularDao.selectOrderCircularById(id);
		renderRequest.setAttribute("orderCircular", orderCircular);
		return "/admin/view.jsp";
	}

}
