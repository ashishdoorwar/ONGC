<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp" %>
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css"/>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script> -->
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
List<FeedbackComment> feedbackComment = (List<FeedbackComment>) request.getAttribute("commentList");
//List<FeedbackReason> reasonList = (List<FeedbackReason>) request.getAttribute("reasonList");
//List<FeedbackHrEnablers> HREnablersList = (List<FeedbackHrEnablers>) request.getAttribute("commentList");
//List<FeedbackHrCategory> hrCategoryList = (List<FeedbackHrCategory>) request.getAttribute("commentList");
String startDate = (String) request.getAttribute("startDate");
	String endDate = (String) request.getAttribute("endDate");
	out.println(noOfRecords);
%>    
<portlet:renderURL var="myPostForm" > 
	<portlet:param name="mvcRenderCommandName" value="getMyPosts"/>
</portlet:renderURL>
<portlet:renderURL var="unrespondedPosts" > 
	<portlet:param name="mvcRenderCommandName" value="unrespondedPosts"/>
</portlet:renderURL>
   <portlet:renderURL var="searchFeedback" > 
	<portlet:param name="mvcRenderCommandName" value="searchFeedbackPost"/>
</portlet:renderURL>
   
 <portlet:renderURL var="feedbackPostForm" > 
 	<portlet:param name="mvcPath" value="/feedback/feedbackPostForm.jsp"/>
 </portlet:renderURL>
 
 <portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>

 <portlet:renderURL var="getCommentData" >
	<portlet:param name="mvcRenderCommandName" value="getCommentInfo"/>
	<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>"></portlet:param>
</portlet:renderURL>

	<div class="formWrapper">
		<div class="row">
			<div class="col-md-12">
			<aui:form action="<%= searchFeedback %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Feedback"> -->
			<aui:row>
				<aui:col width="100"><div class="alert alert-primary" role="alert" style="display:none;" id="alert"></div></aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="Start Date" name="startDate" type="date" value="<%= startDate %>" onchange="checkParameter();checkStartDate();"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="End Date" name="endDate" type="date" value="<%= endDate %>" onchange="checkParameter();checkEndDate();"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
						<aui:select label="Category" name="category" onchange="checkParameter()">
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
					<aui:input label="Post ID" name="postID" type="number" onkeyup="checkParameter()"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="Keyword" name="keyword" type="text" onkeyup="checkParameter()" />
				</aui:col>
				<aui:button-row style="margin-top:2.7%;">
				<aui:button name="submitButton" type="submit" onclick="checkParameter()" value="Search" cssClass="btn btn-primary" />
				<button name="resetButton" id="resetButton" type="button"  onclick="reset();checkParameter();" class="btn btn-primary" >Reset</button>
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
			<a href="#agreeBox" class="btn btn-primary btn-sm mr-1" data-toggle="modal">Post Message</a>
			<a class="btn btn-primary btn-sm mr-1" href="<%= myPostForm %>" >My Posts</a>
			<a class="btn btn-primary btn-sm mr-1" href="<%= unrespondedPosts %>" >View Unresponded Posts</a> 
		    </div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg">
