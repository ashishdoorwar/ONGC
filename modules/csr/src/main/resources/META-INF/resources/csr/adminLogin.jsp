<portlet:renderURL var="login">
	<portlet:param name="mvcRenderCommandName" value="login" />
</portlet:renderURL>

<div class="admin-login-main">
	<div class="admin-login-box">

		<div class="row">
			<div class="col-md-12 wd620-100">
				<div class="admin-logo-box">
					<div class="admin-ongc-logo-login">
						<img src='<%=request.getContextPath()%>/images/ongc-logo.png'
							alt="ONGC Logo" />
					</div>

					<div class="admin-log-tx">CSR Portal</div>
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
						
						<aui:button-row cssClass="text-right m-2 ">
							<aui:button cssClass="btn" value="login" type="submit" />
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
	var inp = document.getElementById("userId");
	inp.focus();
	/* function myFunction() {
		var element = document.getElementById("content");
		element.classList.add("login-body");
	}
	myFunction(); */
</script>
