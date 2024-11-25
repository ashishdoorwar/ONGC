package com.ongc.liferay.csr.dao.Impl;

import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.ProgramNatureDao;
import com.ongc.liferay.csr.model.ProgramNatureBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *  
 * @author Ranjeet
 */
public class ProgramNatureDaoImpl implements ProgramNatureDao {

	public static ProgramNatureDaoImpl instance;

	public static ProgramNatureDaoImpl getInstance() {
		instance = new ProgramNatureDaoImpl();
		return instance;
	}

	public List<ProgramNatureBean> getProgramNatureList() {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		List<ProgramNatureBean> list = new ArrayList<ProgramNatureBean>();
		String query =szResBundl.getString("GETALLPROGRAMNATURE");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			ProgramNatureBean pnb;
			while (rs.next()) {
				pnb = new ProgramNatureBean();
				pnb.setId(rs.getInt("PRN_ID"));
				pnb.setName(rs.getString("PRN_NAME"));
				list.add(pnb);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

}
