package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.VigilanceUserManagementDao;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.row.extractor.VigilanceUserRowExtractor;
import com.ongc.liferay.vigilance.util.VigilanceConstant;

public class VigilanceUserManagementDaoImpl implements VigilanceUserManagementDao {

	public static Connection connection;
	private static final String selectSql = "select * from VIGILANCE_USER_MASTER";
	public VigilanceUserManagementDaoImpl() {
		
	}

	@Override
	public int registerUser(VigilanceUser user,
			String ipAddress)throws SQLException {
                //
 		String sql = "INSERT INTO VIGILANCE_USER_MASTER (TITLE, FIRST_NAME, MIDDLE_NAME, LAST_NAME, GENDER, DOB, FIRST_ADDRESS, SECOND_ADDRESS, COUNTRY, STATE, PINCODE, MOBILE,  LANDLINE_NUMBER, EMAIL_ID, PASSWORD, SECURITY_QUESTION,SECURITY_ANSWER, REGISTRATION_DATE, IP_ADDRESS,VARIFIED_STATUS, VERIFICATION_DATE , USER_TYPE,REGISTRATION_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt =null;
		ResultSet resultSet=null;
		int i =0;
		try{
			connection = DatasourceConnection.getConnection();
		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, user.getTitle());
	
		pstmt.setString(2, user.getFirstName());
	
		if(user.getMiddleName()!=null)
			pstmt.setString(3, user.getMiddleName());
		
		else
			pstmt.setString(3, "");
		
		pstmt.setString(4, user.getLastName());
	
		pstmt.setString(5, user.getGender());
	
		//pstmt.setString(6, "");
	
			
			//input = input.replace('-', '/');
		//}
   
    //java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input ) ;
		 String input = user.getDob() ;
			if(input!=null && input.contains("-")){
			SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
			Date	d1 = stm.parse(input);
			pstmt.setTimestamp(6,new Timestamp(d1.getTime()));
			}else{

				pstmt.setTimestamp(6, null);
				
			}
			
			pstmt.setString(7, user.getFirstAddress());

		pstmt.setString(8, user.getSecondAddress());
	
		pstmt.setInt(9, Integer.parseInt(user.getCountry()));

		pstmt.setString(10, user.getState());
	
		if(user.getPincode()==null)
		pstmt.setString(11, "");
		else
			pstmt.setString(11, user.getPincode());
	
		pstmt.setLong(12, Long.parseLong(user.getMobile()));

		if( user.getLandlineNumber()==null || user.getLandlineNumber().equals("")){
			pstmt.setLong(13,0);
		}
		
		else{
			pstmt.setLong(13,  Long.parseLong(user.getLandlineNumber()));
		}
			
		
		pstmt.setString(14, user.getEmailId());
	
		pstmt.setString(15, user.getPassword());
	
		pstmt.setInt(16, 0);
		
		pstmt.setString(17, user.getSecurityAnswer());
	
		pstmt.setTimestamp(18, new java.sql.Timestamp(new java.util.Date().getTime()));
	
		pstmt.setString(19, ipAddress);
	
		pstmt.setString(20, VigilanceConstant.USER_STATUS_ACTIVE);

		pstmt.setTimestamp(21, new java.sql.Timestamp(new java.util.Date().getTime()));
		
		pstmt.setString(22, VigilanceConstant.VIGILANCE_DEFAULT_USER);
		
		pstmt.setInt(23, getMaxIdFromTable("VIGILANCE_USER_MASTER","REGISTRATION_ID"));
		
		//pstmt.setString(23, user.getOtp());
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

	@Override
	public int changeUserStatusactive(String emailId, String status , java.sql.Date currentDate)
			throws SQLException {
		
		String sql = "UPDATE VIGILANCE_USER_MASTER  SET VARIFIED_STATUS = ? , VERIFICATION_DATE = ? WHERE EMAIL_ID = ? ";
		PreparedStatement pstmt =null;
		ResultSet resultSet=null;
		int i =0;
		try{
			connection = DatasourceConnection.getConnection();
		 pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, status.trim());
		pstmt.setDate(2, currentDate);
		pstmt.setString(3, emailId);
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

	@Override
	public VigilanceUser selectUserByID(String emailId) throws SQLException {
		String sql = selectSql + " Where EMAIL_ID = ? ";
		PreparedStatement pstmt =null;
		List<VigilanceUser> users = null;
		ResultSet resultSet = null;
		try{
			connection = DatasourceConnection.getConnection();
		 pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, emailId);
		resultSet = pstmt.executeQuery();
		users =  VigilanceUserRowExtractor.extractRow(resultSet);
		}
		catch(Exception e)
		{
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
	public int changeUserPassword(int registrationId, String password)throws SQLException {
		
		String sql = "UPDATE VIGILANCE_USER_MASTER  SET PASSWORD = ?  WHERE REGISTRATION_ID = ? ";
		int i = 0;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			connection = DatasourceConnection.getConnection();
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, registrationId);
		 
		i = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection,pstmt,rs );
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
			DatasourceConnection.closeConnection(connection,stmt,rs );
		}
		return ++id;
	}

}
