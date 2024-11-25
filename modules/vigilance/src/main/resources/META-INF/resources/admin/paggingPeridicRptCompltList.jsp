<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="java.util.List"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


<%
String startWith=request.getParameter("startWith");

List list=(List)request.getSession().getAttribute("complaintList");

ComplaintPaginator cpag=new ComplaintPaginator(list,10,startWith);
java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
 %>
 
	  
	  
	 
		    	    <div class="table-responsive sr-table" id="complaintTD">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
		      <thead>
			  <tr >
		           <th>S.No.</th>
		        <th>Complaint No.</th>
		         <th >Complaint Date</th>
		        <th >Subject</th>
		       <%-- <th >Attachment</th>	--%>		       
		         <th>Name</th>
		        <th >Mobile No.</th>
		       <th >Status</th>
		        </tr>
				</thead><tbody>
		 <% while(cpag.isNext()){
			VigilanceComplaint vc=(VigilanceComplaint)cpag.next();
			List<ComplaintAttachment> lst=vc.getAttachmentList();
			String action=vc.getAction()==null?"":vc.getAction();
		%>       
		      <tr>
		        <td><%=cpag.getIndex() %></td>
		       <td><span onclick="viewcomplaintpage(<%=vc.getComplaintNo() %>)" style="color: blue;font-weight: bold;cursor:pointer"> <%= vc.getComplaintActNo() %></span> </td>
		        <td><%= dateFormat.format(vc.getComplaintDate())%></td>
		         <td><%= vc.getComplaintSubject() %></td>
		       <%--  <td><% for(int i=0;i<lst.size();i++){ out.println("<a href='/wps/PA_VIgilance/admin/downloadJsp.jsp?attachmentId="+lst.get(i).getAttachmentId()+"'>"+lst.get(i).getFileName()+"</a> | "); }%></td>--%>
		         <td><%=vc.getComplainBy().getTitle()+" "+vc.getComplainBy().getFirstName()%></td>
		        <td><%= vc.getComplainBy().getMobile()%></td>
		        <td><%= action%></td>
		        </tr>
		        <% }%>
		      </tbody>
		      </table>
			         </div>
  </div>  
					

	    <div class="row">
	    <div class="col-md-12">
           <%= cpag.createIndex("callPeriodicRptPage") %>
        </div>  
   </div>


