<%@page import="java.text.SimpleDateFormat"%>
<!-- <script type="text/javascript"
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/jq-2.2.4/dt-1.10.13/fc-3.2.2/fh-3.1.2/r-2.1.0/sc-1.4.2/datatables.min.css" />
 -->
 <%@page import="com.ongc.liferay.reports.model.AskExpert"%>
<%@ include file="/init.jsp" %>
<%
List<AskExpert> qlist = (List<AskExpert>) request.getAttribute("qlist");
List<Domain> domainList = (List<Domain>) request.getAttribute("domainList");
%>
<script type="text/javascript">
    $("#resetButton").click(function () {
    	$("#<portlet:namespace/>domain").val('');
    	$("#<portlet:namespace/>startDate").val('');
    	$("#<portlet:namespace/>endDate").val('');
    	$("#<portlet:namespace/>queryID").val('');
    	$("#<portlet:namespace/>keyword").val('');
       
    });
</script>
<portlet:renderURL var="getQueriesData">
	<portlet:param name="mvcRenderCommandName" value="getAskExpertData"/>
</portlet:renderURL>

<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
 <portlet:renderURL var="getviewAskExpert" >
	<portlet:param name="mvcRenderCommandName" value="viewAskExpert"/>
	<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>"></portlet:param>
</portlet:renderURL>
<div class="formWrapper">
		<div class="row">
			<div class="col-md-12">
			<aui:form action="<%=getQueriesData %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Ask Your Expert"> -->
			<aui:row>
				<aui:col width="100"><div class="alert alert-primary" role="alert" style="display:none;" id="alert"></div></aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="Start Date" name="startDate" type="date" onchange="checkParameter();checkStartDate();"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="End Date" name="endDate" type="date" onchange="checkParameter();checkEndDate();"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
						<aui:select label="Domain" name="domain" onchange="checkParameter()">
					 		<aui:option value="">Select</aui:option>
							 <% for (Domain domain:domainList){ %>
							<aui:option value="<%= domain.getDomainId() %>"><%= domain.getDomainName() %></aui:option>
							<%} %>
						</aui:select>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="Query ID" name="queryID" type="text" onkeyup="checkParameter()"/>
				</aui:col>
				<aui:col width="18" style="padding-right: 5px;padding-left: 5px;">
					<aui:input label="Keyword" name="keyword" type="text" onkeyup="checkParameter()"/>
				</aui:col>
				<aui:button-row style="margin-top:2%;">
				<aui:button name="submitButton" type="submit" value="Search" onClick="checkParameter()" cssClass="btn btn-primary" />
				<button name="resetButton" id="resetButton" type="button"  onclick="reset();checkParameter();" class="btn btn-primary" >Reset</button>
				<!--  <aui:button name="resetButton" type="button" value="Reset" onClick="this.form.reset()" cssClass="btn btn-primary"/>--> 
			</aui:button-row>
				</aui:row>
			</aui:fieldset>
			</aui:fieldset-group>
			</aui:form>
		</div>
	</div>
		<!-- <div class="row">
			<div class="col-lg">
			<a href="#agreeBox" class="btn" data-toggle="modal" style="background-color: #8c000d;color: #fff !important;">Post Message</a>
			<a class="btn" href="" style="background-color: #8c000d;color: #fff !important;">My Posts</a>
			</div>
		</div> -->
		<div class="row">
			<div class="col-lg mt-4">
				<div class="table-responsive">
  <table id= "viewAskExpert" class="table table-striped table-bordered">
			       <thead>
			            <tr>
							<th>S No</th>
							<th>Query ID</th>
							<th>Domain Name</th>
							<th>Message</th>
							<th>Created By</th>
							<th>No. of Replies</th>
							<th>Last Comment By</th>
							<th>Domain Expert Response</th>
			            </tr>
			        </thead>
			       
			        <tbody>
			        <% 
			        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			        int i=0; for(AskExpert post:qlist){ i++; 
			        %>  
			       <tr>
			       <td><%= i %></td>
			       <td><a onClick="askExpertReply('<%= post.getQueryid() %>')"><%= post.getQueryid() %></a></td>
			       <td><%= post.getDomainName() %></td>
			       <td><%= post.getMessage() %></td>
			       <td><a onclick="popup('<%= post.getCpfno() %>')"><%=post.getUsername()%></a> on <%= sdf1.format(post.getPosted_on()) %></td>
			       <td><%= post.getNoofreplies() %></td>
			       <td><%if(post.getLastcommented_name()!=null){ %><a onclick="popup('<%= post.getLastcommented_by() %>')">
			       <%= post.getLastcommented_name() %></a> on <%if(post.getLastcommented_on()!=null){%><%= sdf1.format(post.getLastcommented_on()) %><%}} %></td>
			       <td><% if(post.getExpertResponse()!= null && post.getExpertResponse()!= ""){%><a onClick="askExpertReply('<%= post.getQueryid() %>')">Yes</a><%} %></td>
			       </tr>
			       <% } %>
			        </tbody>
			 </table> 
			</div>  
		</div> 
		</div> 
	</div>
	<script type="text/javascript">
	 function checkStartDate(){
		 var today = new Date().toISOString().split('T')[0];
		 var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
		 var divAlert = document.getElementById('<portlet:namespace/>alert');
		 if(startDate > today){
			 $('#alert').empty();
			 $('#alert').append('Start Date must not be greater than today\'s date');
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
		 if(endDate > today & endDate > startDate ){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be greater than today\'s date and Start Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else if(endDate == startDate & endDate > today){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be greater than today\'s date and Start Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else if(endDate < startDate){
			 $('#alert').empty();
			 $('#alert').append('End Date must not be less than Start Date');
			 $('#alert').css("display", "block");
			 document.getElementById("<portlet:namespace/>submitButton").disabled = true;			 
		 }
		 else{
			$('#alert').css("display", "none");
		 	document.getElementById("<portlet:namespace/>submitButton").disabled = false;}
		
	 }
	function checkParameter(){
		 if(SearchCheck()) {
		 }
		    else {
		    	document.getElementById("<portlet:namespace/>submitButton").disabled = true;
		    }
	}
	function SearchCheck() {
	   var flag = false;
	  	var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
	  	//alert(startDate);
	  	var  endDate=document.getElementById('<portlet:namespace/>endDate').value;
	  	var  keyword=document.getElementById('<portlet:namespace/>keyword').value;
	  	var  domain=$('#<portlet:namespace/>domain').val();
	  	var  queryID=$('#<portlet:namespace/>queryID').val();
	       if(startDate != "" || endDate != "" || keyword != "" || domain != "" || queryID != "") {
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
	$(document).ready(function() {
		checkParameter(); checkStartDate();checkEndDate();document.getElementById("<portlet:namespace/>submitButton").disabled = true;
		var table = $('#viewAskExpert').DataTable({lengthChange: false,bFilter: false, bInfo: false});
	});
	 function askExpertReply(queryId){
		 window.location.href="<%= getviewAskExpert %>&<portlet:namespace />queryId="+queryId;
	 } 
</script>