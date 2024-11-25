<%@page import="java.util.Date"%>
<%@page import="com.liferay.calendar.model.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.model.OrderCircular"%>
<%@ include file="/init.jsp"%>
<style>
.orderAndCircular-background-color {
	background-color: #770606;
	color: white;
}
</style>

<portlet:actionURL var="addOrderAndCircular"
	name="add_order_circular">
<portlet:param name="mvcPath" value="/admin/view.jsp" />
</portlet:actionURL>
<%
OrderCircular orderCircular = (OrderCircular)request.getAttribute("orderCircular");
if(orderCircular==null){
	orderCircular=new OrderCircular();
}
%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<%@ include file="/admin/adminMenu.jsp"%>
		</div>
		<div class="col-lg-12">
 <liferay-ui:error key="error" message="Illegal argument found" /> 
			<div class="row">
				<div class="col-md-8">
					<h2 class="h2 heading-clr mt0">Order and circular</h2>
					<aui:form action="${addOrderAndCircular}" name="fm" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-12">

								
							</div>
						</div>

						<div class="form-group ">


							<aui:input name="id" type="hidden" value="<%=orderCircular.getId() %>"/>
							<aui:input label="Subject" name="subject" type="textarea" value="<%=orderCircular.getSubject() %>">
							 <aui:validator name="required" />
							</aui:input>
						</div>


						<div class="form-group">
							<aui:input label="Issued By" name="issuedBy" type="text" value="<%=orderCircular.getIssuedBy() %>" >
							 <aui:validator name="required" />
							</aui:input>
						</div>

						<div class="form-group">
							<aui:select name="category" label="Search By Category" value="<%=orderCircular.getCategory() %>">
											<aui:option value="Select">Select</aui:option>
											<aui:option value="Academy">Academy</aui:option>
											<aui:option value="Annual Award">Annual Award</aui:option>
											<aui:option value="BDP">BDP</aui:option>
											<aui:option value="Business Games">Business Games</aui:option>
											<aui:option value="CC">CC</aui:option>
											<aui:option value="CP">CP</aui:option>
											<aui:option value="CPP Charges">CPP Charges</aui:option>
											<aui:option value="CRC">CRC</aui:option>
											<aui:option value="CSR">CSR</aui:option>
											<aui:option value="CTD">CTD</aui:option>
											<aui:option value="Civil">Civil</aui:option>
											<aui:option value="Corporate Finance">Corporate Finance</aui:option>
											<aui:option value="Corporate Policy">Corporate Policy</aui:option>
											<aui:option value="Display of complete">Display of complete</aui:option>
											<aui:option value="E-0 TO E-4">E-0 TO E-4</aui:option>
											<aui:option value="E4 to E5 Promotions">E4 to E5 Promotions</aui:option>
											<aui:option value="ED">ED</aui:option>
											<aui:option value="ER">ER</aui:option>
											<aui:option value="ER-CP">ER-CP</aui:option>
											<aui:option value="Employee Relations">Employee Relations</aui:option>
											<aui:option value="Employee Welfare">Employee Welfare</aui:option>
											<aui:option value="Establishment matter">Establishment matter</aui:option>
											<aui:option value="F&amp;A">F&amp;A</aui:option>
											<aui:option value="Finance">Finance</aui:option>
											<aui:option value="General">General</aui:option>
											<aui:option value="General Admin">General Admin</aui:option>
											<aui:option value="HR">HR</aui:option>
											<aui:option value="HR-CP">HR-CP</aui:option>
											<aui:option value="HR-Establishment">HR-Establishment</aui:option>
											<aui:option value="HR/ER">HR/ER</aui:option>
											<aui:option value="HRD">HRD</aui:option>
											<aui:option value="HSE">HSE</aui:option>
											<aui:option value="Hospitality">Hospitality</aui:option>
											<aui:option value="Industrial Relation">Industrial Relation</aui:option>
											<aui:option value="Legal">Legal</aui:option>
											<aui:option value="Logistics">Logistics</aui:option>
											<aui:option value="MISC">MISC</aui:option>
											<aui:option value="MM">MM</aui:option>
											<aui:option value="Medical">Medical</aui:option>
											<aui:option value="Misc">Misc</aui:option>
											<aui:option value="PAR">PAR</aui:option>
											<aui:option value="PMC">PMC</aui:option>
											<aui:option value="PRBS">PRBS</aui:option>
											<aui:option value="R&amp;P">R&amp;P</aui:option>
											<aui:option value="RTI">RTI</aui:option>
											<aui:option value="Rajbhasha">Rajbhasha</aui:option>
											<aui:option value="Recruitment">Recruitment</aui:option>
											<aui:option value="Retirement">Retirement</aui:option>
											<aui:option value="Seniority List">Seniority List</aui:option>
											<aui:option value="Services">Services</aui:option>
											<aui:option value="Sports">Sports</aui:option>
											<aui:option value="TDS From Salaries">TDS From Salaries</aui:option>
											<aui:option value="TDS from Salaries">TDS from Salaries</aui:option>
											<aui:option value="Trading">Trading</aui:option>
											<aui:option value="Training">Training</aui:option>
											<aui:option value="Transfer">Transfer</aui:option>
											<aui:option value="e-Learning">e-Learning</aui:option>
										</aui:select>
						</div>

						<div class="form-group">
							<aui:input label="Order Number" name="orderNumber" type="text" value="<%=orderCircular.getOrderNumber() %>">
							 <aui:validator name="required" />
							</aui:input>
						</div>

						<div class="form-group">
						 	<aui:input label="Order Date" name="orderDate" type="date" value="<%=orderCircular.getOrderDate() %>">
							 <aui:validator name="required" />
							</aui:input> 
							
						</div>

						<div class="form-group">
							<aui:input label="Upload File" name="fileUpload" type="file" />
							<script>	function ValidateFileUpload() {
		
		var fuData = document.getElementById('<portlet:namespace/>fileUpload');
		var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
				if (Extension == "pdf") {

						if (fuData.files && fuData.files[0]) {

							var size = fuData.files[0].size;
                
							if(size > 100000000){
								alert("Maximum file size exceeds, max file size 100mb");
                                fuData.value = "";
								return;
							}else{
								var reader = new FileReader();
								reader.onload = function(e) {
									$('#<portlet:namespace/>upimg').attr({src:e.target.result, width:'150'});
								}
								reader.readAsDataURL(fuData.files[0]);
							}
						}
				} 
			else 
			{
					alert("Pdf files allowed only.");
					fuData.value = "";
					return;
				}
			}}
			$("#<portlet:namespace/>fileUpload").change(function (e) {
					ValidateFileUpload();
			});</script>
						</div>

						<div class="row">
							<aui:button-row cssClass="text-center ">
								<aui:button cssClass="btn orderAndCircular-background-color "
									type="submit" />
							</aui:button-row>
						</div>
				</div>

				</aui:form>
				<div class="col-md-4 admin-menu">
					<%@ include file="/admin/rightNav.jsp"%>
				</div>
			</div>
		</div>
	</div>
</div>