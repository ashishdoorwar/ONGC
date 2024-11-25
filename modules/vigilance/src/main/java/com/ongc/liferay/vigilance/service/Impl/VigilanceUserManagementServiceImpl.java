package com.ongc.liferay.vigilance.service.Impl;

import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.service.VigilanceUserManagementService;

import java.sql.Date;
import java.sql.SQLException;

public class VigilanceUserManagementServiceImpl implements VigilanceUserManagementService{

	@Override
	public int changeUserStatusactive(String emailId, String status, Date currentDate) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VigilanceUser selectUserByID(String emailId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerUser(VigilanceUser user, String ipAddress) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeUserPassword(int registrationId, String password) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
