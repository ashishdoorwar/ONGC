<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl"%>
<%@page import="com.ongc.liferay.sponsorship.service.SponsorshipService"%>
<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.sponsorship.model.SponsorshipBean"%>
<%

SponsorshipService  sponsorshipService= new SponsorshipServiceImpl();
SponsorshipBean sponsorshipBean = (SponsorshipBean) request.getAttribute("sponsorshipBean");
String backURL = (String) request.getAttribute("backURL");
Date sDate1=new SimpleDateFormat("dd-MM-yyyy").parse("30-06-2022");  
%>
<div class="container">
<%@ include file="/sponsorship/header.jsp"%>

<div class="table-responsive">
	<table class="table table-bordered table-striped mb-4" id="viewData">
		<thead></thead>
		<tbody>
			<tr>
				<td width="20%">Reference No.</td>
				<td><%=sponsorshipBean.getRefno() %></td>
			</tr>
			<tr>
				<td>Created By</td>
				<td><%=sponsorshipBean.getCreatedBy()%></td>
			</tr>
			<tr>
				<td>Subject/Aims</td>
				<td><% String subjectAims = sponsorshipService.getSubjectName(sponsorshipBean.getSubject()); %>
				<%= subjectAims %>
				</td>
			</tr>
			<tr>
				<td>Occassion/Purpose</td>
				<td><%=sponsorshipBean.getPurpose()%></td>
			</tr>
			<tr>
				<td>Beneficiary Name</td>
				<td><%=sponsorshipBean.getPublicationname()%></td>
			</tr>
			<tr>
				<td>EmailID</td>
				<td><%=sponsorshipBean.getEmailid()%></td>
			</tr>
			<tr>
				<td>Dealing Officer</td>
				<td><%=sponsorshipBean.getDealingOfficer()%></td>
			</tr>
			<tr>
				<td>External Recommendation</td>
				<td><%=sponsorshipBean.getRecommendedby()%></td>
			</tr>
			<tr>
				<td>Internal Recommendation</td>
				<td><%=sponsorshipBean.getInternalRecomendation()%></td>
			</tr>
			<tr>
				<td>Final Value</td>
				<td><%=sponsorshipBean.getProposalval()%></td>
			</tr>

			<tr>
				<td>Deliverables</td>
				<td><%=sponsorshipBean.getDeliverable()%></td>
			</tr>
			<tr>
				<td>FR Number</td>
				<td><%=sponsorshipBean.getFrNumber()%></td>
			</tr>
			<tr>
				<td>Tracking Number</td>
				<td><%=sponsorshipBean.getTrackingNumber()%></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><%=sponsorshipBean.getMobileNo()%></td>
			</tr>
			<tr>
				<td>Proposal Attachment</td>
				<td><% 
					try{
					Date createdOn = new SimpleDateFormat("yyyy-MM-dd").parse(sponsorshipBean.getCreatedon());
					if(Validator.isNotNull(sponsorshipBean.getProposalDocFileName())){
					String last = sponsorshipBean.getProposalDocFileName(). substring(sponsorshipBean.getProposalDocFileName(). lastIndexOf('/') + 1);
					String ext =last.substring(last.lastIndexOf("."));
					if(createdOn.after(sDate1)){System.out.println("hi"+sponsorshipBean.getProposalDocFileName());
						if(ext.equals(".jpg")){ String fileName = String.valueOf(last).replace(".jpg", ""); %>
				<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletSponsorship/imageSponsorship?fileName=<%= fileName %>" target="_blank"><%= last%></a>
		 <%}  else if(ext.equals(".docx") || ext.equals(".doc")){ %>
		     <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipWord/sponsorshipWord?wordname=<%= last %>" download target="_blank"><%= last%></a>
					<%} else if(ext.equals(".pdf")){ %>
					<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipPdf/sponsorshipPdf?pdfname=<%= last %>" download="<%= last%>"><%= last%></a>
					<%} } else{ System.out.println("bye"+sponsorshipBean.getProposalDocFileName());
					if(ext.equals(".jpg")){ String fileName = String.valueOf(last).replace(".jpg", ""); %>
				<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletSponsorship/imageSponsorship?fileName=<%=sponsorshipBean.getRefno() %>_<%= fileName %>" target="_blank"><%= last%></a>
		 <%}  else if(ext.equals(".docx") || ext.equals(".doc")){ %>
		     <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipWord/sponsorshipWord?wordname=<%=sponsorshipBean.getRefno() %>_<%= last %>" download target="_blank"><%= last%></a>
					<%} else if(ext.equals(".pdf")){ %>
					<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipPdf/sponsorshipPdf?pdfname=<%=sponsorshipBean.getRefno() %>_<%= last %>" download="<%= last%>"><%= last%></a>
					<%} } } }catch(Exception e){e.printStackTrace();}%> </td>
			</tr>
			<tr>
				<td>Release Order Attachment</td>
				<td> <% 
				try{
				Date createdOn = new SimpleDateFormat("yyyy-MM-dd").parse(sponsorshipBean.getCreatedon());
				if(Validator.isNotNull(sponsorshipBean.getReleaseOrderDocFileName())){
				String releaseOrder = sponsorshipBean.getReleaseOrderDocFileName(). substring(sponsorshipBean.getReleaseOrderDocFileName(). lastIndexOf('/') + 1);
					String releaseOrderext =releaseOrder.substring(releaseOrder.lastIndexOf("."));
					if(createdOn.after(sDate1)){
					if(releaseOrderext.equals(".jpg")){ 
					String releaseOrderfileName = String.valueOf(releaseOrder).replace(".jpg", "");%>
				<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletSponsorship/imageSponsorship?fileName=<%= releaseOrderfileName %>" download target="_blank"><%= releaseOrder%></a>
		    <%} else if(releaseOrderext.equals(".docx") || releaseOrderext.equals(".doc")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipWord/sponsorshipWord?wordname=<%= releaseOrder %>" download target="_blank"><%= releaseOrder%></a>
					<%} else if(releaseOrderext.equals(".pdf")){ %>
					<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipPdf/sponsorshipPdf?pdfname=<%= releaseOrder %>" download="<%= releaseOrder%>"><%= releaseOrder%></a>
					
					<%} }else{
					if(releaseOrderext.equals(".jpg")){ 
					String releaseOrderfileName = String.valueOf(releaseOrder).replace(".jpg", "");%>
				<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletSponsorship/imageSponsorship?fileName=<%=sponsorshipBean.getRefno() %>_<%= releaseOrderfileName %>" download target="_blank"><%= releaseOrder%></a>
		    <%} else if(releaseOrderext.equals(".docx") || releaseOrderext.equals(".doc")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipWord/sponsorshipWord?wordname=<%=sponsorshipBean.getRefno() %>_<%= releaseOrder %>" download target="_blank"><%= releaseOrder%></a>
					<%} else if(releaseOrderext.equals(".pdf")){ %>
					<a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletsponsorshipPdf/sponsorshipPdf?pdfname=<%=sponsorshipBean.getRefno() %>_<%= releaseOrder %>" download="<%= releaseOrder%>"><%= releaseOrder%></a>
					
				
				<%} } } }catch(Exception e){e.printStackTrace();} %>
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td><%String status = sponsorshipService.getSponsorshipStatusName(Integer.parseInt(sponsorshipBean.getStatus())); %>
				<%= status %>
				</td>
			</tr>

			<tr>
				<td>Proposal Recieved Date</td>
				<td><%	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); %>
				<%= sponsorshipBean.getReceivedDate() %>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center mt-2">
<portlet:renderURL var="editCsrProgram">
		<portlet:param name="mvcRenderCommandName" value="edit_sponsorship_program_model" />
		<portlet:param name="id"
			value="<%=Integer.toString(sponsorshipBean.getSponid()) %>" />
			</portlet:renderURL>
	<portlet:actionURL var="deleteCsrProgram" name="delete_sponsorship">
			<portlet:param name="id" value="<%=Integer.toString(sponsorshipBean.getSponid()) %>" />
			<portlet:param name="mvcPath" value="/sponsorship/listSponsorship.jsp" />
	</portlet:actionURL>
	
		<a id="editTag" href="<%=editCsrProgram%>"><button class="btn btn-primary">Edit</button></a>
		<a id="deleteTag" href="<%=deleteCsrProgram%>"><button class="btn btn-primary">Delete</button></a>
		<a href="<%=backURL %>" class="btn btn-primary">Back</a>
		<!-- <input type="submit" id="" value="Back" class="btn btn-primary"
			onclick="javascript:history.go(-1)"> -->

	</div>
</div>
</div>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas
			Corporation Limited</div>
	</div>
</footer>