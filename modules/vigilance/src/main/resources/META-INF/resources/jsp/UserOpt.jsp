<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.ongc.liferay.vigilance.util.ONGCUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page
	import="com.fasterxml.jackson.databind.deser.std.EnumDeserializer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>

<%
	//String genaratedOpt = ONGCUtil.getInstance().generateOTP();
	String genaratedOpt = (String) request.getAttribute("otp");
	//String otpChoice = (String) request.getAttribute("otpChoice");
	//String genaratedOpt = "9691";
%>

<%@page import="com.liferay.portal.kernel.captcha.CaptchaTextException"%>
<%@page import="java.util.Random"%>
<%@ include file="/init.jsp"%>
<html>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/mrmsValidation.js'></script>
<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/tripleencoding.js'></script>

<script language="JavaScript"
	src='<%=request.getContextPath()%>/js/Registration.js'></script>

<script type="text/javascript">
	function clearPwBox() {
		if (document.getElementById) {
			var pw = document.forms[0].password;

			if (pw != null) {
				pw.value = '';
				pw.setAttribute("autocomplete", "off");

			}
		}
	}
	

	 $(document).ready(function() {

		 document.getElementById("resend").style.cssText="display:none";
		setInterval(function(){
			 document.getElementById("resend").style.cssText="display:block";
		   }, 590000);
		
	}); 

	/* let current_datetime = new Date()
	let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds() 

	console.log("Now===>"+formatted_date); */
	
</script>

<%!private int getRendomKey() {
		int key = 0;
		Random rn = new Random();
		int range = 789099;
		key = rn.nextInt(range);
		return key;
	}%>

<s:set name="theme" value="'simple'" scope="page" />


