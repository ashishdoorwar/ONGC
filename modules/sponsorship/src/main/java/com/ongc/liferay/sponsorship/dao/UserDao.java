package com.ongc.liferay.sponsorship.dao;



import com.ongc.liferay.sponsorship.model.ChangePasswordBean;
import com.ongc.liferay.sponsorship.model.User;

import java.util.List;

public interface UserDao {
	public User findByLoginIdAndPassword(String username, String encode);

	public boolean checkOldPassword(String oldPassword, String loginId);

	public boolean changePassword(ChangePasswordBean cpBeans, String loginId);
	
	public List<User> findAll();

	public boolean resetPassword(String loginId);

	public List<User> getSearchUserList(User user);

	public boolean saveUserDetails(User user);
}
