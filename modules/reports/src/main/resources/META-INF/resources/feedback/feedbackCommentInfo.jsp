<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page
	import="com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.FeedbackPostService"%>
<%@page import="com.ongc.liferay.reports.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.UserService"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackReason"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackHrCategory"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackHrEnablers"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackComment"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackPost"%>
<%@ include file="/init.jsp"%>
<%
	FeedbackPost post = (FeedbackPost) request.getAttribute("post");
	List<FeedbackHrEnablers> enablersList = (List<FeedbackHrEnablers>) request.getAttribute("HREnablersList");
	//System.out.println("enablersList=========>"+enablersList.size());
	User users = (User) request.getAttribute("user");
	//out.println(post);
	List<FeedbackComment> feedbackComment = (List<FeedbackComment>) request.getAttribute("commentList");
	//System.out.println(feedbackComment.size());
	List<FeedbackReason> reasonList = (List<FeedbackReason>) request.getAttribute("reasonList");
	//System.out.println("reasonList=========>"+reasonList.size());
	String backURL = (String) request.getAttribute("backURL");
	int srn = 1;
	FeedbackPostService feedbackPostService = new FeedbackPostServiceImpl();
	List<FeedbackHrCategory> hrCategoryList = feedbackPostService.getHRCategoryList();
	//System.out.println("hrCategoryList=========>"+enablersList.size());
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM dd, yyyy hh:mm");
	String context = themeDisplay.getPortalURL();
	Integer likecount = (int) request.getAttribute("likecount");
	Integer dislikecount = (int) request.getAttribute("dislikecount");
	Integer feedhitcount = (int) request.getAttribute("feedhitcount");
	String flag =(String) request.getAttribute("flag");
%>
<portlet:renderURL var="getEmployeeData"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo" />
</portlet:renderURL>

<portlet:renderURL var="getCommentByLocal"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="commentByLocal" />
</portlet:renderURL>
<%--  <portlet:renderURL var="getForwardToCorporate" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="forwardToCorporate"/>
</portlet:renderURL> --%>
<portlet:renderURL var="feedbackCommentForm">
	<portlet:param name="mvcPath" value="/feedback/feedbackCommentForm.jsp" />
	<portlet:param name="postId"
		value="<%=Integer.toString(post.getPostId())%>" />
</portlet:renderURL>
<portlet:resourceURL id="likeFeedback" var="likeFeedbackUrl"></portlet:resourceURL>
<portlet:resourceURL id="hitsFeedback" var="hitsFeedbackUrl"></portlet:resourceURL>

<portlet:resourceURL id="archive" var="archiveURL"></portlet:resourceURL>
<div class="row">
		<div class="col-md-12">
			<ul id="userbox" class="list-inline ul-r-bdr">
				<li><span id="feedHits"><img src="/documents/59362/0/hitIcon.png/f224eb6f-6b00-2efc-7d77-953bb5dd348f?t=1648546337512" />Hits </span> <span id="feedHitCount"><%= feedhitcount %></span>
				
				&nbsp;<a id="pageLikeLink" title="Like"
					href="javascript:feedLike('<%=post.getPostId()%>','<%=users.getCpfNo()%>','L')"><img src="/documents/59362/0/like.png/e86c3c13-c3f9-3f2b-8ee2-2149ce56f300?t=1648546368340" />Like
				</a> <span><i title="Like" class="fa fa-thumbs-up"></i>
				</span> <span id="pageLikeCount"><%= likecount %></span>
				
				&nbsp;<a id="pageDisLikeLink" title="Dislike"
					href="javascript:feedLike('<%=post.getPostId()%>','<%=users.getCpfNo()%>','D')">Dislike
				</a> <span><i title="Dislike" class="fa fa-thumbs-down"
						aria-hidden="true"></i> </span> <span id="dislikeCount"><%= dislikecount %></span></li>

			</ul>

		</div>
	</div>
