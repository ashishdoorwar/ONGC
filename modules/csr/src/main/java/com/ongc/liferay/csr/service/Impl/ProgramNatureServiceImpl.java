package com.ongc.liferay.csr.service.Impl;

import com.ongc.liferay.csr.dao.ProgramNatureDao;
import com.ongc.liferay.csr.dao.Impl.ProgramNatureDaoImpl;
import com.ongc.liferay.csr.model.ProgramNatureBean;
import com.ongc.liferay.csr.service.ProgramNatureService;

import java.util.List;
/**
 *  
 * @author Ranjeet
 */
public class ProgramNatureServiceImpl implements ProgramNatureService {

	private ProgramNatureDao programNatureDao=new ProgramNatureDaoImpl();
	@Override
	public List<ProgramNatureBean> getProgramNatureList() {
		// TODO Auto-generated method stub
		return programNatureDao.getProgramNatureList();
	}

}
