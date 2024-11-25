package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.PassionAudioDao;
import com.ongc.liferay.passion.model.PassionAudio;
import com.ongc.liferay.passion.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassionAudioDaoImpl implements PassionAudioDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet set;
	private Statement stmt;

	
	// Method for get selected passion audio

	public List<PassionAudio> getPassionAudio(String cpfNo,String pId,String subId){
		//system.out.println(cpfNo+"......cpfNo  "+pId+"..........pId  "+subId+"...........subId");
		List<PassionAudio> psAudio=new ArrayList<PassionAudio>();
		String query="SELECT pp.AUDIO_ID,pp.PSN_MP_ID,pp.AUDIO_NAME,pp.DESCRIPTION,pp.FILE_NAME,pp.ENDORSE_COUNT from PSN_AUDIO pp,PSN_MYPASSION pmp WHERE pp.PSN_MP_ID=pmp.PSN_MP_ID and pmp.CPF_NO =? and PMP.PASSION_CATEGORY=? and PMP.PASSION_SUB_CATEGORY=? order by pp.UPLOADED_ON desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, pId);
			pstmt.setString(3, subId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionAudio psnAudio=new PassionAudio();
				psnAudio.setAudioPSN_MPID(set.getString("PSN_MP_ID"));
				psnAudio.setAudioId(set.getString("AUDIO_ID"));
				psnAudio.setAudioName(set.getString("AUDIO_NAME"));
				psnAudio.setAudioDescription(set.getString("DESCRIPTION"));
				psnAudio.setAudioEndorsedCount(set.getString("ENDORSE_COUNT"));
				psnAudio.setAudioFileName(set.getString("FILE_NAME"));
				psAudio.add(psnAudio);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);
		} 
		return psAudio;	
	}
	// End method getPassionAudio

	// Method for getPassionAudioComments
	public List<PassionAudio> getPassionAudioComments(String audioId){
		int aId=0;
		if(audioId!=null)
			aId=Integer.parseInt(audioId);
		List<PassionAudio> psAudio=new ArrayList<PassionAudio>();
		//String query="select PHOTO_ID,ACTION_BY,COMMENTS from PSN_PHOTO_CMNTS_ENDRSMNT where PHOTO_ID=? order by sno";
		String query="select pce.SNO,pce.AUDIO_ID,pce.ACTION_BY,OUM.EMP_NAME,pce.COMMENTS from PSN_AUDIO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.AUDIO_ID=? and PCE.ACTION_BY=OUM.CPF_NUMBER order by comments_on desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, aId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionAudio psnAudio=new PassionAudio();
				psnAudio.setCommentId(set.getInt("SNO"));
				psnAudio.setAudioId(set.getString("AUDIO_ID"));
				psnAudio.setAudioEndorsedByCpf(set.getString("ACTION_BY"));
				psnAudio.setAudioEndorsedBy(set.getString("EMP_NAME"));
				psnAudio.setAudioComments(set.getString("COMMENTS"));
				psAudio.add(psnAudio);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psAudio;	
	}
	// End method getPassionPhotoComments

	// Update Endorse Count

	public boolean updateEndorseCount(String audioId,String cpf) {
		boolean flage=false;
		PreparedStatement pstmt2=null;
		String query="UPDATE PSN_AUDIO SET ENDORSE_COUNT =ENDORSE_COUNT+1 WHERE AUDIO_ID = ?";
		String query1="insert into PSN_AUDIO_CMNTS_ENDRSMNT(SNO,AUDIO_ID,ACTION_BY,ENDORSED_ON)values('"+getMaxIdFromTable("PSN_AUDIO_CMNTS_ENDRSMNT","SNO")+"',?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,audioId);
			pstmt.executeUpdate();
			pstmt2=conn.prepareStatement(query1);
			pstmt2.setString(1,audioId);
			pstmt2.setString(2,cpf);
		//	pstmt2.setString(3,comments);
			pstmt2.executeUpdate();
			conn.commit();
			flage=true;
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}

		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}

	// Method for get getEndorseCount
	public List<PassionAudio> getEndorseCount(String aId){
		int audioId=0;
		if(aId!=null) {
			audioId=Integer.parseInt(aId);
		}
		List<PassionAudio> audioEndrsCnt=new ArrayList<PassionAudio>();
		String query="SELECT distinct pp.ENDORSE_COUNT from PSN_AUDIO pp,PSN_MYPASSION pmp WHERE pp.AUDIO_ID=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, audioId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionAudio endrsCnt=new PassionAudio();
				endrsCnt.setAudioEndorsedCount(set.getString("ENDORSE_COUNT"));
				audioEndrsCnt.add(endrsCnt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return audioEndrsCnt;	
	}
	// End method getEndorseCount


	public boolean checkEndorse(String audioId,String cpf) {
		boolean flage=false;
		int aId=0;
		if(audioId!=null) {
			aId=Integer.parseInt(audioId);
		}
		String query="select AUDIO_ID,ACTION_BY,COMMENTS from PSN_AUDIO_CMNTS_ENDRSMNT where action_by=? and comments is null and audio_id=?";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			pstmt.setInt(2,aId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionAudio psnAudio=new PassionAudio();
				psnAudio.setAudioId(set.getString("AUDIO_ID"));
				psnAudio.setAudioEndorsedBy(set.getString("ACTION_BY"));
				psnAudio.setAudioComments(set.getString("COMMENTS"));
				flage=true;
			}
		} catch (Exception e) {							
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}

	
	public boolean insertAudioComments(String audioId,String cpf,String audioCmnts) {
		boolean flage=false;
		String query="insert into PSN_AUDIO_CMNTS_ENDRSMNT(SNO,AUDIO_ID,ACTION_BY,COMMENTS,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_AUDIO_CMNTS_ENDRSMNT","SNO")+"',?,?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,audioId);
			pstmt.setString(2,cpf);
			pstmt.setString(3,audioCmnts);
			pstmt.executeUpdate();
			flage=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}
	public ArrayList<User> getAudioEndorsedByUserName(String audioId){
		ArrayList<User> al=new ArrayList<User>();
		int aId=0;
		if(audioId!=null) {
			aId=Integer.parseInt(audioId);
		}
		String query="select oum.emp_name,oum.cpf_number from PSN_AUDIO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.AUDIO_ID=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, aId);
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

	public PassionAudio getAudioDetail(String audioId) {
		PassionAudio audio=new PassionAudio();
		String query="select * from psn_audio where audio_id="+audioId+"";
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Connection conn=DatasourceConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				audio.setAudioName(rs.getString(3));
				audio.setAudioFileName(rs.getString(4));
				audio.setAudioDescription(rs.getString(5));
				audio.setAudioEndorsedCount(rs.getString(6));
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}finally{
			DatasourceConnection.closeConnection(conn, stmt, rs);
		}
		
		return audio;
	}
	
	public boolean deleteAudioComments(String cid) {
		boolean flage= false;
		
		String query1="delete from psn_audio_cmnts_endrsmnt where sno='"+cid+"'";
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
