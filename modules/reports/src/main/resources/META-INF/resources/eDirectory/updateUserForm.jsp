<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.Properties"%>
<%@page import="com.ongc.liferay.reports.model.User"%>
<%@ include file="/init.jsp"%>

<portlet:actionURL var="updateUser" name="update_user">
</portlet:actionURL>

<%
	User userData = (User) request.getAttribute("employee");
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
%>
<%-- <div div class="row">
	<div class="col-md-12">
		<img style="width: 100%;"
			src="<%= themeDisplay.getURLPortal() %>/o/blade/servlet/imageServlet?cpfno=<%=userData.getCpfNo()%>">
	</div>
</div> --%>
<!-- <div class="brad-crum">
	<div class="row m-2 p-2">
		<div class="col-md-6">
			<a href="/wps/wcm/myconnect/reports_en/home/" title="Home">Home </a>|
			My Profile
		</div>
		<div class="col-md-6 text-right">
			<a href="javascript:void(0)" onclick="javascript:history.back();"
				title="Click to go back"><i class="fa fa-arrow-left"></i> Back</a>&nbsp;
			| &nbsp;<a title="Click to Print" href="javascript:void(0)"
				onclick=" window.print();"><i class="fa fa-print fa-lg"
				title="Print"></i></a>
		</div>
	</div>


</div> -->

<div class="row">
	<div class="col-md-12 ">
		<aui:form action="${updateUser}" name="fm" method="post">
			<aui:fieldset-group markupView="lexicon">
				<aui:row>
					<aui:col width="50"><img class="rounded img-thumbnail" style="max-width:63px;"
			src="http://uat-reports.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=userData.getCpfNo()%>">
						<aui:input label="Profile Pic :" name="uploadedFile" type="file" />
					</aui:col>
					<aui:col width="50">
						<p>
							<span class="text-danger">#</span> In case of incorrect mobile
							number, please get the same updated in SAP, by your local
							Infocom/HR.
						</p>
						<p>
							<span class="text-danger">*</span> Fields are Mandatory
						</p>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="CPF Number :" name="cpfNum" 
							value="<%=userData.getCpfNo()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Name of Employee :" name="empname" disabled="true"
							value="<%=userData.getEmpName()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Designation" name="designation" disabled="true"
							value="<%=userData.getDesignation()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Employee Level :" name="emplevel" disabled="true"
							value="<%=userData.getEmpLevel()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Place of Posting :" name="placeposting" disabled="true"
							value="<%=userData.getPlacePosting()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Blood Group :" name="bloodgroup" disabled="true"
							value="<%=userData.getBloodGroup()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Date of Birth :" name="dob" disabled="true"
							value="<%=sdf1.format(userData.getDateOfBirth())%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Date of Joining :" name="doj" disabled="true"
							value="<%=sdf1.format(userData.getDateOfJoining())%>" type="text" />
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="50">
						<aui:input label="Work place :" name="workplace"
							value="<%=userData.getWorkPlace()%>" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Department :" name="department"
							value="<%=userData.getDepartment()%>" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Mobile No :" name="mobileno" disabled="true"
							value="<%=userData.getMobileNo()%>" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Fax Number :" name="faxno"
							value="<%=userData.getFaxNumber()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Email-ID (Official) :" name="emailidoff"
							value="<%=userData.getEmailIdOfficial()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Email-ID (Personal) :" name="emailidper"
							value="<%=userData.getEmailIdPersonal()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Office Direct No :" name="phoneoff"
							value="<%=userData.getPhoneNumberOffice()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Office Board No :" name="boardoff"
							value="<%=userData.getBoardNumber()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Office Extension No :" name="extoff"
							value="<%=userData.getExtensionNumber()%>" type="text" />
					</aui:col>
					<aui:col width="50">
						<aui:input label="Residence no :" name="phoneres"
							value="<%=userData.getPhoneNumberHome()%>" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="Office Address :" name="officeaddr"
							value="<%=userData.getOfficeAddress()%>" type="textarea">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="50">
						<aui:input label="Residence Address :" name="residenceaddr"
							value="<%=userData.getResidenceAddress()%>" type="textarea" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="50">
						<aui:input label="About Me :" name="aboutMe"
							value="<%=userData.getAboutMe()%>" type="textarea" />
					</aui:col>
					<%-- <aui:col width="50">
						<aui:input label="Captcha:" name="captchaVal"
							value="<%=userData.getCaptchaVal()%>" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col> --%>
				</aui:row>
				<aui:row>
					<aui:col width="50">
					</aui:col>
					<aui:col width="50">
					</aui:col>
				</aui:row>

			</aui:fieldset-group>
			<aui:button-row cssClass="p-2">
				<aui:button cssClass="btn btn-primary" type="submit" value="Modify" />
				<aui:button cssClass="btn btn-primary" value="Cancel" />
			</aui:button-row>
		</aui:form>
	</div>
</div>
