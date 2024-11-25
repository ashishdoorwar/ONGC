<%@page
	import="com.ongc.liferay.reports.service.Impl.PassionServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.PassionService"%>
<%@page import="com.ongc.liferay.reports.model.SubPassion"%>
<%@page import="com.ongc.liferay.reports.model.Passion"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@ include file="/init.jsp"%>
<script type="text/javascript">
    $("#resetButton").click(function () {
    	$("#<portlet:namespace/>cpfNo").val('');
    	$("#<portlet:namespace/>employeeName").val('');
    	$("#<portlet:namespace/>employeeLevel").val('Select');
    	$("#<portlet:namespace/>currentPlace").val('Select');
    	$("#<portlet:namespace/>mobile").val('');
    	$("#<portlet:namespace/>passionCategory").val('-1');
    	$("#<portlet:namespace/>passion").val('-1');
    });
</script>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<%
	PassionService passionService = new PassionServiceImpl();

	List<Passion> passionName = passionService.getPassionName();
%>
<portlet:defineObjects />
<%
	List<String> empLevel = Stream.of("C", "D", "E9", "E8", "E7", "E6", "E5", "E4", "E3", "E2", "E1", "E0",
			"GT", "S4", "S3", "S2", "S1", "A5", "A4", "A3", "A2", "A1", "W7", "W6", "W5", "W4", "W3", "W2",
			"W1", "TC", "TR", "Retd").collect(Collectors.toList());

	List<String> currentPlace = Stream
			.of("Agartala", "Ahmedabad", "Ankleshwar", "Bokaro", "Cambay", "Chennai", "Dehradun", "Delhi",
					"Goa", "Hazira", "Jodhpur", "Jorhat", "Kakinada", "Karaikal", "Kolkata", "Mehsana",
					"Mumbai", "Nazira", "Panvel", "Rajahmundry", "Silchar", "Sivasagar", "Uran", "Vadodara")
			.collect(Collectors.toList());
	//JSONArray currentPlace= JSONFactoryUtil.createJSONArray("[{\"Agartala\":\"Agartala\"},{\"Ahmedabad\":\"Ahmedabad\"},{\"Ankleshwar\":\"Ankleshwar\"},{\"Bokaro\":\"Bokaro\"},{\"Cambay\":\"Cambay\"},{\"Chennai\":\"Chennai\"},{\"Dehradun\":\"Dehradun\"},{\"Delhi\":\"Delhi\"},{\"Goa\":\"Goa\"},{\"Hazira\":\"Hazira\"},{\"Jodhpur\":\"Jodhpur\"},{\"Jorhat\":\"Jorhat\"},{\"Kakinada\":\"Kakinada\"},{\"Karaikal\":\"Karaikal\"},{\"Kolkata\":\"Kolkata\"},{\"Mehsana\":\"Mehsana\"},{\"Mumbai\":\"Mumbai\"},{\"Nazira\":\"Nazira\"},{\"Panvel\":\"Panvel\"},{\"Rajahmundry\":\"Rajahmundry\"},{\"Silchar\":\"Silchar\"},{\"Sivasagar\":\"Sivasagar\"},{\"Uran\":\"Uran\"},{\"Vadodara\":\"Vadodara\"}]");
	//out.println(currentPlace.get(0));
