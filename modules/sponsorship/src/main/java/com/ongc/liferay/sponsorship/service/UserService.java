package com.ongc.liferay.sponsorship.service;


import com.ongc.liferay.sponsorship.model.ChangePasswordBean;
import com.ongc.liferay.sponsorship.model.User;

import java.util.List;

public interface UserService {

	User findByLoginIdAndPassword(String username, String encode);

	boolean checkOldPassword(String oldPassword, String loginId);

	boolean changePassword(ChangePasswordBean cpBeans, String loginId);
	
	public List<User> findAll();

	boolean resetPassword(String loginId);

	List<User> getSearchUserList(User user);

	boolean saveUserDetails(User user);

}
