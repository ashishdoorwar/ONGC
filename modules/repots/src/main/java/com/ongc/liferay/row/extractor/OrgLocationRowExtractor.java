package com.ongc.liferay.row.extractor;

import com.ongc.liferay.model.OrgLocation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrgLocationRowExtractor {
	public static List<OrgLocation> extractRow(ResultSet resultSet) {
	OrgLocation orgLocation; 
	List<OrgLocation> orgLocations = new ArrayList<OrgLocation>();
	try {
		while (resultSet.next()) {
			orgLocation = new OrgLocation();
			orgLocation.setIdKey(resultSet.getString("ID_KEY"));
			orgLocation.setCityName(resultSet.getString("CITY_NAME"));
			orgLocations.add(orgLocation);
		}
	} catch (SQLException e) {
		//e.printStackTrace();
	}
	return orgLocations;
}
}
