package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.IsdBean;
import com.ongc.liferay.reports.model.MscBean;
import com.ongc.liferay.reports.model.StdBean;

import java.util.List;

public interface TelecomDirDao {

	
	public List<IsdBean> searchIsdCode(IsdBean isd) ;
	
	public List<StdBean> searchStdCode(StdBean std);
	
	public List<MscBean> searchMscCode(MscBean msc);
}
