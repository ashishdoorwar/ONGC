package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.Impl.FindOngcianManagementDaoImpl;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.FindOngcianService;
import com.ongc.liferay.reports.service.Impl.FindOngcianServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"javax.portlet.name="+ ReportsPortletKeys.FindOngcian,
				"mvc.command.name=searchEmployeeInfo"
		}, service = MVCRenderCommand.class
		)

public class FindOngcianRenderCommand implements MVCRenderCommand{

	private static final Log LOGGER = LogFactoryUtil.getLog(FindOngcianRenderCommand.class);
	public final static String renderingPagePath = "/findOngcian/findOngcianData.jsp";

	private FindOngcianService findOngcianService=new FindOngcianServiceImpl();

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String commandName= ParamUtil.getString(renderRequest, "mvcRenderCommandName");
		String cpfNo=ParamUtil.getString(renderRequest,"cpfNo");
		String employeeName=ParamUtil.getString(renderRequest,"employeeName");
		String employeeLevel=ParamUtil.getString(renderRequest,"employeeLevel");
		String currentPlace=ParamUtil.getString(renderRequest,"currentPlace");
		String mobile=ParamUtil.getString(renderRequest,"mobile");
		String passionCategory=ParamUtil.getString(renderRequest,"passionCategory");
		String passion=ParamUtil.getString(renderRequest,"passion");
		//System.out.println("passion"+passion+"passionCategory"+passionCategory+"mobile"+mobile+"currentPlace"+currentPlace+"employeeLevel"+employeeLevel+"employeeName"+employeeName+"cpfNo"+cpfNo);
		List<User> ongcian = null;
		User users=new User();
		users.setCpfNo(cpfNo);
		users.setEmpName(employeeName);
		users.setEmpLevel(employeeLevel);
		users.setCurrentPlace(currentPlace);
		users.setMobileNo(mobile);
		users.setPassionId(passionCategory);
		users.setSubPassionId(passion);
		//System.out.println("hi");
		if(passion.equalsIgnoreCase("-1") || passionCategory.equalsIgnoreCase("-1") || Validator.isNotNull(mobile) || currentPlace.equalsIgnoreCase("Select") || employeeLevel.equalsIgnoreCase("Select") || Validator.isNotNull(employeeName) || Validator.isNotNull(cpfNo)) {
		ongcian = findOngcianService.getOngcian(users);
		renderRequest.setAttribute("findOngician", ongcian);
		}
		return renderingPagePath;
	}

}
