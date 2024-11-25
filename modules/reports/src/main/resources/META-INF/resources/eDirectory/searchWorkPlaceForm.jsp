<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<portlet:actionURL var="searchWorkplaceResult" name="search_workplace">
</portlet:actionURL>
<%
	String workplaceName = ParamUtil.getString(request, "workplaceName");
	String subCategory = ParamUtil.getString(request, "subCategory");
%>

<h1>ONGC WorkPlace Search</h1>
<div class="row">
	<div class="col-md-8">
		<aui:form action="${searchWorkplaceResult}" name="fm">

			<aui:input label="Department :" name="department" type="text" />
			<aui:input label="Location :" name="location" type="text" />
			<aui:input label="Workplace :" name="workplace"
				value="<%=workplaceName%>" type="text" />
			<%
				if (subCategory != null && subCategory != "") {
			%>
			<aui:input label="Sub Category :" name="subCategory"
				value="<%=subCategory%>" type="text" />
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
