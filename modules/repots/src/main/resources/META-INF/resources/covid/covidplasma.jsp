<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@ include file="/init.jsp"%>
<script>
	window.onload = function() {
		$(function() {
			$("#dr").datepicker();
		});

	};
</script>
<%!private Connection conn = null;
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
	}%>


<%
	UserDao userDao = new UserDao();
	User userOngc = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
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
 <portlet:actionURL var="savecovidplasma" name="savecovidPlasma" ></portlet:actionURL>
 <div class="formWrapper">
<aui:form action="<%=savecovidplasma%>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Plasma Donation"> -->
			<aui:row>
				<aui:col width="100">
					<aui:input type="hidden" name="cpfNo" value="<%=cpfNo%>">
					</aui:input>
				</aui:col>
				<aui:col width="50">

					<aui:input type="text" label="Name" id="firtdate" name="name">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input type="text" label="Desgination" name="desig">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input type="text" label="Location" name="location">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input type="text" label="Mobile Number" name="mobileno">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="50">
					<aui:input type="text" label="Blood Group" name="bg">
						<aui:validator name="required" />
					</aui:input>

				</aui:col>
				<aui:col width="50">
					<aui:input type="date" label="Date of Recovery from Covid"
						name="dr" style="margin-left: 16px;">
						<aui:validator name="required" />
					</aui:input>
				</aui:col>
				<aui:col width="100" align="center">
					<label class="btn btn-primary">I hereby volunteer to donate
						Plasma</label>
				</aui:col>
				<aui:button-row >
					<div class="col-md-12 text-center">
					<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary btn-sm" />
					<a class="btn btn-primary btn-sm" onclick="javascript:history.back();" target="" title="">Back</a>
					</div>
				</aui:button-row>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>
</div>
