package com.ongc.liferay.dao;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.model.SessionDto;
import com.ongc.liferay.model.User;
import com.ongc.liferay.row.extractor.UserMasterRowExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	private static final Log LOGGER = LogFactoryUtil.getLog(UserDao.class);
	
	 public List<User> getBirthday(String date , String month, String locations){
		 PreparedStatement pstmt =null;
			ResultSet set =null;
			Connection conn=null;
			List<User> birthdayList=new ArrayList<User>();
		 User user=null;
		 StringBuffer query;
		 if ((date == null || Validator.isBlank(date)) && (month == null || Validator.isBlank(month)) && (locations == null || Validator.isBlank(locations))) { 
			query = new StringBuffer("SELECT CPF_NUMBER,INITCAP(EMP_NAME) AS EMP_NAME, DESIGNATION,WORKPLACE ,E_MAILID_OFF,MOBILE_NUMBER FROM ONGC_USER_MASTER WHERE  extract(day from DT_BIRTH) = EXTRACT(DAY FROM NOW()) and extract(month from DT_BIRTH) =EXTRACT(month FROM NOW()) and EXTRACT(year FROM NOW()) - EXTRACT(year FROM DT_BIRTH) <= 60  and (dos >=NOW() or DOS is null) and emp_level not in('Retd','Seprtd','Rsgnd','Demise') order by emp_name");	
		 }
		 else {
			 query = new StringBuffer("SELECT CPF_NUMBER,INITCAP(EMP_NAME) AS EMP_NAME, DESIGNATION,WORKPLACE ,E_MAILID_OFF,MOBILE_NUMBER FROM ONGC_USER_MASTER WHERE ");
			 if(!locations.equals("Select"))
			 query.append(" UPPER(PLACE_POSTING) like UPPER('%").append(locations).append("%') and "); 
			 query.append(" EXTRACT(day from DT_BIRTH) = ");
			 query.append(date).append(" AND ");
			 query.append("EXTRACT(month from DT_BIRTH) = ");
			 query.append(month).append(" AND EXTRACT(year FROM NOW()) - EXTRACT(year FROM DT_BIRTH) <= 60  and (dos >= NOW() or DOS is null) and emp_level not in('Retd','Seprtd','Rsgnd','Demise') order by emp_name"); 
		 }
		 try{
			 conn=DatasourceConnection.getConnection();
			 pstmt=conn.prepareStatement(query.toString());
			 set=pstmt.executeQuery();
			 while(set.next()){
				 user=new User();
				 user.setEmpName(set.getString("EMP_NAME"));
				 user.setDesignation(set.getString("DESIGNATION"));
				 user.setEmailIdOfficial(set.getString("E_MAILID_OFF"));
				 user.setWorkPlace(set.getString("WORKPLACE"));
				 user.setMobileNo(set.getString("MOBILE_NUMBER"));
				 user.setCpfNo(set.getString("CPF_NUMBER"));
				 birthdayList.add(user);
			 }
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally{
			 DatasourceConnection.closeConnection(conn,pstmt, set);	
		 }
		 return birthdayList;
	 }
	 
	public User getUserByCPFNumber(String cpfNo){
		PreparedStatement pstmt =null;
		Connection connection=null;
		User user=null;
		String query="SELECT * from ONGC_USER_MASTER WHERE  CPF_NUMBER = ? ";
		try{
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
		
			pstmt.setString(1, cpfNo);
			ResultSet set=pstmt.executeQuery();
			
			user = UserMasterRowExtractor.extractRow(set).get(0);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			} 
		return user;	
		
	}
	

	 public boolean updateUserProfile(User user){
		 PreparedStatement pstmt =null;
			ResultSet resultSet =null;
			Connection connection=null;
			boolean flage=false;
				System.out.println("-------------->>>>>>>>"+user.getProfilePicPath());
			String query="UPDATE ONGC_USER_MASTER SET FAX_NUMBER=? ,E_MAILID_PER=? ,OFFICE_ADDRESS=? , "+
					"RESIDENCE_ADDRESS=? , PHONE_NUM_RES=?, PHONE_NUM_OFF=?,"+
					" BOARD_NUMBER=? , EXT_NUMBER=?, "+
					"WORKPLACE =? ,DEPT=?, ABOUT_ME=?,E_MAILID_OFF=?,PROFILE_PIC_PATH=? WHERE CPF_NUMBER = ?";
			try{
				connection=DatasourceConnection.getConnection();
				pstmt=connection.prepareStatement(query);
				pstmt.setString(1,user.getFaxNumber());
				pstmt.setString(2,user.getEmailIdPersonal());
				pstmt.setString(3,user.getOfficeAddress());
				pstmt.setString(4,user.getResidenceAddress());
				pstmt.setString(5,user.getPhoneNumberHome());
				pstmt.setString(6,user.getPhoneNumberOffice());
				pstmt.setString(7,user.getBoardNumber());
				pstmt.setString(8,user.getExtensionNumber());
				pstmt.setString(9,user.getWorkPlace());
				pstmt.setString(10,user.getDepartment());
				pstmt.setString(11,user.getAboutMe());
				pstmt.setString(12,user.getEmailIdOfficial());
				pstmt.setString(13,user.getProfilePicPath());
				pstmt.setString(14,user.getCpfNo());
				flage = 0 < pstmt.executeUpdate();
			}
			catch(Exception ex){
				//ex.printStackTrace(//system.out);
				ex.printStackTrace();
			}
			finally{
				DatasourceConnection.closeConnection(connection, pstmt,resultSet);
			}
			return flage;
		}

	 
		public User getUserByEmailId(String emailId,String screenName){
			PreparedStatement pstmt =null;
			ResultSet resultSet =null;
			Connection connection=null;
			User user=null;
			String query="SELECT * from ONGC_USER_MASTER WHERE  UPPER(E_MAILID_OFF) = ? or cpf_number = ?";
			try{
				connection=DatasourceConnection.getConnection();
				pstmt=connection.prepareStatement(query);
				pstmt.setString(1,emailId.toUpperCase());
				pstmt.setString(2,screenName);
				resultSet=pstmt.executeQuery();
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

		public boolean saveCpf(String cpfno){
			PreparedStatement pstmt =null;
			ResultSet resultSet =null;
			Connection connection=null;
			boolean flag = false;
			Connection conn = DatasourceConnection.getConnection();
			try {
				PreparedStatement prepped = conn
						.prepareStatement("INSERT INTO QN_RES(CPF_NO,QN_NO,ANS_OPT,RES_DATE,STRT_DATE) values(?, 1, ?,NOW(),NOW())");
				prepped.setString(1, cpfno);
				prepped.setString(2, " ");

				int i = prepped.executeUpdate();
				if (i > 0) {
					flag = true;
				}
			} catch (Exception e) {
				System.out.println("Exception saveCpf "+e);
				e.printStackTrace(); 
			}
			finally {
				DatasourceConnection.closeConnection(connection, pstmt, resultSet);
			}
			return flag;
		}

		public List<SessionDto> getUserLogRecord(){
			List<SessionDto> userSession=new ArrayList<SessionDto>();
			SessionDto sessionDto=null;
			PreparedStatement pstmt =null;
			ResultSet set =null;
			Connection conn=null;
			/*String query="SELECT USER_ID, LOGIN_TIME , EXTRACT( MINUTE FROM (CURRENT_TIMESTAMP - LOGIN_TIME) ) MINUTES," +
					"ROUND(EXTRACT( SECOND FROM (CURRENT_TIMESTAMP  - LOGIN_TIME) )) SECONDS from RPTT_USER_LOG where flage = 0 order by LOGIN_TIME DESC";*/
			String query ="SELECT USER_ID, LOGIN_TIME , extract ( minute from LOGOUT_TIME - LOGIN_TIME)  as MINUTES , trunc (extract ( second from LOGOUT_TIME - LOGIN_TIME))  as SECONDS FROM RPTT_USER_LOG WHERE FLAGE = 1  and extract ( day from LOGIN_TIME)  = extract ( day from now()) AND  extract ( month from LOGIN_TIME) =  extract ( month from now()) order by LOGIN_TIME desc";
			
			try{
				 conn=DatasourceConnection.getConnection();
				 pstmt=conn.prepareStatement(query);
				 set=pstmt.executeQuery();
				 while(set.next()){
					 sessionDto = new SessionDto();
					 sessionDto.setUser_id(set.getString("USER_ID"));
					 sessionDto.setLogin_time(set.getDate("LOGIN_TIME"));
					 sessionDto.setActiveSessionTime(set.getString("MINUTES")+":"+set.getString("SECONDS"));
					 userSession.add(sessionDto);
				 }
			 }
			 catch (Exception e) {
				 e.printStackTrace();
			 }
			 finally{
				 DatasourceConnection.closeConnection(conn,pstmt, set);	
			 }
			 return userSession;
		}
}

