package com.ongc.liferay.reports.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.Impl.AskExpertDaoImpl;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ONGC REPORT",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Profile",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/profile/updateUserForm.jsp",
		"javax.portlet.name=" + ReportsPortletKeys.Profile,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProfilePortlet extends MVCPortlet{
	private UserService userService=new UserServiceImpl();
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		User user;
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String cpf_number = httpReq.getParameter("cpf_number");
		if(cpf_number!=null) {
		user = userService.getUserByCPFNumber(cpf_number);
		} else {
		user = userService.getUser();}
		renderRequest.setAttribute("employee", user);
		super.doView(renderRequest, renderResponse);
	}

}
