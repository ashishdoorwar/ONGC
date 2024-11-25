<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.reports.model.StdBean"%>
<%@page import="java.util.List"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<StdBean> stdBeans = (List<StdBean>) request.getAttribute("stdBeans");
%>
<div class="table-responsive">
<table class="table table-striped stripe row-border order-column table-bordered"
	id="stdBeans" width="100%">

	<thead>
		<tr>
			<th>STATE</th>
			<th>LDCA</th>
			<th>SDCA</th>
			<th>STD CODE</th>

		</tr>
	</thead>
	<%
		for (StdBean stdBean : stdBeans) {
	%>
	<tr>
		<td><%=stdBean.getCircleName()%></td>
		<td><%=stdBean.getLdcsName()%></td>
		<td><%=stdBean.getSdcaName()%></td>
		<td><%=stdBean.getSdcaCode()%></td>
	</tr>

	<%
		}
	%>
</table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#stdBeans').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : false,
			fixedColumns : {
				leftColumns : 1,
			}
		});
	});
</script>