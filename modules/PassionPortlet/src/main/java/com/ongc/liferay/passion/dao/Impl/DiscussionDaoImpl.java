package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.DiscussionDao;
import com.ongc.liferay.passion.model.DiscussionBoard;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.util.NameFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiscussionDaoImpl implements DiscussionDao{

	
	private Connection connection;
	private Statement stmt=null;
	private PreparedStatement pstmt;
	private ResultSet rs=null ;
	NameFormatter nf=new NameFormatter();
	
	public List<DiscussionBoard> fetchTopics(String q, String cpf){
		List<DiscussionBoard> tl=new ArrayList<DiscussionBoard>();
		String query;
		if(q.equals(""))
		query="select a.* from (select row_number() over(order by created_on desc) rownum, pdb.Topic_id, pdb.Topic_name, (select count(PARENT_REPLY_ID) from PSN_DISCUSSION_BOARD_REPLIES where Topic_id=pdb.topic_id) as No_of_replies, pdb.Visible_to, TO_CHAR(created_on, 'MON DD YYYY - HH:MI AM'), group_id, created_by, (select emp_name from ongc_user_master where cpf_number=pdb.created_by) as creator from PSN_DISCUSSION_BOARD pdb order by created_on desc) a where a.group_id is null or a.group_id='-1' or a.group_id in (select GROUP_ID from PSN_USER_GROUP where created_by='"+cpf+"' or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpf+"' and JOINING_STATUS='Y'))";
		else
		query="select a.* from (select row_number() over(order by created_on desc) rownum, pdb.Topic_id, pdb.Topic_name, (select count(PARENT_REPLY_ID) from PSN_DISCUSSION_BOARD_REPLIES where Topic_id=pdb.topic_id) as No_of_replies, pdb.Visible_to, TO_CHAR(created_on, 'MON DD YYYY - HH:MI AM'), group_id, created_by, (select emp_name from ongc_user_master where cpf_number=pdb.created_by) as creator from PSN_DISCUSSION_BOARD pdb where UPPER(pdb.tags) = upper('"+q+"') order by created_on desc) a where a.group_id is null or a.group_id='-1' or a.group_id in (select GROUP_ID from PSN_USER_GROUP where created_by='"+cpf+"' or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpf+"' and JOINING_STATUS='Y'))";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setRowNum(rs.getInt(1));
				dBoard.setTopicId(rs.getString(2));
				dBoard.setTopicName(rs.getString(3));
				dBoard.setRepliesCount(rs.getInt(4));
				dBoard.setVisibleTo(rs.getString(5));
				dBoard.setPublishedOn(rs.getString(6));
				dBoard.setGroupId(rs.getString(7));
				dBoard.setCreatedByCpf(rs.getString(8));
				dBoard.setCreatedByName(rs.getString(9));
				tl.add(dBoard);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return tl;
	}
	
	public List fetchTrendingTopics(String cpf){
		List tl=new ArrayList();
		String query;
		
		query="select a.* from (select row_number() over(order by (select count(distinct PARENT_REPLY_ID) from PSN_DISCUSSION_BOARD_REPLIES where Topic_id=pdb.topic_id) desc) rownum, pdb.Topic_id, pdb.Topic_name, (select count(distinct PARENT_REPLY_ID) from PSN_DISCUSSION_BOARD_REPLIES where Topic_id=pdb.topic_id) as no_of_replies, pdb.Visible_to, TO_CHAR(created_on, 'MON DD YYYY - HH:MI AM'),  group_id,(select emp_name from ongc_user_master where cpf_number=pdb.created_by) from PSN_DISCUSSION_BOARD pdb order by no_of_replies desc) a where (a.group_id is null or a.group_id='-1' or a.group_id in (select GROUP_ID from PSN_USER_GROUP where created_by='"+cpf+"' or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpf+"'))) FETCH FIRST 5 rows only";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			System.out.println(query);
			
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setRowNum(rs.getInt(1));
				dBoard.setTopicId(rs.getString(2));
				dBoard.setTopicName(rs.getString(3));
				dBoard.setRepliesCount(rs.getInt(4));
				dBoard.setVisibleTo(rs.getString(5));
				dBoard.setPublishedOn(rs.getString(6));
				dBoard.setCreatedByName(rs.getString(8));
				tl.add(dBoard);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return tl;
	}

	public boolean insertPostNew(User user, DiscussionBoard dBoard) {
		
		boolean flage = false;
		
		String query="insert into psn_discussion_board (SNO, TOPIC_ID, TOPIC_NAME, CREATED_BY, CREATED_ON, visible_TO, TAGS, TOPIC_DESC, GROUP_ID) VALUES " +
				"(?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
	
		try{
			
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setInt(1, getMaxIdFromTable("psn_discussion_board", "sno"));
			pstmt.setInt(2, getMaxIdFromTable("psn_discussion_board", "topic_id"));
			pstmt.setString(3, dBoard.getTopicName());
			pstmt.setString(4, user.getCpfNo());
			pstmt.setString(5, dBoard.getVisibleTo());
			pstmt.setString(6, dBoard.getTags());
			pstmt.setString(7, dBoard.getTopicDesc());
			pstmt.setString(8, dBoard.getGroupId());
			
			//system.out.println(query);
			
			flage = 0 < pstmt.executeUpdate();
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(pstmt, connection);
		}
	return flage;
	}

	public String fetchAllTags(){
		String str="";
		
		String query="SELECT SUBSTR (SYS_CONNECT_BY_PATH (tags , '\\'), 2) csv FROM (SELECT tags , ROW_NUMBER () OVER (ORDER BY tags ) rn, COUNT (*) OVER () cnt FROM psn_discussion_board) WHERE rn = cnt START WITH rn = 1 CONNECT BY rn = PRIOR rn + 1";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				str=rs.getString(1);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return str;
	}

	public List fetchGroups(String cpfNo) {
		List gList=new ArrayList();
		
		String query="select group_id, group_name from psn_user_group where group_id in (select GROUP_ID from PSN_USER_GROUP where created_by='"+cpfNo+"' or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpfNo+"' and JOINING_STATUS='Y' )) order by group_name";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setGroupId(rs.getString(1));
				dBoard.setGroupName(rs.getString(2));
				gList.add(dBoard);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return gList;
	}
	
	public List<DiscussionBoard> topicDetail(String tid){
		List<DiscussionBoard> tDetail=new ArrayList<DiscussionBoard>();
		
		String query="select topic_id, topic_name, created_by, TO_CHAR(created_on, 'DD Mon YYYY - HH:MIAM') as doc, topic_desc, (select emp_name from ongc_user_master where cpf_number=pdb.created_by) as name from psn_discussion_board pdb where topic_id='"+tid+"'";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setTopicId(rs.getString(1));
				dBoard.setTopicName(rs.getString(2));
				dBoard.setCreatedByCpf(rs.getString(3));
				dBoard.setPublishedOn(rs.getString(4));
				dBoard.setTopicDesc(rs.getString(5));
				String name=rs.getString(6);
				dBoard.setCreatedByName(nf.InitialCaps(name));
				tDetail.add(dBoard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println(e);
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return tDetail;
	}

	public boolean postComment(User user, DiscussionBoard dBoard, String tid) {
		
		boolean flage=false;		
				
		String query="insert into psn_discussion_board_replies (sno, topic_id, parent_reply_id, replied_by, replied_on, reply) values (?, ?, ?, ?, current_timestamp, ?)";
	
		try{
			
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setInt(1, getMaxIdFromTable("psn_discussion_board_replies", "sno"));
			pstmt.setString(2, tid);
			pstmt.setInt(3, getMaxIdFromTable("psn_discussion_board_replies", "parent_reply_id"));
			pstmt.setString(4, user.getCpfNo());
			pstmt.setString(5, dBoard.getComment());
			
			//system.out.println(query);
			System.out.println(pstmt);
			flage = 0 < pstmt.executeUpdate();
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(pstmt, connection);
		}
		return flage;
	}
	
	public boolean postReply(User user, DiscussionBoard dBoard, String tid, String pid) {
		
		boolean flage=false;		
				
		String query="insert into psn_discussion_board_replies (sno, topic_id, parent_reply_id, child_reply_id, replied_by, replied_on, reply) values (?, ?, ?, ?, ?, current_timestamp, ?)";
	
		try{
			
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setInt(1, getMaxIdFromTable("psn_discussion_board_replies", "sno"));
			pstmt.setString(2, tid);
			pstmt.setString(3, pid);
			pstmt.setInt(4, getMaxIdFromTable("psn_discussion_board_replies", "child_reply_id"));
			pstmt.setString(5, user.getCpfNo());
			pstmt.setString(6, dBoard.getReply());
			
			//system.out.println(query);
			
			flage = 0 < pstmt.executeUpdate();
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(pstmt, connection);
		}
		return flage;
	}

	
	
	public List<DiscussionBoard> fetchComments(String tid){
		
		List<DiscussionBoard> commentList=new ArrayList<DiscussionBoard>();
		List<DiscussionBoard> commentReplyList=new ArrayList<DiscussionBoard>();
		
		String query="select topic_id, parent_reply_id, replied_by, (select emp_name from ongc_user_master where cpf_number=pdbr.replied_by), TO_CHAR(replied_on, 'DD Mon YYYY - HH:MIAM'), reply from psn_discussion_board_replies pdbr where topic_id='"+tid+"' and child_reply_id is null order by replied_on desc";
		
		try{
			int i=0;
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setTopicId(rs.getString(1));
				dBoard.setCommentId(rs.getString(2));
				dBoard.setCommentBy(rs.getString(3));
				dBoard.setCommentByName(nf.InitialCaps(rs.getString(4)));
				dBoard.setPublishedOn(rs.getString(5));
				dBoard.setComment(rs.getString(6));
			//	dBoard.setReplyList(fetchReplies(tid, rs.getString(2)));
				commentList.add(dBoard);
			}
			Iterator itr=commentList.iterator();
			while(itr.hasNext()){
				DiscussionBoard dBoard=(DiscussionBoard)itr.next();
				dBoard.setReplyList(fetchReplies(tid, dBoard.getCommentId()));
				commentReplyList.add(dBoard);
			}
		}
		catch (Exception e) {
			//system.out.println(e);
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection,pstmt, rs);
		}
		
		return commentReplyList;
	}
	
	private List fetchReplies(String tid, String cid){
		
		List repliesList=new ArrayList();
		
		String query="select topic_id, parent_reply_id, child_reply_id, replied_by, (select emp_name from ongc_user_master where cpf_number=pdbr.replied_by), TO_CHAR(replied_on, 'DD Mon YYYY - HH:MIAM'), reply from psn_discussion_board_replies pdbr where topic_id='"+tid+"' and parent_reply_id='"+cid+"' and child_reply_id is not null order by replied_on desc";
		
		try{
			int i=0;
			connection=DatasourceConnection.getConnection();
			pstmt=connection.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DiscussionBoard dBoard=new DiscussionBoard();
				dBoard.setTopicId(rs.getString(1));
				dBoard.setCommentId(rs.getString(2));
				dBoard.setReplyId(rs.getString(3));
				dBoard.setReplyBy(rs.getString(4));
				dBoard.setReplyByName(nf.InitialCaps(rs.getString(5)));
				dBoard.setPublishedOn(rs.getString(6));
				dBoard.setReply(rs.getString(7));
				repliesList.add(dBoard);
			}
			pstmt.close();
		}
		catch (Exception e) {
			//system.out.println(e);
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection,pstmt,rs);
		}
		
		return repliesList;
	}
	
	public static void main(String[] args) {
		
		
	}

	public boolean deleteComment(String cid) {
		boolean flage= false;
		
		String query1="delete from psn_discussion_board_replies where parent_reply_id='"+cid+"'";
		Connection con=null;
		PreparedStatement pstmt1=null;
		try{
			con=DatasourceConnection.getConnection();
			con.setAutoCommit(false);
			pstmt1=con.prepareStatement(query1);
			
			pstmt1.executeUpdate();
			
			con.commit();
			flage = true;
		}
		catch(Exception ex){
			try {
				con.rollback();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			ex.printStackTrace();

		}
		finally{
			DatasourceConnection.closeConnection(pstmt1, con);
			
		}
		
		return flage;
	}
	
	public boolean deleteReply(String rid) {
		boolean flage= false;
		
		String query1="delete from psn_discussion_board_replies where child_reply_id='"+rid+"'";
		Connection con=null;
		PreparedStatement pstmt1=null;
		try{
			con=DatasourceConnection.getConnection();
			con.setAutoCommit(false);
			pstmt1=con.prepareStatement(query1);
			
			pstmt1.executeUpdate();
			
			con.commit();
			flage = true;
		}
		catch(Exception ex){
			try {
				con.rollback();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			ex.printStackTrace();

		}
		finally{
			DatasourceConnection.closeConnection(pstmt1, con);
			
		}
		
		return flage;
	}

	private int getMaxIdFromTable(String tableName,String colName){
		int id=0;
		
		String query="select max(("+colName+")) from "+tableName;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next())
			{  
				id=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return ++id;
	}
	
}
