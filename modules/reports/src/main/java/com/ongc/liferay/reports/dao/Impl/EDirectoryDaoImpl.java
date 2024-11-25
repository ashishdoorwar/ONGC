package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.report.row.extractor.UserMasterRowExtractor;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.EDirectoryDao;
import com.ongc.liferay.reports.model.DirAssistanceBean;
import com.ongc.liferay.reports.model.OrganizationBean;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.model.UtilityBean;
import com.ongc.liferay.reports.model.WorkplaceBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EDirectoryDaoImpl implements EDirectoryDao {


	private static final Log LOGGER = LogFactoryUtil.getLog(EDirectoryDaoImpl.class);
	
	public List<OrganizationBean> searchOrganization(OrganizationBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<OrganizationBean> orgList=new ArrayList<OrganizationBean>();
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(createOrgQuery(org));
			set=pstmt.executeQuery();
			while(set.next()){
				org=new OrganizationBean();
				org.setSubsiDaryId(set.getString("SUBSIDARYID"));
				org.setName(set.getString("NAME"));
				org.setDesignation(set.getString("DESIGNATION"));
				org.setOfficeNo(set.getString("OFFICENO"));
				org.setResidenceNo(set.getString("RESIDENCENO"));
				org.setLocation(set.getString("LOCATION"));
				org.setFax(set.getString("FAX"));
				org.setMobile(set.getString("MOBILE"));
				org.setEmail(set.getString("EMAIL"));
				org.setAddress(set.getString("ADDRESS"));
				org.setDepartment(set.getString("DEPARTMENT"));
				org.setT1(set.getString("T2"));
				org.setT2(set.getString("T3"));
				org.setSquence(set.getString("SEQUENCE"));
				orgList.add(org);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return orgList;
	}
	
	private String createOrgQuery(OrganizationBean org){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String query="SELECT * FROM ONGCSUBSIDARY";
		boolean f=false;
		if(org!=null){
			if(org.getSubsiDaryId()!=null && !org.getSubsiDaryId().trim().isEmpty()){
				query=query+" WHERE SUBSIDARYID ='"+ org.getSubsiDaryId().trim()+"'";
				f=true;
			}
			if(org.getLocation()!=null && !org.getLocation().trim().isEmpty()){
				if(f)
					query=query+" AND LOCATION='"+org.getLocation().trim()+"'";
				else
					query=query+" WHERE LOCATION='"+org.getLocation().trim()+"'";	
			}
		}
		return query;
	}

	@Override
	public List<WorkplaceBean> searchWorkplace(WorkplaceBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<WorkplaceBean> orgList=new ArrayList<WorkplaceBean>();
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(createWorkPlaceQuery(org));
			set=pstmt.executeQuery();
			while(set.next()){
				org=new WorkplaceBean();

				org.setName(set.getString("NAME"));
				org.setWorkplace(set.getString("WORKPLACE"));
				org.setOfficeNo(set.getString("OFFICENO"));
				org.setResidenceNo(set.getString("RESIDENCENO"));
				org.setLocation(set.getString("LOCATION"));
				org.setFax(set.getString("FAX"));
				org.setMobile(set.getString("MOBILE"));
				org.setSubCategory(set.getString("SUBCATEGORY"));
				org.setAddress(set.getString("ADDRESS"));
				org.setDepartment(set.getString("DEPARTMENT"));
				org.setT1(set.getString("T1"));
				org.setT2(set.getString("T2"));
				org.setOtherCities(set.getString("OTHERCITIES"));
				orgList.add(org);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}

		return orgList;
	}	
	
	

	private String createWorkPlaceQuery(WorkplaceBean org){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String query="SELECT * FROM EDIR_WORKPLACE";
		boolean f=false;
		if(org!=null){
			if(org.getWorkplace()!=null && !org.getWorkplace().trim().isEmpty()){
				query=query+" WHERE WORKPLACE ='"+ org.getWorkplace().trim()+"'";
				f=true;
			}
			if(org.getSubCategory()!=null && !org.getSubCategory().isEmpty()){
				if(f)
					query=query+" AND SUBCATEGORY='"+org.getSubCategory().trim()+"'";
				else
					query=query+" WHERE SUBCATEGORY='"+org.getSubCategory().trim()+"'";
				f=true;
			}
			if(org.getLocation()!=null && !org.getLocation().isEmpty()){
				if(f)
					query=query+" AND UPPER(LOCATION) like UPPER('%"+org.getLocation().trim()+"%')";
				else
					query=query+" WHERE UPPER(LOCATION) like UPPER('%"+org.getLocation().trim()+"%')";
				f=true;
			}
			if(org.getDepartment()!=null && !org.getDepartment().isEmpty()){
				if(f)
					query=query+" AND UPPER(DEPARTMENT) like UPPER('%"+org.getDepartment().trim()+"%')";
				else
					query=query+" WHERE UPPER(DEPARTMENT) like UPPER('%"+org.getDepartment().trim()+"%')";	
			}
		}
		return query;
	}

	@Override
	public List<UtilityBean> searchUtility(UtilityBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<UtilityBean> orgList=new ArrayList<UtilityBean>();
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(createUtilityQuery(org));
			set=pstmt.executeQuery();
			while(set.next()){
				org=new UtilityBean();

				org.setName(set.getString("NAME"));
				org.setUtility(set.getString("UTILITY"));
				org.setSubUtility(set.getString("SUB_UTILITY"));
				org.setOfficeNo(set.getString("OFFICENO"));
				org.setResidenceNo(set.getString("RESIDENCENO"));
				org.setLocation(set.getString("LOCATION"));
				org.setFax(set.getString("FAX"));
				org.setMobile(set.getString("MOBILE"));
				org.setOtherInfo(set.getString("OTHER_INFO"));
				org.setAddress(set.getString("ADDRESS"));
				org.setDepartment(set.getString("DEPARTMENT"));
				org.setOtherCities(set.getString("OTHERCITIES"));
				orgList.add(org);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}

		return orgList;
	}

	private String createUtilityQuery(UtilityBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String query="SELECT * FROM EDIR_UTILITY";
		boolean f=false;
		if(org!=null){
			if(org.getUtility()!=null && !org.getUtility().trim().isEmpty()){
				query=query+" WHERE UTILITY ='"+ org.getUtility().trim()+"'";
				f=true;
			}
			if(org.getSubUtility()!=null && !org.getSubUtility().trim().isEmpty()){
				if(f)
					query=query+" AND SUB_UTILITY='"+org.getSubUtility().trim()+"'";
				else
					query=query+" WHERE SUB_UTILITY='"+org.getSubUtility().trim()+"'";
				f=true;
			}
			if(org.getLocation()!=null && !org.getLocation().trim().isEmpty()){
				if(f)
					query=query+" AND UPPER(LOCATION) like UPPER('%"+org.getLocation().trim()+"%')";
				else
					query=query+" WHERE UPPER(LOCATION) like UPPER('%"+org.getLocation().trim()+"%')";
				f=true;
			}
			if(org.getDepartment()!=null && !org.getDepartment().trim().isEmpty()){
				if(f)
					query=query+" AND UPPER(DEPARTMENT) like UPPER('%"+org.getDepartment().trim()+"%')";
				else
					query=query+" WHERE UPPER(DEPARTMENT) like UPPER('%"+org.getDepartment().trim()+"%')";
			}
		}
		return query;
	}

	@Override
	public List<DirAssistanceBean> searchAssisDir(DirAssistanceBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<DirAssistanceBean> orgList=new ArrayList<DirAssistanceBean>();
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(createAssisDirQuery(org));
			set=pstmt.executeQuery();
			while(set.next()){
				org=new DirAssistanceBean();

				org.setName(set.getString("NAME"));
				org.setCpfNo(set.getString("CPFNO"));
				org.setCategory(set.getString("CATEGORY"));
				org.setOfficeNo(set.getString("OFFICENO"));
				org.setResidenceNo(set.getString("RESIDENCENO"));
				org.setLocation(set.getString("LOCATION"));
				org.setMobile(set.getString("MOBILE"));
				org.setInfocomUnit(set.getString("INFOCOM_UNIT"));
				org.setEmail(set.getString("EMAIL"));
				org.setDesignation(set.getString("DESIGNATION"));

				orgList.add(org);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}

		return orgList;
	}

	private String createAssisDirQuery(DirAssistanceBean org) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		String query="SELECT * FROM FPR_SPR_INFO ";
		boolean f=false;
		if(org!=null){

			if(org.getLocation()!=null && org.getLocation().trim().length()>2){

				query=query+" WHERE LOCATION='"+org.getLocation().trim()+"'";
				f=true;
			}
			if(org.getInfocomUnit()!=null && org.getInfocomUnit().trim().length()>2){
				if(f)
					query=query+" AND INFOCOM_UNIT='"+org.getInfocomUnit().trim()+"'";
				else
					query=query+" WHERE INFOCOM_UNIT='"+org.getInfocomUnit().trim()+"'";	
			}
			if(org.getName()!=null && org.getName().trim().length()>2){
				if(f)
					query=query+" AND NAME='"+org.getName().trim()+"'";
				else
					query=query+" WHERE NAME='"+org.getName().trim()+"'";	
			}
			if(org.getCpfNo()!=null && org.getCpfNo().trim().length()>2){
				if(f)
					query=query+" AND CPFNO='"+org.getCpfNo().trim()+"'";
				else
					query=query+" WHERE CPFNO='"+org.getCpfNo().trim()+"'";	
			} 
		}
		return query;
	}

	@Override
	public List<User> searchLocation(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<User> orgList=new ArrayList<User>();
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(createLocationQuery(user));
			set=pstmt.executeQuery();

			orgList= UserMasterRowExtractor.extractRow(set);
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}

		return orgList;
	}

	private String createLocationQuery(User org) {
		
		String query="SELECT * FROM ONGC_USER_MASTER ";
		boolean f=false;
	//	log.info("-- " + org.getCpfNo());
		if(org!=null){

			if(org.getPlacePostingTrsfr()!=null && !org.getPlacePostingTrsfr().trim().isEmpty()){

				query=query+" WHERE upper(PLACE_POSTING)=upper('"+org.getPlacePostingTrsfr().trim()+"')";
				f=true;
			}

			if(org.getEmpName()!=null && !org.getEmpName().trim().isEmpty()){
				if(f)
					query=query+" AND UPPER(EMP_NAME) like UPPER('%"+org.getEmpName().trim()+"%')";
				else {
					query=query+" WHERE UPPER(EMP_NAME) like UPPER('%"+org.getEmpName().trim()+"%')";
					f=true;
					}
			}
			if(org.getCpfNo()!=null && !org.getCpfNo().trim().isEmpty()){
				if(f)
					query=query+" AND UPPER(CPF_NUMBER) like UPPER('%"+org.getCpfNo().trim()+"%')";
				else {
					f=true;
					query=query+" WHERE UPPER(CPF_NUMBER) like UPPER('%"+org.getCpfNo().trim()+"%')";
					}
			} 
			if(org.getDepartment()!=null && !org.getDepartment().trim().isEmpty()){
				if(f)
					query=query+" AND UPPER(DEPT) like UPPER('%"+org.getDepartment().trim()+"%')";
				else {
					f=true;
					query=query+" WHERE UPPER(DEPT) like UPPER('%"+org.getDepartment().trim()+"%')";
					}
			} 
			
			if (f) {
				//query = query + " AND DOS is null";
			} else {
				query = query + " where DOS is null";
			}
		}
		return query;
	}	
}
