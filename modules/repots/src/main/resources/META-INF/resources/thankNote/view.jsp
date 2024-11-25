<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ include file="/init.jsp" %>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<div class="row">
<div class="col-md-12 mb20">
<div id="paging_container3" class="fc-container table-responsive">
<table id="thank-note-summary" class="table table-bordered" >
						<thead>
							<tr>
								<th scope="col" width="50" height="30">S No.</th>
								<th scope="col">Thanks From</th>
								<th scope="col">Thanks To</th>
								<th scope="col">Brief reason for thanking</th>
								<th scope="col" width="100">Posted Date</th>
							</tr>
						</thead>
						<tbody>
        <%
         Statement stmt =null;
         Statement stmt2 =null;
         ResultSet res=null;
         Connection conn=null;
			try {
				int srno= 0;
				conn = DatasourceConnection.getConnection();
				stmt = conn.createStatement();
				String query="Select nid,initcap(from_name), initcap(to_name), MESSAGE, fromcpf,tocpf, to_char(post_date,'DD-MM-YYYY HH24:MM:SS') from (SELECT nid,from_name,to_name, MESSAGE, fromcpf, tocpf, post_date,ROW_NUMBER() OVER(ORDER BY  post_date DESC ) as rn FROM  (SELECT a.nid,b.emp_name AS from_name, (SELECT c.emp_name FROM ongc_user_master c WHERE a.tocpf=c.cpf_number ) AS to_name, a.message,a.fromcpf, a.tocpf,a.post_date FROM thank_note a,ongc_user_master b WHERE a.fromcpf=b.cpf_number AND a.status='Y'  and a.post_date >=NOW() - interval '30 DAY'  ORDER BY a.post_date DESC) s ) as d ";
			     res = stmt.executeQuery(query);				
			    while (res.next()) {
            srno++;
     		%>
     		<tr>
     		    <td class="srn" width="5%"><%= srno %> </td>	    
     		    <td width="15%"><a href="javascript:popup(<%= res.getString(5) %>);" rel="nofollow" ><%= res.getString(2) %></a></td>
     		    <td width="15%"><a data-toggle="modal" data-target="#myModal" href="#" onclick="javascript:popup(<%= res.getString(6) %>);" rel="nofollow" ><%= res.getString(3) %></a></td>
     		    <td width="50%">
     		    <%= res.getString(4) %></td>
     		    <td width="15%" ><%= res.getString(7) %></td>
     		</tr>
     		<%
}
%>


    <%
         }
catch(Exception e){
    e.printStackTrace();
    }
    finally {
     		if(res!=null)
				res.close();			
			if(stmt!=null)
				stmt.close();
			if(stmt2!=null)
				stmt2.close();
			if(conn!=null)
				conn.close();
      
	}
%>
</tbody>
</table>
</div>
 

</div>
</div>
<script type="text/javascript">
          
            $(document).ready(function() {
           	 var table = $('#thank-note-summary').DataTable( {
           		 lengthChange: false,bFilter: false, bInfo: false
           	    });
           	}); 
function popup(cpfNo){
	 var url="<%=getEmployeeData%>&<portlet:namespace />cpfNo="+cpfNo;
	Liferay.Util.openWindow({
	    dialog: {
	        centered: true,
	        height: 600,
	        modal: true,
	        width: 800,
	        style:"background-color: #8c000d;color: #fff !important;",
	        cssClass: "ui-model",
	        destroyOnHide: true,
           resizable: false,
	    },
	    id: "<portlet:namespace />popUpId",
	    title: "Employee Details",
	    uri: url
	}); 
	  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
        Liferay.Util.Window.getById(popUpId).destroy();
        location.reload();
    },
    ['liferay-util-window']
    ); 
	 
	 
}
</script>