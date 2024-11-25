<%@ include file="/init.jsp" %>
<%
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName()); %>
 <portlet:actionURL var="savecovid19Form" name="savecovidForm" ></portlet:actionURL>
<div class="row">
	<div class="col-lg-12">
		<div class="formWrapper">
			<aui:form action="<%= savecovid19Form %>" method="post" name="myForm" >
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="OCAY (ONGC COVID-19 Assistance for you)"> -->
					<aui:row>
						<aui:col width="100">
							<aui:input type="hidden" name="cpfNo" value="<%= userOngc.getCpfNo() %>">
							</aui:input>
						</aui:col>
						<aui:col width="100">
						<aui:input label="Comment" type="textarea" name="comment" rows="10" class="form-control"
							 maxlength="3000"><aui:validator name="required" /></aui:input>
						</aui:col>
					</aui:row>
					<aui:button-row >
						<div class="col-md-12 text-center">
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-sm btn-primary" />
							<a class="btn btn-sm btn-primary " onclick="javascript:history.back();" target="" title="">Back</a>
						</div>
						</aui:button-row>
						</aui:fieldset>
						</aui:fieldset-group>
		</aui:form>
	</div>
	</div>
</div>
