<%@ include file="/init.jsp" %>
<portlet:renderURL var="userHome">
	<portlet:param name="mvcPath" value="/jsp/userHome.jsp" />
</portlet:renderURL>
<portlet:renderURL var="complaintRegister">
	<portlet:param name="mvcRenderCommandName" value="complaintRegister" />
</portlet:renderURL>
<portlet:renderURL var="viewComplaintStatus">
	<portlet:param name="mvcRenderCommandName" value="viewComplaintStatus" />
</portlet:renderURL>
<portlet:renderURL var="changepasshome">
	<portlet:param name="mvcRenderCommandName" value="changepasshome" />
</portlet:renderURL>
<portlet:renderURL var="userLogout">
	<portlet:param name="mvcRenderCommandName" value="userLogout"  />
</portlet:renderURL>
<html>
<head></head>
<body>
	<h1 class="mt0 mb5">Menu</h1>
	<div class="bg-grey pn menu">
		<ul class="list-unstyled">
			<li title="Home"><a href="<%=userHome %>">
					<i class="fa fa-file"></i>&nbsp; Home</a></li>
				<li title="Register a complaint"><a href="<%=complaintRegister %>">
					<i class="fa fa-refresh"></i>&nbsp; Register a complaint </a></li>
			<li title="View Status"><a href="<%=viewComplaintStatus%>">
					<i class="fa fa-search"></i>&nbsp; View Complaint</a></li>
			<%-- <li title="Change Password"><a href="<%=changepasshome%>">
					<i class="fa fa-key"></i>&nbsp; Change Password</a></li> --%>
			<li title="Log Out"><a href="<%= userLogout%>" cssClass="active">
					<i class="fa fa-power-off"></i>&nbsp; Log Out</a></li>
		</ul>
	</div>
</body>
</html>