package com.ongc.liferay.reports.util;

import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.dao.UserDao;
import com.ongc.liferay.reports.dao.Impl.UserDaoImpl;
import com.ongc.liferay.reports.model.User;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;

public class GetBirthdayUtil {
	
	private UserDao userDao=new UserDaoImpl();
	
	public List<User> getBirthday(RenderRequest renderRequest) throws PortletException {
		// TODO Auto-generated method stub
		String day=ParamUtil.getString(renderRequest, "day");
		String month=ParamUtil.getString(renderRequest, "month");
		String locations=ParamUtil.getString(renderRequest, "locations");
		List<User> birthday = userDao.getBirthday(day , month , locations);
		return birthday;
	}
	
}
