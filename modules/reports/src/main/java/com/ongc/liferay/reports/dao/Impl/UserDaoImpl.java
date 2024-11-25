package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.report.row.extractor.UserMasterRowExtractor;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.UserDao;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.util.PassioSendMail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

	private static final Log LOGGER = LogFactoryUtil.getLog(UserDaoImpl.class);

	@Override
	public List<User> getBirthday(String date, String month, String locations) {
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
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
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query.toString());
			resultSet=pstmt.executeQuery();
//			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			while(resultSet.next()){
//				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
//				jsonObject.put("EMP_NAME", resultSet.getString("EMP_NAME"));
//				jsonObject.put("DESIGNATION",resultSet.getString("DESIGNATION"));
//				jsonObject.put("E_MAILID_OFF",resultSet.getString("E_MAILID_OFF"));
//				jsonObject.put("WORKPLACE",resultSet.getString("WORKPLACE"));
//				jsonObject.put("MOBILE_NUMBER",resultSet.getString("MOBILE_NUMBER"));
//				jsonObject.put("CPF_NUMBER",resultSet.getString("CPF_NUMBER"));
//				jsonArray.put(jsonObject);
				user=new User();
				user.setEmpName(resultSet.getString("EMP_NAME"));
				user.setDesignation(resultSet.getString("DESIGNATION"));
				user.setEmailIdOfficial(resultSet.getString("E_MAILID_OFF"));
				user.setWorkPlace(resultSet.getString("WORKPLACE"));
				user.setMobileNo(resultSet.getString("MOBILE_NUMBER"));
				user.setCpfNo(resultSet.getString("CPF_NUMBER"));
				birthdayList.add(user);
			}
