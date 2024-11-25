<%@page import="com.ongc.liferay.model.ReportQuizBean"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="com.ongc.liferay.serviceImpl.ReportQuizServiceImpl"%>
<%@page import="com.ongc.liferay.service.IReportQuiz"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%

String msg = (String)request.getAttribute("msg");

if(msg !=null){
out.println(msg);	
}
IReportQuiz objReportQuiz = new ReportQuizServiceImpl();
Connection conn = DatasourceConnection.getConnection();
ArrayList als = null;
try {
	als = objReportQuiz.ReportQuizRes(conn);
} catch (SQLException e) {

	e.printStackTrace();
}
finally{
	DatasourceConnection.closeConnection(conn,null);
}
request.setAttribute("als", als);

%>    
 <table width="100%" border="0" class="dataTables table table-bordered table-striped">  
  
<%
	ArrayList<ReportQuizBean> al = (ArrayList<ReportQuizBean>)request.getAttribute("als");
	//out.println(al.size());
	if(al != null && al.size() > 0){
	int i=1;
	%>
	<thead>
	<tr>
	<th>Sl No..</th>
	<th>CPF NO.</th>
	<th>Name</th>
	<th>Designation</th>
	<th>Place of Posting</th>
	<th>Correct Answers</th>
	<th>Completed On</th>
	<th>Time Taken</th>
	</tr>
	</thead>
	<%
		for(ReportQuizBean objBean : al){
		
%>
    <tbody>  
      <tr>
	   <td><%= i%></td>
	    <td><a data-toggle="modal" data-target="#myModal" href="#" onclick="javascript:viewUserDetail(<%= objBean.getCpfno() %>);" rel="nofollow" class="defultchi2 blue"><%= objBean.getCpfno() %></a></td>
	    <td><%= objBean.getUsname()%></td>
	   <td><%= objBean.getDesignation()%></td>
	    <td><%= objBean.getLocation()%></td>
	    <td align="center">
		<%
	    %>
		<script>
	    var ans = [<%= "'"+objBean.getAnswers().replace(",", "','")+"'" %>];
	    var uans = [<%= "'"+objBean.getQuesans().replace(",", "','")+"'" %>];
	    var x=0;
	    for(i=0; i<uans.length; i++){
			    for(k=0; k<ans.length; k++){
					if(uans[i]==ans[k]){
					x++;
					}
				}
	    }  
	   // alert(x);
	    document.write("<span>"+x+"</span>");
	    </script></td>
	    <td><%= objBean.getResdate() %></td>
		 <td><%= objBean.getTimetaken() %></td>
	  </tr>
	           
<%i++;}	%>
</tbody>
	<%}
	else{
	%>
No Data Found
<%} %>
</table>

