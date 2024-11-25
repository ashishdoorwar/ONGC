<%@ include file="/init.jsp"%>
<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>

<portlet:actionURL var="searchStdResult" name="search_std">
</portlet:actionURL>

<div class="row">
	<div class="col-md-8 ">
	<aui:form action="${searchStdResult}" name="fm"  method="post" >
	<aui:fieldset-group markupView="lexicon">
		<aui:input label="Telecom Circle" name="stateName" type="text" />
		<aui:input label="Long Distance charging area:" name="lcda" type="text" />
		<aui:input label="Enter Short Distance charging area:" name="sdcaname" type="text" />
		<aui:input label="SDCA Code (STD): (Enter code excluding starting zero)" name="sdca" type="text" />
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center ">
		<aui:button cssClass="btn eDirectorybackground-color" type="submit" />
	</aui:button-row>
</aui:form>
	</div>
	<div class="col-md-3 ">
	<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>



