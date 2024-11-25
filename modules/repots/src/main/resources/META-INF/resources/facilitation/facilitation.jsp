<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@ include file="/init.jsp"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/paging.js"></script>
<script>
$(document).ready(function(e) {
var cat=document.getElementById("<portlet:namespace/>category").value;
change(cat);
});
function change(cat){
if(cat=="Matrimonial")
{
document.getElementById("location1").style.display='none';
$('#mat').removeAttr("style");
document.getElementById("accom").style.display='none';
}
else if(cat=="Accommodation")
{
$('#location1').removeAttr("style");
$('#accom').removeAttr("style");
document.getElementById("mat").style.display='none';
}
else
{
$('#location1').removeAttr("style");
document.getElementById("mat").style.display='none';
document.getElementById("accom").style.display='none';
}
}
</script>
<portlet:renderURL var="facilitationPostOffer">
	<portlet:param name="mvcPath"
		value="/facilitation/facilitationPostForm.jsp" />
</portlet:renderURL>
<portlet:renderURL var="facilitationOfferSummery">
	<portlet:param name="mvcPath"
		value="/facilitation/facilitationSummery.jsp" />
</portlet:renderURL>
<portlet:renderURL var="facilitationMyPost">
	<portlet:param name="mvcPath" value="/facilitation/myFacPost.jsp" />
</portlet:renderURL>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<div class="formWrapper">
	<aui:form METHOD="POST" name="facilitationForm">
		<div class="row">
			<div class="col-md-3">
				<aui:select id="category" name="category"
					onchange="change(this.value);checkParameter()">
					<aui:option value="0">Select a Category</aui:option>
					<aui:option value="Car Pooling">Car Pooling</aui:option>
					<aui:option value="House Hold Goods">House Hold Goods</aui:option>
					<aui:option value="Accommodation">Accommodation</aui:option>
					<aui:option value="Vehicle">Vehicle</aui:option>
					<aui:option value="Matrimonial">Matrimonial</aui:option>
				</aui:select>
			</div>
			<div class="col-md-3" id="mat" style="display: none;">
				<aui:select label="Matrimony" name="matrimony"
					class="sbHolder2 form-control" onchange="checkParameter();">
					<aui:option value="0">Select Sub Category</aui:option>
					<aui:option value="Bride_L">Looking for Bride</aui:option>
					<aui:option value="Groom_L">Looking for Groom</aui:option>
				</aui:select>
			</div>
			<div class="col-md-3" id="accom" style="display: none;">
				<aui:select label="Sub Category" id="accommodation"
					name="accommodation" class="sbHolder2 form-control"
					onchange="checkParameter();">
					<aui:option value="0">Select Sub Category</aui:option>
					<aui:option value="Buyer">Buyer</aui:option>
					<aui:option value="Seller">Seller</aui:option>
					<aui:option value="RENT_G">Offer for Rent</aui:option>
					<aui:option value="RENT_T">Take on Rent</aui:option>
				</aui:select>
			</div>
			<div class="col-md-3" id="location1">
				<div>
					<aui:select label="Location" name="location"
						class="sbHolder2 form-control" onchange="checkParameter();">
						<aui:option value="0">Select Location</aui:option>
						<aui:option value="Agartala">Agartala</aui:option>
						<aui:option value="Ahmedabad">Ahmedabad</aui:option>
						<aui:option value="Ankleshwar">Ankleshwar</aui:option>
						<aui:option value="Bokaro">Bokaro</aui:option>
						<aui:option value="Cambay">Cambay</aui:option>
						<aui:option value="Chennai">Chennai</aui:option>
						<aui:option value="Dehradun">Dehradun</aui:option>
						<aui:option value="Delhi">Delhi</aui:option>
						<aui:option value="Goa">Goa</aui:option>
						<aui:option value="Hazira">Hazira</aui:option>
						<aui:option value="Jodhpur">Jodhpur</aui:option>
						<aui:option value="Jorhat">Jorhat</aui:option>
						<aui:option value="Kakinada">Kakinada</aui:option>
						<aui:option value="Karaikal">Karaikal</aui:option>
						<aui:option value="Kolkata">Kolkata</aui:option>
						<aui:option value="Mehsana">Mehsana</aui:option>
						<aui:option value="Mumbai">Mumbai</aui:option>
						<aui:option value="Nazira">Nazira</aui:option>
						<aui:option value="Panvel">Panvel</aui:option>
						<aui:option value="Rajahmundry">Rajahmundry</aui:option>
						<aui:option value="Silchar">Silchar</aui:option>
						<aui:option value="Sivasagar">Sivasagar</aui:option>
						<aui:option value="Uran">Uran</aui:option>
						<aui:option value="Vadodara">Vadodara</aui:option>
					</aui:select>
				</div>
			</div>
			<!-- <div class="col-md-3" style="padding-top: 26px;">
			<input type="submit" value="Search" id="Search"
				class="fc-search-btn btn btn-primary" onclick="checkParameter()">
		</div> -->
			<aui:button-row cssClass="text-center" style="padding-top: 29px;">
				<aui:button name="submitButton" type="submit" value="Submit"
					onclick="checkParameter()" cssClass="btn btn-sm btn-primary" />
			</aui:button-row>
		</div>
	</aui:form>
