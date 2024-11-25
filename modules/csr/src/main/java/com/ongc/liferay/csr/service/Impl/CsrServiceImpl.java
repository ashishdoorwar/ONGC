package com.ongc.liferay.csr.service.Impl;

import com.ongc.liferay.csr.dao.CsrDao;
import com.ongc.liferay.csr.dao.Impl.CsrDaoImpl;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;
import com.ongc.liferay.csr.service.CsrService;

import java.util.List;

/**
 * @author Ranjeet
 */
public class CsrServiceImpl implements CsrService{

	private CsrDao csrDao=new CsrDaoImpl();
	
	public List<CSRProgramModel> findAll(FilterBean flterBean){		
		return csrDao.getAll(flterBean);
	}

	@Override
	public CSRProgramModel findById(int id) {
		// TODO Auto-generated method stub
		return csrDao.findById(id);
	}

	@Override
	public String save(CSRProgramModel model) {
		// TODO Auto-generated method stub
		return csrDao.save(model);
	}

	@Override
	public Boolean update(CSRProgramModel model) {
		// TODO Auto-generated method stub
		return csrDao.update(model);
	}
}
