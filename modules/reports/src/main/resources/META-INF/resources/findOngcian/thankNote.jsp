 <%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="com.ongc.liferay.reports.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.UserService"%>
<%@ include file="/init.jsp"%>
<liferay-theme:defineObjects />
<portlet:defineObjects />
 <portlet:actionURL var="saveThanksNote" name="thankNote">
</portlet:actionURL>
<% UserService userService = new UserServiceImpl();
User userOngc = userService.getUser();
String cpfNo = (String) request.getAttribute("cpfNo");
%>
<aui:form action="<%= saveThanksNote %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		Note: It is seen that many are using this 'Thank Your Colleague' feature to just register Thanks for frivolous reason. To maintain the sanctity of the purpose of this forum, we request all to kindly register Thanks only for genuine help received.
			<aui:row>
				<aui:col width="100">
					<%-- <aui:input name="currentURL" type="hidden" value="<%= themeDisplay.getURLCurrent() %>"/> --%>
					<aui:input name="tocpfNo" type="hidden" value="<%= cpfNo %>"/>
					<aui:input name="fromcpfNo" type="hidden" value="<%= userOngc.getCpfNo() %>"/>
					<aui:input label="Enter a brief reason for thanking:" name="message" type="textarea" placeholder="Max 200 Character"/>
				</aui:col>
			</aui:row>
			<aui:button-row cssClass="text-right">
				<aui:button name="submitButton" type="submit"  value="Submit" class="btn btn-primary btn-sm"/>
			</aui:button-row>
	</aui:fieldset-group>
</aui:form>

<script>
function closeDialog(){
		
		var successMessageElement = document.getElementsByClassName("alert-success");
		console.log(successMessageElement);
		if(successMessageElement){
			//window.location.reload();
			setTimeout(function(){closePopup();},100);
		}
		
	}
function closePopup() {
	Liferay.Util.getOpener().<portlet:namespace/>closePopup(
			'<portlet:namespace />popUpId', '');
}
	</script>