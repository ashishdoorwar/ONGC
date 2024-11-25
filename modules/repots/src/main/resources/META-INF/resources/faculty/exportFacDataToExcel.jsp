 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.ongc.liferay.model.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.dao.FacultyDao"%>
<%
 response.setContentType("application/vnd.ms-excel");
 response.setHeader("Content-Disposition", "inline; filename=facultydata.xls");
FacultyDao _fdao= new FacultyDao();
String cpf=null;
List<FacultyBean> fclist;

fclist=  _fdao.getFacData(0);
if(fclist.size() >0  ){
 HttpSession res=request.getSession();
 res.setAttribute("fclist", fclist);
}
response.setContentType("application/ms-excel");
response.setContentLength(fclist.size()); 
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=Leave Details.xls");
 %>
<h3>Faculty Details :</h3>
<hr size="4" color="gray"/>
 
 <div id="link" >
 <table width="100%" border="1"> 

 <tr style="background: #982828; color: #fff; padding: 3px;">
             <th>S No.</th>
             <th>CPF No</th>
             <th>Name</th>
             <th>Designation</th>
             <th>Date of Superannuation</th>
             <th>Mobile No</th>
             <th>Email Id</th>
             <th>Address</th>
             <th>City</th>
             <th>Specialization</th>
             <th>Academic Qualification</th>             
			 <th>Program code</th>
			 <th>Description</th>
			 <th>Posted On</th>
        </tr>
		</table>
<table width="100%" border="1" id="tableData"> 
    <c:forEach items="${fclist}" var="faculty" varStatus="fl">
        	<tr>
			<td>
			<c:out value="${fl.index+1}" />
			</td>
			<td >
			<c:out value="${faculty.cpfno}" />
			</td>
			<td >
			<c:out value="${faculty.name}" />
			</td>
			<td >
			<c:out value="${faculty.designation}" />
			</td>
			<td >
			<c:out value="${faculty.dos}" />
			</td>
			<td >
			<c:out value="${faculty.mob}" />
			</td>
			<td>
			<c:out value="${faculty.email}" />
			</td>
			<td>
			<c:out value="${faculty.address}" />
			</td>
			<td>
			<c:out value="${faculty.city}" />
			</td>
			<td>
			<c:out value="${faculty.specialisation}" />
			</td>
			<td>
			<c:out value="${faculty.academics}" />
			</td>
			<td>
			<c:out value="${faculty.code}" />
			</td>
			<td>
			<c:out value="${faculty.description}" />
			</td>
			<td>
			<c:out value="${faculty.posted_date}" />
			</td>
			</tr>
    </c:forEach>
	
</table>
</div>