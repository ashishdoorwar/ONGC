<%-- <%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="submissionRedirect" > 
 	<portlet:param name="mvcPath" value="/admin/quez.jsp"/>
 </portlet:renderURL>
	<%
	UserDao userDao = new UserDao();
	User u= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress());
 Statement stmt =null;
ResultSet res=null;
Connection conn=null;
boolean chck=true;
String checpfno = u.getCpfNo();
		String query = "select cpf_no from qn_res where cpf_no='"+ checpfno + "'";
        conn = DatasourceConnection.getConnection();
		stmt = conn.createStatement();
	    res = stmt.executeQuery(query);
		if (!res.next()) {
			chck = userDao.saveCpf(checpfno);
			System.out.println("asdfadsf  ggggggggggggg"); 
			%>
			<a href="<%= submissionRedirect%>" class="btn btn-primary">Perform Quiz</a>
			<%
		}
		else{
			%>
			<div class="messageQBox alert alert-warning text-center" id="msgbox"
		style="display: none;">You have already participated in the
		quiz.</div>
			<%
		}
			%> --%>