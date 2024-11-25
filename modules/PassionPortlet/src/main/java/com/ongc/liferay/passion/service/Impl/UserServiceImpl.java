package com.ongc.liferay.passion.service.Impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.dao.Impl.UserDaoImpl;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao=new UserDaoImpl();
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
	public Object getUserByCPFNumber(String cpfNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
