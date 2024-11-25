package com.ongc.liferay.vigilance.dao.Impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.CountryManagementDao;
import com.ongc.liferay.vigilance.model.Country;

public class CountryManagementDaoImpl implements CountryManagementDao{
	

	@Override
	public List<Country> getAllCountry() {
		String sql = "SELECT ID_KEY, COUNTRY_NAME FROM COUNTRY_MASTER"; 
		List<Country> list = null;
		Connection connection  = DatasourceConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet=null;
		try {
			pstmt = connection.prepareStatement(sql);
			
			try {
				//pstmt.setInt(1, 128);
				resultSet=pstmt.executeQuery();
				list = new ArrayList<Country>();
				while (resultSet.next()) {
					Country country = new Country();
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
			//list = VigilanceCountryRowExtractor.extractRow();
		} catch (SQLException e) {
		}
	 catch (Exception e) {
	}
		finally{
			DatasourceConnection.closeConnection(connection, pstmt,resultSet);
		 }
		return list;
	}
}
