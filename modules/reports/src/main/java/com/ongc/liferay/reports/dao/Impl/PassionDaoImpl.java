package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.PassionDao;
import com.ongc.liferay.reports.model.Passion;
import com.ongc.liferay.reports.model.SubPassion;
import com.ongc.liferay.reports.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassionDaoImpl implements PassionDao{
	private static final Log LOGGER = LogFactoryUtil.getLog(PassionDaoImpl.class);
	
	
	@Override
	public List<Passion> getUserPassion(String cpf_no) {
		PreparedStatement pstmt =null;
		ResultSet resultSet =null;
		Connection connection=null;
		connection = DatasourceConnection.getConnection();
		List<Passion>  categoryList1 = new ArrayList<Passion> ();
		String query = "select distinct pmp.psn_mp_id,psmp.passion_name,psmp.passion_id,pmsp.sub_passion,pmsp.subpassion_id,pmp.comments from psn_mypassion pmp,psn_ms_passion psmp,psn_ms_subpassion pmsp where pmp.passion_category=psmp.passion_id and pmsp.subpassion_id=pmp.passion_sub_category and pmp.cpf_no=? order by psn_mp_id";
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cpf_no);
			resultSet = pstmt.executeQuery();
			Passion passion = null;
			while (resultSet.next()) {
				passion = new Passion();
				passion.setPsnMPID(resultSet.getString("PSN_MP_ID"));
				passion.setPassionName(resultSet.getString("PASSION_NAME"));
				passion.setPassionId(resultSet.getString("PASSION_ID"));
				passion.setSubPassion(resultSet.getString("SUB_PASSION"));
				passion.setSubPassionId(resultSet.getString("SUBPASSION_ID"));
				passion.setComments(resultSet.getString("COMMENTS"));
				categoryList1.add(passion);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("erro"+e);
			}
		}
		return categoryList1;
	}


	@Override
	public String getUserPassionMPID(String pID, String subID, String cpf) {
		PreparedStatement pstmt =null;
		ResultSet set =null;
		Connection conn=null;
		conn = DatasourceConnection.getConnection();
		String psnMPID=null;
		String query = "select PSN_MP_ID from PSN_MYPASSION where PASSION_CATEGORY=? and PASSION_SUB_CATEGORY=? and CPF_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pID);
			pstmt.setString(2, subID);
			pstmt.setString(3, cpf);
			set = pstmt.executeQuery();
			Passion passion = null;
			while (set.next()) {
				psnMPID=set.getString("PSN_MP_ID");
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally {
			DatasourceConnection.closeConnection(conn,pstmt,set);
		}
		return psnMPID;
	}


	@Override
	public List<Passion> getPassionName() {
		PreparedStatement pstmt =null;
		ResultSet set =null;
		Connection conn=null;
		List<Passion> categoryList=new ArrayList<Passion>();
		String query="select PASSION_ID,PASSION_NAME from psn_ms_passion order by passion_name";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			set=pstmt.executeQuery();
			Passion passion=null;
			while(set.next()){
				passion=new Passion();
				passion.setPassionId(set.getString("PASSION_ID"));
				passion.setPassionName(set.getString("PASSION_NAME"));
//				passion.setPassionDescription(set.getString("PASSION_DESCRIPTION"));
//				passion.setPublished(set.getString("PUBLISHED"));
//				passion.setCreatedBy(set.getString("CREATED_BY"));
				categoryList.add(passion);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);
		}	
		return categoryList;
	}


	/*@Override
	public List<SubPassion> getSubPassionName() {
		PreparedStatement pstmt =null;
		ResultSet set =null;
		Connection conn=null;
		List<SubPassion> categoryList=new ArrayList<SubPassion>();
		try{
			String query="SELECT pms.PASSION_ID,pms.SUBPASSION_ID,pms.SUB_PASSION,pms.CREATED_BY,(select PASSION_NAME from PSN_MS_PASSION where pms.PASSION_ID=PASSION_ID) PASSION_NAME FROM PSN_MS_SUBPASSION pms ORDER BY SUB_PASSION";
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			set=pstmt.executeQuery();
			SubPassion subPassion=null;
			while(set.next()){
				subPassion=new SubPassion();
				subPassion.setPassionId(set.getString("PASSION_ID"));
				subPassion.setSubPassionId(set.getString("SUBPASSION_ID"));
				subPassion.setSubPassion(set.getString("SUB_PASSION"));
				subPassion.setCreatedBy(set.getString("CREATED_BY"));
				subPassion.setPassionName(set.getString("PASSION_NAME"));
				categoryList.add(subPassion);	
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);
		}	
		return categoryList;
	}*/


	@Override
	public void updatePassionCat1ByPsnId(Passion passionFirst, String cpfNumber, String passionFirstPsnMpId,
			Connection con) {
		PreparedStatement pstmt =null;
			try{
				String query="UPDATE PSN_MYPASSION SET CPF_NO=?,PASSION_CATEGORY=?,PASSION_SUB_CATEGORY=?,COMMENTS=? WHERE PSN_MP_ID=?";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1,cpfNumber);
				pstmt.setString(2,passionFirst.getPassionId());
				pstmt.setString(3,passionFirst.getSubPassionId());
				pstmt.setString(4,passionFirst.getComments());
				pstmt.setString(5,passionFirstPsnMpId);
				pstmt.executeUpdate();
			}catch(Exception e){
				//system.out.println(e);
			}
	}


	@Override
	public boolean submitUserPassion(User user, Passion passion) {
		PreparedStatement pstmt =null;
		ResultSet set =null;
		Connection conn=null;
		boolean flag=false;
		String query1 = "INSERT INTO PSN_MYPASSION(PSN_MP_ID,CPF_NO,PASSION_CATEGORY,PASSION_SUB_CATEGORY,COMMENTS,CREATED_ON) VALUES('"+getMaxIdFromTable("PSN_MYPASSION","PSN_MP_ID")+"',?,?,?,?,CURRENT_TIMESTAMP)";
		try
		{
			conn=DatasourceConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query1);
			pstmt.setString(1, user.getCpfNo());
			pstmt.setString(2, passion.getPassionId());
			pstmt.setString(3, passion.getSubPassionId());
			pstmt.setString(4, passion.getComments());
			//system.out.println(query1);
			pstmt.executeUpdate();
			conn.commit();
			flag= true;
		}
		catch (Exception e) {
			try {
				conn.rollback();
				//log.info("Connection rollback in submitUserPassion method");
			} catch (SQLException sqlEx) {
				//log.info("exception in rollback::"+sqlEx);
				LOGGER.error("erro"+e);
			}
			//log.info(e+" Exception in saveUserPassion method in Dao :::::");
		}
		finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}


	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;
		Connection connection=null;
		String query = "select max(int(" + colName + ")) from " + tableName;
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
			LOGGER.error("erro"+e);
		} finally {
			// ConnMgmt.closeConnection(rs,stmt, connection);
		}

		return ++id;
	}
	
	@Override
	public List<SubPassion> getSubPassionName(String passionID) {
		PreparedStatement pstmt =null;
		ResultSet set =null;
		Connection conn=null;
		List<SubPassion> categoryList=new ArrayList<SubPassion>();
		try{
			String query="SELECT pms.PASSION_ID,pms.SUBPASSION_ID,pms.SUB_PASSION,pms.CREATED_BY FROM PSN_MS_SUBPASSION pms where pms.passion_id=? ORDER BY SUB_PASSION";
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,passionID);
			System.out.println(pstmt);
			set=pstmt.executeQuery();
			SubPassion subPassion=null;
			while(set.next()){
				subPassion=new SubPassion();
				subPassion.setPassionId(set.getString("PASSION_ID"));
				subPassion.setSubPassionId(set.getString("SUBPASSION_ID"));
				subPassion.setSubPassion(set.getString("SUB_PASSION"));
				subPassion.setCreatedBy(set.getString("CREATED_BY"));
				categoryList.add(subPassion);	
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);
		}	
		return categoryList;
	}

}
