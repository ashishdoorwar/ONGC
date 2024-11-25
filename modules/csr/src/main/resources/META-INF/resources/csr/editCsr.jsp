<%@page import="com.ongc.liferay.csr.model.ProgramNatureBean"%>
<%@page
	import="com.ongc.liferay.csr.service.Impl.ProgramNatureServiceImpl"%>
<%@page import="com.ongc.liferay.csr.service.ProgramNatureService"%>
<%@page
	import="com.ongc.liferay.csr.service.Impl.ApprovingAuthorityServiceImpl"%>
<%@page import="com.ongc.liferay.csr.service.ApprovingAuthorityService"%>
<%@page import="com.ongc.liferay.csr.model.ApprovingAuthorityBean"%>
<%@page import="com.ongc.liferay.csr.service.Impl.SubjectServiceImpl"%>
<%@page import="com.ongc.liferay.csr.service.Impl.StatusServiceImpl"%>
<%@page import="com.ongc.liferay.csr.service.SubjectService"%>
<%@page import="com.ongc.liferay.csr.service.StatusService"%>
<%@page import="com.ongc.liferay.csr.model.SubjectBean"%>
<%@page import="com.ongc.liferay.csr.model.StatusBean"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.csr.model.CSRProgramModel"%>
<%@ include file="/init.jsp"%>
<portlet:actionURL var="addCSR" name="add_csr">
	<portlet:param name="mvcPath" value="/csr/viewCsr.jsp" />
</portlet:actionURL>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<%
	CSRProgramModel cSRProgramModel = (CSRProgramModel) request.getAttribute("cSRProgramModel");
	if (cSRProgramModel == null) {
		cSRProgramModel = new CSRProgramModel();

	}
	StatusService statusService = new StatusServiceImpl();
	SubjectService subjectService = new SubjectServiceImpl();
	ApprovingAuthorityService approvingAuthorityService = new ApprovingAuthorityServiceImpl();
	ProgramNatureService natureService = new ProgramNatureServiceImpl();

	List<StatusBean> statusBeans = statusService.getAllStatus();
	List<SubjectBean> subjectBeans = subjectService.getSubjectList();
	List<ApprovingAuthorityBean> authorityBeans = approvingAuthorityService.getApprovingAuthList();
	List<ProgramNatureBean> natureBeans = natureService.getProgramNatureList();
