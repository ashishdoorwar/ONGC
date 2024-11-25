<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/init.jsp" %>

<script>
	function rquur() {
		var  xxx= $('#<portlet:namespace/>req').val();
		if (xxx == "Others") {
			document.getElementById("reqq").style.display='block';
		} else {
			document.getElementById("reqq").style.display='none';
		}
	}

   function oxyChange() {
		var xxx = $('#<portlet:namespace/>req').val();
		if (xxx == "Oxygeon") {
			document.getElementById("testt").style.display='block';
		} else {
			document.getElementById("testt").style.display='none';
		}
	}

	function save() {
		var xxx = $('#req').val();
		if (xxx == "Others") {
			if ($('#reqq').val() == "") {
				alert("Please fill Others details");
				return false;
			}
		}
		
		if (xxx == "Oxygeon") {
			if ($('#oxyNeeded').val() == "") {
				alert("Please enter Oxygen needed");
				return false;
			}
		}

		if ($('#name').val() == "") {
			alert("Name required");
			return false;
		}

		if ($('#desig').val() == "") {
			alert("Designation required");
			return false;
		}

		if ($('#location').val() == "") {
			alert("Location required");
			return false;
		}

		if ($('#mobileno').val() == "") {
			alert("Mobile number required");
			return false;
		}

		if ($('#address').val() == "") {
			alert("Address required");
			return false;
		}
		
		if ($('#spo2Level').val() == "") {
			alert("SPO2 Level required");
			return false;
		}

		if ($('#dependant').val() == "") {
			alert("Whom required");
			return false;
		}
		
		if ($('#req').val() == "") {
			alert("Requirement required");
			return false;
		}
		
		var frm1 = $("#requestConsellingform");
		frm1.submit();
	
	}
</script>



<%! 
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet set;
	String mobileno = "";
	String cpf = "";
	String designation = "";
	String name = "";
	String location = "";
	
	public String getRecord(String CPFNo) {
		boolean flage = false;
		String query = "  SELECT * FROM ongc_user_master WHERE CPF_NUMBER=? ";
		ResultSet rs1 = null;
		String cpfexist = "";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, CPFNo);
			rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				mobileno = rs1.getString("MOBILE_NUMBER");
				designation = rs1.getString("DESIGNATION");
				name = rs1.getString("EMP_NAME");
				location = rs1.getString("PLACE_POSTING");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt);
		}
		return cpfexist;
	}

	%>

<%
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
String cpfNo = userOngc.getCpfNo();

	String b = getRecord(cpfNo);
%>
<%
	if (request.getParameter("msg") != null) {
%>
<div class="alert alert-success">
	<h2>Your details Submitted Successfully.</h2>
</div>
<%
	}
%>

<portlet:actionURL var="savecounsellingrequest" name="saveCounsellingRequest" ></portlet:actionURL>
<div class="formWrapper">
<aui:form name="covidsevaform" action="<%=savecounsellingrequest%>" method="post">
			<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Log Your Request for Beds/Oxygen"> -->
			<aui:row>
				<aui:col width="50">
				<aui:input type="hidden" name="cpf" value="<%= cpfNo %>" ></aui:input>
				<aui:input name="name" type="text" required="true" label="Name"><aui:validator name="required" /></aui:input>
				</aui:col>
					<aui:col width="50">
					<aui:input name="designation" type="text" required="true" value="" label="Designation"><aui:validator name="required" /></aui:input>
					</aui:col>
					</aui:row>
					<aui:row>
				<aui:col width="50">
				<aui:input name="location" type="text" required="true" label="Location"><aui:validator name="required" /></aui:input>
				</aui:col>
					<aui:col width="50">
					<aui:input name="mobile" type="text" required="true" value="" label="Mobile number"><aui:validator name="required" /></aui:input>
					</aui:col>
					</aui:row>
					
					<aui:row>
					<aui:col width="50">
							<aui:input type="text" label="Address" name="address">
								<aui:validator name="required" />
							</aui:input>
						</aui:col>
						<aui:col width="50">
							<aui:input type="text" label="SPo2 level in room air" name="spo2Level" ><aui:validator name="required" /></aui:input>
						</aui:col>
					</aui:row>
					
					<aui:row>
					<aui:col width="50">
							<aui:select id="req" label="Requirement" name="req" onchange="rquur();oxyChange();" required="true">
								<aui:option value="Beds">Beds</aui:option>
								<aui:option value="VBeds">Ventilator Beds</aui:option>
								<aui:option value="ICU">ICU</aui:option>
								<aui:option value="Oxygeon">Oxygen</aui:option>
								<aui:option value="Others">Others</aui:option>
							</aui:select>
						</aui:col>
						<aui:col width="50">
							<aui:select label="Whom" name="dependant" required="true">
								<aui:option value="Self">Self</aui:option>
								<aui:option value="ICU">Dependant</aui:option>
							</aui:select>
						</aui:col>
					</aui:row>
					
					<aui:row>
					<aui:col width="100">
								<div class="form-group" align="left" id="reqq" style="display: none;" >
							<aui:input type="textarea" label="" name="reqqothers" ></aui:input>
	</aui:col>
						<aui:col width="100">
					<div class="form-group" align="left" id="testt" style="display: none;" >
							<aui:input type="text" label="Oxygen&nbsp;(Litres&nbsp;per&nbsp;day)" name="oxyNeeded" ></aui:input>
					</div>
					</aui:col>
					</aui:row>
			<aui:button-row >
				<div class="col-md-12 text-center">
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-sm btn-primary" />
							<a class="btn btn-sm btn-primary" onclick="javascript:history.back();" target="" title="">Back</a>
						</div>
						</aui:button-row>
				
			</aui:fieldset>
			</aui:fieldset-group>
		</aui:form>
	</div>