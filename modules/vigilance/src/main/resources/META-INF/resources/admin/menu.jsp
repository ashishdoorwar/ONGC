<%@ include file="/init.jsp" %>
<portlet:renderURL var="readComplaintList">
	<portlet:param name="mvcRenderCommandName" value="readComplaintList" />
</portlet:renderURL>
<portlet:renderURL var="updateComplaint">
	<portlet:param name="mvcRenderCommandName" value="updateComplaint" />
</portlet:renderURL>
<portlet:renderURL var="searchComplaint">
	<portlet:param name="mvcRenderCommandName" value="searchComplaint" />
</portlet:renderURL>
<portlet:renderURL var="reportComplaintDateWise">
	<portlet:param name="mvcRenderCommandName" value="reportComplaintDateWise" />
</portlet:renderURL>
<portlet:renderURL var="reportComplaintMonthly">
	<portlet:param name="mvcRenderCommandName" value="reportComplaintMonthly" />
</portlet:renderURL>
<portlet:renderURL var="adminChangePassword">
	<portlet:param name="mvcRenderCommandName" value="adminChangePassword" />
</portlet:renderURL>
<portlet:renderURL var="adminLogout">
	<portlet:param name="mvcRenderCommandName" value="adminLogout"  />
</portlet:renderURL>
<html>
<head></head>
  <body>       
	  <h1 class="mt0 mb5">Menu</h1>
	  <div class="bg-grey pn menu">
	  <ul class="list-unstyled">
         <li title="Read Complaints"><a href="<%=readComplaintList%>"><i class="fa fa-file"></i>&nbsp; Read Complaints</a></li>
	  <li title="Update Complaints"><a href="<%=updateComplaint%>"><i class="fa fa-refresh"></i>&nbsp; Update Complaints</a></li>
	  <li title="Search Complaints"><a href="<%=searchComplaint%>"><i class="fa fa-search"></i>&nbsp; Search Complaints</a></li>
	  <li title="Periodic Report"><a href="<%=reportComplaintDateWise%>"><i class="fa fa-bar-chart"></i>&nbsp; Periodic Report</a></li>
	  <li title="Monthly Report"><a href="<%=reportComplaintMonthly%>" ><i class="fa fa-bar-chart"></i>&nbsp; Monthly Report</a></li>
	  <li title="Change Password"><a href="<%=adminChangePassword%>"><i class="fa fa-key"></i>&nbsp; Change Password</a></li>
	  <li title="Log Out"><a href="<%=adminLogout%>"><i class="fa fa-power-off"></i>&nbsp; Log Out</a></li>
	  </ul>
	  </div>   
   </body>
</html>