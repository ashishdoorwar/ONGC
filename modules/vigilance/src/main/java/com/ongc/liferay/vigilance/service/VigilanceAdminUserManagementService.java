package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceAdminUser;

import java.sql.SQLException;


public interface VigilanceAdminUserManagementService {

	public VigilanceAdminUser selectUserByID(String emailId) throws SQLException;

	
	public int changeAdminUserPassword(int registrationId, String password) throws SQLException;

}