<%-- <liferay-ui:search-container total="<%=postList.size()%>" var="searchContainer" delta="20" deltaConfigurable="true" 
  emptyResultsMessage="Oops. There Are No Users To Display, Please add Users">
  
 <liferay-ui:search-container-results results="<%=ListUtil.subList(postList, searchContainer.getStart(),searchContainer.getEnd())%>" />
  <liferay-ui:search-container-row className="com.liferay.ongc.reports.model.FeedbackPost" modelVar="post" keyProperty="postId" escapedModel="<%=true%>">
   <liferay-ui:search-container-column-text name="Post ID" value="${post.postId}"/>
   <liferay-ui:search-container-column-text title="${post.message}" name="Subject" value="${post.subject}"/>
   <liferay-ui:search-container-column-text name="Category" value="${post.category.description}"/>
   <liferay-ui:search-container-column-text name="Created by" value="${post.user.empName} on ${post.postDate}"/>
   <liferay-ui:search-container-column-text name="Last Comment by" value="${post.lastUpdName} on ${post.srt_date}"/>
   <liferay-ui:search-container-column-text name="Auth Response" >
   <% 
   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
   Date date1 = sdf1.parse(post.getPostDate().toString());
   Date date2 = sdf1.parse("2015-10-14");int workDays=0;
    if(post.getCategoryId()==42 || post.getCategoryId()==62 || post.getCategoryId()==63 || post.getCategoryId()==64 || post.getCategoryId()==65 || post.getCategoryId()==66 || post.getCategoryId()==67 || post.getCategoryId()==68 || post.getCategoryId()==61) {
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus().equalsIgnoreCase("LOCAL") && post.getChiefEr().equalsIgnoreCase("NO") && date1.after(date2))
		{
		  out.println("<img src='/documents/38964/0/green-enabler.png/9a5300a0-7a7f-fb70-fa3d-7e37f35d09e9?t=1646907312798' style='padding-left: 13px;'/>");
		}
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus().equalsIgnoreCase("LOCAL") && post.getChiefEr().equalsIgnoreCase("YES") && date1.after(date2))
		{
		  out.println("<img src='/documents/38964/0/yellow-henabler.png/705825ed-f503-0fd5-42a6-7412aecbc855?t=1646907313845' style='padding-left: 13px;'/>");
		}								
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("CLOSE") && post.getRvStatus().equalsIgnoreCase("LOCAL") && date1.after(date2) )
		{
		out.println("<img src='/documents/38964/0/red-henabler.png/24f62d5b-ba79-c4bc-cfff-e78e89dfe60d?t=1646907487439' style='padding-left: 13px;'/>");}								
									
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus().equalsIgnoreCase("CORPORATE") && workDays<3 && date1.after(date2) ){
		out.println("<img src='/documents/38964/0/blue-henabler.png/977b0f9b-a946-f534-f21c-ddfd98716f82?t=1646907311590' style='padding-left: 13px;'/>");}							
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus().equalsIgnoreCase("CORPORATE")&& workDays>3 && date1.after(date2)){
		out.println("<img src='/documents/38964/0/yellow-henabler.png/705825ed-f503-0fd5-42a6-7412aecbc855?t=1646907313845' style='padding-left: 13px;'/>");}
										
		if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("CLOSE") && post.getRvStatus().equalsIgnoreCase("CORPORATE") && date1.after(date2)){
		out.println("<img src='/documents/38964/0/red-henabler.png/24f62d5b-ba79-c4bc-cfff-e78e89dfe60d?t=1646907487439' style='padding-left: 13px;'/>");}
		}
		else if(post.getOpStatus()!=null && !post.getAuth_status().equals("0")){out.println("<img src='/documents/38964/0/comment.png/a387bde4-06dd-1818-f9ac-db94dacdd5de?t=1646907822093'/>");}%>

   </liferay-ui:search-container-column-text>
   
   <liferay-ui:search-container-column-text name="No. of Replies" value="${post.noOfReplies} "/>
  </liferay-ui:search-container-row>
 <liferay-ui:search-iterator />
