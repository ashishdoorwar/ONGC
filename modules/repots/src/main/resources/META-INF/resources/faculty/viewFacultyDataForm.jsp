
<%@page import="com.ongc.liferay.model.FacultyBean"%>
<%@page import="com.ongc.liferay.dao.FacultyDao"%>
<%@ include file="/init.jsp"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	 String id = request.getParameter("facid");
	 FacultyDao _fdao= new FacultyDao();
    FacultyBean fbean= _fdao.getFacDataById(Integer.valueOf(id));
	 
	%>
<div class="table-responsive">
	<table class="table table-striped">
		<tbody>
			<tr>
				<td>CPF Number</td>
				<td><%= fbean.getCpfno() %></td>
				<td>Name of Employee</td>
				<td><%= fbean.getName() %></td>
			</tr>
			<tr>
				<td>Designation</td>
				<td><%= fbean.getDesignation() %></td>
				<td>Date of Superannuation</td>
				<td><%= fbean.getDos() %></td>
			</tr>
			<tr>
				<td>Mobile No&nbsp;</td>
				<td><%= fbean.getMob() %></td>
				<td>Email Id&nbsp;</td>
				<td><%= fbean.getEmail() %></td>
			</tr>
			<tr>
				<td>Fax No</td>
				<td><%= fbean.getFax() %>
				<td>Address</td>
				<td><%= fbean.getAddress() %></td>
			</tr>
			<tr>
				<td>City</td>
				<td><%= fbean.getCity() %></td>
				<td>Area of Specialisation</td>
				<td><%= fbean.getSpecialisation() %></td>
			</tr>
			<tr>
				<td>Academic Qualification</td>
				<td><%= fbean.getAcademics() %></td>
				<td>Program Code</td>
				<td><%= fbean.getCode() %></td>
			</tr>
			<!-- 
						<tr>
					<td >
							Program Title</td>
						<td >
					    <%= fbean.getTitle() %>
						</td>

						<td >
							Program Code</td>
						<td  >
							<%= fbean.getCode() %>
					</tr>
					 -->
			<tr>
				<td>Description</td>
				<td colspan="3"><%= fbean.getDescription() %></td>
			</tr>
		</tbody>
	</table>
</div>
