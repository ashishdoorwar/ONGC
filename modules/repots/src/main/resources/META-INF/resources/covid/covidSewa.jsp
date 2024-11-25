<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/init.jsp" %>
<%!
private Connection conn=null;
private PreparedStatement pstmt;
private ResultSet set;
String mobileno="";
String cpf="";
String designation="";
String name="";
String location="";

 public String getRecord(String CPFNo){
	boolean flage=false;
	String query="  SELECT * FROM ongc_user_master WHERE CPF_NUMBER=? ";
	ResultSet rs1=null; 
	String cpfexist="";
	try{
		conn=DatasourceConnection.getConnection();
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1,CPFNo);
	   rs1=pstmt.executeQuery();
	   while(rs1.next()){ 
	  mobileno=rs1.getString("MOBILE_NUMBER");
	  designation=rs1.getString("DESIGNATION");
	  name=rs1.getString("EMP_NAME");
	  location=rs1.getString("PLACE_POSTING");
	  }
	}
	catch (Exception e) {
	System.out.println("Test---->"+e.getMessage());
     	e.printStackTrace();
	}
	finally{
		DatasourceConnection.closeConnection(conn, pstmt);
	}	

	return cpfexist;
} 
%>


<%
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
String cpfNo = userOngc.getCpfNo();
%>
<% if(request.getParameter("msg")!=null){ %>
<div class="alert alert-success"> Your details Submitted Successfully. </div>
<% } %>

<portlet:actionURL var="savecovidseva" name="saveCovidSewa" ></portlet:actionURL>
<div class="formWrapper">
<aui:form name="covidsevaform" action="<%=savecovidseva%>" method="post">
			<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Covid Sewa"> -->
			<aui:row>
				<aui:col width="50">
				<aui:input type="hidden" name="cpf" value="<%= cpfNo %>" ></aui:input>
				<aui:input name="name" type="text" required="true" label="Name"></aui:input>
				</aui:col>
					<aui:col width="50">
					<aui:input name="designation" type="text" required="true" value="" label="Designation"></aui:input>
					</aui:col>
					</aui:row>
					<aui:row>
				<aui:col width="50">
				<aui:input name="location" type="text" required="true" label="Location"></aui:input>
				</aui:col>
					<aui:col width="50">
					<aui:input name="mobile" type="text" required="true" value="" label="Mobile number"></aui:input>
					</aui:col>
					</aui:row>
			<div id="postbtn_container" align="center">
				<input type="submit" value="Submit" class="btn btn-sm btn-primary"/>
			</div>
			</aui:fieldset>
			</aui:fieldset-group>
		</aui:form>
	</div>
<script>
$( document ).ready(function() {
});
 function ongreporthits(pageurl){
     $.ajax( {
		      type: "GET",
		      url: "/wps/PA_ONGC_Report_M/jspPages/reporturlhit.jsp",
		      data: {'pageurl':pageurl} ,
		      success: function( response ) {
		      }
		} ); 
     }
    
	function save() {
	if($('#name').val()==""){
	alert("Employee name required");
	return false;
	} 
			 
	if($('#desig').val()==""){
	alert("Designation required");
	return false;
	} 
			 
	if($('#location').val()==""){
	alert("Location required");
	return false;
			 } 
			 
	if($('#mobileno').val()==""){
	 alert("MobileNo required");
	  return false;
	 } 		 		 
	
	var frm1 = $("#covidsevaform");
			frm1.submit();
}

</script>


