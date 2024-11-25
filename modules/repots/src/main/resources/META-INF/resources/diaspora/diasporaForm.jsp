<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@ include file="/init.jsp" %>
<% 
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
if(request.getParameter("s")!=null){ %>
<div class="alert alert-success"> Post added Successfully. </div>
<% } %>
<portlet:resourceURL id="getCountriesSuggestions" var="getCountries"></portlet:resourceURL>

<portlet:actionURL var="diasporaProcess" name="saveDiaspora">
	<portlet:param name="mvcPath" value="/diaspora/diasporaSummery.jsp"/>
</portlet:actionURL>
<div class="formWrapper">
			<aui:form action="<%= diasporaProcess %>" method="post" name="diasporaForm" enctype="multipart/form-data" class="form-horizontal">
	<aui:fieldset-group markupView="lexicon">
		<!-- <aui:fieldset label="Message"> -->
			<aui:row>
				<aui:col width="50">
					<aui:input label="CPF No." name="cpfNo" type="hidden" value="<%= userOngc.getCpfNo() %>"/>
					<aui:input label="Name"  type="text" name="name"  maxlength="200" placeholder="Enter First Name" value="<%= userOngc.getEmpName() %>" readonly="true"></aui:input>
				</aui:col>
				<aui:col width="50">
				<aui:input label="Name of Ward"  type="text" name="wname"  maxlength="100" placeholder="Name of Ward">
					   <aui:validator name="required" />
					</aui:input>	
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
				<aui:input label="Email-ID"  type="email" name="email" maxlength="40" placeholder="Enter the Email Id">
					   <aui:validator name="required" />
				</aui:input>	
				</aui:col>
				<aui:col width="50">
					<aui:input label="Mobile Number"  type="number" name="mobile" maxlength="10" placeholder="Enter 10 digit mobile number">
					   <aui:validator name="required" />
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="50">
					<aui:input name="country" type="text" label="Country" maxlength="100"></aui:input>
            <div id="suggesstion-box"></div>
            </aui:col>
				<aui:col width="50">
				<aui:input label="State"  type="text" name="state" maxlength="60" placeholder="Enter the State">
					   <aui:validator name="required" />
				</aui:input>	
				</aui:col>
			</aui:row>
			<aui:row>
			<aui:col width="50">
				<aui:select label="Academics" name="academics" required="true" onchange="change(this.value)">
					 <aui:option value="">Select any</aui:option>
						<aui:option value="Anthropology">Anthropology</aui:option>
							
						<aui:option value="Engineering & Sciences">Engineering &
							Sciences</aui:option>
							<aui:option value="Architecture">Architecture</aui:option>
							<aui:option value="Arts">Arts</aui:option>
							<aui:option value="Doctor of Philosophy">Doctor of Philosophy</aui:option>
							<aui:option value="Education">Education</aui:option>
							<aui:option value="Engineering & Sciences">Engineering & Sciences</aui:option>
							<aui:option value="Health Sciences">Health Sciences</aui:option>
							<aui:option value="Hotel Management">Hotel Management</aui:option>
							<aui:option value="Management">Management</aui:option>
							<aui:option value="Medical">Medical</aui:option>
						<aui:option value="Others">Others</aui:option>
					</aui:select>
			</aui:col>
			<aui:col width="50">
				<aui:input label="University"  type="text" name="universty" maxlength="100" placeholder="Enter the University">
				</aui:input>	
				</aui:col>
			</aui:row>
			<aui:row>
			<aui:col width="50">
				<aui:input label="Other Information" type="textarea" name="description" rows="10" class="form-control"
							 maxlength="500" placeholder="Max 500 char"></aui:input>
			</aui:col>
			<aui:col width="50">
			<aui:input type="file" name="imgfile" label="My File"></aui:input>
			<img src="" id="upimg" alt=""/>
			</aui:col>
			</aui:row>
			<aui:button-row cssClass="text-center">
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary btn-sm" />
				<input type="button" value="Reset" class="btn btn-primary btn-sm" onClick="this.form.reset()">
			</aui:button-row>
		</aui:fieldset>
		
	</aui:fieldset-group>
	
