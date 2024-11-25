<%@ include file="/init.jsp"%>
<div class="container">
	<%@ include file="/sponsorship/header.jsp"%>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<portlet:renderURL var="createUser">
	<portlet:param name="mvcRenderCommandName" value="createUser"  />
</portlet:renderURL>
	<main> <aui:form action="${createUser}" name="fm" method="post">
		<aui:fieldset-group markupView="lexicon">
			<aui:row>
				<aui:col width="50">
					<aui:input label="CPF No." name="cpfNo" type="text">

						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Incharge Name" name="userName" type="text">

						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="location" name="location" type="text">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Designation" name="designation" type="text">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Email Id" name="emailId" type="email">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Mobile" name="mobileNo" type="text">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="About Me" name="aboutMe" type="text">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:button-row cssClass="text-center">
				<aui:button cssClass="btn btn-primary btn-sm" type="submit" />
			</aui:button-row>

		</aui:fieldset-group>
		</aui:form></main>
</div>
</body>
</html>
