package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;


import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.OPTManagementDao;

public class OPTManagementDaoImpl implements OPTManagementDao{
	
	public static Connection connection;
	
	private static String insertSql = "INSERT INTO ONGCIND_OTP_MSTR (ID, MOBILE_NUMBER, OTP, CREATED_ON, OPT_TYPE) VALUES(?, ? ,? ,NOW() ,? )";
	private static String insertLoginOtpSql = "update vigilance_user_master set login_otp=?,login_otp_validate=NOW() where  email_id = ?";

	public OPTManagementDaoImpl(){
		
	}

	@Override
	public int insertOPT(String mobileNumber, String otp,
			String otpType){
		//system.out.println("insertOPT");
		int i = 0;
		ResultSet resultSet=null;
		PreparedStatement pstmt =null;
		try {
			connection = DatasourceConnection.getConnection();
			 pstmt = connection.prepareStatement(insertSql);
			 pstmt.setInt(1, getMaxIdFromTable("ONGCIND_OTP_MSTR","ID"));
			pstmt.setString(2, mobileNumber);
			pstmt.setString(3, otp);
		//	pstmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
			pstmt.setString(4, otpType);
			i = pstmt.executeUpdate();
			//system.out.println("insertOPT: ..."+i );
		} catch (SQLException e) {
		e.printStackTrace();
		}
		finally{
			 DatasourceConnection.closeConnection( connection,pstmt,resultSet);
		 }
		return i;
	}

	@Override
	public String getLatestOTP(String phoneNumber , String otpType) {
		String validityTime = ResourceBundle.getBundle("com.ongc.corporate.vigilance.util.vigilance").getString("otp_validity");
		String dbOtp = null;
		String selectSql = "select * from ONGCIND_OTP_MSTR where id=( select max(id) from ONGCIND_OTP_MSTR where mobile_number= '"+ phoneNumber.trim()+"')";
		Statement stmt =null;
		ResultSet rs=null;
		try {
			connection = DatasourceConnection.getConnection();
			 stmt = connection.createStatement();
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				dbOtp = rs.getString("OTP");
			}
		} catch (SQLException e) {
			//system.out.println("error in select latest OTP"+ e);
		}
		finally{
			 DatasourceConnection.closeConnection( connection,stmt,rs);
		 }
		return dbOtp;
	}
	
	@Override
	public int insertLoginOPT(String otp,String emailId){
		int i = 0;
		ResultSet resultSet=null;
		PreparedStatement pstmt =null;
		try {
			connection = DatasourceConnection.getConnection();
			 pstmt = connection.prepareStatement(insertLoginOtpSql);
			pstmt.setString(1, otp);
			pstmt.setString(2, emailId);
			i = pstmt.executeUpdate();
			System.out.println(pstmt);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		finally{
			 DatasourceConnection.closeConnection( connection,pstmt,resultSet);
		 }
		return i;
	}
	
	private int getMaxIdFromTable(String tableName,String colName){
		int id=0;
		
		String query="select max("+colName+") from "+tableName;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				id=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection( connection,stmt,rs);
		}
		
		return ++id;
	}

	@Override
	public String getLoginOtpVerified(String emailID) {
		String dbOtp = null;
		String selectSql = "select * from vigilance_user_master where email_id='"+emailID+"'";
		Statement stmt =null;
		ResultSet rs=null;
		try {
			connection = DatasourceConnection.getConnection();
			 stmt = connection.createStatement();
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				dbOtp = rs.getString("login_otp_validate");
			}
		} catch (SQLException e) {
			//system.out.println("error in select latest OTP"+ e);
		}
		finally{
			 DatasourceConnection.closeConnection( connection,stmt,rs);
		 }
		return dbOtp;
	}


}
