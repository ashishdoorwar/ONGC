<%@ include file="/init.jsp" %>     
   <%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceFactory"%>
<%@page import="java.util.List"%>


<script language="JavaScript" src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript" src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript" src='<%=request.getContextPath()%>/js/Registration.js'></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("a#show-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeIn(300);
		})
		$("a#close-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeOut(300);
		})
	})
	function addUpload(divName,fileObj) {
			var intVal=parseInt(divName);
       if(intVal<5){
		$("#file" + (intVal+1)).show();
       $("#r" + (intVal)).show();
	   }
	   showFileSize(divName,fileObj)
	}
	
	
function showFileSize(divName,fileObj) {
    var input, file;

    // (Can't use `typeof FileReader === "function"` because apparently
    // it comes back as "object" on some browsers. So just see if it's there
    // at all.)
     input = fileObj;
    if (!window.FileReader) {
        document.getElementById('fSize'+divName).innerHTML=" [The file API isn't supported on this browser yet.]";
        return;
    }
   
    else {
        file = input.files[0];
        document.getElementById('fSize'+divName).innerHTML=" ["+ ((file.size)/(1024*1024)).toFixed(3) +"  MB]"
        //bodyAppend("p", "File " + file.name + " is " + file.size + " bytes in size");
    }
}	
	
	 function addMoreFields(fld) {
	 fld.disabled=true;
     var table = document.getElementById("filetable");
        var div = document.createElement('div');
   table.appendChild(div);
     
  div.id="stf"+(tbno+1);
    
  div.addClass="form-item";
    
     div.innerHTML = '<label>File :</label> <input type="file" id="'+(tbno+1)+'" name="dataFile" onchange="addMoreFields(this)" ><span id="rtf'+(tbno+1)+'"><span>';

    document.getElementById("rtf"+fld.id).innerHTML='<a href ="javascript:deleteFields('+tbno+')">remove</a>';

tbno++;
}
 
var tbno=1;
 function deleteFields(fid) {
	
/*	 var table = document.getElementById("filetable");
div=document.getElementById("stf"+fid);
             table.removeChild(div);
			 */
$("#file" + fid).hide();
       
     } 
		
	
	
</script>

<script>
function backMonthlyReportpage()
{
  document.backtomonth.submit();
}
function backPeriodicReportpage()
{
  document.backtodatewise.submit();
}
</script>
<s:form action="reportComplaintMonthly" method="post" name="backtomonth" theme="simple">
<s:hidden name="month" id="month"/>
<s:hidden name="year" id="year"/>

</s:form>
<s:form action="reportComplaintDateWise" method="post" name="backtodatewise" theme="simple">
<s:hidden name="startDate" />
<s:hidden name="endDate" />

</s:form>

 <body>
  <s:set name="theme" value="'simple'" scope="page" />
  
  
   <header>
   <%@ include file="/admin/header.jsp" %>
    </header>
    <!--header end-->
    
    
    <!--Start Main Contant section-->
      <div class="container">
       <div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
<%@ include file="/admin/menu.jsp" %>
      	</div>
       
       
       
     
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-9">
      





<%
/* ValueStack vs = ActionContext.getContext().getValueStack(); */
List country = VigilanceFactory.getContentManagementInstance().getContentByType(VigilanceConstant.VIGILANCE_CONTENT_TYPE_LOCATION);
/* vs.set("country", country); */
 %>

<s:form cssClass="webform" action="registerComplaint" onsubmit="return complaintFormValidation(this);" enctype="multipart/form-data" method="POST">

 <!-- To show message start -->
	<div id="errorMsg" class="errors"></div>
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


		<div class="form-item">
		<div class="row">
			<div class="col-xs-6"><h1 class="mb5 mt0">Complaint Details 
<!--
<s:a action="reportComplaintDateWise" title="" cssClass="pull-right   btn-sm" >
      <big><i class="fa fa-angle-double-left fa-lg" aria-hidden="true"></i> Back</big> </s:a>

-->




</h1></div>
<div class="col-xs-6">

<s:if test="month">
	  <span title="Back" class="pull-right back-btn" href="" onclick="backMonthlyReportpage()" align="right"> <i class="fa fa-angle-double-left fa-lg" aria-hidden="true"></i> Back</span>
	</s:if>
		<s:else>
	  <span title="Back" class="pull-right  back-btn" href="" onclick="backPeriodicReportpage()" id="backPridRpt" align="right" > <i class="fa fa-angle-double-left fa-lg" aria-hidden="true"></i> Back</span>
	  </s:else>
