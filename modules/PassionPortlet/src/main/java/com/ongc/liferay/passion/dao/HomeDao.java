package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.HomeNews;
import com.ongc.liferay.passion.model.HomeTrending;

import java.util.List;

public interface HomeDao {

	List getPassion(String subPsn);

	List getPassionEnrlUser(String subPsn);
	
	List<HomeTrending> trending();
	
	List myTrending(String cpfNo);
	
	List fetchUpdates(String cpf);

	public List fetchElite();
	
	public List news();
	
	public HomeNews newsDetail(String newsId);
}
