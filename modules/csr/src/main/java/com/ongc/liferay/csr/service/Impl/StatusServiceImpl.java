package com.ongc.liferay.csr.service.Impl;

import com.ongc.liferay.csr.dao.StatusDao;
import com.ongc.liferay.csr.dao.Impl.StatusDaoImpl;
import com.ongc.liferay.csr.model.StatusBean;
import com.ongc.liferay.csr.service.StatusService;

import java.util.List;

/**
 * @author Ranjeet
 */
public class StatusServiceImpl implements StatusService {

	private StatusDao statusDao=new StatusDaoImpl();
	
	@Override
	public List<StatusBean> getAllStatus() {
		return statusDao.getAllStatus();
	}

}
