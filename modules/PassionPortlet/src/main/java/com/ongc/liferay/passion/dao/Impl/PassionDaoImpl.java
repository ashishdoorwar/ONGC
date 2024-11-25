package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.PassionDao;
import com.ongc.liferay.passion.model.Passion;
import com.ongc.liferay.passion.model.SubPassion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PassionDaoImpl implements PassionDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet set;
	private HttpServletRequest servletRequest;
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
		// Method for get selected user's passion

		
		public List<Passion> getUserPassion(String cpf_no) {
			conn = DatasourceConnection.getConnection();
			List<Passion>  categoryList1 = new ArrayList<Passion> ();
			String query = "select distinct pmp.PSN_MP_ID,psmp.passion_name,psmp.passion_id,PMSP.SUB_PASSION,PMSP.SUBPASSION_ID,pmp.comments from psn_mypassion pmp,psn_ms_passion psmp,psn_ms_subpassion pmsp where PMP.passion_category=PSMP.PASSION_ID and PMSP.SUBPASSION_ID=PMP.PASSION_SUB_CATEGORY and pmp.cpf_no=? order by PSN_MP_ID";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cpf_no);
				set = pstmt.executeQuery();
				Passion passion = null;
				while (set.next()) {
					passion = new Passion();
					passion.setPsnMPID(set.getString("PSN_MP_ID"));
					passion.setPassionName(set.getString("PASSION_NAME"));
					passion.setPassionId(set.getString("PASSION_ID"));
					passion.setSubPassion(set.getString("SUB_PASSION"));
					passion.setSubPassionId(set.getString("SUBPASSION_ID"));
					passion.setComments(set.getString("COMMENTS"));
					categoryList1.add(passion);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}
			return categoryList1;
		}
		

		// Method for get passion name from master table
		public String getUserPassionMPID(String pID,String subID,String cpf) {
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
				e.printStackTrace();
			}
			finally {
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}
			return psnMPID;
		}
		
		
		public List<Passion> getPassionName(){
			List<Passion> categoryList=new ArrayList<Passion>();
			String query="SELECT * FROM PSN_MS_PASSION";
			try{
				conn=DatasourceConnection.getConnection();
				pstmt=conn.prepareStatement(query);
				set=pstmt.executeQuery();
				Passion passion=null;
				while(set.next()){
					passion=new Passion();
					passion.setPassionId(set.getString("PASSION_ID"));
					passion.setPassionName(set.getString("PASSION_NAME"));
					passion.setPassionDescription(set.getString("PASSION_DESCRIPTION"));
					passion.setPublished(set.getString("PUBLISHED"));
					passion.setCreatedBy(set.getString("CREATED_BY"));
					categoryList.add(passion);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}	
			return categoryList;
		}

		

		// Method for get subpassion name from master table
		public List<SubPassion> getSubPassionName(){
			List<SubPassion> categoryList=new ArrayList<SubPassion>();
			String query="SELECT pms.PASSION_ID,pms.SUBPASSION_ID,pms.SUB_PASSION,pms.CREATED_BY,(select PASSION_NAME from PSN_MS_PASSION where pms.PASSION_ID=PASSION_ID) PASSION_NAME FROM PSN_MS_SUBPASSION pms";
			try{
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
				e.printStackTrace();
			}
			finally{
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}	
			return categoryList;
		}
		
		public List<SubPassion> getSubPassionName(String psnid){
			List<SubPassion> categoryList=new ArrayList<SubPassion>();
			String query="SELECT pms.passion_id, pms.subpassion_id, pms.sub_passion  FROM psn_ms_subpassion pms where passion_id=?";
			try{
				conn=DatasourceConnection.getConnection();
				
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, psnid);
				set=pstmt.executeQuery();
				SubPassion subPassion=null;
				while(set.next()){
					subPassion=new SubPassion();
					subPassion.setPassionId(set.getString("PASSION_ID"));
					subPassion.setSubPassionId(set.getString("SUBPASSION_ID"));
					subPassion.setSubPassion(set.getString("SUB_PASSION"));
					categoryList.add(subPassion);	
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}	
			return categoryList;
		}		
}
