package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.AskExpertDao;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;
import com.ongc.liferay.reports.util.PassioSendMail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AskExpertDaoImpl implements AskExpertDao{

	private static final Log LOGGER = LogFactoryUtil.getLog(AskExpertDaoImpl.class);
	
	@Override
	public List<AskExpert> getAllQueryList(AskExpertSearch askExpertSearch) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List <AskExpert> alist= new ArrayList<AskExpert>();
		  try{
		    StringBuilder query= new StringBuilder();
			SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat stm1 = new SimpleDateFormat("yyyy-MM-dd");
		    Date d1=null,d2=null;
		    query.append("Select a.QUERY_ID,a.DOMAIN_ID,a.CPF_NO,a.MESSAGE,a.NO_OF_REPLIES,a.PRIORITY,a.POSTED_ON,a.MODIFIED_ON,a.ISACTIVE,a.EXPERTRESPONSE,coalesce(a.LAST_COMMENTED_ON,a.posted_on) as LAST_COMMENTED_ON,a.LAST_COMMENTED_BY,(Select initcap(c.emp_name) from ongc_user_master c where a.LAST_COMMENTED_BY=c.cpf_number )as LAST_COMMENTED_NAME,initcap(b.EMP_NAME) as NAME,initcap(c.DOMAIN_NAME) as DOMAIN_NAME from RPTT_ASK_EXPERT a ,ONGC_USER_MASTER b,RPTT_DOMAIN_MASTER c where a.cpf_no=b.cpf_number and a.domain_id=c.domain_id  and a.isactive='Y' ");
		    if(askExpertSearch!=null && askExpertSearch.getKeyword()!=null && !"".equalsIgnoreCase(askExpertSearch.getKeyword()))
		    {
		    	query.append(" and lower(a.message) like ('%"+askExpertSearch.getKeyword().toLowerCase() +"%')");
		    }
		    if(askExpertSearch!=null && askExpertSearch.getQueryid()!=null && !"".equalsIgnoreCase(askExpertSearch.getQueryid()))
		    {
		    	query.append(" and a.query_id ='"+askExpertSearch.getQueryid()+"'");
		    }
		    if(askExpertSearch!=null && askExpertSearch.getDomainId()!=null && !"".equalsIgnoreCase(askExpertSearch.getDomainId()))
		    {
		    	query.append(" and a.domain_id ='"+askExpertSearch.getDomainId()+"'");
		    }
		    if(askExpertSearch!=null &&  askExpertSearch.getStartDate()!=null && !"".equalsIgnoreCase(askExpertSearch.getStartDate()) )
			{
				  String startDate="";
				
					d1 = stm.parse(askExpertSearch.getStartDate());
					startDate=stm1.format(d1);
				
				   query.append("   AND DATE(a.posted_on)  >= DATE('"+startDate+"')");
			}
		    if(askExpertSearch!=null && askExpertSearch.getEndDate()!=null && !"".equalsIgnoreCase(askExpertSearch.getEndDate()) )
			{
				  String endDate="";
					d2 = stm.parse(askExpertSearch.getEndDate());
					endDate=stm1.format(d2);
				   query.append("   AND DATE(a.posted_on)  <= DATE('"+endDate+"')");
			}
		    query.append(" order by LAST_COMMENTED_ON desc");
		    conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query.toString());
			set=pstmt.executeQuery();
			while(set.next())
			{
				AskExpert askexp= new AskExpert();
				askexp.setQueryid(set.getInt("QUERY_ID"));
				askexp.setDomainId(set.getInt("DOMAIN_ID"));
				askexp.setCpfno(set.getString("CPF_NO"));
				String comm=set.getString("MESSAGE");
				if (comm != null) {
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");
				}
				
				askexp.setMessage(comm);
				askexp.setNoofreplies(set.getInt("NO_OF_REPLIES"));
				askexp.setPriority(set.getString("PRIORITY"));
				askexp.setPosted_on(set.getTimestamp("POSTED_ON"));
				askexp.setModified_on(set.getTimestamp("MODIFIED_ON"));
				askexp.setIsActive(set.getString("ISACTIVE"));
				askexp.setExpertResponse(set.getString("EXPERTRESPONSE"));
				askexp.setLastcommented_on(set.getTimestamp("LAST_COMMENTED_ON"));
				askexp.setLastcommented_by(set.getString("LAST_COMMENTED_BY"));
				askexp.setUsername(set.getString("NAME"));
				askexp.setDomainName(set.getString("DOMAIN_NAME"));
				askexp.setLastcommented_name(set.getString("LAST_COMMENTED_NAME"));
				
				alist.add(askexp);

			}
			  
		  }catch (Exception e) {
				LOGGER.error("erro"+e);
		  }
		  finally{
				DatasourceConnection.closeConnection(conn,pstmt,set);
			}	
		 return alist; 
	}

	@Override
	public List<Domain> getDomainList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		List<Domain> domainList=new ArrayList<Domain>();
		try{
			conn=DatasourceConnection.getConnection();
			String query="SELECT DOMAIN_ID, DOMAIN_NAME FROM RPTT_DOMAIN_MASTER WHERE ISACTIVE='Y'";
			stmt=conn.createStatement();
			set=stmt.executeQuery(query);
			while(set.next()){
				Domain domain=new Domain();
				domain.setDomainId(set.getInt("DOMAIN_ID"));
				domain.setDomainName(set.getString("DOMAIN_NAME"));
				domainList.add(domain);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		  finally{
				DatasourceConnection.closeConnection(conn, stmt, set);
			}
		return domainList;
	}

	@Override
	public List<Map<String, String>> getDomainExpertList(int domainId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		List<Map<String, String>> expertList=new ArrayList<Map<String, String>>();
		
		try{
			conn=DatasourceConnection.getConnection();
			String query="Select a.cpf_no,initcap(b.emp_name) as name,initcap( b.designation) as designation,initcap(b.place_posting) as place,  c.domain_name from RPTT_EXPERT_MASTER a, ONGC_USER_MASTER b,  rptt_domain_master c where a. isactive='Y' and a.cpf_no = b.cpf_number and a.domain_id=c.domain_id and a.domain_id="+domainId;
			stmt=conn.createStatement();
			set=stmt.executeQuery(query);
			int i=1;
			while(set.next()){
				Map<String, String> map=new HashMap<String, String>();
				map.put("SLNO", i+"");
				map.put("NAME", set.getString("NAME"));
				map.put("CPF", set.getString("cpf_no"));
				map.put("DESIGNATION", set.getString("designation"));
				map.put("PLACE", set.getString("PLACE"));
				map.put("DOMAIN", set.getString("domain_name"));
				expertList.add(map);
				i++;
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		  finally{
				DatasourceConnection.closeConnection(conn, stmt, set);
			}
		return expertList;
	}

	@Override
	public boolean saveAskExpert(AskExpert askExpert) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag=false;
		try {
			String query="INSERT INTO RPTT_ASK_EXPERT (QUERY_ID, DOMAIN_ID, CPF_NO, MESSAGE, NO_OF_REPLIES, PRIORITY, POSTED_ON, ISACTIVE) values (?, ?, ?, ?, 0, ?, CURRENT_TIMESTAMP, 'Y')";
			conn = DatasourceConnection.getConnection();
			int id=getMaxIdFromTable("RPTT_ASK_EXPERT", "QUERY_ID", conn);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, askExpert.getDomainId());
			pstmt.setString(3, askExpert.getCpfno());
			pstmt.setString(4, askExpert.getMessage());
			pstmt.setString(5, askExpert.getPriority());
			flag = 0 < pstmt.executeUpdate();
			if(flag)
			{
				    //sendMailToExpert (askExpert);
			       new Thread( new Runnable() {
		          public void run(){
		        	  sendMailToExpert (askExpert);//** CALL METHOD FOR WORK IN BACKGROUND **
		          return; // to stop the thread
		                          }
		         }).start();
			}
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt);
		}
		
		return flag;
	}

	@Override
	public AskExpert viewAskExpert(int queryId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		AskExpert askExpert=new AskExpert();
		askExpert.setQueryid(queryId);
		try{
		    String query="Select a.QUERY_ID,a.DOMAIN_ID,a.CPF_NO,a.MESSAGE,a.NO_OF_REPLIES,a.PRIORITY,a.POSTED_ON,a.MODIFIED_ON,a.ISACTIVE,a.EXPERTRESPONSE,a.LAST_COMMENTED_ON,a.LAST_COMMENTED_BY,initcap(b.EMP_NAME) as NAME,initcap(c.DOMAIN_NAME) as DOMAIN_NAME from RPTT_ASK_EXPERT a ,ONGC_USER_MASTER b,RPTT_DOMAIN_MASTER c where a.cpf_no=b.cpf_number and a.domain_id=c.domain_id and a.query_id=?";
		    conn = DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, queryId);
			set=pstmt.executeQuery();
			while(set.next())
			{
				askExpert.setQueryid(set.getInt("QUERY_ID"));
				askExpert.setDomainId(set.getInt("DOMAIN_ID"));
				askExpert.setCpfno(set.getString("CPF_NO"));
				String comm=set.getString("MESSAGE");
				if (comm != null) {
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");
				}
				askExpert.setMessage(comm);
				askExpert.setNoofreplies(set.getInt("NO_OF_REPLIES"));
				askExpert.setPriority(set.getString("PRIORITY"));
				askExpert.setPosted_on(set.getTimestamp("POSTED_ON"));
				askExpert.setModified_on(set.getTimestamp("MODIFIED_ON"));
				askExpert.setIsActive(set.getString("ISACTIVE"));
				askExpert.setExpertResponse(set.getString("EXPERTRESPONSE"));
				askExpert.setLastcommented_on(set.getTimestamp("LAST_COMMENTED_ON"));
				askExpert.setLastcommented_by(set.getString("LAST_COMMENTED_BY"));
				askExpert.setUsername(set.getString("NAME"));
				askExpert.setDomainName(set.getString("DOMAIN_NAME"));
			}
		  }catch (Exception e) {
				LOGGER.error("erro"+e);
		  }
		  finally{
			  DatasourceConnection.closeConnection(conn,pstmt, set);
			}
		
		return askExpert;
	}

	@Override
	public List<ExpertReply> getAskExpertReplyList(int queryId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet set = null;
		List<ExpertReply> replyList=new ArrayList<ExpertReply>();
		try{
			conn = DatasourceConnection.getConnection();
			String query="SELECT R.REPLY_ID, R.QUERY_ID, R.RES_MESSAGE, R.CPF_NO, r.POSTED_ON, R.ISACTIVE, R.ISEXPERT, initcap(b.EMP_NAME) as NAME FROM RPTT_ASK_EXPERT_REPLY R, ONGC_USER_MASTER b WHERE r.QUERY_ID="+ queryId +" and r.cpf_no=b.cpf_number AND R.ISACTIVE='Y' order by posted_on desc";
			stmt=conn.createStatement();
			set=stmt.executeQuery(query);
			while(set.next()){
				ExpertReply reply=new ExpertReply();
				reply.setReplyId(set.getInt("REPLY_ID"));
				reply.setQueryId(set.getInt("QUERY_ID"));
				String comm=set.getString("RES_MESSAGE");
				if (comm != null) {
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");
				}
				reply.setReplyMessage(comm);
				reply.setCpfno(set.getString("CPF_NO"));
				reply.setPosted_on(set.getDate("POSTED_ON"));
				reply.setIsActive(set.getString("ISACTIVE"));
				reply.setIsExpert(set.getString("ISEXPERT"));
				reply.setUserName(set.getString("NAME"));
				replyList.add(reply);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		  finally{
			  DatasourceConnection.closeConnection(conn,pstmt, set);
			}
		return replyList;
	}

	private int getNoOfReply(int queryid, Connection conn2) {
		int id = 0;
		String query = "select NO_OF_REPLIES from RPTT_ASK_EXPERT where QUERY_ID =" + queryid;
		Statement stmt = null;
		ResultSet rs = null;
		try {				
			stmt = conn2.createStatement();
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

	
	@Override
	public boolean updateAskExpert(AskExpert askExpert) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag=false;
		try {
			String query1="UPDATE RPTT_ASK_EXPERT SET NO_OF_REPLIES=?, EXPERTRESPONSE=?, LAST_COMMENTED_BY=?, LAST_COMMENTED_ON=CURRENT_TIMESTAMP WHERE QUERY_ID=?";
			String query2="UPDATE RPTT_ASK_EXPERT SET NO_OF_REPLIES=?, LAST_COMMENTED_BY=?, LAST_COMMENTED_ON=CURRENT_TIMESTAMP WHERE QUERY_ID=?";
			conn = DatasourceConnection.getConnection();
			int noOfReply=getNoOfReply(askExpert.getQueryid(), conn);
			if("Y".equalsIgnoreCase(askExpert.getExpertResponse())){
				pstmt = conn.prepareStatement(query1);
				pstmt.setInt(1, noOfReply);
				pstmt.setString(2, askExpert.getExpertResponse());
				pstmt.setString(3, askExpert.getLastcommented_by());
				pstmt.setInt(4, askExpert.getQueryid());
			} else {
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, noOfReply);
				pstmt.setString(2, askExpert.getLastcommented_by());
				pstmt.setInt(3, askExpert.getQueryid());
			}
			flag = 0 < pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean saveAskExpertComment(ExpertReply expertReply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag=false;
		try {
			String query="INSERT INTO RPTT_ASK_EXPERT_REPLY (REPLY_ID, QUERY_ID, RES_MESSAGE, CPF_NO, POSTED_ON, ISACTIVE, ISEXPERT) values (?, ?, ?, ?, CURRENT_TIMESTAMP, 'Y', ?)";
			conn = DatasourceConnection.getConnection();
			int id=getMaxIdFromTable("RPTT_ASK_EXPERT_REPLY", "REPLY_ID", conn);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, expertReply.getQueryId());
			pstmt.setString(3, expertReply.getReplyMessage());
			pstmt.setString(4, expertReply.getCpfno());
			pstmt.setString(5, expertReply.getIsExpert());
			flag = 0 < pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("erro"+e);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		
		return flag;
	}

	@Override
	public List<AskExpert> getPendingPostList(int domainId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		 List <AskExpert> alist= new ArrayList<AskExpert>();
		  try{
		    String query="Select a.QUERY_ID,a.DOMAIN_ID,a.CPF_NO,a.MESSAGE,a.NO_OF_REPLIES,a.PRIORITY,a.POSTED_ON,a.MODIFIED_ON,a.ISACTIVE,a.EXPERTRESPONSE,coalesce(a.LAST_COMMENTED_ON,a.posted_on) as LAST_COMMENTED_ON,a.LAST_COMMENTED_BY,(Select initcap(c.emp_name) from ongc_user_master c where a.LAST_COMMENTED_BY=c.cpf_number )as LAST_COMMENTED_NAME,initcap(b.EMP_NAME) as NAME,initcap(c.DOMAIN_NAME) as DOMAIN_NAME from RPTT_ASK_EXPERT a ,ONGC_USER_MASTER b,RPTT_DOMAIN_MASTER c where a.cpf_no=b.cpf_number and a.domain_id=c.domain_id and (a.expertresponse !='Y' or a.expertresponse is null) and a.isactive='Y' and a.domain_id=?  order by LAST_COMMENTED_ON desc";
		    conn = DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, domainId);
			set=pstmt.executeQuery();
			while(set.next())
			{
				AskExpert askexp= new AskExpert();
				askexp.setQueryid(set.getInt("QUERY_ID"));
				askexp.setDomainId(set.getInt("DOMAIN_ID"));
				askexp.setCpfno(set.getString("CPF_NO"));
				String comm=set.getString("MESSAGE");
				if (comm != null) {
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");
				}
				askexp.setMessage(comm);
				askexp.setNoofreplies(set.getInt("NO_OF_REPLIES"));
				askexp.setPriority(set.getString("PRIORITY"));
				askexp.setPosted_on(set.getTimestamp("POSTED_ON"));
				askexp.setModified_on(set.getTimestamp("MODIFIED_ON"));
				askexp.setIsActive(set.getString("ISACTIVE"));
				askexp.setExpertResponse(set.getString("EXPERTRESPONSE"));
				askexp.setLastcommented_on(set.getTimestamp("LAST_COMMENTED_ON"));
				askexp.setLastcommented_by(set.getString("LAST_COMMENTED_BY"));
				askexp.setUsername(set.getString("NAME"));
				askexp.setDomainName(set.getString("DOMAIN_NAME"));
				askexp.setLastcommented_name(set.getString("LAST_COMMENTED_NAME"));
				alist.add(askexp);
			}
		  }catch (Exception e) {
			  LOGGER.error("erro"+e);
		  }
		  finally{
			  DatasourceConnection.closeConnection(conn,pstmt, set);
			}	
		 return alist;
	}

	@Override
	public List<AskExpert> getMyPostList(String cpfNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List <AskExpert> alist= new ArrayList<AskExpert>();
		  try{
		    String query="Select a.QUERY_ID,a.DOMAIN_ID,a.CPF_NO,a.MESSAGE,a.NO_OF_REPLIES,a.PRIORITY,a.POSTED_ON,a.MODIFIED_ON,a.ISACTIVE,a.EXPERTRESPONSE,coalesce(a.LAST_COMMENTED_ON,a.posted_on) as LAST_COMMENTED_ON,a.LAST_COMMENTED_BY,(Select initcap(c.emp_name) from ongc_user_master c where a.LAST_COMMENTED_BY=c.cpf_number )as LAST_COMMENTED_NAME,initcap(b.EMP_NAME) as NAME,initcap(c.DOMAIN_NAME) as DOMAIN_NAME from RPTT_ASK_EXPERT a ,ONGC_USER_MASTER b,RPTT_DOMAIN_MASTER c where a.cpf_no=b.cpf_number and a.domain_id=c.domain_id and a.isactive='Y' and a.cpf_no=? order by LAST_COMMENTED_ON desc";
		    conn = DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			set=pstmt.executeQuery();
			while(set.next())
			{
				AskExpert askexp= new AskExpert();
				askexp.setQueryid(set.getInt("QUERY_ID"));
				askexp.setDomainId(set.getInt("DOMAIN_ID"));
				askexp.setCpfno(set.getString("CPF_NO"));
				String comm=set.getString("MESSAGE");
				if (comm != null) {
					comm = comm.replaceAll("&gt;", ">");
					comm = comm.replaceAll("&lt;", "<");
				}
				askexp.setMessage(comm);
				askexp.setNoofreplies(set.getInt("NO_OF_REPLIES"));
				askexp.setPriority(set.getString("PRIORITY"));
				askexp.setPosted_on(set.getTimestamp("POSTED_ON"));
				askexp.setModified_on(set.getTimestamp("MODIFIED_ON"));
				askexp.setIsActive(set.getString("ISACTIVE"));
				askexp.setExpertResponse(set.getString("EXPERTRESPONSE"));
				askexp.setLastcommented_on(set.getTimestamp("LAST_COMMENTED_ON"));
				askexp.setLastcommented_by(set.getString("LAST_COMMENTED_BY"));
				askexp.setUsername(set.getString("NAME"));
				askexp.setDomainName(set.getString("DOMAIN_NAME"));
				askexp.setLastcommented_name(set.getString("LAST_COMMENTED_NAME"));
				alist.add(askexp);

			}
		  }catch (Exception e) {
			  LOGGER.error("erro"+e);
		  }
		  finally{
			  DatasourceConnection.closeConnection(conn,pstmt, set);
			}	
		 return alist; 
	}

	@Override
	public int getDomainByCpf(String cpfNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int domainId=0;
		  try{
		    String query="Select DOMAIN_ID from RPTT_EXPERT_MASTER where isactive='Y' and CPF_NO=?";
		    conn = DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			set=pstmt.executeQuery();
			while(set.next())
			{
			 domainId=set.getInt("DOMAIN_ID");
			}
		  }catch (Exception e) {
			  LOGGER.error("erro"+e);
		  }
		  finally{
				DatasourceConnection.closeConnection(conn,pstmt, set);
			}	
		 return domainId; 
	}

	@Override
	public boolean checkExpertRole(String cpfNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		boolean checkExpert=false;
		  try{
		    String query="Select * from RPTT_EXPERT_MASTER where isactive='Y' and CPF_NO=?";
		    conn = DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			set=pstmt.executeQuery();
			if(set.next())
			{
				checkExpert=true;
			}
		  }catch (Exception e) {
			  LOGGER.error("erro"+e);
		  }
		  finally{
			  DatasourceConnection.closeConnection(conn,pstmt, set);
			}	
		 return checkExpert; 
	}

	@Override
	public boolean sendMailToExpert(AskExpert askexpert) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		 boolean mailFlag=false;
		 	try{
		 	 String subject="Query on your Domain Expertise";
		 	 String toEmailId="";
		 	  PassioSendMail sendData=new PassioSendMail();
		 	  String query="Select a.cpf_no,initcap(b.emp_name) as name,c.domain_name,(select initcap(d.emp_name) from ONGC_USER_MASTER d where d.cpf_number= ?) as posted_name from RPTT_EXPERT_MASTER a, ONGC_USER_MASTER b,  rptt_domain_master c where a. isactive='Y' and a.cpf_no = b.cpf_number and a.domain_id=c.domain_id and a.DOMAIN_ID=?";
		      conn=DatasourceConnection.getConnection();
		 	  pstmt=conn.prepareStatement(query);
		 	  pstmt.setString(1, askexpert.getCpfno());
		      pstmt.setInt(2, askexpert.getDomainId());
		      set=pstmt.executeQuery();
		      while(set.next())
		      {	    
		        String cpfNo=set.getString("cpf_no");
		    	toEmailId=cpfNo+"@ongc.co.in";
		       StringBuffer msg=new StringBuffer();  
		       String username=set.getString("name");
		       String domain=set.getString("domain_name");
		       String postedby_name=set.getString("posted_name");
		       //toEmailId="mithun.7790@gmail.com";
		       Calendar cal= Calendar.getInstance();
		        msg.append("Dear "+username+",<br /><br />The following query has been raised on <strong>\"reports.ongc.co.in\"</strong> on your Domain Expertise <strong>'"+domain+"',</strong> by <strong>"+postedby_name+"</strong> on "+cal.getTime()+"<br />");
		       String message=askexpert.getMessage();
		       if (message != null) {
		    	   message = message.replaceAll("&gt;", ">");
		    	   message = message.replaceAll("&lt;", "<");
				} 
		       msg.append("<em>"+message+"</em>");
		       msg.append("<br /><strong>Please reply to this query in ");
		       if("Urgent".equalsIgnoreCase(askexpert.getPriority()))
		       {
		    	  msg.append("3 working days.</strong>");
		       }
		       else
		       {
		    	  msg.append("10 working days.</strong> ");
		       }
		       msg.append("<br><br><strong> Warm Regards, <br> ONGC Reports Team <br></strong><br><br> <br>P.S. This is a system-generated mail from ONGC Reports Team.  Please do not reply. <br /><br />");
		       mailFlag=sendData.sendEmail(subject,msg.toString(),toEmailId);
		     //  For Authoring
		       mailFlag=true;
		      
		      }
		 	}catch (Exception e) {
		 		LOGGER.error("erro"+e);
		     }
		     finally{
		 		DatasourceConnection.closeConnection(conn,pstmt, set);
		 	}
		 	return mailFlag;
	}

	private int getMaxIdFromTable(String tableName, String colName, Connection conn) {
		int id = 1000;
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
		if(id==0){
			id=1000;
		}
		return ++id;
	}
	
	@Override
	public boolean checkExpertRoleForDomain(String cpfNo, int domainId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		boolean checkExpert=false;
		  try{
		    String query="Select * from RPTT_EXPERT_MASTER where isactive='Y' and CPF_NO=? and DOMAIN_ID=?";
		    conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, cpfNo);
			pstmt.setInt(2, domainId);
			set=pstmt.executeQuery();
			if(set.next())
			{
				checkExpert=true;
			}
		  }catch (Exception e) {
			  LOGGER.error("erro"+e);
		  }
		  finally{
				DatasourceConnection.closeConnection(conn,pstmt, set);
			}	
		 return checkExpert; 
	}

}
