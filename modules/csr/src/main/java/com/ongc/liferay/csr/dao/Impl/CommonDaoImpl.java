package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ranjeet
 */
public class CommonDaoImpl {
	
	public static CommonDaoImpl instance;

	public static CommonDaoImpl getInstance() {
		instance = new CommonDaoImpl();
		return instance;
	}

	synchronized public int getMaxIdFromTable(String tableName, String colName) {
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int id = 0;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return ++id;
	}

}
