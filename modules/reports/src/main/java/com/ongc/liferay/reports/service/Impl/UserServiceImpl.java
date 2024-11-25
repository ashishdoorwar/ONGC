package com.ongc.liferay.reports.service.Impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.ongc.liferay.reports.dao.UserDao;
import com.ongc.liferay.reports.dao.Impl.UserDaoImpl;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.UserService;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public List<User> getBirthday(String date, String month, String locations) {
		
		return this.userDao.getBirthday(date, month, locations);
	}

	@Override
	public User getUserByCPFNumber(String cpfNo) {
		
		return this.userDao.getUserByCPFNumber(cpfNo);
	}

	@Override
	public boolean saveThanksnote(String fromCpf, String toCpf, String message) {
		
		return this.userDao.saveThanksnote(fromCpf, toCpf, message);
	}

	@Override
	public boolean updateUser(String cpf, String chk) {
		
		return this.userDao.updateUser(cpf, chk);
	}

	@Override
	public int getKCCount(String cpf, String chk) {
		
		return this.userDao.getKCCount(cpf, chk);
	}

	@Override
	public boolean saveUser(String cpf, String chk) {
		
		return this.userDao.saveUser(cpf, chk);
	}

	@Override
	public boolean check_cpf(String cpf) {
		
		return this.userDao.check_cpf(cpf);
	}
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		
		ServiceContext serviceContext=ServiceContextThreadLocal.getServiceContext();
		long userId = serviceContext.getUserId();
		com.liferay.portal.kernel.model.User user=null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDao.getUserByEmailId(user.getEmailAddress());
	}

	@Override
	public boolean updateUserProfile(User user) {
		
		return userDao.updateUserProfile(user);
	}


	
}
