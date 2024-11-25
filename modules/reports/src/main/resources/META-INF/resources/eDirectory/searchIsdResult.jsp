<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.reports.model.IsdBean"%>
<%@page import="java.util.List"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<%
List<IsdBean> isdBeans =(List<IsdBean>)request.getAttribute("isdBeans");
%>
<div class="table-responsive">
<table class="table table-striped stripe row-border order-column " id="organizationBeans" width="100%">
<thead>
	<tr>
		<th>Country</th>
		<th>ISD Code</th>
		<th>IDD Code</th>
		
	</tr>
	</thead>
	<%
	for(IsdBean isdBean: isdBeans){%>
		<tr>
			<td><%=isdBean.getCountry() %></td>
			<td><%=isdBean.getIsdCode() %></td>
			<td><%=isdBean.getIddCode() %></td>
		</tr>
		
	<%}
	%>
</table> 
</div>
<script type="text/javascript">
$(document).ready(function() {
    var table = $('#organizationBeans').DataTable( {
        scrollY:        "400px",
        scrollX:        true,
        scrollCollapse: false,
        fixedColumns:   {
            leftColumns: 1,
        }
    } );
});
</script>