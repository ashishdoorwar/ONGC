package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.UserDao;
import com.ongc.liferay.reports.dao.Impl.UserDaoImpl;


import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Birthday,
		 "mvc.command.name=sendBirthdayMsg"
		 }, service = MVCRenderCommand.class
		 )

public class BirthdayMsgRenderCommand implements MVCRenderCommand{
	public final static String renderingEmployeePagePath = "/birthday/birthdayMsg.jsp";
	
	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String cpfNo=ParamUtil.getString(renderRequest,"cpfNo");
		String name=ParamUtil.getString(renderRequest,"name");
		renderRequest.setAttribute("name", name);
		renderRequest.setAttribute("cpfNo", cpfNo);
		return renderingEmployeePagePath;
	}
}
