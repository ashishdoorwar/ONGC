<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>

 <script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script> 

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
	<%@ include file="/admintemplate/logoutHeader.jsp"%>
</header>
<!--header end-->


<!--Start Main Contant section-->
<div class="container" id="skip-main-content">



	<!--right section start-->


	<div class="col-md-12">
		<h1 class="mb5 mt0">Forgot Password</h1>
		<div class="bdr-orange search-form">

			<div class="form-item"></div>



			</br>
			<s:set name="theme" value="'simple'" scope="page" />
			<portlet:renderURL var="cancel">
				<portlet:param name="mvcPath"
					value="/jsp/userLogin.jsp" />
			</portlet:renderURL>
			<portlet:renderURL var="forgotPassword" >
				<portlet:param name="mvcRenderCommandName" value="forgot_password"/>
			</portlet:renderURL>
			<liferay-ui:error key="user_error" message="Username doesn't exists" />
			<liferay-ui:error key="question_error" message="Security question does not Match" />
			<liferay-ui:error key="answer_error" message="Security Answer does not Match" />
			<aui:form action="${forgotPassword}" name="fm" method="post">
				<aui:row>
					<aui:col width="50">
						<aui:input name="username" label="Username" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:select name="securityQuestion" label="Security Question">

							<aui:option value="">Select</aui:option>
							<aui:option value="33">What is the colour of your vehicle ?</aui:option>
							<aui:option value="34">What is your place of Birth ?</aui:option>
							<aui:option value="35">What is your Maiden Name ?</aui:option>
							<aui:option value="36">What is your favourite colour ?</aui:option>
							<aui:option value="37">What is your Favourite Dish ?</aui:option>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input name="securityAnswer" label="Security Answer"
							type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
				</aui:row>
				<aui:button-row cssClass="text-center m-2 ">
					<aui:button cssClass="btn" value="Submit" type="submit" />
					
					<a class="btn text-white" href="<%=cancel %>">Cancel</a>
				</aui:button-row>
			</aui:form>

		</div>

	</div>
	<!-- /lightbox-panel -->

	<div id="lightbox"></div>
	<!-- /lightbox -->




	<br /> <br />

	<!-- 
   <div class="col-md-12"><div id="lightbox-panel">
   <br/>
	<!--  <h1 class="mb5 mt0">Password Policy</h1> 
		<div class="bg-grey pn">
		<br/>
	<ol>
		<li>Password should be minimum 8 characters long and maximum 14 characters. </li>
		<li>Password must contain at least one character in upper case and one character in lower case.</li> 
		<li>Password should contain at least one number and one special character (!,@,$,#).</li>
		<li>Password must have alphabet in first position.</li>
		<li>Maximum identical consecutive characters in the password must not be more than 2.</li>
		<li>Password must not contain user id as the substring.</li>
	</ol>   
</div>
	<p align="center">
		<a id="close-panel" href="#" class="close"><b>Close</b>
		</a>
	</p>  -->

</div>

<footer>
	<%@ include file="/admintemplate/footer.jsp"%>
</footer>
<!--End Footer-->
