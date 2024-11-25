<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<portlet:renderURL var="openSponsorshipForm">
	<portlet:param name="mvcPath" value="/sponsorship/editSponsorship.jsp" />
</portlet:renderURL>
<portlet:renderURL var="openChagePasswordForm">
	<portlet:param name="mvcRenderCommandName"
		value="openChagePasswordForm" />
</portlet:renderURL>
<portlet:renderURL var="diplayAllUserList">
	<portlet:param name="mvcRenderCommandName" value="display_User" />
</portlet:renderURL>
<portlet:renderURL var="userLogout">
	<portlet:param name="mvcRenderCommandName" value="userLogout" />
</portlet:renderURL>
<portlet:renderURL var="searchSponsorship">
	<portlet:param name="mvcRenderCommandName" value="search_sponsorship" />
</portlet:renderURL>
<%
	String loginId = (String) request.getAttribute("loginId");
	String loginId1 = ParamUtil.getString(request, "loginId");
%>
<header class="header">
	<div class="row ongc-logo">

		<div class="col-md-6 logo">
			<div class="logo-tbl">
				<a href="<%=searchSponsorship%>" title="Home"><img
					class="img-logo"
					src="<%=request.getContextPath()%>/images/ongc-logo.png"
					alt="Logo"> <span class="ongc-r">Oil and Natural Gas
						Corporation Limited</span> </a>
			</div>
		</div>
		<div class="col-md-6">
			<div class="text-right loginUserName">
				<span class="fa fa-user"></span>,
				<%
					if (loginId != null) {
				%>
				<%=loginId%>
				<%
					} else {
				%>
				<%=loginId1%>
				<%
					}
				%>
			</div>
		</div>



	</div>

	<!-- Menu Section -->
	<div class="row ongc-menu mn mb5 ">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="<%=openSponsorshipForm%>"
						class="sponsorship-button-color"> Add <span
							class="glyphicon glyphicon-plus-sign"></span>
					</a></li>

					<li><a href="${searchSponsorship}">Edit</a>
					<li><a href="https://webice.ongc.co.in/ims#" target="_blank">Invoice
							tracking</a></li>
					<li><a href="${openChagePasswordForm}">Change Password</a></li>
					<li><a href="${diplayAllUserList}">Manage Users</a></li>
					<li><a href="${userLogout}">Logout</a></li>

					<!-- Navigation End -->
				</ul>
			</div>





		</nav>
	</div>

</header>