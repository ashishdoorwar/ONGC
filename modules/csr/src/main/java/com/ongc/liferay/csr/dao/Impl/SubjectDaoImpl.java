package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.SubjectDao;
import com.ongc.liferay.csr.model.SubjectBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ranjeet
 */
public class SubjectDaoImpl implements SubjectDao {
	
	public static SubjectDaoImpl instance;
	public static SubjectDaoImpl getInstance() {
		instance = new SubjectDaoImpl();
		return instance;
	}

	public List<SubjectBean> getSubjectList() {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		String query =szResBundl.getString("GETALLSUBJECT");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SubjectBean subject;
			while (rs.next()) {
				subject = new SubjectBean();
				subject.setSubId(rs.getInt("SUBID"));
				subject.setSubName(rs.getString("SUBNAME"));
				list.add(subject);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement,rs );
		}
		return list;
	}

	public String getSubjectName(String subid) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String subname = null;
		String query = "SELECT SUBNAME FROM SUBJECT_MASTER where SUBID= ?";
		int subjectid = Integer.parseInt(subid);
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				subname = rs.getString("SUBNAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return subname;
	}

	

}
