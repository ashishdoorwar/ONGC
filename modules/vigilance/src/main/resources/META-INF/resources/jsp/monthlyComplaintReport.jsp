<%@page import="com.ongc.liferay.vigilance.model.VigilanceAdminUser"%>
<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="java.util.List"%>
<head>
<s:form action="vigilanceAdminLogin" method="post" name="timeout"
	theme="simple">
</s:form>
<%

	if (session.getAttribute(VigilanceConstant.VIGILANCE_ADMINUSER) == null) {
%>
<script>
document.timeout.submit();
</script>
<%
	}
%>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/datepicker.css")%>'
	type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.css'
	type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/Adminstyle.css' type="text/css">
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/complaintPagging.js'></script>


<script>
	function clearField()
	{
	document.getElementById("month").value="";
	document.getElementById("year").value="";
	document.getElementById("actionType").value="";	
	}
	
	
function rptFormvalidation()
{	
		var month=document.getElementById("month").value;
		var year=document.getElementById("year").value;
		if (month=="-1") {
		 alert("Please select a month");
		 document.getElementById("month").focus();
		return false();
		}
		else if (year=="-1") {
	     alert("Please select a year");
		 document.getElementById("year").focus();
		return false();
		}
		
	return true;
}
	

	
	function complaintreadUpdate(pid, status){
	document.complaintForm.compId.value=pid;
	document.complaintForm1.compId1.value=pid;
	if(status=="NEW")
	document.complaintForm.submit();
	else
	document.complaintForm1.submit();	
}
</script>
<%!public String getTreamFileName(String fileName) {
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String fName = fileName.substring(0, fileName.lastIndexOf("."));
		if (fName.length() > 15) {
			fName = fName.substring(0, 15) + ".." + extName;
		} else {
			fName = fileName;
		}
		return fName;
	}%>
</head>
<%

VigilanceAdminUser userData = (VigilanceAdminUser) request.getAttribute("userData");
	Integer month = (int) request.getAttribute("month");
	Integer year = (int) request.getAttribute("year");
	List<VigilanceComplaint> list = (List<VigilanceComplaint>) request.getAttribute("complaintList");
	if (list == null)
		list = new java.util.ArrayList<VigilanceComplaint>();
	//ComplaintPaginator cpag = new ComplaintPaginator(list, 10, "0");
	List<String> yrs = new ArrayList<String>();
	java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
	
%>
<s:form action="readComplaintDetails" method="post" name="complaintForm"
	theme="simple">
	<s:hidden name="complaint.complaintNo" id="compId" />

	<s:hidden name="month" />
	<s:hidden name="year" />

</s:form>

<header>
	<%@ include file="/admin/header.jsp"%>
</header>
<div class="container " id="skip-main-content">
	<div class="row">
	<!--left section start-->

	<div class="col-md-3">
		<%@ include file="/admin/menu.jsp"%>
	</div>

	<!--left section end-->

	<!--right section start-->
	<div class="col-md-9">


		<div class="form-item">
			<div class="row">
				<div class="col-md-6">
					<h1 class="mb5 mt0">Monthly Complaint Report</h1>
				</div>

			</div>
			<div class="bg-grey bdr-orange search-form">


<portlet:renderURL var="reportComplaintMonthly">
	<portlet:param name="mvcRenderCommandName" value="reportComplaintMonthly" />
</portlet:renderURL>


				<div>
					<aui:form action="<%=reportComplaintMonthly%>" method="post">
						<aui:row>
							<aui:col width="50">
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="50">


								<aui:select name="month">
									<aui:option value="-1">Select</aui:option>
									<aui:option value="1">January</aui:option>
									<aui:option value="2">February</aui:option>
									<aui:option value="3">March</aui:option>
									<aui:option value="4">April</aui:option>
									<aui:option value="5">May</aui:option>
									<aui:option value="6">June</aui:option>
									<aui:option value="7">July</aui:option>
									<aui:option value="8">August</aui:option>
									<aui:option value="9">September</aui:option>
									<aui:option value="10">October</aui:option>
									<aui:option value="11">November</aui:option>
									<aui:option value="12">December</aui:option>

								</aui:select>
							</aui:col>
							<aui:col width="50">
								<aui:select name="year">
								<%for (int i = 1900 + (new java.util.Date()).getYear(); i >= 2013; i--) { %>
									<aui:option value="<%= i%>"><%= i%></aui:option>
												<%} %>
								
								</aui:select>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="50">

								<aui:select name="action">
								<aui:option value="-1">Select</aui:option>
								<aui:option value="Under Examination">Under Examination</aui:option>
								<aui:option value="Under Investigation">Under Investigation</aui:option>
								<aui:option value="Closed">Closed</aui:option>
								<aui:option value="Others">Others</aui:option>
							</aui:select>
							</aui:col>

						</aui:row>
						<br />
						<aui:row>
							<aui:button-row cssClass="p-2 text-center">
								<aui:button cssClass="btn btn-primary" type="submit"
									value="Search" />

							</aui:button-row>
						</aui:row>
					</aui:form>
					
			</div>
			<br />
			<div class="row">
