package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.util.Properties;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;


@Component(
		property = {
				"javax.portlet.name="+ ReportsPortletKeys.EDirectory,
				"mvc.command.name=getUserProfileInfo"
		}, service = MVCRenderCommand.class
		)
public class GetProfileUserRenderCommand implements MVCRenderCommand {

	private UserService userService=new UserServiceImpl();

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ServiceContext serviceContext=ServiceContextThreadLocal.getServiceContext();
		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();
		User user = themeDisplay.getUser();
		com.ongc.liferay.reports.model.User employee = userService.getUser();
		renderRequest.setAttribute("employee", employee);

		return "/eDirectory/updateUserForm.jsp";
	}

}
