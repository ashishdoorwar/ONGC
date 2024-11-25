<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.reports.model.DirAssistanceBean"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
	List<DirAssistanceBean> assisDires = (List<DirAssistanceBean>) request.getAttribute("searchAssisDir");
%>
<div class="table-responsive">
<table  class="table table-striped stripe row-border order-column table-bordered" id="assisDires" width="100%">
	<thead>
		<tr>
			<th>CPF No.</th>
			<th>Name</th>
			<th>Designation</th>
			<th>E mail</th>
			<th>Residence No.</th>
			<th>Office No.</th>
			<th>Location</th>
			<th>Infocom Unit</th>
			<th>Category</th>
		</tr>
	</thead>
	<%
		for (DirAssistanceBean assistanceBean : assisDires) {
	%>
	<tr>
		<td><%=assistanceBean.getCpfNo()%></td>
		<td><%=assistanceBean.getName()%></td>
		<td><%=assistanceBean.getDesignation()%></td>
		<td><%=assistanceBean.getEmail()%></td>
		<td><%=assistanceBean.getResidenceNo()%></td>
		<td><%=assistanceBean.getOfficeNo()%></td>
		<td><%=assistanceBean.getLocation()%></td>
		<td><%=assistanceBean.getInfocomUnit()%></td>
		<td><%=assistanceBean.getCategory()%></td>
	</tr>
	<%
		}
	%>

</table>
</div>

<script>
	$(document).ready(
			function() {
				var table = $('#assisDires').DataTable({
					lengthChange : false,
					buttons : [ 'copy', 'excel', 'pdf', 'colvis' ]
				});

				table.buttons().container().appendTo(
						'#example_wrapper .col-md-6:eq(0)');

			});
</script>