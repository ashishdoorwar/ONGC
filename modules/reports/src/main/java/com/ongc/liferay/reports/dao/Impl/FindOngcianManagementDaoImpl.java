package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.report.row.extractor.FindOngcianRowExtractor;
import com.ongc.liferay.report.row.extractor.OrgLocationRowExtractor;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.FindOngcianManagementDao;
import com.ongc.liferay.reports.model.OrgLocation;
import com.ongc.liferay.reports.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FindOngcianManagementDaoImpl implements FindOngcianManagementDao{


	private static final Log LOGGER = LogFactoryUtil.getLog(FindOngcianManagementDaoImpl.class);
	

	protected String basicOrgLocationSql = "select * from ONGC_ORGANIZATION_LOCATION ORDER BY CITY_NAME";

	protected String basicOngcianSelectSql = "select distinct oum.CPF_NUMBER, oum.EMP_NAME, oum.EMP_LEVEL, "
			+ "oum.DESIGNATION, oum.PLACE_POSTING, oum.ORG_UNIT_NUM, oum.DT_JOINING, oum.DT_BIRTH, "
			+ "oum.BIRTH_PLACE, oum.HOMETOWN, oum.RELIGION, oum.CATEGORY, oum.STATE, oum.NATIONALITY, "
			+ "oum.MARITAL_STATUS, oum.DT_OF_MARRIAGE, oum.NAME_OF_SPOUSE, oum.CPF_NO_SPOUSE, "
			+ "oum.DT_OF_JOINING_TRSFR, oum.PLACE_POSTING_TRSFR, oum.LOCAL_ADDRESS, oum.PHONE_NUM_RES, "
			+ "oum.PHONE_NUM_OFF, oum.BOARD_NUMBER, oum.EXT_NUMBER, oum.FAX_NUMBER, oum.MOBILE_NUMBER, "
			+ "oum.E_MAILID_OFF, oum.E_MAILID_PER, oum.BLOOD_GROUP, oum.CREATED_ON, oum.CREATED_BY, "
			+ "oum.MODIFIED_ON, oum.MODIFIED_BY, oum.BOARD_NO_RES, oum.EXT_NUMBER_RES, oum.ICNET_NUM_RES, "
			+ "oum.DEPT, oum.WORKPLACE, oum.OFFICE_ADDRESS, oum.RESIDENCE_ADDRESS, oum.TITLE, "
			+ "oum.ABOUT_ME "
			+ "from ONGC_USER_MASTER oum LEFT JOIN PSN_MYPASSION pmp on "
			+ "UPPER(oum.CPF_NUMBER )=UPPER(pmp.cpf_no) ";

	@Override
	public List<OrgLocation> selectOrgainzationLocation() {
		Connection connection =null;
		Statement stmt = null;
		ResultSet rs = null;
		List<OrgLocation> list = null;
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(basicOrgLocationSql);
			if (rs != null) {
				list = OrgLocationRowExtractor.extractRow(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}
		return list;
	}

	@Override
	public List<User> findOngcian(String query) {
		Connection connection1 =null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		basicOngcianSelectSql = basicOngcianSelectSql + query;
		List<User> list = null;
		try {
			connection1 = DatasourceConnection.getConnection();
			stmt1 = connection1.createStatement();
			rs1 = stmt1.executeQuery(basicOngcianSelectSql);
			list = FindOngcianRowExtractor.extractRow(rs1, connection1);
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection1, stmt1, rs1);
		}
		return list;
	}
	
	public String subPassion(String cpfNo, Connection conn) {
		Statement stmt1 = null;
		//conn = DatasourceConnection.getConnection();
		ResultSet rs1 = null;
		String sp = "";
		String psName = "";
		String subPsnName = "";
		String query = "select pmyp.passion_name,pmsp.sub_passion from psn_mypassion pmp,psn_ms_subpassion pmsp,psn_ms_passion pmyp where PMP.PASSION_SUB_CATEGORY=PMSP.SUBPASSION_ID and pmyp.PASSION_ID=pmp.PASSION_CATEGORY and PMP.CPF_NO='"
				+ cpfNo + "' order by psn_mp_id";
		try {
			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery(query);
			while (rs1.next()) {
				psName = rs1.getString("passion_name");
				subPsnName = rs1.getString("sub_passion");
				sp = sp + psName + " | " + subPsnName + "; ";
			}
			if (!sp.equals("")) {
				int i = sp.lastIndexOf(";");
				sp = sp.substring(0, i);
				sp = sp.replaceFirst(" | ", " ");
			}
			stmt1.close();
		} catch (SQLException e) {
			LOGGER.error("erro"+e);
		} finally {
			
			//DatasourceConnection.closeConnection(conn, stmt1, rs1);
		}
		return sp;
	}
}