<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>

<script type="text/javascript">
    $("#resetButton").click(function () {
    	$("#<portlet:namespace/>category").val('Select');
    	$("#<portlet:namespace/>startDate").val('');
    	$("#<portlet:namespace/>endDate").val('');
    	$("#<portlet:namespace/>postID").val('');
    	$("#<portlet:namespace/>keyword").val('');
       
    });
</script>
<%
	List<FeedbackPost> postList = (List<FeedbackPost>) request.getAttribute("postList");
	int noOfRecords = (Integer) request.getAttribute("countRow");
	int pageStart = (Integer) request.getAttribute("pageStart");
	String pageFeedback = (String) request.getAttribute("page");
%>
<portlet:renderURL var="myPostForm">
	<portlet:param name="mvcRenderCommandName" value="getMyPosts" />
</portlet:renderURL>
<portlet:renderURL var="unrespondedPosts">
	<portlet:param name="mvcRenderCommandName" value="unrespondedPosts" />
</portlet:renderURL>
<portlet:renderURL var="searchFeedback">
	<portlet:param name="mvcRenderCommandName" value="searchFeedbackPost" />
</portlet:renderURL>

<portlet:renderURL var="feedbackPostForm">
	<portlet:param name="mvcPath" value="/feedback/feedbackPostForm.jsp" />
</portlet:renderURL>

<portlet:renderURL var="getEmployeeData"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo" />
</portlet:renderURL>

<portlet:renderURL var="getCommentData">
	<portlet:param name="mvcRenderCommandName" value="getCommentInfo" />
	<portlet:param name="backURL"
		value="<%=themeDisplay.getURLCurrent()%>"></portlet:param>
</portlet:renderURL>

