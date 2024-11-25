package com.ongc.liferay.csr.service.Impl;

import com.ongc.liferay.csr.dao.ApprovingAuthorityDao;
import com.ongc.liferay.csr.dao.Impl.ApprovingAuthorityDaoImpl;
import com.ongc.liferay.csr.model.ApprovingAuthorityBean;
import com.ongc.liferay.csr.service.ApprovingAuthorityService;

import java.util.List;
/**
 *  
 * @author Ranjeet
 */
public class ApprovingAuthorityServiceImpl implements ApprovingAuthorityService{

	private ApprovingAuthorityDao approvingAuthorityDao=new ApprovingAuthorityDaoImpl(); 
	@Override
	public List<ApprovingAuthorityBean> getApprovingAuthList() {
		// TODO Auto-generated method stub
		return approvingAuthorityDao.getApprovingAuthList();
	}

}
