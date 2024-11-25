package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.row.UserMasterRowExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{

	public User getUserByCPFNumber(String cpfNo){
		 Connection conn=null;
		 PreparedStatement pstmt=null;
		 ResultSet set=null;
		User user=null;
		String query="SELECT * from ONGC_USER_MASTER WHERE  CPF_NUMBER = ? ";
		//system.out.println(query);
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
		
			pstmt.setString(1, cpfNo);
			System.out.println(pstmt);
			set=pstmt.executeQuery();
			
			user = UserMasterRowExtractor.extractRow(set).get(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);	
			} 
		return user;	
		
	}
	@Override
	public User getUserByEmailId(String emailId){
		User user=null;
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		System.out.println(emailId);
		String query="SELECT * from ONGC_USER_MASTER WHERE  UPPER(E_MAILID_OFF) = ? ";
		try{
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);

			//				pstmt.setString(1, emailId);
			pstmt.setString(1, emailId.toUpperCase());
			resultSet=pstmt.executeQuery();
			System.out.println(pstmt);
			user = UserMasterRowExtractor.extractRow(resultSet).get(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection, pstmt, resultSet);
		} 
		return user;	

	}
	@Override
	public boolean validateUser(String cpfNumber) {
		Connection conn=null;
		boolean flag = false;
		conn = DatasourceConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn
					.prepareStatement("select * from ONGC_USER_MASTER where cpf_Number=?");
			ps.setString(1, cpfNumber);

			rs = ps.executeQuery();

			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, ps, rs);
		}

		return flag;
	}
}