</liferay-ui:search-container>  --%>
<div class="table-responsive">
		   <table id= "viewFeedback" class="table table-striped table-bordered" >
			       <thead>
			            <tr>
			                <th>SR</th>
			                <th>Post ID	</th>
			                <th>Subject</th>
			                <th>Category</th>
			                <th>Created by</th>
			                <th>Last Comment by</th>
			                <th>Auth Response</th>
			                <th>No. of Replies</th>
			            </tr>
			        </thead>
			        <tbody>
			        <% int i=0;

					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		            int workDays=0;
			      for(FeedbackPost post:postList){ i++;
			       //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		            Date date1 = sdf1.parse(post.getPostDate().toString());
		            Date date2 = sdf1.parse("2015-10-14"); %>
			       <tr>
			       <td><%= i %></td>
			       <td><%= post.getPostId() %></td>
			       <td><a onclick="commentDetails(<%= post.getPostId() %>)"><span title=''><%= post.getSubject() %></span></a></td>
			       <td><%= post.getCategory().getDescription() %></td>
			       <td><a onclick="popup('<%=post.getUser().getCpfNo()%>')"><%=post.getUser().getEmpName()%></a>
								on <%if(post.getPostDate()!=null){%><%=sdf1.format(post.getPostDate())%><%} %></td>
							<td><a onclick="popup('<%=post.getUser().getCpfNo()%>')"><%=post.getLastUpdName()%></a>
								on <%if(post.getSrt_date()!=null){%><%=sdf1.format(post.getSrt_date())%><%} %></td>
							
							  <td><% if(post.getCategoryId()==42 || post.getCategoryId()==62 || post.getCategoryId()==63 || post.getCategoryId()==64 || post.getCategoryId()==65 || post.getCategoryId()==66 || post.getCategoryId()==67 || post.getCategoryId()==68 || post.getCategoryId()==61) {
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("LOCAL") && post.getChiefEr()!=null && post.getChiefEr().equalsIgnoreCase("NO") && date1.after(date2))
								{%>
								  <img src='<%=request.getContextPath()%>/images/green-enabler.png' style='padding-left: 13px;'/>
								<% }
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("LOCAL") && post.getChiefEr()!=null && post.getChiefEr().equalsIgnoreCase("YES") && date1.after(date2))
								{%>
								  <img src='<%=request.getContextPath()%>/images/yellow-henabler.png' style='padding-left: 13px;'/>
								<%}								
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("CLOSE") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("LOCAL") && date1.after(date2) )
								{%>
								  <img src='<%=request.getContextPath()%>/images/red-henabler.png' style='padding-left: 13px;'/>
								<%}							
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("CORPORATE") && workDays<3 && date1.after(date2) ){
									%>
									  <img src='<%=request.getContextPath()%>/images/blue-henabler.png' style='padding-left: 13px;'/>
									<%}							
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("OPEN") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("CORPORATE")&& workDays>3 && date1.after(date2)){
									%>
									  <img src='<%=request.getContextPath()%>/images/yellow-henabler.png' style='padding-left: 13px;'/>
									<%}
																
								if(post.getOpStatus()!=null && post.getOpStatus().equalsIgnoreCase("CLOSE") && post.getRvStatus()!=null && post.getRvStatus().equalsIgnoreCase("CORPORATE") && date1.after(date2)){
									%>
									  <img src='<%=request.getContextPath()%>/images/red-henabler.png' style='padding-left: 13px;'/>
									<%}
								}
								else if(!post.getAuth_status().equals("0")){
									%>
									  <img src='<%=request.getContextPath()%>/images/comment.png' style='padding-left: 13px;'/>
									<%}%>
					</td>  
			       <td><%= post.getNoOfReplies() %></td>
			       </tr>
			       <% } %>
			        </tbody>
			 </table> 
			</div>   
		</div> 
		</div> 
	</div>
<div class="modal" id="agreeBox">
		<div class="agreeBlock modal-dialog">
			<div class="modal-content">
			<div class="modal-header">
          
          <h4 class="modal-title">Your feedback is valuable to us. We will make sure to
				address your queries and issues to your satisfaction.</h4>
				<a href="javascript:void(0)" class="close" id="closebox" data-dismiss="modal">&times;</a>
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
				<a id="closebox" tile="Disagree" data-dismiss="modal" class="btn btn-primary">I Disagree</a>&nbsp;&nbsp;
				<a onclick="showfeedbackForm();" id="iagree" title="Agree" class="btn btn-primary">I
					Agree</a>
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
		 var url="<%=getEmployeeData %>&<portlet:namespace />cpfNo="+cpfNo;
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
		 window.location.href="<%= getCommentData %>&<portlet:namespace />postId="+postId;
	 } 
	 
	 $(document).ready(function() {
		 var table = $('#viewFeedback').DataTable( {
			 lengthChange: false,bFilter: false, bInfo: false
		    });
		}); 
		 
	 function showfeedbackForm(){
				$("#agreeBox").modal('hide');
				window.location.href='<%= feedbackPostForm %>';
			}
	</script>