<body>
	<!-- Top Accessibility nav bar -->

	<!-- header start-->
	<header>
		<div class="topSecBg">
		<div class="container-fluid">	
		<div class="row">
		<div class="col-md-5 col-sm-12">
		<div id="logo-wrapper">
		<div class="logoSection">
		<div class="logo d-flex" aria-hidden="true">
		<a title="Bandhan" href="#" class="d-flex">
			<img src='<%=request.getContextPath()%>/images/logo.png'
			alt="logo" />
		</div>
		</a>
		<div class="aajadiLogo"><img src='<%=request.getContextPath()%>/images/azadiLogo.png'
			alt="logo" /></div>
		</div>
		</div>
		</div>
		<div class="col-md-7 col-sm-12">
		<div id="barTop-wrapper">
		<div class="barTop">
		<div class="linktop_div" >    
		<ul class="list-unstyled list-inline mb-0">
		<%-- <li class="list-inline-item"><img src='<%=request.getContextPath()%>/images/lastUpdated.png' class="webIcon mr-2" />Website Last Updated On 16 February 2022 05:31:00 pm</li> --%>
		<li class="list-inline-item"><a href="#skip-main-content" class="" title="Skip to main content" >Skip to main content</a></li>
		<li class="hidden-xs list-inline-item" >
		<a href="javascript:void(0);" data-toggle="modal" data-target="#myModalPop" class="" title="Screen Reader Access" >Screen Reader Access</a>
		</li>
		<li class="list-inline-item">
		<div class="dropdown"><a href="#" class="fontImg" class="dropdown-toggle" data-toggle="dropdown">
		<img class="access-light-theme" src='<%=request.getContextPath()%>/images/accessibility.png' alt="Accessibility Options">
		<img class="access-dark-theme" src='<%=request.getContextPath()%>/images/accessibility-white.png' alt="Accessibility Options">
		</a>
		
		<ul class="ft-con-ul dropdown-menu">
		<li class="ft-control hidden-xs hidden-sm font-set-option">
		<a tabindex="0" href="javascript:void(0);" title="Click to increase font" class="inc" aria-label="Click to increase font"><span>+A</span> Increase Font</a>
		<a href="javascript:void(0);" title="Click to increase font" class="inc2" aria-hidden="true" aria-label="Click to increase font"><span>+A</span> Increase Font</a>
		<a tabindex="0" href="javascript:void(0);" title="Click to normal font" class="nml" aria-label="Click to normal font"><span>A</span> Normal Font</a>
		<a tabindex="0" href="javascript:void(0);" title="Click to decrease font" class="dec" aria-label="Click to decrease font"><span>-A</span> Decrease Font</a>
		<a href="javascript:void(0);" title="Click to decrease font" class="dec2" aria-hidden="true" aria-label="Click to decrease font"><span>-A</span> Decrease Font</a></li>
		<li class="ft-control"><a tabindex="0" href="javascript:void(0);" id="dark-theme" aria-label="Select Dark Theme" class="clr-white" title="Select Dark Theme"><span class="black-bg ">A</span> Dark Theme</a>
		<a tabindex="0" aria-selected="true" href="javascript:void(0);" id="light-theme" class="light-theme" aria-label="Select Normal Theme" title="Select Normal Theme"><span>A</span> Normal Theme</a></li>
		</ul>
		</div>
		</li>
		</ul>
		</div>
		
		<portlet:renderURL var="registration">
								<portlet:param name="mvcPath"
									value="/jsp/VigilanceRegistration.jsp" />
							</portlet:renderURL>
							<portlet:renderURL var="forgotpasshome">
								<portlet:param name="mvcPath"
									value="/jsp/VigilanceForgotPassword.jsp" />
							</portlet:renderURL>
							<portlet:renderURL var="verifyOtp">
								<portlet:param name="mvcRenderCommandName" value="verify_otp" />
							</portlet:renderURL>
							<portlet:renderURL var="resendOtp">
								<portlet:param name="mvcRenderCommandName" value="resend_otp" />
							</portlet:renderURL>
							<liferay-ui:error key="Incorrect OTP" message="Incorrect OTP" />
							<liferay-ui:error key="OTPExceeded" message="OTP time exceeded" />
							
		</div>	
		</div>
		
		</div>
		
		</div>
		<div class="row formBox">
		<div class="col-md-12">
		<aui:form action="${verifyOtp}" name="fm" method="post">
								<div class="row">
								<div class="col-md-12">
		<ul class="list-inline">
		<li class="list-inline-item">
								Please enter verification code sent on mobile: 
								<aui:input name="generatedOtp" value="<%=genaratedOpt%>" type="hidden" />
								<aui:input name="otp" placeholder="Enter OTP" label="" type="text" onclick="checkResend()">
									<aui:validator name="required" />
								</aui:input></li>
				<li class="list-inline-item"><a href="<%= resendOtp%>" class="btn" id="resend" >Resend</a>	</li>			
				<li class="list-inline-item">				
								<aui:button-row cssClass="text-right">
									<%-- <% String oTPExceeded = (String) request.getAttribute("OTPExceeded");
										if(oTPExceeded!=null && oTPExceeded.equalsIgnoreCase("OTPExceeded")){  %>
										<a href="<%= resendOtp%>" class="btn" >Resend</a>
										<%} %> --%>
										
									<aui:button cssClass="btn" value="Submit" type="submit" />
								</aui:button-row></li>
								</div>
							</aui:form>
		</div>
		</div>
		
		
		
		
		</div>
		</div>
		</header>
	<!--header end-->

