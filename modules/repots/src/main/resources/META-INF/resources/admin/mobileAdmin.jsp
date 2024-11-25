<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.model.MobileUserBean"%>
<%@ include file="/init.jsp"%>
<style>
.left-box {
	max-width: 862px !important;
	margin: auto !important;
	float: none;
}
#myNavbar .navbar-nav {
	float: left !important
}
.wpthemeHiddenPlusControlHeaderParent {
	display: none;
}
</style>
<portlet:renderURL var="searchMobUserRecord">
	<portlet:param name="mvcRenderCommandName" value="searchMobileAdmin"/>
</portlet:renderURL>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<%@ include file="/admin/adminMenu.jsp"%>
		</div>
		<div class="col-lg-12">
			<%
				List<MobileUserBean> userLog = (List<MobileUserBean>) request.getAttribute("userLog");
			%>

			<aui:form id="searchForm" method="post"
				action="<%=searchMobUserRecord %>" class="form-horizontal">
				<aui:fieldset-group markupView="lexicon">
					<!-- <aui:fieldset label="Mobile User Details:"> -->
						<aui:row>
							<aui:col width="50">
								<aui:input type="date" name="searchFrom" label="Start Date"/>
							</aui:col>
							<aui:col width="50">
								<aui:input type="date" label="End date" name="searchTo" cssClass="form-control" />
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="50">
								<aui:input type="text" label="CPF No" name="cpfNo" id="cpfNo"
									cssClass="form-control" />
							</aui:col>
							<aui:col width="50">
								<aui:input type="text" label="Name" name="name" id="name"
									cssClass="form-control" />
							</aui:col>
						</aui:row>
						<aui:button-row>
							<aui:button name="submitButton" type="submit" value="Submit"
								style="background-color: #8c000d;color: #fff !important;" />
						</aui:button-row>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
			<% if(userLog != null){ %>
			<table id="mobileAdmin" class="table table-striped"
				style="width: 100%" cellspacing="0">
				<thead>
					<tr>
						<th>User Name</th>
						<th>CPF No</th>
						<th>IMEI No</th>
						<th>Login Time</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 0;
					
					SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
						for (MobileUserBean logUser : userLog) {
							i++;
					%>
					<tr>
						<td><%=logUser.getUserName()%></td>
						<td><%=logUser.getCpfNo()%></td>
						<td><%= logUser.getImeiNo() %></td>
						<td><%=sdf.format(sdf1.parse(logUser.getLoginDate().toString()))  %></td>
					</tr>
					<% } %>
				</tbody>
			</table>
			<% } %>
		</div>
	</div>
</div>
<script>
			 $(document).ready(function() {
				 var table = $('#mobileAdmin').DataTable( {
					 lengthChange: false,bFilter: false, bInfo: false
				    });
				}); 
			 </script>