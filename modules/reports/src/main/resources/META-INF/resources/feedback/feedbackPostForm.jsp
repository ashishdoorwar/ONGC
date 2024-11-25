<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="com.ongc.liferay.reports.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.UserService"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@ include file="/init.jsp" %>

<portlet:defineObjects />
<% 
String backURL = (String) request.getAttribute("backURL");
UserService userService = new UserServiceImpl();
User employee= userService.getUser();
%>
 <div class="formWrapper">
 <div class="row">
 <div class="col-md-12">
 
 <liferay-ui:error key="error" message="Illegal argument found" /> 
 <portlet:actionURL var="postFeedback" name="feedbackPostForm" ></portlet:actionURL>
  <aui:form action="<%= postFeedback %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Message">
			<aui:row>
				<aui:col width="50">
					<aui:input label="CPF No." name="cpfNo" type="hidden" value="<%= employee.getCpfNo() %>"/>
					<aui:input label="Subject" name="subject" type="text" placeholder="Enter Your Subject (Maximum 100 Characters)">
					   <aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:select label="Category" name="category" required="true">
					  <aui:option value="">Select</aui:option>
							<aui:option value="45">Corporate MM</aui:option>
							<aui:option value="51">Drilling Services</aui:option>
    						<aui:option value="52">Exploration</aui:option>
							<aui:option value="44">Finance</aui:option>    						
    						<aui:option value="63">HR/ER- Awards</aui:option>
							<aui:option value="65">HR/ER- Employee Welfare</aui:option>    						
    						<aui:option value="66">HR/ER- Grievance</aui:option>
							<aui:option value="68">HR/ER- Local Issues</aui:option>
							<aui:option value="62">HR/ER- PAR, Seniority, Promotions</aui:option>
							<aui:option value="64">HR/ER- Policy</aui:option>
							<aui:option value="61">HR/ER- Transfers </aui:option>
    						<aui:option value="67">HR/ER- Trusts &amp; Social Security</aui:option>
    						<aui:option value="54">ICE</aui:option>
							<aui:option value="53">IT and Infocom</aui:option>
							<aui:option value="59">Medical</aui:option>
							<aui:option value="69">Offshore</aui:option>
    						<aui:option value="70">Onshore</aui:option>
							<aui:option value="43">Organisation</aui:option>
							<aui:option value="41">Portal</aui:option>
							<aui:option value="55">Safety</aui:option>
							<aui:option value="56">Security</aui:option>
							<aui:option value="57">Transport &amp; Logistics</aui:option>
							<aui:option value="71">Well Services</aui:option>
							<aui:option value="72">Make in India</aui:option>
							<aui:option value="73">ECPF Trust</aui:option>
							<aui:option value="60">Other</aui:option>	
					</aui:select>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
				<label for="Form">From</label> <%= employee.getCpfNo() %><br><br>
				</aui:col>
			</aui:row>
			<aui:row>
				
				<aui:col width="100">
					<!-- <aui:field-wrapper label="Message"> -->
					<label for="cke_<portlet:namespace/>content">Message</label>
						<liferay-ui:input-editor name="content" initMethod="initEditor" width="100" height="400" resizable="true" toolbarSet="liferay-article">
						<aui:validator name="required" />
						</liferay-ui:input-editor>
					<!-- </aui:field-wrapper> -->
				</aui:col>
			</aui:row>
			<!-- <aui:row>
			<aui:col width="50">
					<img src="" alt="Captcha"/>
				</aui:col>
				<aui:col width="50">
					<aui:input label="Captcha" name="captcha" type="text" ><aui:validator name="required" /></aui:input>
				</aui:col>
			</aui:row> -->
			<aui:button-row cssClass="text-center mt-4">
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary btn-sm" />
					<aui:button name="resetButton" type="button"  onClick="this.form.reset()"  value="Reset" cssClass="btn btn-primary btn-sm" />
	<a onclick="javascript:history.go(-1)" class="btn btn-primary btn-sm">Back</a>
			</aui:button-row>
		</aui:fieldset>
		<hr>
		<aui:row>
	<strong>Note</strong>
		<ul class="red_note">       
		  <li>Please use above text editor to write your comments.</li>
	      <li>Copying /pasting text directly from word doc file should be avoided as that may result in exceeding permissible character length (due to hidden tags).  </li>
	      <li>Instead notepad can be used for this purpose. Or copy word document to notepad first before pasting to above text editor </li>
      	</ul>	
    </aui:row>
	</aui:fieldset-group>
	
</aui:form>
</div>
</div>
</div>