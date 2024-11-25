package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.IncidentDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.IncidentBean;
import com.ongc.liferay.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adjecti
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SMALL Report",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Incident Board",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/incidentBoard/view.jsp",
		"javax.portlet.name=" + RepotsPortletKeys.REPOTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RepotsPortlet extends MVCPortlet {

	private UserDao userDao = new UserDao();
	private IncidentDao incidentDao =new IncidentDao();
	
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User userOngc =userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
		Connection conn = DatasourceConnection.getConnection();
		String cpf=null;
		List<IncidentBean> alist = null;
		try {
		alist=  incidentDao.getIncidents(conn);
		if(alist.size() >0  ){
		 cpf= alist.get(0).getPosteddby();
		 renderRequest.setAttribute("alist", alist);
		 renderRequest.setAttribute("userOngc", userOngc);
		  }
		}catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
		super.doView(renderRequest, renderResponse);
	}
	
	
}