<div class="col-lg" align="right"><button id="btnExport" class="btn btn-primary" onclick="ExcelReport();">Export to Excel</button></div>
				<div class="col-md-12">


					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<td>Total Results (<%=list.size()%>)
							</td>
							<%
								if (list.size() > 0) {
							%>
							<td align="right"><%-- <a class="btn btn-primary mb5"
								title="Print PDF"
								href="<%= request.getContextPath() %>/admin/pdfReportJsp.jsp?type=monthly&month=<%=month %>&year=<%=year%>" download target="_blank">Print
									PDF</a> --%> <%
 	}
 %></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
				</div>
			</div>

			<div class="row" id="complaintTD">


				<div class="col-md-12">

					<div class="table-responsive sr-table">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table" id="tableData">
							<thead>
								<tr>
									<th>S.No.</th>
									<th>Complaint No.</th>
									<th>Complaint Date</th>
									<th>Subject</th>
									<%-- <th >Attachment</th>	--%>
									<th>Name</th>
									<th>Mobile No.</th>
									<th>Status</th>
									<th>Other Status</th>
									<th>Remark</th>
								</tr>
							</thead>
							<tbody>
								<% int sno=0;
									//while (cpag.isNext()) {
										//VigilanceComplaint vc = (VigilanceComplaint) cpag.next();
										for(VigilanceComplaint vc:list){sno++;
										List<ComplaintAttachment> lst = vc.getAttachmentList();
										String action = vc.getAction() == null ? "" : vc.getAction();
								%>

								<tr>
									<td><%=sno%></td>
									<td>
									<portlet:renderURL var="viewcomplaintDtlpage">
										<portlet:param name="mvcPath" value="/jsp/AdminComplaintViewDetails.jsp" />
										<portlet:param name="emailId" value="<%=userData.getEmailId() %>" />
										<portlet:param name="complaintNo" value="<%=String.valueOf(vc.getComplaintNo()) %>" />
									</portlet:renderURL>
									<a href="<%=viewcomplaintDtlpage%>"><span
															onclick="viewcomplaintDtlpage(<%=vc.getComplaintNo()%>)"
															style="color: blue; font-weight: bold; cursor: pointer">
											<%=vc.getComplaintActNo()%></a></td>
									<td><%=dateFormat.format(vc.getComplaintDate())%></td>
									<td><%=vc.getComplaintSubject()%></td>

									<td><%=vc.getComplainBy().getTitle() + " " + vc.getComplainBy().getFirstName()%></td>

									<td><%=vc.getComplainBy().getMobile()%></td>


									<td><%=action%></td>
									<td><%=vc.getOtherStatus()%></td>
									<td><%= vc.getRemarks() %></td>

								</tr>
								<%
									}
								%>

							</tbody>
						</table>
					</div>


				</div>
				<%-- <div class="row">
					<div class="col-md-12">
						<%=cpag.createIndex("callPeriodicRptPage")%>
					</div>
				</div> --%>
			</div>

		</div>
	</div>
</div>
</div>
</div>

<script type="text/javascript">
         function ExcelReport()
         {
         var tab_text="<table border='2px'><tr bgcolor='#FFFFFF'>";
         var textRange; var j=0;
         tab = document.getElementById('tableData'); 
for(j = 0 ; j < tab.rows.length ; j++) 
{     
    tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";       
}

tab_text=tab_text+"</table>";
tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");
tab_text= tab_text.replace(/<img[^>]*>/gi,""); 
tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); 
var ua = window.navigator.userAgent;
var msie = ua.indexOf("MSIE "); 

if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      
{
    txtArea1.document.open("txt/html","replace");
    txtArea1.document.write(tab_text);
    txtArea1.document.close();
    txtArea1.focus(); 
    sa=txtArea1.document.execCommand("SaveAs",true,"monthlyReport.xls");
}  
else                 
    sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

   return (sa);
  }

  </script>   


<br clear="all" />
</div>
</div>
</div>
<footer>
	<%@ include file="/admin/footer.jsp"%>
</footer>


