package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.activation.DataSource;

import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.VigilanceUserDao;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.row.extractor.VigilanceUserRowExtractor;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.TEA;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

public class VigilanceUserDaoImpl implements VigilanceUserDao {

	public static Connection connection;
	private static final String selectSql = "select * from VIGILANCE_USER_MASTER";
	@Override
	public String registerVigilanceUser(VigilanceUser user, String ipAddress) {
		
		try {
//			String encPass = TEA.encrypt(user.getPassword(), user.getEmailId()); // to encrypt password
//			user.setPassword(encPass);
			int status = VigilanceFactory.getUserManagementInstance().registerUser(user, ipAddress);

			if (status == 1) {				
				return VigilanceConstant.SUCCESS;
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			return "fail";
		}
	}

	@Override
	public String changeUserStatus(String emailId, String status) {
		try {
			java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
			int i = VigilanceFactory.getUserManagementInstance().changeUserStatusactive(emailId, status , currentDate);
			if (i == 1) {
				return "success";
			} else {
				return "input";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "failure";
		}
	}

	@Override
	public VigilanceUser getUserByEmailId(String emailId) {
		VigilanceUser user = null;
		 try {
			 user = VigilanceFactory.getUserManagementInstance().selectUserByID(emailId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user ;
	}
	
	@Override
	public VigilanceUser getUserByMobile(String mobile) {
		VigilanceUser user = null;
		 try {
			 connection = DatasourceConnection.getConnection();
			 String sql = selectSql + " Where MOBILE = ? ";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				long mob =Long.valueOf(mobile);
				//pstmt.setString(1, mobile);
				pstmt.setLong(1, mob);
				ResultSet resultSet = pstmt.executeQuery();
				List<VigilanceUser> users =  VigilanceUserRowExtractor.extractRow(resultSet);
				if (users.size() > 0) {
					user= users.get(0);
					////system.out.println("Already exists mobile"+user);
				} else {
					user= null;
				}
			 //user = VigilanceFactory.getUserManagementInstance().selectUserByID(mobile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 finally
		 {
			 if(connection!=null){
				 try{
				 connection.close();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
		 }
		return user ;
	}

	@Override
	public void changePassword(int registrationId,String emailId, String password) {
		String encPass = TEA.encrypt(password, emailId ); // to encrypt password
//		String encPass = "";
		try {
			int i = VigilanceFactory.getUserManagementInstance().changeUserPassword(registrationId, encPass );
			if (i > 0) {
//				ONGCUtil.getInstance().sendOTPOnEmail(emailId, user.getOtp(),VigilanceConstant.CHANGE_PASSWORD);
				ONGCUtil.getInstance().sendConfirmOnEmail(emailId, VigilanceConstant.CHANGE_PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
