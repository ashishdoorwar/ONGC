package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.TelecomDirDao;
import com.ongc.liferay.reports.model.IsdBean;
import com.ongc.liferay.reports.model.MscBean;
import com.ongc.liferay.reports.model.StdBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelecomDirDaoImpl implements TelecomDirDao {
	

	private static final Log LOGGER = LogFactoryUtil.getLog(TelecomDirDaoImpl.class);
	
	public List<IsdBean> searchIsdCode(IsdBean isd) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<IsdBean> isdList = new ArrayList<IsdBean>();
		try {
			conn=DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(createsearchIsdQuery(isd));
			set = pstmt.executeQuery();
			while (set.next()) {
				isd = new IsdBean();
				isd.setCountry(set.getString("COUNTRY"));
				isd.setIddCode(set.getString("IDD_CODE"));
				isd.setIsdCode(set.getString("ISD_CODE"));
				isdList.add(isd);
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return isdList;
	}

	private String createsearchIsdQuery(IsdBean isd) {
		String query = "SELECT * FROM ISD";
		boolean f = false;
		if (isd != null && isd.getCountry() != null
				&& isd.getCountry().trim().length()>1) {
			query = query + " WHERE COUNTRY ='" + isd.getCountry().trim() + "'";
			f = true;
		}
		if (isd != null && isd.getIddCode() != null
				&& isd.getIddCode().trim().length()>1) {
			if (!f)
				query = query + " WHERE  IDD_CODE='" + isd.getIddCode().trim()
						+ "'";
			else
				query = query + " AND IDD_CODE='" + isd.getIddCode().trim()
						+ "'";
			f = true;
		}
		if (isd != null && isd.getIsdCode() != null
				&& isd.getIsdCode().trim().length()>1) {
			if (!f)
				query = query + " WHERE ISD_CODE='" + isd.getIsdCode().trim()
						+ "'";
			else
				query = query + " AND ISD_CODE='" + isd.getIsdCode().trim()
						+ "'";
			f = true;
		}
		
		return query;
	}

	public List<StdBean> searchStdCode(StdBean std) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<StdBean> stdList = new ArrayList<StdBean>();
		try {
			conn=DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(createsearchStdQuery(std));
			set = pstmt.executeQuery();
			while (set.next()) {
				std = new StdBean();
				std.setCircleName(set.getString("SERVICE_AREA"));
				std.setLdcsName(set.getString("LDCA"));
				std.setSdcaName(set.getString("SDCA"));
				std.setSdcaCode(set.getString("SDCA_CODE"));
				stdList.add(std);
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return stdList;
	}

	private String createsearchStdQuery(StdBean std) {
		String query = "SELECT * FROM STD";
		boolean f = false;
		if (std != null && std.getCircleName() != null
				&& std.getCircleName().trim().length()>1) {
			query = query + " WHERE SERVICE_AREA='" + std.getCircleName().trim()
					+ "'";
			f = true;
		}
		if (std != null && std.getLdcsName() != null
				&& std.getLdcsName().trim().length()>1) {
			if (f)
				query = query + " AND LDCA='" + std.getLdcsName().trim() + "'";
			else
				query = query + " WHERE LDCA='" + std.getLdcsName().trim()
						+ "'";
			f = true;
		}
		if (std != null && std.getSdcaName() != null
				&& std.getSdcaName().trim().length()>1) {
			if (f)
				query = query + " AND SDCA='" + std.getSdcaName().trim() + "'";
			else
				query = query + " WHERE SDCA='" + std.getSdcaName().trim()
						+ "'";
			f = true;
		}
		if (std != null && std.getSdcaCode() != null
				&& std.getSdcaCode().trim().length()>1) {
			if (f)
				query = query + " AND SDCA_CODE='" + std.getSdcaCode().trim()
						+ "'";
			else
				query = query + " WHERE SDCA_CODE='" + std.getSdcaCode().trim()
						+ "'";
			f = true;
		}
		return query;
	}

	public List<MscBean> searchMscCode(MscBean msc) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<MscBean> mscList = new ArrayList<MscBean>();
		try {
			conn=DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(createsearchMscQuery(msc));
			 set = pstmt.executeQuery();
			while (set.next()) {
				msc = new MscBean();
				msc.setNetwork(set.getString("NETWORK"));
				msc.setCircle(set.getString("CIRCLE"));
				msc.setCircleCategory(set.getString("CIRCLE_CATEGORY"));
				msc.setMobileOperator(set.getString("OPERATOR"));
				msc.setMncCode(set.getString("MNC_CODE"));
				msc.setStartSeries(set.getString("START_SERIES"));
				msc.setEndSeries(set.getString("END_SERIES"));
				msc.setCodeDetails(set.getString("CODE_DETAILS"));
				mscList.add(msc);
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return mscList;
	}

	private String createsearchMscQuery(MscBean msc) {
		String query = "SELECT * FROM MSC";
		boolean f = false;
		if (msc != null && msc.getNetwork() != null
				&& msc.getNetwork().trim().length()>1) {
			query = query + " WHERE NETWORK='" + msc.getNetwork().trim() + "'";
			f = true;
		}
		if (msc != null && msc.getCircle() != null
				&& msc.getCircle().trim().length()>1) {
			if (f)
				query = query + " AND CIRCLE='" + msc.getCircle().trim() + "'";
			else
				query = query + " WHERE CIRCLE='" + msc.getCircle().trim()
						+ "'";
			f = true;
		}
		if (msc != null && msc.getMobileOperator() != null
				&& msc.getMobileOperator().trim().length()>1) {
			if (f)
				query = query + " AND OPERATOR='"
						+ msc.getMobileOperator().trim() + "'";
			else
				query = query + " WHERE OPERATOR='"
						+ msc.getMobileOperator().trim() + "'";
			f = true;
		}
		if (msc != null && msc.getMncCode() != null
				&& msc.getMncCode().trim().length()>1) {
			if (f)
				query = query + " AND MNC_CODE='" + msc.getMncCode().trim()
						+ "'";
			else
				query = query + " WHERE MNC_CODE='" + msc.getMncCode().trim()
						+ "'";
			f = true;
		}
		if (msc != null && msc.getCodeDetails() != null
				&& msc.getCodeDetails().trim().length()>1) {
			if (f)
				query = query + " AND CODE_DETAILS LIKE '%"
						+ msc.getCodeDetails().trim() + ",%'";
			else
				query = query + " WHERE CODE_DETAILS LIKE '%"
						+ msc.getCodeDetails().trim() + ",%'";
			f = true;
		}
		return query;
	}

}
