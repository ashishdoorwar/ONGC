package com.ongc.liferay.sponsorship.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.sponsorship.connection.DatasourceConnection;
import com.ongc.liferay.sponsorship.dao.UserDao;
import com.ongc.liferay.sponsorship.model.ChangePasswordBean;
import com.ongc.liferay.sponsorship.model.User;
import com.ongc.liferay.sponsorship.util.Base64Decoder;
import com.ongc.liferay.sponsorship.util.Base64Encoder;
public class UserDaoImpl implements UserDao {
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	private static final Log LOGGER = LogFactoryUtil.getLog(UserDaoImpl.class);
	final String getUserDetailByCpfAndPassword="SELECT * FROM TM_USER_DTL WHERE CPF_NUMBER=? AND PASSWORD=?";
	final String getUserDetailByLogiIdAndPassword="SELECT * FROM TM_USER_DTL WHERE LOGINID=? AND PASSWORD=?";
	final String getUserDetailByCpf="SELECT * FROM TM_USER_DTL WHERE CPF_NUMBER=?";
	final String getAllUserList="SELECT * FROM TM_USER_DTL ORDER BY CREATION_DATE DESC ";
	final String updChagePassword="UPDATE TM_USER_DTL SET FLAG=1,PASSWORD=? WHERE LOGINID=?"; 
	final String resetPassword="UPDATE TM_USER_DTL SET FLAG=0,PASSWORD=? WHERE LOGINID=?";
	final String isOldPassword="SELECT COUNT(*) FLAG FROM TM_USER_DTL WHERE LOGINID=? AND PASSWORD=?";
	final String insertQuery="INSERT INTO TM_USER_DTL (CPF_NUMBER, EMP_NAME,DESIGNATION,LOCATION,EMAILID,CONTACTNO,ACTIVE,LOGINID, PASSWORD,CREATION_DATE, CREATED_BY)"+ 
			" VALUES (?,?,?,?,?,?,'1',?,'cGFzc3dvcmQ=',now(),?)";
	
