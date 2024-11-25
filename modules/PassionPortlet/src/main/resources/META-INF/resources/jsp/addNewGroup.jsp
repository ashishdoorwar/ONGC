<%@ include file="/init.jsp"%>


<portlet:actionURL var="addNewGroup" name="add_new_group">
		</portlet:actionURL>
<div class="formWrapper">

<h3>Create New Group</h3>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<aui:form action="${addNewGroup}" name="fm" method="post">
	<aui:fieldset-group markupView="lexicon">
		<aui:row>
			<aui:col width="100">
				<aui:input label="Group Name" name="groupName" type="text" />
			</aui:col>

		</aui:row>
		<aui:row>
			<aui:col width="100">
				<aui:input label="Members" name="q" type="text" onkeyup="getMember()" />
				<aui:select name="members" label="">
				</aui:select>
			</aui:col>
		</aui:row>
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center mt-2">

		<aui:button cssClass="btn btn-primary" type="submit" value="Submit" />

		<aui:button cssClass="btn btn-primary" value="Cancel" />

	</aui:button-row>
</aui:form>
</div>

<portlet:resourceURL id="getMemberUrl" var="getMember"></portlet:resourceURL>
<script>
$( document ).ready(function() {
	 $("#<portlet:namespace />members").hide();
});
function getMember() {
	
	var q = document.getElementById("<portlet:namespace/>q").value;
	AUI().use(
			'aui-io-request',
			function(A) {
				A.io.request('${getMember}', {
					method : 'get',
					dataType : "json",
					cache : true,
					data : {
						<portlet:namespace/>q : q,
					},
					on : {
						success : function() {
							var responseData = new Array();
							responseData = this.get('responseData');
							console.log(responseData);
							 $("#<portlet:namespace />members").show();
							 $("#<portlet:namespace />members").empty();
							for (var i = 0; i < responseData.length; i++) {
								var id = responseData[i]['empCpf'];
								var name = responseData[i]['empName'];
								console.log(name);
								$('#<portlet:namespace />members').append(
										new Option(name, id));
							}
							 if(responseData.length==0){
								 
							 $("#<portlet:namespace />members").hide();
							 }
						},
						error : function() {
							alert("Error occured on server.");
						}
					}
				});
			});
}



</script>