<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.util.ComplaintPaginator"%>
<%@page import="java.util.List"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%!
public String getTreamFileName(String fileName)
	{
	  String extName=fileName.substring(fileName.lastIndexOf("."));
	   String fName=fileName.substring(0,fileName.lastIndexOf("."));
	   if(fName.length()>15)
	   {
	      fName=fName.substring(0,15)+".."+extName;
	   }
	   else
	   {
	     fName=fileName;
	   }
	   return fName;
	}
%>


<%
String startWith=request.getParameter("startWith");

List list=(List)request.getSession().getAttribute("complaintList");

ComplaintPaginator cpag=new ComplaintPaginator(list,10,startWith);
java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
 %>
    <div class="col-md-12">
		  
		     <div class="table-responsive sr-table">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
		      <thead>
			  <tr >
		           <th>S.No.</th>
		        <th>Complaint No.</th>
		         <th >Complaint Date</th>
		        <th >Subject</th>
		        <th >Attachment</th>		       
		         <th>Name</th>
		        <th >Mobile No.</th>
		       <th >Action</th>
		        </tr>
				</thead><tbody>
		 <% while(cpag.isNext()){
			VigilanceComplaint vc=(VigilanceComplaint)cpag.next();
			List<ComplaintAttachment> lst=vc.getAttachmentList();
		%>       
		         <tr>
		        <td><%=cpag.getIndex() %></td>
		        <td><%= vc.getComplaintActNo() %> </td>
		        <td><%= dateFormat.format(vc.getComplaintDate())%></td>
		         <td><%= vc.getComplaintSubject() %></td>
		        <td>
		        
<% for(ComplaintAttachment complaintAttachment:lst){ 
String ext =complaintAttachment.getFileName().substring(complaintAttachment.getFileName().lastIndexOf(".")); 
String fileName = complaintAttachment.getFileName().substring(0, complaintAttachment.getFileName().indexOf("."));%>
		    <% if(ext.equals(".jpg")){ %>
		    <a  href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaint/imageComplaint?fileName=<%= fileName %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <%} else if(ext.equals(".pdf")){ %>
		    <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintPdf/complaintPdf?pdfname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		     <%} else{%> 
		      <a href="<%= themeDisplay.getURLPortal() %>/o/blade/servletComplaintWord/complaintWord?wordname=<%= complaintAttachment.getFileName() %>" download target="_blank"><%=complaintAttachment.getFileName() %></a>
		    <% } } %>
		        <%-- <% for(int i=0;i<lst.size();i++){ if(i==0) out.println("<a href='/wps/PA_Vigilance/admin/downloadJsp.jsp?attachmentId="+lst.get(i).getAttachmentId()+"'>"+lst.get(i).getFileName()+"</a>"); else out.println(" | <a href='/wps/PA_Vigilance/admin/downloadJsp.jsp?attachmentId="+lst.get(i).getAttachmentId()+"'>"+lst.get(i).getFileName()+"</a> "); }%> --%>
		        </td>
		         <td><%=vc.getComplainBy().getTitle()+" "+vc.getComplainBy().getFirstName()%></td>
		        
		        <td><%= vc.getComplainBy().getMobile()%></td>

		       
		       <td><span onclick="viewcomplaintpage(<%=vc.getComplaintNo() %>)" class="lnk" > Update</span></td>
		        
		       
		        </tr>
		        <% }%>
		
		  </tbody>
		      </table>
			  </div>
			 
			 
			 
			 
</div>

 <div class="row">
	      
	  
	  <div class="col-md-12">
      <%= cpag.createIndex("callCompltUpdatePage") %>
        </div>  
</div>
</tbody>
		      </table>