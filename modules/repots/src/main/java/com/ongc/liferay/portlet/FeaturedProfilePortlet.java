package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;
import com.ongc.liferay.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
				"javax.portlet.display-name=Featured Profile",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/featuredProfile/view.jsp",
				"javax.portlet.name=" + RepotsPortletKeys.FeaturedProfile,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class FeaturedProfilePortlet extends MVCPortlet {
	
		private UserDao userDao = new UserDao();

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User userByEmailId = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
		String query = "select cpfnum from featured_profile where cpfnum='"+ userByEmailId.getCpfNo() + "'";
        Connection conn = null;
		Statement stmt = null;ResultSet res = null;
		boolean existRecord=false;
		try {
			conn = DatasourceConnection.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
				if (res.next()) {
					existRecord = true;
				}
				renderRequest.setAttribute("existRecord", existRecord);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, stmt,res);
		}
	   super.doView(renderRequest, renderResponse);
	}
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Repots");				
		String filePath = szResBundl.getString("featuredProfile").toString().trim();

		String cpfno = ParamUtil.getString(actionRequest, "cpfno1");
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("imgfile");
		File file = uploadPortletRequest.getFile("imgfile");
		String dbFileName;
		dbFileName=String.valueOf(cpfno+file.getName().substring(file.getName().lastIndexOf(".")));
		File to = new File(filePath+dbFileName);
		try {
			CommonUtil.copy(file, to);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String high1 = ParamUtil.getString(actionRequest, "high1");
		String high2 = ParamUtil.getString(actionRequest, "high2");
		String high3 = ParamUtil.getString(actionRequest, "high3");
		String high4 = ParamUtil.getString(actionRequest, "high4");
		String high5 = ParamUtil.getString(actionRequest, "high5");
		String about = ParamUtil.getString(actionRequest, "about");
		String passion = ParamUtil.getString(actionRequest, "passion");
		String others = ParamUtil.getString(actionRequest, "others");
		Connection conn=null;
		boolean existRecord=false;
		PreparedStatement ps=null;
		ResultSet set=null;
		String msg = null;
		int count = 0;
		byte[] b = null;
		try {
			conn = DatasourceConnection.getConnection();
			ps = conn.prepareStatement("insert into featured_profile(cpfnum,high1,high2,high3,high4,high5,about,passion,others,status,photo,f_id) values(?,?,?,?,?,?,?,?,?,'N',?,?)");
			ps.setString(1, cpfno);
			ps.setString(2, high1);
			ps.setString(3, high2);
			ps.setString(4, high3);
			ps.setString(5, high4);
			ps.setString(6, high5);
			ps.setString(7, about);
			ps.setString(8, passion);
			ps.setString(9, others);
			ps.setBytes(10, b);

			ps.setInt(11, getMaxIdFromTable("featured_profile", "f_id"));
			count = ps.executeUpdate();
			if (count > 0) {
				msg="Success";
				actionRequest.setAttribute("msg", msg);
			} else {msg="Failure";
			actionRequest.setAttribute("msg", msg);}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, ps);
		}
		actionRequest.setAttribute("existRecord", existRecord);
		//actionResponse.sendRedirect("mvcPath", "/featuredProfile/view.jsp");
	}
	
	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;

		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection=null;
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt,rs );
		}

		return ++id;
	}
	//super.processAction(actionRequest, actionResponse);

}
