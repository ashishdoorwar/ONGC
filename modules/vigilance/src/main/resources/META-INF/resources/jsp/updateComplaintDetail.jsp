<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@ include file="/init.jsp"%>

<%
	VigilanceComplaint complaint = (VigilanceComplaint) request.getAttribute("complaintDtl");
	if (complaint == null) {

		complaint = new VigilanceComplaint();
	}
%>

<style>
.active {
	color: white;
	text-decoration: none;
	padding-left: 527px;
	cursor: pointer;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 0.9em;
	font-weight: 600;
}
</style>
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/Adminstyle.css' type="text/css">

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/complaintPagging.js'></script>


<script>
	var date;
	function resetHandler() {
		date = document.getElementById("startDate").value;
	}

	function resetForm() {
		setTimeout(function() {
			var startDate = document.getElementById("startDate").value;
			document.getElementById("startDate").value = date;
		}, 50);
		return true;
	}

	function formvalidation1() {
		var sapNo = document.getElementById("sapNo").value;

		var startDate = document.getElementById("startDate").value;

		var actionDrop = document.getElementById("actionDrop").value;
		var remarks = document.getElementById("remarks").value;
		if (sapNo == "") {
			document.getElementById("errorMsg").innerHTML = ("Please specify SAP No");
			document.getElementById("sapNo").focus();
			return false;
		}

		if (startDate == "") {
			document.getElementById("errorMsg").innerHTML = ("Please specify date");
			document.getElementById("startDate").focus();
			return false;
		}

		if (actionDrop == "-1") {
			document.getElementById("errorMsg").innerHTML = ("Please select a action");
			document.getElementById("actionDrop").focus();
			return false;
		}

		if (remarks != "") {
			document.getElementById("H_remarks").value = Base64
					.tripleEncoding(remarks);
		}

		document.getElementById("H_startDate").value = Base64
				.tripleEncoding(startDate);
		document.getElementById("action").value = Base64
				.tripleEncoding(actionDrop);
		document.getElementById("H_sapNo").value = Base64.tripleEncoding(sapNo);

		return true;
	}
	window.onload = function() {
		$(function() {

			$('#startDate').datepick();
			//$('#endDate').datepick();
			if (document.getElementById("startDate").value != "")
				date = document.getElementById("startDate").value;
		});

	};
</script>
<script>
function checkStatus(){
	
	var status= $('#<portlet:namespace/>action').val();
	if(status=="Others"){
		$("#statDiv").css("display", "block");
	}else{
		$("#statDiv").css("display", "none");
	}
}
</script>

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
			<!-- To show message start -->


			<!-- To show message end -->
			<!-- main content start-->


			<div class="form-item">
				<h1 class="mb5 mt0">
					Complaint No: &nbsp;
					<s:property value="complaint.complaintActNo" />
					<s:a action="updateComplaint" title=""
						cssClass="pull-right   btn-sm">
						<big><i class="fa fa-angle-double-left fa-lg"
							aria-hidden="true"></i> Back</big>
					</s:a>
				</h1>
				<%
					int flagDate = 0;
				%>
			</div>

			<div class="bg-grey bdr-orange search-form">
			
 <liferay-ui:error key="error" message="Something wrong, please contact your administrator" /> 
 
 <liferay-ui:success key="success" message="Complaint updated Successfully" /> 
<portlet:renderURL var="updateSAPComplaint">
	<portlet:param name="mvcRenderCommandName" value="updateSAPComplaint" />
