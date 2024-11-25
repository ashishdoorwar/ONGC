<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
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
	List<VigilanceComplaint> list = (List<VigilanceComplaint>) request.getAttribute("complaintList");
	java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
%>




<header>
	<%@ include file="/admin/header.jsp"%>
</header>
<div class="container " id="skip-main-content">
	<div class="row">
	<!--left section start-->

	<div class="col-md-3">
		<%@ include file="/admin/menu.jsp"%>
	</div>

<portlet:renderURL var="readComplaintDetails">
	<portlet:param name="mvcRenderCommandName" value="readComplaintDetails" />
</portlet:renderURL>

<portlet:renderURL var="updateComplaintDetail">
	<portlet:param name="mvcRenderCommandName" value="updateComplaintDetail" />
</portlet:renderURL>


	<!--left section end-->

	<!--right section start-->
	<div class="col-md-9">


		<div class="form-item">
			<h1 class="mb5 mt0">Search Complaint</h1>
		</div>




		<div class="bg-grey bdr-orange search-form">


			<portlet:renderURL var="searchComplaint">
				<portlet:param name="mvcRenderCommandName" value="searchComplaint" />
			</portlet:renderURL>


			<div class="row">
				<aui:form action="<%=searchComplaint%>" method="post" style="width:100%">

					<aui:row>
						<aui:col width="50">

							<aui:input label="Start Date :" name="startDate" type="date" />
						</aui:col>
						<aui:col width="50">
							<aui:input label="End date :" name="endDate" type="date" />
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">

							<aui:input label="Complaint No. :" name="complaintNo" type="text" />
						</aui:col>
						<aui:col width="50">

							<aui:select name="complaintStatus" label="Complaint Status :">
								<option value="-1">Select</option>
								<option value="NEW">NEW</option>
								<option value="READ">READ</option>
								<option value="FORWARD">FORWARD</option>
							</aui:select>
						</aui:col>
					</aui:row>
					<br />
					<aui:row>
						<aui:button-row cssClass="p-2 text-center">
							<aui:button cssClass="btn btn-primary" type="submit"
								value="Search" />
							<aui:button cssClass="btn btn-primary" value="Cancel" />
						</aui:button-row>
					</aui:row>
				</aui:form>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12">
					Total Results (<%=list.size()%>)
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
						<table id="viewComplaint"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>S.No.</th>
									<th>Complaint No.</th>
									<th>Complaint Date</th>
									<th>Subject</th>
									<th>Attachment</th>
									<th>Name</th>
									<th>Mobile No.</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<% int sno = 0;
									for(VigilanceComplaint vc:list){sno++;
										List<ComplaintAttachment> lst = vc.getAttachmentList();
								%>

								<tr>
									<td><%=sno%></td>
									<td><%=vc.getComplaintActNo()%></td>
									<td><%=dateFormat.format(vc.getComplaintDate())%></td>
									<td><%=vc.getComplaintSubject()%></td>
									<td>
 <%if(!lst.isEmpty()){
for(ComplaintAttachment complaintAttachment:lst){ %>
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } }  else { %>NA<%}	 %>
									</td>
									<td><%= vc.getComplainBy().getFirstName()%></td>
									<td><%=vc.getComplainBy().getMobile()%></td>
									<td><span
										onclick="complaintreadUpdate('<%=vc.getComplaintNo()%>','<%=vc.getStatus()%>')"
										class="lnk"> <%=vc.getStatus()%></span></td>
								</tr>
								<% } %>
							</tbody>
						</table>
					</div>


			</div>


		</div>


