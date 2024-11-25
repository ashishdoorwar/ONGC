<%@page import="com.ongc.liferay.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="/init.jsp" %>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<portlet:renderURL var="sendBirthdayNote" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="sendBirthdayMsg"/>
</portlet:renderURL>
<portlet:renderURL var="getBirthday" > 
 	<portlet:param name="mvcRenderCommandName" value="getBirthdayInfo"/>
 </portlet:renderURL>
<aui:form action="<%= getBirthday %>" method="post" name="birthday"> <!-- onsubmit="return validateDate();" -->
<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Birthdays"> -->
			<aui:row>
				<aui:col width="33">
<aui:select name="day" id="day" cssClass="form-control" required="true">
	<aui:option value="">Select Date</aui:option>
	<aui:option value="1">1</aui:option>
	<aui:option value="2">2</aui:option>
	<aui:option value="3">3</aui:option>
	<aui:option value="4">4</aui:option>
	<aui:option value="5">5</aui:option>
	<aui:option value="6">6</aui:option>
	<aui:option value="7">7</aui:option>
	<aui:option value="8">8</aui:option>
	<aui:option value="9">9</aui:option>
	<aui:option value="10">10</aui:option>
	<aui:option value="11">11</aui:option>
	<aui:option value="12">12</aui:option>
	<aui:option value="13">13</aui:option>
	<aui:option value="14">14</aui:option>
	<aui:option value="15">15</aui:option>
	<aui:option value="16">16</aui:option>
	<aui:option value="17">17</aui:option>
	<aui:option value="18">18</aui:option>
	<aui:option value="19">19</aui:option>
	<aui:option value="20">20</aui:option>
	<aui:option value="21">21</aui:option>
	<aui:option value="22">22</aui:option>
	<aui:option value="23">23</aui:option>
	<aui:option value="24">24</aui:option>
	<aui:option value="25">25</aui:option>
	<aui:option value="26">26</aui:option>
	<aui:option value="27">27</aui:option>
	<aui:option value="28">28</aui:option>
	<aui:option value="29">29</aui:option>
	<aui:option value="30">30</aui:option>
	<aui:option value="31">31</aui:option>
</aui:select>
</aui:col>
				<aui:col width="33">
 <aui:select label='Month' name="month" cssClass="form-control" required="true">
    <aui:option value=''>Select Month</aui:option>
    <aui:option value='1'>January</aui:option>
    <aui:option value='2'>February</aui:option>
    <aui:option value='3'>March</aui:option>
    <aui:option value='4'>April</aui:option>
    <aui:option value='5'>May</aui:option>
    <aui:option value='6'>June</aui:option>
    <aui:option value='7'>July</aui:option>
    <aui:option value='8'>August</aui:option>
    <aui:option value='9'>September</aui:option>
    <aui:option value='10'>October</aui:option>
    <aui:option value='11'>November</aui:option>
    <aui:option value='12'>December</aui:option>
    </aui:select>
</aui:col>
				<aui:col width="33">
  <aui:select name='locations' label="Locations" class="form-control">
    <aui:option value=''>Select Location</aui:option>
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
</aui:col>
</aui:row>
<aui:row>
	<div class="col-md-12 text-center">
   <input type="submit" class="btn btn-primary"> 
	<aui:button name="resetButton" type="button"  onClick="this.form.reset()"  value="Reset" cssClass="btn btn-primary"/>
</div>
</aui:row>
</aui:fieldset>
</aui:fieldset-group>
    </aui:form>
	<div class="container mt-4">
<div class="row">
	<%
		List<User> list=(List<User>) request.getAttribute("birthday");
		if(list!=null ){ 
			if(list.size() == 0) 
			out.println("No Record Found");
 		for(int i=0;i<list.size();i++){
		 User userOngc=list.get(i);
		 String Designation=userOngc.getDesignation();
		 String Email=userOngc.getEmailIdOfficial();
		 String WorkPlace=userOngc.getWorkPlace();
		 if(Designation==null)
		 	Designation="-";
		 if(Email==null)
		 	Email="-";
		 if(WorkPlace==null)
		 	WorkPlace="-";
 	%>

	<div class="col-lg-4" style="border: 1px solid #555;">
	
	<a href="javascript:popup(<%= userOngc.getCpfNo() %>);" rel="nofollow" style="color:#0000ee; font-weight: bold; ">  <%=userOngc.getEmpName() %></a>
		<!--	<span><b style="color:black;"><%=userOngc.getEmpName()%></b>, <%=Designation%></span>
			 <span><%=WorkPlace %> </span>
			<span> <%
 					if (userOngc.getEmailIdOfficial() != null) {
 						out.println(userOngc.getEmailIdOfficial()+",&nbsp;");
 					}
 					%>
					 <%
 					if (userOngc.getMobileNo() != null) {
 						out.print(userOngc.getMobileNo());
 					}
 %></span>  -->
 	<div align="right" style="display: inline-block;float:right;"><a href="javascript:viewBdayWish(<%=userOngc.getCpfNo() %>,'<%=userOngc.getEmpName()%>');">
 	<img src="<%=request.getContextPath()%>/images/wishes.png" style="width: 22px;padding-left: 0 !important;"alt=""/></a></div>
	</div>
	<%} }
%>
</div>
</div>
<script>
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

function viewBdayWish(cpfNo,name){
	 var url="<%=sendBirthdayNote %>&<portlet:namespace />cpfNo="+cpfNo+"&<portlet:namespace />name="+name;
	Liferay.Util.openWindow({
	    dialog: {
	        centered: true,
	        height: 400,
	        modal: true,
	        width: 600,
	        style:"background-color: #8c000d;color: #fff !important;",
	        cssClass: "ui-model",
	        destroyOnHide: true,
        resizable: false,
	    },
	    id: "<portlet:namespace />popUpId",
	    title: "Birthday Wishes",
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