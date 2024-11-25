<%@page import="com.ongc.liferay.sponsorship.model.User"%>
<%@ include file="/init.jsp"%>
<!-- Favicon -->
<%@page import="java.util.List"%>



<s:set name="theme" value="'simple'" scope="page" />
<s:set name="users" value="userlist" scope="request" />

<%
	List<User> users = (List<User>) request.getAttribute("userList");
%>

<div class="container">

	<%@ include file="/sponsorship/header.jsp"%>

	<main> <!-- 	<s:form action="diplayAllUserList" cssClass="form-horizontal pl20 pr20 pb20 pt20">

			<div class="row input-daterange" id="datepicker">
				<div class="col-md-12">
					<div class="form-group">
						<div class="col-sm-9 col-md-3">
							<label class="control-label ml5">CPF Number</label>
							<s:textfield name="filterUser.cpfNo" cssClass="smalltext form-control" id="cpfNo" />
						</div>

						<div class="col-sm-9 col-md-3">
							<label class="control-label ml5">Location</label>
							<s:textfield name="filterUser.location" cssClass="smalltext form-control" id="location" />
						</div>

						
					</div>
				</div>
			</div>


			<div class="row">
				<div class=" col-sm-12 text-center">
					<s:submit cssClass="btn btn-primary" value="Search" title="Search"></s:submit>
				</div>
			</div>


		</s:form> --> <portlet:renderURL var="searchUser">
		<portlet:param name="mvcRenderCommandName" value="search_user" />
		<portlet:renderURL var="createUserForm">
	<portlet:param name="mvcPath" value="/sponsorship/createuserForm.jsp" />
</portlet:renderURL>
	</portlet:renderURL> <aui:form action="${searchUser}" name="fm" method="post">
		<aui:row>
			<aui:col width="50">
				<aui:input name="cpfNo" label="CPF Number" type="text" />
			</aui:col>
			<aui:col width="50">
				<aui:input name="location" label="Location" type="text" />
			</aui:col>
		</aui:row>
		<aui:button-row cssClass="text-center m-2 ">
			<aui:button cssClass="btn" value="Search" type="submit" />
		</aui:button-row>
	</aui:form>

	<h1>List of Users</h1>
	<div align="right">
		<a href="${createUserForm}">
			<span class="fa fa-plus-circle"></span>Add
		</a>
	</div>
	<liferay-ui:success key="success" message="Password reset successfully!" />
	<div id="story">
		<div class="welcome">
			<!-- <s:if test="hasActionMessages()">
				<div class="alert alert-success">
					<s:actionmessage cssClass="actionMessage" />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="alert alert-danger">
					<s:actionerror cssClass="actionMessage" />
				</div>
			</s:if> -->
		</div>
		<div class="clearfix"></div>
		<div class="table-responsive">
			<%-- <display:table name="users" id="item" pagesize="10" cellspacing="0"
				cellpadding="0" class="table table-bordered table-striped">
				<display:column title="Sr No"><%=pageContext.getAttribute("item_rowNum")%></display:column>
				<display:column property="userName" title="User Name" />
				<display:column property="emailId" title="Email Id" />
				<display:column	property="designation" title="Designation" />
				<display:column property="location" title="Location" />
				<display:column property="mobileNo" title="Mobile No" />
				<display:column property="loginId" title="Login Id" />
				<display:column property="active" title="Active" />
				<display:column title=" ">
					<s:a id="" action="openUserProfile">
						<s:param name="user.loginId">${item.loginId}</s:param>
					Update 
					</s:a>

				</display:column>
				<display:column title=" ">
					<s:a id="" action="resetPasswordAction">
						<s:param name="user.loginId">${item.loginId}</s:param>
						 <span class="glyphicon glyphicon-repeat" title="Reset password"></span>
					</s:a>

				</display:column>
				

				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="paging.banner.first">
					<span class="pagelinks"><a href="{1}">First</a><a href="{2}">
							<< </a>{0}<a href="{3}"> >> </a><a href="{4}">Last</a> </span>
				</display:setProperty>
				<display:setProperty name="paging.banner.last">
					<span class="pagelinks"><a href="{1}">First</a><a href="{2}">
							<< </a>{0}<a href="{3}"> >> </a><a href="{4}">Last</a> </span>
				</display:setProperty>
			</display:table>
 --%>

			<table id="userData" class="table table-bordered">
				<thead>
					<tr>
						<th>User Name</th>
						<th>Email Id</th>
						<th>Designation</th>
						<th>Location</th>
						<th>Mobile No</th>
						<th>Login Id</th>
						<th>Active</th>
						<th>Reset Password</th>
					</tr>
					</thead>
					<%
						for (User userData : users) {
					%>
					<tr>
						<td><%=userData.getUserName()%></td>
						<td><%=userData.getEmailId()%></td>
						<td><%=userData.getDesignation()%></td>
						<td><%=userData.getLocation()%></td>
						<td><%=userData.getMobileNo()%></td>
						<td><%=userData.getLoginId()%></td>
						<td><%=userData.getActive()%></td>
						<portlet:renderURL var="resetPassword">
							<portlet:param name="mvcRenderCommandName" value="reset_password" />
							<portlet:param name="loginId" value="<%=userData.getLoginId()%>" />
						</portlet:renderURL>
						<td><a href="${resetPassword}"><span
								class="fa fa-repeat" title="Reset password"></span></a></td>

					</tr>
					<%
						}
					%>
				
			</table>

		</div>
		<br />
	</div>
	</main>




</div>
<!------------footer start------------>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas Corporation
			Limited</div>
	</div>
</footer>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#userData').DataTable({
			lengthChange: false,bFilter: false, bInfo: false

		});
	});
</script>