package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.StatusDao;
import com.ongc.liferay.csr.model.StatusBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author Ranjeet
 */
public class StatusDaoImpl implements StatusDao {
	
	public static StatusDaoImpl instance;

	public static StatusDaoImpl getInstance() {
		instance = new StatusDaoImpl();
		return instance;
	}

	public List<StatusBean> getAllStatus() {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<StatusBean> list = new ArrayList<StatusBean>();
		String query =szResBundl.getString("GETALLSTATUS");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			StatusBean status;
			while (rs.next()) {
				status = new StatusBean();
				status.setId(rs.getInt("STATID"));
				status.setStatus(rs.getString("STATUS"));
				list.add(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	public StatusBean  getStatusNameById(String id) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String status = null;
		String query =szResBundl.getString("GETSTATUSBYID");
		int statusId = Integer.parseInt(id);
		StatusBean bean = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, statusId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StatusBean();
				
				bean.setId(Integer.parseInt(rs.getString("STATID")));
				bean.setStatus(rs.getString("STATUS"));
				bean.setStatusCode(rs.getString("STATUSCODE"));
				bean.setActive(rs.getString("ACTIVE"));
				bean.setCreatedBy(rs.getString("CREATEDBY"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return bean;
	}
	

	
	public List<StatusBean> getAllStatusList() {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<StatusBean> list = new ArrayList<StatusBean>();
		String query =szResBundl.getString("GETALLSTATUSLIST");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			StatusBean status;
			while (rs.next()) {
				status = new StatusBean();
				status.setId(rs.getInt("STATID"));
				status.setStatus(rs.getString("STATUS"));
				status.setCreatedOn(rs.getString("CREATEDON"));
				status.setCreatedBy(rs.getString("CREATEDBY"));
				status.setStatusCode(rs.getString("STATUSCODE"));
				list.add(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}
	
	synchronized public  boolean save(StatusBean model){
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Boolean rtFlag=false;
		//String refNo = "REF";
		String insertQuery=szResBundl.getString("INSSTATUS");
		
		
		try {
			int i = 0;
			int id = CommonDaoImpl.getInstance().getMaxIdFromTable("TM_CSR_STATUS", "STATID");
			//refNo = id;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1,id);
			pstmt.setString(2, model.getStatus());
		    //pstmt.setString(3, model.getCreatedOn());
			pstmt.setString(3, model.getStatusCode());
			pstmt.setString(4, model.getCreatedBy());
			pstmt.setString(5, model.getActive());			
			rtFlag=pstmt.executeUpdate()>0?true:false;
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return rtFlag;
		
	}
	public boolean isExist(String StatusCode){
		Connection conn=null;
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String subname = null;
		Boolean rtFlag=false;
		
		String query = szResBundl.getString("ISEXIST");
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, StatusCode);
			rs=pstmt.executeQuery();
			rs.next();
			rtFlag=rs.getInt("ISEXIT")>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return rtFlag;
	}
	public boolean deleteStatus(String StatusCode){
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Boolean rtFlag=false;
		String query = szResBundl.getString("DELVSTATUS");
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, StatusCode);
			
			rtFlag = pstmt.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return rtFlag;
	}
	
	public boolean removeStatus(String StatusCode){
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		Boolean rtFlag=false;
		String query = szResBundl.getString("RMVSTATUS");
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, StatusCode);
			
			rtFlag = pstmt.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return rtFlag;
	}
	public boolean updateBean(StatusBean model){
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		Boolean rtFlag=false;
		String query = szResBundl.getString("UPDSTATUS");
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, model.getStatus());
			pstmt.setString(2, model.getStatusCode());
			rtFlag = pstmt.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return rtFlag;
	}
	
}
