package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.SQLException;


import com.ongc.liferay.vigilance.dao.VigilanceAdminUserDao;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

public class VigilanceAdminUserDaoImpl implements VigilanceAdminUserDao {

	
	
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
	public VigilanceAdminUser getUserByEmailId(String emailId) {
		VigilanceAdminUser user = null;
		 try {
			 user = VigilanceFactory.getAdminUserManagementInstance().selectUserByID(emailId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user ;
	}

	@Override
	public void changePassword(int registrationId,String emailId, String password) {
//		String encPass = TEA.encrypt(password, emailId ); // to encrypt password
	 	String encPass="";
		try {
			int i = VigilanceFactory.getAdminUserManagementInstance().changeAdminUserPassword(registrationId, encPass );
			if (i > 0) {
//				ONGCUtil.getInstance().sendOTPOnEmail(emailId, user.getOtp(),VigilanceConstant.CHANGE_PASSWORD);
				ONGCUtil.getInstance().sendConfirmOnEmail(emailId, VigilanceConstant.CHANGE_PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
