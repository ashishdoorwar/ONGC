package com.ongc.liferay.dao;

import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.model.Covid19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Covid19Dao {
	
	
	public List<Covid19> getCovid19DetailsUserByCpfNo(String cpfNo) {
		List<Covid19> list = new ArrayList<Covid19>();
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet set= null;

		try {
			String query = "SELECT * FROM TT_COVID19_DETAILS WHERE CPFNO=? ORDER BY COVID_ID DESC";
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			set = pstmt.executeQuery();
			while (set.next()) {
				Covid19 covid19 = new Covid19();
				covid19.setCpfNo(set.getString("COVID_ID"));
				covid19.setCpfNo(set.getString("CPFNO"));
				covid19.setConcern(set.getString("CONCERN"));
				covid19.setCreatedDate(set.getString("CREATED_DATE"));
				covid19.setStatus(set.getString("STATUS"));

				list.add(covid19);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return list;

	}

	public List<Covid19> getAll() {
		List<Covid19> list = new ArrayList<Covid19>();
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet set= null;
		try {
			String query = "SELECT * FROM TT_COVID19_DETAILS ORDER BY COVID_ID DESC";
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			set = pstmt.executeQuery();
			while (set.next()) {
				Covid19 covid19 = new Covid19();
				covid19.setCpfNo(set.getString("COVID_ID"));
				covid19.setCpfNo(set.getString("CPFNO"));
				covid19.setConcern(set.getString("CONCERN"));
				covid19.setCreatedDate(set.getString("CREATED_DATE"));
				covid19.setStatus(set.getString("STATUS"));
				list.add(covid19);
			}
		} catch (Exception e) {
			// covid19 = null;
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return list;

	}

	public boolean insertCovid19(String cpfNo, String msg) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt= null;

		String query = "INSERT INTO TT_COVID19_DETAILS(CPFNO,CONCERN,CREATED_DATE,covid_id,STATUS) "
				+ " VALUES (?,?,?,?,'Y')";
		try {
			conn = DatasourceConnection.getConnection();

			int covid_id = getMaxIdFromTable("TT_COVID19_DETAILS", "covid_id");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, msg);
			Date d = new Date();
			java.sql.Date timestamp = new java.sql.Date(d.getTime());
			pstmt.setDate(3, timestamp);
			pstmt.setInt(4, covid_id);
			flag = 0 < pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}

	
	public boolean insertChiefLabFeedback(String cpfNo, String fName,String lName,String mobile,String email,String comment) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt= null;

		String query = "INSERT INTO CHIEF_LAB_FEEDBACK(FID,CPFNO,FIRST_NAME,LAST_NAME,MOBILE_NO,EMAIL_ID,COMMENT) "
				+ " VALUES (?,?,?,?,?,?,?)";
		try {
			conn = DatasourceConnection.getConnection();
			int fid = getMaxIdFromTable("chief_lab_feedback", "fid");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fid);
			pstmt.setString(2, cpfNo);
			pstmt.setString(3, fName);
			pstmt.setString(4, lName);
			pstmt.setString(5, mobile);
			pstmt.setString(6, email);
			pstmt.setString(7, comment);
			flag = 0 < pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}

	
	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;
		Connection connection = null;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return ++id;
	}
}
