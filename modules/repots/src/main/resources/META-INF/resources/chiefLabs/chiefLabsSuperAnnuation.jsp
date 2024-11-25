<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp" %>
<div class="row" >
<div class="col-md-12 mb20" id="mainContentBoday" role="main" tabindex="-1">
<div class="row">
<h3 role="heading">Birthday List</h3>
</div>
</div>
<%
		int id=0;
		 %>
<div class="table-responsive">
<table class="table table-striped table-bordered"  id="feedTable">
<thead>
		<TR>
		
			<TH style="width:10%">ID</TH>
			<TH style="width:20%">CPF NO</TH>
			<TH style="width:50%">NAME</TH>
			<TH style="width:20%">DOB</TH>
		</TR>
		</thead>
		<% 
               Connection conn=null;
               Statement statement=null;
               ResultSet resultset=null;
               String time = "";
   try{
             String isLogin =String.valueOf(session.getAttribute("isLogin"));
             time=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
            
            
             
             if(!"YES".equals(isLogin))
             {%>
             <%response.sendRedirect("/wps/PA_ONGC_Report_M/jspPages/admin/chiefLabLogin.jsp"); %> 
	
             <% }
             conn = DatasourceConnection.getConnection();
             statement = conn.createStatement();
             resultset = statement.executeQuery("SELECT DISTINCT * FROM CHIEFLABBIRTHDAY WHERE SUBSTRING( DT_BIRTH, 4, 2 )='"+time.substring(3, 5)+"' and SUBSTRING( DT_BIRTH, 1, 2)='"+time.substring(0, 2)+"' ") ;
            while(resultset.next()){
        %>
        <tbody>
		<TR>
			<TD><%=++id
			 %></TD>
			<TD><%= resultset.getString(2) %></TD>
			<TD><%= resultset.getString(3) %></TD>
			<TD><%= resultset.getString(5) %></TD>
		</TR>
		</tbody>
		<% 
           } 
          
          if(id==0){%>
          <div class="row">
<h3 style="margin-left: 362px;">No Data found</h3>
</div>
          
          <%}
           
   
           }catch(Exception e)
           { e.printStackTrace();
           }
          finally {
		DatasourceConnection.closeConnection(conn, statement, resultset);
	}
       %>
	</TABLE>
</div>
</div>
<!-- SuperAnnuation start  -->

<div class="row">
<h3 role="heading">Superannuation List</h3>
</div>
<div class="col-md-12">
<%
		int id1=0;
		 %>
<div class="table-responsive">
<table class="table table-striped table-bordered"  id="feedTable">
<thead>
		<TR>
		
			<TH style="width:10%">ID</TH>
			<TH style="width:20%">CPF NO</TH>
			<TH style="width:50%">NAME</TH>
			<TH style="width:20%">DOR</TH>
		</TR>
		</thead>
		<% 
               Connection conn1=null;
               Statement statement1=null;
               ResultSet resultset1=null;
               String time1 = "";
   try{
             String isLogin =String.valueOf(session.getAttribute("isLogin"));
             time=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
             if(!"YES".equals(isLogin))
             {%>
             <%response.sendRedirect("/wps/PA_ONGC_Report_M/jspPages/admin/chiefLabLogin.jsp"); %> 
	
             <% }
             String xtt=time.substring(3, 5);
             conn = DatasourceConnection.getConnection();
             statement = conn.createStatement();
             resultset = 
            statement.executeQuery("select distinct * from CHIEFLAB_SUPERANNUTED where SUBSTRING( DT_BIRTH, 4, 2 )='"+xtt+"' and SUBSTRING( DT_BIRTH, 7, 4 )='"+time.substring(6, 10)+"' ") ;
            while(resultset.next()){
        %>
        <tbody>
		<TR>
			<TD><%=++id1
			 %></TD>
			<TD><%= resultset.getString(2) %></TD>
			<TD><%= resultset.getString(3) %></TD>
			<TD><%= resultset.getString(5) %></TD>
		</TR>
		</tbody>
		<% 
           } 
          
          if(id1==0){%>
          <div class="row">
<h3 style="margin-left: 362px;">No Data found</h3>
</div>
          
          <%}
           
   
           }catch(Exception e)
           {e.printStackTrace();
           }
          finally {
		DatasourceConnection.closeConnection(conn1, statement1, resultset1);
	}
       %>
	</TABLE>
</div>
</div>
