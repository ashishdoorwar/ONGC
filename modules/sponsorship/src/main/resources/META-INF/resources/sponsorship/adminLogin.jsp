
<%-- <link rel="stylesheet" type="text/css"
	href='<%=request.getContextPath()%>/css/style.css'>
<link rel="stylesheet" type="text/css"
	href='<%=request.getContextPath()%>/css/style-admin.css'> --%>
<portlet:renderURL var="login">
	<portlet:param name="mvcRenderCommandName" value="login" />
</portlet:renderURL>

<div class="admin-login-main">
	<div class="admin-login-box">

		<div class="row">
			<div class="col-md-12 wd620-100">
				<div class="admin-logo-box" style="position: relative;">
					<div class="admin-ongc-logo-login">
						<img src='<%=request.getContextPath()%>/images/ongc-logo.png'
							alt="ONGC Logo" />
					</div>

					<div class="admin-log-tx">Sponsorship Portal</div>
					<!-- <s:form action="adminLogin" autocomplete="off"
						cssClass="form-horizontal login-form mt20">
						<div class="form-group">
							<div class="welcome admin-msg">
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
							<label class="control-label col-sm-4" for="user">User Id:</label>
							<div class="col-sm-8">
								<s:textfield id="userId" cssClass="form-control"
									placeholder="Enter User Id" name="userid"></s:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="pwd">Password:</label>
							<div class="col-sm-8">
								<s:password id="password" name="password"
									cssClass="form-control" placeholder="Enter Password"></s:password>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10 text-right">
								<s:submit cssClass="btn btn-primary" value="Login" title="Login" />
							</div>
						</div>
					</s:form> -->
					<liferay-ui:error key="Enter valid UserName"
						message="Enter valid UserName" />
					<liferay-ui:error key="Enter valid Password"
						message="Enter valid Password" />
					<liferay-ui:error key="errorMsg" message="Invalid Captcha ." />
					<aui:form action="${login}" name="fm" method="post">
						<aui:input name="username" type="text" />
						<aui:input name="password" type="password" />
						<liferay-portlet:resourceURL var="captchaimgurl">
							<liferay-portlet:param name="captchaAct" value="image" />
						</liferay-portlet:resourceURL>
						<div class="capWrap">
							<div class="captchaArea">
								<div class="captchaTxt">
									<img id="captcha_img" src="${captchaimgurl}" />
								</div>
								<Button class="utlBtn" onclick="refreshCaptchFun()">
									<i class="fa fa-refresh" aria-hidden="true"></i>
								</Button>
								<Button class="utlBtn" onclick="playCaptchAudioFun()">
									<i class="fa fa-volume-up" aria-hidden="true"></i>
								</Button>
							</div>

							<input type="text" name="<portlet:namespace />captchText"
								placeholder="Enter captcha" maxlength="6" spellcheck="false"
								required>

							<div class="statusTxt"></div>
						</div>
						<liferay-portlet:resourceURL var="captchaaudiourl">
							<liferay-portlet:param name="captchaAct" value="audio" />
						</liferay-portlet:resourceURL>
						<aui:button-row cssClass="m-2 ">
							<aui:button cssClass="btn btn-primary" value="login"
								type="submit" />
						</aui:button-row>
					</aui:form>

				</div>
			</div>
		</div>
	</div>
</div>
<!-- 
<footer class="login-footer pos-ab bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas Corporation
			Limited</div>
	</div>
</footer>  -->


<script language="Javascript">
function playCaptchAudioFun(){
	   var xpath="${captchaaudiourl}&sks="+new Date();
	   var audio = new Audio(xpath);
	   audio.play();
}
function refreshCaptchFun(){

document.getElementById("captcha_img").src="${captchaimgurl}&stks="+new Date();
	   
}
	/* var inp = document.getElementById("userId");
	inp.focus(); */
	/* function myFunction() {
		var element = document.getElementById("content");
		element.classList.add("login-body");
	}
	myFunction(); */
</script>
