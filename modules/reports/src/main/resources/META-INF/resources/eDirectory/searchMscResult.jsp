<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.reports.model.MscBean"%>
<%@page import="java.util.List"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<MscBean> mscBeans = (List<MscBean>) request.getAttribute("mscBeans");
%>
<div class="table-responsive">
<table class="table table-striped stripe row-border order-column table-bordered"
	id="mscBeans" width="100%">

	<thead>
		<tr>
			<th>Network</th>
			<th>Circle Category</th>
			<th>Circle</th>
			<th>Operator</th>
			<th>Mnc Code</th>
			<th>Start Series</th>
			<th>End Series</th>
			<th>Series details</th>

		</tr>
	</thead>
	<%
		for (MscBean mscBean : mscBeans) {
	%>
	<tr>
		<td><%=mscBean.getNetwork()%></td>
		<td><%=mscBean.getCircleCategory()%></td>
		<td><%=mscBean.getCircle()%></td>
		<td><%=mscBean.getMobileOperator()%></td>
		<td><%=mscBean.getMncCode()%></td>
		<td><%=mscBean.getStartSeries()%></td>
		<td><%=mscBean.getEndSeries()%></td>
		<td><%=mscBean.getCodeDetails()%></td>
	</tr>

	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#mscBeans').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>