<!--banner sec start---------->
<div id="bannerHome-wrapper">
	<div class="bannerSection">
		<img src='<%=request.getContextPath()%>/images/banner01.jpg'
		alt="Banner" />
	</div>
	</div>
	
	<!--banner end---------->
	<div class="lodgeCompContent-wrapper" id="skip-main-content">
		<div class="container-fluid">
		<div class="row">
		<div class="col-md-8">
		<div class="lodgeCompContent">
		<div>
		<h3>Lodge your Complaint</h3>
		<p>Information received from contractors, vendors, suppliers, employees and general public about corruption, malpractice or misconduct on the part of ONGC personnel will be considered as a complaint. By registering to this site, anyone can file a formal complaint with the company on any ethical issues pertaining to corruption, malpractice or misconduct. However, anonymous/pseudonymous complaints will not be entertained. Complaint must be brief, contain factual details, verifiable facts and not contain vague/frivolous/absurd information. Complainant can attach his/her complaint in a single file in PDF/Word/Jpg format of max. size 10MB.</p>
		<p>After receiving the complaint, ONGC will seek confirmation from the complainant for owning or disowning the complaint, as the case may be, together with copy of his/her identity proof through post as per CVC Manual. Only after receiving complainant’ confirmation, further action on the complaint will be taken by Vigilance. It is therefore, advised that complainant provides his/her proper name and full address. If on verification, complaint is found to be pseudonymous, it will be filed without any action.</p>
		<p><strong>Further, it is informed that any complaint received on email will not be entertained.</strong></p>
		<p class="mb-0">For login issues, mail to: <a href="mailto:cc@ongc.co.in">cc@ongc.co.in</a></p>
		<p>NOTE: Any query other than login or website related issues will not be entertained</p>
		</div>
		</div></div>
		<div class="col-md-4 bgOrange">
		<div class="introHome">
		<h1>YOUR INTEGRITY FUELS YOUR PROSPERITY</h1>
		<p>Stay alert at work. Don't fear to report about any malpractice or fraud. <br />
		Remember, every act of integrity helps create the right environment.</p>
		</div>
		</div>
		</div>
		</div>
		</div>
		<div class="aboutIntro" >
			<div class="container-fluid">
			
			<div class="row">
			<div class="col-md-12">
			<h1>About Vigilance</h1>
			<p>Long term success of a corporate entity lies in internalization of ethical values and ensuring that these are worn irretrievably into company policies and practices. Vigilance department has an important role to play in ushering transparency, efficiency and integrity to working of the organization. Over the years vigilance function has evolved from a policing function to risk assessment, analysis and control function. By ensuring fair play, justice and preventing seepage of resources it promotes morale, efficiency and thereby better bottom-line.
			</p>
			<p>ONGC's Board is chaired by Dr. Alka Mittal | Director (HR) and Chairman & Managing Director - Additional Charge of this Maharatna Company. The vigilance set up in ONGC group of companies is presently headed by CVO(Chief Vigilance Officer), Mr. Ranjan Prakash Thakur, IRTS. There are 20 vigilance units, spread across the organization at various Regions, Assets, Basins and Plants. In addition to these units, CVO also looks after the vigilance set up in ONGC Videsh Limited(OVL) and ONGC Petro additions Limited(OPaL). Under the able guidance of CVO, vigilance department in ONGC constantly endeavours toward evolving best corporate practices.</p>
			</div>
			</div>
			
			</div>
			</div>
	<!--Start Main Contant section-->
	<div class="container">
		<div class="aboutWrapper">
			<img src='<%=request.getContextPath()%>/images/aboutBG.png' alt="About" />
			<div class="aboutVigi">
				<p class="heading">Contact</p>
				<p class="text-center">Tel (office) : 011 - 26129006, 26129016, 26755006<br />
				Email : <a href="mailto:cc@ongc.co.in">cc@ongc.co.in</a></p>
			</div>
			<div class="preventiveContent">
			<img src='<%=request.getContextPath()%>/images/prevenIcon.png' alt="PREVENTIVE VIGILANCE" />
			<p>PREVENTIVE<br /> VIGILANCE</p>
			<p>Integrity in the workplace<br />
			<a href="#">Know More...</a>
			</p>
			</div>
			<div class="departContent">
			<img src='<%=request.getContextPath()%>/images/departmentIcon.png' alt="VIGILANCE DEPARTMENT" />
			<p>VIGILANCE<br /> DEPARTMENT</p>
			<p>Your partner in progress! <br />
			</p>
			</div>
			<div class="awarenessContent">
			<img src='<%=request.getContextPath()%>/images/awareness.png' alt="VIGILANCE AWARENESS" />
			<p>VIGILANCE<br /> AWARENESS</p>
			<p>How to work more efficiently!<br />
			</p>
			</div>
			</div>
			<div class="compKnowMore">
			<img src='<%=request.getContextPath()%>/images/complaint-icon.png' />
			<div>
			<h2>How to lodge <span>your complaint</span></h2>
			</div>
			</div>
			<div class="lodgeCompContent fBottom">
				<div class="btmBarBg test-center">YOUR PARTNER IN PROGRESS</div>
				</div>
  
		</div>