%>
<div class="container">
	<%@ include file="/csr/header.jsp"%>


	<div>
		<h1>CSR Program / Project</h1>
	</div>
	<div>
		<aui:form action="${addCSR}" name="fm" method="post">
			<aui:fieldset-group markupView="lexicon">
				<aui:row>
					<aui:col width="50">
						<aui:input label="" name="id" type="hidden"
							value="<%=cSRProgramModel.getId()%>" />
						<aui:select label="Subject" name="subId" id="subId"
							value="<%=cSRProgramModel.getSubject()%>">
							<aui:option value=''>Select Subject</aui:option>
							<%
								for (SubjectBean subjectBean : subjectBeans) {
							%>
							<aui:option value="<%=subjectBean.getSubId()%>"><%=subjectBean.getSubName()%></aui:option>
							<%
								}
							%>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Name / Title of Program" name="programName"
							value="<%=cSRProgramModel.getProgramName()%>" type="text">
							<aui:validator name="maxLength">200</aui:validator>
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Program Cost:" name="programCost"
							value="<%=cSRProgramModel.getProgramCost()%>" type="text">
							<aui:validator name="maxLength">10</aui:validator>
							<aui:validator name="required" />
						</aui:input>
					</aui:col>

				</aui:row>

				<aui:row>
					<aui:col width="50">
						<aui:input label="From Date:" name="programDate"
							value="<%=cSRProgramModel.getProgramDate()%>" type="date">
							<aui:validator name="required" />
							
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:select label="Nature of Program" name="programNature"
							id="programNature"
							value="<%=cSRProgramModel.getProgramNature()%>">
							<aui:option value=''>Select Nature of Program</aui:option>
							<%
								for (ProgramNatureBean natureBean : natureBeans) {
							%>
							<aui:option value="<%=natureBean.getId()%>"><%=natureBean.getName()%></aui:option>
							<%
								}
							%>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
					<aui:col width="50">
						<aui:input label="To Date: " name="programDateTo"
							value="<%=cSRProgramModel.getProgramDateTo()%>" type="date">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
				</aui:row>


				<aui:row>
					<aui:col width="50">
						<aui:input label="Date of Receipt of Proposal"
							name="proposalReciptDate"
							value="<%=cSRProgramModel.getProposalReciptDate()%>" type="date">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Financial Assistance from ONGC:"
							name="fundingFrmOngc"
							value="<%=cSRProgramModel.getFundingFrmOngc()%>" type="text">
							<aui:validator name="required" />
							<aui:validator name="maxLength">100</aui:validator>
						</aui:input>
					</aui:col>

				</aui:row>

				<aui:row>
					<aui:col width="50">
						<aui:input label="Name of Implementing Agency:"
							name="implementingAgencyName"
							value="<%=cSRProgramModel.getImplementingAgencyName()%>"
							type="text">
							<aui:validator name="required" />
							<aui:validator name="maxLength">100</aui:validator>
						</aui:input>

					</aui:col>

					<aui:col width="50">
						<aui:input label="Applicable GST:" name="applicableGst"
							value="<%=cSRProgramModel.getApplicableGst()%>" type="text">
							<aui:validator name="required" />
							<aui:validator name="maxLength">100</aui:validator>
						</aui:input>
					</aui:col>

				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:fieldset-group>
							<aui:field-wrapper name="legalStatus" required="true">
								<label class="aui-field-label">Legal Status: </label>
								<aui:input label="Section 8 Company" name="legalStatus"
									value="<%=cSRProgramModel.getImplementingAgencyName()%>"
									type="radio" />
								<aui:input label="NGO" name="legalStatus"
									value="<%=cSRProgramModel.getImplementingAgencyName()%>"
									type="radio" />
								<aui:input label="Trust" name="legalStatus"
									value="<%=cSRProgramModel.getImplementingAgencyName()%>"
									type="radio" />
							</aui:field-wrapper>
						</aui:fieldset-group>
					</aui:col>
					<aui:col width="50">
						<aui:fieldset-group>
							<label class="aui-field-label">IT Exemption: </label>
							<aui:input name="check" label="80G" type="checkbox" value="80G"></aui:input>
							<aui:input name="check" label="12AA" type="checkbox" value="12AA"></aui:input>
						</aui:fieldset-group>
					</aui:col>

				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:fieldset-group>
							<label class="aui-field-label">Legal Status of
								Implementing Partner: </label>
							<aui:input label="Company" name="checkLegalStatus"
								value="Company" type="checkbox" />
							<aui:input label="Section 8 Company " name="checkLegalStatus"
								value="Section 8 Company" type="checkbox" />
							<aui:input label="NGO" name="checkLegalStatus" value="NGO"
								type="checkbox" />
							<aui:input label="Trust" name="checkLegalStatus" value="Trust"
								type="checkbox" />
						</aui:fieldset-group>

					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Contact person for Project/Program:"
							name="contactPerson"
							value="<%=cSRProgramModel.getContactPerson()%>" type="text" >
							<aui:validator name="maxLength">100</aui:validator>
							</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Email:" name="email"
							value="<%=cSRProgramModel.getEmail()%>" type="email" />
					</aui:col>

				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="80G Attachment:" name="eightyGDoc"
							value="<%=cSRProgramModel.getEightyGDocFileName()%>" type="file" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="12AA Attachment:" name="twelveAADoc"
							value="<%=cSRProgramModel.getTwelveAADocFileName()%>" type="file" />
					</aui:col>

				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Receipts:" name="receiptsDoc"
							value="<%=cSRProgramModel.getReceiptsDoc()%>" type="file" />
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Designation in Agency:"
							name="designarionInAgency"
							value="<%=cSRProgramModel.getDesignarionInAgency()%>" type="text" >
							<aui:validator name="maxLength">100</aui:validator>
							</aui:input>
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Mobile Number:" name="mobileNo"
							value="<%=cSRProgramModel.getMobileNo()%>" type="text" >
							<aui:validator name="maxLength">15</aui:validator>
							<aui:validator name="custom"
								errorMessage="You can enter a maximum of 15 numeric and special characters(like +)">
							  function (val, fieldNode, ruleValue) {
							  var returnValue = true;
							  var iChars = "~`!@#$%^&-*()_=[]\\\';,./{}|\":<>?qwertyuiopasdfghjklzxcvbnm";
							                for (var i = 0; i < val.length; i++) {
							                    if (iChars.indexOf(val.charAt(i)) != -1) {                
							                     returnValue = false;
							                    }
							                }
							                return returnValue;
							        }
							</aui:validator>
							</aui:input>
							
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Nature of Activity (Schedule VII):"
							name="activityNature"
							value="<%=cSRProgramModel.getActivityNature()%>" type="text">
							<aui:validator name="required" />
							<aui:validator name="maxLength">100</aui:validator>
						</aui:input>
					</aui:col>
					<aui:col width="50">

						<aui:select label="Approving Authority as per BDP:"
							name="programNatureBDP" id="programNatureBDP"
							value="<%=cSRProgramModel.getApprovingAuthorityAsPerBDM()%>">
							<aui:option value=''>Select pproving Authority as per BDP:</aui:option>
							<%
								for (ApprovingAuthorityBean authorityBean : authorityBeans) {
							%>
							<aui:option value="<%=authorityBean.getId()%>"><%=authorityBean.getName()%></aui:option>
							<%
								}
							%>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Disha file number" name="dishaFileNumber"
							value="<%=cSRProgramModel.getDishaFileNumber()%>" type="text" >
							<aui:validator name="maxLength">500</aui:validator>
							</aui:input>
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="FR Number" name="frNumber"
							value="<%=cSRProgramModel.getFrNumber()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Tracking Number" name="trackingNumber"
							value="<%=cSRProgramModel.getTrackingNumber()%>" type="text" />
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Proposal Attachment (upto 5 mb)"
							name="proposalDoc" value="<%=cSRProgramModel.getProposalDoc()%>"
							type="file" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Release Order Attachment (upto 2 mb)"
							name="releaseOrderDoc"
							value="<%=cSRProgramModel.getReleaseOrderDocFileName()%>"
							type="file" />
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:select label="Status" name="status" id="status"
							value="<%=cSRProgramModel.getStatus()%>">
							<aui:option value=''>Select Status</aui:option>
							<%
								for (StatusBean statusBean : statusBeans) {
							%>
							<aui:option value="<%=statusBean.getId()%>"><%=statusBean.getStatus()%></aui:option>
							<%
								}
							%>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Remarks" name="remark"
							value="<%=cSRProgramModel.getRemark()%>" type="text" >
							<aui:validator name="maxLength">500</aui:validator>
							</aui:input>
					</aui:col>


				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="File Currently with" name="filecurrentlywith"
							value="<%=cSRProgramModel.getFilecurrentlywith()%>" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>



				</aui:row>
			</aui:fieldset-group>
			<aui:button-row cssClass="text-center">
				<%
					if (cSRProgramModel.getId() != null) {
				%>
				<aui:button cssClass="btn btn-primary btn-sm" type="submit"
					value="Update" />

				<%
					} else {
				%>
				<aui:button cssClass="btn btn-primary btn-sm" type="submit" />
				<%
					}
				%>
				<aui:button type="cancel" cssClass="btn btn-primary btn-sm"
					value="Reset" onclick="this.form.reset()" />
			</aui:button-row>
		</aui:form>
	</div>
</div>
<!------------footer start------------>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas Corporation
			Limited</div>
	</div>
</footer>