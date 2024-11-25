package com.ongc.liferay.dao;

import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.util.PassioSendMail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BirthDayWishDao {


	
	 public boolean sendBirthdayWishes(String fromCpf, String toCpf,String frmName,String toName, String message)
	 {
		 Connection conn = null;
		 PreparedStatement pstmt=null;
		 ResultSet set=null;
		 boolean flag = false;
		
		 try{
			 
			   // System.out.println("sendBirthdayWishes :: "+fromCpf+" to "+toCpf+" frmName "+frmName+" to name  "+toName+" message "+message );
			    PassioSendMail sendData = new PassioSendMail();
	    	    String fromEmail= fromCpf+"@ongc.co.in";
		    	//String toEmail= toCpf+"@ongc.co.in";
	    	    String toEmail= "mithun.7790@gmail.com";
	    	    
	    	   // System.out.println("sendBirthdayWishes fromEmail :"+fromEmail+"  toEmail  "+toEmail);
		    	String sub="Happpy Birthday";
		    	//String mailmsg="Dear "+toName+",<br/> <br/> You have received the following Thank You note from "+frmName+" :<br/> <br/> <em>"+message+"</em><br/><br/>You can view this Thank You note by logging in to reports.ongc.co.in and clicking the \"Thank Your Colleague\" and seeing the table there. <br/><br/><br/> Best wishes from ONGCReports Team. ";
	 	        flag=sendData.sendEmail1(sub, message, fromEmail ,toEmail);
	 	        
			 
		 }catch (Exception ex) {
		    	System.out.println("Birthday Wishes sendBirthdayWishes Exception "+ex+":: ");
		    } finally {
		    	DatasourceConnection.closeConnection(conn,pstmt, set);
		   }
		 return flag;
	 }
	 
	public boolean saveBirthdayWishes(String fromCpf, String toCpf,String frmName,String toName)
	  {
		 Connection conn = null;
		 PreparedStatement pstmt=null;
		 ResultSet set=null;
		String message="Dear <strong>"+toName+"</strong>,<br/> <br/> I wish you a very happy birthday.<br/><br/> It has been a great experience working with you.<br/><br/> I wish you have many more years to your life and much more life in those years.<br/><br/>Regards,<br/><strong>"+frmName+"</strong><br/>(Through ONGCReports) ";
		String query = "insert into birthday_wishes(id,fromcpf,tocpf,message,post_date,status) values(?,?,?,?,current_timestamp,'Y')";
	    boolean flage = false;
	    int id =0;
	    try {
	      id=getMaxIdFromTable("birthday_wishes","id");	
	      conn = DatasourceConnection.getConnection();
	      pstmt = conn.prepareStatement(query);	     
	      pstmt.setInt(1, id);
	      pstmt.setString(2, fromCpf);
	      pstmt.setString(3, toCpf);
	      pstmt.setString(4, message);
	      
	      flage = pstmt.executeUpdate() > 0;
	      if(flage)
	      {
	    	 flage= sendBirthdayWishes(fromCpf,toCpf,frmName,toName,message);
	      }
	      	    	
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	DatasourceConnection.closeConnection(conn,pstmt, set);
	    }
	    return flage;
	  }
	
	private int getMaxIdFromTable(String tableName,String colName){
		
		Connection conn = null;
		 PreparedStatement pstmt=null;
		 ResultSet set=null;
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
			e.printStackTrace();
		}finally {
	    	DatasourceConnection.closeConnection(conn,pstmt, set);
	    } 
		
		return ++id;
	}	
}