<div class="row">
	<div class="col-md-12">
		<table class="table table-bordered table-striped">
			<tr>
				<td><a title="Click to view full profile"
					onclick="popup(<%=post.getUser().getCpfNo()%>)"> <%=post.getUser().getEmpName()%></a>,
					<%=post.getUser().getDesignation()%>, <%=post.getUser().getLocalAddress()%>
					<%
						/* Iterator it1 = enablersList.iterator();
						while (it1.hasNext()) {
							FeedbackHrEnablers enablers = (FeedbackHrEnablers) it1.next(); */
							for(FeedbackHrEnablers enablers:enablersList){
							if ((enablers.getCpfNo().equalsIgnoreCase(users.getCpfNo()))) {
								if (("L1".equalsIgnoreCase(enablers.getRole().trim()))
										|| users.getCpfNo().equalsIgnoreCase("78619")) {
					%> <%
 	if (post.getOpStatus().equals("OPEN")) {
 %> <input type="submit" value="Forward to Corporate" id="button"
					name="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#myModal" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:commentDetails(<%=post.getPostId()%>)"
					id="button" class="btn mr5 btn-primary pull-right">Comment by
						Local Enabler</a>&nbsp; <%
 	} } else if ((enablers.getRole().trim().equalsIgnoreCase("L2"))
 						|| users.getCpfNo().equalsIgnoreCase("78619")) {
 %> <%
 	if (post.getOpStatus().equals("OPEN") && post.getRvStatus().equals("CORPORATE") ) {
 %> <input type="submit" value="Comment by Corporate Enabler"
					id="button" name="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#myModalCP" /> <%
 	}
 				}
 			
 		}
 	}
 %>
			</tr>
			<tr>
				<td>
				<%= sdf1.format(post.getPostDate()) %></td>
			</tr>
			<tr>
				<td><b>Subject: <%=post.getSubject()%></b></td>
			</tr>
			<tr>
				<td><p><%=post.getMessage()%></p></td>
			</tr>
			<s:if test="%{reasonList.isEmpty()}"></s:if>
			<s:else>
				<tr>
					<td><s:iterator value="reasonList">
							<%
							if(reasonList.size() != 0){
								for (FeedbackReason reason : reasonList) {
							%>
							<table class="table table-bordered table-striped mb0">
								<tbody>
									<tr>
										<td> <a title="Click to view full profile"
											onclick="popup(<%= reason.getUser().getCpfNo() %>);"><%=reason.getUser().getEmpName()%></a>,
											<%=reason.getUser().getDesignation()%>,<%=reason.getUser().getLocalAddress()%>  <span
											class="alert alert-danger pt5 pl5 pr5 pb5 pull-right mb0">
											
												<% if (reason.getStatus().equalsIgnoreCase("CLOSE")) {	%>
												Closed
												<% } if (reason.getStatus().equalsIgnoreCase("CORPORATE")) { %> 
												Forwarded to Corporate Enabler <% } %>
										</span></td>
									</tr>
									<tr>
										<td><%=reason.getReasonOn()%></td>
									</tr>
									<tr>
										<td><p class="locEnabColor"><%=reason.getReason()%></p></td>
									</tr>
								</tbody>
							</table>
							<%
								}
							}
							%>
						</s:iterator></td>
				</tr>
			</s:else>
			<%
			if(feedbackComment!=null && feedbackComment.size()!=0){
				for (FeedbackComment comment : feedbackComment) {
			%>
			<tr>
				<td><div class="alert alert-danger" role="alert" style="display: none;"
				id="alert"></div>
					<table class="table table-bordered table-striped mb0" id="data-<%=comment.getCommentId()%>">
						<tr>
							<td><a title="Click to view full profile"
								onclick="popup(<%=comment.getUser().getCpfNo()%>);"> <%=comment.getUser().getEmpName()%></a>,
								<%=comment.getUser().getDesignation()%>, <%=comment.getUser().getLocalAddress()%>
							<div class="pull-right">
												 <%if(comment.getStatus().equalsIgnoreCase("Active")){ %>
													<s:set var="role" value="users.getCpfNo()" />
													<%
														if (users.getCpfNo().equals("76121") || users.getCpfNo().equals("78619")
															 || users.getCpfNo().equals("122379") || users.getCpfNo().equals("editor1")) {
													%>
												
													<a href="javascript:archive('<%=post.getPostId()%>','<%=comment.getCommentId()%>')"
														title="Archive" cssClass="btn btn-primary">
										        Archive
	        </a>
													<%
														if (!users.getCpfNo().equals("role")) {
													%>
													<!-- <a
														onclick="return confirm('Do you want to delete feedback comment')"
														title="Delete" cssClass="btn btn-primary"
														action="deltFeedbackComment">
														<s:param name="comment.commentId">
															<s:property value="commentId" />
														</s:param>
														<s:param name="post.postId">
															<s:property value="postId" />
														</s:param>
	        Delete
	        </a> -->
													<%
														}
																}
												 }
													%>
												<%if(comment.getStatus().equalsIgnoreCase("Inactive")){ %>
													<%
													if (users.getCpfNo().equals("76121")
															|| users.getCpfNo().equals("78619")
															|| users.getCpfNo().equals("122379")){
													%>
													<s:a
														onclick="return confirm('Do you want to restore feedback comment')"
														title="Restore Archive" cssClass="btn btn-primary"
														action="resArchFeedbackComment">
														<s:param name="comment.commentId">
															<s:property value="commentId" />
														</s:param>
														<s:param name="post.postId">
															<s:property value="postId" />
														</s:param>
	        Restore Archive
	        </s:a>


													<s:a
														onclick="return confirm('Do you want to delete feedback comment')"
														title="Delete" cssClass="btn btn-primary"
														action="deltFeedbackComment">
														<s:param name="comment.commentId">
															<s:property value="commentId" />
														</s:param>
														<s:param name="post.postId">
															<s:property value="postId" />
														</s:param>
	        Delete
	        </s:a>
													<%
														}
												}
													%>
											</div>
							</td>
						</tr>
						<tr>
							<td>
							
							<%=comment.getPostTextDate()%></td>
						</tr>
						<tr>
							<td>
								<%
									if (comment.isAuthResp()) {
								%> <img title="" width="30" height="30"> <br>Response<br>
								<br /> <%
 	} else {
 %> <b><%=srn++%>. Comment:</b> <%
 	}
 %> <%
 	if (comment.isAuthResp()) {
 %> <span><%=comment.getCommentText()%></span> <%
 	} else {
 %> <%=comment.getCommentText()%> <%
 	}
 %>
							</td>
						</tr>

					</table>
				</td>

			</tr>
			<%
				}
			}
		
			%>
		</table>
	</div>
	<div class="col-lg-12 mt-4 text-center">
		<%
		if (post.getOpStatus().equals("OPEN")) {
			if (!(users.getCpfNo().equals("81750") || users.getCpfNo().equals("57477"))) {
		%>
		<a href="<%=feedbackCommentForm%>" class="btn-primary btn btn-sm">Give
			Your Comments</a>
		<%
			}
		}
		%>
		<a href="<%=backURL%>" class="btn-primary btn btn-sm">Back</a>
	</div>
