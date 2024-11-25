package com.ongc.liferay.vigilance.dao;

import com.ongc.liferay.vigilance.model.VigilanceAdminUser;

public interface VigilanceAdminUserDao {
	
	
	public String changeUserStatus(String emailId, String status);
	
	public VigilanceAdminUser getUserByEmailId(String emailId);

	public void changePassword(int registrationId,String emailId, String password) ;

}
