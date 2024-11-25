package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.IncidentDao;
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

import org.apache.commons.io.FileUtils;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.REPOTS,
			"mvc.command.name=saveIncident"
		},
		service = MVCActionCommand.class
	)
public class IncidentActionCommand extends BaseMVCActionCommand{
	
	private IncidentDao incidentDao =new IncidentDao();	

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Repots");				
		String filePath = szResBundl.getString("incidentFilePath").toString().trim();
		String cpfNo= ParamUtil.getString(actionRequest, "cpfno");
		String subject= ParamUtil.getString(actionRequest, "subject");
		String category= ParamUtil.getString(actionRequest, "category");
		String description= ParamUtil.getString(actionRequest, "description");
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("imgfile");
		File file = uploadPortletRequest.getFile("imgfile");
		String title = fileName;
		try {
			boolean checkParameter = CommonUtil.checkParameter(actionRequest);
			if(checkParameter) {
				SessionErrors.add(actionRequest,"error");
				actionResponse.sendRedirect("mvcPath", "/incidentBoard/newincident.jsp");
			} else {
			Connection conn = DatasourceConnection.getConnection();
			boolean insertIncident = incidentDao.insertIncident(conn, subject, category, description, cpfNo, null, title);
			//File to = new File(filePath+title);
			FileUtils.copyFile(uploadPortletRequest.getFile("imgfile"), new File(filePath,fileName));
			//CommonUtil.copy(file, to);
			conn.close();
		} }catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
