package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.UserDao;
import com.ongc.liferay.csr.model.ChangePasswordBean;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.util.Base64Decoder;
import com.ongc.liferay.csr.util.Base64Encoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	
	private final String getUserDetailByCpfAndPassword="SELECT * FROM TM_USER_DTL WHERE CPF_NUMBER=? AND PASSWORD=?";
	private final String getUserDetailByLogiIdAndPassword="SELECT * FROM TM_USER_DTL WHERE LOGINID=? AND PASSWORD=?";
	private final String getUserDetailByCpf="SELECT * FROM TM_USER_DTL WHERE CPF_NUMBER=?";
	private final String getAllUserList="SELECT * FROM TM_USER_DTL ORDER BY CREATION_DATE DESC ";
	private final String updChagePassword="UPDATE TM_USER_DTL SET FLAG=1,PASSWORD=? WHERE LOGINID=?"; 
	private final String resetPassword="UPDATE TM_USER_DTL SET FLAG=0,PASSWORD=? WHERE LOGINID=?";
	private final String isOldPassword="SELECT COUNT(*) FLAG FROM TM_USER_DTL WHERE LOGINID=? AND PASSWORD=?";
	private final String insertQuery="INSERT INTO TM_USER_DTL (CPF_NUMBER, EMP_NAME,DESIGNATION,LOCATION,EMAILID,CONTACTNO,ACTIVE,LOGINID, PASSWORD,CREATION_DATE, CREATED_BY)"+ 
			" VALUES (?,?,?,?,?,?,'1',?,'cGFzc3dvcmQ=',now(),?)";
	public User findByCpfAndPassword(String cpf,String password){
		User ub=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
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
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return ub;
	}
	public User findByLoginIdAndPassword(String loginId,String password){
		User ub=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(getUserDetailByLogiIdAndPassword);
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
			e.printStackTrace();
		}
		return ub;
	}
	
	public User findByCpf(String cpf){
		User ub=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
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
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return ub;
	}
	
	public List<User> findAll(){
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
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
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return ul;
	}

	
	public boolean changePassword(ChangePasswordBean obj,String loginId){
		Boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(updChagePassword);
			pstmt.setString(1, Base64Encoder.encode(obj.getNewPassword()));
			pstmt.setString(2, loginId);
			flag = pstmt.executeUpdate()>0?true:false;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return flag;
	}
	
	public boolean checkOldPassword(String oldPassword,String loginId){
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(isOldPassword);
			pstmt.setString(1,loginId);
			pstmt.setString(2, Base64Encoder.encode(oldPassword));
			rs=pstmt.executeQuery();
			rs.next();
			flag=rs.getInt("FLAG")>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return flag;
	}
	
	
	public boolean resetPassword(String loginId){
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(resetPassword);
			pstmt.setString(1, Base64Encoder.encode(loginId));
			pstmt.setString(2, loginId);
			flag = pstmt.executeUpdate()>0?true:false;			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return flag;
	}
	public Boolean saveUserDetails(User obj){
		Boolean result=false;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
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
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		return result;
	}
	
	public List<User> getSearchUserList(User obj){
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
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
			e.printStackTrace();
		}finally {
			DatasourceConnection.closeConnection(conn, pstmt,rs);
		}
		
		return ul;
		
	}
}
