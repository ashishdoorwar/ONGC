<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="org.mcavallo.opencloud.Tag"%>
<%@page import="org.mcavallo.opencloud.Cloud"%>
<%@page import="com.ongc.liferay.passion.util.TagCloud"%>
<%@page import="com.ongc.liferay.passion.model.DiscussionBoard"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<%
	List<DiscussionBoard> discussionBoards = (List<DiscussionBoard>) request.getAttribute("discussionBoards");
	UserService userService=new UserServiceImpl();
	User userData=userService.getUser();
%>
<portlet:renderURL var="addNewGroup">
	<portlet:param name="mvcPath" value="/jsp/addNewGroup.jsp" />
</portlet:renderURL>
<div class="row">
	<div class="col-md-9">
<div class="table-responsive">
		<table class="table table-striped stripe row-border order-column  table-bordered"
			id="discussionBoard" width="100%">
			<thead>
				<tr>
					<th>Topic Name</th>
					<th>Published By</th>
					<th>Posts</th>
					<th>Visibility</th>
				</tr>
			</thead>
			<%
				for (DiscussionBoard discussionBoard : discussionBoards) {
			%>
			<portlet:renderURL var="viewPost">
				<portlet:param name="mvcRenderCommandName" value="view_post" />
				<portlet:param name="tid" value="<%=discussionBoard.getTopicId()%>" />
			</portlet:renderURL>

			<tr>
				<td><a href="<%=viewPost%>"><%=discussionBoard.getTopicName()%></a></td>
				<td><%=discussionBoard.getCreatedByName()%></td>
				<td><%=discussionBoard.getRepliesCount()%></td>
				<td><%=discussionBoard.getVisibleTo()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>
	<div class="col-md-3">
		
			<h5>
				<a href="#" class="headingBg">Create
					New Topic</a>
			</h5>

		
		
			<h5 class="mt-2">
				<a href="<%=addNewGroup%>" class="headingBg">Create New Group</a>
			</h5>
		
		 <div class="discusstionLeft mt-2" >
        	<ul class="cloud">
        	<%
				TagCloud tc=new TagCloud();
				Cloud cloud=tc.discussionTag(userData.getCpfNo());
				cloud.setMaxTagsToDisplay(10);
				cloud.setMaxWeight(20.0);
				cloud.setMinWeight(10.0);
				// cycle through output tags
				for (Tag tag : cloud.tags()) {
				    // add an HTML link for each tag
			%>
			<portlet:renderURL var="viewDiscussionCloud">
								<portlet:param name="mvcRenderCommandName"
									value="view_Discussion_Cloud" />
								<portlet:param name="q" value="<%=tag.getName() %>" />
							</portlet:renderURL>
				<li><a href="<%=viewDiscussionCloud%>"><%=tag.getName()%></a></li>
			<%
				} 
			%>
            </ul>
    </div>
	</div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var table = $('#discussionBoard').DataTable({
				lengthChange: false,bFilter: false, bInfo: false
			});
		});
	</script>