package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.Covid19Dao;
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

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.Diaspora,
			"mvc.command.name=saveDiaspora"
		},
		service = MVCActionCommand.class
	)
public class DiasporaActionCommand extends BaseMVCActionCommand{

	private UserDao userDao = new UserDao();
	private Covid19Dao cdao = new Covid19Dao();	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Repots");				
		String filePath = szResBundl.getString("filePath").toString().trim()+"diaspora/";
		
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("imgfile");
		File file = uploadPortletRequest.getFile("imgfile");
		String title = fileName;

		String dbFileName;
		
		//File to = new File("G:/ONGC/Image/"+file.getName());
		//copy(file, to);
		String cpfNo=ParamUtil.getString(actionRequest, "cpfNo");
		String name=ParamUtil.getString(actionRequest, "name");
		String wname=ParamUtil.getString(actionRequest, "wname");
		String email=ParamUtil.getString(actionRequest, "email");
		int mobile=ParamUtil.getInteger(actionRequest, "mobile");
		String country=ParamUtil.getString(actionRequest, "country");
		String state=ParamUtil.getString(actionRequest, "state");
		String academics=ParamUtil.getString(actionRequest, "academics");
		String universty=ParamUtil.getString(actionRequest, "universty");
		String description=ParamUtil.getString(actionRequest, "description");
		byte[] b=null;

		int id=0;
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/diaspora/diasporaForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String query="select max(id) from ongc_diaspora";
			Statement stmt=null;
			PreparedStatement ps= null;
			ResultSet rs=null;

			Connection connection = DatasourceConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);			
				while(rs.next())
				{  
					id=rs.getInt(1);
				}
				id++;

				User userOngc =userDao.getUserByCPFNumber(cpfNo);
				ps = connection.prepareStatement("insert into ongc_diaspora(cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,status,sub_category,created_on,id) values(?,?,?,?,?,?,?,?,?,?,?,'A','POST',NOW(),?)");
				ps.setString(1, userOngc.getCpfNo());
				ps.setString(2, userOngc.getEmpName());
				ps.setString(3, wname);
				ps.setString(4, email);
				ps.setInt(5, mobile);
				ps.setString(6, country);
				ps.setString(7, state);
				ps.setString(8, academics);
				ps.setString(9, universty);
				ps.setString(10, description);
				ps.setBytes(11,b);
				ps.setInt(12, id);
				int a=ps.executeUpdate();
				if(file.exists()) {
					dbFileName=String.valueOf(id+file.getName().substring(file.getName().lastIndexOf(".")));
					File to = new File(filePath+dbFileName);
					CommonUtil.copy(file, to);
					} else {
						dbFileName = null;
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//					actionResponse.setRenderParameter("mvcPath","/diaspora/diasporaSummery.jsp");	
		}
	}
}
