<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@ include file="/init.jsp"%>

<style>
.requiredValFldMsg {
	display: none;
}

.mand {
	color: red
}
</style>
<script>
	function change(subcat) {

		if (subcat == "Matrimonial") {

			document.getElementById("accom").style.display = 'none';
			document.getElementById("locationfield").style.display = 'none';
			document.getElementById("matrimony").style.display = 'block';
		}
		if (subcat == "Accommodation") {
			document.getElementById("matrimony").style.display = 'none';
			document.getElementById("accom").style.display = 'block';
			document.getElementById("locationfield").style.display = 'block';
		}
		if (subcat == "Car Pooling" || subcat == "House Hold Goods"
				|| subcat == "Vehicle" || subcat == "Vehicle" || subcat == "") {
			document.getElementById("accom").style.display = 'none';
			document.getElementById("matrimony").style.display = 'none';
			document.getElementById("locationfield").style.display = 'block';
		}
	}

	var _chkclk = true;
	function save() {
		console.log(_chkclk);
		console.log(' call save ');
		var frm = $("#facilitation-post");
		if (validateForm(frm)) {

			var yesNo = confirm('Are you sure you want to submit?');
			if (yesNo == true) {
				console.log(' _chkclk: ' + _chkclk);
				if (_chkclk) {
					_chkclk = false;
					$("#facilitation-post").submit();
				} else {
					return false;
				}

			}
		}

	}
</script>

</head>
<body>
	<%
		UserDao userDao = new UserDao();
		User userOngc = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
		String username = " ";
		String cpf = " ";
		if (userOngc != null) {
			username = userOngc.getEmpName();
			cpf = userOngc.getCpfNo();
		}
	%>
	<%--********************************************************************************************************************** --%>
	<portlet:actionURL var="addFacilitation" name="add_facilitation"></portlet:actionURL>

	<div class="formWrapper">
		<div class="mainform">
			<aui:form id="facilitation-post" method="post"
				action="${addFacilitation}" class="form-horizontal"
				enctype="multipart/form-data">
				<aui:fieldset-group markupView="lexicon">
					<!-- <aui:fieldset label="Message"> -->
					<aui:row>
						<aui:col width="50">
							<aui:input type="hidden" name="cpfno" value="<%=cpf%>"></aui:input>
							<aui:input type="hidden" value="<%=username%>" name="usertype"
								id="usertype"></aui:input>
							<aui:input name="name" type="text" maxlength="64"
								placeholder="Enter Your Name" value="<%=username%>"
								readonly="true"></aui:input>
						</aui:col>
						<aui:col width="50">
							<aui:input name="email" type="email" required="true"
								placeholder="Enter Email address" maxlength="64" value=""
								label="Email"></aui:input>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:input name="title" label="Title for post" type="text"
								required="true" placeholder="Enter Title" maxlength="64"
								value=""></aui:input>
						</aui:col>
						<aui:col width="50">
							<aui:input name="mobile" type="text" required="true"
								maxlength="10" placeholder="Enter  Mobile Number"></aui:input>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:select name="category" onchange="change(this.value)"
								required="true">
								<aui:option value="">select a Category</aui:option>
								<aui:option value="Car Pooling">Car Pooling</aui:option>
								<aui:option value="House Hold Goods">House Hold Goods</aui:option>
								<aui:option value="Accommodation">Accommodation</aui:option>
								<aui:option value="Vehicle">Vehicle</aui:option>
								<aui:option value="Matrimonial">Matrimonial</aui:option>
							</aui:select>
						</aui:col>
							<div class="col-md-6" id="matrimony" style="display: none;">
								<div class="col-md-4 radio  mb10">
									<aui:input type="radio" name="sub_matrx" value="Bride_L"
										label="Looking for Bride" />
									<aui:input type="radio" name="sub_matrx" value="Groom_L"
										label="Looking for Groom" />
								</div>
							</div>
							<div class="col-md-6"  id="accom" style="display: none;">
								<aui:input type="radio" name="sub_accom" value="Buyer"
									label="Buyer" />
								<aui:input type="radio" name="sub_accom" value="Seller"
									label="Seller" />
								<aui:input type="radio" name="sub_accom" value="RENT_G"
									label="Offer for Rent" />
								<aui:input type="radio" name="sub_accom" value="RENT_T"
									label="Take on Rent" />
							</div>
						<aui:col width="50">
							<div id="locationfield">
								<aui:select label="Location" name="location" role="combobox"
									aria-live="assertive">
									<aui:option value="">Select Location</aui:option>
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
							</div>
						</aui:col>
					</aui:row>
					<aui:row>
						<aui:col width="50">
							<aui:input type="file" name="imgfile" class="form-control"
								label="Upload Photo" />
							<div id="uploadPreview">
								<img scr="" id="upimg" />
							</div>
							<script>
								function ValidateFileUpload() {

									var fuData = document
											.getElementById('<portlet:namespace/>imgfile');
									var FileUploadPath = fuData.value;

									if (FileUploadPath == '') {
										//alert("Please upload an image");

									} else {
										var Extension = FileUploadPath
												.substring(
														FileUploadPath
																.lastIndexOf('.') + 1)
												.toLowerCase();
										if (Extension == "gif"
												|| Extension == "png"
												|| Extension == "bmp"
												|| Extension == "jpeg"
												|| Extension == "jpg") {

											if (fuData.files && fuData.files[0]) {

												var size = fuData.files[0].size;

												if (size > 100000) {
													alert("Maximum file size exceeds, max file size 100kb");
													fuData.value = "";
													return;
												} else {
													var reader = new FileReader();
													reader.onload = function(e) {
														$(
																'#<portlet:namespace/>upimg')
																.attr(
																		{
																			src : e.target.result,
																			width : '150'
																		});
													}
													reader
															.readAsDataURL(fuData.files[0]);
												}
											}
										} else {
											alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");
											fuData.value = "";
											return;
										}
									}
								}

								$("#<portlet:namespace/>imgfile").change(
										function(e) {
											ValidateFileUpload();
										});
							</script>
						</aui:col>
						<aui:col width="50">
							<aui:input required="true" type="textarea" label="Description"
								name="description" placeholder="Enter Description"
								maxlength="300"></aui:input>
						</aui:col>
					</aui:row>
					<div class="blank1"></div>
					<div id="postbtn_container" style="width: 10%; margin: auto;">
						<input type="submit" value="Post" class="post-btn btn btn-primary" />
					</div>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</div>
	</div>

	<script>
		$(document).ready(function(e) {
			var setin = setInterval(function() {
				findUname()
			}, 1000);
			function findUname() {
				var usname = $("#<portlet:namespace/>getUserName").val();
				var uniqueCpf = $("#<portlet:namespace/>uniqueCpf").val();
				if (usname != 0 && usname.length != 0) {

					/*if(  uniqueCpf.indexOf("R") > -1 || uniqueCpf.indexOf("BandhanRT") > -1 ){
						  
						       document.getElementById("postbttn").style.display='none';
					}*/
					$('#<portlet:namespace/>name').val(usname);
					$('#<portlet:namespace/>cpfno').val(uniqueCpf);
					clearInterval(setin);
				}
			}

			if (nonOngcian == "y") {
				$('#usertype').val('N');
			} else {
				$('#<portlet:namespace/>usertype').val('O');
			}

			/*function findUname(){
				
			var usname ='Rajkumari';
			var uniqueCpf ='76121';
			if(usname != 0 && usname.text().length !=0){
			alert(usname);
			alert(uniqueCpf);
				$('#name').val(usname);
				$('#cpfno').val(uniqueCpf);
				clearInterval(setin);
			}
			}*/

		});
	</script>
	<style>
