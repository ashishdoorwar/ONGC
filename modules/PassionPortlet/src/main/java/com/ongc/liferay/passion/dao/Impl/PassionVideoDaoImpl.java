package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.PassionVideoDao;
import com.ongc.liferay.passion.model.PassionVideo;
import com.ongc.liferay.passion.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassionVideoDaoImpl implements PassionVideoDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet set;
	private Statement stmt;
	
	// Method for get selected passion video

	public List<PassionVideo> getPassionVideo(String cpfNo,String pId,String subId){
		List<PassionVideo> psVideo=new ArrayList<PassionVideo>();
		String query="SELECT pp.VIDEO_ID,pp.PSN_MP_ID,pp.VIDEO_NAME,pp.DESCRIPTION,pp.FILE_NAME,pp.ENDORSE_COUNT from PSN_VIDEO pp,PSN_MYPASSION pmp WHERE pp.PSN_MP_ID=pmp.PSN_MP_ID and pmp.CPF_NO =? and PMP.PASSION_CATEGORY=? and PMP.PASSION_SUB_CATEGORY=? order by pp.UPLOADED_ON desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, pId);
			pstmt.setString(3, subId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionVideo psnVideo=new PassionVideo();
				psnVideo.setVideoPSN_MPID(set.getString("PSN_MP_ID"));
				psnVideo.setVideoId(set.getString("VIDEO_ID"));
				psnVideo.setVideoName(set.getString("VIDEO_NAME"));
				psnVideo.setVideoDescription(set.getString("DESCRIPTION"));
				psnVideo.setVideoEndorsedCount(set.getString("ENDORSE_COUNT"));
				//psnVideo.setVideoTag(set.getString("TAGS"));  
				psnVideo.setVideoFileName(set.getString("FILE_NAME"));
				psVideo.add(psnVideo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psVideo;	
	}

	// Method for getPassionVideoComments
	public List<PassionVideo> getPassionVideoComments(String videoId){
		List<PassionVideo> psVideo=new ArrayList<PassionVideo>();
		int vId=0;
		if(videoId!=null)
			vId=Integer.parseInt(videoId);
		//String query="select PHOTO_ID,ACTION_BY,COMMENTS from PSN_PHOTO_CMNTS_ENDRSMNT where PHOTO_ID=? order by sno";
		String query="select pce.SNO,pce.VIDEO_ID,pce.ACTION_BY,OUM.EMP_NAME,pce.COMMENTS from PSN_VIDEO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.VIDEO_ID=? and PCE.ACTION_BY=OUM.CPF_NUMBER order by comments_on desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, vId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionVideo psnVideo=new PassionVideo();
				psnVideo.setCommentId(set.getInt("SNO"));
				psnVideo.setVideoId(set.getString("VIDEO_ID"));
				psnVideo.setVideoEndorsedByCpf(set.getString("ACTION_BY"));
				psnVideo.setVideoEndorsedBy(set.getString("EMP_NAME"));
				psnVideo.setVideoComments(set.getString("COMMENTS"));
				psVideo.add(psnVideo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psVideo;	
	}
	// End method getPassionPhotoComments

	// Update Endorse Count

	public boolean updateEndorseCount(String videoId,String cpf) {
		boolean flage=false;
		PreparedStatement pstmt2=null;
		String query="UPDATE PSN_VIDEO SET ENDORSE_COUNT =ENDORSE_COUNT+1 WHERE VIDEO_ID = ?";
		String query1="insert into PSN_VIDEO_CMNTS_ENDRSMNT(SNO,VIDEO_ID,ACTION_BY,ENDORSED_ON)values('"+getMaxIdFromTable("PSN_VIDEO_CMNTS_ENDRSMNT","SNO")+"',?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,videoId);
			pstmt.executeUpdate();
			pstmt2=conn.prepareStatement(query1);
			pstmt2.setString(1,videoId);
			pstmt2.setString(2,cpf);
			//	pstmt2.setString(3,comments);
			pstmt2.executeUpdate();
			conn.commit();
			flage=true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqlEx) {
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}

	// Method for get getEndorseCount
	public List<PassionVideo> getEndorseCount(String vId){
		int videoId=0;
		if(vId!=null) {
			videoId=Integer.parseInt(vId);
		}
		List<PassionVideo> videoEndrsCnt=new ArrayList<PassionVideo>();
		String query="SELECT distinct pp.ENDORSE_COUNT from PSN_VIDEO pp,PSN_MYPASSION pmp WHERE pp.VIDEO_ID=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionVideo endrsCnt=new PassionVideo();
				endrsCnt.setVideoEndorsedCount(set.getString("ENDORSE_COUNT"));
				videoEndrsCnt.add(endrsCnt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return videoEndrsCnt;	
	}

	// Method for check Endorse

	public boolean checkEndorse(String videoId,String cpf) {
		boolean flage=false;
		int vId=0;
		if(videoId!=null)
			vId=Integer.parseInt(videoId);
		String query="select VIDEO_ID,ACTION_BY,COMMENTS from PSN_VIDEO_CMNTS_ENDRSMNT where action_by=? and comments is null and video_id=?";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			pstmt.setInt(2,vId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionVideo psnVideo=new PassionVideo();
				psnVideo.setVideoId(set.getString("VIDEO_ID"));
				psnVideo.setVideoEndorsedBy(set.getString("ACTION_BY"));
				psnVideo.setVideoComments(set.getString("COMMENTS"));
				flage=true;
			}
		} catch (Exception e) {							
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}

	// Method for insert video comments

	public boolean insertVideoComments(String videoId,String cpf,String videoCmnts) {
		boolean flage=false;
		String query="insert into PSN_VIDEO_CMNTS_ENDRSMNT(SNO,VIDEO_ID,ACTION_BY,COMMENTS,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_VIDEO_CMNTS_ENDRSMNT","SNO")+"',?,?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,videoId);
			pstmt.setString(2,cpf);
			pstmt.setString(3,videoCmnts);
			pstmt.executeUpdate();
			flage=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}
	// End method insertVideoComments
	public ArrayList<User> getVideoEndorsedByUserName(String videoId){
		ArrayList<User> al=new ArrayList<User>();
		int vId=0;
		if(videoId!=null) {
			vId=Integer.parseInt(videoId);
		}
		String query="select oum.emp_name,oum.cpf_number from PSN_VIDEO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.VIDEO_ID=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, vId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				User user=new User();
				user.setEmpName(set.getString("EMP_NAME"));
				user.setCpfNo(set.getString("CPF_NUMBER"));
		        al.add(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return al;	
	}

	public PassionVideo getVideoDetail(String videoId) {
		PassionVideo video=new PassionVideo();
		String query="select * from psn_video where video_id="+videoId+"";
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Connection conn=DatasourceConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				video.setVideoName(rs.getString(3));
				video.setVideoFileName(rs.getString(4));
				video.setVideoDescription(rs.getString(5));
				video.setVideoEndorsedCount(rs.getString(6));
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}finally{
			DatasourceConnection.closeConnection(conn, stmt, rs);
		}
		
		return video;
	}
	
	public boolean deleteVideoComments(String cid) {
		boolean flage= false;
		
		String query1="delete from psn_video_cmnts_endrsmnt where sno='"+cid+"'";
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
