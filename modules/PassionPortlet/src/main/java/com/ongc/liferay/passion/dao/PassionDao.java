package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.Passion;
import com.ongc.liferay.passion.model.SubPassion;

import java.util.List;

public interface PassionDao {

	List<Passion> getUserPassion(String cpfNo);

	List<Passion> getPassionName();

	List<SubPassion> getSubPassionName();

}
