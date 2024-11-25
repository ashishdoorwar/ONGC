<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ include file="/init.jsp" %>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>

		<div class="table-responsive">
		<table class="table table-bordered" id="leadership" summary="Employee's Leadership Activities">
			<thead>
				<tr>
					
					<th colspan="6">&nbsp;</th>
					<th colspan="2">ReaderSpeak</th>
					<th colspan="3">&nbsp;</th>
					<th colspan="2">Know Your Colleague</th>
					<th colspan="2">Treasure Hunt</th>
					<th colspan="2">Thank You Note</th>
					<th colspan="2">&nbsp;</th>					
				
				</tr>
                <tr>
				<th>Current Rank</th>
					<th>Name</th>
					<th>CPF No</th>
					<th>Comment</th>
					<th>Like</th>
					<th>Dislike</th>
					<th >Articles</th>
					<th>Poems</th>
					
					<th>Feedback Posts</th>
					<th>Feedback Comments</th>
					<th>Enabler Response</th>
					<th>W</th>
                    <th >P</th>
                    <th>W</th>
                    <th >P</th>
                    <th>Received</th>
                    <th >Sent</th>
						<th>Opinion</th> 
					<th><b>Total Score</b></th>
				</tr>
			</thead>
			<tbody>
				<%
					Connection conn = null;
					Statement stmt = null;
					ResultSet res = null;
					try {

						int srno = 0;

						
						conn = DatasourceConnection.getConnection();

						stmt = conn.createStatement();
						String query = "SELECT name,cpfnum,comments,likes,dislikes,articles,readerspeak_poems,feedback_posts,feedback_comments,feedback_enabler,knw_colleague,knw_colleague_partcpt,thunt,thunt_partcpt,receive_note,sent_note,opinionology,total FROM (SELECT initcap(b.emp_name) AS name, a.cpfnum, a.comments, a.likes,a.dislikes,a.articles,a.readerspeak_poems,a.feedback_posts, a.feedback_comments,a.feedback_enabler,a.knw_colleague,a.knw_colleague_partcpt, a.thunt, a.thunt_partcpt,a.receive_note, a.sent_note,a.opinionology, (a.comments + a.likes + a.dislikes + a.articles + a.readerspeak_poems+ a.feedback_posts + a.feedback_comments + a.feedback_enabler + a.knw_colleague + a.knw_colleague_partcpt + a.thunt+a.thunt_partcpt + a.receive_note + a.sent_note + a.opinionology) AS Total,ROW_NUMBER() OVER (ORDER BY  total DESC ) rn FROM leadership_board a,ongc_user_master b  WHERE a.cpfnum=b.cpf_number ORDER BY total DESC) as d fetch first 500 rows only ";
						res = stmt.executeQuery(query);
						while (res.next()) {
							srno++;
				%>
				<tr>
					<td class="srn"><%=srno%></td>
					<th scope="row"><%=res.getString(1)%></th>
					<td>
					<a  href="#" onclick="javascript:popup(<%=res.getString(2)%>)" rel="nofollow" class="defultchi2 blue"><%=res.getString(2)%></a>
					</td>
					
					<td><%=res.getString(3)%></td>
					<td><%=res.getString(4)%></td>
					<td><%=res.getString(5)%></td>
					<td><%=res.getString(6)%></td>
					<td><%=res.getString(7)%></td>
					<td><%=res.getString(8)%></td>
					<td><%=res.getString(9)%></td>
					<td><%=res.getString(10)%></td>
					<td><b><%=res.getString(11)%></b></td>
					<td><b><%=res.getString(12)%></b></td>
					<td><b><%=res.getString(13)%></b></td>
					<td><b><%=res.getString(14)%></b></td>
					<td><b><%=res.getString(15)%></b></td>
					<td><b><%=res.getString(16)%></b></td>
					<td><b><%=res.getString(17)%></b></td>
					<td><b><%=res.getString(18)%></b></td>
				</tr>
				<%
					}
				%>
				
			</tbody>
		</table>
		</div>
		<br/>
	
		<%
			} catch (Exception e) {
				System.out.println("Exception inside viewFullLeaderBoard JSP "+e);
				e.printStackTrace();
			} finally {
     		if(res!=null)
				res.close();			
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
      
	}
		%>


<!-- Modal -->
  </tbody></table>
  
<script type="text/javascript">
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

$(document).ready(function() {
	 var table = $('#leadership').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false
	    });
	}); 
        </script>
  
 