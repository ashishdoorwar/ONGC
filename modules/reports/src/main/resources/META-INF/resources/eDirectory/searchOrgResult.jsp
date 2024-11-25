<%@page import="com.ongc.liferay.reports.model.OrganizationBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<OrganizationBean> organizationBeans = (List<OrganizationBean>) request
			.getAttribute("searchOrganization");
%>
<div class="table-responsive">
<table class="table table-striped stripe row-border order-column table-bordered"
	id="organizationBeans" width="100%">
	<thead>
		<tr>
			<th>SUBSID</th>
			<th>Name</th>
			<th>Designation</th>
			<th>Office No.</th>
			<th>Residence No.</th>
			<th>Mobile</th>
			<th>Fax</th>
			<th>Address</th>
		</tr>
	</thead>
	<%
		for (OrganizationBean organizationBean : organizationBeans) {
	%>
	<tr>
		<td><%=organizationBean.getSubsiDaryId()%></td>
		<td><%=organizationBean.getName()%></td>
		<td><%=organizationBean.getDesignation()%></td>
		<td><%=organizationBean.getOfficeNo()%></td>
		<td><%=organizationBean.getResidenceNo()%></td>
		<td><%=organizationBean.getMobile()%></td>
		<td><%=organizationBean.getFax()%></td>
		<td><%=organizationBean.getAddress()%></td>
	</tr>

	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#organizationBeans').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>