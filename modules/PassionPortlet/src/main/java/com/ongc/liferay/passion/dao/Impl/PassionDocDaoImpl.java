package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.PassionDocDao;
import com.ongc.liferay.passion.model.PassionDoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassionDocDaoImpl implements PassionDocDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private ResultSet set;
	private Statement stmt;
	
	
	// Method for get passion document file
	public  List<PassionDoc> getPassionDocumentFile(String cpfNo,String pId,String subId){
		List<PassionDoc> psDoc=new ArrayList<PassionDoc>();
		String query="SELECT pp.DOCS_ID,pp.PSN_MP_ID,pp.DOCS_NAME,pp.DESCRIPTION,pp.FILE_NAME,pp.ENDORSE_COUNT from PSN_DOCS pp,PSN_MYPASSION pmp WHERE pp.PSN_MP_ID=pmp.PSN_MP_ID and pmp.CPF_NO =? and PMP.PASSION_CATEGORY=? and PMP.PASSION_SUB_CATEGORY=? order by pp.UPLOADED_ON desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, pId);
			pstmt.setString(3, subId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionDoc psnDocs=new PassionDoc();
				psnDocs.setDocId(set.getString("DOCS_ID"));
				psnDocs.setPsnMPID(set.getString("PSN_MP_ID"));
				psnDocs.setDocName(set.getString("DOCS_NAME"));
				psnDocs.setDocDescription(set.getString("DESCRIPTION"));
				//psnPhoto.setPhotoTag(set.getString("TAGS")); 
				psnDocs.setDocFileName(set.getString("FILE_NAME"));
				psnDocs.setEndorsedCount(set.getString("ENDORSE_COUNT"));
				psDoc.add(psnDocs);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psDoc;	
	}

	public PassionDoc getDocDetail(String docId) {
		PassionDoc doc=new PassionDoc();
		String query="select * from psn_docs where docs_id="+docId+"";
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Connection conn=DatasourceConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				doc.setDocName(rs.getString(3));
				doc.setDocFileName(rs.getString(4));
				doc.setDocDescription(rs.getString(5));
				doc.setEndorsedCount(rs.getString(6));
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}finally{
			DatasourceConnection.closeConnection(conn, stmt, rs);
		}
		
		return doc;
	}
	
//  ----------  method for max id from table  ------------  //
	
	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;

		String query = "select max(int(" + colName + ")) from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatasourceConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DatasourceConnection.closeConnection(rs,stmt, connection);
		}

		return ++id;
	}
	
}
