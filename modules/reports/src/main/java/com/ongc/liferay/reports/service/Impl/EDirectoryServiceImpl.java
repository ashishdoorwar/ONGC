package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.EDirectoryDao;
import com.ongc.liferay.reports.dao.Impl.EDirectoryDaoImpl;
import com.ongc.liferay.reports.model.DirAssistanceBean;
import com.ongc.liferay.reports.model.OrganizationBean;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.model.UtilityBean;
import com.ongc.liferay.reports.model.WorkplaceBean;
import com.ongc.liferay.reports.service.EDirectoryService;

import java.util.List;

public class EDirectoryServiceImpl implements EDirectoryService {

	private EDirectoryDao directoryDao=new EDirectoryDaoImpl();
	@Override
	public List<OrganizationBean> searchOrganization(OrganizationBean organizationBean) {

		
		return directoryDao.searchOrganization(organizationBean);
	}
	@Override
	public List<WorkplaceBean> searchWorkplace(WorkplaceBean workplaceBean) {
		// TODO Auto-generated method stub
		
		return directoryDao.searchWorkplace(workplaceBean);
	}
	@Override
	public List<UtilityBean> searchUtility(UtilityBean org) {
		// TODO Auto-generated method stub
		
		return directoryDao.searchUtility(org);
	}
	@Override
	public List<DirAssistanceBean> searchAssisDir(DirAssistanceBean org) {
		// TODO Auto-generated method stub
		
		return directoryDao.searchAssisDir(org);
	}
	@Override
	public List<User> searchLocation(User user) {
		// TODO Auto-generated method stub
		
		return directoryDao.searchLocation(user);
	}
	
	

	
}
