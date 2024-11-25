package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.OrgLocation;
import com.ongc.liferay.reports.model.User;

import java.util.List;

public interface FindOngcianManagementDao {
public List<OrgLocation> selectOrgainzationLocation();
	
	public List<User> findOngcian(String query);
}
