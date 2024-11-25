<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ page import="java.net.URLEncoder"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	
	
	String portalUrl = themeDisplay.getURLPortal();
	String encodedPortalUrl = URLEncoder.encode(portalUrl);
	String currentUrl = themeDisplay.getURLCurrent(); 
	String encodedCurrentUrl = URLEncoder.encode(currentUrl);
	String siteName = layout.getGroup().getFriendlyURL(); 
	String encodedSiteName = URLEncoder.encode(siteName);
	long groupId = layout.getGroup().getGroupId();
	String quesryStringSign = "?";
	if(currentUrl.contains("?")){
		quesryStringSign = "&";
	}
	
%>