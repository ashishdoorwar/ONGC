<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%

String assetEntryURL = (String) request.getAttribute("assetEntryURL");
String portalUrl = themeDisplay.getURLPortal();
String currentUrl = themeDisplay.getURLCurrent();
String currentPage;
if(assetEntryURL!=null){
currentPage = currentUrl.substring(0, currentUrl.indexOf("?"));}
else {
	currentPage = currentUrl.substring(0, currentUrl.indexOf("/"));
}
%>