</div>

<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Corporate</h4>
			</div>
			<div class="modal-body">
				<portlet:actionURL var="forwardtocorporate"
					name="forwardToCorporate"></portlet:actionURL>

				<aui:form method="POST" name="forwardtocorporate">
					<aui:row>
						<aui:input label="Post ID" name="postId" type="hidden"
							value="<%=post.getPostId()%>" />
						<aui:select label="Category" name="category">
							<aui:option value="">Select</aui:option>
							<%
								for (FeedbackHrCategory hrCategory : hrCategoryList) {
							%>
							<aui:option value="<%=hrCategory.getHrCatId()%>"><%=hrCategory.getHrCategory()%></aui:option>
							<%
								}
							%>
						</aui:select>
					</aui:row>
					<aui:row>
						<aui:input name="comment" type="textarea"></aui:input>
					</aui:row>
					<aui:button-row cssClass="text-center">
						<aui:button name="submitButton" type="submit" onclick="ajaxCall()"
							value="Submit" cssClass="btn-primary btn btn-sm" />
					</aui:button-row>
				</aui:form>
				<script type="text/javascript">
function ajaxCall(){
	var postId = '<%=post.getPostId()%>';
	var category = $('#<portlet:namespace/>category').val();
	var comment = $('#<portlet:namespace/>comment').val();
	jQuery.ajax({
        type     : 'POST',
    	url      : '<%=forwardtocorporate%>',
    	data: {"<portlet:namespace/>postId":postId,"<portlet:namespace/>category":category,"<portlet:namespace/>comment":comment},
    	success  : function(data){ 
		location.reload();
		}
    	});
	}
