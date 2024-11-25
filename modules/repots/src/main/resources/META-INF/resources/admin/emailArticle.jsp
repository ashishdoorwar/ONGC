<%@ include file="/init.jsp"%>
<% 
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
%>

 <portlet:actionURL var="saveemailArticleURL" name="saveemailArticle" ></portlet:actionURL>
 
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<aui:form class="form-horizontal" method="POST" name="emailArt" action="<%= saveemailArticleURL %>">
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Email Article"> -->
			<aui:row>
				<aui:col width="100">
					<aui:input type="hidden" name="fromCpf" id="fromCpf" value="<%= userOngc.getCpfNo() %>" />
					<aui:input type="hidden" name="fromName" id="fromName" value="<%= userOngc.getEmpName() %>" />
					<aui:input type="hidden" name="pageurl" value="<%= themeDisplay.getURLCurrent() %>" />
					<aui:input type="hidden" name="pagetitle" value="<%= themeDisplay.getLayout().getName(themeDisplay.getLocale()) %>" />
					<aui:input type="text" required="true" name="name"
						label="Recipient's Name" maxlength="200"
						placeholder="Enter recipient's name" />
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input type="text" required="true" label="Recipient's Mail"
						name="email" maxlength="500"
						placeholder="Enter recipient's mail id" />
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Message" type="textarea" name="message" rows="10"
						class="form-control" maxlength="500"
						placeholder="Enter your message (Max 500 char)">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:button-row>
				<aui:button name="submitButton" type="submit" value="Submit"
					style="background-color: #8c000d;color: #fff !important;" />
			</aui:button-row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>