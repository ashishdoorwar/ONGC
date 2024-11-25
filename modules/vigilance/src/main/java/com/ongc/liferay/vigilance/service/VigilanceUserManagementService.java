package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceUser;

import java.sql.SQLException;

public interface VigilanceUserManagementService {
	
	public int changeUserStatusactive(String emailId , String status, java.sql.Date currentDate) throws SQLException;

	public VigilanceUser selectUserByID(String emailId) throws SQLException;

	public int registerUser(VigilanceUser user, String ipAddress) throws SQLException;

	public int changeUserPassword(int registrationId, String password) throws SQLException;

}
