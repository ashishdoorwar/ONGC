<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<portlet:actionURL var="searchMscResult" name="search_msc">

</portlet:actionURL>

<div class="row">
	<div class="col-md-8 ">
	<aui:form action="${searchMscResult}" name="fm"  method="post" >
	<aui:fieldset-group markupView="lexicon">
		<aui:input label="Network" name="network" type="text" />
		<aui:input label="Circle" name="circle" type="text" />
		<aui:input label="Mobile Operator:" name="operator" type="text" />
		<aui:input label="MNC Code:" name="mncCode"   type="text" />
		<aui:input label="MNC Code: MSC Code: (Enter first 5 digit excluding starting zero)" name="codeDetails"   type="text" />
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center ">
		<aui:button cssClass="btn eDirectorybackground-color " type="submit" />
	</aui:button-row>
</aui:form>
	</div>
	<div class="col-md-3 ">
	<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>

