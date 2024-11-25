
<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="java.util.List"%>

<portlet:defineObjects />
<!DOCTYPE html>
<html>
<s:form action="vigilanceAdminLogin" method="post" name="timeout"
	theme="simple">
</s:form>
<%
	if(session.getAttribute(VigilanceConstant.VIGILANCE_ADMINUSER)==null){
%>
<script>
document.timeout.submit();
</script>
<%
	}
%>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>css/datepicker.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>js/DatePicker/jquery.datepick.css'
	type="text/css">

<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/DatePicker/jquery.1.4.2.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/DatePicker/jquery.datepick.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath() %>/js/complaintPagging.js'></script>

<body>
<portlet:renderURL var="updateComplaintDetail">
	<portlet:param name="mvcRenderCommandName" value="updateComplaintDetail" />
</portlet:renderURL>
	<%
		List list=(List)request.getAttribute("complaintList");
	
	ComplaintPaginator cpag=new ComplaintPaginator(list,10,"0");
	java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
	%>
	<s:form action="readComplaintDetails" method="post"
		name="complaintForm" theme="simple">
		<s:hidden name="complaint.complaintNo" id="compId" />
	</s:form>


	<h1 class="mb5 mt0">Complaint</h1>
	<div class="bg-grey bdr-orange search-form">

		<table>
			<tr>
				<td colspan="8"><s:if test="hasActionMessages()">
						<div class="welcome">
							<s:actionmessage />
						</div>
					</s:if></td>
			</tr>
			<tr>
				<td colspan="8">Total Results (<%=list.size()%>)
				</td>
			</tr>
			<tr>
				<td colspan="8"></td>
			</tr>

		</table>



		<div class="table-responsive sr-table" id="complaintTD">
			<table class="table table-striped table-bordered" id="viewComplaint">
				<thead>
					<tr>
						<th>S.No.</th>
						<th>Complaint No.</th>
						<th>Complaint Date</th>
						<th>Subject</th>
						<th>Attachment</th>
						<th>Name</th>
						<th>Mobile No.</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>


					<%
						while(cpag.isNext()){
						VigilanceComplaint vc=(VigilanceComplaint)cpag.next();
						List<ComplaintAttachment> lst=vc.getAttachmentList();
					%>

					<tr>
						<td><%=cpag.getIndex()%></td>
						<td><%=vc.getComplaintActNo()%></td>
						<td><%=dateFormat.format(vc.getComplaintDate())%></td>
						<td><%=vc.getComplaintSubject()%></td>
						<td>
							<%-- <%
								for(int i=0;i<lst.size();i++){if(i==0) out.println("<a href='/wps/PA_Vigilance/admin/downloadJsp.jsp?attachmentId="+lst.get(i).getAttachmentId()+"'>"+lst.get(i).getFileName()+"</a>"); else out.println(" | <a href='/wps/PA_Vigilance/admin/downloadJsp.jsp?attachmentId="+lst.get(i).getAttachmentId()+"'>"+lst.get(i).getFileName()+"</a>"); }
							%> --%>
							   <%if(!lst.isEmpty()){
for(ComplaintAttachment complaintAttachment:lst){ %>
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } }  else { %>NA<%}	 %>
						</td>
						<td><%=vc.getComplainBy().getTitle()+" "+vc.getComplainBy().getFirstName()%></td>

						<td><%=vc.getComplainBy().getMobile()%></td>

						<td><span
							onclick="complaintreadUpdate(<%=vc.getComplaintNo() %>)" class="lnk"> Read</span></td>

					</tr>
					<%
						}
					%>
					<%-- <tr>
						<td colspan="9">
							<!--<div class="pagination2"><a href="#" class="next"><img src="images/next_button.png" width="35" height="22" alt="first" /></a><a href="#" class="prev"><img src="images/page_prev.png" width="16" height="17" alt="next" /> </a><span><a href="#">1</a>234567</span><a href="#" class="next"><img src="images/page_next1.png" width="16" height="17" alt="next" /> </a> <a href="#"><img src="images/lastimg.png" width="35" height="22" alt="last" /></a></div>-->
							<%=cpag.createIndex("callComplaintPage")%></td>


					</tr> --%>
				</tbody>
			</table>
		</div>





	</div>


	<script src='<%=request.getContextPath() %>/js/jquery.min.js'
		type="text/javascript"></script>
	<script src='<%=request.getContextPath() %>/js/bootstrap-datepicker.js'
		type="text/javascript"> </script>
	<script src='<%=request.getContextPath() %>/js/bootstrap.min.js'
		type="text/javascript"> </script>

	<script>
   $(document).ready(function () {
                
                $('.input-daterange').datepicker({
                    todayBtn: "linked"
                });
        
            });
   
   function complaintreadUpdate(pid) {
			window.location.href="<%= updateComplaintDetail %>&<portlet:namespace />pid="+pid;

	}
   
   $(document).ready(function() {
		 var table = $('#viewComplaint').DataTable( {
			 lengthChange: false,bFilter: false, bInfo: false
		    });
		});
 </script>
</body>
</html>