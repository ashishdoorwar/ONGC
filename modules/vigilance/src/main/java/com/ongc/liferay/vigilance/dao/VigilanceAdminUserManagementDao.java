package com.ongc.liferay.vigilance.dao;

import com.ongc.liferay.vigilance.model.VigilanceAdminUser;

import java.sql.SQLException;



public interface VigilanceAdminUserManagementDao {

	public VigilanceAdminUser selectUserByID(String emailId) throws SQLException;

	
	public int changeAdminUserPassword(int registrationId, String password) throws SQLException;

}