<!--otp section end-->
		<%-- 	<div class="col-md-3">
				<div class="row">
					<div class="col-md-12 col-sm-6">
						<div class="login-sec">
							<h1>OTP</h1>
									<portlet:renderURL var="registration">
								<portlet:param name="mvcPath"
									value="/jsp/VigilanceRegistration.jsp" />
							</portlet:renderURL>
							<portlet:renderURL var="forgotpasshome">
								<portlet:param name="mvcPath"
									value="/jsp/VigilanceForgotPassword.jsp" />
							</portlet:renderURL>
							<portlet:renderURL var="verifyOtp">
								<portlet:param name="mvcRenderCommandName" value="verify_otp" />
							</portlet:renderURL>
							<liferay-ui:error key="Incorrect OTP" message="Incorrect OTP" />
							<aui:form action="${verifyOtp}" name="fm" method="post">
								<aui:input name="generatedOtp" value="<%=genaratedOpt%>"
									type="hidden" />
								<aui:input name="otp" placeholder="Enter OTP" type="text">
									<aui:validator name="required" />
								</aui:input>
								<aui:button-row cssClass="text-right">
									<aui:button cssClass="btn" value="Submit" type="submit" />
								</aui:button-row>
							</aui:form>
							
						</div>
					
					</div>

					<div class="col-md-12 col-sm-6">
						<h1>Lodge your Complaint</h1>
						<div class="bg-grey">
							<p>Information received from contractors, vendors, suppliers,
								employees and general public about corruption, malpractice or
								misconduct on the part of public servants can be termed as a
								complaint. By registering to this site, anyone can file a formal
								complaint with the company on any ethical issues pertaining to
								corruption, malpractice or misconduct. Anonymous/Pseudonymous
								complaints may not be entertained. It is therefore; desired to
								please give your proper name and address. If on verification,
								complaint is found to be pseudonymous, it may be filed.</p>

						</div>
						<p class="text-primary">
							For login issues, Mail to : <a href="mailto:cc@ongc.co.in"
								title="cc@ongc.co.in">cc@ongc.co.in</a> <br /> <small
								class="text-danger">NOTE: Any query other than login or
								website related issues will not be entertained</small>
						</p>
					</div>
				</div>


			</div> --%>
			<!--otp section end-->

		</div>

	<!--End Main Contant section-->

	<!--End Footer-->
	 <div class="modal fade" id="myModalPop" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Screen Reader Access</h4>
        </div>
        <div class="modal-body">
	<div class="table-responsive" dir="ltr">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>
					Screen Reader</th>
				<th>
					Website</th>
				<th>
					Free / Commercial</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					Non Visual Desktop Access (NVDA)</td>
				<td>
					<a href="http://www.nvda-project.org/" title="External website that opens in a new window" target="_blank">http://www.nvda-project.org/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					System Access To Go</td>
				<td>
					<a href="http://www.satogo.com/" title="External website that opens in a new window" target="_blank">http://www.satogo.com/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Thunder</td>
				<td>
					<a href="http://www.screenreader.net/index.php?pageid=11" title="External website that opens in a new window" target="_blank">http://www.screenreader.net/index.php?pageid=11<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					WebAnywhere</td>
				<td>
					<a href="http://webanywhere.cs.washington.edu/wa.php" title="External website that opens in a new window" target="_blank">http://webanywhere.cs.washington.edu/wa.php<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Hal</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=5" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=5<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					JAWS</td>
				<td>
					<a href="http://www.freedomscientific.com/jaws-hq.asp" title="External website that opens in a new window" target="_blank">http://www.freedomscientific.com/jaws-hq.asp<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Supernova</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=1" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=1<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Window-Eyes</td>
				<td>
					<a href="http://www.gwmicro.com/Window-Eyes/" title="External website that opens in a new window" target="_blank">http://www.gwmicro.com/Window-Eyes/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
		</tbody>
	</table>
