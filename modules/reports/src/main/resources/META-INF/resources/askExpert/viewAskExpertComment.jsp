<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.reports.model.ExpertReply"%>
<%@page import="com.ongc.liferay.reports.model.AskExpert"%>
<%@ include file="/init.jsp" %>
<%
AskExpert askExpert = (AskExpert) request.getAttribute("askExpert");
List<ExpertReply> askExpertReply = (List<ExpertReply>) request.getAttribute("replyList");
String backURL = (String) request.getAttribute("backURL");
int srn=1;
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
%>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<portlet:renderURL var="askExpertReplyForm" >
<portlet:param name="mvcPath" value="/askExpert/askExpertReplyForm.jsp"/>
	<portlet:param name="mvcRenderCommandName" value="askExpertReply"/>
	<portlet:param name="queryId" value="<%= Integer.toString(askExpert.getQueryid()) %>"/>
	<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>"></portlet:param>
</portlet:renderURL>
<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
<table class="table table-bordered table-striped">
	<tr><td><a title="Click to view full profile" onclick="popup('<%= askExpert.getCpfno() %>')"><%= askExpert.getUsername()  %></a></td></tr>
	<tr><td><%= sdf1.format(askExpert.getPosted_on())  %></td></tr>
	<tr><td><b>Domain: <%= askExpert.getDomainName() %></b></td></tr>
	<tr><td><p><%= askExpert.getMessage() %></p></td></tr>
	<% for(ExpertReply post:askExpertReply){ %>
	<tr><td><table class="table table-bordered table-striped mb0">
	<tr><td><a title="Click to view full profile" onclick="popup('<%= post.getCpfno()%>')"><%= post.getUserName() %></a></td></tr>
	<tr><td><%= sdf1.format(post.getPosted_on()) %></td></tr>
	<tr><td><% if(post.getIsExpert()!=""){ if("Y".equalsIgnoreCase(post.getIsExpert())) {%>
			<strong>Authorized Comment: </strong><br/><br/><span style="color:#8c000d"><%= post.getReplyMessage() %></span>
			<%} else{%><%= post.getReplyMessage() %><%}} %></td></tr></table></td>
	</tr>
<%} %>
</table>
</div>
</div>
<div class="col-lg-12 text-center">
<a href="<%= askExpertReplyForm %>" class="btn btn-primary btn-sm" >Give Your Opinion</a> 
<a href="<%= backURL %>" class="btn btn-primary btn-sm">Back</a>
		</div>
		</div>
<script>
function popup(cpfNo){
	 var url="<%=getEmployeeData%>&<portlet:namespace />cpfNo="+cpfNo;
	Liferay.Util.openWindow({
	    dialog: {
	        centered: true,
	        height: 600,
	        modal: true,
	        width: 800,
	        style:"background-color: #8c000d;color: #fff !important;",
	        cssClass: "ui-model",
	        destroyOnHide: true,
           resizable: false,
	    },
	    id: "<portlet:namespace />popUpId",
	    title: "Employee Details",
	    uri: url
	}); 
	  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
        Liferay.Util.Window.getById(popUpId).destroy();
        location.reload();
    },
    ['liferay-util-window']
    ); 
}
</script>