<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ include file="/init.jsp" %>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<div class="table-responsive">
<table id="facsummary" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>S. No.</th>
								<th>Location</th>
								<th>Category</th>
								<th>Title</th>
								<th>Posted By</th>
								<th>Mobile</th>
								<th>Posted Date</th>
							</tr>
						</thead>
						<tbody>
        <%
			try {
				int srno= 0;
				String stat=null;
				Connection conn = DatasourceConnection.getConnection();
				Statement stmt = conn.createStatement();
				String query="SELECT  id, cpf_no, initcap(name), mobile, email, location, category, title, discription, TO_CHAR(created_on, 'DD-MON-YYYY'), status, photos,user_type  FROM (SELECT  q.*, ROW_NUMBER() OVER(ORDER BY  q.Created_On DESC ) rn  FROM (  SELECT  *  FROM  ongc_facilitation WHERE STATUS='A'  ORDER BY  Created_On DESC ) q ) A ";
				ResultSet res = stmt.executeQuery(query);
				while (res.next()) {
            srno++;
     		%>
     		<tr>
     		    <td class="srn"><%= srno%>
     		    </td>
     		    <td><%= res.getString(6) %></td>
     		    <td><%= res.getString(7) %></td>
     		    <th>
     		    <div class="postDetail" style="display:none;">
     		    <div class="detailBox"><%= res.getString(9) %></div>
     		    </div><a onclick="javascript:facDesc('<%= res.getString(9) %>');"><%= res.getString(8) %></a></th>
     		    <td>
	           <% 
				stat=res.getString(13);
				 if("O".equalsIgnoreCase(stat))
	                   {			%>
     		   <a href="#" onclick="javascript:popup(<%= res.getString(2) %>);" rel="nofollow" class="defultchi2 blue" id="link"><%= res.getString(3) %></a>
				<%} else { %>
				<span><%= res.getString(3) %></span>
				<%}%>
			   </td>
     		    <td><%= res.getString(4) %></td>
     		    <td><%= res.getString(10) %></td>
     		</tr>
     		<%
}
%>
</tbody>
</table>
</div>
    <%
    res.close();
    stmt.close();
    conn.close();
        }
catch(Exception e)
{
    e.printStackTrace();
    }
%>
 
  <div class="modal fade pop" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" title="Close" class="close" data-dismiss="modal">&times;</button> <!-- onclick="closePanel()" -->
          <h4 class="modal-title">Post Details</h4>
        </div>
        <div class="modal-body">
		        	<div id="postDetais">
					<span id="postDetaisSpan"></span>
</div>

        </div>
      
      </div>
      
    </div>
  </div>
  
<script type="text/javascript">


$(document).ready(function() {
	 var table = $('#facsummary').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false
	    });
	}); 
	
function popup(cpfNo){
	 var url="<%=getEmployeeData %>&<portlet:namespace />cpfNo="+cpfNo;
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

function facDesc(desc){
	//alert(desc);
	$('#postDetaisSpan').text(desc);
    $("#myModal2").modal("show");
}
        </script>