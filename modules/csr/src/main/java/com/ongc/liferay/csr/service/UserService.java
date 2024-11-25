package com.ongc.liferay.csr.service;

import com.ongc.liferay.csr.model.ChangePasswordBean;
import com.ongc.liferay.csr.model.User;

import java.util.List;

public interface UserService {

	User findByLoginIdAndPassword(String username, String encode);

	boolean checkOldPassword(String oldPassword, String loginId);

	boolean changePassword(ChangePasswordBean cpBeans, String loginId);
	
	public List<User> findAll();

	boolean resetPassword(String loginId);

	public List<User> getSearchUserList(User obj);
	
	public Boolean saveUserDetails(User obj);
}
