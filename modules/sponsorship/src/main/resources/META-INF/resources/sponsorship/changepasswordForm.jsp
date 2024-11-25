<%@page import="javax.portlet.PortletRequest"%>
<%@page
	import="com.liferay.portal.kernel.management.jmx.GetAttributesAction"%>
<%@page import="javax.portlet.PortletSession"%>
<%@page import="com.liferay.portal.kernel.util.CookieUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.List"%>
<!-- Favicon -->

<%@ include file="/init.jsp"%>

<script>
	
		
</script>
<style>
.calender img {
	width: 20px;
	height: 20px;
	position: absolute;
	right: 30px;
	top: 7px;
}
</style>


<div class="container">
	<%@ include file="/sponsorship/header.jsp"%>


	<h1>Change Password Form</h1>


	<!-- 	<s:form action="changePasswordAdction" method="POST"  cssClass="form-horizontal mt20 spn-form" theme="simple">
		


		<div class="row">
			<div class="col-md-12">

				<div class="welcome">
					<s:if test="hasActionMessages()">
						<div class="alert alert-success">
							<s:actionmessage cssClass="actionMessage" />
						</div>
					</s:if>
					<s:if test="hasActionErrors()">
						<div class="alert alert-danger">
							<s:actionerror cssClass="actionMessage" />
						</div>
					</s:if>
				</div>
			</div>
		</div>
		
		
		<div class="form-group ">
			<label class="control-label col-sm-3 col-md-4"> Old Password<span class="mand">*</span>
			</label>
			<div class="col-sm-9 col-md-8">
				 <s:password id="oldPassword"  cssClass="form-control" name="cpBeans.oldPassword" required="required"/> 
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-sm-3 col-md-4"> New Password
				<span class="mand">*</span>
			</label>
			<div class="col-sm-9 col-md-8">
				 <s:password id="newPassword"  cssClass="form-control"  name="cpBeans.newPassword" required="required"/> 
			</div>
		</div>
		<div class="form-group ">
			<label class="control-label col-sm-3 col-md-4"> Confirm Password<span class="mand">*</span></label>
			<div class="col-sm-9 col-md-8">
				<s:password id="confirmPassword" cssClass="form-control" name="cpBeans.confirmPassword" required="required"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-sm-offset-2 col-sm-10 text-right">
				<s:submit cssClass="btn btn-primary" value="Change Password" id="submitForm" />
			</div>
		</div>
		</s:form> -->
		 <liferay-ui:error key="error" message="Illegal argument found" /> 
	<portlet:actionURL var="changePassword" name="change_password">
<portlet:param name="mvcPath" value="/sponsorship/changepasswordForm.jsp"  />
	</portlet:actionURL>

	<aui:form action="${changePassword}" name="fm" method="post">
		<aui:input name="loginId" value="<%=loginId %>" type="hidden" />
		<aui:input name="oldPassword" label="Old Password" type="text">
			<aui:validator name="required" />
		</aui:input>
		<aui:input name="newPassword" label="New Password" type="text">
			<aui:validator name="required" />
		</aui:input>
		<aui:input name="confirmPassword" label="Confirm Password" type="text">
			<aui:validator name="required" />
		</aui:input>
		<aui:button-row cssClass="m-2 ">
			<aui:button cssClass="btn btn-primary" value="Submit" type="submit" />
		</aui:button-row>
	</aui:form>



</div>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas Corporation
			Limited</div>
	</div>
</footer>
<div class="loader">
	<div class="load-icon">
		<i class="glyphicon glyphicon-refresh"></i> <span>Loading...</span>
	</div>
</div>
<script>
	$(document).ready(function() {
		
		$('#submitForm').click(function() {
			$('.loader').show();
		});
		
		
		});
	
</script>