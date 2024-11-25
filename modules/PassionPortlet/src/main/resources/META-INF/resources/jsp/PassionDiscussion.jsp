<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.DiscussionDao"%>
<%@page import="com.ongc.liferay.passion.util.Paginator"%>
<%@page import="com.ongc.liferay.passion.util.TagCloud"%>
<%@page import="com.ongc.liferay.passion.model.DiscussionBoard"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="java.util.List"%>
<%@page import="org.mcavallo.opencloud.Cloud"%>
<%@page import="org.mcavallo.opencloud.Tag"%>
<%@ include file="/init.jsp"%>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/discussionpagging.js"></script>
<script>
	function viewPost(id) {
		document.getElementById("myValue").value = id;
		document.mform.submit();
	}
	function showDiscussion(tagform, id) {
		document.getElementById("tagKey").value = id;

		document.tagform.action = "showDiscussion";
		tagform.submit();
	}
</script>
<portlet:renderURL var="addNewGroup">
	<portlet:param name="mvcPath" value="/jsp/addNewGroup.jsp" />
</portlet:renderURL>
 <portlet:renderURL var="addNewTopic">
					<portlet:param name="mvcPath" value="/jsp/PostNewTopic.jsp" />
				</portlet:renderURL> 
<div class="contentarea">

	<div>
		<h2>Discussion Board</h2>
	</div>
	<div class="row">
		<div class="col-md-9">
			<s:form method="GET" action="viewPost" name="mform" id="mform">
				<input type="hidden" name="tid" value="" id="myValue" />
			</s:form>
			<div class="discusstionLeft" id="viewPostList">
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Topic Name</th>
								<th>Published By</th>
								<th>Posts</th>
								<th>Visibility</th>
							</tr>
						</thead>
						<tbody>
							<%
								UserService userService1 = new UserServiceImpl();
								User userData1 = userService1.getUser();
								DiscussionDao discussionDao = new DiscussionDaoImpl();
								List tl = discussionDao.fetchTrendingTopics(userData1.getCpfNo());
								Paginator pag = new Paginator(tl, 10, "0");
								while (pag.isNext()) {
									DiscussionBoard dBoard = (DiscussionBoard) pag.next();
							%>
							<tr>
								<td><div class="">
										<portlet:renderURL var="viewPost1">
											<portlet:param name="mvcRenderCommandName" value="view_post" />
											<portlet:param name="tid"
												value="<%=dBoard.getTopicId()%>" />
										</portlet:renderURL>
										<a href="<%=viewPost1%>"><%=dBoard.getTopicName()%></a>
									</div></td>
								<td><div class=""><%=dBoard.getCreatedByName()%></div></td>
								<td><div class=""><%=dBoard.getRepliesCount()%></div></td>
								<td>
									<%
										if (dBoard.getVisibleTo().equalsIgnoreCase("P")) {
									%>
									<div class="">
										<span><img
											src="<%=request.getContextPath()%>/images/icon2.png" alt="" />
										</span>Public
									</div> <%
 	} else {
 %>
									<div class="">
										<span><img
											src="<%=request.getContextPath()%>/images/icon3.png" alt="" /></span>
										Group
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

				<ul class="pagination">
					<%=pag.createIndex()%>
				</ul>
				<div class="clearboth"></div>

			</div>

		</div>
		<div class="col-md-3">
			<div>

				<div class="discusstionLeft">
					<div class="">
						<h5>
							<a href="<%=addNewTopic %>">Create New Topic</a>
						</h5>
					</div>
					<div class="" style="margin-top: 5px;">
						<h5>
							<a href="<%=addNewGroup%>" class="headingBg">Create New Group</a>
					</div>
				</div>
				<div class="discusstionLeft" style="margin-top: 10px;">
					<ul class="cloud">
						<%
							TagCloud tc = new TagCloud();
							Cloud cloud = tc.discussionTag(userData1.getCpfNo());
							cloud.setMaxTagsToDisplay(10);
							cloud.setMaxWeight(20.0);
							cloud.setMinWeight(10.0);
							// cycle through output tags
							for (Tag tag : cloud.tags()) {
								// add an HTML link for each tag
						%>
						<portlet:renderURL var="viewDissCloud">
							<portlet:param name="mvcRenderCommandName"
								value="view_Discussion_Cloud" />
							<portlet:param name="q" value="<%=tag.getName()%>" />
						</portlet:renderURL>
						<li><a href="<%=viewDissCloud%>"><%=tag.getName()%></a></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>


	</div>


</div>
