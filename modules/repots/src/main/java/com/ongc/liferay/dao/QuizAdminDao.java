package com.ongc.liferay.dao;

import com.ongc.liferay.connection.DatasourceConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class QuizAdminDao {
	
	
	public int insertQues(String qdesc,List<String> lst) {
		Connection conn =null;
		Statement statement = null; 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int i = 0;
		int qId=0;
		int count=0;
		String qn_no="";
		String opval="a";
		String insertQuery = "INSERT INTO QUES_MASTER ( QID,QN_NO,QDESC)" +
				" VALUES (?,?,?)";
		
		try {
			qId= getQid();
			qId=qId+1;
			qn_no="question"+qId;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1,qId);
			pstmt.setString(2,qn_no);
			pstmt.setString(3,qdesc);
		   	i = pstmt.executeUpdate();
		   	if(i>0)
		   	{
		   		Iterator itr=lst.iterator();
				while(itr.hasNext())
				{
					
					
					if(count==0)
					{
						opval="a";
					}
					else if(count==1)
					{
						opval="b";
					}
					else if(count==2)
					{
						opval="c";
					}
					else if(count==3)
					{
						opval="d";
					}
				    insertOption(qId,itr.next().toString(),opval);
				    count++;
				}
		   	}
		   	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return i;		
	}
	
	public int getQid()
	{
		Connection conn =null;
		Statement statement = null; 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int qid=0;
		String query = "Select max(qid) as qId from ques_master";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			
		   	rs = pstmt.executeQuery();
		   	if(rs.next())
		   	{
		   	  qid= rs.getInt("qId");
		   	}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		
		return qid;
	}
	
	public int getOptId()
	{
		Connection conn =null;
		Statement statement = null; 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int optId=0;
		String query = "Select max(optid) as oId from ques_option";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			
		   	rs = pstmt.executeQuery();
		   	if(rs.next())
		   	{
		   		optId= rs.getInt("oId");
		   	}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		
		return optId;
	}
	
	
	public int insertOption(int qid,String option,String opval) {
		Connection conn =null;
		Statement statement = null; 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int i = 0;
		int optId= 0;
		
		String query = "insert into ques_option(optid,qid,qn_option,op) VALUES (?,?,?,?)";
		
		try {
			optId=getOptId();
			optId=optId+1;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,optId);
			pstmt.setInt(2,qid);
			pstmt.setString(3,option);
			pstmt.setString(4,opval);
		   	i = pstmt.executeUpdate();
		   	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return i;		
	}
	
	public String getOpValues(int qid)
	{
		Connection conn =null;
		Statement statement = null; 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String op="";
		String query = "Select op  from ques_option where qid='"+qid+"' order by optid desc";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			
		   	rs = pstmt.executeQuery();
		   	if(rs.next())
		   	{
		   	  op= rs.getString("op");
		   	}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		
		return op;
	}
	
	
}
