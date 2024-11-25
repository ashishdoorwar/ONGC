<%@ include file="/init.jsp" %>
<% 
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
String msg = (String) request.getAttribute("msg");
if(msg!=null){ %>
<div class="alert alert-success"> Feedback Submitted Successfully. </div>
<% } %>

 <portlet:actionURL var="savechieflabFeed" name="savechieflabFeedForm" >
 	<portlet:param name="mvcPath" value="/chiefLabs/view.jsp"/>
 </portlet:actionURL>
 
 <liferay-ui:error key="error" message="Illegal argument found" /> 
		<aui:form action="<%= savechieflabFeed %>" method="post" name="myForm" onsubmit=" return saveFeedback()">
	<aui:fieldset-group markupView="lexicon">
	<!-- 	<aui:fieldset label="Message"> -->
			<aui:row>
				<aui:col width="50">
					<aui:input label="CPF No." name="cpfNo" type="hidden" value="<%= userOngc.getCpfNo() %>"/>
					<aui:input label="First Name"  type="text" name="firstName"  maxlength="200" placeholder="Enter First Name">
					   <aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
				<aui:input label="Last Name"  type="text" name="lastName"  maxlength="200" placeholder="Enter Last Name">
					   <aui:validator name="required" />
					</aui:input>	
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Mobile Number"  type="text" name="mobile" maxlength="10" placeholder="Enter the Mobile Number">
					   <aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
				<aui:input label="Email-ID"  type="text" name="email" maxlength="100" placeholder="Enter the Email Id">
					   <aui:validator name="required" />
				</aui:input>	
				</aui:col>
			</aui:row>
			<aui:row>
			<aui:col width="100">
				<aui:input label="Comment" type="textarea" name="comment" rows="10" class="form-control"
							 maxlength="3000"><aui:validator name="required" /></aui:input>
			</aui:col>
			</aui:row>
			<aui:button-row >
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary btn-sm" />
			</aui:button-row>
		</aui:fieldset>
		<hr>
	</aui:fieldset-group>
</aui:form>