</portlet:renderURL>
				<aui:form action="${updateSAPComplaint}" method="post"
					style="width:100%">
					<aui:row>
						<aui:col width="50">
							<aui:input name="complaintActNo" type="hidden" value="<%=complaint.getComplaintNo() %>"/>
							<aui:input label="ZVRMS/Disha File No." name="sapNo" type="text" required="true">
							<aui:validator name="maxLength">14</aui:validator>
							<aui:validator errorMessage="14 digit max length,only numeric and 1 slash allow" name="custom">
								 function(val, fieldNode, ruleValue) {
								return /[0-9]/.test(val) && /[/]/.test(val);
								}
								</aui:validator>
								<aui:validator
								errorMessage="Slash position should be 7th."
								name="custom">
								 function(val, fieldNode, ruleValue) {
								 var character= val.charAt(6)
								return /[/]/.test(character);
								}
								 
								</aui:validator>
							</aui:input>
						</aui:col>
						<aui:col width="50">
							<aui:input label="ZVRMS Creation Date" name="startDate" type="date" required="true">
							</aui:input>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:select name="action" id="action" label="Action/Status" required="true" onchange="checkStatus();">
								<aui:option value="-1">Select</aui:option>
								<!-- <aui:option value="INVESTIGATION">INVESTIGATION</aui:option>
								<aui:option value="FILED">FILED</aui:option>
								<aui:option value="FACTUAL REPORT">FACTUAL REPORT</aui:option>
								<aui:option value="SENT FOR NECESSARY ACTION">SENT FOR
										NECESSARY ACTION</aui:option>
								<aui:option value="SCRUTINY OF CONTRACT">SCRUTINY OF
										CONTRACT</aui:option> -->
								<aui:option value="Under Examination">Under Examination</aui:option>
								<aui:option value="Under Investigation">Under Investigation</aui:option>
								<aui:option value="Closed">Closed</aui:option>
								<aui:option value="Others">Others</aui:option>

							</aui:select>
						</aui:col>
						<div class="col-md-6" id="statDiv" style="display:none;">
							<aui:input label="Others" name="otherStatus" type="text" value="" maxLength="200" placeholder="Max 200 char"/>
						</div>
						<aui:col width="50">
							<aui:input label="Remarks" name="remarks" type="textarea">
							</aui:input>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:button-row cssClass="p-2 text-center">
							<aui:button cssClass="btn btn-primary" type="submit"
								value="Update" />
							<aui:button cssClass="btn btn-primary" 
								value="Reset"  onclick="this.form.reset()" />
						</aui:button-row>
					</aui:row>
				</aui:form>

				<%-- <s:form action="updateSAPComplaint" method="post" theme="simple"
					onsubmit="return formvalidation1();" onreset="resetHandler();">
					<div class="row">


						<s:hidden name="complaint.complaintNo" id="compId" />
						<s:hidden name="startDate" id="H_startDate" />
						<s:hidden name="sapNo" id="H_sapNo" />
						<s:hidden name="remarks" id="H_remarks" />
						<div class="row">
							<!-- To show message start -->
							<div id="errorMsg" class="errors"></div>
							<s:if test="hasActionErrors()">
								<div id="errorDiv" class="errors">
									<s:actionerror />
								</div>
							</s:if>

							<s:if test="hasActionMessages()">
								<div class="welcome">
									<s:actionmessage />
								</div>
							</s:if>
							<!-- To show message end -->
							<!-- main content start-->
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-sm-3 col-md-3"> SAP No.
									</label>

									<s:if test="%{complaintDtl.sapNo==null}">
										<div class="col-sm-9 col-md-8">
											<s:textfield name="complaintDtl.sapNo" id="sapNo"
												cssClass="form-control" maxlength="20" />
										</div>
								</div>
							</div>
							</s:if>
							<s:else>
								<%
									flagDate = 1;
								%>
								<div class="col-sm-9 col-md-8">
									<s:textfield name="complaintDtl.sapNo" id="sapNo"
										cssClass="form-control" maxlength="20" readonly="true" />
								</div>
						</div>
					</div>
					</s:else>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-3 col-md-3"> Date</label>
							<div class="col-sm-9 col-md-8">
								<s:textfield name="" id="startDate" cssClass="form-control"
									readonly="true" />

							</div>
						</div>
					</div>

					<script>
						
					<%if (flagDate == 1) {%>
						var verifiedDate = '<s:date name="complaintDtl.verifiedDate" format="dd-MM-yyyy" />';
						//alert("date is "+verifiedDate);
						document.getElementById("startDate").value = verifiedDate;
					<%} else {%>
						document.getElementById("startDate").value = "";
					<%}%>
						
					</script>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-sm-3 col-md-3"> Action </label>
								<div class="col-sm-9 col-md-8">
									<s:select name="complaintDtl.action" id="actionDrop"
										headerKey="-1" cssClass="form-control" headerValue="Select"
										list="#{'INVESTIGATION':'INVESTIGATION','FILED':'FILED','FACTUAL REPORT':'FACTUAL REPORT','SENT FOR NECESSARY ACTION':'SENT FOR NECESSARY ACTION','SCRUTINY OF CONTRACT':'SCRUTINY OF CONTRACT'}"></s:select>
									<s:hidden name="action" value="-1" id="action" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-sm-3 col-md-3"> Remarks</label>
								<div class="col-sm-9 col-md-8">
									<s:textarea name="complaintDtl.remarks" id="remarks"
										onkeyup="checkChar(this)" rows="3" cssClass="form-control"></s:textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-item">
								<div class="col-sm-12 text-right">
									<s:submit value="Update" cssClass="btn btn-primary" />
									<input type="reset" name="button2" id="button2" value="Reset"
										onclick="return resetForm();" class="btn btn-primary" />
								</div>
							</div>

						</div>
					</div>
				</s:form> --%>


				<br />
				<div id="fullform " class=" ">
					<div class="table-responsive ">
						<table class="table ">
							<tbody>
								<tr class="">
									<td width="25%"><b>Complaint No:</b></td>
									<td width="20%">&nbsp; <%=complaint.getComplaintActNo()%>
									</td>
									<td width="20%"><b>Complaint Date:</b>&nbsp;</td>
									<td width="35%"><%=complaint.getComplaintDate()%></td>
								</tr>
								<tr>
									<td><b>Complainant Name:</b></td>

									<td><b></b>&nbsp;<%-- <%=complaint.getTitle() == null ? "" : complaint.getTitle()%>&nbsp; --%><%=complaint.getFirstName() == null ? "" : complaint.getFirstName()%>&nbsp;
										<%=complaint.getMiddleName() == null ? "" : complaint.getMiddleName()%>&nbsp;
										<%=complaint.getLastName() == null ? "" : complaint.getLastName()%></td>

									<td><b>Phone No:</b>&nbsp;</td>

									<td><%=complaint.getComplainBy().getMobile()%></td>
								</tr>
								<tr>

									<td><b>Email id:</b></td>

									<td>&nbsp;<%=complaint.getComplainBy().getEmailId()%></td>

									<td><b>Address:</b>&nbsp;</td>
									<td><%=complaint.getComplainBy().getFirstAddress()%>
										&nbsp;<%=complaint.getComplainBy().getSecondAddress()%></td>
								</tr>
								<tr>
									<td><b>Country:</b></td>
									<td>&nbsp;<%=complaint.getComplainBy().getCountry()%></td>
									<td><b>State:</b></td>
									<td><%=complaint.getComplainBy().getState()%></td>
								</tr>
								<tr>
									<td colspan="4"><b>Complaint Against::</b>&nbsp;</td>
								</tr>
								<tr>
									<td><b>Name:</b></td>
									<td><%=complaint.getComplaintAgainst()%></td>
									<td><b>Address:</b></td>
									<td><%=complaint.getWorkCentre()%></td>
								</tr>
								<tr>
									<td><b>Department:</b></td>
									<td><%=complaint.getDepartmetn()%></td>
									<td><b>Designation:</b></td>
									<td><%=complaint.getDesignation()%></td>
								</tr>
								<tr>
									<td colspan="4" class="bold"><b>Complaint Details :</b></td>
								</tr>
								<tr>
									<td width="25%" class="bold"><b>Subject:</b></td>
									<td colspan="3"><p>
											<%=complaint.getComplaintSubject()%>
										</p></td>
								</tr>
								<tr>
									<td class="bold">Allegations/Complaint Details:</td>
									<td colspan="3"><p>
											<%=complaint.getComplaintDetail()%>
										</p></td>
								</tr>
								<tr>
									<td>Attachment:</td>
									 <td colspan="3"><p>
											<span> 
											<% if(!complaint.getAttachmentList().isEmpty()){ %>
<% for(ComplaintAttachment complaintAttachment:complaint.getAttachmentList()){
String ext =complaintAttachment.getFileName().substring(complaintAttachment.getFileName().lastIndexOf(".")); 
String fileName = complaintAttachment.getFileName().substring(0, complaintAttachment.getFileName().indexOf("."));%>
		    <% if(ext.equals(".jpg")){ %>
		    <a  href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaint/imageComplaint?fileName=<%= fileName %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <%} else if(ext.equals(".pdf")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintPdf/complaintPdf?pdfname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		     <%} else{%> 
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } } }else{%>NA<%}%>
											<%--<%
 	boolean chkFlag = false;
 %> <s:if test="%{complaint.attachmentList.isEmpty()}">
												</s:if> <s:else>
													<s:iterator value="complaint.attachmentList">
														<%
															chkFlag = true;
														%>
														<a
															href="<%=request.getContextPath()%>/admin/downloadJsp.jsp?attachmentId=<s:property value="attachmentId"/>">
															<s:property	value="fileName" /></a>
				&nbsp;&nbsp;
		        </s:iterator>
												</s:else> <%
 	if (!chkFlag)
 				out.println("NA");
 %>--%>
											</span>
										</p></td> 
										<%--  <td colspan="3" >
										 <% 
										 if(complaint.getAttachment()!=null){
										 String ext =complaint.getAttachment().substring(complaint.getAttachment().lastIndexOf(".")); 
		    String complaintNO = String.valueOf(complaint.getComplaintActNo()).replace("VIG0", "");
		    String fileName = complaint.getAttachment().substring(0, complaint.getAttachment().indexOf("."));
		    %>
		    <% if(ext.equals(".jpg")){ %>
		    <img class="img-thumbnail" style="max-height: 150px;" src="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaint/imageComplaint?fileName=<%= complaintNO %>">
		    <%} else if(ext.equals(".pdf")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintPdf/complaintPdf?pdfname=<%= complaintNO %>.pdf" target="_blank">Download</a>
		     <%} else{%> 
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintNO %><%=ext %>" download target="_blank">Download</a>
		    <% }}else{%>NA<%} %>
		    </td> --%>
								</tr>
							</tbody>
						</table>
					</div>
					<br /> <br />
				</div>

				<!-- main content end-->

				<div class="row">
					 <div class="col-md-12 text-center">
						<button class="btn btn-primary ">
							<a href="<%=request.getContextPath()%>/admin/printJsp.jsp?compId=<%= complaint.getComplaintNo() %>" download target="_blank">
								<span class="glyphicon glyphicon-print"></span> Print
							</a>
						</button>
					</div> 
				</div>
			</div>
		</div>
	</div>


</div>
<footer>
	<%@ include file="/admin/footer.jsp"%>
</footer>

