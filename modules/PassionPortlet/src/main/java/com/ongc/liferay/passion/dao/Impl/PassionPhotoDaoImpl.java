package com.ongc.liferay.passion.dao.Impl;


import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.PassionPhotoDao;
import com.ongc.liferay.passion.model.Passion;
import com.ongc.liferay.passion.model.PassionPhoto;
import com.ongc.liferay.passion.model.User;

import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PassionPhotoDaoImpl implements PassionPhotoDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private ResultSet set;
	private Statement stmt;
	
	
	// Method for get selected passion photo
	public  List<PassionPhoto> getPassionPhoto(String cpfNo,String pId,String subId){
		List<PassionPhoto> psPhoto=new ArrayList<PassionPhoto>();
		String query="SELECT pp.PHOTO_ID,pp.PSN_MP_ID,pp.PHOTO_NAME,pp.DESCRIPTION,pp.FILE_NAME,pp.ENDORSE_COUNT from PSN_PHOTO pp,PSN_MYPASSION pmp WHERE pp.PSN_MP_ID=pmp.PSN_MP_ID and pmp.CPF_NO =? and PMP.PASSION_CATEGORY=? and PMP.PASSION_SUB_CATEGORY=? order by pp.UPLOADED_ON desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, pId);
			pstmt.setString(3, subId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setPhotoId(set.getString("PHOTO_ID"));
				psnPhoto.setPsnMPID(set.getString("PSN_MP_ID"));
				psnPhoto.setPhotoName(set.getString("PHOTO_NAME"));
				psnPhoto.setPhotoDescription(set.getString("DESCRIPTION"));
				//psnPhoto.setPhotoTag(set.getString("TAGS")); 
				psnPhoto.setFileName(set.getString("FILE_NAME"));
				psnPhoto.setEndorsedCount(set.getString("ENDORSE_COUNT"));
				psPhoto.add(psnPhoto);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psPhoto;	
	}

	public  boolean removePassion(String Id,String type,String filename,String cpfNo){
		boolean flage=false;
		String query = null;
		String query1= null;
		//system.out.println(cpfNo+".........cpfNo");
		try{
			conn=DatasourceConnection.getConnection();
			String file = "passion.properties";
			Properties prop = new Properties();
			prop.load(super.getClass().getClassLoader().getResourceAsStream(file));
			String filePath = prop.getProperty("passionFilePath");
			//system.out.println(filePath+"..............filePath");
			String passionPath = filePath + cpfNo;
			if(type.equals("photo")){
				//system.out.println(type);
				 query="delete from psn_photo where PHOTO_ID=?";
				 query1="delete from PSN_PHOTO_CMNTS_ENDRSMNT where PHOTO_ID=?";
				 String passionImagePath=passionPath	+ "\\photos\\";
				 String passionSmallImagePath=passionPath	+ "\\photos\\small\\";
				 String passionMediumImagePath=passionPath	+ "\\photos\\medium\\";
				 //system.out.println("image store path::::"+passionImagePath+"\\"+filename);
				 File deletefile = new File(passionImagePath+"\\"+filename);
				 File deleteSmallfile = new File(passionSmallImagePath+"\\"+filename);
				 File deleteMediumfile = new File(passionMediumImagePath+"\\"+filename);
				 deletefile.delete();
				 deleteSmallfile.delete();
				 deleteMediumfile.delete();
				 flage=true;
			}
				else if(type.equals("audio")){
					 query="delete from PSN_AUDIO where AUDIO_ID=?";
					 query1="delete from PSN_AUDIO_CMNTS_ENDRSMNT where AUDIO_ID=?";
					 String passionAudioPath=passionPath	+ "\\audios\\";
					 //system.out.println("audio store path::::"+passionAudioPath);
					 File deletefile = new File(passionAudioPath+"\\"+filename);
					 deletefile.delete();
					 flage=true;
					 }
				else if(type.equals("video")){
					 query="delete from PSN_VIDEO where VIDEO_ID=?";
					 query="delete from PSN_VIDEO_CMNTS_ENDRSMNT where VIDEO_ID=?";
					 String passionVideoPath=passionPath	+ "\\videos\\";	 
					 //system.out.println("video store path::::"+passionVideoPath);
					 File deletefile = new File(passionVideoPath+"\\"+filename);
					 deletefile.delete();
					 flage=true;
				}
			pstmt=conn.prepareStatement(query);
			pstmt1=conn.prepareStatement(query1);
			pstmt.setString(1, Id);
			pstmt1.setString(1, Id);
            pstmt1.executeQuery();
            pstmt.executeQuery();
            conn.commit();
			
		}
		
		catch(Exception ex){
			try {
				conn.rollback();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			ex.printStackTrace();

		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);
			DatasourceConnection.closeConnection(conn,pstmt1,set);
		} 
		//system.out.println(flage+"............return flage"); 
		return flage;
	}

	// Method for getPassionPhotoComments
	public List<PassionPhoto> getPassionPhotoComments(String photoId){
		int pId=0;
		if(photoId!=null)
			pId=Integer.parseInt(photoId);
		List<PassionPhoto> psPhoto=new ArrayList<PassionPhoto>();
		String query="select pce.SNO,pce.PHOTO_ID,pce.Action_by,OUM.EMP_NAME,pce.COMMENTS from PSN_PHOTO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.PHOTO_ID=? and PCE.ACTION_BY=OUM.CPF_NUMBER order by comments_on desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setCommentId(set.getInt("SNO"));
				psnPhoto.setPhotoId(set.getString("PHOTO_ID"));
				psnPhoto.setEndorsedByCpf(set.getString("ACTION_BY"));
				psnPhoto.setEndorsedBy(set.getString("EMP_NAME"));
				psnPhoto.setPhotoComments(set.getString("COMMENTS"));
				psPhoto.add(psnPhoto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psPhoto;	
	}

	// Update Endorse Count

	public boolean updateEndorseCount(String photoId,String cpf) {
		boolean flage=false;
		PreparedStatement pstmt2=null;
		int pId=0;
		if(photoId!=null && photoId!="") {
			pId=Integer.parseInt(photoId);
					
					
		}
		String query="UPDATE PSN_PHOTO SET ENDORSE_COUNT =ENDORSE_COUNT+1 WHERE PHOTO_ID = ?";
		String query1="insert into PSN_PHOTO_CMNTS_ENDRSMNT(SNO,PHOTO_ID,ACTION_BY,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_PHOTO_CMNTS_ENDRSMNT","SNO")+"',?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,pId);
			pstmt.executeUpdate();
			pstmt2=conn.prepareStatement(query1);
			pstmt2.setInt(1,pId);
			pstmt2.setString(2,cpf);
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
	public List<PassionPhoto> getEndorseCount(String pId){
		
		int photoId=0;
		if(pId!=null)
			photoId=Integer.parseInt(pId);
		List<PassionPhoto> photoEndrsCnt=new ArrayList<PassionPhoto>();
		String query="SELECT distinct pp.ENDORSE_COUNT from PSN_PHOTO pp,PSN_MYPASSION pmp WHERE pp.PHOTO_ID=?";
		try{
			System.out.println(pId);
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, photoId);

			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto endrsCnt=new PassionPhoto();
				endrsCnt.setEndorsedCount(set.getString("ENDORSE_COUNT"));
				photoEndrsCnt.add(endrsCnt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return photoEndrsCnt;	
	}


	public boolean checkEndorse(String photoId,String cpf) {
		boolean flage=false;
		
		int pId=0;
		if(photoId!=null)
			pId=Integer.parseInt(photoId);
		String query="select PHOTO_ID,ACTION_BY,COMMENTS from PSN_PHOTO_CMNTS_ENDRSMNT where action_by=? and comments is null and photo_id=?";
		try {
			System.out.println(cpf+"cpf");
			System.out.println(photoId+"photoId");
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			pstmt.setInt(2,pId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setPhotoId(set.getString("PHOTO_ID"));
				psnPhoto.setEndorsedBy(set.getString("ACTION_BY"));
				psnPhoto.setPhotoComments(set.getString("COMMENTS"));
				flage=true;
			}


		} catch (Exception e) {							
			e.printStackTrace();

		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}



	public boolean insertPhotoComments(String photoId,String cpf,String photoCmnts) {
		boolean flage=false;
		int pid=0;
		if(photoId!=null)
			pid=Integer.parseInt(photoId);
		String query="insert into PSN_PHOTO_CMNTS_ENDRSMNT(SNO,PHOTO_ID,ACTION_BY,COMMENTS,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_PHOTO_CMNTS_ENDRSMNT","SNO")+"',?,?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,pid);
			pstmt.setString(2,cpf);
			pstmt.setString(3,photoCmnts);
			System.out.println("---------------->>>"+pstmt);
			pstmt.executeUpdate();
			flage=true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}


	
	public boolean insertPhotoCmntsEndrsmnt(List passion,String cpfNo) {
		boolean flage=false;
		Passion _psn=null;
		conn=DatasourceConnection.getConnection();
		for(int p=0;p<passion.size();p++)
		{
			_psn =(Passion) passion.get(p);
		PreparedStatement pstmt2=null;
		List<PassionPhoto> psnPhotoId=new ArrayList<PassionPhoto>();
		String psnPhotoCmntsQuery="select pce.PHOTO_ID from PSN_PHOTO psnPhoto,PSN_MYPASSION pmp where pmp.CPF_NO=? and psnPhoto.PSN_MP_ID=pmp.PSN_MP_ID and pmp.PASSION_CATEGORY=? and pmp.PASSION_SUB_CATEGORY=? order by psnPhoto.PSN_MP_ID";
	    try{
		pstmt=conn.prepareStatement(psnPhotoCmntsQuery);
			pstmt.setString(1, cpfNo);
			pstmt.setString(2, _psn.getPassionId());
			pstmt.setString(3, _psn.getSubPassionId());
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setPhotoId(set.getString("PHOTO_ID"));
				psnPhotoId.add(psnPhoto);
			}
	    }catch(Exception e){
	    	//system.out.println(e);
	    }
//		List<PassionPhoto> psnPhotoCmntsEndrs=(List<PassionPhoto>)request.getSession().getAttribute("psnPhotoCmnts");
		List<PassionPhoto> psnPhotoCmntsEndrs=new ArrayList<PassionPhoto>();
		String actionBy=null;
		String comments=null;
		String commentsOn=null;
		String endorseCount=null;
		String strPhotoId=null;
		for(int i=0;i<psnPhotoId.size();i++){
			strPhotoId=psnPhotoId.get(i).getPhotoId();
			
		for(int l=0;l<psnPhotoCmntsEndrs.size();l++)	
		{
			actionBy=psnPhotoCmntsEndrs.get(l).getEndorsedBy();
			comments=psnPhotoCmntsEndrs.get(l).getPhotoComments();
			commentsOn=psnPhotoCmntsEndrs.get(l).getCommentsOn();
			endorseCount=psnPhotoCmntsEndrs.get(l).getEndorsedCount();
		
		String query="UPDATE PSN_PHOTO SET ENDORSE_COUNT =? WHERE PHOTO_ID = ?";
		String query1="insert into PSN_PHOTO_CMNTS_ENDRSMNT(SNO,PHOTO_ID,ACTION_BY,COMMENTS,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_PHOTO_CMNTS_ENDRSMNT","SNO")+"',?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,endorseCount);
			pstmt.setString(2,strPhotoId);
			pstmt.executeUpdate();
			pstmt2=conn.prepareStatement(query1);
			pstmt2.setString(1,strPhotoId);
			pstmt2.setString(2,actionBy);
			pstmt2.setString(3,comments);
			pstmt2.setString(4,commentsOn);
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
		}}}
		return flage;
	}

	
	// Method for getEndorsedByPics
	public byte[] getEndorsedByPics(String photoId,String empCPF){
		//system.out.println(photoId+":::::::::photoId");
		//system.out.println(empCPF+":::::::::CPFEMP");
		
		byte fileData[] = null;
		String query="select OUM.PROFILE_PIC from PSN_PHOTO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.PHOTO_ID=? and pce.action_by=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, photoId);
			pstmt.setString(2, empCPF);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("PROFILE_PIC");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}

	// Method for getEndorsedByUserName
	public ArrayList<User> getEndorsedByUserName(String photoId){
		ArrayList<User> al=new ArrayList<User>();
		int pId=0;
		if(photoId!=null) {
			pId=Integer.parseInt(photoId);
		}
		String query="select oum.emp_name,oum.cpf_number from PSN_PHOTO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.PHOTO_ID=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, pId);
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

	public static void main(String[] args) {
		//system.out.println(pd.getPassionPhoto(a,b,c).get(0).getPhotoId());

	}
	public byte[] getEndorsedAudioByPics(String audioId,String empCPF){
		//system.out.println(audioId+":::::::::audioId");
		//system.out.println(empCPF+":::::::::CPFEMP");
		
		byte fileData[] = null;
		String query="select OUM.PROFILE_PIC from PSN_AUDIO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.AUDIO_ID=? and pce.action_by=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, audioId);
			pstmt.setString(2, empCPF);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("PROFILE_PIC");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}
	
	public byte[] getEndorsedPdfByPics(String pdfId,String empCPF){
		//system.out.println(pdfId+":::::::::pdfId");
		//system.out.println(empCPF+":::::::::CPFEMP");
		//ArrayList<byte[]> al=new ArrayList<byte[]>();
		byte fileData[] = null;
		String query="select OUM.PROFILE_PIC from PSN_DOCS_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.DOCS_ID=? and pce.action_by=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pdfId);
			pstmt.setString(2, empCPF);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("PROFILE_PIC");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
				//al.add(fileData);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}
	
	public byte[] getEndorsedVideoByPics(String videoId,String empCPF){
		//system.out.println(videoId+":::::::::videoId");
		//system.out.println(empCPF+":::::::::CPFEMP");
		
		byte fileData[] = null;
		String query="select OUM.PROFILE_PIC from PSN_VIDEO_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.VIDEO_ID=? and pce.action_by=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, videoId);
			pstmt.setString(2, empCPF);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("PROFILE_PIC");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}
	public List<PassionPhoto> getPassionPdfComments(String pdfId){
		List<PassionPhoto> psPhoto=new ArrayList<PassionPhoto>();
		String query="select pce.SNO,pce.DOCS_ID,pce.ACTION_BY,OUM.EMP_NAME,pce.COMMENTS from PSN_DOCS_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.DOCS_ID=? and PCE.ACTION_BY=OUM.CPF_NUMBER order by comments_on desc";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pdfId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setCommentId(set.getInt("SNO"));
				psnPhoto.setPhotoId(set.getString("DOCS_ID"));
				psnPhoto.setEndorsedByCpf(set.getString("ACTION_BY"));
				psnPhoto.setEndorsedBy(set.getString("EMP_NAME"));
				psnPhoto.setPhotoComments(set.getString("COMMENTS"));
				psPhoto.add(psnPhoto);
			}
			//system.out.println("ffffffffffffffff  psPhoto  "+psPhoto.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return psPhoto;	
	}
	public boolean updateEndorsePdfCount(String pdfId,String cpf) {
		boolean flage=false;
		PreparedStatement pstmt2=null;
		String query="UPDATE PSN_DOCS SET ENDORSE_COUNT =ENDORSE_COUNT+1 WHERE DOCS_ID = ?";
		String query1="insert into PSN_DOCS_CMNTS_ENDRSMNT(SNO,DOCS_ID,ACTION_BY,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_DOCS_CMNTS_ENDRSMNT","SNO")+"',?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,pdfId);
			pstmt.executeUpdate();
			pstmt2=conn.prepareStatement(query1);
			pstmt2.setString(1,pdfId);
			pstmt2.setString(2,cpf);
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
	public List<PassionPhoto> getEndorsePdfCount(String pId){
		List<PassionPhoto> photoEndrsCnt=new ArrayList<PassionPhoto>();
		String query="SELECT distinct pp.ENDORSE_COUNT from PSN_DOCS pp,PSN_MYPASSION pmp WHERE pp.DOCS_ID=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pId);

			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto endrsCnt=new PassionPhoto();
				endrsCnt.setEndorsedCount(set.getString("ENDORSE_COUNT"));
				photoEndrsCnt.add(endrsCnt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return photoEndrsCnt;	
	}
	
	public List<PassionPhoto> getEndorseCountPdf(String pId){
		List<PassionPhoto> photoEndrsCnt=new ArrayList<PassionPhoto>();
		String query="SELECT distinct pp.ENDORSE_COUNT from PSN_DOCS pp,PSN_MYPASSION pmp WHERE pp.DOCS_ID=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pId);

			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto endrsCnt=new PassionPhoto();
				endrsCnt.setEndorsedCount(set.getString("ENDORSE_COUNT"));
				photoEndrsCnt.add(endrsCnt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return photoEndrsCnt;	
	}
	
	public boolean checkEndorsePdf(String pdfId,String cpf) {
		boolean flage=false;

		String query="select DOCS_ID,ACTION_BY,COMMENTS from PSN_DOCS_CMNTS_ENDRSMNT where action_by=? and comments is null and DOCS_ID=?";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			pstmt.setString(2,pdfId);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				PassionPhoto psnPhoto=new PassionPhoto();
				psnPhoto.setPhotoId(set.getString("DOCS_ID"));
				psnPhoto.setEndorsedBy(set.getString("ACTION_BY"));
				psnPhoto.setPhotoComments(set.getString("COMMENTS"));
				flage=true;
			}
		} catch (Exception e) {							
			e.printStackTrace();

		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}
	public boolean insertPdfComments(String pdfId,String cpf,String pdfCmnts) {
		boolean flage=false;
		String query="insert into PSN_DOCS_CMNTS_ENDRSMNT(SNO,DOCS_ID,ACTION_BY,COMMENTS,COMMENTS_ON)values('"+getMaxIdFromTable("PSN_DOCS_CMNTS_ENDRSMNT","SNO")+"',?,?,?,CURRENT_TIMESTAMP)";
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,pdfId);
			pstmt.setString(2,cpf);
			pstmt.setString(3,pdfCmnts);
			pstmt.executeUpdate();
			flage=true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally{
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}
	public ArrayList<User> getPdfEndorsedByUserName(String photoId){
		ArrayList<User> al=new ArrayList<User>();
		String query="select oum.emp_name,oum.cpf_number from PSN_DOCS_CMNTS_ENDRSMNT pce,ONGC_USER_MASTER oum where pce.DOCS_ID=? and pce.comments is null and PCE.ACTION_BY=OUM.CPF_NUMBER order by pce.sno";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, photoId);
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
	
	public PassionPhoto getPhotoDetail(String photoId) {
		PassionPhoto photo=new PassionPhoto();
		String query="select * from psn_photo where photo_id="+photoId+"";
		ResultSet rs=null;
		Statement stmt=null;
		try {
			Connection conn=DatasourceConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				photo.setPhotoName(rs.getString(3));
				photo.setFileName(rs.getString(4));
				photo.setPhotoDescription(rs.getString(5));
				photo.setEndorsedCount(rs.getString(6));
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}finally{
			DatasourceConnection.closeConnection(conn, stmt, rs);
		}
		
		return photo;
	}
	public byte[] getUserProfile(String empCPF){
		//system.out.println(":::::::::photoId");
		//system.out.println(empCPF+":::::::::CPFEMP");
		byte fileData[] = null;
		String query="select OUM.PROFILE_PIC from ONGC_USER_MASTER OUM where OUM.CPF_NUMBER=?";
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, empCPF);
			ResultSet set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("PROFILE_PIC");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}
	
	public boolean deletePhotoComments(String cid) {
		boolean flage= false;
		
		String query1="delete from psn_photo_cmnts_endrsmnt where sno='"+cid+"'";
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
	
	public boolean deletePdfComments(String cid) {
		boolean flage= false;
		
		String query1="delete from psn_docs_cmnts_endrsmnt where sno='"+cid+"'";
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

		String query = "select max((" + colName + ")) from " + tableName;
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
