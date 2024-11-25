<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.GroupDaoImpl"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.model.Group"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<script src="<%=request.getContextPath()%>/js/scroller.js"></script>
<script src="<%=request.getContextPath()%>/js/group.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/scroller.css" />
<style>
.request {
	font-style: italic;
}
</style>
<portlet:renderURL var="addNewGroup1">
	<portlet:param name="mvcPath" value="/jsp/addNewGroup.jsp" />
</portlet:renderURL>
<script>
	(function($) {
		$(window).load(function() {

			$(".hi").mCustomScrollbar({
				theme : "minimal"
			});

		});
	})(jQuery);

	$(document).ready(function() {
		$(".flip").click(function() {
			$(".panel").toggle();
		});
	});
</script>
<%
	UserService userService1 = new UserServiceImpl();
	User userData1 = userService1.getUser();
%>
<portlet:renderURL var="addNewTopic1">
	<portlet:param name="mvcPath" value="/jsp/PostNewTopic.jsp" />
</portlet:renderURL>
<div class="contentarea">

	<div>
		<div class="f-left">
			<h2>Group/Categories</h2>
		</div>

		<div class="grp_topic">
			<a href="<%=addNewGroup1%>"><table>
					<tr>
						<td>&#160;Create New Group</td>
					</tr>
				</table></a>
		</div>
		<div class="grp_topic" style="margin-right: 5px;">
			<a href="<%=addNewTopic1%>"><table>
					<tr>
						<td>&#160;Create New Topic</td>
					</tr>
				</table></a>
		</div>
		<div class="grp_notif">
			<div id="notification" style="margin-top: 0 !important;">
				<%
					GroupDaoImpl gDao = new GroupDaoImpl();
					List inviteList = gDao.fetchInvitingGroups(userData1.getCpfNo());
				%>
				<div class="flip">
					<img height="17" width="17"
						src="<%=request.getContextPath()%>/images/imgres.png" alt="" />
					Notifications-<%=inviteList.size()%>
				</div>
				<div class="panel">
					<div
						style="width: 20px; height: 10px; margin-right: 25px; float: right; position: relative;">
						<%
							if (inviteList.size() != 0) {
						%>
						<div class="arrow-up"></div>
						<%
							}
						%>
						<s:form name="vform" id="vform" method="get" action="viewGroup">
							<s:hidden name="gid" id="gIdv"></s:hidden>
							<s:hidden name="gadmin" id="gAdminv"></s:hidden>
							<s:hidden name="topiccount" id="topicCountv"></s:hidden>
						</s:form>
						<s:form name="dform" id="dform" method="post"
							action="declineGroup">
							<s:hidden name="gid" id="gIdd"></s:hidden>
						</s:form>
						<s:form name="jform" id="jform" method="post" action="joinGroup">
							<s:hidden name="gid" id="gIdj"></s:hidden>
						</s:form>
						<s:form name="eform" id="eform" method="get" action="editGroup">
							<s:hidden name="gid" id="gIde"></s:hidden>
						</s:form>
						<s:form name="delform" id="delform" method="post"
							action="deleteGroup">
							<s:hidden name="gid" id="gIdDel"></s:hidden>
						</s:form>
					</div>
					<div class="hi">

						<ul>
							<%
								Iterator it1 = inviteList.iterator();
								while (it1.hasNext()) {
									Group group1 = (Group) it1.next();
							%>


							<li><div class="thumb">
									<s:a action="viewProfile">
										<s:param name="empCpf"><%=group1.getCreatedByCpf()%></s:param>
										<img height="45px" width="45px"
											src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=group1.getCreatedByCpf()%>"
											alt=" ">
									</s:a>
								</div>
								<p>
									<s:a action="viewProfile">
										<s:param name="empCpf"><%=group1.getCreatedByCpf()%></s:param>
										<%=group1.getCreatedByName()%></s:a>
									invited you to join Group "<%=group1.getGroupName()%>" on
									<%=group1.getCreatedOn()%>. <span class="request">&nbsp;&nbsp;<a
										onclick="joinGroup(document.jform,'<%=group1.getGroupId()%>');"
										href="#">ACCEPT</a> &nbsp;&nbsp;<a
										onclick="declineGroup(document.dform,'<%=group1.getGroupId()%>');"
										href="#">DECLINE</a></span>
								</p></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
			</div>

		</div>
	</div>

	<br clear="all" />
	<div class="discusstionLeft" style="margin-top: 10px;">
		<!-- <div class="row"><h5> Creat New Group</h5></div>-->

		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Group Name</th>
						<th>Group Admin</th>
						<th>Number of Members</th>
						<th>Creation Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						List glist = gDao.fetchGroups(userData1.getCpfNo());

						Iterator it = glist.iterator();
						while (it.hasNext()) {
							Group group = (Group) it.next();
							String groupName = group.getGroupName();
							if (groupName.contains("'")) {
								groupName = groupName.replaceAll("'", "_@S@_");
							}
							if (groupName.contains("\"")) {
								groupName = groupName.replaceAll("\"", "_@D@_");
							}
					%>
					<portlet:actionURL var="deleteGroup" name="deleteGroup">
						<portlet:param name="gid" value="<%=group.getGroupId()%>" />
					</portlet:actionURL>
					<portlet:renderURL var="editGroup1">
						<portlet:param name="mvcPath" value="/jsp/EditGroup1.jsp" />
						<portlet:param name="gId" value="<%=group.getGroupId()%>" />
					</portlet:renderURL>
					<portlet:renderURL var="viewGroup">
						<portlet:param name="mvcPath" value="/jsp/ViewGroup.jsp" />
						<portlet:param name="gid" value="<%=group.getGroupId()%>" />
						<portlet:param name="topiccount"
							value="<%=String.valueOf(group.getTopicCount())%>" />
						<portlet:param name="gadmin"
							value="<%=group.getCreatedByCpf()%>" />
					</portlet:renderURL>

					<tr>
						<td><div>
								<a href="<%=viewGroup%>"><%=group.getGroupName()%></a>
							</div></td>
						<td>
							<div>
								<s:a action="viewProfile">
									<s:param name="empCpf"><%=group.getCreatedByCpf()%></s:param>
									<%=group.getCreatedByName()%></s:a>
							</div>
						</td>
						<td><div><%=group.getNoOfMembers() + 1%></div></td>
						<td><div><%=group.getCreatedOn()%></div></td>
						<td>
							<%
								if (userData1.getCpfNo().equals(group.getCreatedByCpf())) {
							%>
							<div>
								<span><a href="<%=editGroup1%>"><img
										src="<%=request.getContextPath()%>/images/tab_symbol02.png"
										alt="" title="Edit" /></a> <a href="<%=deleteGroup%>"><img
										src="<%=request.getContextPath()%>/images/close.png"
										title="Delete" alt="" /></a></span>
							</div> <%
						}
						%>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

	</div>
</div>
