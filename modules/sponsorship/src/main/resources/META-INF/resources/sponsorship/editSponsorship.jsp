<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ongc.liferay.sponsorship.model.SponStatus"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="com.ongc.liferay.sponsorship.model.SponsorshipBean"%>
<%@page
	import="com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl"%>
<%@page import="com.ongc.liferay.sponsorship.service.SponsorshipService"%>
<%@page import="com.ongc.liferay.sponsorship.model.FilterBean"%>
<%@page import="com.ongc.liferay.sponsorship.model.SubjectBean"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<script>
function checkStatus(){
	var status= $('#<portlet:namespace/>status').val();
	if(status==5){
		$("#statDiv").css("display", "block");
		$("#rnum").css("display", "none");
	}else if(status==6){
		$("#rnum").css("display", "block");
		$("#statDiv").css("display", "none");
	}else{
		$("#statDiv").css("display", "none");
		$("#rnum").css("display", "none");
	}
}
</script>
<portlet:actionURL var="addSponsorship" name="add_sponsorship">
	<portlet:param name="mvcPath" value="/sponsorship/listSponsorship.jsp" />
</portlet:actionURL>
<portlet:renderURL var="searchSponsorship">
	<portlet:param name="mvcRenderCommandName" value="search_sponsorship" />
</portlet:renderURL>

<%
	SponsorshipBean sponsorshipBean = (SponsorshipBean) request.getAttribute("sponsorshipBean");
	if (sponsorshipBean == null) {
		sponsorshipBean = new SponsorshipBean();
	}

	SponsorshipService sponsorshipService = new SponsorshipServiceImpl();
	List<SubjectBean> subjectBeans = sponsorshipService.getSubjectList();
	List<SponStatus> sponStatusList = sponsorshipService.getProposalStatus();

	List<String> sponsTypes = Stream.of("Environmental", "Scientific/Technical", "Sports", "Cultural",
			"Mega events", "College events", "Others").collect(Collectors.toList());
	List<String> approvalAuths = Stream.of("Executive Committee", "Director(Finance)", "Director(Offshore)",
			"Director(HR)", "Director(Exploration)", "Director(T&FS)", "Director(Onshore)",
			"Chief CC/Key executives", "Incharge CC").collect(Collectors.toList());
	List<String> internalRecomendations = Stream.of("CMD", "Director(Finance)", "Director(Offshore)",
			"Director(HR)", "Director(Exploration)", "Director(Onshore)", "Director(T&FS)",
			"Chief CC/Key executives").collect(Collectors.toList());
%>
<div class="container">
<%@ include file="/sponsorship/header.jsp"%>
<h1>Sponsorship / Advertisement Form</h1>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<aui:form action="${addSponsorship}" name="fm" method="post" enctype="multipart/form-data">
	<aui:fieldset-group markupView="lexicon">
		<aui:row>
			<aui:col width="50">
			<aui:input type="hidden" name="id" value="<%=sponsorshipBean.getSponid() %>"/>
				<aui:select label="Subject/Aim" name="subject" id="subject"
					value="<%=sponsorshipBean.getSubject()%>">
					<aui:option value=''>Select Subject</aui:option>
					<%
						for (SubjectBean subjectBean : subjectBeans) {
					%>
					<aui:option value="<%=subjectBean.getSubId()%>"><%=subjectBean.getSubName()%></aui:option>
					<%
						}
					%>
					<aui:validator name="required" />
				</aui:select>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Occasion/Purpose" name="purpose" value="<%=sponsorshipBean.getPurpose() %>"
					type="text">
					<aui:validator name="required" />

				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:select label="Sponsorship Theme" name="sponsType"
					id="sponsType" value="<%=sponsorshipBean.getSponsType()%>">
					<aui:option value=''>Select Sponsorship Theme</aui:option>
					<%
						for (String sponsType : sponsTypes) {
					%>
					<aui:option value="<%=sponsType%>"><%=sponsType%></aui:option>
					<%
						}
					%>
					<aui:validator name="required" />
				</aui:select>
			</aui:col>

		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Beneficiary Name " name="publicationname" value="<%=sponsorshipBean.getPublicationname() %>"
					type="text">
					<aui:validator name="required" />
<aui:validator name="maxLength">100</aui:validator>
				</aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Dealing Officer" name="dealingOfficer" value="<%=sponsorshipBean.getDealingOfficer() %>"
					type="text">
					<aui:validator name="required" />
<aui:validator name="maxLength">50</aui:validator>
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Email ID" name="emailid" value="<%=sponsorshipBean.getEmailid() %>" type="email">
					<aui:validator name="required" />
<aui:validator name="maxLength">50</aui:validator>
				</aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Phone Number" name="mobileNo" value="<%=sponsorshipBean.getMobileNo() %>" type="text">
					<aui:validator name="required" />