</div>
<div class="row">
	<div class="col-md-12 text-right mb-4">
		<a href="<%= facilitationMyPost %>" class="postlink btn btn-primary">My
			Posts</a> <a href="<%= facilitationOfferSummery %>"
			class="postlink btn btn-primary">Offer Summary</a> <a
			href="<%=facilitationPostOffer %>" class="postlink btn btn-primary">Post
			Offer</a>
	</div>
</div>
<div class="row facilitationPost">
	<div class="col-md-12">
		<table id="pagingTable" class=" mb10" width="100%">
			<thead>
			</thead>
			<tbody>
				<%
     Statement stmt =null;
    ResultSet res=null;
    Connection conn=null;
        try{
         String location ="0";
        String category = request.getParameter("category");
        String subcategory= "0";
        if("Matrimonial".equalsIgnoreCase(category)) {
        location="0";
        subcategory=request.getParameter("matrimony");
        } else if("Accommodation".equalsIgnoreCase(category)) {
        subcategory=request.getParameter("accommodation");
        location= request.getParameter("location");
        } else {
        location= request.getParameter("location");
        } if(location!=null && category!=null){
        	String queryPart="";
        	if(!location.equals("0") && !category.equals("0"))
        	{
        		queryPart="select id, cpf_no, name, mobile, email, location, category, title, discription, TO_CHAR(created_on, 'DD-MON-YYYY'), status, photos,user_type from ongc_facilitation where location = '"+ location + "' and category = '" + category + "' "; 
        		 if (!"0".equalsIgnoreCase(subcategory))
            		 {
            		  queryPart=queryPart+ " and sub_category='"+ subcategory+"' ";
            		 }
            		  queryPart=queryPart+ " and status='A' ORDER BY Created_on DESC";
        	} else {
            		queryPart="select id, cpf_no, name, mobile, email, location, category, title, discription, TO_CHAR(created_on, 'DD-MON-YYYY'), status, photos,user_type from ongc_facilitation where (location = '" + location + "' or category = '" + category + "' )"; 
            		 if (!"0".equalsIgnoreCase(subcategory))
            		 {
            		  queryPart=queryPart+ " and sub_category='"+ subcategory+"' ";
            		 }
            		  queryPart=queryPart+ " and status='A' ORDER BY Created_on DESC";
        	}
			 conn=DatasourceConnection.getConnection();
			 stmt=conn.createStatement();
			 res = stmt.executeQuery(queryPart) ;
			boolean empty = true; 
            while(res.next()) {
            	empty = false;
     		%>
				<tr>
					<td>
						<div class="my-post">
							<div class="row mn">
								<div class="col-md-10">
									<h3 class="heading-clr mt0 ft18"><%=res.getString(8)%></h3>
									<div class="blank1"></div>
									<p><%=res.getString(9)%></p>
									<!-- <%
       String cpf="78619";     
        if(res.getString(2).equalsIgnoreCase(cpf))
        {
        %>			
			<form  id="facilitation-archive" method="post" action="pg-home-Facilitation-Archive">
			<input type="hidden" value=<%= res.getString(1) %> name="posId"/>
			<input type="hidden" value=<%= cpf %> name="cpf"/>
			  <span> <input type="submit" value="Archive" class="btn btn-primary">
			  </form>
		<%} %>
                			-->
								</div>
								<div class="col-md-2">
									<div class="ft14 clr-brown text-right">
										<strong> <%=res.getString(10)%></strong>
					<%-- 					<%
											if (res.getBlob(12) != null) {
										%> --%>
										<div class="imageWrapper">
											<img src="<%= themeDisplay.getURLPortal() %>/o/blade/commonServlet/imageFile?imgID=<%= res.getString(1)%>&type=facilitation"
												width="50px" height="50px" class="thImage" />
											<%-- <div class="bigImage">
												<img
													src="http://uat-reports.ongc.co.in/o/blade/commonServlet/imageFile?imgID=<%= res.getString(1)%>&type=facilitation">
											</div> --%>
										</div>
										<%-- <%
											}
										%> --%>
									</div>
								</div>
							</div>
							<div class="row mn">
								<div class="col-md-10">
									<ul class="list-inline ul-r-bdr ml0 mb0">
										<li><%=res.getString(7)%></li>
										<li title="<%=res.getString(6)%>"><%=res.getString(6)%></li>
										<li class="bdr-af-none">
											<%
												String stat = res.getString(13);
															if ("O".equalsIgnoreCase(stat)) {
											%> <a title="View Profile" data-toggle="modal"
											data-target="#myModal" href="#"
											onclick="javascript:popup(<%=res.getString(2)%>);"
											rel="nofollow"><%=res.getString(3)%></a> <%
 	} else {
 %> <span><%=res.getString(3)%></span> <% } %>
										</li>
										<div class="blank1"></div>
									</ul>
									<div class="blank1"></div>
								</div>
								<div class="col-md-2">
									<ul class="list-inline ul-r-bdr pull-right mb0">
										<li class="bdr-af-none ft18 bdr-af-none"><%=res.getString(4)%>
										</li>
									</ul>
								</div>
							</div>
							<script>
    document.facilitationForm.location.value="<%=location%>";
    document.facilitationForm.category.value="<%=category%>";
	$(document).ready(function() {
	if($('#mat').is(':visible')){
		document.facilitationForm.matrimony.value="<%=subcategory%>";
	}
	if($('#accom').is(':visible')){
		document.facilitationForm.accommodation.value="<%=subcategory%>";
	}
	});
    </script>
						</div> <%
 	}
 %>
					</td>
				</tr>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Exception in facilitation jsp :: " + e.getMessage());
					} finally {
						if (res != null)
							res.close();
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();

					}
				%>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
function checkParameter(){
	 if(SearchCheck()) {
	 }
	    else {
	    	document.getElementById("<portlet:namespace/>submitButton").disabled = true;
	    }
}
$(document).ready(function() {
	 checkParameter();
	 $('#pagingTable').paging({limit:10});
});
function SearchCheck() {
  var flag = false;
 	var  accommodation=$('#<portlet:namespace/>accommodation').val();
 	var  location=$('#<portlet:namespace/>location').val();
 	var  matrimony=$('#<portlet:namespace/>matrimony').val();
 	var  category=$('#<portlet:namespace/>category').val();
      if(accommodation != "0" || location != "0" || matrimony != "0" || category != "0") {
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
</script>