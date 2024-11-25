package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.VigilanceAdminUserManagementDao;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;

public class VigilanceAdminUserManagementDaoImpl implements VigilanceAdminUserManagementDao {

	public static Connection connection;
	private static final String selectSql = "select * from VIGILANCE_ADMIN_MASTER";
	
	public VigilanceAdminUserManagementDaoImpl() {
		
	}

	@Override
	public VigilanceAdminUser selectUserByID(String emailId) throws SQLException {
		String sql = selectSql + " Where EMAIL_ID = ? ";
		List<VigilanceAdminUser> users = new ArrayList<VigilanceAdminUser>();
		PreparedStatement pstmt=null;
		ResultSet resultSet =null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, emailId);
			resultSet = pstmt.executeQuery();
			System.out.println(pstmt);
			VigilanceAdminUser user;
			while (resultSet.next()) {
				
				user = new VigilanceAdminUser();
			
				user.setName(resultSet.getString("NAME"));
				
				
				user.setMobile(resultSet.getString("MOBILE"));
				user.setId(resultSet.getInt("ID"));
				user.setEmailId(resultSet.getString("EMAIL_ID"));
				user.setPassword(resultSet.getString("PASSWORD"));
				
				user.setModifyDate(resultSet.getString("MODIFYDATE"));
				
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection,pstmt,resultSet );
		 }
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int changeAdminUserPassword(int id, String password)throws SQLException {
		
		String sql = "UPDATE VIGILANCE_ADMIN_MASTER  SET PASSWORD = ?  WHERE ID = ? ";
		PreparedStatement pstmt=null;
		int i =0;
		ResultSet resultSet=null;
		try{
			connection = DatasourceConnection.getConnection();
		 pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, id);
		 
		i = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally{
			 DatasourceConnection.closeConnection(connection,pstmt,resultSet );
		 }
		return i;
	}
}
