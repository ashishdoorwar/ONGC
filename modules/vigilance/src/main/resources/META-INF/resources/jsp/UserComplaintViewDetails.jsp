<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl"%>
<%@page import="com.ongc.liferay.vigilance.dao.ComplaintManagementDao"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.util.ONGCUtil"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceUser"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceFactory"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp" %>
<%@page import="java.util.List"%>
<portlet:defineObjects />
	 <link rel="stylesheet"  href='<%=request.getContextPath()%>/css/datepicker.css")%>'  type="text/css">


<script language="JavaScript" src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript" src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript" src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript" src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#complaintSubject").focus();
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
 <body>
 <%
 VigilanceComplaint complaint=null;
 String complaintNo=ParamUtil.getString(request, "complaintNo");
 ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
 System.out.println("In jsp --------------"+complaintNo);
 complaint=comp.getComplaintDetails(Integer.parseInt(complaintNo));
 
 
 %>
  <s:set name="theme" value="'simple'" scope="page" />
  
  
   <header>
  <%@ include file="/admintemplate/header.jsp" %>
    </header>
    <!--header end-->
    
    
    <!--Start Main Contant section-->
      <div class="container" id="skip-main-content">
       <div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
	<%@ include file="/admintemplate/menu.jsp" %>
      	</div>
       
       
       
     
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-9">
      





<%
List country = VigilanceFactory.getContentManagementInstance().getContentByType(VigilanceConstant.VIGILANCE_CONTENT_TYPE_LOCATION);
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
  <%
  
  String emailId=ParamUtil.getString(request, "emailId");
  //System.out.println(emailId);
VigilanceUser userDetail =VigilanceFactory.getUserServiceInstance().getUserByEmailId(emailId);
if(ONGCUtil.isAdmin(userDetail.getEmailId()) )
{
%>
			<% }%>

		<div class="form-item">
			<h1 class="mb5 mt0">Complaint Status</h1>
		</div>
		
	<div class="bg-grey bdr-orange search-form">

			<div id="fullform">
    
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
		       
			  <tbody>
			   <tr>
		        <td width="25%"><b>Complaint No:</b></td>
				<td width="20%">&nbsp;<%=complaint.getComplaintActNo() %></td>
		        <td width="20%"><b>Complaint Date:</b>&nbsp;</td>
				<td width="35%"><%=complaint.getComplaintDate() %></td>
		        </tr>
		        <tr>
		        <td ><b>Complainant Name:</b></td>
		        
		      <td><b></b>&nbsp;
		      <%=complaint.getComplainBy().getTitle()==null?"":complaint.getComplainBy().getTitle() %>&nbsp;<%=complaint.getComplainBy().getFirstName()==null?"" :complaint.getComplainBy().getFirstName()%>&nbsp; 
		       <%=complaint.getComplainBy().getMiddleName()==null? "" :complaint.getComplainBy().getMiddleName()%> &nbsp;<%=complaint.getComplainBy().getLastName().equals("null")?"":complaint.getComplainBy().getLastName() %> </td>
		       
		        <td><b>Phone No:</b>&nbsp;</td>
		       
		       <td><%=complaint.getComplainBy().getMobile() %></td>
		        </tr>
		      <tr>
			  
			 <td><b>Email id:</b></td>  
			 
			 <td>&nbsp;<%=complaint.getComplainBy().getEmailId() %></td>
			 
             <td> <b>Address:</b>&nbsp;</td>
		 <td><%=complaint.getComplainBy().getFirstAddress()%> &nbsp;<%=complaint.getComplainBy().getSecondAddress() %></td>  

        
		        </tr>
		        <tr>
		        <td><b>Country:</b></td>
				<td>&nbsp;<%=complaint.getComplainBy().getCountry() %></td>
		        <td><b>State:</b></td>
				<td><%=complaint.getComplainBy().getState() %></td>
		        </tr>
		         <tr>
		         <td colspan="4">
		        <b>Complaint Against::</b>&nbsp;
		        </td>
		        </tr>
		      <tr>
		         <td>
		        <b>Name:</b></td>
				  <td><%=complaint.getComplaintAgainst() %></td>
		        
		        <td>
		        <b>Address:</b>
				</td>
				<td><%=complaint.getWorkCentre() %>
		        </td>
				</tr>
				<tr>
		        <td><b>Department:</b></td>
				<td><%=complaint.getDepartmetn() %></td>
		        <td><b>Designation:</b> </td>
				<td><%=complaint.getDesignation() %></td>
		        </tr>
		        
		       
	      

	        <tr>
		    <td colspan="4" class="bold"><b>Complaint  Details :</b></td>
		    
	      </tr>
	       <tr>
		    <td width="25%" class="bold"><b>Subject:</b></td>
		    <td colspan="3" ><p> <%=complaint.getComplaintSubject() %></p></td>
	      </tr>
		  <tr>
		    <td  class="bold">Allegations/Complaint Details:</td>
		    <td colspan="3" ><p><%=complaint.getComplaintDetail() %></p></td>
	      </tr>
	       <tr>
		    <td class="bold">Attachment:</td>
		<%--     <td colspan="3" >
		    <% String ext =complaint.getAttachment().substring(complaint.getAttachment().lastIndexOf(".")); 
		    String complaintNO = String.valueOf(complaint.getComplaintActNo()).replace("VIG0", "");
		    String fileName = complaint.getAttachment().substring(0, complaint.getAttachment().indexOf("."));
		    %>
		    <% if(ext.equals(".jpg")){ %>
		    <img class="img-thumbnail" style="max-height: 150px;" src="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaint/imageComplaint?fileName=<%= complaintNO %>">
		    <%} else if(ext.equals(".pdf")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintPdf/complaintPdf?pdfname=<%= complaintNO %>.pdf" target="_blank">Download</a>
		     <%} else{%> 
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintNO %><%=ext %>" download target="_blank">Download</a>
		    <% }%>
		    </td> --%>
		     <td colspan="3" ><p><span>
