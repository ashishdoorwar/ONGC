package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.Passion;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.PassionService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.PassionServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Birthday,
		 "javax.portlet.name="+ ReportsPortletKeys.AskExpert,
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "javax.portlet.name="+ ReportsPortletKeys.FindOngcian,
		 "mvc.command.name=getEmployeeInfo"
		 }, service = MVCRenderCommand.class
		 )

public class BirthdayEmployeeInfoRenderCommand implements MVCRenderCommand{
	public final static String renderingEmployeePagePath = "/employeeInfo.jsp";
	
	private UserService userService=new UserServiceImpl();
	private PassionService passionService= new PassionServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo=ParamUtil.getString(renderRequest,"cpfNo");
		User userByCPFNumber = userService.getUserByCPFNumber(cpfNo);
		List<Passion> userPassion = passionService.getUserPassion(cpfNo);
		renderRequest.setAttribute("userByCPFNumber", userByCPFNumber);
		renderRequest.setAttribute("userPassion", userPassion);
		
		return renderingEmployeePagePath;
	}
}
