package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceUser;

public interface VigilanceUserService {
	
	public String registerVigilanceUser(VigilanceUser user , String ipAddress);
	
	public String changeUserStatus(String emailId, String status);
	
	public VigilanceUser getUserByEmailId(String emailId);
	
	public VigilanceUser getUserByMobile(String mobile);

	public void changePassword(int registrationId,String emailId, String password) ;

}