</div>

		</div>
		
	<div class="bg-grey bdr-orange search-form">

			<div id="fullform">
    
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
		       
			  <tbody>
			   <tr>
		        <td width="25%"><b>Complaint No:</b></td>
				<td width="20%">&nbsp;<s:property value="complaint.complaintActNo" /></td>
		        <td width="20%"><b>Complaint Date:</b>&nbsp;</td>
				<td width="35%"><s:date name="complaint.complaintDate" format="dd-MM-yyyy" /></td>
		        
		        </tr>
				
				
				
				
				
		        <tr>
		        <td ><b>Complainant Name:</b></td>
		        
		      <td><b></b>&nbsp;<s:property value="complaint.complainBy.title" />&nbsp;<s:property value="complaint.complainBy.firstName" />&nbsp; 
		        <s:property value="Complain_By.middleName" />&nbsp; <s:property value="Complain_By.lastName" /></td>
		       
		        <td><b>Phone No:</b>&nbsp;</td>
		       
		       <td><s:property value="complaint.complainBy.mobile" /></td>
		        </tr>
		      <tr>
			  
			 <td><b>Email id:</b></td>  
			 
			 <td>&nbsp;<s:property value="complaint.complainBy.emailId" /></td>
			 
             <td> <b>Address:</b>&nbsp;</td>
		 <td><s:property value="complaint.complainBy.firstAddress" />  &nbsp;<s:property value="complaint.complainBy.secondAddress" /></td>  

        
		        </tr>
		        <tr>
		        <td><b>Country:</b></td>
				<td>&nbsp;<s:property value="complaint.complainBy.country" /></td>
		        <td><b>State:</b></td>
				<td><s:property value="complaint.complainBy.state" /></td>
		        </tr>
		         <tr>
		         <td colspan="4">
		        <b>Complaint Against::</b>&nbsp;
		        </td>
		        </tr>
		      <tr>
		         <td>
		        <b>Name:</b></td>
				  <td><s:property value="complaint.complaintAgainst" /></td>
		        
		        <td>
		        <b>Address:</b>
				</td>
				<td><s:property value="complaint.workCentre" />
		        </td>
				</tr>
				<tr>
		        <td><b>Department:</b></td>
				<td><s:property value="complaint.departmetn" /></td>
		        <td><b>Designation:</b> </td>
				<td><s:property value="complaint.designation" /></td>
		        </tr>
		        
		       
	      

	        <tr>
		    <td colspan="4" class="bold"><b>Complaint  Details :</b></td>
		    
	      </tr>
	       <tr>
		    <td width="25%" class="bold"><b>Subject:</b></td>
		    <td colspan="3" ><p> <s:property value="complaint.complaintSubject" /></p></td>
	      </tr>
		  <tr>
		    <td  class="bold">Allegations/Complaint Details:</td>
		    <td colspan="3" ><p><s:property value="complaint.complaintDetail" /> </p></td>
	      </tr>
	       <tr>
		    <td class="bold">Attachment:</td>
		    <td colspan="3" ><p><span>
		       <%
boolean chkFlag=false;
%>
		    <s:if test="%{complaint.attachmentList.isEmpty()}">
		     </s:if>
		        <s:else>
		        <s:iterator value="complaint.attachmentList">
			<%
            chkFlag=true;
            %>
            <s:property value="fileName" />
             
		        <a href="<%= request.getContextPath() %>/admin/downloadJsp.jsp?attachmentId=<s:property value="attachmentId"/>"><s:property value="fileName" /></a>
				&nbsp;&nbsp;
		        </s:iterator>
		        </s:else>
				<%
				if(!chkFlag)
				out.println("NA");
				%>
		        </span></p></td>
	      </tr>
	  
	
		
		 
		  </tbody>
	    </table>
		  

  <br/> <br/>
	
   
		</div>
		</div>
		</s:form>

	<!-- main content end-->
	



  <div class="row">
    <div class="col-md-12 text-center">
	<button class="btn btn-primary "> <a href="<%= request.getContextPath() %>/admin/printJsp.jsp?compId=<s:property value="complaint.complaintNo"/>" download target="_blank"> <span class="glyphicon glyphicon-print"></span> Print</a></button>
	</div>
		</div>

      
    
       </div>
   </div>
   </div>
 


           <footer>
           <%@ include file="/admin/footer.jsp" %>
         </footer>
 
   </body>
</html>