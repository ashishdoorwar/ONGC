<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceFactory"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceUser"%>
<%@ include file="/init.jsp"%>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/datepicker.css")%>'
	type="text/css">

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>



<s:set name="theme" value="'simple'" scope="page" />
<%
	VigilanceUser userData = (VigilanceUser) request.getAttribute("userData");
	System.out.println(userData.getEmailId());
	System.out.println(userData == null);
	List<VigilanceComplaint> list = VigilanceFactory.getComplaintServiceInstance()
			.getComplaintByUserID(userData.getRegistrationId());
%>

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


<header>
	<jsp:include page="/admintemplate/header.jsp"></jsp:include>
</header>
<!--header end-->


<!--Start Main Contant section-->
<div class="container" id="skip-main-content">
	<div class="row">
		<!--left section start-->

		<div class="col-md-3">
			<jsp:include page="/admintemplate/menu.jsp"></jsp:include>
		</div>




		<!--left section end-->

		<!--right section start-->

		<div class="col-md-9">




			<s:set name="theme" value="'simple'" scope="page" />

			<s:form action="viewComplaintDetails" method="post"
				name="complaintForm" theme="simple">
				<s:hidden name="complaint.complaintNo" id="compId" />
			</s:form>
			<s:form cssClass="webform" action="registerComplaint"
				onsubmit="return complaintFormValidation(this);"
				enctype="multipart/form-data" method="POST">


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

				<div>
					<div class="form-item">
						<h1 class="mb5 mt0">View Complaint</h1>
					</div>


					<div class="bg-grey bdr-orange search-form">


						<div class="row">


							<div class="col-md-12">

								<br />
								<div class="form-item">
									<%
										if (list == null || list.size() == 0) {
												System.out.println("Inside if condition");
									%>
									<div>No Record Found</div>
									<%
										} else {
												System.out.println("Inside else condition");
												out.println("Total Results (" + list.size() + ")");
									%>

									<div class="table-responsive sr-table">

										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="table">
											<thead>
												<tr>
													<th>S.No.</th>
													<th>Complaint No.</th>
													<th>Date</th>
													<th width=" 160">Subject</th>
													<!-- <th>Complaint Against</th> -->
													<th>Location</th>
													<th>Status</th>
												</tr>
											</thead>
											<%
												java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
														for (int i = 0; i < list.size(); i++) {

															VigilanceComplaint complaint = list.get(i);
															String comAgnst = "";
															String comName = "";
															if (complaint.getComplaintAgainst() != null)
																comAgnst = complaint.getComplaintAgainst().trim().equals("-1") ? ""
																		: complaint.getComplaintAgainst();
															if (complaint.getCountry() != null)
																comName = complaint.getCountry().equals("-1") ? "" : complaint.getCountry();
											%>
											<portlet:renderURL var="viewcomplaintDtlpage">
												<portlet:param name="mvcPath"
													value="/jsp/UserComplaintViewDetails.jsp" />
												<portlet:param name="emailId"
													value="<%=userData.getEmailId() %>" />
												<portlet:param name="complaintNo"
													value="<%=String.valueOf(complaint.getComplaintNo()) %>" />
											</portlet:renderURL>

											<tbody>
												<tr>
													<td>
														<%
															out.print(i + 1);
														%>
													</td>
													<td><a href="<%=viewcomplaintDtlpage%>"><span
															onclick="viewcomplaintDtlpage(<%=complaint.getComplaintNo()%>)"
															style="color: blue; font-weight: bold; cursor: pointer"><%=complaint.getComplaintActNo()%></span>
													</a></td>
													<td><%out.print(complaint.getComplaintDate() ==null? "" : new SimpleDateFormat("dd-MM-yyyy").format(complaint.getComplaintDate())); %></td> 
													
													<td>
														<%
															out.print(complaint.getComplaintSubject());
														%>
													</td>
													<%-- <td>
														<%
															out.print(comAgnst);
														%>
													</td>
												 --%>
													 <td>
														<%out.print(comName); %>
													</td>
													<td>
														<%out.print(complaint.getAction()==null?"":""); %>
													</td>
												</tr>
												<%} %>
											</tbody>
										</table>
										<%}%>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</s:form>

			<div id="lightbox"></div>
			<!-- /lightbox -->



		</div>
	</div>
</div>
<footer>
	<jsp:include page="/admintemplate/footer.jsp"></jsp:include>
</footer>
