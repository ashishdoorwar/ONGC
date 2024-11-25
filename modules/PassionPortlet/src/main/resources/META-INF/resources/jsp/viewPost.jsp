<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.passion.model.DiscussionBoard"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<script src="<%=request.getContextPath()%>/js/discussionBoard.js"></script>
<style>
.post_reply {
	display: none
}
</style>
<%
	UserService userService = new UserServiceImpl();
	User userData = userService.getUser();
	String tid = "";
%>
<div class="contentarea">
	<div class="left">

		<div class="row">
			<div class="col-md-12">
				<h2>Discussion Board</h2>
			</div>
		</div>
		<div class="backNews">
			<%-- <s:a action="showDiscussion">
				<img src="<%=request.getContextPath()%>/images/back.jpg" alt=""
					title="Back" />
			</s:a> --%>
		</div>
		<div style="clear: both;"></div>

		<div class="discusstionLeft">
			<div class="cummentBox">
				<%
					List<DiscussionBoard> tDetail = (List<DiscussionBoard>) request.getAttribute("tDetail");

					Iterator it = tDetail.iterator();
					while (it.hasNext()) {
						DiscussionBoard dBoard = (DiscussionBoard) it.next();
						tid = dBoard.getTopicId();
				%>
				<div class="d-flex">
				<div class="cumUserIcon">
					<s:a action="viewProfile">
						<img height="80px" width="83px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=dBoard.getCreatedByCpf()%>" alt="" class="spoimg" />
					</s:a>
				</div>
				<div class="cumUserTopic">
					<div class="row">
						<div class="col-md-12">
							<div class="d-flex justify-content-between">
							<div class="f-left">
								<h3>

									<portlet:renderURL var="viewProfile">
										<portlet:param name="mvcRenderCommandName"
											value="view_profile" />
										<portlet:param name="cpfno"
											value="<%=dBoard.getCreatedByCpf()%>" />
									</portlet:renderURL>

									<a href="<%=viewProfile%>"><%=dBoard.getCreatedByName()%></a>
								</h3>
							</div>
							<div class="f-right">
								<ul>
									<li>Published on:</li>
									<li><%=dBoard.getPublishedOn()%></li>
									<li style="width: 11px;">&nbsp;&nbsp;</li>
								</ul>
							</div>
						</div>
						<div class="col-md-12">
							<p>
								<b><%=dBoard.getTopicName()%></b><br /><%=dBoard.getTopicDesc().replaceAll("\n", "<br />")%></p>
						</div>
						</div>
					</div>
				</div>
			</div>
					
					

			
		
				<%
					}
				%>
			</div>


			<div class="row">
				<div class="col-md-12">
					<h5 class="headingBg">Post Your Comment</h5>
				</div>
			</div>
			<s:form action="deleteComment" theme="simple" method="post"
				name="cform" id="cform">
				<s:hidden name="tid" value="%{tid}"></s:hidden>
				<input type="hidden" name="cid" value="" id="comid" />
			</s:form>
			<s:form action="deleteReply" theme="simple" method="post"
				name="rform" id="rform">
				<s:hidden name="tid" value="%{tid}"></s:hidden>
				<input type="hidden" name="rid" value="" id="repid" />
			</s:form>
			<portlet:actionURL var="submitComment" name="submit_Comment">
			</portlet:actionURL>
			<div class="cummentBox">
				<aui:form action="${submitComment}" method="post" name="mform">
					<aui:input type="hidden" name="tid" value="<%=tid%>"></aui:input>
					<aui:input type="hidden" name="cid" value="" id="cid" />
					<aui:input type="hidden" name="rid" value="" id="rid" />
					<div class="d-flex">
					<div class="cumUserIcon">
						<img height="83px" width="80px" src="" alt="" class="spoimg" />
					</div>
					<div class="cumUserTopic">
						<div>
							<aui:input type="textarea" id="comment" name="comment" rows=""
								cols="" placeholder="Post your comment" maxlength="100" />
						</div>
						<div>
							<aui:button type="submit" value="Post" cssClass="btn btn-primary btn-sm" />
						</div>
					</div>
				</div>
				</aui:form>

			</div>

			<%
				List<DiscussionBoard> tl = (List<DiscussionBoard>) request.getAttribute("cList");
				int flagCnt = 0;
				Iterator it2 = tl.iterator();
				while (it2.hasNext()) {
					DiscussionBoard disboard = (DiscussionBoard) it2.next();
					//cid=disboard.getCommentId();
			%>
			<div class="cummentBox">
				<div class="d-flex ">
				<div class="cumUserIcon">
					<s:a action="viewProfile">
						<s:param name="empCpf"><%=disboard.getCommentBy()%></s:param>
						<img height="80px" width="83px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=disboard.getCommentBy()%>" alt="" class="spoimg" />
					</s:a>
				</div>

				<div class="cumUserTopic">
					<div class="d-flex justify-content-between">
						<div class="f-left">
							<h3>

								<portlet:renderURL var="viewProfileComment">
									<portlet:param name="mvcRenderCommandName" value="view_profile" />
									<portlet:param name="cpfno"
										value="<%=disboard.getCommentBy()%>" />
								</portlet:renderURL>

								<a href="<%=viewProfileComment%>"><%=disboard.getCommentByName()%></a>
							</h3>
						</div>
						<div class="f-right">
							<ul>
								<li>Published on:</li>
								<li><%=disboard.getPublishedOn()%></li>
								<%
									if (userData.getCpfNo().equalsIgnoreCase(disboard.getCommentBy())) {
								%>
								<li><a href="#"
									onclick="deleteComment(document.cform,'<%=disboard.getCommentId()%>')"><img
										src="<%=request.getContextPath()%>/images/delete.jpg" alt="" /></a></li>
								<%
									} else {
								%>
								<li style="width: 16px;">&nbsp;&nbsp;</li>
								<%
									}
								%>
							</ul>
						</div>
					
					</div>
					<p><%=disboard.getComment().replaceAll("\n", "<br />")%><br />
						<a class="reply btn btn-primary btn-sm" id="reply<%=flagCnt%>"
							href="#post_reply<%=flagCnt%>">Reply</a>
					</p>
				
				</div>
			</div>


				<div class="mLeft100">

					<portlet:actionURL var="submitReply" name="submit_Reply">
					</portlet:actionURL>
					<div class="cummentBox post_reply" id="post_reply<%=flagCnt%>">
						<aui:form action="${submitReply}" method="post">
							<aui:input name="tid" type="hidden"
								value="<%=disboard.getTopicId()%>" />
							<aui:input name="cid" type="hidden"
								value="<%=disboard.getCommentId()%>" />
							<div class="cumUserTopic">
								<div>
									<aui:input type="textarea" name="reply"
										placeholder="Post your reply" maxlength="100" />
								</div>
								<div>
									<aui:button type="submit" value="Post"
										cssClass="btn btn-primary btn-sm" />
								</div>
							</div>
						</aui:form>
					</div>
					<%
						flagCnt++;
							if (!disboard.getReplyList().isEmpty()) {
								List rList = disboard.getReplyList();

								Iterator it3 = rList.iterator();
								while (it3.hasNext()) {
									DiscussionBoard discussionboard = (DiscussionBoard) it3.next();
					%>
					<div class="dis-thread">
						<div class="d-flex">
						<div class="cumUserIcon">
							<s:a action="viewProfile">
								<s:param name="empCpf"><%=disboard.getCommentBy()%></s:param>
								<img height="80px" width="83px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=disboard.getCommentBy()%>" alt="" class="spoimg" />
							</s:a>
						</div>
						<div class="cumUserTopicReply">
							<div class="d-flex justify-content-between">
								<div class="f-left">
									<h3>
										<portlet:renderURL var="viewProfileComment1">
											<portlet:param name="mvcRenderCommandName"
												value="view_profile" />
											<portlet:param name="cpfno"
												value="<%=disboard.getCommentBy()%>" />
										</portlet:renderURL>

										<a href="<%=viewProfileComment1%>"><%=disboard.getCommentByName()%></a>
									</h3>
								</div>
								<div class="f-right">
									<ul>
										<li>Published on:</li>
										<li><%=discussionboard.getPublishedOn()%></li>
										<%
											if (userData.getCpfNo().equalsIgnoreCase(discussionboard.getReplyBy())) {
										%>
										<li><a href="#"
											onclick="deleteReply(document.rform,'<%=discussionboard.getReplyId()%>')"><img
												src="<%=request.getContextPath()%>/images/delete.jpg" alt="" /></a></li>
										<%
											} else {
										%>
										<li style="width: 14px;">&nbsp;&nbsp;</li>
										<%
											}
										%>
									</ul>
								</div>
							</div>
							<p><%=discussionboard.getReply().replaceAll("\n", "<br />")%></p>
							</div>
						</div>
							
							
						
					</div>
					</div>
				</div>

				<%
					}
						}
				%>
			</div>
			<%
				}
			%>
		</div>

	</div>



<script>
	function showDiscussion(tagform, id) {
		document.getElementById("tagKey").value = id;

		document.tagform.action = "showDiscussion";
		tagform.submit();
	}

	$(window).bind("pageshow", function() {
		$("#comment").val('');
		$('.cummentBox').find('input:text').val('');

	});
</script>