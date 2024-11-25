<%@page import="com.ongc.liferay.reports.model.UtilityBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>


<%
	List<UtilityBean> utilities = (List<UtilityBean>) request.getAttribute("searchUtility");
%>
<div class="table-responsive">
<table  class="table table-striped stripe row-border order-column table-bordered" id="utilities" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Department</th>
			<th>Mobile</th>
			<th>Fax</th>
			<th>Residence No.</th>
			<th>Office No.</th>
			<th>Other Info</th>
			<th>Utility</th>
			<th>Sub Utility</th>
		</tr>
	</thead>
	<%
		for (UtilityBean utilityBean : utilities) {
	%>
	<tr>
		<td><%=utilityBean.getName()%></td>
		<td><%=utilityBean.getAddress()%></td>
		<td><%=utilityBean.getDepartment()%></td>
		<td><%=utilityBean.getMobile()%></td>
		<td><%=utilityBean.getFax()%></td>
		<td><%=utilityBean.getResidenceNo()%></td>
		<td><%=utilityBean.getOfficeNo()%></td>
		<td><%=utilityBean.getOtherInfo()%></td>
		<td><%=utilityBean.getUtility()%></td>
		<td><%=utilityBean.getSubUtility()%></td>
	</tr>
	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#utilities').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>