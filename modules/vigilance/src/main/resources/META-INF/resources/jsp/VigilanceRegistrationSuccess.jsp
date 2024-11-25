<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceUser"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<script type="text/javascript">

window.onload = function() {
	document.getElementById("compOtp").focus();
}
</script>
 
  <s:set name="theme" value="'simple'" scope="page" />

  <%
VigilanceUser user =(VigilanceUser)request.getSession(true).getAttribute(VigilanceConstant.VIGILANCE_USER);
String type="";
if(!user.getMobile().substring(0, 2).equals("91"))
	type="email id";
else
	type="mobile number";		

 %>

	
  
  
   <header>
  <jsp:include page="template/header2.jsp"></jsp:include>
    </header>
    <!--header end-->
    
    
    <!--Start Main Contant section-->
      <div class="container">
       <div class="row">
    <!--left section start-->
 
       
       
       
     
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-12">
      





	<s:set name="theme" value="'simple'" scope="page" />
<s:form cssClass="webform" name="regenarateOtp" action="registerRegenerateOTP">
</s:form>
	<s:form cssClass="webform" action="registrationOTP">
		<s:hidden name="user.emailId"></s:hidden>
		<s:hidden name="user.mobile"></s:hidden>

 
	<div>
		<div class="form-item">
			<h1 class="mb5 mt0">Registration complete using OTP</h1>
		</div>
		
		
	<div class="bg-grey bdr-orange search-form">
	 <div class="row">
           <!-- To show message start -->
	<s:if test="hasActionErrors()">
	   <div id="errorDiv" class="errors">
	      <s:actionerror/>
	   </div>
	</s:if>
	
	<s:if test="hasActionMessages()">
	   <div class="welcome">
	      <s:actionmessage/>
	   </div>
	</s:if>
	<!-- To show message end -->
	<!-- main content start-->
 </div>
<div><b>OTP has been sent on your registered <%=type %>. Please fill OTP to complete your registration</b><br></div>
				<br>
				<div class="row">

				</div>
				<div class="row">
			<div class="col-md-3">
				<div class="row">
			<div  class="form-item">
				<label class="col-md-4">OTP:</label>
				<div class="col-md-8">
				<s:password label="OTP" id="regOtp" cssClass="form-control col-md-2" maxlength="6" name="newOtp" ></s:password>
			</div>
			</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-3">
			<div class="form-item">
				<div><span onclick="document.regenarateOtp.submit();" style="color: blue;font-weight: bold;cursor:pointer">Click here </span> for re-generate OTP</div>
			</div>
			</div>
			</div>
			
				
			</div>
		
			<div class="row">
			<div class="col-md-3 col-md-offset-1">
			<div class="form-item">
				<s:submit cssClass="btn btn-primary"></s:submit>
			</div></div></div>
			
			<br/><br/>
			
		</div>
		</div>
		</s:form>
	</div>
	<!-- main content end-->
	





      
      

       </div>
   </div>
     
           <footer>
			   <jsp:include page="template/footer.jsp"></jsp:include>
         </footer>
    <!--End Footer-->