//			System.out.println(jsonArray);
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}finally{
			try {
				resultSet.close();

			} catch (Exception e2) { }
			try {
				pstmt.close();

			} catch (Exception e2) {}
			try {
				connection.close();

			} catch (Exception e2) {}
		}
		return birthdayList;
	}

	public User getUserByCPFNumber(String cpfNo){
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		User user=null;
		String query="SELECT * from ONGC_USER_MASTER WHERE  CPF_NUMBER = ? ";
		try {
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			ResultSet set=pstmt.executeQuery();
			user = UserMasterRowExtractor.extractRow(set).get(0);
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}	
		} 
		return user;	

	}

	public boolean check_cpf(String cpf){
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		String query = "select cpfnum from leadership_board where cpfnum='"+cpf+"'";
		boolean flage = false;
		try{
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			ResultSet res = pstmt.executeQuery() ; 
			if(!res.next()){
				flage=true;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("erro"+e);
			}
		}
		return flage;
	}
	public boolean saveUser(String cpf, String chk)
	{
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		String query ="";
		if(chk.equalsIgnoreCase("winner")) {		
			query=	"insert into leadership_board(cpfnum,knw_colleague) VALUES(?,?)";
		} else if (chk.equalsIgnoreCase("thanknote1")){
			query=	"insert into leadership_board(cpfnum,sent_note) VALUES(?,?)";
		} else if (chk.equalsIgnoreCase("thanknote2")){
			query=	"insert into leadership_board(cpfnum,receive_note) VALUES(?,?)";
		} else {
			query=	"insert into leadership_board(cpfnum,knw_colleague_partcpt) VALUES(?,?)";
		}
		boolean flage = false;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cpf);
			if(chk.equalsIgnoreCase("winner")) {	
				pstmt.setInt(2, 5);
			} else if(chk.equalsIgnoreCase("thanknote1")) {
				pstmt.setInt(2, 2);
			} else if(chk.equalsIgnoreCase("thanknote2"))	{	
				pstmt.setInt(2, 10);
			} else {
				pstmt.setInt(2, 1);
			}
			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}
		}
		return flage;
	}

	public boolean saveThanksnote(String fromCpf, String toCpf, String message)
	{
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		
		String query = "insert into thank_note(fromcpf,tocpf,message,post_date,nid) VALUES(?,?,?,NOW(),?)";
		boolean flage = false;
		try {
			connection = DatasourceConnection.getConnection();
			int id=getMaxIdFromTable("thank_note", "nid", connection);
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, fromCpf);
			pstmt.setString(2, toCpf);
			pstmt.setString(3, message);
			pstmt.setInt(4, id);
			flage = pstmt.executeUpdate() > 0;
			if(flage)
			{
				
	    	    PassioSendMail sendData = new PassioSendMail();
	    	    String fromEmail= fromCpf+"@ongc.co.in";
		    	String toEmail= toCpf+"@ongc.co.in";
//	    	    String toEmail= "ashishdoorwar96@gmail.com";
		    	String  fromName =fromCpf;
		    	String recvName=toCpf;
		    	String sub="You have received a Thank You note from "+fromName;
		    	String mailmsg="Dear "+recvName+",<br/> <br/> You have received the following Thank You note from "+fromName+" :<br/> <br/> <em>"+message+"</em><br/><br/>You can view this Thank You note by logging in to reports.ongc.co.in and clicking the \"Thank Your Colleague\" and seeing the table there. <br/><br/><br/> Best wishes from ONGCReports Team. ";
	 	        boolean mailflag=sendData.sendEmail1(sub, mailmsg, fromEmail ,toEmail);
	 	     //  boolean mailflag=sendData.sendEmail1(sub, mailmsg, fromEmail ,"mithun.7790@gmail.com");
	 	       //Code for sending sms to receiver on mobile number
//	 	       String smstext="You have received a 'Thank You note' from "+fromName+".\n\nYou can view this 'Thank You note' by logging in to reports.ongc.co.in.\n\n ONGCReports Team.";
//	 	       String recieverMobileNo=this.getReceiverMobNo(toCpf);
//
//				if(recieverMobileNo.length()>=10)
//					{
//						recieverMobileNo="91"+recieverMobileNo.substring(recieverMobileNo.length()-10,recieverMobileNo.length());					
//						sendThankNoteSMS(recieverMobileNo, smstext);
//					}
				 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}
		}
		return flage;
	}

	public boolean updateUser(String cpf, String chk)
	{
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		String query = "";
		if(chk.equalsIgnoreCase("winner"))
		{	
			query= "update leadership_board set knw_colleague=? where cpfnum = ? ";
		}
		else if(chk.equalsIgnoreCase("thanknote1"))
		{	
			query= "update leadership_board set sent_note=? where cpfnum = ? ";
		}
		else if(chk.equalsIgnoreCase("thanknote2"))
		{	
			query= "update leadership_board set receive_note=? where cpfnum = ? ";
		}
		else
		{
			query= "update leadership_board set knw_colleague_partcpt=? where cpfnum = ? ";
		}
		boolean flage = false;
		try {

			int kc=getKCCount(cpf,chk);
			if(chk.equalsIgnoreCase("winner"))
			{	
				kc= kc+5;
			}
			else if(chk.equalsIgnoreCase("thanknote1"))
			{	
				kc= kc+2;
			}
			else if(chk.equalsIgnoreCase("thanknote2"))
			{	
				kc= kc+10;
			}
			else
			{
				kc=kc+1;
			}
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, kc);
			pstmt.setString(2, cpf);

			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}
		}
		return flage;
	}

	public int getKCCount(String cpf,String chk)
	{
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		String query = "";
		if(chk.equalsIgnoreCase("winner"))
		{
			query="Select knw_colleague from leadership_board where cpfnum= ? ";
		}
		else  if(chk.equalsIgnoreCase("thanknote1"))
		{
			query="Select sent_note from leadership_board where cpfnum= ? ";
		}
		else  if(chk.equalsIgnoreCase("thanknote2"))
		{
			query="Select receive_note from leadership_board where cpfnum= ? ";
		}
		else
		{
			query="Select knw_colleague_partcpt from leadership_board where cpfnum= ? ";
		}
		int count = 0;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cpf);


			resultSet = pstmt.executeQuery();
			while(resultSet.next())
			{
				count= resultSet.getInt(1);

			}


		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}
		}
		return count;
	}

	public boolean updateUserProfile(User user){
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		boolean flage=false;
		/*String query="UPDATE ONGC_USER_MASTER SET TITLE=?, MOBILE_NUMBER=?"+
			" ,FAX_NUMBER=? ,E_MAILID_PER=? ,OFFICE_ADDRESS=? ,"+
			"RESIDENCE_ADDRESS=? , PHONE_NUM_RES=?, PHONE_NUM_OFF=?,"+
			" BOARD_NUMBER=? , BOARD_NO_RES=? , EXT_NUMBER=?, "+
			"EXT_NUMBER_RES=? , ICNET_NUM =? , ICNET_NUM_RES= ? WHERE CPF_NUMBER = ?";
		 */
		/*String query="UPDATE ONGC_USER_MASTER SET MOBILE_NUMBER=?"+
					" ,FAX_NUMBER=? ,E_MAILID_PER=? ,OFFICE_ADDRESS=? ,"+
					"RESIDENCE_ADDRESS=? , PHONE_NUM_RES=?, PHONE_NUM_OFF=?,"+
					" BOARD_NUMBER=? , BOARD_NO_RES=? , EXT_NUMBER=?, "+
					"EXT_NUMBER_RES=? , ICNET_NUM =? , ICNET_NUM_RES= ?,WORKPLACE =? ,DEPT=?  WHERE CPF_NUMBER = ?";*/
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
			System.out.println(pstmt);
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

	@Override
	public User getUserByEmailId(String emailId){
		User user=null;
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		String query="SELECT * from ONGC_USER_MASTER WHERE  UPPER(E_MAILID_OFF) = ?";
		try{
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1, emailId.toUpperCase());
			resultSet=pstmt.executeQuery();
			user = UserMasterRowExtractor.extractRow(resultSet).get(0);
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(connection, pstmt, resultSet);
		} 
		return user;	

	}
	
	private int getMaxIdFromTable(String tableName, String colName, Connection conn) {
		int id = 1000;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;
		try {				
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			//ConnMgmt.closeConnection(rs, stmt, connection);
		}
		if(id==0){
			id=1000;
		}
		return ++id;
	}


}
