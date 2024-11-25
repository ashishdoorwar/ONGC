package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
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
			"javax.portlet.name=" +RepotsPortletKeys.Facilitation,
			"mvc.command.name=add_facilitation"
		},
		service = MVCActionCommand.class
	)
public class AddFacilitationActionCommand extends BaseMVCActionCommand{
	
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		//int fcount = listOfFiles.length;
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Repots");				
		String filePath = szResBundl.getString("filePath").toString().trim()+"facilitation/";

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("imgfile");
		File file = uploadPortletRequest.getFile("imgfile");
		String fileTitle = fileName;
		String dbFileName;
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				
				actionResponse.sendRedirect("mvcPath", "/facilitation/facilitationPostForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		if(file.exists()) {
		dbFileName=String.valueOf(getMaxIdFromTable("ongc_facilitation", "id")+file.getName().substring(file.getName().lastIndexOf(".")));
		File to = new File(filePath+dbFileName);
		CommonUtil.copy(file, to);
		} else {
			dbFileName = null;
		}
		byte[] b=null;
		String cpfno =ParamUtil.getString(actionRequest, "cpfno");
		String name =ParamUtil.getString(actionRequest, "name");
		long mobile =ParamUtil.getLong(actionRequest, "mobile");
		String email =ParamUtil.getString(actionRequest, "email");
		String location =ParamUtil.getString(actionRequest, "location");
		String category =ParamUtil.getString(actionRequest, "category");
		String title =ParamUtil.getString(actionRequest, "title");
		String description =ParamUtil.getString(actionRequest, "description");
		String sub_category= null;
		if(category.equals("Accommodation")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_accom");
		} else if(category.equals("Matrimonial")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_matrx");
		} else {
			sub_category="REPORTS";
		}
		String usertype="O";
		PreparedStatement ps =null;
		ResultSet res=null;
		Connection conn=null;
		String queryPart = "";
		try {
		conn=DatasourceConnection.getConnection();
		ps = conn.prepareStatement("insert into ongc_facilitation(cpf_no,name,mobile,email,location,category,title,discription,created_on,status,photos,sub_category,user_type,id) values(?,?,?,?,?,?,?,?,now(),'A',?,?,?,?)");
		ps.setString(1, cpfno);
		ps.setString(2, name);
		ps.setLong(3, mobile);
		ps.setString(4, email);
		ps.setString(5, location);
		ps.setString(6, category);
		ps.setString(7, title);
		ps.setString(8, description);
		ps.setBytes(9, b);
		ps.setString(10,sub_category);
		ps.setString(11,usertype);
		ps.setInt(12, getMaxIdFromTable("ongc_facilitation", "id"));
		ps.executeUpdate();
		if(category.equals("Accommodation")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_accom");
			queryPart="select cpfno from ongc_facilitation_subscribe where location = '"+ location + "' and category = '" + category + "' and subcategory = '" + sub_category + "' "; 
		} else if(category.equals("Matrimonial")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_matrx");
			queryPart="select cpfno from ongc_facilitation_subscribe where location = '"+ location + "' and category = '" + category + "' and subcategory = '" + sub_category + "' "; 
		} else {
			queryPart="select cpfno from ongc_facilitation_subscribe where location = '"+ location + "' and category = '" + category + "'"; 
		}
		Statement stmt1=conn.createStatement();
		res = stmt1.executeQuery(queryPart) ;
		while(res.next()) {
//			System.out.println("Facilitation Sending mail to this CPFNO ==============================>"+res.getString(1));
		}
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		 finally {
		     		if(res!=null)
						res.close();			
					if(ps!=null)
						ps.close();			
					if(conn!=null)
						conn.close();
			}
		}
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

}
