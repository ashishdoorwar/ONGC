package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.model.OrderCircularCategory;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=OrderCircular",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/orderCircular/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.OrderCircular,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class OrderCircularPortlet extends MVCPortlet {

	private OrderAndCircularService orderAndCircularService = new OrderAndCircularServiceImpl();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<OrderCircularCategory> list = orderAndCircularService.getOrderAndCircularCategory();
		renderRequest.setAttribute("categoryOrderCircular", list);
		List<OrderCircular> orderCircular =null;
		LocalDate todayDate = java.time.LocalDate.now();
		LocalDate endDate = todayDate.minusMonths(1);
		if(todayDate!=null || endDate!=null  ) {
			orderCircular = getOrderCircular(todayDate,endDate);}
		renderRequest.setAttribute("orderCircular", orderCircular);
		super.doView(renderRequest, renderResponse);
	}
	
	private List<OrderCircular> getOrderCircular(LocalDate todayDate, LocalDate endDate) {
		List<OrderCircular> list = null;
		list = orderAndCircularService.selectOrderAndCircular(String.valueOf(endDate.toString()), String.valueOf(todayDate), null, null);
		return list;
	}

}
