package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.Facilitation,
			"mvc.command.name=addSubscription"
		},
		service = MVCActionCommand.class
	)
public class FacilitationSubscribeActionCommand extends BaseMVCActionCommand{
	
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String name= ParamUtil.getString(actionRequest, "name");
		String category= ParamUtil.getString(actionRequest, "category");
		String sub_category= null;
		if(category.equals("Accommodation")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_accom");
		} else if(category.equals("Matrimonial")) {
			sub_category = ParamUtil.getString(actionRequest, "sub_matrx");
		} else {
			sub_category="";
		}
		String location =ParamUtil.getString(actionRequest, "location");
		String cpfno =ParamUtil.getString(actionRequest, "cpfno");
//		System.out.println("name=====>"+name+"category==========>"+category+"location==========>"+location+"cpfno==========>"+cpfno+"sub_category==========>"+sub_category);
		PreparedStatement ps =null;
		ResultSet res=null;
		Connection conn=null;
		byte[] b=null;
		try {
		conn=DatasourceConnection.getConnection();
		String query = "select count(*) as record from ongc_facilitation_subscribe where cpfno='"+ cpfno + "' and category='" + category +"' and subcategory='"+ sub_category +"' and location='"+ location +"'";
      	Statement stmt1 = conn.createStatement();
	    ResultSet res1 = stmt1.executeQuery(query);
	    int count = 0;
	    while(res1.next()){
	    	count = res1.getInt("record");
	    }  
	    if(count>0) {}else {
		ps = conn.prepareStatement("insert into ongc_facilitation_subscribe(id,category,subcategory,cpfno,location,creation_on) values(?,?,?,?,?,now())");
		ps.setLong(1, getMaxIdFromTable("ongc_facilitation_subscribe", "id"));
		ps.setString(2, category);
		ps.setString(3, sub_category);
		ps.setString(4, cpfno);
		ps.setString(5, location);
		ps.executeUpdate();
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
