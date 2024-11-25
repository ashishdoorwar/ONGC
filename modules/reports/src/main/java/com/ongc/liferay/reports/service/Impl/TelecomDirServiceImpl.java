package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.TelecomDirDao;
import com.ongc.liferay.reports.dao.Impl.TelecomDirDaoImpl;
import com.ongc.liferay.reports.model.IsdBean;
import com.ongc.liferay.reports.model.MscBean;
import com.ongc.liferay.reports.model.StdBean;
import com.ongc.liferay.reports.service.TelecomDirService;

import java.util.List;

public class TelecomDirServiceImpl implements TelecomDirService{

	private TelecomDirDao telecomDirDao=new TelecomDirDaoImpl();
	
	@Override
	public List<IsdBean> searchIsdCode(IsdBean isd) {
		// TODO Auto-generated method stub
		
		return telecomDirDao.searchIsdCode(isd);
	}

	@Override
	public List<StdBean> searchStdCode(StdBean std) {
		// TODO Auto-generated method stub
		
		return telecomDirDao.searchStdCode(std);
	}

	@Override
	public List<MscBean> searchMscCode(MscBean msc) {
		// TODO Auto-generated method stub
		
		return telecomDirDao.searchMscCode(msc);
	}

}
