package com.ongc.liferay.reports.service;

import com.ongc.liferay.reports.model.DirAssistanceBean;
import com.ongc.liferay.reports.model.OrganizationBean;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.model.UtilityBean;
import com.ongc.liferay.reports.model.WorkplaceBean;

import java.util.List;

public interface EDirectoryService {

	public List<OrganizationBean> searchOrganization(OrganizationBean organizationBean);
	
	public List<WorkplaceBean> searchWorkplace(WorkplaceBean workplaceBean );
	
	public List<UtilityBean> searchUtility(UtilityBean org);
	
	public List<DirAssistanceBean> searchAssisDir(DirAssistanceBean org);
	
	public List<User> searchLocation(User user);
}