<div class="formWrapper">
	<div class="row">
		<div class="col-md-12">
		
 <liferay-ui:error key="error" message="Illegal argument found" /> 
			<aui:form action="<%=searchFeedback%>" method="post" name="myForm">
				<aui:fieldset-group markupView="lexicon">
					<!-- <aui:fieldset label="Feedback"> -->
					<aui:row>
						<aui:col width="100">
							<div class="alert alert-primary" role="alert"
								style="display: none;" id="alert"></div>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
							<aui:input label="Start Date" name="startDate" type="date"
								onchange="checkParameter();checkStartDate();" />
						</aui:col>
						<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
							<aui:input label="End Date" name="endDate" type="date"
								onchange="checkParameter();checkEndDate();" />
						</aui:col>
						<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
							<aui:select label="Category" name="category"
								onchange="checkParameter()">
								<aui:option value="Select">Select</aui:option>
								<aui:option value="45">Corporate MM</aui:option>
								<aui:option value="51">Drilling Services</aui:option>
								<aui:option value="52">Exploration</aui:option>
								<aui:option value="44">Finance</aui:option>
								<aui:option value="63">HR/ER- Awards</aui:option>
								<aui:option value="65">HR/ER- Employee Welfare</aui:option>
								<aui:option value="66">HR/ER- Grievance</aui:option>
								<aui:option value="68">HR/ER- Local Issues</aui:option>
								<aui:option value="62">HR/ER- PAR, Seniority, Promotions</aui:option>
								<aui:option value="64">HR/ER- Policy</aui:option>
								<aui:option value="61">HR/ER- Transfers </aui:option>
								<aui:option value="67">HR/ER- Trusts &amp; Social Security</aui:option>
								<aui:option value="54">ICE</aui:option>
								<aui:option value="53">IT and Infocom</aui:option>
								<aui:option value="59">Medical</aui:option>
								<aui:option value="69">Offshore</aui:option>
								<aui:option value="70">Onshore</aui:option>
								<aui:option value="43">Organisation</aui:option>
								<aui:option value="41">Portal</aui:option>
								<aui:option value="55">Safety</aui:option>
								<aui:option value="56">Security</aui:option>
								<aui:option value="57">Transport &amp; Logistics</aui:option>
								<aui:option value="71">Well Services</aui:option>
								<aui:option value="72">Make in India</aui:option>
								<aui:option value="73">ECPF Trust</aui:option>
								<aui:option value="60">Other</aui:option>
							</aui:select>
						</aui:col>
						<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
							<aui:input label="Post ID" name="postID" type="number"
								onkeyup="checkParameter()" />
						</aui:col>
						<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
							<aui:input label="Keyword" name="keyword" type="text"
								onkeyup="checkParameter()" />
						</aui:col>
						<aui:button-row style="margin-top:2.7%;">
							<aui:button name="submitButton" type="submit"
								onclick="checkParameter()" value="Search"
								cssClass="btn btn-primary" />
							<button name="resetButton" id="resetButton" type="button"
								onclick="reset();checkParameter();" class="btn btn-primary">Reset</button>
						</aui:button-row>
					</aui:row>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 mt-4">
			<div>
				<a href="#agreeBox" class="btn btn-primary btn-sm mr-1"
					data-toggle="modal">Post Message</a> <a
					class="btn btn-primary btn-sm mr-1" href="<%=myPostForm%>">My
					Posts</a> <a class="btn btn-primary btn-sm mr-1"
					href="<%=unrespondedPosts%>">View Unresponded Posts</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg">
		Total Result (<%=noOfRecords %>)

			<div class="table-responsive">
				<table id="viewFeedback" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>SR</th>
							<th>Post ID</th>
							<th>Subject</th>
							<th>Category</th>
							<th>Created by</th>
							<th>Last Comment by</th>
							<th>Auth Response</th>
							<th>No. of Replies</th>
						</tr>
					</thead>
					<tbody>
						<%
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						int pageSerial = Integer.parseInt(pageFeedback)-1;
							int i = pageSerial*10;
							int workDays = 0;
							for (FeedbackPost post : postList) {
								i++;
								Date date1 = sdf1.parse(post.getPostDate().toString());
								Date date2 = sdf1.parse("2015-10-14");
						%>
						<tr>
							<td><%=i%></td>
							<td><%=post.getPostId()%></td>
							<td><a onclick="commentDetails(<%=post.getPostId()%>)"><span
									title=''><%=post.getSubject()%></span></a></td>
							<td><%=post.getCategory().getDescription()%></td>
							<td><a onclick="popup('<%=post.getUser().getCpfNo()%>')"><%=post.getUser().getEmpName()%></a>
								on <%if(post.getPostDate()!=null){%><%=sdf1.format(post.getPostDate())%><%} %></td>
							<td><a onclick="popup('<%=post.getUser().getCpfNo()%>')"><%=post.getLastUpdName()%></a>
								on <%if(post.getSrt_date()!=null){%><%=sdf1.format(post.getSrt_date())%><%} %></td>
							<td>
								<%
									if (post.getCategoryId() == 42 || post.getCategoryId() == 62 || post.getCategoryId() == 63
												|| post.getCategoryId() == 64 || post.getCategoryId() == 65 || post.getCategoryId() == 66
												|| post.getCategoryId() == 67 || post.getCategoryId() == 68 || post.getCategoryId() == 61) {
											if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("OPEN")
													&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("LOCAL")
													&& post.getChiefEr() != null && post.getChiefEr().equalsIgnoreCase("NO")
													&& date1.after(date2)) {
								%> <img
								src='<%=request.getContextPath()%>/images/green-enabler.png'
								style='padding-left: 13px;' /> <%
 	}
 			if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("OPEN")
 					&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("LOCAL")
 					&& post.getChiefEr() != null && post.getChiefEr().equalsIgnoreCase("YES")
 					&& date1.after(date2)) {
 %> <img
								src='<%=request.getContextPath()%>/images/yellow-henabler.png'
								style='padding-left: 13px;' /> <%
 	}
 			if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("CLOSE")
 					&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("LOCAL")
 					&& date1.after(date2)) {
 %> <img
								src='<%=request.getContextPath()%>/images/red-henabler.png'
								style='padding-left: 13px;' /> <%
 	}
 			if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("OPEN")
 					&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("CORPORATE")
 					&& workDays < 3 && date1.after(date2)) {
 %> <img
								src='<%=request.getContextPath()%>/images/blue-henabler.png'
								style='padding-left: 13px;' /> <%
 	}
 			if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("OPEN")
 					&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("CORPORATE")
 					&& workDays > 3 && date1.after(date2)) {
 %> <img
								src='<%=request.getContextPath()%>/images/yellow-henabler.png'
								style='padding-left: 13px;' /> <%
 	}

 			if (post.getOpStatus() != null && post.getOpStatus().equalsIgnoreCase("CLOSE")
 					&& post.getRvStatus() != null && post.getRvStatus().equalsIgnoreCase("CORPORATE")
 					&& date1.after(date2)) {
 %> <img
								src='<%=request.getContextPath()%>/images/red-henabler.png'
								style='padding-left: 13px;' /> <%
 	}
 		} else if (!post.getAuth_status().equals("0")) {
 %> <img src='<%=request.getContextPath()%>/images/comment.png'
								style='padding-left: 13px;' /> <%
 	}
 %>
							</td>
							<td><%=post.getNoOfReplies()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	