</aui:form>
</div>
		<script>
		function ValidateFileUpload() {

			var fuData = document.getElementById('imgfile');
			var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {
				//alert("Please upload an image");

			} else {
				var Extension = FileUploadPath.substring(
						FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

				if (Extension == "gif" || Extension == "png"
						|| Extension == "bmp" || Extension == "jpeg"
						|| Extension == "jpg") {

					if (fuData.files && fuData.files[0]) {

						var size = fuData.files[0].size;

						if (size > 100000) {
							//alert("Maximum file size exceeds, max file size 100kb");
							document.getElementById("valert").innerHTML='Maximum file size exceeds, max file size 100kb';
		                    document.getElementById("valert").focus();
							fuData.value = "";
							return;
						} else {
							var reader = new FileReader();

							reader.onload = function(e) {
								$('#upimg').attr({
									src : e.target.result,
									width : '150'
								});
							}

							reader.readAsDataURL(fuData.files[0]);
						}
					}

				}

				else {
					//alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");
					document.getElementById("valert").innerHTML='Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ';
		            document.getElementById("valert").focus();
				}
			}
		}

		$("#imgfile").change(function(e) {
			ValidateFileUpload();
		});

		$(document).ready(function(e) {
			
			$('div').removeClass('virtual-corp-box');
			$('#name').val($("#getUserName").val());
			$('#cpfno').val($("#uniqueCpf").val());
			
			
			
			$(document).on("click", '#suggesstion-box li', function(event) { 
		var a= $(this).text();
		$('#country').val(a);
		$("#suggesstion-box").hide();
	});

	<%-- $("#<portlet:namespace/>country").keyup(function(){
		alert($(this).val());
		$.ajax({
		type: "POST",
		url: "<%= getCountries.toString() %>"+'&<portlet:namespace/>q='+$(this).val(),
		dataType: "json",
		beforeSend: function(){

		},
		success: function(data){
			alert(data);
			console.log(data)
			$("#suggesstion-box").show();
			$("#suggesstion-box").html(data);
		}
		});
	}); --%>

		});



		function formSubmit() {
			//alert('test');
			var frm1 = $("#diasporaForm");
		//	var uniqueCpf =$( "#UNFrame" ).contents().find( "#uniqueCpf" );	
		
		///	var uniqueCpf = '78619';

		//	$('#cpfno').val(uniqueCpf.val());
		
		///	$('#cpfno').val(uniqueCpf);

			var mob = /^[1-9]{1}[0-9]{9}$/;
			var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			var txtMobile = diasporaForm.mobile.value;
			var email = diasporaForm.email.value;

			if (diasporaForm.wname.value == null
					|| diasporaForm.wname.value == "") {
				diasporaForm.wname.focus();
				//alert("Please fill name of your ward");
				document.getElementById("valert").innerHTML='Please fill name of your ward';
		        document.getElementById("valert").focus();
				return false;

			}
			if( /[^a-zA-Z0-9\-\./ ]/.test( diasporaForm.wname.value ) ) {
                //alert('ward should not contain special characters');
			    document.getElementById("valert").innerHTML='Ward name should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if (diasporaForm.email.value == null || diasporaForm.email.value == "") {
				diasporaForm.email.focus();
				//alert("Please enter email id");
				document.getElementById("valert").innerHTML='Please enter email id';
		        document.getElementById("valert").focus();
				return false;

			}
			if( /[^a-zA-Z0-9\-\@./ ]/.test( diasporaForm.email.value ) ) {
			    document.getElementById("valert").innerHTML='Email id should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if (mail.test(email) == false) {
				diasporaForm.email.focus();
				//alert("Please enter valid email id");
				document.getElementById("valert").innerHTML='Please enter valid email id';
		        document.getElementById("valert").focus();
				return false;

			}
			if (diasporaForm.mobile.value == null
					|| diasporaForm.mobile.value == ""
					|| mob.test(txtMobile) == false) {
				diasporaForm.mobile.focus();
				//alert("Please enter valid mobile number");
				document.getElementById("valert").innerHTML='Please enter valid mobile number';
		        document.getElementById("valert").focus();
				return false;

			}
			if( /[^a-zA-Z0-9\-\/ ]/.test( diasporaForm.mobile.value ) ) {
			    document.getElementById("valert").innerHTML='Mobile number should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if (diasporaForm.country.value == null
					|| diasporaForm.country.value == "") {
				diasporaForm.country.focus();
				//alert("Please fill country");
				document.getElementById("valert").innerHTML='Please fill country';
		        document.getElementById("valert").focus();
				return false;

			}
			if( /[^a-zA-Z0-9\-\./ ]/.test( diasporaForm.country.value ) ) {
			    document.getElementById("valert").innerHTML='Country should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if( /[^a-zA-Z0-9\-\./ ]/.test( diasporaForm.state.value ) ) {
			    document.getElementById("valert").innerHTML='State should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if (diasporaForm.academics.value == null
					|| diasporaForm.academics.value == "") {
				diasporaForm.academics.focus();
				//alert("Please fill category");
				document.getElementById("valert").innerHTML='Please fill category';
		        document.getElementById("valert").focus();
				return false;
			}
            if( /[^a-zA-Z0-9\-\./ ]/.test( diasporaForm.universty.value ) ) {
			    document.getElementById("valert").innerHTML='Universty should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			if( /[^a-zA-Z0-9\-\./ ]/.test( diasporaForm.description.value ) ) {
			    document.getElementById("valert").innerHTML='Other Information should not contain special characters';
		        document.getElementById("valert").focus();
                return false;
            }
			var fuData = document.getElementById('imgfile');
			var FileUploadPath = fuData.value;

			if (FileUploadPath == '') {

				//alert("Please upload your pic.");
				//	return false;
				//	return true;

			} else {
				var Extension = FileUploadPath.substring(
						FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

				if (Extension == "gif" || Extension == "png"
						|| Extension == "bmp" || Extension == "jpeg"
						|| Extension == "jpg") {

					if (fuData.files && fuData.files[0]) {

						var size = fuData.files[0].size;

						if (size > 200000) {
							//alert("Maximum file size exceeds, max file size 200kb");
							document.getElementById("valert").innerHTML='Maximum file size exceeds, max file size 200kb';
		                    document.getElementById("valert").focus();
							fuData.value = "";
							return false;
							;
						}

					}
				} else {
					//alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");
					document.getElementById("valert").innerHTML='Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ';
		            document.getElementById("valert").focus();
					return false;
				}

			}

			frm1.submit();
}
	</script>