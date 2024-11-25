<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<User> locations = (List<User>) request.getAttribute("searchLocation");
%>
<div class="table-responsive">
<table  class="table table-striped stripe row-border order-column " id="locations" width="100%">
	<thead>
		<tr>
			<th>CPF No.</th>
			<th>Name</th>
			<th>Department</th>
			<th>Mobile</th>
			<th>Fax</th>
			<th>Res. No.</th>
			<th>Office Phone Number</th>
			<th>Place of posting</th>
			<th>Work Place</th>
		</tr>

	</thead>
	<%
		for (User userlocation : locations) {
	%>
	<tr>
		<td><%=userlocation.getCpfNo()%></td>
		<td><%=userlocation.getEmpName()%></td>
		<td><%=userlocation.getDepartment()%></td>
		<td><%=userlocation.getMobileNo()%></td>
		<td><%=userlocation.getFaxNumber()%></td>
		<td><%=userlocation.getResidenceAddress()%></td>
		<td><%=userlocation.getOfficeAddress()%></td>
		<td><%=userlocation.getPlacePosting()%></td>
		<td><%=userlocation.getWorkPlace()%></td>

	</tr>
	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#locations').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>