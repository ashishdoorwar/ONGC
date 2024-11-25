<%@page import="com.ongc.liferay.reports.model.WorkplaceBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<WorkplaceBean> workplaces = (List<WorkplaceBean>) request.getAttribute("searchWorkplace");
%>
<div class="table-responsive">
<table class="table table-striped stripe row-border order-column data table-bordered" id="workplaces" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Department</th>
			<th>Mobile</th>
			<th>Fax</th>
			<th>Residence No.</th>
			<th>Office No.</th>
			<th>WorkPlace</th>
			<th>Category</th>
		</tr>
	</thead>
	<%
		for (WorkplaceBean bean : workplaces) {
	%>
	<tr>
		<td><%=bean.getName()%></td>
		<td><%=bean.getAddress()%></td>
		<td><%=bean.getDepartment()%></td>
		<td><%=bean.getMobile()%></td>
		<td><%=bean.getFax()%></td>
		<td><%=bean.getResidenceNo()%></td>
		<td><%=bean.getOfficeNo()%></td>
		<td><%=bean.getWorkplace()%></td>
		<td><%=bean.getSubCategory()%></td>
	</tr>

	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#workplaces').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>