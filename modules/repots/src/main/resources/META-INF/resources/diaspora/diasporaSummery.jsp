<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/paging.js"></script>
<portlet:renderURL var="getEmployeeData"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo" />
</portlet:renderURL>
<portlet:renderURL var="diasporaForm">
	<portlet:param name="mvcPath" value="/diaspora/diasporaForm.jsp" />
</portlet:renderURL>

<portlet:renderURL var="diasporaSummery">
	<portlet:param name="mvcRenderCommandName" value="diasporaSummeryURL" />
</portlet:renderURL>
<div class="formWrapper">
<%-- <% String query = (String) request.getAttribute("queryPart");
out.println(query);
%> --%>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
	<aui:form action="<%=diasporaSummery%>" method="post"
		name="diasporaForm" class="form-horizontal">
	
			<aui:row>
				<aui:col width="50">
					<aui:input name="country" type="text" label="Country"
						maxlength="100"></aui:input>
					<div id="suggesstion-box"></div>
				</aui:col>
				<aui:col width="50">
					<aui:select label="Academics" name="academics">
						<aui:option value="">Select any</aui:option>
						<aui:option value="Anthropology">Anthropology</aui:option>
						<aui:option value="Engineering & Sciences">Engineering &
							Sciences</aui:option>
						<aui:option value="Architecture">Architecture</aui:option>
						<aui:option value="Arts">Arts</aui:option>
						<aui:option value="Doctor of Philosophy">Doctor of Philosophy</aui:option>
						<aui:option value="Education">Education</aui:option>
						<aui:option value="Engineering & Sciences">Engineering & Sciences</aui:option>
						<aui:option value="Health Sciences">Health Sciences</aui:option>
						<aui:option value="Hotel Management">Hotel Management</aui:option>
						<aui:option value="Management">Management</aui:option>
						<aui:option value="Medical">Medical</aui:option>
						<aui:option value="Others">Others</aui:option>
					</aui:select>
				</aui:col>

			</aui:row>
	<aui:button-row cssClass="text-center" style="padding-top: 29px;">
				<aui:button name="submitButton" type="submit" value="Search"
					cssClass="btn btn-primary" />
				<a href="<%=diasporaForm%>" class="postlink btn btn-primary"
					title="Register as a Diaspora">Register as a Diaspora</a>
			</aui:button-row>
	</aui:form>