	public User findByCpfAndPassword(String cpf,String password){
		User ub=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getUserDetailByCpfAndPassword);
			pstmt.setString(1, cpf);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ub=new User();
				ub.setUserName(rs.getString("EMP_NAME"));
				ub.setCpfNo(rs.getString("CPF_NUMBER"));
				ub.setDesignation(rs.getString("DESIGNATION"));
				ub.setDateOfBirth(rs.getDate("DT_OF_BIRTH"));
				ub.setLocation(rs.getString("LOCATION"));
				ub.setAboutMe(rs.getString("ABOUT_ME"));
				ub.setEmailId(rs.getString("EMAILID"));
				ub.setActive(rs.getInt("ACTIVE"));
				ub.setAddress(rs.getString("ADDRESS"));
				ub.setCommunicatedAddress(rs.getString("COMM_ADDRESS"));
				ub.setDateOfJoining(rs.getDate("DT_OF_JOINING"));
				ub.setCreationDate(rs.getDate("CREATION_DATE"));
				ub.setPanNumber(rs.getString("PAN_NUMBER"));
				ub.setLoginId(rs.getString("LOGINID"));
				ub.setPassword(Base64Decoder.decode(rs.getString("PASSWORD")));
				ub.setMobileNo(rs.getString("CONTACTNO"));
				ub.setLandLineNo(rs.getString("LAND_LINE_NUMBER"));
				ub.setFlag(rs.getInt("FLAG"));
				
			}
			
		}catch(Exception e){
			LOGGER.info("error ");
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return ub;
	}
	public User findByLoginIdAndPassword(String loginId,String password){
		User ub=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getUserDetailByCpfAndPassword);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ub=new User();
				ub.setUserName(rs.getString("EMP_NAME"));
				ub.setCpfNo(rs.getString("CPF_NUMBER"));
				ub.setDesignation(rs.getString("DESIGNATION"));
				ub.setDateOfBirth(rs.getDate("DT_OF_BIRTH"));
				ub.setLocation(rs.getString("LOCATION"));
				ub.setAboutMe(rs.getString("ABOUT_ME"));
				ub.setEmailId(rs.getString("EMAILID"));
				ub.setActive(rs.getInt("ACTIVE"));
				ub.setAddress(rs.getString("ADDRESS"));
				ub.setCommunicatedAddress(rs.getString("COMM_ADDRESS"));
				ub.setDateOfJoining(rs.getDate("DT_OF_JOINING"));
				ub.setCreationDate(rs.getDate("CREATION_DATE"));
				ub.setPanNumber(rs.getString("PAN_NUMBER"));
				ub.setLoginId(rs.getString("LOGINID"));
				ub.setPassword(Base64Decoder.decode(rs.getString("PASSWORD")));
				ub.setMobileNo(rs.getString("CONTACTNO"));
				ub.setLandLineNo(rs.getString("LAND_LINE_NUMBER"));
				ub.setFlag(rs.getInt("FLAG"));
			}
			
		}catch(Exception e){
			LOGGER.info("error:findByCpfAndPassword");
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return ub;
	}
	public User findByCpf(String cpf){
		User ub=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getUserDetailByCpf);
			pstmt.setString(1, cpf);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ub=new User();
				ub.setUserName(rs.getString("EMP_NAME"));
				ub.setCpfNo(rs.getString("CPF_NUMBER"));
				ub.setDesignation(rs.getString("DESIGNATION"));
				ub.setDateOfBirth(rs.getDate("DT_OF_BIRTH"));
				ub.setLocation(rs.getString("LOCATION"));
				ub.setAboutMe(rs.getString("ABOUT_ME"));
				ub.setEmailId(rs.getString("EMAILID"));
				ub.setActive(rs.getInt("ACTIVE"));
				ub.setAddress(rs.getString("ADDRESS"));
				ub.setCommunicatedAddress(rs.getString("COMM_ADDRESS"));
				ub.setDateOfJoining(rs.getDate("DT_OF_JOINING"));
				ub.setCreationDate(rs.getDate("CREATION_DATE"));
				ub.setPanNumber(rs.getString("PAN_NUMBER"));
				ub.setLoginId(rs.getString("LOGINID"));
				ub.setPassword(rs.getString("PASSWORD"));
				ub.setMobileNo(rs.getString("CONTACTNO"));
				ub.setLandLineNo(rs.getString("LAND_LINE_NUMBER"));
			}
			
		}catch(Exception e){
			
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return ub;
	}
	
	public List<User> findAll(){
		User ub=null;
		List<User> ul=new ArrayList<User>();
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getAllUserList);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ub=new User();
				ub.setUserName(rs.getString("EMP_NAME"));
				ub.setCpfNo(rs.getString("CPF_NUMBER"));
				ub.setDesignation(rs.getString("DESIGNATION"));
				ub.setDateOfBirth(rs.getDate("DT_OF_BIRTH"));
				ub.setLocation(rs.getString("LOCATION"));
				ub.setAboutMe(rs.getString("ABOUT_ME"));
				ub.setEmailId(rs.getString("EMAILID"));
				ub.setActive(rs.getInt("ACTIVE"));
				ub.setAddress(rs.getString("ADDRESS"));
				ub.setCommunicatedAddress(rs.getString("COMM_ADDRESS"));
				ub.setDateOfJoining(rs.getDate("DT_OF_JOINING"));
				ub.setCreationDate(rs.getDate("CREATION_DATE"));
				ub.setPanNumber(rs.getString("PAN_NUMBER"));
				ub.setLoginId(rs.getString("LOGINID"));
				ub.setPassword(rs.getString("PASSWORD"));
				ub.setMobileNo(rs.getString("CONTACTNO"));
				ub.setLandLineNo(rs.getString("LAND_LINE_NUMBER"));
				ul.add(ub);
			}
			
		}catch(Exception e){
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return ul;
	}

	
	public boolean changePassword(ChangePasswordBean obj,String loginId){
		boolean flag=false;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(updChagePassword);
			pstmt.setString(1, Base64Encoder.encode(obj.getNewPassword()));
			pstmt.setString(2, loginId);
			flag = pstmt.executeUpdate()>0?true:false;
			
		}catch(Exception e){
			LOGGER.info("error:changePassword");
			e.printStackTrace();
			
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	
	public boolean checkOldPassword(String oldPassword,String loginId){
		boolean flag=false;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(isOldPassword);
			pstmt.setString(1,loginId);
			pstmt.setString(2, Base64Encoder.encode(oldPassword));
			rs=pstmt.executeQuery();
			rs.next();
			flag=rs.getInt("FLAG")>0?true:false;
		}catch(Exception e){
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	
	
	public boolean resetPassword(String loginId){
		boolean flag=false;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(resetPassword);
			pstmt.setString(1, Base64Encoder.encode(loginId));
			pstmt.setString(2, loginId);
			flag = pstmt.executeUpdate()>0?true:false;			
		}catch(Exception e){
			LOGGER.info("error:resetPassword");
			e.printStackTrace();
			
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	public boolean saveUserDetails(User obj){
		Boolean result=false;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1,obj.getCpfNo());
			pstmt.setString(2, obj.getUserName());
			pstmt.setString(3, obj.getDesignation());
			pstmt.setString(4, obj.getLocation());
			pstmt.setString(5, obj.getEmailId());
			pstmt.setString(6, obj.getMobileNo());
			pstmt.setString(7, obj.getCpfNo());
			pstmt.setString(8, obj.getCreatedBy());
			result = pstmt.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();	
			
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return result;
	}
	
	public List<User> getSearchUserList(User obj){
		StringBuilder getSearchUserList= new StringBuilder();
		User ub=null;
		List<User> ul=new ArrayList<User>();
		try{ 
			 getSearchUserList.append("SELECT * FROM TM_USER_DTL");
			 if(obj.getCpfNo().length()>0)	
				 getSearchUserList.append(" WHERE CPF_NUMBER = ? ORDER BY CREATION_DATE DESC");
			 else if(obj.getLocation().length()>0)
				 getSearchUserList.append(" WHERE  LOCATION = ?  ORDER BY CREATION_DATE DESC");
			 else if(obj.getLocation().length()>0 && obj.getCpfNo().length()>0)
				 getSearchUserList.append(" WHERE CPF_NUMBER = ? AND LOCATION = ?  ORDER BY CREATION_DATE DESC");
			 else
				 getSearchUserList.append(" ORDER BY CREATION_DATE DESC");
			 
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getSearchUserList.toString());
			if(obj.getCpfNo().length()>0)	
				pstmt.setString(1,obj.getCpfNo());
			 else if(obj.getLocation().length()>0)
				pstmt.setString(1,obj.getLocation());
			 else if(obj.getLocation().length()>0 && obj.getCpfNo().length()>0) { 
				 	pstmt.setString(1,obj.getCpfNo());
				 	pstmt.setString(2,obj.getLocation());
				 }
				 
			rs = pstmt.executeQuery();
			while(rs.next()){
				ub=new User();
				ub.setUserName(rs.getString("EMP_NAME"));
				ub.setCpfNo(rs.getString("CPF_NUMBER"));
				ub.setDesignation(rs.getString("DESIGNATION"));
				ub.setDateOfBirth(rs.getDate("DT_OF_BIRTH"));
				ub.setLocation(rs.getString("LOCATION"));
				ub.setAboutMe(rs.getString("ABOUT_ME"));
				ub.setEmailId(rs.getString("EMAILID"));
				ub.setActive(rs.getInt("ACTIVE"));
				ub.setAddress(rs.getString("ADDRESS"));
				ub.setCommunicatedAddress(rs.getString("COMM_ADDRESS"));
				ub.setDateOfJoining(rs.getDate("DT_OF_JOINING"));
				ub.setCreationDate(rs.getDate("CREATION_DATE"));
				ub.setPanNumber(rs.getString("PAN_NUMBER"));
				ub.setLoginId(rs.getString("LOGINID"));
				ub.setPassword(rs.getString("PASSWORD"));
				ub.setMobileNo(rs.getString("CONTACTNO"));
				ub.setLandLineNo(rs.getString("LAND_LINE_NUMBER"));
				ul.add(ub);
			}
			
		}catch(Exception e){
		}finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		
		return ul;
	}
	
	public List<User> getUserMobileNumber(){
		String mobileNo=null;
		List<User> userMobileNoList = new ArrayList<>();
		User user=null;
		String query="SELECT MOBILE_NO FROM SPON_DETAILS";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				user=new User();
				mobileNo=rs.getString("MOBILE_NO");
				//log.info("**********mobileNo******"+mobileNo);
				user.setMobileNo(mobileNo);
				userMobileNoList.add(user);
			}
		}
		catch (Exception e) {
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, rs);	
			} 
		return userMobileNoList;	
		
	}
	
}