<aui:validator name="maxLength">10</aui:validator>
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Received On"  id="receivedDate" name="receivedDate"  type="date"
				value="<%=sponsorshipBean.getReceivedDate() %>">
					<aui:validator name="required" />
				</aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Event Date" name="eventDate" value="<%=sponsorshipBean.getEventDate() %>" type="date">
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="External Recommendation" name="recommendedby"
					value="<%=sponsorshipBean.getRecommendedby() %>" type="text">
					<aui:validator name="maxLength">200</aui:validator>
				</aui:input>
			</aui:col>
			<aui:col width="50">

				<aui:select label="Internal Recommendation"
					name="internalRecomendation" id="internalRecomendation"
					value="<%=sponsorshipBean.getInternalRecomendation()%>">
					<aui:option value=''>Select Internal Recommendation</aui:option>
					<%
						for (String internalRecomendation : internalRecomendations) {
					%>
					<aui:option value="<%=internalRecomendation%>"><%=internalRecomendation%></aui:option>
					<%
						}
					%>
				</aui:select>

			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Final Value" name="proposalval" value="<%=sponsorshipBean.getProposalval() %>"
					type="text">
				</aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Deliverable" name="deliverable" value="<%=sponsorshipBean.getDeliverable() %>"
					type="text">
<aui:validator name="maxLength">1000</aui:validator>
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">


				<aui:select label="Approving Authority" name="approvalAuth"
					id="approvalAuth" value="<%=sponsorshipBean.getApprovalAuth()%>">
					<aui:option value=''>Select Approving Authority</aui:option>
					<%
						for (String approvalAuth : approvalAuths) {
					%>
					<aui:option value="<%=approvalAuth%>"><%=approvalAuth%></aui:option>
					<%
						}
					%>
				</aui:select>
			</aui:col>

		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Disha file number" name="filenumber" value="<%=sponsorshipBean.getFilenumber() %>"
					type="text">
<aui:validator name="maxLength">500</aui:validator>
				</aui:input>
			</aui:col>

		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="FR Number" name="frNumber" value="<%=sponsorshipBean.getFrNumber() %>" type="text">
				</aui:input>
			</aui:col>

			<aui:col width="50">
				<aui:input label="Tracking Number" name="trackingNumber" value="<%=sponsorshipBean.getTrackingNumber() %>"
					type="text">
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Proposal Attachment (upto 10 mb)"
					name="proposalDoc" value="" type="file">
				</aui:input>
					<script>	function ValidateproposalDoc() {
		
		var fuData = document.getElementById('<portlet:namespace/>proposalDoc');
		var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
				if (Extension == "pdf" || Extension == "jpg" || Extension == "docx" || Extension == "doc" || Extension == "jpeg") {

						if (fuData.files && fuData.files[0]) {

							var size = fuData.files[0].size;
                
							if(size > 10485760){
								alert("Maximum file size exceeds, max file size 10MB");
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
					alert("pdf,jpg and docx files allowed only.");
					fuData.value = "";
					return;
				}
			}}
			$("#<portlet:namespace/>proposalDoc").change(function (e) {
				ValidateproposalDoc();
			});</script>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Release Order Attachment (upto 4 mb)"
					name="releaseOrderDoc" value="" type="file">

				</aui:input>
				<script>	function ValidateFileUpload() {
		
		var fuData = document.getElementById('<portlet:namespace/>releaseOrderDoc');
		var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
				if (Extension == "pdf" || Extension == "jpg" || Extension == "docx" || Extension == "doc" || Extension == "jpeg") {

						if (fuData.files && fuData.files[0]) {

							var size = fuData.files[0].size;
                
							if(size > 4194304){
								alert("Maximum file size exceeds, max file size 4MB");
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
					alert("pdf,jpg and docx files allowed only.");
					fuData.value = "";
					return;
				}
			}}
			$("#<portlet:namespace/>releaseOrderDoc").change(function (e) {
					ValidateFileUpload();
			});</script>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">

				<aui:select label="Status" name="status" id="status"
					value="<%=sponsorshipBean.getStatus()%>" onchange="checkStatus();">
					<aui:option value=''>Select Status</aui:option>
					<%
						for (SponStatus sponStatus : sponStatusList) {
					%>
					<aui:option value="<%=sponStatus.getStatid()%>"><%=sponStatus.getStatus()%></aui:option>
					<%
						}
					%>
					<aui:validator name="required" />
				</aui:select>
			</aui:col>
		</aui:row>
<aui:row>
			<div class="col-md-6" id="statDiv" style="display:none;">
			<aui:input label="Other Status" name="otherStatus" type="text" value="<%=sponsorshipBean.getOtherStatus() %>" maxLength="200" placeholder="Max 200 char"/>
			</div>
			<div class="col-md-6" id="rnum" style="display:none;">
			<aui:input label="Release Order No" name="ronum" type="text" value="<%=sponsorshipBean.getRonum() %>" maxLength="200" placeholder="Max 200 char"/>
			<aui:input label="Release Order Date" name="rodt" type="date" value="<%=sponsorshipBean.getRodt() %>" />
			</div>
		</aui:row>
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center mt-2">
		<% if(sponsorshipBean.getSponid()!=0){
			%>
		<aui:button cssClass="btn btn-primary" type="submit" value="Update" />
		<% }else{%>
		<aui:button cssClass="btn btn-primary" type="submit" />
		<aui:button type="cancel" cssClass="btn-primary" value="Reset" onclick="this.form.reset()" />
		<%} %>
		<a class="btn btn-primary" href="<%=searchSponsorship%>">Back</a>
		
	</aui:button-row>

</aui:form>
</div>
<footer class="login-footer bg-dark-blue light-blue-clr">
	<div class="row">
		<div class="col-xs-12">Copyright Oil and Natural Gas
			Corporation Limited</div>
	</div>
</footer>

<script>
$(function(){
$('.datepicker').datepicker({
format: 'mm-dd-yyyy'
});
});
$(document).ready(function() {
	checkStatus();
});
</script>