<%@ include file="/init.jsp" %>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceFactory"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceUser"%>
<%@page import="java.util.List"%>

	 <link rel="stylesheet"  href='<%=request.getContextPath() %>/css/datepicker.css")%>'  type="text/css">


<script language="JavaScript" src='<%=request.getContextPath() %>/js/mrmsValidation.js'></script>
<script language="JavaScript" src='<%=request.getContextPath() %>/js/tripleencoding.js'></script>
<script language="JavaScript" src='<%=request.getContextPath() %>/js/Registration.js'></script>
<script language="JavaScript" src='<%=request.getContextPath() %>/js/DatePicker/jquery.1.4.2.js'></script>

<script type="text/javascript">  
    $(document).ready(function(){ 
     $("#oldPassword").focus(); 
     $("a#show-panel").click(function(){  
        $("#lightbox, #lightbox-panel").fadeIn(300);  
     })  
     $("a#close-panel").click(function(){  
         $("#lightbox, #lightbox-panel").fadeOut(300);  
     })  
    })  
</script>
 
  <s:set name="theme" value="'simple'" scope="page" />
<% VigilanceUser userData = (VigilanceUser)request.getAttribute("userData");
   List<VigilanceComplaint> list = VigilanceFactory.getComplaintServiceInstance().getComplaintByUserID(userData.getRegistrationId());
%> 

  	
  
   <header>
  <jsp:include page="/admintemplate/header.jsp"></jsp:include>
    </header>
    <!--header end-->
    
    
    <!--Start Main Contant section-->
      <div class="container">
       <div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
	<jsp:include page="/admintemplate/menu.jsp"></jsp:include>
      	</div>
       
       
       
     
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-9">
      



<s:set name="theme" value="'simple'" scope="page" />

<s:form cssClass="webform" action="changeUserPassword" name="changepass_form" >
	

 

	<div>
		<div class="form-item">
			<h1 class="mb5 mt0">Change Password</h1>
		</div>
		
		
	<div class="bg-grey bdr-orange search-form">
	
<div class="form-horizontal">
<!-- <div class="">
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
	</div>
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
			<div class="col-sm-9 col-md-8">
			
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
     
       <s:a id="cancelBtn" action="UserHome" cssClass="btn btn-primary" >Cancel</s:a>
  </div>
  </div>		 -->
  <liferay-ui:error key="Enter Old Password" message="Enter Old Password" />
  <liferay-ui:error key="Enter New Password" message="Enter New Password" />
  <liferay-ui:error key="Old Password Not Match" message="Old Password Not Match" />
  <liferay-ui:error key="error" message="error" />

  <% 
  	String loginId=(String)request.getAttribute("loginId");
  %>
  <portlet:actionURL var="changePassword" name="change_password">

	</portlet:actionURL>
  <aui:form action="${changePassword}" name="fm" method="post">
		<aui:input name="loginId" value="<%=loginId %>" type="hidden" />
		<aui:input name="oldPassword" label="Old Password" type="text" />
		<aui:input name="newPassword" label="New Password" type="text" />
		<aui:input name="confirmPassword" label="Confirm Password" type="text" />
		<aui:button-row cssClass="text-center m-2 ">
			<aui:button cssClass="btn" value="Submit" type="submit" />
			<aui:button cssClass="btn" value="Cancel" type="cancel" />
		</aui:button-row>
	</aui:form>
		
		</div>
		
		<!-- <div id="myModal" class="modal fade" role="dialog">
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
</div> -->
		
		

		
		
		
		
		</div>
		
	</div>
	<!-- main content end-->
</s:form>

<!-- /lightbox-panel -->

<div id="lightbox"></div>
<!-- /lightbox -->
      
      

       </div>
   </div>
       </div>
           <footer>
			   <jsp:include page="/admintemplate/footer.jsp"></jsp:include>
         </footer>
    <!--End Footer-->

    <script type="text/javascript" src="js/bootstrap-datepicker.js"></script> 
 <script>
   $(document).ready(function () {
                
                $('.input-daterange').datepicker({
                    todayBtn: "linked"
                });
        
            });
 </script>
