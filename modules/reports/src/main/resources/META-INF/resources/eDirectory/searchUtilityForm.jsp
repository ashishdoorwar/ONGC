<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<portlet:actionURL var="searchUtilityResult" name="search_utility">
</portlet:actionURL>

<%
	String utilityName = ParamUtil.getString(request, "utilityName");
	String subUtility = ParamUtil.getString(request, "subUtility");
%>
<h1>ONGC Utility Search</h1>
<div class="row">
	<div class="col-md-8">
		<aui:form action="${searchUtilityResult}" name="fm">

			<aui:input label="Department :" name="department" type="text" />
			<aui:input label="Location :" name="location" type="text" />
			<aui:input label="Utility :" name="utility" value="<%=utilityName%>"
				type="text" />
			<%
				if (subUtility != null && subUtility != "") {
			%>
			<aui:input label="SubUtility :" name="SubUtility"
				value="<%=subUtility%>" type="text" />
			<%
				}
			%>
			<aui:button-row cssClass="text-center ">
				<aui:button cssClass="btn eDirectorybackground-color" type="submit" />
			</aui:button-row>
		</aui:form>
	</div>
	<div class="col-md-4 ">
		<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>