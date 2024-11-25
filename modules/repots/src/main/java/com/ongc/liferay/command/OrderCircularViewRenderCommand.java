package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Admin,
		 "mvc.command.name=editOrderCircular"
		 }, service = MVCRenderCommand.class
		 )
public class OrderCircularViewRenderCommand implements MVCRenderCommand{

	private OrderAndCircularService orderAndCircularDao=new OrderAndCircularServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String startDate =ParamUtil.getString(renderRequest, "startDate");
		String endDate =ParamUtil.getString(renderRequest, "endDate");
		String subject =ParamUtil.getString(renderRequest, "subject");
		String category =ParamUtil.getString(renderRequest, "category");
		orderAndCircularDao.selectOrderAndCircular(startDate, endDate, subject, category);
		List<OrderCircular> selectTopOrderAndCircular = orderAndCircularDao.selectTopOrderAndCircular();
		renderRequest.setAttribute("selectTopOrderAndCircular", selectTopOrderAndCircular);
		return "/admin/editOrderCircular.jsp";
	}

}
