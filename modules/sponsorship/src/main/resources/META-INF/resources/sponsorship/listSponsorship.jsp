<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.sponsorship.model.FilterBean"%>
<%@page import="com.ongc.liferay.sponsorship.model.SubjectBean"%>
<%@page import="com.ongc.liferay.sponsorship.model.SponsorshipBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="searchSponsorship">
	<portlet:param name="mvcRenderCommandName" value="search_sponsorship" />
</portlet:renderURL>

<%
List<SponsorshipBean> sponsorshipListData =(List<SponsorshipBean>)request.getAttribute("sponsorshipListData");

List<SubjectBean> subjectBeans =(List<SubjectBean>)request.getAttribute("subjectBeans");
List<FilterBean> filterBeans =(List<FilterBean>)request.getAttribute("filterBeans");
List<SubjectBean> createdbyList =(List<SubjectBean>)request.getAttribute("createdbyList");

SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat stm1 = new SimpleDateFormat("dd-MM-yyyy");
%>
<div class="container">
<%@ include file="/sponsorship/header.jsp"%>
<div class="sponsorshipContainer">
	<div class="sponsorshipheader">
		<h1>List of Sponsorship and Advertisement</h1>
	</div>
	<div align="right">
		
	</div>


	<aui:form action="${searchSponsorship}" name="fm" method="post">
		<aui:fieldset-group markupView="lexicon">
			<aui:row>
				<aui:col width="25">

					<aui:select label="Subject/Aims" name="subName" id="subName">
						<aui:option value=''>Select Subject</aui:option>
						<%
							for (SubjectBean subjectBean : subjectBeans) {
						%>
						<aui:option value="<%=subjectBean.getSubId()%>"><%=subjectBean.getSubName()%></aui:option>
						<%
							}
						%>
					</aui:select>

				</aui:col>
				<aui:col width="25">
					<aui:input label="Beneficiary Name" name="publicationname" type="text" />
				</aui:col>
				<aui:col width="25">
					<aui:input label="External Recommendation" name="recommendedby" type="text" />
				</aui:col>
				<aui:col width="25">
					<aui:input label="Purpose" name="purpose" type="text" />
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="25">
				<aui:select label="Created By" name="createdBy" id="status">
						<aui:option value=''>Select Created By</aui:option>
						<%
							for (SubjectBean createdby : createdbyList) {
						%>
						<aui:option value="<%=createdby.getLocation()%>"><%=createdby.getLocation()%></aui:option>
						<%
							}
						%>
					</aui:select>
					
				</aui:col>
				<aui:col  width="25">
				<aui:input label="Reference Number" name="refNo" type="text" />
				</aui:col>
				<aui:col  width="25">
				<aui:select label="Status" name="status" id="status">
						<aui:option value=''>Select Status</aui:option>
						<%
							for (FilterBean statusBean : filterBeans) {
						%>
						<aui:option value="<%=statusBean.getStatus()%>"><%=statusBean.getStatus()%></aui:option>
						<%
							}
						%>
					</aui:select>
				</aui:col>
				
			</aui:row>
			<aui:button-row cssClass="text-center">
				<aui:button cssClass="btn btn-primary" type="submit" value="Search" />
			</aui:button-row>
		</aui:fieldset-group>
	</aui:form>

	
		<button id="excel" class="btn btn-primary">Export to excel</button>

	<div class="sponsorship_table table-responsive">
		<table class="table table-striped table-bordered"
			id="sponsorship" width="100%">

			<thead>
				<tr>
					<th>Reference Number</th>
					<th>Subject/Aims</th>
					<th>Beneficiary Name</th>
					<th>External Recommendation</th>
					<th>Purpose</th>
					<th>Status</th>
					<th>Created By</th>
					<th>Created On</th>
					<th style="display:none;"></th>
					<th>Updated On</th>

				</tr>
			</thead>
			<%
				for (SponsorshipBean sponsorshipProgramModel : sponsorshipListData) {
			%>
			<tr>
				<portlet:renderURL var="viewSponsorship">
					<portlet:param name="mvcRenderCommandName" value="view_sponsorship_program_model" />
					<portlet:param name="id" value="<%=Integer.toString(sponsorshipProgramModel.getSponid()) %>" />
					<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"></portlet:param>
				</portlet:renderURL>
				<td><a href="<%=viewSponsorship%>"><%=sponsorshipProgramModel.getRefno()%></a></td>
				<td><%=sponsorshipProgramModel.getSubject()%></td>
				<td><%=sponsorshipProgramModel.getPublicationname()%></td>
				<td><%=sponsorshipProgramModel.getRecommendedby()%></td>
				<td><%=sponsorshipProgramModel.getPurpose()%></td>
				<td><%=sponsorshipProgramModel.getStatus()%></td>
				<td><%=sponsorshipProgramModel.getCreatedBy()%></td>
				<td><%=stm1.format(stm.parse(sponsorshipProgramModel.getCreatedon()))%></td>
				<td style="display:none;"><%=sponsorshipProgramModel.getUpdatedon()%></td>
				<td><% String updatedDate=stm1.format(stm.parse(sponsorshipProgramModel.getUpdatedon()));
				%><%-- <%=sponsorshipProgramModel.getUpdatedon()%> --%><%=updatedDate %></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>
</div>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas
			Corporation Limited</div>
	</div>
</footer>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#sponsorship').DataTable({
			lengthChange: false,bFilter: false, bInfo: false, order: [[8, 'desc']]

		});
	});
	$("#excel").click(function() {
		$("#sponsorship").table2excel({
			// exclude CSS class
			filename : "Sponsorship_detials.xls",//do not include extension
		});
	});
</script>