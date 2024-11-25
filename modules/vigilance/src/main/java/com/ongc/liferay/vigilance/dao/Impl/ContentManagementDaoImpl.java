package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.ContentManagementDao;
import com.ongc.liferay.vigilance.model.VigilanceContent;

public class ContentManagementDaoImpl implements ContentManagementDao {



	@Override
	public List<VigilanceContent> getContentByType(String ContentType) {
		String sql = "SELECT ID_KEY, CONTENT, DESCRIPTION FROM CONTENT_MASTER where CONTENT_TYPE = ?";
		PreparedStatement pstmt =  null;
		List<VigilanceContent> contents = null;
		Connection connection = null;
		ResultSet resultSet =null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ContentType);
			resultSet = pstmt.executeQuery();
			VigilanceContent content;
			contents = new ArrayList<VigilanceContent>();
			
			while (resultSet.next()) {
				content = new VigilanceContent();
				content.setIdKey(resultSet.getString("ID_KEY"));
				content.setContent(resultSet.getString("CONTENT"));
				contents.add(content);
			}
			
		} catch (SQLException e) {
		} finally{
			 DatasourceConnection.closeConnection( connection,pstmt,resultSet);	
		 }
		return contents;
	}

}
