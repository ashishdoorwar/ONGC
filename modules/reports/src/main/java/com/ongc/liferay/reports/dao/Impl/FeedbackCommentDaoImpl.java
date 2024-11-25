package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.FeedbackCommentDao;
import com.ongc.liferay.reports.model.FeedbackComment;
import com.ongc.liferay.reports.model.FeedbackReason;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.util.ReportConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCommentDaoImpl implements FeedbackCommentDao{

	private static final Log LOGGER = LogFactoryUtil.getLog(FeedbackCommentDaoImpl.class);
	@Override
	public boolean saveFeedbackComment(FeedbackComment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flage=false;
		String query="INSERT INTO RPTT_FEEDBACK_COMMENT (COMMENT_ID, POST_ID, CPF_NO, COMMENT_TEXT,COMMENT_TEXT1,COMMENT_TEXT2,COMMENT_TEXT3,COMMENT_TEXT4, AUTHRESPONSE,POST_DATE) VALUES(?,?,?,?,?,?,?,?,?,NOW())";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			int id=getMaxIdFromTable("RPTT_FEEDBACK_COMMENT", "COMMENT_ID", conn);
			pstmt.setInt(1,id);
			pstmt.setInt(2,comment.getPostId());
			pstmt.setString(3,comment.getUser().getCpfNo());
			String kk=comment.getCommentText();
			String kk1=kk.substring(0,kk.length()/5);
			String kk2=kk.substring(kk.length()/5,2*(kk.length()/5));
			String kk3=kk.substring(2*(kk.length()/5),3*(kk.length()/5));
			String kk4=kk.substring(3*(kk.length()/5),4*(kk.length()/5));
			String kk5=kk.substring(4*(kk.length()/5));
			
			
			pstmt.setString(4,kk1);
			pstmt.setString(5,kk2);
			pstmt.setString(6,kk3);
			pstmt.setString(7,kk4);
			pstmt.setString(8,kk5);
			if (comment.isAuthResp()) 
				pstmt.setString(9,"Y");
			else 
				pstmt.setString(9,"N");
			
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
	public boolean archiveFeedbackComment(String status, int postId) {
		Connection conn = null;
		boolean flage=false;
		String query="UPDATE RPTT_FEEDBACK_COMMENT SET STATUS = ? WHERE COMMENT_ID = ?";
		PreparedStatement pstmt1 = null;
		try{
			conn=DatasourceConnection.getConnection(); 
			pstmt1=conn.prepareStatement(query);
			pstmt1.setString(1,status);
			pstmt1.setInt(2,postId);
			flage=0 < pstmt1.executeUpdate();
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt1);
		}
		return flage;
	}

	@Override
	public boolean deleteFeedbackComment(int postId) {
		Connection conn = null;
		boolean flage=false;
		String query="DELETE FROM RPTT_FEEDBACK_COMMENT WHERE COMMENT_ID = ?";
		PreparedStatement pstmt1 = null;
		try{
			conn=DatasourceConnection.getConnection(); 
			pstmt1=conn.prepareStatement(query);
			
			pstmt1.setInt(1,postId);
			flage=0 < pstmt1.executeUpdate();
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt1);
		}
		return flage;
	}

	@Override
	public List<FeedbackComment> getCommentListByPostId(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<FeedbackComment> commentList=new ArrayList<FeedbackComment>();
		String query="SELECT C.COMMENT_ID,C.STATUS,C.COMMENT_TEXT,C.COMMENT_TEXT1,C.COMMENT_TEXT2,C.COMMENT_TEXT3,C.COMMENT_TEXT4,C.POST_ID, C.POST_DATE, U.CPF_NUMBER,U.PLACE_POSTING , U.EMP_NAME, U.DESIGNATION,C.AUTHRESPONSE FROM RPTT_FEEDBACK_COMMENT C,ONGC_USER_MASTER U WHERE C.CPF_NO=U.CPF_NUMBER AND c.status = ? AND C.POST_ID=? order by C.POST_DATE";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,ReportConstant.COMMENT_STATUS_ACTIVE);
			pstmt.setInt(2,postId);
			set=pstmt.executeQuery();
			FeedbackComment comment=null;
			while(set.next()){
				comment= new FeedbackComment();
				comment.setUser(new User());
				comment.setCommentId(set.getInt("COMMENT_ID"));
				
				String comm = set.getString("COMMENT_TEXT")+set.getString("COMMENT_TEXT1")+set.getString("COMMENT_TEXT2")+set.getString("COMMENT_TEXT3")+set.getString("COMMENT_TEXT4");
				if(comm != null){
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");	
				}
				comment.setCommentText(comm);
				
				//comment.setCommentText(set.getString("COMMENT_TEXT"));
				
				comment.setPostId(set.getInt("POST_ID"));
				comment.setPostDate(set.getTimestamp("POST_DATE"));
				comment.getUser().setCpfNo(set.getString("CPF_NUMBER"));
				comment.getUser().setEmpName(set.getString("EMP_NAME"));
				comment.getUser().setDesignation(set.getString("DESIGNATION"));
				comment.getUser().setLocalAddress(set.getString("PLACE_POSTING"));
				comment.setStatus(set.getString("STATUS"));
				if (set.getString("AUTHRESPONSE").equals("Y")) 
					comment.setAuthResp(true);
				 else 
					comment.setAuthResp(false);
				
				
				commentList.add(comment);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}
		return commentList;
	}

	@Override
	public List<FeedbackComment> getAllCommentListByPostId(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<FeedbackComment> commentList=new ArrayList<FeedbackComment>();

		String query="SELECT C.COMMENT_ID,C.STATUS,C.COMMENT_TEXT,C.COMMENT_TEXT1,C.COMMENT_TEXT2,C.COMMENT_TEXT3,C.COMMENT_TEXT4,C.POST_ID, C.POST_DATE, U.CPF_NUMBER,U.PLACE_POSTING , U.EMP_NAME, U.DESIGNATION,C.AUTHRESPONSE FROM RPTT_FEEDBACK_COMMENT C,ONGC_USER_MASTER U WHERE C.CPF_NO=U.CPF_NUMBER AND C.COMMENT_ID!= ? AND C.POST_ID=? order by C.POST_DATE";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,-1);
			pstmt.setInt(2,postId);
			set=pstmt.executeQuery();
			FeedbackComment comment=null;
			while(set.next()){
				comment= new FeedbackComment();
				comment.setUser(new User());
				comment.setCommentId(set.getInt("COMMENT_ID"));
				String comm = set.getString("COMMENT_TEXT")+set.getString("COMMENT_TEXT1")+set.getString("COMMENT_TEXT2")+set.getString("COMMENT_TEXT3")+set.getString("COMMENT_TEXT4");
				
				comm = comm.replaceAll("&gt;", ">");
				comm = comm.replaceAll("&lt;", "<");
				comment.setCommentText(comm);
				
				comment.setPostId(set.getInt("POST_ID"));
				comment.setPostDate(set.getTimestamp("POST_DATE"));
				comment.getUser().setCpfNo(set.getString("CPF_NUMBER"));
				comment.getUser().setEmpName(set.getString("EMP_NAME"));
				comment.getUser().setDesignation(set.getString("DESIGNATION"));
				comment.getUser().setLocalAddress(set.getString("PLACE_POSTING"));
				comment.setStatus(set.getString("STATUS"));
				if (set.getString("AUTHRESPONSE").equals("Y")) 
					comment.setAuthResp(true);
				 else 
					comment.setAuthResp(false);
				
				commentList.add(comment);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}
		return commentList;
	}

	@Override
	public List<FeedbackComment> getCommentListByStatus() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<FeedbackComment> commentList=new ArrayList<FeedbackComment>();
		String query="SELECT * FROM RPTT_FEEDBACK_COMMENT Where STATUS !=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,ReportConstant.COMMENT_STATUS_ACTIVE);
			set=pstmt.executeQuery();
			FeedbackComment comment=null;
			while(set.next()){
				comment= new FeedbackComment();
				comment.setUser(new User());
				comment.setCommentId(set.getInt("COMMENT_ID"));
				String comm = set.getString("COMMENT_TEXT")+set.getString("COMMENT_TEXT1")+set.getString("COMMENT_TEXT2")+set.getString("COMMENT_TEXT3")+set.getString("COMMENT_TEXT4");
				comm = comm.replaceAll("&gt;", ">");
				comm = comm.replaceAll("&lt;", "<");
				comment.setCommentText(comm);
				//comment.setCommentText(set.getString("COMMENT_TEXT"));
				comment.setPostId(set.getInt("POST_ID"));
				comment.setPostDate(set.getTimestamp("POST_DATE"));
				comment.getUser().setCpfNo(set.getString("CPF_NUMBER"));
				comment.getUser().setEmpName(set.getString("EMP_NAME"));
				comment.getUser().setDesignation(set.getString("DESIGNATION"));
				commentList.add(comment);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}
		return commentList;
	}

	@Override
	public List<FeedbackReason> getReasonsListByPostId(int postId) {
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		List<FeedbackReason> reasonList=new ArrayList<FeedbackReason>();
		//String query="Select SNO, reason, action_by, STATUS, action_on,EMP_NAME, PLACE_POSTING, DESIGNATION, HR_CAT_ID from(SELECT distinct A.SNO, a.reason, a.action_by, A.STATUS, TO_CHAR(a.action_on, 'Month dd, yyyy HH:mm:ss') as action_on,B.EMP_NAME, B.PLACE_POSTING, B.DESIGNATION, A.HR_CAT_ID  FROM rptt_feedback_rep_status A,ongc_user_master B WHERE a.feedback_id=? AND a.action_by=b.cpf_number )ORDER BY action_on";
		String query="Select distinct A.SNO, a.reason, a.action_by, A.STATUS, a.action_on,B.EMP_NAME, B.PLACE_POSTING, B.DESIGNATION, A.HR_CAT_ID  FROM rptt_feedback_rep_status A,ongc_user_master B WHERE a.feedback_id=? AND a.action_by=b.cpf_number ORDER BY action_on";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,postId);
			set=pstmt.executeQuery();
			FeedbackReason reason=null;
			while(set.next()){
				reason= new FeedbackReason();
				reason.setUser(new User());
				reason.setReasonId(set.getInt("SNO"));
				reason.setReason(set.getString("REASON"));
				reason.setStatus(set.getString("STATUS"));
				reason.getUser().setCpfNo(set.getString("ACTION_BY"));
				reason.setReasonOn(set.getString("ACTION_ON"));
				reason.getUser().setEmpName(set.getString("EMP_NAME"));
				reason.getUser().setLocalAddress(set.getString("PLACE_POSTING"));
				reason.getUser().setDesignation(set.getString("DESIGNATION"));
				//reason.getUser().setEmpLevel(set.getString("ROLE"));
				reason.setHrCatId(set.getInt("HR_CAT_ID"));
				reasonList.add(reason);
			}
		}
		catch (Exception e) {
			LOGGER.error("erro"+e);
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt, set);
		}
		return reasonList;
	}

	@Override
	public int getMaxIdFromTable(String tableName, String colName, Connection conn) {
		int id = 0;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			//ConnMgmt.closeConnection(rs, stmt, connection);
		}
		return ++id;
	}
	
}
