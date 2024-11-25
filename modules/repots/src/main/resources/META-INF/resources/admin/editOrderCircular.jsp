<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ongc.liferay.model.OrderCircular"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<style>
.orderAndCircular-background-color {
	background-color: #770606;
	color: white;
}
</style>
<%
	List<OrderCircular> selectTopOrderAndCircular = null;
	selectTopOrderAndCircular = (List<OrderCircular>) request.getAttribute("selectTopOrderAndCircular");
	if (selectTopOrderAndCircular == null)
		selectTopOrderAndCircular = new ArrayList();
%>
<div class="row">
	<div class="col-lg-12">
		<%@ include file="/admin/adminMenu.jsp"%>
	</div>
	<div class="col-md-8 pr10 mb20">
		<h2 class="h2 heading-clr mt0">List of Order and circular</h2>

		<div id="story">
			<div class="row">
				<div class="col-md-12">
					<div class="innersearch_order">
						<div class="welcome"></div>

						<form id="EditOrderAndCircular" name="EditOrderAndCircular"
							action="${addOrderAndCircular}" method="post"
							class="form-horizontal pl20 pr20 pb20 pt20">
							<div class="bg-grey fs-title search-form">
								<div class="row input-daterange" id="datepicker">
									<aui:col width="50">
										<aui:input label="Search By Keyword" name="searchSubject"
											type="text" />
									</aui:col>
									<aui:col width="50">
										<aui:select name="category" label="Search By Category">
											<aui:option value="Option 1">Option 1</aui:option>
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
											<aui:option value="Display of complete">Display of
											complete</aui:option>
											<aui:option value="E-0 TO E-4">E-0 TO E-4</aui:option>
											<aui:option value="E4 to E5 Promotions">E4 to E5
											Promotions</aui:option>
											<aui:option value="ED">ED</aui:option>
											<aui:option value="ER">ER</aui:option>
											<aui:option value="ER-CP">ER-CP</aui:option>
											<aui:option value="Employee Relations">Employee Relations</aui:option>
											<aui:option value="Employee Welfare">Employee Welfare</aui:option>
											<aui:option value="Establishment matter">Establishment
											matter</aui:option>
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
											<aui:option value="Industrial Relation">Industrial
											Relation</aui:option>
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
									</aui:col>
								</div>
								<aui:button-row cssClass="text-center ">
									<aui:button cssClass="btn orderAndCircular-background-color "
										type="submit" value="Search" />
								</aui:button-row>

							</div>
						</form>

						<div class="clearfix"></div>
						<table cellspacing="0" id="item"
							class="table table-bordered table-striped" cellpadding="0">
							<thead>
								<tr>
									<th></th>
									<th></th>
									<th>Subject</th>
									<th>Issued By</th>
									<th>Category</th>
									<th>Order Number</th>
									<th>Order Date</th>
									<th>Posted On</th>
								</tr>
							</thead>
							<tbody>
								<%
								SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
									for (OrderCircular orderCircular : selectTopOrderAndCircular) {
								%>
								<tr>
									<portlet:actionURL var="deleteOrderAndCircular"
										name="delete_order_circular">
										<portlet:param name="mvcPath" value="/admin/view.jsp" />
										<portlet:param name="id" value="<%=String.valueOf(orderCircular.getId())%>" />
									</portlet:actionURL>
									<portlet:renderURL var="editOrderCircularData">
										<portlet:param name="mvcRenderCommandName"
											value="editOrderCircularData" />
										<portlet:param name="id" value="<%=String.valueOf(orderCircular.getId())%>" />
									</portlet:renderURL>
									<td><a id="editTag" href="${editOrderCircularData}">Edit</a></td>
									<td><a id="deleteTag" href="${deleteOrderAndCircular}">Delete</a></td>
									<td><a target="_blank" href=""><%=orderCircular.getSubject()%></a></td>
									<td><%=orderCircular.getIssuedBy()%></td>
									<td><%=orderCircular.getCategory()%></td>
									<td><%=orderCircular.getOrderNumber()%></td>
									<td><%=orderCircular.getOrderDate()%></td>
									<td><%=sdf.format(orderCircular.getCreateDate())%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>


					</div>
				</div>
			</div>

		</div>


	</div>
	<div class="col-md-4 pl10 admin-menu mb20">
		<%@ include file="/admin/rightNav.jsp"%>
	</div>


</div>