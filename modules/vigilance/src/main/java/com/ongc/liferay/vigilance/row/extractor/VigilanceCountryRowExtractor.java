package com.ongc.liferay.vigilance.row.extractor;

import com.ongc.liferay.vigilance.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VigilanceCountryRowExtractor {


	public static List<Country> extractRow(ResultSet resultSet) {
		Country country;
		List<Country> list = new ArrayList<Country>();
		try {
			while (resultSet.next()) {
				country = new Country();
				country.setIdKey(resultSet.getString("ID_KEY"));
				country.setCountryName(resultSet.getString("COUNTRY_NAME"));
				list.add(country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
