<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<portlet:actionURL var="searchLocationResult" name="search_location">

</portlet:actionURL>
<%
String location=ParamUtil.getString(request, "locationName");

%>
<h1>ONGC Search</h1>
<div class="row">
<div class="col-md-8">
<aui:form action="${searchLocationResult}" name="fm"  method="post" >
	<aui:fieldset-group markupView="lexicon">
		<aui:input label="CPF Number" name="cPFNumber" type="text" />
		<aui:input label="Name of Employee" name="empName" type="text" />
		<aui:input label="Department :" name="department" type="text" />
		<aui:input label="Location :" name="location" value="<%=location %>"  type="text" />
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center ">
		<aui:button cssClass="btn eDirectorybackground-color btn-primary" type="submit" />
	</aui:button-row>
</aui:form>
</div>
<div class="col-md-4 ">
	<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>