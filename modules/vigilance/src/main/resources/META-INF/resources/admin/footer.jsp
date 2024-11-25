
<!DOCTYPE html>
<html>
<head></head>
<body>
	<!-- Modal -->
	<!-- <div class="modal fade" id="myModalPop" role="dialog">
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
									<th>Screen Reader</th>
									<th>Website</th>
									<th>Free / Commercial</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Non Visual Desktop Access (NVDA)</td>
									<td><a href="http://www.nvda-project.org/"
										title="External website that opens in a new window"
										target="_blank">http://www.nvda-project.org/<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Free</td>
								</tr>
								<tr>
									<td>System Access To Go</td>
									<td><a href="http://www.satogo.com/"
										title="External website that opens in a new window"
										target="_blank">http://www.satogo.com/<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Free</td>
								</tr>
								<tr>
									<td>Thunder</td>
									<td><a
										href="http://www.screenreader.net/index.php?pageid=11"
										title="External website that opens in a new window"
										target="_blank">http://www.screenreader.net/index.php?pageid=11<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Free</td>
								</tr>
								<tr>
									<td>WebAnywhere</td>
									<td><a href="http://webanywhere.cs.washington.edu/wa.php"
										title="External website that opens in a new window"
										target="_blank">http://webanywhere.cs.washington.edu/wa.php<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Free</td>
								</tr>
								<tr>
									<td>Hal</td>
									<td><a
										href="http://www.yourdolphin.co.uk/productdetail.asp?id=5"
										title="External website that opens in a new window"
										target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=5<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Commercial</td>
								</tr>
								<tr>
									<td>JAWS</td>
									<td><a href="http://www.freedomscientific.com/jaws-hq.asp"
										title="External website that opens in a new window"
										target="_blank">http://www.freedomscientific.com/jaws-hq.asp<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Commercial</td>
								</tr>
								<tr>
									<td>Supernova</td>
									<td><a
										href="http://www.yourdolphin.co.uk/productdetail.asp?id=1"
										title="External website that opens in a new window"
										target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=1<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Commercial</td>
								</tr>
								<tr>
									<td>Window-Eyes</td>
									<td><a href="http://www.gwmicro.com/Window-Eyes/"
										title="External website that opens in a new window"
										target="_blank">http://www.gwmicro.com/Window-Eyes/<span
											class="hidethis"> (External website that opens in a
												new window)</span></a></td>
									<td>Commercial</td>
								</tr>
							</tbody>
						</table>
					</div>



				</div>
			</div>

		</div>

	</div> -->
	<p class="text-center">&copy; Copyright Oil and Natural Gas
		Corporation Limited, All Rights Reserved.</p>


	<script>
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
							$('table').parent('div').addClass(
									'table-responsive');
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