.error {
	color: red;
	display: block;
}
</style>
	<!-- jQuery Form Validation code -->
	<script>
		jQuery.validator.setDefaults({
			debug : true,
			success : "valid"
		});
		jQuery.validator.addMethod("validateEmail", function(value, element) {
			// allow any non-whitespace characters as the host part
			return this.optional(element)
					|| /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
							.test(value);
		}, 'Please enter a valid email address.');
		// When the browser is ready...
		$(function() {
			$("#<portlet:namespace/>post-btn").click(function() {
				var cleanValue = $("#description").val();
				cleanValue = cleanValue.replace(/'/g, "&apos;");
				$("#<portlet:namespace/>description").val(cleanValue);
			});

			// Setup form validation on the #register-form element
			$("#<portlet:namespace/>facilitation-post")
					.validate(
							{

								// Specify the validation rules
								rules : {
									mobile : {
										required : true,
										number : true,
										minlength : 10
									},
									email : {
										required : true,
										validateEmail : true
									},
									location : {

										required : function(element) {
											if ($(
													"#<portlet:namespace/>category")
													.val() == "Matrimonial") {
												return false;
											} else {
												return true;
											}
										}
									},
									category : {
										required : true,

									},

									sub_matrx : {
										required : function(element) {
											if ($(
													"#<portlet:namespace/>category")
													.val() == "Matrimonial") {
												return true;
											} else {
												return false;
											}
										}
									},
									sub_accom : {
										required : function(element) {
											if ($(
													"#<portlet:namespace/>category")
													.val() == "Accommodation") {
												return true;
											} else {
												return false;
											}
										}
									},
									title : {
										required : true,
										maxlength : 75
									},
									description : {
										required : true,
										maxlength : 300
									},
								},

								// Specify the validation error messages
								messages : {
									mobile : "Please enter a valid mobile number",
									email : "Please enter a valid email addres",
									location : "Please select location",
									category : "Please select category",
									sub_matrx : "Please select sub category",
									sub_accom : "Please select sub category",
									title : {
										required : "Please provide a title for the post",
										maxlength : "Crossed maximum character length"
									},
									description : {
										required : "Please provide a description for the post",
										maxlength : "Crossed maximum character limit"
									}
								},
								errorPlacement : function(error, element) {
									if (element.is(":radio")) {
										error.appendTo(element
												.parents('.container'));
									} else { // This is the default behavior 
										error.insertAfter(element);
									}
								},
								submitHandler : function(form) {
									form.submit();
								}
							});

		});
	</script>