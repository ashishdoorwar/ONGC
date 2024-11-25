<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="/init.jsp" %>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="java.util.List"%>
<head>
<s:form action="vigilanceAdminLogin" method="post" name="timeout" theme="simple">
</s:form>
<%
if(session.getAttribute(VigilanceConstant.VIGILANCE_ADMINUSER)==null){
%>
<script>
document.timeout.submit();
</script>
<%
}
%>
     
	 <link rel="stylesheet"  href='<%=request.getContextPath()%>/css/datepicker.css")%>'  type="text/css">
	<link rel="stylesheet"
	href='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.css'
	type="text/css">
	<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/Adminstyle.css'
	type="text/css">
	<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>
	
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
	<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/complaintPagging.js'></script>

	
	<script>
	
window.onload = function(){
	$(function() {
		$('#startDate').datepick();	
		$('#endDate').datepick();
	});
		
	};
</script>
<%!
public String getTreamFileName(String fileName)
	{
	  String extName=fileName.substring(fileName.lastIndexOf("."));
	   String fName=fileName.substring(0,fileName.lastIndexOf("."));
	   if(fName.length()>15)
	   {
	      fName=fName.substring(0,15)+".."+extName;
	   }
	   else
	   {
	     fName=fileName;
	   }
	   return fName;
	}
%>
</head>
<%
List<VigilanceComplaint> list=(List<VigilanceComplaint>)request.getAttribute("complaintList");
//ComplaintPaginator cpag=new ComplaintPaginator(list,10,"0");
java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
 %>
<s:form action="updateComplaintDetail" method="post" name="complaintForm" theme="simple">
<s:hidden name="complaint.complaintNo" id="compId"/>
</s:form>
   <header>
   <%@ include file="/admin/header.jsp" %>
    </header>
	<div class="container " id="skip-main-content">
	<div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
      <%@ include file="/admin/menu.jsp" %>
      	</div>
       
       
       
     
       <!--left section end-->
       
       <!--right section start-->
<div class="col-md-9">


<div class="form-item">
			<h1 class="mb5 mt0">Update Complaint</h1>
		</div>
			
		


<div class="bg-grey bdr-orange search-form">
		



	
	  <div class="row">
	  
	  
	  <div class="col-md-12">
	  

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  
		  <tr>
		    <td >Total Results (<%=list.size() %>)</td>
	      </tr>
		  <tr>
		  
		  </tr></table> 
</div></div>

 <div class="row" id="complaintTD">
	  
	  
	  <div class="col-md-12">
		  
		     <div class="table-responsive sr-table">
		    <table id="viewComplaint"
							class="table table-striped table-bordered">
		      <thead>
			  <tr >
		           <th>S.No.</th>
		        <th>Complaint No.</th>
		         <th >Complaint Date</th>
		        <th >Subject</th>
		        <th >Attachment</th>		       
		         <th>Name</th>
		        <th >Mobile No.</th>
		       <th >Action</th>
		        </tr>
				</thead> <tbody>	
		 <% int sno=0;
			//while (cpag.isNext()) {
			//VigilanceComplaint vc = (VigilanceComplaint) cpag.next();
			for(VigilanceComplaint vc:list){sno++;
			//VigilanceComplaint vc=(VigilanceComplaint)cpag.next();
			List<ComplaintAttachment> lst=vc.getAttachmentList();
		%>   
		
<portlet:renderURL var="updateComplaintDetail">
	<portlet:param name="mvcRenderCommandName" value="updateComplaintDetail" />
	<portlet:param name="pid" value="<%=String.valueOf(vc.getComplaintNo())  %>" />
</portlet:renderURL>
	
		      <tr>
		        <td><%=sno %></td>
		        <td><%= vc.getComplaintActNo() %> </td>
		        <td><%= dateFormat.format(vc.getComplaintDate())%></td>
		         <td><%= vc.getComplaintSubject() %></td>
		        <td>
		       <%if(!lst.isEmpty()){
for(ComplaintAttachment complaintAttachment:lst){ %>
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } }  else { %>NA<%}	 %>
		        </td>
		         <td><%=vc.getComplainBy().getFirstName()%></td>
		        <td><%= vc.getComplainBy().getMobile()%></td>
		       <td><a href="<%=updateComplaintDetail%>"> Update</a></td>
		        </tr>
		        <% }%>
		
		  </tbody>
		      </table>
			  </div>
			 
			 
			 
			 
</div>
<%-- 
 <div class="row">
	      
	  
	  <div class="col-md-12">
      <%= cpag.createIndex("callCompltUpdatePage") %>
        </div>  
</div> --%>
     </div>


</div>



	 
</div>
</div>
</div>
</div>
<script>
$(document).ready(function() {
	 var table = $('#viewComplaint').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false
	    });
	}); </script>



<br clear="all"/>
</div>
</div>
</div>
    <footer>
    <%@ include file="/admin/footer.jsp" %>
         </footer>


