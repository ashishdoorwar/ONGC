<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.model.Covid19"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.dao.Covid19Dao"%>
<style>
.requiredValFldMsg {
	display: none;
}

.mand {
	color: red
}
</style>
	<%
		Covid19Dao cdao=new Covid19Dao();
		List<Covid19> list = cdao.getAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	%>
	<div class="mainform">
		<% if (list != null && list.size() > 0) {	%>
		<div class="table-responsive">
			<table class="table table-striped table-bordered" id="homecovid19">
				<thead>
					<tr>
						<th>S No.</th>
						<th>CPF No</th>
						<th>Concern</th>
						<th>Posted On</th>
					</tr>
				</thead>
				<%	int i = 1;
					for(Covid19 c19:list){	%>
				<tbody>
					<tr>
						<td><%=i%></td>
						<td><%=c19.getCpfNo()%></td>
						<td><%=c19.getConcern()%></td>
						<td><%=sdf1.format(sdf.parse(c19.getCreatedDate().toString()))%></td>
					</tr>
					<%	i++; 
						}%>
				</tbody>
			</table>
		</div>
	</div>
	<%	} else {%>
	<p style="text-align: center;">	<b><s:property value="searchmesg" /></b></p>
	<%	}	%>
	<div style="float: right; margin-top: 10px";><!-- javascript:viewCovid19Home(); -->
		<a class="btn btn-primary btn-sm"  onclick="javascript:history.back();" target="" title="">Back</a>
	</div>

<script>
			 $(document).ready(function() {
				 var table = $('#homecovid19').DataTable( {
					 lengthChange: false,bFilter: false, bInfo: false
				    });
				}); 
			 </script>