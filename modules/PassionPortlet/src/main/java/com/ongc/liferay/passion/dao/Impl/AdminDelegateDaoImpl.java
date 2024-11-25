package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.AdminDelegateDao;
import com.ongc.liferay.passion.model.Passion;
import com.ongc.liferay.passion.model.PassionMaster;
import com.ongc.liferay.passion.model.SubPassion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDelegateDaoImpl implements AdminDelegateDao{

	
	public List<Object> getPassionList() {
		 Connection conn;
		 PreparedStatement ps;
		 ResultSet rs;
		 PassionMaster passionMaster=null;
		 List<Object> passionList=new ArrayList<Object>();
		try {
			conn = DatasourceConnection.getConnection();
			ps = conn
					.prepareStatement("select * from PSN_MS_PASSION order by created_on desc");
			rs = ps.executeQuery();
			while (rs.next()) {
				passionMaster = new PassionMaster();
				passionMaster.setPassionId(rs.getString(2));
				passionMaster.setPassionName(rs.getString(3));
				passionMaster.setPassionDescription(rs.getString(4));
				passionMaster.setPublished(rs.getString(5));
				passionMaster.setCreatedBy(rs.getString(6));
				passionMaster.setCreatedOn(rs.getTime(7));
				// passionMaster.setPassionImage(rs.getBlob(8));
				passionList.add(passionMaster);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return passionList;
	}
	public List<Passion> getAllPassionList() {
		 Connection conn=null;
		 PreparedStatement ps=null;
		List<Passion> allPassionList = new ArrayList<Passion>();
		String query = "select passion_id, passion_name from psn_ms_passion pmp order by pmp.passion_name";

		ResultSet rs = null;
		try {
			conn = DatasourceConnection.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				Passion passion = new Passion();
				passion.setPassionId(rs.getString(1));
				passion.setPassionName(rs.getString(2));
				allPassionList.add(passion);
			}
			
			for(Passion passion:allPassionList){
				passion.setSubPassionList(getSubPassion(passion.getPassionId(), conn));
			}

		} catch (Exception e) {

			//system.out.println(e);
		} finally {
			DatasourceConnection.closeConnection(conn, ps, rs);
		}

		return allPassionList;
	}
	private List<SubPassion> getSubPassion(String psnId, Connection conn2) {
		List<SubPassion> subPassionList = new ArrayList<SubPassion>();
		String query = "select subpassion_id, sub_passion FROM psn_ms_subpassion pms where PMS.PASSION_ID='"
				+ psnId + "' order by pms.sub_passion";

		ResultSet rs1 = null;
		PreparedStatement ps1 = null;
		try {

			ps1 = conn2.prepareStatement(query);

			rs1 = ps1.executeQuery();

			while (rs1.next()) {

				SubPassion subPassion = new SubPassion();

				subPassion.setSubPassionId(rs1.getString(1));
				subPassion.setSubPassion(rs1.getString(2));
				subPassionList.add(subPassion);
			}

		} catch (Exception e) {

			//system.out.println(e);
		} finally {
			// ConnMgmt.closeConnection(rs, ps, conn);
			rs1 = null;
			ps1 = null;
		}

		return subPassionList;
	}
}
