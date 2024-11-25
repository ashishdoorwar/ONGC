<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<portlet:renderURL var="openChagePasswordForm">
<portlet:param name="mvcRenderCommandName" value="openChagePasswordForm" />
</portlet:renderURL>
<portlet:renderURL var="searchCSR">
	<portlet:param name="mvcRenderCommandName" value="search_csr" />
</portlet:renderURL>
<portlet:renderURL var="diplayAllUserList">
	<portlet:param name="mvcRenderCommandName" value="display_User" />
</portlet:renderURL>
<portlet:renderURL var="userLogout">
	<portlet:param name="mvcRenderCommandName" value="userLogout"  />
</portlet:renderURL>
<%
	String loginId=(String)request.getAttribute("loginId");
	String loginId1=ParamUtil.getString(request, "loginId");

%>
<header class="header">
	<div class="row ongc-logo">

		<div class="col-md-6 logo">
			<div class="logo-tbl">
				<a href="<%=searchCSR %>" title="Home"><img class="img-logo"
					src="<%=request.getContextPath()%>/images/ongc-logo.png" alt="Logo">
					<span class="ongc-r">Oil and Natural Gas Corporation Limited</span>
				</a>
			</div>
		</div>
<div class="col-md-6">
	<div class="text-right loginUserName">
		<span class="fa fa-user"></span>,
		<%if(loginId!=null){ %>
		<%=loginId %>
		<%}else{ %>
		<%=loginId1 %>
		<%} %>
	</div>
</div>


	</div>

	<!-- Menu Section -->
	<div class="row ongc-menu mn mb5 ">
		<div class="col-md-12">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar">
				<span class="navbar-toggler-icon"></span>
			  </button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="navbar-nav">
					<li><a href="${searchCSR}" title="Home">
							<span class="fa fa-home"></span>
						</a></li>
					<li><a href="${openChagePasswordForm}">Change Password</a></li>
					<s:if test="#session.user.cpfNo == 'Admin1'">
						<li><a href="${diplayAllUserList}">User List</a></li>
					</s:if>
			
					<li><a href="<%= userLogout%>" title="Logout">
							<span class="fa fa-power-off"></span>
						</a></li>

					<!-- Navigation End -->
				</ul>
			</div>





		</nav>
	</div>
</div>
</header>