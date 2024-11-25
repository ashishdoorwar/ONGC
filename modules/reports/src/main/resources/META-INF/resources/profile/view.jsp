<%@ include file="/init.jsp" %>

<portlet:renderURL var="getUserProfileInfo">
				<portlet:param name="mvcRenderCommandName"
				value="getUserProfileInfo" />
     			</portlet:renderURL>
				<a href="<%=getUserProfileInfo%>"><b>Click
							here</b> </a>to update your contact details