<div class="row" style="float: right;">
	<div class="col-md-12 text-right">
		<ul class="pagination">
			<%
				int a = 1, b = 10, noOfPages;
				if (noOfRecords % 10 == 0)
					noOfPages = noOfRecords / 10;
				else
					noOfPages = (noOfRecords / 10) + 1;
				if (pageStart != 1) {
			%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to First page' title='Link to First page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?")) %>?page=<%= a%>">First </a></li>
			 <li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to Previous page' title='Link to Previous page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${pageStart - 1}"> << </a></li>
		 	<%} if (pageStart == noOfPages) {%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to <%=noOfPages - 4%> page'
				title='Link to <%=noOfPages - 4%> page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=<%=noOfPages - 4%>"> <%=noOfPages - 4%>
			</a>
			</li>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to <%=noOfPages - 3%> page'
				title='Link to <%=noOfPages - 3%> page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=<%= noOfPages - 3%>"> <%=noOfPages - 3%>
			</a>
			</li>
			<%	} if (pageStart == (noOfPages - 1)) {	%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to <%=noOfPages - 4%> page'
				title='Link to <%=noOfPages - 4%> page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=<%=(noOfPages - 5) %>"> <%=noOfPages - 4%>
			</a>
			</li>
			<% }
				for (int j = pageStart - 2; j <= pageStart + 2; j++) {
					a = (j - 1) * (10);
					if (j == pageStart) {%>
			<li class="page-item"><a role='link' aria-disabled='true'
				tabindex='-1' aria-label='Link to <%=j%> Page' class="in-active-pg">
					<%=j%> </a></li>
			<% } else if (j > 0 && j <= noOfPages) {%>
			<li class="page-item">
			<a role='link' tabindex='0'
				aria-label='Link to <%=i%> page' title='Link to <%=j%> page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))%>?page=<%= j%>"> <%=j%> </a>
			</li>
			<%	}//else
				}//for
				if (pageStart == 1) {%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to 4 page' title='Link to 4 page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${pageStart + 3}"> 4 </a>
			</li>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to 5 page' title='Link to 5 page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${pageStart + 4}"> 5 </a>
			</li>
			<%}if (pageStart == 2) {%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to 5 page' title='Link to 5 page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${pageStart + 3}"> 5 </a>
			</li>
			<%} if (pageStart != noOfPages) {%>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to Next page' title='Link to Next page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${pageStart + 1}"> >> </a>
			</li>
			<li class="page-item"><a role='link' tabindex='0'
				aria-label='Link to Last page' title='Link to Last page'
				href="<%= themeDisplay.getPortalURL()  %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))  %>?page=${noOfPages}">Last</a>
			</li>
			<%} else {%>
			<li class="page-item"><a role='link' aria-disabled='true'
				tabindex='-1' aria-label='Link to Next Page' class="in-active-pg">
					>> </a>
			</li>
			<li class="page-item"><a role='link' aria-disabled='true'
				tabindex='-1' aria-label='Link to Last Page' class="in-active-pg">Last</a>
			</li>
			<% } %>
		</ul>
	</div>
