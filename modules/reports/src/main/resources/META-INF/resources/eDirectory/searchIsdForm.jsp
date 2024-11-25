<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<portlet:actionURL var="searchIsdResult" name="search_isd">
</portlet:actionURL>

<div class="row">
	<div class="col-md-8 ">
	<aui:form action="${searchIsdResult}" name="fm"  method="post" >
	<aui:fieldset-group markupView="lexicon">
		<aui:input label="Country :" name="country" type="text" />
		<aui:input label="ISD Code :" name="isdCode" type="text" />
		<aui:input label="IDD Code :" name="iddCode" type="text" />
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center ">
		<aui:button cssClass="btn eDirectorybackground-color" type="submit" />
	</aui:button-row>
</aui:form>
	</div>
	<div class="col-md-4 ">
	<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>

