package com.ongc.liferay.dao;

import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.model.IncidentBean;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

public class IncidentDao {

	public ArrayList<IncidentBean> getIncidents(Connection conn) throws SQLException {

		ArrayList<IncidentBean> al = new ArrayList<IncidentBean>();

		try{
			String query = "select t1.id,t1.category,t1.description,t1.subject,t1.post_date,t1.posted_by,t1.fname,t2.emp_name from rptt_incident_details t1 inner join ongc_user_master t2 on t1.posted_by = t2.cpf_number order by t1.post_date desc";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			int i = 0;
			while (res.next()) {
				i++;
				IncidentBean in =new IncidentBean();
				in.setId(res.getInt("id"));
				in.setIndex(i);
				in.setCategory(res.getString("category"));
				in.setDescription(res.getString("description"));
				in.setSubject(res.getString("subject"));
				SimpleDateFormat stm =new SimpleDateFormat("MM/dd/yyyy");
				String date =stm.format(res.getDate("post_date"));
				in.setDate(date);
				in.setPosteddby(res.getString("posted_by"));
				String fileNameWithOutExt = FilenameUtils.removeExtension(res.getString("fname"));
				in.setFname(fileNameWithOutExt);
				in.setPostedbyName(res.getString("emp_name"));
				al.add(in);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return al;
	}
	public String getName(String cpf,Connection conn){
		String query = "select initcap(a.EMP_NAME) as emp from ongc_user_master a where a.CPF_NUMBER='"+cpf+"'";
		String emp_name="";
		ResultSet res=null;
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			while (res.next()) {

				emp_name= res.getString("emp");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			if(res!=null)
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return emp_name;
	}

	public boolean insertIncident(Connection conn,String sub, String cat,String incident,String postedby,InputStream inpt,String fname){
		boolean flag = false;
		PreparedStatement pstmt=null;
		try {
			int id = getMaxIdFromTable("rptt_incident_details", "id");
			String query="insert into rptt_incident_details(id,category,description,subject,posted_by,filedata,fname,post_date) VALUES(?,?,?,?,?,?,?,NOW())";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,id);
			pstmt.setString(2,cat);
			pstmt.setString(3,incident);
			pstmt.setString(4,sub);
			pstmt.setString(5,postedby);
			pstmt.setBytes(6,null);
			pstmt.setString(7,fname);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public String getLocation(String cpf){
		PreparedStatement pstmt=null;
		Connection conn=DatasourceConnection.getConnection();
		ResultSet rs=null;
		String place=null;
		try {
			String query="Select a.place_posting from ongc_user_master a where a.cpf_number=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				place=rs.getString("place_posting");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, rs);	
		}
		return place;
	}
	
	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;
		Connection connection = null;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;
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
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}
		return ++id;
	}
}