</div>


</div>

 <div class="modal" id="agreeBox">
	<div class="agreeBlock modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title">Your feedback is valuable to us. We
					will make sure to address your queries and issues to your
					satisfaction.</h4>
				<a href="javascript:void(0)" class="close" id="closebox"
					data-dismiss="modal">&times;</a>
			</div>
			<div class="modal-body">
				<ol>
					<li>We request you to please go through the previous feedback
						topics to check if similar query has already been posted and
						answered.</li>
					<li>Please post your Issue in a clear manner along with
						relevant details, which shall help us in responding faster.</li>
					<li>Please follow netiquette guidelines while posting your
						feedback. In case netiquette guidelines are violated, the post
						shall be closed and archived/ deleted. Management reserves the
						right to take suitable action in case of repeated violations by an
						individual. Please read the netiquette under interface in ONGC
						Reports.</li>
				</ol>
				<div class="btn-box text-center">
					<a id="closebox" tile="Disagree" data-dismiss="modal"
						class="btn btn-primary">I Disagree</a>&nbsp;&nbsp; <a
						onclick="showfeedbackForm();" id="iagree" title="Agree"
						class="btn btn-primary">I Agree</a>
				</div>
			</div>
		</div>
	</div>
</div>
 <script>
	
	 $(document).ready(function() {
		 checkParameter();
		 checkStartDate();checkEndDate();document.getElementById("<portlet:namespace/>submitButton").disabled = true;
	 });
	 
	 function checkStartDate(){
		 var today = new Date().toISOString().split('T')[0];
		 var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
		 var divAlert = document.getElementById('<portlet:namespace/>alert');
		 if(startDate > today){
			 $('#alert').empty();
			 $('#alert').append('Start date  must be less than current day');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else{
			$('#alert').css("display", "none");
		 	document.getElementById("<portlet:namespace/>submitButton").disabled = false;}
		
	 }
	 function checkEndDate(){
		 var today = new Date().toISOString().split('T')[0];
		 var  endDate=document.getElementById('<portlet:namespace/>endDate').value;
		 var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
		 var divAlert = document.getElementById('<portlet:namespace/>alert');
		 if(endDate > today & endDate < startDate ){
			 $('#alert').empty();
			 $('#alert').append('End date Must not be greater than today\'s date and Must be greater thean Start Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else if(endDate == startDate & endDate > today){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be greater than today\'s date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else if(endDate < startDate){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be less than Start Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else if(endDate > today){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be greater than today\'s Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else{
			$('#alert').css("display", "none");
		 	document.getElementById("<portlet:namespace/>submitButton").disabled = false;}
		
	 }
	function checkParameter(){
		 if(SearchCheck()) {
		 } else {
		    	document.getElementById("<portlet:namespace/>submitButton").disabled = true;
		    }
	}
	function SearchCheck() {
	   var flag = false;
	  	var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
	  	var  endDate=document.getElementById('<portlet:namespace/>endDate').value;
	  	var  keyword=document.getElementById('<portlet:namespace/>keyword').value;
	  	var  category=$('#<portlet:namespace/>category').val();
	  	var  postID=$('#<portlet:namespace/>postID').val();
	       if(startDate != "" || endDate != "" || keyword != "" || category != "Select" || postID != "") {
	    	   document.getElementById("<portlet:namespace/>submitButton").disabled = false;
	       	flag = true;
	          return flag;
	       }
	  
	   return flag;
	}
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
		 window.location.href="<%=getCommentData%>&<portlet:namespace />postId="+postId;
	 } 
	 
	 $(document).ready(function() {
		 var table = $('#viewFeedback').DataTable( {
			 lengthChange: false,bFilter: false, bInfo: false,bPaginate: false
		    });
		}); 
		 
	 function showfeedbackForm(){
				$("#agreeBox").modal('hide');
				window.location.href='<%=feedbackPostForm%>';
			}
	</script>