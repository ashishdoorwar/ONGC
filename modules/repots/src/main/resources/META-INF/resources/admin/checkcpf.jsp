
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@ include file="/init.jsp" %>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>

<%

UserDao udao = new UserDao();
				User u= udao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
	String msg = "false";
    Statement stmt =null;
    ResultSet res=null;
    Connection conn=null;
	try {
		String checpfno = u.getCpfNo();
		System.out.println("checpfno "+checpfno);
		String query = "select cpf_no from qn_res where cpf_no='"+ checpfno + "'";
        conn = DatasourceConnection.getConnection();
		stmt = conn.createStatement();
	    res = stmt.executeQuery(query);
		if (!res.next()) {
			System.out.println("asdfadsf  ");
			boolean chck = udao.saveCpf(checpfno);
        	msg = "true";
			System.out.println("asdfadsf  ggggggggggggg");
		}
	  
	   }
	   catch (Exception e) 
	   {
		System.out.println("Exception checkcpf jsp "+e);   
		e.printStackTrace();
	   }
      finally {
     		if(res!=null)
				res.close();			
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
      }
	  System.out.println("msg msg msg "+msg);
	out.println(msg);
%>
