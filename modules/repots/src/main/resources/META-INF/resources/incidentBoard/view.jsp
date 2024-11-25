<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.model.IncidentBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%
User userOngc = (User) request.getAttribute("userOngc");
String uCpfId = userOngc.getCpfNo();
List<IncidentBean> incidentList = (List<IncidentBean>) request.getAttribute("alist");
%>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<portlet:renderURL var="saveIncident" >
	<portlet:param name="mvcPath" value="/incidentBoard/newincident.jsp"/>
</portlet:renderURL>
<script type="text/javascript">

$( document ).ready(function() {
	  var uCpfId = $("#uniqueCpf").val;
		   if(uCpfId=='76121' || uCpfId=='78619' || uCpfId=='58809' || uCpfId=='94314' || uCpfId=='52208' || uCpfId=='124917' || uCpfId=='48038' || uCpfId=='57346' || uCpfId=='96643' || uCpfId=='106508' || uCpfId=='55072'
		   || uCpfId=='124989' || uCpfId=='61301' || uCpfId=='81784'|| uCpfId=='64431' || uCpfId=='62826' || uCpfId=='77902' || uCpfId=='34154' || uCpfId=='61308' || uCpfId=='35724' || uCpfId=='95964' || uCpfId=='51041' || uCpfId=='61137' || uCpfId=='34428' || uCpfId=='62775' || uCpfId=='95962' || uCpfId=='49021' || uCpfId=='91888' || uCpfId=='53087' || uCpfId=='81532' || uCpfId=='46556' || uCpfId=='78253' || uCpfId=='62727' || uCpfId=='78570' || uCpfId=='48347' || uCpfId=='61868' || uCpfId=='26491'
		   || uCpfId=='70472' || uCpfId=='52624' || uCpfId=='61317'|| uCpfId=='49272' || uCpfId=='53421' || uCpfId=='48159' || uCpfId=='57371' || uCpfId=='66067' || uCpfId=='51435' || uCpfId=='42787' || uCpfId=='82197' || uCpfId=='46588' || uCpfId=='94343' || uCpfId=='34141' || uCpfId=='48311' || uCpfId=='53945'
		   || uCpfId=='95825' || uCpfId=='122393' || uCpfId=='123569'|| uCpfId=='devopsadmin')
      {
$('#link').attr('style','display: inline-block');
      }
		});
</script>

<p dir="ltr" class="text-right">
	<a href="<%=saveIncident%>" id="link" title="" target="" id="link"
		style="display: inline-block;" class="btn btn-primary">Upload
		Information / Incident</a>
</p>
<input type="hidden" name="uniqueCpf"
	value="<%=userOngc.getCpfNo()%>" />
<hr size="4" color="gray" />
<liferay-ui:error key="error" message="Illegal argument found" />
<div class="table-responsive">
	<table id="incident"
		class="dataTables table table-bordered table-striped ">
		<thead>
			<tr>
				<th>Post ID</th>
				<th>Subject</th>
				<th>Category</th>
				<th>Description</th>
				<th>Posted Date</th>
				<th>Posted By</th>
				<th>File Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${alist}" var="employee">
				<tr>
					<td><c:out value="${employee.index}" /></td>
					<td><c:out value="${employee.subject}" /></td>
					<td><c:out value="${employee.category}" /></td>
					<td><c:out value="${employee.description}" /></td>
					<td><c:out value="${employee.date}" /></td>
					<td><a onclick="popup('${employee.posteddby}')"><c:out
								value="${employee.postedbyName}" /></a></td>
					<td><c:if test="${not empty employee.fname}">
							<a target="_blank"
								href="<%= themeDisplay.getURLPortal() %>/o/blade/incidentServlet/pdfIncident?pdfname=${employee.fname}"
								class="defultchi2 blue"> <i class="fa fa-file-pdf-o"
								style="width: 25px; height: 25px; vertical-align: middle; margin: 0px 3px 0 0;"
								aria-hidden="true"></i> <c:out value="${employee.fname}" /></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
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

$(document).ready(function() {
	 var table = $('#incident').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false,pageLength: 100
	    });
	}); 
</script>