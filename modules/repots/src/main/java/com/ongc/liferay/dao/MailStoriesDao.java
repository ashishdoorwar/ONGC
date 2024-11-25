package com.ongc.liferay.dao;

import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.util.PassioSendMail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MailStoriesDao {

	 
	 
	 public boolean saveMailArticleRecord(String fromCpf, String fromName, String toName,String toEmail,String message,String url,String title)
	  {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet set = null;
		String absoluteurl="https://reports.ongc.co.in/"+url;
		String mssg="Dear <strong>"+toName+"</strong>,<br/> <br/> <em> "+message+"</em><br/> <br/> <strong>Click below link to view shared article: </strong><br/> <a href=\""+absoluteurl+"\" title=\""+absoluteurl+"\">"+title+" </a>   <br/><br/><br/><strong>You have to log in to reports.ongc.co.in with your credentials.</strong><br/><br/> ("+fromName+")";
		String query = "insert into MAIL_ARTICLE_RECORD(id,fromcpf,toname,toemail,message,pgurl,pgtitle,postdate,status) values(?,?,?,?,?,?,?,current_timestamp,'Y')";
	    boolean flage = false;
	    int id =0;
	    try {
	      id=getMaxIdFromTable("MAIL_ARTICLE_RECORD","id");	
	      conn = DatasourceConnection.getConnection();
	      pstmt = conn.prepareStatement(query);	     
	      pstmt.setInt(1, id);
	      pstmt.setString(2, fromCpf);
	      pstmt.setString(3, toName);
	      pstmt.setString(4, toEmail);
	      pstmt.setString(5, message);
	      pstmt.setString(6, url);
	      pstmt.setString(7, title);
	      flage = pstmt.executeUpdate() > 0;
	      if(flage)
	      {
	    	 flage= sendMailArticle(fromCpf,toEmail,mssg);
	      }
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	DatasourceConnection.closeConnection(conn,pstmt, set);
	    }
	    return flage;
	  }
	 
	 
	 public boolean sendMailArticle(String fromCpf,String toEmail,String message)
	 {Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet set = null;
		 boolean flag = false;
		 try{
			    PassioSendMail sendData = new PassioSendMail();
	    	    String fromEmail= fromCpf+"@ongc.co.in";
	    	   // toEmail= "mithun.7790@gmail.com";   
	    	    //toEmail= "mukherjee_d@ongc.co.in";   
		    	String sub="Interesting Article on Ongc Reports";
		    	//String mailmsg="Dear "+toName+",<br/> <br/> You have received the following Thank You note from "+frmName+" :<br/> <br/> <em>"+message+"</em><br/><br/>You can view this Thank You note by logging in to reports.ongc.co.in and clicking the \"Thank Your Colleague\" and seeing the table there. <br/><br/><br/> Best wishes from ONGCReports Team. ";
	 	        flag=sendData.sendEmail1(sub, message, fromEmail ,toEmail);
	 	       // System.out.println("sendMailArticle  flag "+flag);
			 
		 }catch (Exception ex) {
		    	System.out.println("sendMailArticle Exception "+ex+":: ");
		    } finally {
		    	DatasourceConnection.closeConnection(conn,pstmt, set);
		   }
		 return flag;
	 }	 
	
	private int getMaxIdFromTable(String tableName,String colName){
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet set = null;
		int id=0;
		String query="select max("+colName+") from "+tableName;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			set = pstmt.executeQuery();			
			while(set.next())
			{  
				id=set.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("Mail Stories -> exeption   getMaxIdFromTable>>"+e);
		}finally {
	    	DatasourceConnection.closeConnection(conn,pstmt, set);
	    } 
		
		return ++id;
	}	


}
