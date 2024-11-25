<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.model.SessionDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp"%>
<style>
.orderAndCircular-background-color {
	background-color: #770606;
	color: white;
}
</style>
<%
List<SessionDto> userLogRecord=null;
userLogRecord=(List<SessionDto>)request.getAttribute("userLogRecord");
if(userLogRecord==null)
	userLogRecord=new ArrayList();
%>
<div class="row">
	<div class="col-lg-12">
		<%@ include file="/admin/adminMenu.jsp"%>
	</div>
	<div class="col-md-8 pr10 mb20">
		<h2 class="h2 heading-clr mt0">List of Order and circular</h2>

		<div id="story">
			<div class="row">
				<div class="col-md-12">
					<div class="innersearch_order">
						<div class="welcome"></div>

						<form id="EditOrderAndCircular" name="EditOrderAndCircular"
							action="${addOrderAndCircular}" method="post"
							class="form-horizontal pl20 pr20 pb20 pt20">
							<div class="bg-grey fs-title search-form">
								<div class="row input-daterange" id="datepicker">
									<aui:col width="50">
										<aui:input label="Start Date" name="searchFrom" type="date" />
									</aui:col>
									<aui:col width="50">
										<aui:input label="End date" name="searchTo" type="date" />
									</aui:col>
								</div>
								<aui:button-row cssClass="text-center ">
									<aui:button cssClass="btn orderAndCircular-background-color "
										type="submit" value="Search" />
									<aui:button cssClass="btn orderAndCircular-background-color "
										value="Reset" />
								</aui:button-row>

							</div>
						</form>

						<div>
							<p>Total user : <%=userLogRecord.size() %></p>
						</div>
						<table cellspacing="0" id="item"
							class="table table-bordered table-striped" cellpadding="0">
							<thead>
								<tr>
									<th>CPF Number</th>
									<th>Login Time</th>
									<th>Active Session Time</th>
								</tr>
							</thead>
							<tbody>
							<%
								SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
							for(SessionDto sessionDto:userLogRecord){ 
								
							%>
								<tr>
									<td><%=sessionDto.getUser_id() %></td>
									<td><%=sdf.format(sdf1.parse(sessionDto.getLogin_time().toString())) %></td>
									<td><%=sessionDto.getActiveSessionTime() %></td>
								</tr>
								<%} %>
							</tbody>
						</table>


					</div>
				</div>
			</div>

		</div>


	</div>
	<div class="col-md-4 pl10 admin-menu mb20">
		<%@ include file="/admin/rightNav.jsp"%>
	</div>


</div>