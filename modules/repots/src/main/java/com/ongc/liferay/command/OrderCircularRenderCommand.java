package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PassionDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.MobileUserBean;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.model.OrderCircularCategory;
import com.ongc.liferay.model.Passion;
import com.ongc.liferay.model.User;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.OrderCircular,
		 "mvc.command.name=searchOrderCircular"
		 }, service = MVCRenderCommand.class
		 )

public class OrderCircularRenderCommand implements MVCRenderCommand{
	public final static String renderingOrderCircularPagePath = "/orderCircular/view.jsp";
	private OrderAndCircularService orderAndCircularService = new OrderAndCircularServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String startDate=ParamUtil.getString(renderRequest, "startDate");
		String endDate=ParamUtil.getString(renderRequest, "endDate");
		String subject=ParamUtil.getString(renderRequest, "keyword");
		String category=ParamUtil.getString(renderRequest, "category");
		renderRequest.setAttribute("startDate", startDate);
		renderRequest.setAttribute("endDate", endDate);
		List<OrderCircular> orderCircular = getOrderCircular(startDate,endDate,subject,category);
		renderRequest.setAttribute("orderCircular", orderCircular);
		List<OrderCircularCategory> list = orderAndCircularService.getOrderAndCircularCategory();
		renderRequest.setAttribute("categoryOrderCircular", list);
		return renderingOrderCircularPagePath;
	}
	
	private List<OrderCircular> getOrderCircular(String startDate,String endDate,String subject,String category) {
		System.out.println("startDate====>"+startDate+"endDate==========>"+endDate+"subject=====>"+subject+"category====>"+category);
		List<OrderCircular> list = null;
		if(Validator.isNotNull(startDate) || Validator.isNotNull(endDate) || Validator.isNotNull(subject) || Validator.isNotNull(category) ) {
		list = orderAndCircularService.selectOrderAndCircular(startDate, endDate, subject, category);}
		return list;
	}
}
