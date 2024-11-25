<%@ include file="/init.jsp" %>

 <liferay-ui:error key="error" message="Illegal argument found" /> 
 
 <liferay-ui:success key="Success" message="Post Submitted Successfully!" /> 
<portlet:renderURL var="getQueriesData">
	<portlet:param name="mvcRenderCommandName" value="getAskExpertData"/>
</portlet:renderURL>

<portlet:renderURL var="askExpertForm">
	<portlet:param name="mvcPath" value="/askExpert/askExpertForm.jsp"/> 
</portlet:renderURL>

<div class="container">
		<div class="row" align="center">
			<div class="col-lg">
				<a onclick="showAskExpertView()" class="btn btn-primary btn-sm" data-toggle="modal">Queries and Expert Views</a>
				<a onclick="showAskExpertForm()" class="btn btn-primary btn-sm" href="" >Post Your Query</a>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
	 function showAskExpertView(){
			window.location.href='<%= getQueriesData %>';
		}
	 function showAskExpertForm(){
			window.location.href='<%= askExpertForm %>';
		}
</script>