<script>
$(document).ready(function() {
	 var table = $('#viewComplaint').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false
	    });
	}); 
	
	function CompareFirstFieldDateGreaterThanSecondFieldDate(ctrlIDFirst,
			ctrlIDSecound, ctrlNameFirst, ctrlNameSecound) {
		var fieldDateFirst = document.getElementById(ctrlIDFirst).value;
		var fieldDateSecound = document.getElementById(ctrlIDSecound).value;

		if ((fieldDateFirst == "") && (fieldDateSecound == "")) {
			return true;
		} else {
			if (fieldDateFirst == "") {
				alert("Please enter " + ctrlNameFirst);
				//document.getElementById(ctrlIDFirst).value = ""; -- Commented By Perumal on 23 July 2010
				document.getElementById(ctrlIDFirst).focus();
				return false;
			} else if (fieldDateSecound == "") {
				alert("Please enter " + ctrlNameSecound);
				// document.getElementById(ctrlIDSecound).value = ""; -- Commented By Perumal on 23 July 2010
				document.getElementById(ctrlIDSecound).focus();
				return false;
			} else {
				fieldDateFirst = fieldDateFirst.split("/");
				var Date1 = new Date();
				Date1.setFullYear(fieldDateFirst[2], fieldDateFirst[1] - 1,
						fieldDateFirst[0]);

				fieldDateSecound = fieldDateSecound.split("/");
				var Date2 = new Date();
				Date2.setFullYear(fieldDateSecound[2], fieldDateSecound[1] - 1,
						fieldDateSecound[0]);

				if (Date1 > Date2) {
					return true;
				} else {
					//alert(" Please Enter "+ ctrlNameFirst + " and " + ctrlNameSecound +".");
					alert(ctrlNameFirst + " should be greater than "
							+ ctrlNameSecound + ".");
					// document.getElementById(ctrlIDFirst).value = ""; -- Commented By Perumal on 23 July 2010
					document.getElementById(ctrlIDFirst).focus();
					return false;
				}
			}
		}
	}

	function isGreaterDate(dt, dt2) {
		var splitDt = dt.split('-');
		var splitDt2 = dt2.split('-');
		var DtDate = parseInt(splitDt[0]);
		var DtMo = parseInt(splitDt[1]);
		var DtYr = parseInt(splitDt[2]);
		var Dt2Date = parseInt(splitDt2[0]);
		var Dt2Mo = parseInt(splitDt2[1]);
		var Dt2Yr = parseInt(splitDt2[2]);
		if (DtYr < Dt2Yr) {
			return true;
		}
		if (Dt2Yr == DtYr && DtMo < Dt2Mo) {
			return true;
		}
		if (Dt2Yr == DtYr && Dt2Mo == DtMo && DtDate < Dt2Date) {
			return true;
		}
		return false;
	}

	function clearField() {
		document.getElementById("startDate").value = "";
		document.getElementById("endDate").value = "";
		document.getElementById("complaintNo").value = "";
		document.getElementById("complaintStatusDrop").value = "";

	}

	function formvalidation() {

		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;

		if (startDate == "") {
			alert("Start Date should not be blank");
			document.getElementById("startDate").focus();
			return false;
		} else if (endDate == "") {
			alert("End Date should not be blank");
			document.getElementById("endDate").focus();
			return false;
		}

		if (isGreaterDate(endDate, startDate)) {
			alert("End date should be greater than Start date");
			document.getElementById("endDate").value = "";
			document.getElementById("endDate").focus();
			return false;
		}

		var status = document.getElementById("complaintStatusDrop").value;
		if (status != "-1") {
			document.getElementById("complaintStatus").value = Base64
					.tripleEncoding(status);

		}

		return true;
	}

	window.onload = function() {
		$(function() {
			$('#startDate').datepick();
			$('#endDate').datepick();
		});

	};

	function complaintreadUpdate(pid, status) {
		
		if (status == "NEW")
			window.location.href="<%= readComplaintDetails.toString() %>&<portlet:namespace />pid="+pid;
		else
			window.location.href="<%= updateComplaintDetail.toString() %>&<portlet:namespace />pid="+pid;

	}
</script>

	</div>
</div>
</div>

<br clear="all" />

<footer>
	<%@ include file="/admin/footer.jsp"%>
</footer>

