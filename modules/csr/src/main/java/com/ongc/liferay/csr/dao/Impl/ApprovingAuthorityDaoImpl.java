package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.ApprovingAuthorityDao;
import com.ongc.liferay.csr.model.ApprovingAuthorityBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 *  
 * @author Ranjeet
 */
public class ApprovingAuthorityDaoImpl implements ApprovingAuthorityDao{
	
	public static ApprovingAuthorityDaoImpl instance;
				
	public static ApprovingAuthorityDaoImpl getInstance() {
		instance = new ApprovingAuthorityDaoImpl();
		return instance;
	}

	public List<ApprovingAuthorityBean> getApprovingAuthList() {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");	
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<ApprovingAuthorityBean> list = new ArrayList<ApprovingAuthorityBean>();
		String query =szResBundl.getString("GETALLAPPROVINGAUTH");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			ApprovingAuthorityBean auth;
			while (rs.next()) {
				auth = new ApprovingAuthorityBean();
				auth.setId(rs.getInt("AUTH_ID"));
				auth.setName(rs.getString("AUTH_NAME"));
				list.add(auth);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public ApprovingAuthorityBean  getApproverById(Integer id) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");	
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ApprovingAuthorityBean obj= new ApprovingAuthorityBean();
		String query =szResBundl.getString("GETALLAPPROVINGAUTH");
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				obj.setActive(rs.getString("ACTIVE"));
				obj.setCode(rs.getString("AUTH_CODE"));
				obj.setId(rs.getInt("AUTH_ID"));
				obj.setActive(rs.getString("ACTIVE"));
				obj.setName(rs.getString("AUTH_NAME"));
				obj.setCreatedBy(rs.getString("CREATEDBY"));
				obj.setCreatedOn(rs.getString("CREATEDON"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);

		}
		return obj;
	}



}
