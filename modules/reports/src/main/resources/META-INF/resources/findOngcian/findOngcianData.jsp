
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />
<%@ include file="viewFindOngcian.jsp"%>
<%
List<User> ongicians = (List<User>) request.getAttribute("findOngician");
%>
<portlet:renderURL var="getEmployeeData"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo" />
</portlet:renderURL>
<portlet:renderURL var="saveThankNote"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="saveThanks" />
</portlet:renderURL>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table id="findOngcianTable"
					class="table table-striped table-bordered" style="width: 100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>CPF Number</th>
							<th>Name</th>
							<th>Posting</th>
							<th>Mobile</th>
							<th>Email-ID (Official)</th>
							<th>Passion</th>
							<th>Thank You Note</th>
						</tr>
					</thead>

					<tbody>
						<% 
						if(ongicians!=null){
						int i=0;for(User ongician:ongicians){ %>
						<tr>
							<td>
								<%if(ongician.getProfilePicPath()!= null){ %><img src=""
								class="img-responsive"> <%} %><i class="fa fa-user"></i>
							</td>
							<td><%= ongician.getCpfNo() %></td>
							<td><a onclick="popup('<%= ongician.getCpfNo() %>')"
								id="cpfNo"><%= ongician.getEmpName() %></a></td>
							<td><%= ongician.getCurrentPlace() %></td>
							<td><% if(ongician.getMobileNo()!= null) {%><%= ongician.getMobileNo() %><%} %></td>
							<td>
								<% if(ongician.getEmailIdOfficial()!= null) {%><%= ongician.getEmailIdOfficial() %>
								<%} %>
							</td>
							<td><a href="<%= themeDisplay.getURLPortal() %>/group/reports_en/passion-my-profile?cpf_number=<%= ongician.getCpfNo() %>"><%= ongician.getSubPassion() %></a></td>
							<td><a onclick="thankspopup('<%= ongician.getCpfNo() %>')"
								id="cpfNo_thanksNote">Click here</a></td>
						</tr>
						<%} 
						}
						%>
					</tbody>
				</table>
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
 
 function thankspopup(cpfNo){
	 var url="<%=saveThankNote%>&<portlet:namespace />cpfNo="+cpfNo;
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
	    title: "Thank You Note",
	    uri: url
	}); 
	  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
         Liferay.Util.Window.getById(popUpId).destroy();
         //location.reload();
         setTimeout(function(){closePopup();},500)
     },
     ['liferay-util-window']
     ); 
}
 
 $(document).ready(function() {
	 
	 var table = $('#findOngcianTable').DataTable( {
		 lengthChange: false,bFilter: false, bInfo: false
	    });
	}); 
 
</script>
<%-- <liferay-ui:search-container total="<%=ongicians.size()%>" var="searchContainer" delta="20" deltaConfigurable="true" 
  emptyResultsMessage="Oops. There Are No Users To Display, Please add Users">
  
 <liferay-ui:search-container-results results="<%=ListUtil.subList(ongicians, searchContainer.getStart(),searchContainer.getEnd())%>" />
  <liferay-ui:search-container-row className="User" modelVar="ongician" keyProperty="cpfNo" >
   <liferay-ui:search-container-column-text name="CPF No" cssClass="width-11" value="${ongician.cpfNo}"/>
   <liferay-ui:search-container-column-text name="Name" value="${ongician.empName}"/>
   <liferay-ui:search-container-column-text name="Mobile" value="${ongician.mobileNo}"/>
   <liferay-ui:search-container-column-text name="Email" value="${ongician.emailIdOfficial}"/>
  </liferay-ui:search-container-row>
 <liferay-ui:search-iterator />
</liferay-ui:search-container>  --%>