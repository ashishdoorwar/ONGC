package com.ongc.liferay.sponsorship.service.Impl;



import com.ongc.liferay.sponsorship.dao.UserDao;
import com.ongc.liferay.sponsorship.dao.Impl.UserDaoImpl;
import com.ongc.liferay.sponsorship.model.ChangePasswordBean;
import com.ongc.liferay.sponsorship.model.User;
import com.ongc.liferay.sponsorship.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService{
	private UserDao userDao=new UserDaoImpl();
	@Override
	public User findByLoginIdAndPassword(String username, String encode) {
		
		return userDao.findByLoginIdAndPassword(username, encode);
	}
	@Override
	public boolean checkOldPassword(String oldPassword, String loginId) {
		// TODO Auto-generated method stub
		return userDao.checkOldPassword(oldPassword,loginId);
	}
	@Override
	public boolean changePassword(ChangePasswordBean cpBeans, String loginId) {
		// TODO Auto-generated method stub
		return userDao.changePassword(cpBeans,loginId);
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Override
	public boolean resetPassword(String loginId) {
		// TODO Auto-generated method stub
		return userDao.resetPassword(loginId);
	}
	@Override
	public List<User> getSearchUserList(User user) {
		// TODO Auto-generated method stub
		return userDao.getSearchUserList(user);
	}
	@Override
	public boolean saveUserDetails(User user) {
		// TODO Auto-generated method stub
		return userDao.saveUserDetails(user);
		
	}

}
