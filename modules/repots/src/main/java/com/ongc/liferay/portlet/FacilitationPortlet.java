package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Facilitation Center",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/facilitation/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Facilitation,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class FacilitationPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doView(renderRequest, renderResponse);
	}

	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String postId = ParamUtil.getString(resourceRequest, "postId");
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flage = false;
		JSONArray jsonArr =null;
		try {
			PrintWriter out=resourceResponse.getWriter();
			String query = "UPDATE ONGC_FACILITATION SET STATUS = ? WHERE ID = ?";
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "D");
			pstmt.setLong(2, Long.parseLong(postId));
			flage = 0 < pstmt.executeUpdate();
			jsonArr = JSONFactoryUtil.createJSONArray();
			JSONObject json = JSONFactoryUtil.createJSONObject();
			 json.put("flageKey", flage);
			 jsonArr.put(json);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt);
		}
		resourceResponse.getWriter().write(jsonArr.toString());
		//super.serveResource(resourceRequest, resourceResponse);
	}
}