%>
<portlet:resourceURL id="subPassionUrl" var="subPassionResourceURL"></portlet:resourceURL>
<div class="container formWrapper">
	<div class="row">
		<div class="col-lg-12">
			<portlet:renderURL var="searchEmployee">
				<portlet:param name="mvcRenderCommandName"
					value="searchEmployeeInfo" />
			</portlet:renderURL>
			<!-- <div class="alert alert-primary" role="alert" style="display:none;" id="alert">Please select atleast one search parameter!</div> -->
			<aui:form  method="post" action="<%=searchEmployee%>" name="myForm" > <%--  --%>
				<aui:fieldset-group markupView="lexicon">
					<!-- <aui:fieldset label="Search Employee Details"> -->
						<aui:row>
							<aui:col width="50">
								<aui:input label="CPF No." name="cpfNo" type="text" onkeyup="checkParameter()">
								<aui:validator name="alphanum"/>
								</aui:input>
							</aui:col>
							<aui:col width="50">
								<aui:input label="Employee Name" name="employeeName" type="text" onkeyup="checkParameter()" />
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="50">
								<aui:select label="Employee Level" name="employeeLevel" onchange="checkParameter()">
									<aui:option value="Select">Select</aui:option>
									<%
										for (int i = 0; i < empLevel.size(); i++) {
									%>
									<aui:option value="<%=empLevel.get(i)%>"><%=empLevel.get(i)%></aui:option>
									<%
										}
									%>
								</aui:select>
							</aui:col>
							<aui:col width="50">
								<aui:select label="Current Place" name="currentPlace" onchange="checkParameter()">
									<aui:option value="Select">Select</aui:option>
								    <aui:option value="AGAR">Agartala</aui:option>
								    <aui:option value="ABAD">Ahmedabad</aui:option>
								    <aui:option value="ANKL">Ankleshwar</aui:option>
								    <aui:option value="BKRO">Bokaro</aui:option>
								    <aui:option value="BRDA">Baroda</aui:option>
								    <aui:option value="CAMB">Cambay</aui:option>
								    <aui:option value="CHEN">Chennai</aui:option>
								    <aui:option value="CWS SIVASAGAR">CWS SIVASAGAR</aui:option>
								    <aui:option value="CBM EAST BOKARO">CBM EAST BOKARO</aui:option>
								    <aui:option value="DEHR">Dehradun</aui:option>
								    <aui:option value="DELI">Delhi</aui:option>
								    <aui:option value="Goa">Goa</aui:option>
								    <aui:option value="HAZI">Hazira</aui:option>
								    <aui:option value="JODH">Jodhpur</aui:option>
								    <aui:option value="Jorhat">Jorhat</aui:option>
								    <aui:option value="Kakinada">Kakinada</aui:option>
								    <aui:option value="Karaikal">Karaikal</aui:option>
								    <aui:option value="KOLG">Kolkata</aui:option>
								    <aui:option value="MEHS">Mehsana</aui:option>
								    <aui:option value="MUMB">Mumbai</aui:option>
								    <aui:option value="Nazira">Nazira</aui:option>
								    <aui:option value="Panvel">Panvel</aui:option>
								    <aui:option value="RAJY">Rajahmundry</aui:option>
								    <aui:option value="SILC">Silchar</aui:option>
								    <aui:option value="Sivasagar">Sivasagar</aui:option>
								    <aui:option value="Uran">Uran</aui:option>
								    <aui:option value="Vadodara">Vadodara</aui:option>
									<%-- <%
										for (int i = 0; i < currentPlace.size(); i++) {
									%>
									<aui:option value="<%=currentPlace.get(i)%>"><%=currentPlace.get(i)%></aui:option>
									<%
										}
									%> --%>
								</aui:select>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="50">
								<aui:input label="Mobile No." name="mobile" type="text" onkeyup="checkParameter()">
								 <aui:validator name="custom"  errorMessage="You can enter a maximum of 15 numeric and special characters(+)">
								  <aui:validator name="minLength">6</aui:validator>
								  <aui:validator name="maxLength">15</aui:validator>
								  function (val, fieldNode, ruleValue) {
								  var returnValue = true;
								  var iChars = "~`!@#$%^&-*()_=[]\\\';,./{}|\":<>?qwertyuiopasdfghjklzxcvbnm";
								                for (var i = 0; i < val.length; i++) {
								                    if (iChars.indexOf(val.charAt(i)) != -1) {                
								                     returnValue = false;
								                    }
								                }
								                return returnValue;
								        }
								    </aui:validator>
								</aui:input>
							</aui:col>
							<aui:col width="25">
								<aui:select label="Passion Category" name="passionCategory"
									onChange="javascript:selectSubPassion(this.value);checkParameter()">
									<!-- onChange="javascript:selectSubPassion(this);" -->
									<aui:option value="-1">Select</aui:option>
									<%
										for (Passion passion : passionName) {
									%>
									<aui:option value="<%=passion.getPassionId()%>"><%=passion.getPassionName()%></aui:option>
									<%
										}
									%>
								</aui:select>
							</aui:col>
							<aui:col width="25">
								<aui:select label="Passion" name="passion" >
									<aui:option value="-1">Select</aui:option>

								</aui:select>
							</aui:col>
						</aui:row>
						<div id="searchButton">
						<aui:button-row cssClass="text-center">
							<aui:button name="submitButton" type="submit" value="Submit"
								class="btn btn-primary btn-sm" onclick="checkParameter()"/>
								<button name="resetButton" id="resetButton" type="button"
								onclick="reset();checkParameter();" class="btn btn-primary">Reset</button>
						</aui:button-row>
						</div>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</div>
	</div>
</div>
<script>
	function selectSubPassion(passionId) {
		console.log(passionId);
		AUI().use(
				'aui-io-request',
				function(A) {
					A.io.request('${subPassionResourceURL}', {
						method : 'get',
						dataType : "json",
						cache : true,
						data : {
							<portlet:namespace/>passionId : passionId,
						},
						on : {
							success : function() {
								var responseData = new Array();
								responseData = this.get('responseData');
								for (var i = 0; i < responseData.length; i++) {
									var id = responseData[i]['id'];
									var name = responseData[i]['name'];
									$('#<portlet:namespace />passion').append(
											new Option(name, id));
								}
							},
							error : function() {
								alert("Error occured on server.");
							}
						}
					});
				});
	}
	
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
		 checkParameter(); closeDialog();
	 });
	function SearchCheck() {
	    var flag = false;
	   	var  cpfNo=document.getElementById('<portlet:namespace/>cpfNo').value;
	   	var  employeeName=document.getElementById('<portlet:namespace/>employeeName').value;
	   	var  employeeLevel=document.getElementById('<portlet:namespace/>employeeLevel').value;
	   	var  currentPlace=document.getElementById('<portlet:namespace/>currentPlace').value;
	   	var  mobile=document.getElementById('<portlet:namespace/>mobile').value;
	   	var  passionCategory=document.getElementById('<portlet:namespace/>passionCategory').value;
	        if(cpfNo != "" || employeeName != "" || mobile != "" || employeeLevel != "Select" || currentPlace != "Select" || passionCategory != "-1" ) {

		    	document.getElementById("<portlet:namespace/>submitButton").disabled = false;
	        	flag = true;
	           return flag;
	        }
	   
	    return flag;
	}
	
</script>
<script>
function closeDialog(){
		var successMessageElement = document.getElementsByClassName("alert-success");
		console.log(successMessageElement);
		if(successMessageElement){
			setTimeout(function(){closePopup();},500);
		}
		
	}
function closePopup() {
	Liferay.Util.getOpener().<portlet:namespace/>closePopup(
			'<portlet:namespace />popUpId', '');
}
	</script>