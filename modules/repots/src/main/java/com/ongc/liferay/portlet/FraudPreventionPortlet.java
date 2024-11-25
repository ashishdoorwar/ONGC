package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PledgeDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				"javax.portlet.display-name=Fraud Pledge",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/fraudPrevention/view.jsp",
				"javax.portlet.name=" + RepotsPortletKeys.FraudPlegde,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class FraudPreventionPortlet extends MVCPortlet {
	
	private PledgeDao dao = new PledgeDao();
	private UserDao userDao = new UserDao();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User userByEmailId = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
		
		String type="FRAUD";
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<pledge>");
			int count = 0;
			count = dao.getPledgeCount(type);
			xmlBuffer.append("<count>").append(count).append("</count>");
			xmlBuffer.append("</pledge>");
			renderRequest.setAttribute("xmlBuffer", xmlBuffer.toString());
				boolean flag = false;
				String status= null;
				flag = dao.checkCpf(userByEmailId.getCpfNo(),type);
				if(flag) {
					status = "true";
				} else {
					status = "false";
				}
				renderRequest.setAttribute("xmlBufferCheckCPf", status);
				renderRequest.setAttribute("cpfNo", userByEmailId.getCpfNo());
				renderRequest.setAttribute("userName", userByEmailId.getEmpName());
				
		super.doView(renderRequest, renderResponse);
	}
}
