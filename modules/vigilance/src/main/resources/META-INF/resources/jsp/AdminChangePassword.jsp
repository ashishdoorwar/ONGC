<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<s:form action="vigilanceAdminLogin" method="post" name="timeout"
	theme="simple">
</s:form>
<%
	if (session.getAttribute(VigilanceConstant.VIGILANCE_ADMINUSER) == null) {
%>
<%
	}
%>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js?ppp=0909'></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#oldPassword").focus();
		$("a#show-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeIn(300);
		})
		$("a#close-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeOut(300);
		})
	})
</script>


<s:set name="theme" value="'simple'" scope="page" />




<header>
	<%@ include file="/admin/header.jsp"%>
</header>
<!--header end-->


<!--Start Main Contant section-->

<div class="container" id="skip-main-content">
	<div class="row">
		<!--left section start-->

		<div class="col-md-3">
			<%@ include file="/admin/menu.jsp"%>
		</div>




		<!--left section end-->

		<!--right section start-->

		<div class="col-md-9">
			<div class="form-item">
				<h1 class="mb5 mt0">Change Password</h1>
			</div>


			<div class="bg-grey bdr-orange search-form">




				<!--  <s:form cssClass="webform" action="adminChangePassDo" name="changepass_form"  >
	



	<div>
		<div class="form-item">
			<h1 class="mb5 mt0">Change Password</h1>
		</div>
		
		
	<div class="bg-grey bdr-orange search-form">
	
<div class="form-horizontal">
	 To show message start
	   <div id="errorMsg" class="errors">
	
	<s:if test="hasActionErrors()">
	      <s:actionerror/>
	
	</s:if>
	   </div>
	<s:if test="hasActionMessages()">
	   <div class="welcome">
	      <s:actionmessage/>
	   </div>
	</s:if>
	To show message end
	main content start
	  <div class="row">
	   <div class="col-md-8 ">
  <div class="form-group ">
			<label class="control-label col-sm-3 col-md-4">Old Password <span class="required" title="This field is required.">*</span></label>
			 <div class="col-sm-9 col-md-8">
			<s:password id="oldPassword" maxlength="14" cssClass="form-control" name="oldPassword"></s:password>
			</div>
			 </div>
 </div>
		
 </div>		
	
	 <div class="row ">	
	 
	 <div class="col-md-8 ">	
	 <div class="form-group ">
			<label class="control-label col-sm-3 col-md-4">New Password <span class="required" title="This field is required.">*</span></label>
			<div class="col-sm-9 col-md-8 ">
			
			<s:password id="password" maxlength="14" cssClass="form-control" name="newPassword"></s:password><a class="lnk" id="show-panel" href="javascript:void(0)" data-toggle="modal" data-target="#myModal"><b><u>Password Policy</u></b></a>
		</div>
	</div>	
	</div>
		</div>
	
		 <div class="row">
                <div class="col-md-8 ">
		 <div class="form-group ">
		 	
		 
			<label class="control-label col-sm-3 col-md-4">Confirm Password <span class="required" title="This field is required.">*</span></label>
			
			<div class="col-sm-9 col-md-8">
			<s:password id="confirmPass" maxlength="14" cssClass="form-control" name="confirmPasswrod"></s:password>
			</div>
			</div>
		</div>
	</div>	
	
	 <div class="row">
	<div class=" col-sm-6 text-right">
     <input type="button" name="tt" value="Submit" class="btn btn-primary" onclick="changePassValidation();" />
      <s:a id="cancelBtn" action="readComplaintList" cssClass="btn btn-primary" >Cancel</s:a>
  </div>
  </div>		
		
		</div>
	                     
		

    </div>
	
	
					<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Password Policy</h4>
      </div>
      <div class="modal-body">
        	<ol>
		<li>Password should be minimum 8 characters long and maximum 14 characters. </li>
		<li>Password must contain at least one character in upper case and one character in lower case.</li> 
		<li>Password should contain at least one number and one special character (!,@,$,#).</li>
		<li>Password must have alphabet in first position.</li>
		<li>Maximum identical consecutive characters in the password must not be more than 2.</li>
		<li>Password must not contain user id as the substring.</li>
	</ol>
      </div>
    
    </div>
    </div>
    </div>
	
		
	</div>
	main content end
</s:form> -->

				<liferay-ui:error key="Enter Old Password"
					message="Enter Old Password" />
				<liferay-ui:error key="Enter New Password"
					message="Enter New Password" />
				<liferay-ui:error key="Old Password Not Match"
					message="Old Password Not Match" />
				<liferay-ui:error key="error" message="error" />
				<portlet:actionURL var="adminChangePassDo" name="adminChangePassDo">

				</portlet:actionURL>

				<aui:form action="${adminChangePassDo}" name="fm" method="post">
					<aui:input name="oldPassword" label="Old Password" type="text">
						<aui:validator name="required" />
					</aui:input>
					<aui:input name="newPassword" label="New Password" type="text">
						<aui:validator name="required" />
						<aui:validator name="maxLength">14</aui:validator>
								<aui:validator name="minLength">8</aui:validator>
							<aui:validator
								errorMessage="Password must contain at least one character in upper
								case and one character in lower case.
								Password should contain at least one number and one
								special character (!,@,$,#)."
								name="custom">
								 function(val, fieldNode, ruleValue) {
								return /[a-z]/.test(val) && /[A-Z]/.test(val) &&/[0-9]/.test(val) && /[!,@,$,#]/.test(val);
								}
								 
								</aui:validator>
								<aui:validator
								errorMessage="Password must have alphabet in first position."
								name="custom">
								 function(val, fieldNode, ruleValue) {
								 var character= val.charAt(0)
								return /[a-z]/.test(character) || /[A-Z]/.test(character);
								}
								 
								</aui:validator>
					</aui:input>
					<a class="lnk" id="show-panel" href="javascript:void(0)" data-toggle="modal" data-target="#myModal"><b><u>Password Policy</u></b></a>
					<aui:input name="confirmPassword" label="Confirm Password"
						type="text">
						<aui:validator name="required" />
						<aui:validator name="equalTo">'#<portlet:namespace />newPassword'</aui:validator>
					</aui:input>
					<aui:button-row cssClass="text-center m-2 ">
						<aui:button cssClass="btn" value="Submit" type="submit" />
					</aui:button-row>
				</aui:form>

				<!-- /lightbox-panel -->
				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">

						Modal content
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Password Policy</h4>
							</div>
							<div class="modal-body">
								<ol>
									<li>Password should be minimum 8 characters long and
										maximum 14 characters.</li>
									<li>Password must contain at least one character in upper
										case and one character in lower case.</li>
									<li>Password should contain at least one number and one
										special character (!,@,$,#).</li>
									<li>Password must have alphabet in first position.</li>
									<li>Maximum identical consecutive characters in the
										password must not be more than 2.</li>
									<li>Password must not contain user id as the substring.</li>
								</ol>
							</div>

						</div>
					</div>
				</div>
				<div id="lightbox"></div>
				<!-- /lightbox -->



			</div>
		</div>
	</div>
</div>
<footer>
	<%@ include file="/admin/footer.jsp"%>
</footer>
<!--End Footer-->


