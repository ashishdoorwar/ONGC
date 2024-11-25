<%@page import="com.ongc.liferay.vigilance.util.VigilanceConstant"%>
<%@page import="com.ongc.liferay.vigilance.util.VigilanceFactory"%>
<%@ include file="/init.jsp"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>

<portlet:defineObjects />
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#oldPassword").focus();
		$("a#show-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeIn(300);
		})
		$("a#close-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeOut(300);
		})
	})
</script>






<header>
	<%@ include file="/admintemplate/header.jsp"%>
</header>
<!--header end-->


<!--Start Main Contant section-->
<div class="container" id="skip-main-content">
	<div class="row">
		<!--left section start-->

		<div class="col-md-3">
			<%@ include file="/admintemplate/menu.jsp"%>
		</div>




		<!--left section end-->

		<!--right section start-->

		<div class="col-md-9">




			<s:set name="theme" value="'simple'" scope="page" />
			<%
				List country = VigilanceFactory.getContentManagementInstance()
						.getContentByType(VigilanceConstant.VIGILANCE_CONTENT_TYPE_LOCATION);
			%>


<portlet:renderURL var="cancel">
		<portlet:param name="mvcPath" value="/jsp/userHome.jsp" />
	</portlet:renderURL>

			<portlet:actionURL var="registerComplaint" name="registerComplaint">
				<portlet:param name="mvcPath" value="/jsp/userHome.jsp" />
			</portlet:actionURL>
			<div class="form-item">
				<h1 class="mb5 mt0">Register a Complaint</h1>

			</div>

			<div class="bg-grey bdr-orange search-form">
				<aui:form action="${registerComplaint}" name="fom" method="post" enctype="multipart/form-data">
					<aui:row>
						<aui:col width="50">
							<aui:input name="complaintSubject" label="Subject of Complaint" type="text">
								<aui:validator name="required" />
							</aui:input>
							<!-- <aui:select name="complaintSubject" label="Subject of Complaint ">
							<aui:option value="">Select</aui:option>
							<aui:option value="Demanding/Accepting Gratification">Demanding/Accepting Gratification</aui:option>
							<aui:option value="Obtaining Valuables">Obtaining Valuables</aui:option>
							<aui:option value="Obtaining Pecuniary Advantage">Obtaining Pecuniary Advantage</aui:option>
							<aui:option value="Possessing Disproportionate Assets">Possessing Disproportionate Assets</aui:option>
							<aui:option value="Wilful negligence in decision-making">Wilful negligence in decision-making</aui:option>
							<aui:option value="Others">Others</aui:option>
						<aui:validator name="required" />
						</aui:select> -->
						</aui:col>
						<aui:col width="50">
							<aui:input name="tenderNumber" label="Tender Number (if any)"
								type="text" />
						</aui:col>
					</aui:row>
					<span>In case the complaint does not contain vigilance angle, kindly register your grievance using the url -
<a href="https://grievance.ongc.co.in/" target="blank">https://grievance.ongc.co.in</a> or (click here)</span>
					<aui:row>
						<aui:col>
							<aui:input name="complaintDetail"
								label="Allegations/Complaint Details" type="textarea" placeholder="Enter the Allegations/Complaint Details & Max 1000 Characters are allowed">
								<aui:validator name="required" />
								<aui:validator name="maxLength">1000</aui:validator>
							</aui:input>
							<span>*Allowed special characters: ( ) : # @ " ; { } ! $ * + _ = ? , - /<br>
							<h3>Details of person/section against whom complaint is being lodged</h3>
							</span>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="25">
							<aui:select name="title" label="Title" >
								<aui:option value="-1">Select</aui:option>
								<aui:option value="Mr.">Mr.</aui:option>
								<aui:option value="Ms.">Ms.</aui:option>
							</aui:select>
						</aui:col>
						<aui:col width="25">
							<aui:input name="firstName" label="First Name" type="text"/>
						</aui:col>
						<aui:col width="25">
							<aui:input name="middleName" label="Middle Name" type="text" />
						</aui:col>
						<aui:col width="25">
							<aui:input name="lastName" label="Last Name" type="text" />
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:input name="departmetn" label="Department/Sections (if any)"
								type="text" />
						</aui:col>

						<aui:col width="50">
							<aui:input name="location" label="Location" type="text" />
							<!-- <aui:select name="location" label="Location">
							<aui:option value="-1">Select</aui:option>
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
						</aui:select> -->
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:input name="workCentre" label="Address" type="text" />
						</aui:col>
						<aui:col width="50">
							<aui:input name="designation" label="Designation(if any)"
								type="text" />
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="100">
							<aui:input name="dataFile" label="Single Attachment (If any) [supported files: .doc,.docx,.pdf,.jpg and .jpeg and file size should be less than 20MB]" type="file" />
						</aui:col>
						<script>	function ValidateFileUpload() {
		
		var fuData = document.getElementById('<portlet:namespace/>dataFile');
		var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
				if (Extension == "pdf" || Extension == "jpg" || Extension == "docx" || Extension == "doc" || Extension == "jpeg") {

						if (fuData.files && fuData.files[0]) {

							var size = fuData.files[0].size;
                
							if(size > 20971520){
								alert("Maximum file size exceeds, max file size 20MB");
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
					alert("pdf,jpg and docs files allowed only.");
					fuData.value = "";
					return;
				}
			}}
			$("#<portlet:namespace/>dataFile").change(function (e) {
					ValidateFileUpload();
			});</script>
					</aui:row>
					<!-- <aui:input name="TermsCondition"
						label="I have read, understood and agree to the Terms and Conditions"
						type="checkbox" >
						<aui:validator name="required" />
						</aui:input> -->
					<aui:button-row cssClass="text-center m-2 ">
						<aui:button cssClass="btn" value="Submit Complaint" type="submit" />
						<a class="btn text-white" href="<%=cancel%>">Cancel</a>
						
					</aui:button-row>
				</aui:form>



				<div class="clear-fix"></div>

			</div>




			<!-- Modal -->
			<!-- <div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Terms and Conditions</h4>
      </div>
      <div class="modal-body">
        	<ol style="text-align: justify;">
		<li>Definition: Complaint is expression of dissatisfaction, concern or other feedback that needs a response. Any information received from contractors, vendors, suppliers, employees and general public about corruption, malpractice or misconduct on the part of public servants would be termed as a complaint. It is one of the important sources of information for identification of vigilance related issue.</li>
		<li>Complaint can be lodged against officials belonging to the organization/or against any irregularities within the organization</li>
		<li>Anonymous/Pseudonymous com 	plaint will not be entertained. So, please give your proper name and address. If on verification is found to be pseudonymous, it may be filed.</li>
		<li>Complaint must be brief and contain be brief and contain factual details, verifiable facts and related matters. Complaints containing vague information or absurd statements are most likely to be filed.</li>
		<li>All actionable complaints are addressed by Vigilance Department and are followed up till their logical conclusion</li>
	</ol>
      </div>
    
    </div>

  </div>
</div> -->


			<!-- main content end-->
		</div>



		<div id="lightbox"></div>


	</div>

</div>
</div>




<footer>
	<%@ include file="/admintemplate/footer.jsp"%>
</footer>


