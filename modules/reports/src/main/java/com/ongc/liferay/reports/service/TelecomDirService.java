package com.ongc.liferay.reports.service;

import com.ongc.liferay.reports.model.IsdBean;
import com.ongc.liferay.reports.model.MscBean;
import com.ongc.liferay.reports.model.StdBean;

import java.util.List;

public interface TelecomDirService {

	
	public List<IsdBean> searchIsdCode(IsdBean isd) ;
	
	public List<StdBean> searchStdCode(StdBean std);
	
	public List<MscBean> searchMscCode(MscBean msc);
}
