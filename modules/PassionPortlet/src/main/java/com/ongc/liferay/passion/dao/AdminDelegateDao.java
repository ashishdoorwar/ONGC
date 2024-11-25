package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.Passion;

import java.util.List;

public interface AdminDelegateDao {

	public List<Object> getPassionList() ;
	public List<Passion> getAllPassionList();
}
