<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.model.OrderCircular"%>
<%@page import="com.ongc.liferay.model.OrderCircularCategory"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@ include file="/init.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script>
$(function(){
$('.datepicker').datepicker({
format: 'mm-dd-yyyy'
});
});
</script>

<%
	List<OrderCircularCategory> orderCircularCat = (List<OrderCircularCategory>) request.getAttribute("categoryOrderCircular");
	List<OrderCircular> orderList = (List<OrderCircular>) request.getAttribute("orderCircular");
	String startDate = (String) request.getAttribute("startDate");
	String endDate = (String) request.getAttribute("endDate");
	LocalDate enDate = java.time.LocalDate.now();
	LocalDate stDate = enDate.minusMonths(1);
%>
<portlet:renderURL var="getOrderCircular">
	<portlet:param name="mvcRenderCommandName" value="searchOrderCircular"/>
</portlet:renderURL>
<div class="formWrapper">
 <aui:form action="<%= getOrderCircular %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
	<!-- 	<aui:fieldset label="Orders and Circulars"> -->
		<label for="Search By">Search By</label><hr>
			<aui:row>
				<aui:col width="50">
				<%if(startDate!=null){ %>
					<aui:input label="Start Date" name="startDate" type="date" value="<%= startDate %>" onChange="checkParameter()"/>
					<%}else{ %>
					<aui:input label="Start Date" name="startDate" type="date" value="<%= stDate %>" onChange="checkParameter()"/>
					<%} %>
				</aui:col>
				<aui:col width="50">
				<%if(endDate!=null){ %>
					<aui:input label="End Date" name="endDate" type="date" value="<%= endDate %>" onChange="checkParameter()"/>
					<%}else{ %>
					<aui:input label="End Date" name="endDate" type="date" value="<%= enDate %>" onChange="checkParameter()"/>
					<%} %>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input label="Keyword" name="keyword" type="text" cssStyle="datepicker" onkeyup="checkParameter()"/>
				</aui:col>
				<aui:col width="50">
					<aui:select label="By Category" name="category" onchange="javascript:checkParameter();">
						<aui:option value="">Select</aui:option>
					<% if(orderCircularCat!=null){for(OrderCircularCategory orderCircular: orderCircularCat){ %>
						<aui:option value="<%= orderCircular.getCategoryName() %>"><%= orderCircular.getCategoryName() %></aui:option>
						<%} }%>
					</aui:select>
				</aui:col>
			</aui:row>
			<div id="searchButton">
			<aui:button-row cssClass="text-center">
				<aui:button name="submitButton" type="submit" value="Submit" onclick="checkParameter()" cssClass="btn btn-sm btn-primary" />
			</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>
</div>
<div class="table-responsive">
<table id="viewOrderAndCircular" class="table table-striped table-bordered mt-2" >
	<thead>
		<tr>
			<th>Subject</th>				
			<th>Issued By</th>
			<th>Category</th>
			<th>Order Number</th>
			<th>Order Date</th>
			<th>Posted On</th>
			<th style="display:none;"></th>
		</tr>
	</thead>
	<tbody>
		<% if(orderList !=null){ 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i=0;
			      for(OrderCircular order:orderList){ i++; %>
		<tr>
			<td><a target="_blank" title="Download PDF" href='<%= themeDisplay.getURLPortal() %>/o/blade/pdfservlet/pdf?pdfname=<%= order.getFileUploadFileName() %>'><%= order.getSubject() %></a></td>
			<td><%= order.getIssuedBy() %></td>
			<td><%= order.getCategory() %></td>
			<td><%= order.getOrderNumber() %></td>
			<td><%= sdf1.format(sdf.parse(order.getOrderDate())) %></td>
			<td><%= sdf1.format(sdf.parse(order.getCretDate().toString())) %></td>
			<td style="display:none;"><%= order.getOrderDate() %></td>
		</tr>
		<% } }%>
	</tbody>
</table>
</div>
<script>
function checkParameter(){
	 if(SearchCheck()) {
	 }
	    else {
	    //	 $('#alert').css("display", "block");
	    	// $('#searchButton').css("display", "none");
	    	document.getElementById("<portlet:namespace/>submitButton").disabled = true;
	    }
}
$(document).ready(function() {
	 checkParameter();
});
function SearchCheck() {
   var flag = false;
  	var  startDate=document.getElementById('<portlet:namespace/>startDate').value;
  	var  endDate=document.getElementById('<portlet:namespace/>endDate').value;
  	var  keyword=document.getElementById('<portlet:namespace/>keyword').value;
  	var  category=$('#<portlet:namespace/>category').val();
       if(startDate != "" || endDate != "" || keyword != "" || category != "") {
    	   document.getElementById("<portlet:namespace/>submitButton").disabled = false;
       	flag = true;
          return flag;
       }
  
   return flag;
}
			 $(document).ready(function() {
				 var table = $('#viewOrderAndCircular').DataTable( {
					 lengthChange: false,bFilter: false, bInfo: false,
					 order: [[6, 'desc']]
				    });
				}); 
			 </script>
			 <script>
            var datefrom;
        YUI().use(
  'aui-datepicker',
  function(Y) {
    datefrom = new Y.DatePicker(
      {
        trigger: '<portlet:namespace/>startDate',
        popover: {
          zIndex: 1
        },
        calendar: {
                maximumDate : new Date(today.getFullYear(),today.getMonth()+1,today.getDate()),
                minimumDate : new Date(),
                },

        on: {
            selectionChange: function(event) {

          }
        }
      }
    );
  }
);

    </script>