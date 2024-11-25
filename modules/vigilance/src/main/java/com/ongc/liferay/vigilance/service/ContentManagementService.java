package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceContent;

import java.util.List;

public interface ContentManagementService {

	public List<VigilanceContent> getContentByType(String ContentType);
	

}