</div>
<div id="paging_container3" class="fc-container">
	<div class="table-responsive mt5">
		<table id="innerTable" class="table table-bordered">

			<tbody>

				<%

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");  
					/*  Connection conn=null;
					 Statement stmt=null;
					 ResultSet res=null;
					 String queryPart = "";
					 String country ="";
					String academics="";
						try {
							 country = request.getParameter("country");
							 academics = request.getParameter("academics");
							 if(country == null ){
							 	country="";
							 	}
							 	 if ( academics ==null)
							 	{
							 		academics="";
							 	}
							if(country != null && academics !=null){
						 if(country.length()>0 && academics.length()>0 )
							 	{
							 	queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on  from ongc_diaspora a where a.status='A' and a.sub_category='POST' and upper(a.country)='"
											+ country.toUpperCase()
											+ "' and a.academics ='"
											+ academics
											+ "'  order by a.created_on desc";
							 	}	
							
							else if(country.length()>0 )
							{
							queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on from ongc_diaspora a where a.status='A' and a.sub_category='POST' and upper(a.country)='"
											+ country.toUpperCase()
											+ "' order by a.created_on desc";
							}
							else if(academics.length()>0)
							{
							queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on from ongc_diaspora a where a.status='A' and a.sub_category='POST' and a.academics ='"
											+ academics
											+ "' order by a.created_on desc";
							}
							 	else
							 	{
							 	queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on from ongc_diaspora a where a.status='A' and a.sub_category='POST' and upper(a.country)='"
											+ country.toUpperCase()
											+ "'  order by a.created_on desc";
											}
								 conn = DatasourceConnection.getConnection();
								 stmt = conn.createStatement();
								 res = stmt.executeQuery(queryPart);
								 out.println(queryPart);
								boolean empty = true;
								while (res.next()) {
									empty = false; */
					JSONArray jsonDiaspora = (JSONArray) request.getAttribute("jsonArrayDiaspora");
					if (jsonDiaspora != null) {
						for (int i = 0; i < jsonDiaspora.length(); i++) {
							JSONObject jsonDiasporaObject = (JSONObject) jsonDiaspora.get(i);
				%>
				<tr>
					<td>
						<div class="row mn">
							<div class="dias-sec col-md-12 pn pt10 pb10 mb10">
								<div class="col-md-4">
									<%-- <%
										if (jsonDiasporaObject.get("photo") != null) {
									%> --%>
									<div class="imageWrapper">
											<img src="<%= themeDisplay.getURLPortal() %>/o/blade/commonServlet/imageFile?imgID=<%=jsonDiasporaObject.get("id")%>&type=diaspora" class="thImage" />

									</div>
									<%-- <%
										}
									%> --%>
									<P class="heading-clr mt5 mb0" role="heading"
										aria-level='<%=jsonDiasporaObject.get("ward_name")%>'>
										<%=jsonDiasporaObject.get("ward_name")%></p>
									<span class="date"> <%
											Date date = sdf.parse(jsonDiasporaObject.get("created_on").toString());
									sdf.format(date);%>
									<%= sdf1.format(date) %></span>
									<div class="">
										<a
											href="javascript:popup(<%=jsonDiasporaObject.get("cpf_no")%>);"
											rel="nofollow" class="defultchi2"><%=jsonDiasporaObject.get("name")%></a>
									</div>
								</div>
								<div class="col-md-4">
									<div>
										<b>Country:</b>
										<%=jsonDiasporaObject.get("country")%>
									</div>
									<div>
										<%
											if (jsonDiasporaObject.get("state") != null) {
										%>
										<b> State: </b>
										<%=jsonDiasporaObject.get("state")%>
										<%
											}
										%>
									</div>
									<div>
										<b>Academics:</b>
										<%=jsonDiasporaObject.get("academics")%>
									</div>
									<div>
										<%
											if (jsonDiasporaObject.get("university") != null) {
										%>
										<b> University: </b>
										<%=jsonDiasporaObject.get("university")%>
										<%
											}
										%>
									</div>
								</div>

								<div class="col-md-4 text-justify">
									<div>
										<b> Mobile:</b>
										<%=jsonDiasporaObject.get("mobile_number")%>
									</div>
									<div>
										<b> Email: </b>
										<%=jsonDiasporaObject.get("email")%>
									</div>


									<%
										if (jsonDiasporaObject.get("description") != null) {
									%>

									<b>Other Information:</b>
									<%=jsonDiasporaObject.get("description")%>
								</div>
							</div>
							<%
								}
									}
							%>
						
				</tr>
				</td>

				<%
					}
				%>

				<div class="blank1"></div>

				<div class="alt_page_navigation"></div>

			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
function popup(cpfNo){
	 var url="<%=getEmployeeData%>&<portlet:namespace />cpfNo=" + cpfNo;
		Liferay.Util.openWindow({
			dialog : {
				centered : true,
				height : 600,
				modal : true,
				width : 800,
				style : "background-color: #8c000d;color: #fff !important;",
				cssClass : "ui-model",
				destroyOnHide : true,
				resizable : false,
			},
			id : "<portlet:namespace />popUpId",
			title : "Employee Details",
			uri : url
		});
		Liferay.provide(window, '<portlet:namespace />closePopup', function(
				popUpId, id) {
			Liferay.Util.Window.getById(popUpId).destroy();
			location.reload();
		}, [ 'liferay-util-window' ]);
	}
	
            $(document).ready(function() {
                $('#innerTable').paging({limit:10});
            });
</script>