</div>



						</div>
        </div>
       
      </div>
      
    </div>

	<script>
		function playCaptchAudioFun() {
			var xpath = "${captchaaudiourl}&sks=" + new Date();
			var audio = new Audio(xpath);
			audio.play();
		}
		function refreshCaptchFun() {

			document.getElementById("captcha_img").src = "${captchaimgurl}&stks="
					+ new Date();

		}
		$(document)
				.ready(
						function() {
							$("*")
									.each(
											function() {
												var th = this;
												var fntNormal = $(this).css(
														'font-size');
												fntNormal = fntNormal
														.substring(
																0,
																fntNormal.length - 2);
												var fntInt = parseInt(fntNormal);
												var one = 1;
												var fntLess = fntInt - one;
												var fntPlus = fntInt + one;
												var fntPP = fntPlus + one;
												var fntLL = fntLess - one;

												function setCookie(cname,
														cvalue, exdays) {
													var d = new Date();
													d
															.setTime(d
																	.getTime()
																	+ (exdays * 24 * 60 * 60 * 1000));
													var expires = "expires="
															+ d.toGMTString();
													document.cookie = cname
															+ "=" + cvalue
															+ ";" + expires
															+ ";path=/";
												}
												function getCookie(cname) {
													var name = cname + "=";
													var decodedCookie = decodeURIComponent(document.cookie);
													var ca = decodedCookie
															.split(';');
													for (var i = 0; i < ca.length; i++) {
														var c = ca[i];
														while (c.charAt(0) == ' ') {
															c = c.substring(1);
														}
														if (c.indexOf(name) == 0) {
															return c
																	.substring(
																			name.length,
																			c.length);
														}
													}
													return "";
												}
												function eraseCookie(name) {
													setCookie(name, "", -1);
												}
												function checkCookie() {
													var fnCheckLess = getCookie("fnLess");
													var fnCheckLL = getCookie("fnLL");
													var fnCheckNormal = getCookie("fnNormal");
													var fnCheckPlus = getCookie("fnPlus");
													var fnCheckPP = getCookie("fnPP");
													if (fnCheckLess != "") {
														$(th).css('font-size',
																+fntLess);
														$(".dec2").show();
														$(".inc2").hide();
													}
													if (fnCheckLL != "") {
														$(th).css('font-size',
																+fntLL);
														$(".dec2").show();
													}
													if (fnCheckNormal != "") {
														$(th).css('font-size',
																+fntNormal);
														$(".dec2, .inc2")
																.hide();
													}
													if (fnCheckPlus != "") {
														$(th).css('font-size',
																+fntPlus);
														$(".inc2").show();
														$(".dec2").hide();
													}
													if (fnCheckPP != "") {
														$(th).css('font-size',
																+fntPP);
														$(".inc2").show();
													}
												}

												$(".dec")
														.click(
																function() {
																	$(th)
																			.css(
																					'font-size',
																					+fntLess);
																	$(".dec2")
																			.show();
																	$(".inc2")
																			.hide();
																	setCookie("fnLess");
																	eraseCookie("fnLL");
																	eraseCookie("fnNormal");
																	eraseCookie("fnPlus");
																	eraseCookie("fnPP");
																});
												$(".dec2")
														.click(
																function() {
																	$(th)
																			.css(
																					'font-size',
																					+fntLL);
																	setCookie("fnLL");
																	eraseCookie("fnLess");
																	eraseCookie("fnNormal");
																	eraseCookie("fnPlus");
																	eraseCookie("fnPP");
																});
												$(".nml")
														.click(
																function() {
																	$(th)
																			.css(
																					'font-size',
																					+fntNormal);
																	$(
																			".dec2, .inc2")
																			.hide();
																	setCookie("fnNormal");
																	eraseCookie("fnLess");
																	eraseCookie("fnLL");
																	eraseCookie("fnPlus");
																	eraseCookie("fnPP");
																});
												$(".inc")
														.click(
																function() {
																	$(th)
																			.css(
																					'font-size',
																					+fntPlus);
																	$(".inc2")
																			.show();
																	$(".dec2")
																			.hide();
																	setCookie("fnPlus");
																	eraseCookie("fnLL");
																	eraseCookie("fnNormal");
																	eraseCookie("fnLess");
																	eraseCookie("fnPP");
																});
												$(".inc2")
														.click(
																function() {
																	$(th)
																			.css(
																					'font-size',
																					+fntPP);
																	setCookie("fnPP");
																	eraseCookie("fnLL");
																	eraseCookie("fnNormal");
																	eraseCookie("fnPlus");
																	eraseCookie("fnLess");
																});

												setTimeout(function() {
													checkCookie();
												}, 100)

											});
						});
	</script>
	<script>
		$(function() {
			function setCookie(cname, cvalue, exdays) {
				var d = new Date();
				d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
				var expires = "expires=" + d.toGMTString();
				document.cookie = cname + "=" + cvalue + ";" + expires
						+ ";path=/";
			}

			function getCookie(cname) {
				var name = cname + "=";
				var decodedCookie = decodeURIComponent(document.cookie);
				var ca = decodedCookie.split(';');
				for (var i = 0; i < ca.length; i++) {
					var c = ca[i];
					while (c.charAt(0) == ' ') {
						c = c.substring(1);
					}
					if (c.indexOf(name) == 0) {
						return c.substring(name.length, c.length);
					}
				}
				return "";
			}

			function eraseCookie(name) {
				setCookie(name, "", -1);
			}

			function checkCookie() {
				var idVal = getCookie("dark");
				if (idVal != "") {
					$("body").attr('id', 'contrast');
				}
			}

			$("#dark-theme").click(function() {
				$("body").attr('id', 'contrast');
				setCookie("dark");
			});

			$("#light-theme").click(function() {
				$("body").attr('id', '');
				eraseCookie('dark');
			});

			checkCookie();
		});
	</script>
</body>
</html>