<% if(complaint.getAttachmentList().isEmpty()){ %>
<% for(ComplaintAttachment complaintAttachment:complaint.getAttachmentList()){ 
String ext =complaintAttachment.getFileName().substring(complaintAttachment.getFileName().lastIndexOf(".")); 
String fileName = complaintAttachment.getFileName().substring(0, complaintAttachment.getFileName().indexOf("."));%>
		    <% if(ext.equals(".jpg")){ %>
		    <a  href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaint/imageComplaint?fileName=<%= fileName %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <%} else if(ext.equals(".pdf")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintPdf/complaintPdf?pdfname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		     <%} else{%> 
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } } } else{%>NA<%} %>
		       <%-- <%
boolean chkFlag=false;
%>
		    <s:if test="%{complaint.attachmentList.isEmpty()}">
		     </s:if>
		        <s:else>
		        <s:iterator value="complaint.attachmentList">
		        
			<%
            chkFlag=true;
            %>
		        <a href="<%= request.getContextPath() %>/admin/downloadJsp.jsp?attachmentId=<s:property value="attachmentId"/>"><s:property value="fileName" /></a>
				&nbsp;&nbsp;
		        </s:iterator>
		        </s:else>
				<%
				if(!chkFlag)
				out.println("NA");
				%> --%>
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
	 <button class="btn btn-primary "> <a href="<%= renderRequest.getContextPath() %>/admin/printJsp.jsp?compId=<%= complaint.getComplaintNo() %>"> <span class="glyphicon glyphicon-print"></span> Print</a></button> 
	</div>
		</div>   
       </div>
   </div>
   </div>
           <footer>
			   <%@ include file="/admintemplate/footer.jsp" %>
         </footer>
 
   </body>
</html>