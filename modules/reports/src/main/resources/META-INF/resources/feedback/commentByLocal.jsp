<%@ include file="/init.jsp"%>
<liferay-theme:defineObjects />
<portlet:defineObjects />
<% String postId = (String) request.getAttribute("postId"); %>
 <portlet:actionURL var="commentLocalEnabler" name="CommentLocal" ></portlet:actionURL>
<aui:form action="<%= commentLocalEnabler %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<aui:row>
			<aui:col width="100">
				<aui:input label="" value="<%= postId %>" name="postID" type="hidden" />
				<aui:input name="comment" type="textarea" label="Comment"></aui:input>
			</aui:col>
		</aui:row>
		<aui:button-row cssClass="text-center">
				<aui:button name="submitButton" type="submit" onclick="closeDialog();" value="Close & Submit" cssClass="btn-primary btn btn-sm"/>
			</aui:button-row>
	</aui:fieldset-group>
</aui:form>

<script>
function closeDialog(){
		
		var successMessageElement = document.getElementsByClassName("alert-success");
		console.log(successMessageElement);
		if(successMessageElement){
			setTimeout(function(){closePopup();},100);
		}
		
	}
function closePopup() {
	Liferay.Util.getOpener().<portlet:namespace/>closePopup(
			'<portlet:namespace />popUpId', '');
}
	</script>