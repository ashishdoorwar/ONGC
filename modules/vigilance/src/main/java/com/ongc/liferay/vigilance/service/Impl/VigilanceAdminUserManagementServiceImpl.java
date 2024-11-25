package com.ongc.liferay.vigilance.service.Impl;

import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.service.VigilanceAdminUserManagementService;

import java.sql.SQLException;


public class VigilanceAdminUserManagementServiceImpl implements VigilanceAdminUserManagementService{

	@Override
	public VigilanceAdminUser selectUserByID(String emailId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changeAdminUserPassword(int registrationId, String password) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


}
