<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.model.PassionDoc"%>
<%@page import="com.ongc.liferay.passion.model.PassionVideo"%>
<%@page import="com.ongc.liferay.passion.model.PassionAudio"%>
<%@page import="com.ongc.liferay.passion.model.PassionPhoto"%>
<%@page import="com.ongc.liferay.passion.model.Passion"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ongc.liferay.passion.model.SubPassion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ include file="/init.jsp"%>
<%
User userData =(User) request.getAttribute("userData");
%>
<div class="contentarea">
	<div>
		<div>
			<h2>User Details</h2>
		</div>
		<div class="discusstionLeft userDetails" style="border: 1px solid #ddd">
			<div class="prflbox">
				<div class="f-left img">
					<img height="70px" width="75px"
						src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=userData.getCpfNo()%> />"
						alt="" class="spoimg" /> <span class="user-name"><%=userData.getEmpName() %></span>
					<%

						String cpf = (String) request.getAttribute("cpfnumber");
					UserService userService=new UserServiceImpl();
					User userData1=userService.getUser();
						String loggedUser = userData1.getCpfNo();
						if (cpf.equalsIgnoreCase(loggedUser)) {
					%>
					<!-- <span class="rightu"> <input type="button" value=""
						onclick="location.href = '/wps/portal/reports/reports-profile';"
						class="button user-edit" /></span> 
						 <span class="rightu"> <input
						type="button" value=""
						onclick="location.href = '/wps/portal/reports/reports-profile';"
						class="button user-edit" />
					</span>  -->
					<%
						}
					%>

				</div>
				<%
					if (cpf.equalsIgnoreCase(loggedUser)) {
				%>
				
				<%
					}
				%>
			</div>
			<div>
				<h5>Employee Details</h5>
			</div>
			<div class="empcon">
				<div class="empdbox">
					<ul class="emp">
						<li>
							<p class="empq">CPF Number:</p>
							<p class="empa">
								<%=userData.getCpfNo() %>
							</p>
						</li>
						<li>
							<p class="empq">Designation:</p>
							<p class="empa">
								<%=userData.getDesignation() %>
							</p>
						</li>
						<li>
							<p class="empq">Place of Posting:</p>
							<p class="empa">
								<%=userData.getCurrentPlace() %>
							</p>
						</li>
						<li>
							<p class="empq">Date of Birth:</p>
							<p class="empa">----</p>
						</li>
						<li>
							<p class="empq">Work Place:</p>
							<p class="empa">
								<s:property value="user.workPlace" />
								<%=userData.getWorkPlace() %>
							</p>
						</li>
						<li>
							<p class="empq">Mobile No.:</p>
							<p class="empa">
								<s:property value="user.mobileNo" />
								<%=userData.getMobileNo() %>
							</p>
						</li>
						<li>
							<p class="empq">Email-ID(Official):</p>
							<p class="empa">
								<a href="mailto:abcd@ongc.co.in"><s:property
										value="user.emailIdOfficial" />
										<%=userData.getEmailIdOfficial() %>
										</a>
							</p>
						</li>
						<li>
							<p class="empq">Office Direct No:</p>
							<p class="empa">
								<s:property value="user.phoneNumberOffice" />
								<%=userData.getPhoneNumberOffice() %>
							</p>
						</li>
						<li>
							<p class="empq">Office Extension No:</p>
							<p class="empa">
								<s:property value="user.extensionNumber" />
								<%=userData.getExtensionNumber() %>
							</p>
						</li>
						<li>
							<p class="empq">Office Address:</p>
							<p class="empa">
								<s:property value="user.officeAddress" />
								<%=userData.getOfficeAddress() %>
							</p>
						</li>
					</ul>
				</div>
				<div class="empdbox brnone">
					<ul class="emp">
						<li>
							<p class="empq">Name of Employee:</p>
							<p class="empa">
								<s:property value="user.empName" />
								<%=userData.getEmpName() %>
							</p>
						</li>
						<li>
							<p class="empq">Employee Level:</p>
							<p class="empa">
								<s:property value="user.empLevel" />
								<%=userData.getEmpLevel() %>
							</p>
						</li>
						<li>
							<p class="empq">Blood Group:</p>
							<p class="empa">
								<s:property value="user.bloodGroup" />
								<%=userData.getBloodGroup() %>
							</p>
						</li>
						<li>
							<p class="empq">Date of Joining:</p>
							<p class="empa">----</p>
						</li>
						<li>
							<p class="empq">Department:</p>
							<p class="empa">
								<s:property value="user.department" />
								<%=userData.getDepartment() %>
							</p>
						</li>
						<li>
							<p class="empq">Fax Number:</p>
							<p class="empa">----</p>
						</li>
						<li>
							<p class="empq">Email-ID(Personal):</p>
							<p class="empa">
								<a href="mailto:abcd@ongc.co.in"><s:property
										value="user.emailIdPersonal" />
										<%=userData.getEmailIdPersonal() %></a>
							</p>
						</li>
						<li>
							<p class="empq">Office Board No.:</p>
							<p class="empa">----</p>
						</li>
						<li>
							<p class="empq">Residence Direct No.:</p>
							<p class="empa">
								<s:property value="user.phoneNumberHome" />
								<%=userData.getPhoneNumberHome() %>
							</p>
						</li>
						<li>
							<p class="empq">Residence Address:</p>
							<p class="empa">
								<s:property value="user.ResidenceAddress" />
								<%=userData.getResidenceAddress() %>
							</p>
						</li>
					</ul>
				</div>
			</div>
			<%
				Map<String, Integer> mp = new HashMap();

				List<SubPassion> sub_passionName = (List) request.getAttribute("subpassionName");
				List<Passion> passion = (List) request.getAttribute("passionName");
				if (sub_passionName.size() > 0) {
					for (int i = 0; i < sub_passionName.size(); i++) {
						//System.out.println("passionId:"+sub_passionName.get(i).getPassionId()+",passionName:"+sub_passionName.get(i).getPassionName()+",subpassionId:"+sub_passionName.get(i).getSubPassionId()+",subpassionName:"+sub_passionName.get(i).getSubPassion());

					}
				}
				List<PassionPhoto> psnPhoto1 = (List) request.getAttribute("psnPhoto1");
				List<PassionPhoto> psnPhoto2 = (List) request.getAttribute("psnPhoto2");
				List<PassionPhoto> psnPhoto3 = (List) request.getAttribute("psnPhoto3");
				List<PassionAudio> psnAudio1 = (List) request.getAttribute("psnAudio1");
				List<PassionAudio> psnAudio2 = (List) request.getAttribute("psnAudio2");
				List<PassionAudio> psnAudio3 = (List) request.getAttribute("psnAudio3");
				List<PassionVideo> psnVideo1 = (List) request.getAttribute("psnVideo1");
				List<PassionVideo> psnVideo2 = (List) request.getAttribute("psnVideo2");
				List<PassionVideo> psnVideo3 = (List) request.getAttribute("psnVideo3");
				List<PassionDoc> psnDoc1 = (List) request.getAttribute("psnDoc1");
				List<PassionDoc> psnDoc2 = (List) request.getAttribute("psnDoc2");
				List<PassionDoc> psnDoc3 = (List) request.getAttribute("psnDoc3");
				String category1 = (String) request.getAttribute("category1");
				String category2 = (String) request.getAttribute("category2");
				String category3 = (String) request.getAttribute("category3");
				String strPassionId1 = (String) request.getAttribute("strPassionId1");
				String strPassionId2 = (String) request.getAttribute("strPassionId2");
				String strPassionId3 = (String) request.getAttribute("strPassionId3");
				String strSubPassionId1 = (String) request.getAttribute("strSubPassionId1");
				String strSubPassionId2 = (String) request.getAttribute("strSubPassionId2");
				String strSubPassionId3 = (String) request.getAttribute("strSubPassionId3");
				String strPassionname1 = (String) request.getAttribute("strPassionname1");
				String strPassionname2 = (String) request.getAttribute("strPassionname2");
				String strPassionname3 = (String) request.getAttribute("strPassionname3");
				String strSubPassionname1 = (String) request.getAttribute("strSubPassionname1");
				String strSubPassionname2 = (String) request.getAttribute("strSubPassionname2");
				String strSubPassionname3 = (String) request.getAttribute("strSubPassionname3");
				String strPassionNote1 = (String) request.getAttribute("strPassionNote1");
				String strPassionNote2 = (String) request.getAttribute("strPassionNote2");
				String strPassionNote3 = (String) request.getAttribute("strPassionNote3");

				int i = (Integer) request.getAttribute("i");
				int k = (Integer) request.getAttribute("k");
			%>

			<%
				if (category1 != "") {
			%>
			<div class="mTop20">
				<h2>My Passion</h2>
			</div>
			<div>
				<h5><%=strPassionname1%>
					|
					<%=strSubPassionname1%>
					<img id="upDownImg1"
						src="<%=request.getContextPath()%>/images/up.png"
						onclick="showDiv(1);" class="slide-down">
				</h5>

			</div>
			<div class="empcon" id="psnDiv1">
				<div class="soprtbox">
					<!--<h6>
							Category 1:<spanstrPassionname1me1 %></span>
						</h6>
						&nbsp;&nbsp;
						<h6>
							Sub Category 1:<spanstrSubPassionname1me1 %></span>
						</h6>
						<p>
							<strong>Note:</strong>
						</p>-->
					<div id="subpassionnote1" class="noteBox">
						<p id="strPassionNote1"><%=strPassionNote1 == null ? "" : strPassionNote1%></p>
					</div>
				</div>
				<%
					if (category1.equalsIgnoreCase("category1")) {
							if (psnPhoto1 != null) {
								if (psnPhoto1.size() > 0) {
				%>
				<p class="photo">Photos</p>

				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnPhoto1.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',1);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul1" style="left: 10px;">
								<%
									for (int j = 0; j < psnPhoto1.size(); j++) {

														String Caption = psnPhoto1.get(j).getPhotoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");

														String Description = psnPhoto1.get(j).getPhotoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>


								<li>
									<div style="position: relative; cursor: pointer;">
										<img id="CategoryP1File<%=(j + 1)%>"
											onclick="viewUserDetail('<%=psnPhoto1.get(j).getFileName()%>','<%=psnPhoto1.get(j).getPhotoId()%>','<%=Caption%>','<%=Description%>','<%=psnPhoto1.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId1%>','<%=strSubPassionId1%>','<%=strPassionname1%>','<%=strSubPassionname1%>')"
											src="" alt="<%=(j + 1)%>">
										<div class="item-caption"><%=psnPhoto1.get(j).getPhotoName()%></div>
										<div class="end-count"><%=psnPhoto1.get(j).getEndorsedCount()%></div>
									</div>
								</li>
								<script>
									var url="/wps/PA_ONGC_Passion/jspPages/passionPhoto.jsp?fileName="+'<%=psnPhoto1.get(j).getFileName()%>'+"&number="+'<%=cpf%>'+"&size=medium";
									document.getElementById("CategoryP1File<%=(j + 1)%>").src=url;
									</script>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnPhoto1.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',1);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category1.equalsIgnoreCase("category1")) {
							if (psnAudio1 != null) {
								if (psnAudio1.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Audios</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnAudio1.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',2);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul2" style="left: 10px;">
								<%
									for (int j = 0; j < psnAudio1.size(); j++) {
														String Caption = psnAudio1.get(j).getAudioName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");

														String Description = psnAudio1.get(j).getAudioDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryA1File<%=(j + 1)%>"
											onclick="playAudio('<%=psnAudio1.get(j).getAudioFileName()%>','<%=psnAudio1.get(j).getAudioId()%>','<%=Caption%>','<%=Description%>','<%=psnAudio1.get(j).getAudioEndorsedCount()%>','<%=cpf%>','<%=strPassionId1%>','<%=strSubPassionId1%>','<%=strPassionname1%>','<%=strSubPassionname1%>')"
											src="<%=request.getContextPath()%>/images/mp3.jpg" alt="">
										<div class="item-caption"><%=psnAudio1.get(j).getAudioName()%></div>
										<div class="end-count"><%=psnAudio1.get(j).getAudioEndorsedCount()%></div>
									</div></li>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnAudio1.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',2);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category1.equalsIgnoreCase("category1")) {
							if (psnVideo1 != null) {
								if (psnVideo1.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Videos</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnVideo1.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',3);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul3" style="left: 10px;">
								<%
									for (int j = 0; j < psnVideo1.size(); j++) {

														String Caption = psnVideo1.get(j).getVideoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnVideo1.get(j).getVideoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryV1File<%=(j + 1)%>"
											onclick="playVideo('<%=psnVideo1.get(j).getVideoFileName()%>','<%=psnVideo1.get(j).getVideoId()%>','<%=Caption%>','<%=Description%>','<%=psnVideo1.get(j).getVideoEndorsedCount()%>','<%=cpf%>','<%=strPassionId1%>','<%=strSubPassionId1%>','<%=strPassionname1%>','<%=strSubPassionname1%>')"
											src="<%=request.getContextPath()%>/images/mp4.jpg" alt="">
										<div class="item-caption"><%=psnVideo1.get(j).getVideoName()%></div>
										<div class="end-count"><%=psnVideo1.get(j).getVideoEndorsedCount()%></div>
									</div></li>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnVideo1.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',3);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category1.equalsIgnoreCase("category1")) {
							if (psnDoc1 != null) {
								if (psnDoc1.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Documents</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnDoc1.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',4);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul4" style="left: 10px;">
								<%
									for (int j = 0; j < psnDoc1.size(); j++) {

														String Caption = psnDoc1.get(j).getDocName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnDoc1.get(j).getDocDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}

														int ext = psnDoc1.get(j).getDocFileName().lastIndexOf(".");
														String extnsn = psnDoc1.get(j).getDocFileName().substring(ext + 1);
														if (extnsn.equalsIgnoreCase("pdf")) {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD1File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc1.get(j).getDocFileName()%>','<%=psnDoc1.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc1.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId1%>','<%=strSubPassionId1%>','<%=strPassionname1%>','<%=strSubPassionname1%>')"
											src="<%=request.getContextPath()%>/images/pdf.jpg" alt="">
										<div class="item-caption"><%=psnDoc1.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc1.get(j).getEndorsedCount()%></div>
									</div></li>

								<%
									} else {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD1File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc1.get(j).getDocFileName()%>','<%=psnDoc1.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc1.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId1%>','<%=strSubPassionId1%>','<%=strPassionname1%>','<%=strSubPassionname1%>')"
											src="<%=request.getContextPath()%>/images/word-icon.png"
											alt="">
										<div class="item-caption"><%=psnDoc1.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc1.get(j).getEndorsedCount()%></div>
									</div></li>

								<%
									}
													}
								%>
							</ul>
						</div>
						<%
							if (psnDoc1.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',4);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
					}
				%>
			</div>
			<!-- --------------  CATEGORY 2 --------------- -->
			<%
				if (category2 != "") {
			%>
			<div class="row">
				<div class="col-md-12">
				<h5><%=strPassionname2%>
					|
					<%=strSubPassionname2%><img id="upDownImg2"
						src="<%=request.getContextPath()%>/images/down.png"
						onclick="showDiv(2);" class="slide-down">
				</h5>
			</div>
		</div>
			<div class="empcon" id="psnDiv2">
				<div class="soprtbox">
					<!--<h6>
							Category 2:<spanstrPassionname2me2 %></span>
						</h6>
						&nbsp;&nbsp;
						<h6>
							Sub Category 2:<spanstrSubPassionname2me2 %></span>
						</h6>
						<p>
							<strong>Note:</strong>
						</p>-->
					<div class="noteBox">
						<p id="strPassionNote2"><%=strPassionNote2 == null ? "" : strPassionNote2%></p>
					</div>
				</div>
				<%
					if (category2.equalsIgnoreCase("category2")) {
							if (psnPhoto2 != null) {
								if (psnPhoto2.size() > 0) {
				%>

				<p class="photo">Photos</p>

				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnPhoto2.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',5);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul5" style="left: 10px;">
								<%
									for (int j = 0; j < psnPhoto2.size(); j++) {
														String Caption = psnPhoto2.get(j).getPhotoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnPhoto2.get(j).getPhotoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryP2File<%=(j + 1)%>"
											onclick="viewUserDetail('<%=psnPhoto2.get(j).getFileName()%>','<%=psnPhoto2.get(j).getPhotoId()%>','<%=Caption%>','<%=Description%>','<%=psnPhoto2.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId2%>','<%=strSubPassionId2%>','<%=strPassionname2%>','<%=strSubPassionname2%>')"
											src="" alt="image<%=(j + 1)%>">
										<div class="item-caption"><%=psnPhoto2.get(j).getPhotoName()%></div>
										<div class="end-count"><%=psnPhoto2.get(j).getEndorsedCount()%></div>
									</div></li>

								<script>
									var url="/wps/PA_ONGC_Passion/jspPages/passionPhoto.jsp?fileName="+'<%=psnPhoto2.get(j).getFileName()%>'+"&number="+'<%=cpf%>'+"&size=medium";
									document.getElementById("CategoryP2File<%=(j + 1)%>").src=url;
									</script>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnPhoto2.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',5);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category2.equalsIgnoreCase("category2")) {
							if (psnAudio2 != null) {
								if (psnAudio2.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Audios</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnAudio2.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',6);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul6" style="left: 10px;">
								<%
									for (int j = 0; j < psnAudio2.size(); j++) {
														String Caption = psnAudio2.get(j).getAudioName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnAudio2.get(j).getAudioDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryA2File<%=(j + 1)%>"
											onclick="playAudio('<%=psnAudio2.get(j).getAudioFileName()%>','<%=psnAudio2.get(j).getAudioId()%>','<%=Caption%>','<%=Description%>','<%=psnAudio2.get(j).getAudioEndorsedCount()%>','<%=cpf%>','<%=strPassionId2%>','<%=strSubPassionId2%>','<%=strPassionname2%>','<%=strSubPassionname2%>')"
											src="<%=request.getContextPath()%>/images/mp3.jpg" alt="">
										<div class="item-caption"><%=psnAudio2.get(j).getAudioName()%></div>
										<div class="end-count"><%=psnAudio2.get(j).getAudioEndorsedCount()%></div>
									</div></li>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnAudio2.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',6);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category2.equalsIgnoreCase("category2")) {
							if (psnVideo2 != null) {
								if (psnVideo2.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Videos</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnVideo2.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',7);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner7">
							<ul id="carousel_ul" style="left: 10px;">
								<%
									for (int j = 0; j < psnVideo2.size(); j++) {
														String Caption = psnVideo2.get(j).getVideoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnVideo2.get(j).getVideoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryV2File<%=(j + 1)%>"
											onclick="playVideo('<%=psnVideo2.get(j).getVideoFileName()%>','<%=psnVideo2.get(j).getVideoId()%>','<%=Caption%>','<%=Description%>','<%=psnVideo2.get(j).getVideoEndorsedCount()%>','<%=cpf%>','<%=strPassionId2%>','<%=strSubPassionId2%>','<%=strPassionname2%>','<%=strSubPassionname2%>')"
											src="<%=request.getContextPath()%>/images/mp4.jpg" alt="">
										<div class="item-caption"><%=psnVideo2.get(j).getVideoName()%></div>
										<div class="end-count"><%=psnVideo2.get(j).getVideoEndorsedCount()%></div>
									</div></li>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnVideo2.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',7);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category2.equalsIgnoreCase("category2")) {
							if (psnDoc2 != null) {
								if (psnDoc2.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Documents</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnDoc2.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',8);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul8" style="left: 10px;">
								<%
									for (int j = 0; j < psnDoc2.size(); j++) {

														String Caption = psnDoc2.get(j).getDocName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnDoc2.get(j).getDocDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}

														int ext = psnDoc2.get(j).getDocFileName().lastIndexOf(".");
														String extnsn = psnDoc2.get(j).getDocFileName().substring(ext + 1);
														if (extnsn.equalsIgnoreCase("pdf")) {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD2File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc2.get(j).getDocFileName()%>','<%=psnDoc2.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc2.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId2%>','<%=strSubPassionId2%>','<%=strPassionname2%>','<%=strSubPassionname2%>')"
											src="<%=request.getContextPath()%>/images/pdf.jpg" alt="">
										<div class="item-caption"><%=psnDoc2.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc2.get(j).getEndorsedCount()%></div>
									</div></li>
								<%
									} else {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD2File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc2.get(j).getDocFileName()%>','<%=psnDoc2.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc2.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId2%>','<%=strSubPassionId2%>','<%=strPassionname2%>','<%=strSubPassionname2%>')"
											src="<%=request.getContextPath()%>/images/word-icon.png"
											alt="">
										<div class="item-caption"><%=psnDoc2.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc2.get(j).getEndorsedCount()%></div>
									</div></li>

								<%
									}
													}
								%>
							</ul>
						</div>
						<%
							if (psnDoc2.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',8);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
					}
				%>
			</div>
			<!-- -----------End-------- -->
			<!-- --------------  CATEGORY 3 --------------- -->
			<%
				if (category3 != "") {
			%>
			<div class="row">
				<div class="col-md-12">
				<h5><%=strPassionname3%>
					|
					<%=strSubPassionname3%><img id="upDownImg3"
						src="<%=request.getContextPath()%>/images/down.png"
						onclick="showDiv(3);" class="slide-down">
				</h5>
			</div>
		</div>
			<div class="empcon" id="psnDiv3">
				<div class="soprtbox">
					<!--<h6>
							Category 3:<spanstrPassionname3me3 %></span>
						</h6>
						&nbsp;&nbsp;
						<h6>
							Sub Category 3:<spanstrSubPassionname3me3 %></span>
						</h6>
						<p>
							<strong>Note:</strong>
						</p>-->
					<div class="noteBox">
						<p id="strPassionNote3"><%=strPassionNote3 == null ? "" : strPassionNote3%></p>
					</div>
				</div>
				<%
					if (category3.equalsIgnoreCase("category3")) {
							if (psnPhoto3 != null) {
								if (psnPhoto3.size() > 0) {
				%>
				<p class="photo">Photos</p>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnPhoto3.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',9);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner9">
							<ul id="carousel_ul9" style="left: 10px;">
								<%
									for (int j = 0; j < psnPhoto3.size(); j++) {
														String Caption = psnPhoto3.get(j).getPhotoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnPhoto3.get(j).getPhotoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryP3File<%=(j + 1)%>"
											onclick="viewUserDetail('<%=psnPhoto3.get(j).getFileName()%>','<%=psnPhoto3.get(j).getPhotoId()%>','<%=Caption%>','<%=Description%>','<%=psnPhoto3.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId3%>','<%=strSubPassionId3%>','<%=strPassionname3%>','<%=strSubPassionname3%>')"
											src="" alt="image<%=(j + 1)%>">
										<div class="item-caption"><%=psnPhoto3.get(j).getPhotoName()%></div>
										<div class="end-count"><%=psnPhoto3.get(j).getEndorsedCount()%></div>
									</div></li>
								<script>
									var url="/wps/PA_ONGC_Passion/jspPages/passionPhoto.jsp?fileName="+'<%=psnPhoto3.get(j).getFileName()%>'+"&number="+'<%=cpf%>'+"&size=medium";
									document.getElementById("CategoryP3File<%=(j + 1)%>
									").src = url;
								</script>
								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnPhoto3.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',9);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category3.equalsIgnoreCase("category3")) {
							if (psnAudio3 != null) {
								if (psnAudio3.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Audios</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnAudio3.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',10);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul10" style="left: 10px;">
								<%
									for (int j = 0; j < psnAudio3.size(); j++) {
														String Caption = psnAudio3.get(j).getAudioName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnAudio3.get(j).getAudioDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryA3File<%=(j + 1)%>"
											onclick="playAudio('<%=psnAudio3.get(j).getAudioFileName()%>','<%=psnAudio3.get(j).getAudioId()%>','<%=Caption%>','<%=Description%>','<%=psnAudio3.get(j).getAudioEndorsedCount()%>','<%=cpf%>','<%=strPassionId3%>','<%=strSubPassionId3%>','<%=strPassionname3%>','<%=strSubPassionname3%>')"
											src="<%=request.getContextPath()%>/images/mp3.jpg" alt="">
										<div class="item-caption"><%=psnAudio3.get(j).getAudioName()%></div>
										<div class="end-count"><%=psnAudio3.get(j).getAudioEndorsedCount()%></div>
									</div></li>

								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnAudio3.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',10);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category3.equalsIgnoreCase("category3")) {
							if (psnVideo3 != null) {
								if (psnVideo3.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Videos</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnVideo3.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',11);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul11" style="left: 10px;">
								<%
									for (int j = 0; j < psnVideo3.size(); j++) {
														String Caption = psnVideo3.get(j).getVideoName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnVideo3.get(j).getVideoDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryV3File<%=(j + 1)%>"
											onclick="playVideo('<%=psnVideo3.get(j).getVideoFileName()%>','<%=psnVideo3.get(j).getVideoId()%>','<%=Caption%>','<%=Description%>','<%=psnVideo3.get(j).getVideoEndorsedCount()%>','<%=cpf%>','<%=strPassionId3%>','<%=strSubPassionId3%>','<%=strPassionname3%>','<%=strSubPassionname3%>')"
											src="<%=request.getContextPath()%>/images/mp4.jpg" alt="">
										<div class="item-caption"><%=psnVideo3.get(j).getVideoName()%></div>
										<div class="end-count"><%=psnVideo3.get(j).getVideoEndorsedCount()%></div>
									</div></li>

								<%
									}
								%>
							</ul>
						</div>
						<%
							if (psnVideo3.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',11);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
				%>
				<%
					if (category3.equalsIgnoreCase("category3")) {
							if (psnDoc3 != null) {
								if (psnDoc3.size() > 0) {
				%>
				<div class="soprtbox">
					<p class="photo">Documents</p>
				</div>
				<div class="thumbnailer">
					<div id="carousel_container">
						<%
							if (psnDoc3.size() > 8) {
						%>
						<div id="left_scroll">
							<a title="Slide left" onclick="slideImg('left',12);"><img
								src="<%=request.getContextPath()%>/images/prv.png"
								alt="left arrow"></a>
						</div>
						<%
							}
						%>
						<div id="carousel_inner">
							<ul id="carousel_ul12" style="left: 10px;">
								<%
									for (int j = 0; j < psnDoc3.size(); j++) {

														String Caption = psnDoc3.get(j).getDocName();
														Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
														String Description = psnDoc3.get(j).getDocDescription();
														if (Description != null) {
															Description = Description.replace("'", "\\'").replace("\"", "&quot;");
														}

														int ext = psnDoc3.get(j).getDocFileName().lastIndexOf(".");
														String extnsn = psnDoc3.get(j).getDocFileName().substring(ext + 1);
														if (extnsn.equalsIgnoreCase("pdf")) {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD3File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc3.get(j).getDocFileName()%>','<%=psnDoc3.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc3.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId3%>','<%=strSubPassionId3%>','<%=strPassionname3%>','<%=strSubPassionname3%>')"
											src="<%=request.getContextPath()%>/images/pdf.jpg" alt="">
										<div class="item-caption"><%=psnDoc3.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc3.get(j).getEndorsedCount()%></div>
									</div></li>
								<%
									} else {
								%>
								<li><div style="position: relative; cursor: pointer;">
										<img id="CategoryD3File<%=(j + 1)%>"
											onclick="viewUserDetailPdf('<%=psnDoc3.get(j).getDocFileName()%>','<%=psnDoc3.get(j).getDocId()%>','<%=Caption%>','<%=Description%>','<%=psnDoc3.get(j).getEndorsedCount()%>','<%=cpf%>','<%=strPassionId3%>','<%=strSubPassionId3%>','<%=strPassionname3%>','<%=strSubPassionname3%>')"
											src="<%=request.getContextPath()%>/images/word-icon.png"
											alt="">
										<div class="item-caption"><%=psnDoc3.get(j).getDocName()%></div>
										<div class="end-count"><%=psnDoc3.get(j).getEndorsedCount()%></div>
									</div></li>

								<%
									}
													}
								%>
							</ul>
						</div>
						<%
							if (psnDoc3.size() > 8) {
						%>
						<div id="right_scroll">
							<a title="Slide right" onclick="slideImg('right',12);"><img
								src="<%=request.getContextPath()%>/images/nxt.png"
								alt="right arrow"></a>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
							}
						}
					}
				%>
				<!-- -----------End-------- -->
			</div>

		</div>
		<script src="<%=request.getContextPath()%>/js/carousel.js"></script>
		<script src="<%=request.getContextPath()%>/js/carouselpop.js"></script>
		<script>
			$(document).ready(function(e) {
				$('ul.emp li:odd').css({
					'background-color' : '#fcfafa'
				});
				$('#psnDiv2').hide();
				$('#psnDiv3').hide();
			})

			function showDiv(i) {

				var a = $('#upDownImg' + i).attr('src');
				if (a == '/wps/PA_ONGC_Passion/images/up.png') {
					$('#psnDiv' + i).slideUp("slow");
					$('#upDownImg' + i).attr('src',
							'images/down.png');
				} else {
					$('#psnDiv' + i).slideDown("slow");
					$('#upDownImg' + i).attr('src',
							'/wps/PA_ONGC_Passion/images/up.png');
					$('html, body').animate({
						scrollTop : $('#upDownImg' + i).offset().top
					}, 500);
				}
			}
		</script>

		<input type="hidden" name="getCPF" id="getCPF"
			value='<s:property value="user.cpfNo" />' />

		<%
			String empCpf = (String) request.getParameter("empCpf");
			//String uCPF="78619";
			if (empCpf != null) {
		%>
		<input type="hidden" name="userCpf" id="userCpf" value="<%=empCpf%>" />
		<%} %>




	</div>
</div>

