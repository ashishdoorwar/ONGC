package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.FeedbackCategoryDao;
import com.ongc.liferay.reports.model.FeedbackCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCategoryDaoImpl implements FeedbackCategoryDao{

	private static final Log LOGGER = LogFactoryUtil.getLog(FeedbackCategoryDaoImpl.class);
	
	@Override
	public boolean saveFeedbackCategory(FeedbackCategory category) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		boolean flage=false;
		String query="INSERT INTO RPTT_FEEDBACK_CATEGORY (FEEDBACK_ID,DESCRIPTION, CREATEDBY) VALUES(?,?,?)";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, category.getFeedbackId());
			pstmt.setString(2,category.getDescription());
			pstmt.setString(3, category.getCreatedBy());

			flage=0<pstmt.executeUpdate();
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}	

		return flage;
	}

	@Override
	public List<FeedbackCategory> getCategoryListByFeedbakId(int feedbackId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<FeedbackCategory> categoryList=new ArrayList<FeedbackCategory>();
		String query="SELECT * FROM RPTT_FEEDBACK_CATEGORY WHERE FEEDBACK_ID=? order by DESCRIPTION";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,1);
			set=pstmt.executeQuery();
			FeedbackCategory category=null;
			while(set.next()){
				category=new FeedbackCategory();
				if(set.getInt("CATEGORY_ID")!=60){
				category.setCategoryId(set.getInt("CATEGORY_ID"));
				category.setDescription(set.getString("DESCRIPTION"));
				category.setCreatedBy(set.getString("CREATEDBY"));
				category.setCreatedOn(set.getDate("CREATEDON"));
				categoryList.add(category);
				}
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set );
		}	

		return categoryList;
	}

	@Override
	public boolean roleCheck(String userCpf){
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		boolean result=false;
		String query="SELECT * FROM RPTT_FEEDBACK_ENABLERS_MASTER WHERE CPF_NO=? and role in ('L1','L2')";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userCpf);
			set=pstmt.executeQuery();
			if (set.next() ) {    
				result=true;
				} 
			
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set );
		}	

		return result;
	}
	

	@Override
	public String getHREnablersRole(String cpf) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String role="";
		String query="select a.role from rptt_feedback_enablers_master a where a.cpf_no=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			set=pstmt.executeQuery();
			while(set.next()){
				role=set.getString("role");
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}
		return role;
	}

	@Override
	public String getLocation(String userCpf) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String location="";
		String query="SELECT location FROM RPTT_FEEDBACK_ENABLERS_MASTER WHERE CPF_NO=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userCpf);
			set=pstmt.executeQuery();
			while(set.next()){ 
				location=set.getString("location");
				  
				} 
			
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set );
		}	

		return location;
	}

	@Override
	public String getsubLocation(String userCpf) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String sublocation="";
		String query="SELECT sub_location FROM RPTT_FEEDBACK_ENABLERS_MASTER WHERE CPF_NO=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userCpf);
			set=pstmt.executeQuery();
			while(set.next()){ 
				sublocation=set.getString("sub_location");
				  
				} 
			
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}	

		return sublocation;
	}

	@Override
	public ArrayList getCatId(String userCpf) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String catId="";
		ArrayList catids= new ArrayList();
		String query="SELECT hr_cat_id FROM RPTT_FEEDBACK_ENABLERS_MASTER WHERE CPF_NO=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userCpf);
			set=pstmt.executeQuery();
			while(set.next()){ 
				catId=set.getString("hr_cat_id");
				  catids.add(catId);
				} 
			
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}	

		return catids;
	}

}
