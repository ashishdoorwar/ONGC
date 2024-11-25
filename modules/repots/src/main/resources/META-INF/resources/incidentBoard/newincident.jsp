<%@ include file="/init.jsp" %>
<% 
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
String username = " ";
String cpf = " ";
if (userOngc != null) {
	username = userOngc.getEmpName();
	cpf = userOngc.getCpfNo();
}
%>
<portlet:actionURL var="incidenPostForm" name="saveIncident" ></portlet:actionURL>
<aui:form id="incident-post" method="post"	action="<%= incidenPostForm %>" class="form-horizontal" enctype="multipart/form-data">
			<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Upload Information/Incident"> -->
			<aui:row>
				<aui:col width="100">
					<aui:input type="hidden" name="cpfno" value="<%=cpf%>"></aui:input>
				</aui:col>
					<aui:col width="100">
					<aui:input name="subject" type="text" required="true" placeholder="Max 100 char" maxlength="100" value="" label="Subject"></aui:input>
					</aui:col>
					</aui:row>
					<aui:row>
					<aui:col width="100">
					<aui:select name="category" onchange="change(this.value)" required="true">
						<aui:option value="">Select Category</aui:option>
						<aui:option value="Information Update">Information Update</aui:option>
						<aui:option value="Incident Report">Incident Report</aui:option>
						<aui:option value="Reply">Reply</aui:option>
					</aui:select>
					</aui:col>
				<aui:col width="100">
						<aui:input required="true" type="textarea" label="Description" name="description" placeholder="Enter Description" maxlength="500"></aui:input>
	</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input type="file" name="imgfile" class="form-control" label="File" />

					<script>	function ValidateFileUpload() {
		
		var fuData = document.getElementById('<portlet:namespace/>imgfile');
		var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
				if (Extension == "pdf") {

						if (fuData.files && fuData.files[0]) {

							var size = fuData.files[0].size;
                
							if(size > 100000){
								alert("Maximum file size exceeds, max file size 100kb");
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
			$("#<portlet:namespace/>imgfile").change(function (e) {
					ValidateFileUpload();
			});</script>
					</aui:col>
	</aui:row>
			<div id="postbtn_container" align="center">
				<input type="submit" value="Submit" class="btn" style="background-color: #8c000d;color: #fff !important;"/>
				<aui:button name="resetButton" type="button"  onClick="this.form.reset()"  value="Reset" style="background-color: #8c000d;color: #fff !important;"/>
			</div>
			</aui:fieldset>
			</aui:fieldset-group>
		</aui:form>