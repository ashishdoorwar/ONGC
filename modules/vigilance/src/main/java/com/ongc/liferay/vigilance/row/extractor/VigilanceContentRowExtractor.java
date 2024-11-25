package com.ongc.liferay.vigilance.row.extractor;

import com.ongc.liferay.vigilance.model.VigilanceContent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VigilanceContentRowExtractor {
	
	public static List<VigilanceContent> extractRow(ResultSet resultSet){
		VigilanceContent content = null;
		List<VigilanceContent> contents = new ArrayList<VigilanceContent>();
		try {
			while(resultSet.next()) {
				content = new VigilanceContent();
				content.setIdKey(resultSet.getString("ID_KEY"));
				content.setContent(resultSet.getString("CONTENT"));
				content.setContentType(resultSet.getString("CONTENT_TYPE"));
				content.setDescription(resultSet.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contents;
	}
}
