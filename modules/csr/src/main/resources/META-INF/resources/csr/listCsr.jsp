<%@page import="com.ongc.liferay.csr.model.CSRProgramModel"%>
<%@ include file="/init.jsp"%>
<%@ include file="/csr/header.jsp"%>
<div class="container">
	<h1>View Details</h1>
	<%
		CSRProgramModel csrProgramModel = (CSRProgramModel) request.getAttribute("cSRProgramModel");
	%>
	<div class="table-responsive">
		<table class="table table-bordered table-striped tableDetails"
			id="viewData">
			<thead></thead>
			<tbody>
				<tr>
					<td width="20%">Reference No.</td>
					<td><%=csrProgramModel.getRefNo()%></td>
				</tr>
				<tr>
					<td>Created By</td>
					<td><%=csrProgramModel.getCreatedBy()%></td>
				</tr>

				<tr>
					<td>Subject</td>
					<td><%=csrProgramModel.getSubjectName()%></td>
				</tr>
				<tr>
					<td>Name / Title of Program</td>
					<td><%=csrProgramModel.getProgramName()%></td>
				</tr>
				<tr>
					<td>Program Cost</td>
					<td><%=csrProgramModel.getProgramCost()%></td>
				</tr>
				<tr>
					<td>Program Tenure:</td>
					<td>from Date:<%=csrProgramModel.getProgramDate()%>
						&nbsp;&nbsp;&nbsp;&nbsp;To Date:<%=csrProgramModel.getProgramDateTo()%></td>
				</tr>
				<tr>
					<td>Date of Receipt of Proposal</td>
					<td><%=csrProgramModel.getProposalReciptDate()%></td>
				</tr>
				<tr>
					<td>Nature of Program</td>
					<td><%=csrProgramModel.getProgramNatureName()%></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><%=csrProgramModel.getEmail()%></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><%=csrProgramModel.getMobileNo()%></td>
				</tr>
				<tr>
					<td>Contact person for Project/Program</td>
					<td><%=csrProgramModel.getContactPerson()%></td>
				</tr>
				<tr>
					<td>Nature of Activity (Schedule VII)</td>
					<td><%=csrProgramModel.getActivityNature()%></td>
				</tr>

				<tr>
					<td>IT Exemption 80G</td>
					<td><input type="checkbox" name="csrBean.exemption80G" disabled = "true"
						value="<%=csrProgramModel.getExemption80G() %>" id="csrBean_exemption80G"><input
						type="hidden" id="__checkbox_csrBean_exemption80G" disabled = "true"
						name="__checkbox_csrBean.exemption80G" value="<%=csrProgramModel.getExemption80G() %>"></td>
					
				</tr>
				<tr>
				<td>IT Exemption 12AA</td>

					<td><input type="checkbox" name="csrBean.exemption12AA"  disabled = "true"
						value="<%=csrProgramModel.getExemption12AA() %>" id="csrBean_exemption12AA"><input
						type="hidden" id="__checkbox_csrBean_exemption12AA" disabled = "true"
						name="__checkbox_csrBean.exemption12AA" value="<%=csrProgramModel.getExemption12AA() %>"></td>
				</tr>
				<tr>
					<td>Approving Authority as per BDP</td>
					<td><%=csrProgramModel.getApprovingAuthorityAsPerBDMName()%></td>
				</tr>
				<tr>
					<td>Designation in Agency</td>
					<td><%=csrProgramModel.getDesignarionInAgency()%></td>
				</tr>
				<tr>
					<td>Legal Status</td>
					<td><%=csrProgramModel.getLegalStatus()%></td>
				</tr>

				<tr>
					<td>Status</td>
					<td><%=csrProgramModel.getStatusName()%></td>
				</tr>

				<tr>
					<td>Remarks</td>
					<td><%=csrProgramModel.getRemark()%></td>
				</tr>
				<!-- 	-- -->
				<tr>
					<td>Disha file number</td>
					<td><%=csrProgramModel.getDishaFileNumber()%></td>
				</tr>
				<tr>
					<td>FR Number</td>
					<td><%=csrProgramModel.getFrNumber()%></td>
				</tr>
				<tr>
					<td>Tracking Number</td>
					<td><%=csrProgramModel.getTrackingNumber()%></td>
				</tr>
				<tr>
					<td>Proposal Attachment</td>
					<td><%=csrProgramModel.getProposalDocFileName()%></td>

					</a>
					</td>
				</tr>
				<tr>
					<td>Release Order Attachment</td>
					<td><%=csrProgramModel.getReleaseOrderDoc()%></td>
				</tr>
				<tr>
					<td>80G Attachment</td>
					<td><a href="/wps/PA_ONGC_CSR/csrdoc/80G_REF28_" download="">

					</a></td>
				</tr>
				<tr>
					<td>12AA Attachment</td>
					<td><a href="/wps/PA_ONGC_CSR/csrdoc/12A_REF28_" download="">

					</a></td>
				</tr>
				<tr>
					<td>Receipt Attachment</td>
					<td><a href="/wps/PA_ONGC_CSR/csrdoc/RCPT_REF28_" download="">

					</a></td>
				</tr>
				<tr>
					<td>File Currently with</td>
					<td>ban</td>
				</tr>
			</tbody>
		</table>

	</div>
	<div class="col-md-12 text-center">


		<portlet:renderURL var="editCsrProgram">
			<portlet:param name="mvcRenderCommandName" value="edit_csr_program" />
			<portlet:param name="id"
				value="<%=Integer.toString(csrProgramModel.getId())%>" />
		</portlet:renderURL>
		<portlet:actionURL var="deleteCsrProgram" name="delete_csr_program">
			<portlet:param name="id"
				value="<%=Integer.toString(csrProgramModel.getId())%>" />

		</portlet:actionURL>
<%if(loginId == csrProgramModel.getCreatedBy()){ %>
		<a id="editTag" href="<%=editCsrProgram%>">
			<button class="btn btn-primary">Edit</button>
		</a> <a id="deleteTag" href="<%=deleteCsrProgram%>">
		<button
				class="btn btn-primary">Delete</button> </a>
			<%} %>	
				 <input type="submit" id=""
			value="Back" class="btn btn-primary"
			onclick="javascript:history.go(-1)">

	</div>
</div>
<!------------footer start------------>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas Corporation
			Limited</div>
	</div>
</footer>