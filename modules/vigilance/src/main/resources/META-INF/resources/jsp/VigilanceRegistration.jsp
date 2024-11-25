<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/datepicker.css")%>'
	type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.css'
	type="text/css">

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.1.4.2.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/DatePicker/jquery.datepick.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#emailId").focus();
		$("a#show-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeIn(300);
		})
		$("a#close-panel").click(function() {
			$("#lightbox, #lightbox-panel").fadeOut(300);
		})
		$("a#pass-show-panel").click(function() {
			$("#lightbox, #pass-lightbox-panel").fadeIn(300);
		})
		$("a#pass-close-panel").click(function() {
			$("#lightbox, #pass-lightbox-panel").fadeOut(300);
		})
	})
</script>




<script>
	window.onload = function() {
		$(function() {
			$('#dbirth').datepick();
		});
	};
</script>
<s:set name="theme" value="'simple'" scope="page" />




<header>
	<%@ include file="/admintemplate/logoutHeader.jsp"%>
</header>
<!--header end-->


<!--Start Main Contant section-->
<div class="container" id="skip-main-content">
	<portlet:actionURL var="registerUser" name="registerUser">
		<portlet:param name="mvcPath" value="/jsp/userLogin.jsp" />
	</portlet:actionURL>

	<portlet:renderURL var="cancel">
		<portlet:param name="mvcPath" value="/jsp/userLogin.jsp" />
	</portlet:renderURL>
	<div>
		<h1 class="mb5 mt0">Registration</h1>
		
		<liferay-ui:error key="Email already exist" message="Email already exist" />
		<aui:form action="<%=registerUser%>" method="post" style="width:100%">
			<div class="bg-grey bdr-orange search-form">
				<div class="form-item">

					<p>
						All fields marked with <sup>* </sup>are mandatory.
					</p>
				</div>
				<br />
				<div class="form-item">

					<h2 class="yellow">
						<strong>Login Details</strong>
					</h2>
				</div>
				<aui:row>
					<aui:col width="33">
						<aui:input label="Username (e-mail)" name="emailId" type="email">
							<aui:validator name="required" />
							<aui:validator name="email" />
						</aui:input>
					</aui:col>
					<!--<aui:col width="33">
						<aui:input label="Password :" name="password" type="hidden">
							<aui:validator name="maxLength">14</aui:validator>
								<aui:validator name="minLength">8</aui:validator>
							<aui:validator
								errorMessage="Password must contain at least one character in upper
								case and one character in lower case.
								Password should contain at least one number and one
								special character (!,@,$,#)."
								name="custom">
								 function(val, fieldNode, ruleValue) {
								return /[a-z]/.test(val) && /[A-Z]/.test(val) &&/[0-9]/.test(val) && /[!,@,$,#]/.test(val);
								}
								 
								</aui:validator>
								<aui:validator
								errorMessage="Password must have alphabet in first position."
								name="custom">
								 function(val, fieldNode, ruleValue) {
								 var character= val.charAt(0)
								return /[a-z]/.test(character) || /[A-Z]/.test(character);
								}
								 
								</aui:validator> 
						</aui:input>
						 <a class="lnk" id="show-panel" data-toggle="modal"
							data-target="#policy"><b><u>Password Policy</font></u></b></a> 
					</aui:col>-->
					<!-- <aui:col width="33">
						<aui:input label="Confirm Password :" name="confirmPass"
							type="hidden">
							 <aui:validator name="required" />
							<aui:validator name="maxLength">14</aui:validator>
								<aui:validator name="minLength">8</aui:validator>
							<aui:validator
								errorMessage="The Password cannot match the Confirm Password"
								name="custom">
                function(val, fieldNode, ruleValue) {
                var match = false;
                var pass= $("#<portlet:namespace />password").val();
                if(pass===val){
                	match=true;
                }
				
                        return match;
                }
        </aui:validator> 



						</aui:input>
					</aui:col> -->
				</aui:row>

				<!-- <aui:row>
					<aui:col width="33">
						<aui:select name="securityQuestion" label="Security Question">
							<aui:option value="">Select</aui:option>
							<aui:option value="33">What is the colour of your vehicle ?</aui:option>
							<aui:option value="34">What is your place of Birth ?</aui:option>
							<aui:option value="35">What is your Maiden Name ?</aui:option>
							<aui:option value="36">What is your favourite colour ?</aui:option>
							<aui:option value="37">What is your Favourite Dish ?</aui:option>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
					<aui:col width="33">
						<aui:input label="Answer :" name="answer" type="text">
							<aui:validator name="required" />
						</aui:input>

					</aui:col>
				</aui:row> -->


				<br />
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<h2 class="yellow">
								<strong>Personal Details</strong>
							</h2>
						</div>
					</div>
				</div>
				<aui:row>
					<aui:col width="33">
						<aui:select name="userTitle" label="Title">
							<aui:option value="">Select</aui:option>
							<aui:option value="Mr.">Mr.</aui:option>
							<aui:option value="Ms.">Ms.</aui:option>
						</aui:select>
					</aui:col>
					<aui:col width="33">
						<aui:input label="First Name :" name="firstName" type="text">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="33">
						<aui:input label="Middle Name :" name="middleName" type="text" />
					</aui:col>
				</aui:row>

				<aui:row>
					<aui:col width="33">
						<aui:input label="Last Name:" name="lastName" type="text">
						</aui:input>
					</aui:col>
					<aui:col width="33">
						<aui:input label="DOB :" name="dob" type="date">
							<aui:validator name="required" />
							<aui:validator name="custom"
								errorMessage="Enter above 18 year old ">
								function (val, fieldNode, ruleValue) {
            					var date = new Date(val);
            					var today = new Date();
                  				var age =today.getFullYear()-date.getFullYear();
                  				var returnValue=true;
                  				if(age<18){
            					 returnValue = false;
                  				}
            					 return returnValue;
        					}
								</aui:validator>
						</aui:input>
					</aui:col>
					<aui:col width="33">
						<aui:input label="Phone No:" name="phoneNumber" type="text" />
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="33">
						<aui:input label="Mobile No. :(<h2>Country Code Mobile No. eg. INDIA 919999999999</h2>)" name="mobile" type="text" >
							<aui:validator name="required" />
							<aui:validator name="custom"
								errorMessage="You can enter a maximum of 15 numeric and no special characters allowed">
								<aui:validator name="minLength">12</aui:validator>
								<aui:validator name="maxLength">15</aui:validator>
							  function (val, fieldNode, ruleValue) {
							  var returnValue = true;
							  var iChars = "~`!@#$%^&-+*()_=[]\\\';,./{}|\":<>?qwertyuiopasdfghjklzxcvbnm";
							                for (var i = 0; i < val.length; i++) {
							                    if (iChars.indexOf(val.charAt(i)) != -1) {                
							                     returnValue = false;
							                    }
							                }
							                return returnValue;
							        }
							</aui:validator>
						</aui:input>
						<span class="description"></span>
					</aui:col>
				</aui:row>

				<br />
				<div class="form-item">
					<h2 class="yellow">
						<strong>Communication Address</strong>
					</h2>
					<br />
				</div>

				<aui:row>
					<aui:col width="33">
						<aui:input label="Address (Line 1) :" name="address1"
							type="textarea">
							<aui:validator name="required" />
						</aui:input>
					</aui:col>
					<aui:col width="33">
						<aui:input label="Address (Line 2) :" name="address2"
							type="textarea" />
					</aui:col>
					<aui:col width="33">
						<aui:select name="countryID" label="Country">
							<aui:option value="">Select Country</aui:option>
							<aui:option value="381">Afghanistan</aui:option>
							<aui:option value="382">Albania</aui:option>
							<aui:option value="383">Algeria</aui:option>
							<aui:option value="384">American Samoa</aui:option>
							<aui:option value="385">Andorra</aui:option>
							<aui:option value="386">Angola</aui:option>
							<aui:option value="387">Anguilla</aui:option>
							<aui:option value="388">Antarctica</aui:option>
							<aui:option value="389">Antigua and Barbuda</aui:option>
							<aui:option value="390">Argentina</aui:option>
							<aui:option value="391">Armenia</aui:option>
							<aui:option value="392">Aruba</aui:option>
							<aui:option value="393">Australia</aui:option>
							<aui:option value="394">Austria</aui:option>
							<aui:option value="395">Azerbaijan</aui:option>
							<aui:option value="396">Bahamas</aui:option>
							<aui:option value="397">Bahrain</aui:option>
							<aui:option value="398">Bangladesh</aui:option>
							<aui:option value="399">Barbados</aui:option>
							<aui:option value="400">Belarus</aui:option>
							<aui:option value="401">Belgium</aui:option>
							<aui:option value="402">Belize</aui:option>
							<aui:option value="403">Benin</aui:option>
							<aui:option value="404">Bermuda</aui:option>
							<aui:option value="405">Bhutan</aui:option>
							<aui:option value="406">Bolivia</aui:option>
							<aui:option value="407">Bosnia and Herzegowina</aui:option>
							<aui:option value="408">Botswana</aui:option>
							<aui:option value="409">Bouvet Island</aui:option>
							<aui:option value="410">Brazil</aui:option>
							<aui:option value="411">British Indian Ocean Territory</aui:option>
							<aui:option value="412">Brunei Darussalam</aui:option>
							<aui:option value="413">Bulgaria</aui:option>
							<aui:option value="414">Burkina Faso</aui:option>
							<aui:option value="415">Burundi</aui:option>
							<aui:option value="416">Cambodia</aui:option>
							<aui:option value="417">Cameroon</aui:option>
							<aui:option value="418">Canada</aui:option>
							<aui:option value="419">Cape Verde</aui:option>
							<aui:option value="420">Cayman Islands</aui:option>
							<aui:option value="421">Central African Republic</aui:option>
							<aui:option value="422">Chad</aui:option>
							<aui:option value="423">Chile</aui:option>
							<aui:option value="424">China</aui:option>
							<aui:option value="425">Christmas Island</aui:option>
							<aui:option value="426">Cocos (Keeling) Islands</aui:option>
							<aui:option value="427">Colombia</aui:option>
							<aui:option value="428">Comoros</aui:option>
							<aui:option value="429">Congo</aui:option>
							<aui:option value="430">Congo, the Democratic Republic of
									the</aui:option>
							<aui:option value="431">Cook Islands</aui:option>
							<aui:option value="432">Costa Rica</aui:option>
							<aui:option value="433">Cote d`Ivoire</aui:option>
							<aui:option value="434">Croatia (Hrvatska)</aui:option>
							<aui:option value="435">Cuba</aui:option>
							<aui:option value="436">Cyprus</aui:option>
							<aui:option value="437">Czech Republic</aui:option>
							<aui:option value="438">Denmark</aui:option>
							<aui:option value="439">Djibouti</aui:option>
							<aui:option value="440">Dominica</aui:option>
							<aui:option value="441">Dominican Republic</aui:option>
							<aui:option value="442">East Timor</aui:option>
							<aui:option value="443">Ecuador</aui:option>
							<aui:option value="444">Egypt</aui:option>
							<aui:option value="445">El Salvador</aui:option>
							<aui:option value="446">Equatorial Guinea</aui:option>
							<aui:option value="447">Eritrea</aui:option>
							<aui:option value="448">Estonia</aui:option>
							<aui:option value="449">Ethiopia</aui:option>
							<aui:option value="450">Falkland Islands (Malvinas)</aui:option>
							<aui:option value="451">Faroe Islands</aui:option>
							<aui:option value="452">Fiji</aui:option>
							<aui:option value="453">Finland</aui:option>
							<aui:option value="454">France</aui:option>
							<aui:option value="455">France, Metropolitan</aui:option>
							<aui:option value="456">French Guiana</aui:option>
							<aui:option value="457">French Polynesia</aui:option>
							<aui:option value="458">French Southern Territories</aui:option>
							<aui:option value="459">Gabon</aui:option>
							<aui:option value="460">Gambia</aui:option>
							<aui:option value="461">Georgia</aui:option>
							<aui:option value="462">Germany</aui:option>
							<aui:option value="463">Ghana</aui:option>
							<aui:option value="464">Gibraltar</aui:option>
							<aui:option value="465">Greece</aui:option>
							<aui:option value="466">Greenland</aui:option>
							<aui:option value="467">Grenada</aui:option>
							<aui:option value="468">Guadeloupe</aui:option>
							<aui:option value="469">Guam</aui:option>
							<aui:option value="470">Guatemala</aui:option>
							<aui:option value="471">Guinea</aui:option>
							<aui:option value="472">Guinea-Bissau</aui:option>
							<aui:option value="473">Guyana</aui:option>
							<aui:option value="474">Haiti</aui:option>
							<aui:option value="475">Heard and Mc Donald Islands</aui:option>
							<aui:option value="476">Holy See (Vatican City State)</aui:option>
							<aui:option value="477">Honduras</aui:option>
							<aui:option value="478">Hong Kong</aui:option>
							<aui:option value="479">Hungary</aui:option>
							<aui:option value="480">Iceland</aui:option>
							<aui:option value="481">India</aui:option>
							<aui:option value="482">Indonesia</aui:option>
							<aui:option value="483">Iran (Islamic Republic of)</aui:option>
							<aui:option value="484">Iraq</aui:option>
							<aui:option value="485">Ireland</aui:option>
							<aui:option value="486">Israel</aui:option>
							<aui:option value="487">Italy</aui:option>
							<aui:option value="488">Jamaica</aui:option>
							<aui:option value="489">Japan</aui:option>
							<aui:option value="490">Jordan</aui:option>
							<aui:option value="491">Kazakhstan</aui:option>
							<aui:option value="492">Kenya</aui:option>
							<aui:option value="493">Kiribati</aui:option>
							<aui:option value="494">Korea, Democratic People`s Republic
									of</aui:option>
							<aui:option value="495">Korea, Republic of</aui:option>
							<aui:option value="496">Kuwait</aui:option>
							<aui:option value="497">Kyrgyzstan</aui:option>
							<aui:option value="498">Lao People`s Democratic Republic</aui:option>
							<aui:option value="499">Latvia</aui:option>
							<aui:option value="500">Lebanon</aui:option>
							<aui:option value="501">Lesotho</aui:option>
							<aui:option value="502">Liberia</aui:option>
							<aui:option value="503">Libyan Arab Jamahiriya</aui:option>
							<aui:option value="504">Liechtenstein</aui:option>
							<aui:option value="505">Lithuania</aui:option>
							<aui:option value="506">Luxembourg</aui:option>
							<aui:option value="507">Macau</aui:option>
							<aui:option value="508">Macedonia, The Former Yugoslav
									Republic of</aui:option>
							<aui:option value="509">Madagascar</aui:option>
							<aui:option value="510">Malawi</aui:option>
							<aui:option value="511">Malaysia</aui:option>
							<aui:option value="512">Maldives</aui:option>
							<aui:option value="513">Mali</aui:option>
							<aui:option value="514">Malta</aui:option>
							<aui:option value="515">Marshall Islands</aui:option>
							<aui:option value="516">Martinique</aui:option>
							<aui:option value="517">Mauritania</aui:option>
							<aui:option value="518">Mauritius</aui:option>
							<aui:option value="519">Mayotte</aui:option>
							<aui:option value="520">Mexico</aui:option>
							<aui:option value="521">Micronesia, Federated States of</aui:option>
							<aui:option value="522">Moldova, Republic of</aui:option>
							<aui:option value="523">Monaco</aui:option>
							<aui:option value="524">Mongolia</aui:option>
							<aui:option value="525">Montserrat</aui:option>
							<aui:option value="526">Morocco</aui:option>
							<aui:option value="527">Mozambique</aui:option>
							<aui:option value="528">Myanmar</aui:option>
							<aui:option value="529">Namibia</aui:option>
							<aui:option value="530">Nauru</aui:option>
							<aui:option value="531">Nepal</aui:option>
							<aui:option value="532">Netherlands</aui:option>
							<aui:option value="533">Netherlands Antilles</aui:option>
							<aui:option value="534">New Caledonia</aui:option>
							<aui:option value="535">New Zealand</aui:option>
							<aui:option value="536">Nicaragua</aui:option>
							<aui:option value="537">Niger</aui:option>
							<aui:option value="538">Nigeria</aui:option>
							<aui:option value="539">Niue</aui:option>
							<aui:option value="540">Norfolk Island</aui:option>
							<aui:option value="541">Northern Mariana Islands</aui:option>
							<aui:option value="542">Norway</aui:option>
							<aui:option value="543">Oman</aui:option>
							<aui:option value="544">Pakistan</aui:option>
							<aui:option value="545">Palau</aui:option>
							<aui:option value="546">Panama</aui:option>
							<aui:option value="547">Papua New Guinea</aui:option>
							<aui:option value="548">Paraguay</aui:option>
							<aui:option value="549">Peru</aui:option>
							<aui:option value="550">Philippines</aui:option>
							<aui:option value="551">Pitcairn</aui:option>
							<aui:option value="552">Poland</aui:option>
							<aui:option value="553">Portugal</aui:option>
							<aui:option value="554">Puerto Rico</aui:option>
							<aui:option value="555">Qatar</aui:option>
							<aui:option value="556">Reunion</aui:option>
							<aui:option value="557">Romania</aui:option>
							<aui:option value="558">Russian Federation</aui:option>
							<aui:option value="559">Rwanda</aui:option>
							<aui:option value="560">Saint Kitts and Nevis</aui:option>
							<aui:option value="561">Saint LUCIA</aui:option>
							<aui:option value="562">Saint Vincent and the Grenadines</aui:option>
							<aui:option value="563">Samoa</aui:option>
							<aui:option value="564">San Marino</aui:option>
							<aui:option value="565">Sao Tome and Principe</aui:option>
							<aui:option value="566">Saudi Arabia</aui:option>
							<aui:option value="567">Senegal</aui:option>
							<aui:option value="568">Seychelles</aui:option>
							<aui:option value="569">Sierra Leone</aui:option>
							<aui:option value="570">Singapore</aui:option>
							<aui:option value="571">Slovakia (Slovak Republic)</aui:option>
							<aui:option value="572">Slovenia</aui:option>
							<aui:option value="573">Solomon Islands</aui:option>
							<aui:option value="574">Somalia</aui:option>
							<aui:option value="575">South Africa</aui:option>
							<aui:option value="576">South Georgia and the South
									Sandwich Islands</aui:option>
							<aui:option value="577">Spain</aui:option>
							<aui:option value="578">Sri Lanka</aui:option>
							<aui:option value="579">St. Helena</aui:option>
							<aui:option value="580">St. Pierre and Miquelon</aui:option>
							<aui:option value="581">Sudan</aui:option>
							<aui:option value="582">Suriname</aui:option>
							<aui:option value="583">Svalbard and Jan Mayen Islands</aui:option>
							<aui:option value="584">Swaziland</aui:option>
							<aui:option value="585">Sweden</aui:option>
							<aui:option value="586">Switzerland</aui:option>
							<aui:option value="587">Syrian Arab Republic</aui:option>
							<aui:option value="588">Taiwan, Province of China</aui:option>
							<aui:option value="589">Tajikistan</aui:option>
							<aui:option value="590">Tanzania, United Republic of</aui:option>
							<aui:option value="591">Thailand</aui:option>
							<aui:option value="592">Togo</aui:option>
							<aui:option value="593">Tokelau</aui:option>
							<aui:option value="594">Tonga</aui:option>
							<aui:option value="595">Trinidad and Tobago</aui:option>
							<aui:option value="596">Tunisia</aui:option>
							<aui:option value="597">Turkey</aui:option>
							<aui:option value="598">Turkmenistan</aui:option>
							<aui:option value="599">Turks and Caicos Islands</aui:option>
							<aui:option value="600">Tuvalu</aui:option>
							<aui:option value="601">Uganda</aui:option>
							<aui:option value="602">Ukraine</aui:option>
							<aui:option value="603">United Arab Emirates</aui:option>
							<aui:option value="604">United Kingdom</aui:option>
							<aui:option value="605">United States</aui:option>
							<aui:option value="606">United States Minor Outlying
									Islands</aui:option>
							<aui:option value="607">Uruguay</aui:option>
							<aui:option value="608">Uzbekistan</aui:option>
							<aui:option value="609">Vanuatu</aui:option>
							<aui:option value="610">Venezuela</aui:option>
							<aui:option value="611">Viet Nam</aui:option>
							<aui:option value="612">Virgin Islands (British)</aui:option>
							<aui:option value="613">Virgin Islands (U.S.)</aui:option>
							<aui:option value="614">Wallis and Futuna Islands</aui:option>
							<aui:option value="615">Western Sahara</aui:option>
							<aui:option value="616">Yemen</aui:option>
							<aui:option value="617">Yugoslavia</aui:option>
							<aui:option value="618">Zambia</aui:option>
							<aui:option value="619">Zimbabwe</aui:option>
							<aui:validator name="required" />
						</aui:select>
					</aui:col>
				</aui:row>
				<aui:row>
					<aui:col width="33">
						<aui:input label="State :" name="state" type="text" />
					</aui:col>
					<aui:col width="33">
						<aui:input label="Pincode :" name="pincode" type="text">
							<aui:validator name="custom"
								errorMessage="You can enter a maximum of 6 numeric">
								<aui:validator name="maxLength">6</aui:validator>
								<aui:validator name="minLength">6</aui:validator>
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

				</aui:row>

				<!-- <div class="form-item">
					<h2 class="yellow">
						<strong>User Agreement</strong>
					</h2>
				</div>
 -->
				<div class="row">
					<div class="col-md-12">


						<!-- <div class="form-item">
							<aui:input type="checkbox" name="TermsCondition"
								label="I have read, understood and agree to the Terms and Conditions " >
								<aui:validator name="required" />
								</aui:input>
							<a class="lnk" id="show-panel" data-toggle="modal"
								data-target="#myModal"><b><u>Read</font></u></b></a>
						</div> -->
						<br />

						<aui:row>
							<aui:button-row cssClass="p-2 text-center">
								<aui:button cssClass="btn btn-primary" type="submit"
									value="Submit" />
								<a class="btn text-white" href="<%=cancel%>">Cancel</a>
							</aui:button-row>
						</aui:row>
					</div>
				</div>

			</div>
		</aui:form>

		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Terms and Conditions</h4>
					</div>
					<div class="modal-body">


						<ol style="text-align: justify;">
							<li>Definition: Complaint is expression of dissatisfaction,
								concern or other feedback that needs a response. Any information
								received from contractors, vendors, suppliers, employees and
								general public about corruption, malpractice or misconduct on
								the part of public servants would be termed as a complaint. It
								is one of the important sources of information for
								identification of vigilance related issue.</li>
							<li>Complaint can be lodged against officials belonging to
								the organization/or against any irregularities within the
								organization</li>
							<li>Anonymous/Pseudonymous complaint will not be
								entertained. So, please give your proper name and address. If on
								verification is found to be pseudonymous, it may be filed.</li>
							<li>Complaint must be brief and contain be brief and contain
								factual details, verifiable facts and related matters.
								Complaints containing vague information or absurd statements are
								most likely to be filed.</li>
							<li>All actionable complaints are addressed by Vigilance
								Department and are followed up till their logical conclusion</li>
						</ol>

					</div>


				</div>

			</div>
		</div>
		<div id="policy" class="modal fade" role="dialog" aria-hidden="true"
			style="display: none;">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h4 class="modal-title">Password Policy</h4>
					</div>
					<div class="modal-body">
						<ol>
							<li>Password should be minimum 8 characters long and maximum
								14 characters.</li>
							<li>Password must contain at least one character in upper
								case and one character in lower case.</li>
							<li>Password should contain at least one number and one
								special character (!,@,$,#).</li>
							<li>Password must have alphabet in first position.</li>
							<li>Maximum identical consecutive characters in the password
								must not be more than 2.</li>
							<li>Password must not contain user id as the substring.</li>
						</ol>

					</div>

				</div>

			</div>
		</div>


		<!-- /lightbox-panel -->

		<div id="lightbox"></div>

	</div>

</div>
<footer>
	<%@ include file="/admintemplate/footer.jsp"%>
</footer>
