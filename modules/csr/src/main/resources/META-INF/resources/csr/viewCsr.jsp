<%@page import="com.ongc.liferay.csr.model.SubjectBean"%>
<%@page import="com.ongc.liferay.csr.model.StatusBean"%>
<%@page import="com.ongc.liferay.csr.model.CSRProgramModel"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="openCsrForm">
	<portlet:param name="mvcPath" value="/csr/editCsr.jsp" />
</portlet:renderURL>
<portlet:renderURL var="searchCSR">
	<portlet:param name="mvcRenderCommandName" value="search_csr" />
</portlet:renderURL>
<!--<link rel="stylesheet" type="text/css"
	href='<%=request.getContextPath()%>/css/style.css'>
<link rel="stylesheet" type="text/css"
	href='<%=request.getContextPath()%>/css/style-admin.css'>-->

<%
	List<CSRProgramModel> cSRProgramModels = (List<CSRProgramModel>) request.getAttribute("cSRProgramModels");
	List<SubjectBean> subjectBeans = (List<SubjectBean>) request.getAttribute("subjectBeans");
	List<StatusBean> statusBeans = (List<StatusBean>) request.getAttribute("statusBeans");
%>

<!-- Header -->
<div class="container">
	<%@ include file="/csr/header.jsp"%>





	<div class="csrContainer">
		<div class="csrheader">
			<h1>List of CSR Program / Project</h1>
		</div>
		<div align="right">
			<a href="<%=openCsrForm%>" class="btn btn-primary btn-sm"> Add <span
				class="fa fa-plus-circle"></span>
			</a>
		</div>


		<aui:form action="${searchCSR}" name="fm" method="post">
			<aui:fieldset-group markupView="lexicon">
				<aui:row>
					<aui:col width="25">

						<aui:select label="Subject" name="subName" id="subName">
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
						<aui:input label="Program Name" name="programName" type="text" />
					</aui:col>
					<aui:col width="25">
						<aui:input label="Reference Number" name="refNo" type="text" />
					</aui:col>
					<aui:col width="25">
						<aui:select label="Status" name="status" id="status">
							<aui:option value=''>Select Status</aui:option>
							<%
													for (StatusBean statusBean : statusBeans) {
												%>
							<aui:option value="<%=statusBean.getId()%>"><%=statusBean.getStatus()%></aui:option>
							<%
													}
												%>
						</aui:select>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="25">
						<aui:input label="Created By" name="createdBy" type="text" />
					</aui:col>
				</aui:row>
				<aui:button-row cssClass="text-center">
					<aui:button cssClass="btn btn-primary" type="submit" value="Search" />
				</aui:button-row>
			</aui:fieldset-group>
		</aui:form>


		<p>
			<button id="excel" class="btn btn-primary">Export to excel</button>
		</p>
		<div class="csr_table">
			<div class="table-responsive">
				<table
					class="table table-striped stripe row-border order-column table-bordered"
					id="csr" width="100%">

					<thead>
						<tr>
							<th>Reference Number</th>
							<th>Subject</th>
							<th>Program Name</th>
							<th>Status</th>
							<th>Program Nature</th>
							<th>Created By</th>
							<th>Creation Date</th>

						</tr>
					</thead>
					<%
										for (CSRProgramModel csrProgramModel : cSRProgramModels) {
									%>
					<tr>
						<portlet:renderURL var="viewCsrProgramModel">
							<portlet:param name="mvcRenderCommandName"
								value="view_csr_program_model" />
							<portlet:param name="id"
								value="<%=Integer.toString(csrProgramModel.getId())%>" />
						</portlet:renderURL>
						<td><a href="<%=viewCsrProgramModel%>"><%=csrProgramModel.getRefNo()%></a></td>
						<td><%=csrProgramModel.getSubjectName()%></td>
						<td><%=csrProgramModel.getProgramName()%></td>
						<td><%=csrProgramModel.getStatusName()%></td>
						<td><%=csrProgramModel.getProgramNatureName()%></td>
						<td><%=csrProgramModel.getCreatedBy()%></td>
						<td><%=csrProgramModel.getCreatedOn()%></td>

					</tr>

					<%
				}
			%>

				</table>
			</div>
		</div>
	</div>
	<!------------footer start------------>
	<footer class="login-footer bg-dark-blue light-blue-clr">
		<div class="row">
			<div class="col-xs-12">Copyright Oil and Natural Gas
				Corporation Limited</div>
		</div>
	</footer>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#csr').DataTable({
			lengthChange: false,bFilter: false, bInfo: false


		});
	});

	$("#excel").click(function() {
		$("#csr").table2excel({
			// exclude CSS class
			filename : "CSR_Details.xls",//do not include extension
		});
	});
</script>