</script>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<div class="modal fade" id="myModalCP" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Comment By Corporate Enabler</h4>
			</div>
			<div class="modal-body">
				<portlet:actionURL var="closeCommentFeedPost"
					name="commentByCorporate"></portlet:actionURL>

				<aui:form method="POST" name="commentbycorporate">
					<aui:row>
						<aui:input label="Post ID" name="postId" type="hidden"
							value="<%=post.getPostId()%>" />
					<aui:input name="revertReason" type="textarea" label="Comment" required="required" maxlength="250"></aui:input>
					</aui:row>
					<aui:button-row cssClass="text-center">
						<aui:button name="submitButton" type="submit" onclick="commentByCorporate()"
							value="Submit and Close Post" cssClass="btn-primary btn btn-sm" title="Submit and Close Post"/>
					</aui:button-row>
				</aui:form>
				<script type="text/javascript">
					function commentByCorporate(){
						var postId = '<%=post.getPostId()%>';
						var comment = $('#<portlet:namespace/>revertReason').val();
						jQuery.ajax({
					        type     : 'POST',
					    	url      : '<%=closeCommentFeedPost%>',
					    	data: {"<portlet:namespace/>postId":postId,"<portlet:namespace/>comment":comment},
					    	success  : function(data){ 
							location.reload();
							}
					    	});
						}
				</script>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<script>
	 function popup(cpfNo){
		 var url="<%=getEmployeeData%>&<portlet:namespace />cpfNo="+cpfNo;
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 800,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "Employee Details",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         location.reload();
	     },
	     ['liferay-util-window']
	     );  
	}
	 
	 function commentDetails(postId){
		 var url="<%=getCommentByLocal%>&<portlet:namespace />postId="+postId;
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 800,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "Comment by Local Enabler",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         //location.reload();
	     },
	     ['liferay-util-window']
	     ); 
	}
	 

	 function feedLike(postid,cpfnum,status){
			$.ajax({
			      type: "POST",
			      url: '<%= likeFeedbackUrl.toString() %>',
			      data:"<portlet:namespace/>postid="+postid+"&<portlet:namespace/>cpfnum="+cpfnum+"&<portlet:namespace/>status="+status,
			      success: function( response ) {
			    	  <% Integer likecount1 = (int) request.getAttribute("likecount");
			    	  out.println(likecount1);
			    	  Integer dislikecount1 = (int) request.getAttribute("dislikecount");
			    	  %>
			    	  $("#pageLikeLink").html("");
			    	  $("#pageLikeLink").hide();
			    	  $("#pageDisLikeLink").html("");
			    	  $("#pageDisLikeLink").hide();
			    	  $("#pageLikeCount").html( "("+<%= likecount1 %>+")");
			    	  $("#dislikeCount").html( "("+<%= dislikecount1 %>+")");
			      }
			});
		}

/* 	 function getFeedLikeCount(postid,cpfnum){
	 	$.ajax({
	 	      type: "POST",
	 	      url: "/wps/PA_ONGC_Report_M/jspPages/web/feedback/feedBackLikeCount.jsp",
	 	      data:"postid="+postid+"&cpfnum="+cpfnum,
	 	      success: function( response ) {
	 	      $("#pageLikeCount").html( "("+$(response).find("totalLikeCount").text()+")");
	 	      $("#dislikeCount").html( "("+$(response).find("totalDisLikeCount").text()+")");
	 	      var flag = $(response).find("userLike").text();
	 	      if(flag == "true"){
 	    		  $("#pageLikeLink").hide();
 	    		  $("#pageLikeLink").html("");
 	    		  $("#pageDisLikeLink").html("");
 	    	      $("#pageDisLikeLink").hide();
	 		    }else {
 	    		  $("#pageLikeLink").show();
 	    		  $("#pageDisLikeLink").show();
	 	    	  }
	 	      }
	 	});
	 } */

	 function getFeedbackHitsCount(postid,cpfnum){
	 	$.ajax({
	 	      type: "POST",
	 	      url: "<%= hitsFeedbackUrl %>",
	 	      data:"<portlet:namespace/>postid="+postid+"&<portlet:namespace/>cpfnum="+cpfnum,
	 	      success: function( response ) {
	 	    	  <% Integer feedhitcount1 = (int) request.getAttribute("feedhitcount"); %>
	 	    	$("#feedHitCount").html( "("+<%= feedhitcount1 %>+")");	    		
	 	      }
	 	});
	 }

	 $(document).ready(function() {
	 getFeedbackHitsCount('<%=post.getPostId()%>','<%=users.getCpfNo()%>');
	 	 setTimeout(function(){
	 		getFeedLikeCount('<%=post.getPostId()%>','<%=users.getCpfNo()%>');
	 	},300); 
	 });
	  
	 
	 function archive(postId,commentId) {
		 let text = "Do you want to archive feedback comment.";
		 alert(postId+"/"+commentId);
		  if (confirm(text) == true) {
			AUI().use(
					'aui-io-request',
					function(A) {
						A.io.request('${archiveURL}', {
							method : 'POST',
							dataType : "json",
							cache : true,
							data : {
								<portlet:namespace/>postId : postId,<portlet:namespace/>commentId : commentId,
							},
							on : {
								success : function() {
									 var responseData = new Array();
										responseData = this.get('responseData');
										console.log(responseData);
										for (var i = 0; i < responseData.length; i++) {
											var id = responseData[i]['flageKey'];
											 $('#alert').append('Comment archived successfully.');
												$('#alert').css("display", "block");
											setTimeout(function(){
									            $('#alert').slideUp(500, function(){ $(this).remove(); });
									        }, 5000);
											if(id){$('#data-'+commentId).remove();}
										}
								},
								error : function() {
									alert("Error occured on server.");
								}
							}
						});
					});
		} 
	 }
		  
	</script>
