package com.ongc.liferay.vigilance.dao;

import com.ongc.liferay.vigilance.model.VigilanceContent;

import java.util.List;


public interface ContentManagementDao {

	public List<VigilanceContent> getContentByType(String ContentType);
	

}
