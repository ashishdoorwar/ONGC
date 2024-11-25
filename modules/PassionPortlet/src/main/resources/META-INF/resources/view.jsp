
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.DiscussionDao"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.model.HomeNews"%>
<%@page import="com.ongc.liferay.passion.model.HomeElite"%>
<%@page import="com.ongc.liferay.passion.model.DiscussionBoard"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.passion.model.HomeUpdates"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.util.NameFormatter"%>
<%@page import="org.mcavallo.opencloud.Tag"%>
<%@page import="org.mcavallo.opencloud.Cloud"%>
<%@page import="com.ongc.liferay.passion.util.TagCloud"%>
<%@ include file="/init.jsp"%>

<%
	ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	td.getURLCurrent();
	td.getURLPortal();
	td.getURLHome();
	String url=(td.getURLCurrent().substring(td.getURLCurrent(). lastIndexOf("/") + 1). trim());
	UserService userService = new UserServiceImpl();
	User userData = userService.getUser();
	String cpfNo = userData.getCpfNo();
	HomeDao homeDao = new HomeDaoImpl();
	if(url.equals("ongcpassion")){
		
		
%>

<portlet:renderURL var="viewAllActivities">
	<portlet:param name="mvcPath" value="/jsp/AllActivities.jsp" />
</portlet:renderURL>

<portlet:renderURL var="viewEliteGroup">
	<portlet:param name="mvcPath" value="/jsp/EditGroup.jsp" />
</portlet:renderURL>
<portlet:renderURL var="showDiscussion">
	<portlet:param name="mvcPath" value="/jsp/PassionDiscussion.jsp" />
</portlet:renderURL>
<portlet:renderURL var="viewNews">
	<portlet:param name="mvcPath" value="/jsp/ViewNews.jsp" />
</portlet:renderURL>
<style>
#DBtags {
	display: none;
}
</style>
<div class="row">
	<div class="col-md-9">
		<div class="clearboth">
			<span id="basic-slider-previous" class="basic-slider-previous">
				<i class="glyphicon glyphicon-menu-left">d</i>
			</span> <span id="basic-slider-next" class="basic-slider-next"> <i
				class="glyphicon glyphicon-menu-right">d</i>
			</span>



			<form action="#" name="mform" id="mform">
				<input type="hidden" name="tid" value="" id="myValue" />
				<div id="topSliderHome" class="carousel slide" data-ride="carousel">

					<!-- Indicators -->
					<ul class="carousel-indicators">
						<li data-target="#topSliderHome" data-slide-to="0" class="active"></li>
						<li data-target="#topSliderHome" data-slide-to="1"></li>
						<li data-target="#topSliderHome" data-slide-to="2"></li>
						<li data-target="#topSliderHome" data-slide-to="3"></li>
						<li data-target="#topSliderHome" data-slide-to="4"></li>
					</ul>

					<!-- The slideshow -->
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div>
								<div class="clearboth">
									<h3>Recent Activities</h3>
								</div>
								<ul class="recentact"
									style="min-height: 177px; margin-bottom: 0;">
									<%
										List<HomeUpdates> updates = homeDao.fetchUpdates(cpfNo);

										if (updates.size() == 0) {
									%>
									<span>Currently no recent activities.</span>
									<%
										}

										Iterator it6 = updates.iterator();
										int listRow1 = 1;
										while (it6.hasNext() && listRow1 < 6) {	
										HomeUpdates hUpdates = (HomeUpdates) it6.next();%>
									<portlet:renderURL var="viewProfile">
										<portlet:param name="mvcRenderCommandName"
											value="view_profile" />
										<portlet:param name="cpfno" value="<%=hUpdates.getEmpCpf()%>" />
									</portlet:renderURL>
									<%if (!hUpdates.getAction().equalsIgnoreCase("newpassion")) {

												if (hUpdates.getAction().equalsIgnoreCase("enrolled")) {
									%>

									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>Enrolled for Passion ' <span class="sub-txt"><%=hUpdates.getPassionName()%>
													| <%=hUpdates.getSubPassion()%></span>'
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("newtopic")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>started a topic <s:action="viewPost">
													<s:param name="tid"><%=hUpdates.getTopicId()%></s:param><%=hUpdates.getTopicName()%>'</s:action></small></a></li>
									<%
										}
												String Caption = hUpdates.getCaption();
												if (Caption != null) {
													Caption = Caption.replace("'", "\\'").replace("\"", "&quot;");
												}

												String Description = hUpdates.getFileDesc();
												if (Description != null) {
													Description = Description.replace("'", "\\'").replace("\"", "&quot;");
												}

												if (hUpdates.getAction().equalsIgnoreCase("photoendorsed")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has endorsed your work item <a
												href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("audioendorsed")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has endorsed your work item <a
												href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("videoendorsed")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has endorsed your work item <a
												href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("docsendorsed")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has endorsed your work item <a
												href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("photocommented")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has posted comment on your work item <a
												href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("audiocommented")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has posted comment on your work item <a
												href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("videocommented")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has posted comment on your work item <a
												href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("docscommented")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has posted comment on your work item <a
												href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a>
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("photoadded")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has added <a
												href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a> on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%>
													| <%=hUpdates.getSubPassion()%></span>'
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("audioadded")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has added <a
												href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a> on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%>
													| <%=hUpdates.getSubPassion()%></span>'
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("videoadded")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has added <a
												href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a> on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%>
													| <%=hUpdates.getSubPassion()%></span>'
										</small></a></li>
									<%
										}
												if (hUpdates.getAction().equalsIgnoreCase("docsadded")) {
									%>
									<li><a><span>&bull;</span> <a href="<%= viewProfile%>">
												<s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName()%></a>
											<small>has added <a
												href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%=Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption()%>'
											</a> on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%>
													| <%=hUpdates.getSubPassion()%></span>'
										</small></a></li>
									<%
										}
												listRow1++;
											}
										}
									%>
								</ul>
								<!-- <p class="activities-note">* These 'Recent Activities' relate to Passions enrolled by You.</p> -->
								<%
									if (updates.size() != 0) {
								%>
								<div class="text-right">
									<div class="readmore">
										<a href="<%=viewAllActivities%>">View Earlier Activities</a>
									</div>
								</div>
								<%
									} else {
								%>
								<div class="text-right">
									<a href="<%=viewAllActivities%>" class="readmore"
										style="visibility: hidden;">View Earlier Activities</a>
								</div>
								<%
									}
								%>

							</div>
						</div>
						<div class="carousel-item">
							<div>
								<div class="clearboth">
									<h3>Hot Discussion Topics</h3>
								</div>

								<ul class="hotdiscussion">
									<%
										DiscussionDao discussionDao = new DiscussionDaoImpl();
										List<DiscussionBoard> dl = discussionDao.fetchTrendingTopics(cpfNo);

										Iterator it4 = dl.iterator();
										int i = 1;
										while (it4.hasNext()) {
											DiscussionBoard dboard = (DiscussionBoard) it4.next();
									%>
									<portlet:renderURL var="viewPost">
										<portlet:param name="mvcRenderCommandName" value="view_post" />
										<portlet:param name="tid" value="<%=dboard.getTopicId()%>" />
									</portlet:renderURL>
									<li><a href="<%=viewPost %>"> <%=dboard.getTopicName()%></a></li>
									<%
										i++;
										}
									%>
								</ul>
								<%
									if (dl.size() != 0) {
								%>
								<div class="text-right">
									<div class="readmore">
										<a href="<%=showDiscussion %>">View More</a>
									</div>
								</div>
								<%
									} else {
								%>
								<div class="text-right">
									<a class="readmore" style="visibility: hidden;">View More</a>
								</div>
								<%
									}
								%>
							</div>
						</div>
						<div class="carousel-item">
							<div>
								<div class="clearboth">
									<h3>New Members In Elite Group</h3>
								</div>
								<ul class="newmember">
									<%
										List<HomeElite> elite = homeDao.fetchElite();
										int listRow = 1;
										Iterator it5 = elite.iterator();
										if (elite.size() == 0) {
									%>
									<span>Currently no members in elite Group.</span>
									<%
										}
									%>

									<%
										while (it5.hasNext() && listRow <= 5) {
											HomeElite helite = (HomeElite) it5.next();
									%>
									<li><s:a action="viewProfile">
									 <div class="d-flex">
											<img height="116px" width="116px"
												src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=helite.getEmpCpf()%>"
												alt="" />
										<div>
											<s:param name="empCpf"><%=helite.getEmpCpf()%></s:param>
											<span><%=helite.getEmpName()%></span>
										</div>
									</div>
										</s:a></li>
									<%
										listRow++;
										}
									%>

								</ul>
								<%
									if (elite.size() != 0) {
								%>
								<div class="text-right">
									<div class="readmore">
										<a href="<%=viewEliteGroup%>">View More</a>
									</div>
								</div>
								<%
									} else {
								%>
								<div class="text-right">
									<a href="<%=viewEliteGroup%>" cssClass="readmore"
										cssStyle="visibility:hidden;">View More</a>
								</div>
								<%
									}
								%>
							</div>
						</div>
						<div class="carousel-item">
							<div>
								<div class="clearboth">
									<h3>News</h3>
								</div>

								<ul class="news">
									<%
										List<HomeNews> nl = homeDao.news();

										Iterator it1 = nl.iterator();
										int listRow2 = 1;
										while (it1.hasNext() && listRow2 <= 3) {
											HomeNews hnews = (HomeNews) it1.next();
											String newsDesc = hnews.getNewsDesc();
									%>
									<portlet:renderURL var="viewNewsDetail">
										<portlet:param name="mvcPath" value="/jsp/ViewNewsDetail.jsp" />
										<portlet:param name="newsId" value="<%=hnews.getNewsId()%>" />
									</portlet:renderURL>
									<li><h4><%=hnews.getNewsCreatedOn()%></h4>
										<p class="colorRed">
											<a href="<%=viewNewsDetail%>"> <%=hnews.getNewsTitle()%>
											</a>
										</p> <%
 	if (newsDesc.length() > 150)
 			newsDesc = newsDesc.substring(0, 149);
 %>
										<p>
											<a href="<%=viewNewsDetail%>"> </s:param> <%=newsDesc%>...
											</a>
										</p></li>
									<%
										listRow2++;
										}
									%>
								</ul>
								<%
									if (nl.size() != 0) {
								%>

								<div class="text-right">
									<div class="readmore">
										<a href="<%=viewNews%>">View More</a>
									</div>
								</div>
								<%
									} else {
								%>
								<div class="text-right">
									<a class="readmore" style="visibility: hidden;">View More</a>
								</div>
								<%
									}
								%>
							</div>
						</div>
						<div class="carousel-item">
							<div>
								<div class="clearboth">
									<h3>New Passion</h3>
								</div>

								<ul class="recentact">
									<%
										Iterator it7 = updates.iterator();
										int listRow3 = 1;
										while (it7.hasNext() && listRow3 < 6) {
											HomeUpdates hUpdates = (HomeUpdates) it7.next();

											if (hUpdates.getAction().equalsIgnoreCase("newpassion")) {
									%>
									<li><span>&bull;</span><%=hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion()%>
										<small>has been added.</small></li>
									<%
										}

											listRow3++;
										}
									%>
								</ul>

								<div class="text-right">
									<a class="readmore" style="visibility: hidden;">View More</a>
								</div>

							</div>
						</div>
					</div>


				</div>
			</form>
		</div>
		<div class="contentarea">
			<div class="left">
				<div>

					<ul class="toprated">
						<li>
							<h3>Discussion Cloud</h3>
							<div class=abcd id="DBmyCanvasContainer">
								<canvas width="235" height="230" id="DBmyCanvas">
						  </canvas>
							</div>
							<div id="DBtags">
								<ul>
									<%
										TagCloud tc = new TagCloud();
										Cloud cloud = tc.discussionTag(userData.getCpfNo());
										cloud.setMaxTagsToDisplay(20);
										cloud.setMaxWeight(20.0);
										cloud.setMinWeight(10.0);

										for (Tag tag : cloud.tags()) {
									%>
									<portlet:renderURL var="viewDiscussionCloud">
										<portlet:param name="mvcRenderCommandName"
											value="view_Discussion_Cloud" />
										<portlet:param name="q" value="<%=tag.getName()%>" />
									</portlet:renderURL>
									<li><a href="<%=viewDiscussionCloud%>"><%=tag.getName()%></a></li>
									<%
										}
									%>
								</ul>
							</div>


						</li>
						<li>
							<h3>Work-Item Cloud</h3>
							<div class=abcd id="WImyCanvasContainer">
								<canvas width="235" height="230" id="WImyCanvas">
				
			  </canvas>
							</div>
							<div style="display: none;" id="WItags">
								<ul>
									<%
										NameFormatter nf = new NameFormatter();
										Cloud cloud2 = tc.workItemTag();
										cloud2.setMaxTagsToDisplay(20);
										cloud2.setMaxWeight(20.0);
										cloud2.setMinWeight(10.0);
										// cycle through output tags
										for (Tag tag : cloud2.tags()) {
											String[] parArr = tag.getLink().split("//");
											String fileName = parArr[0];
											String fileId = parArr[1];
											String caption = parArr[2];
											String description = parArr[3];
											String endorseCount = parArr[4];
											String cpf = parArr[5];
											String passionName = parArr[6];
											String subPassionName = parArr[7];
											String type = parArr[8];

											caption = caption.replace("'", "\\'").replace("\"", "&quot;");
											if (description != null) {
												description = description.replace("'", "\\'").replace("\"", "&quot;");
											}

											// add an HTML link for each tag

											if (type.equalsIgnoreCase("photo")) {
									%>

									<li><a style="font-size: <%=tag.getWeight()%>px"
										href="javascript:viewUserDetail('<%=fileName%>','<%=fileId%>','<%=caption%>','<%=description%>','<%=endorseCount%>','<%=cpf%>',0,0,'<%=passionName%>','<%=subPassionName%>')"><%=tag.getName()%></a></li>

									<%
										}
											if (type.equalsIgnoreCase("audio")) {
									%>
									<li><a style="font-size: <%=tag.getWeight()%>px"
										href="javascript:playAudio('<%=fileName%>','<%=fileId%>','<%=caption%>','<%=description%>','<%=endorseCount%>','<%=cpf%>',0,0,'<%=passionName%>','<%=subPassionName%>')"><%=tag.getName()%></a></li>
									<%
										}
											if (type.equalsIgnoreCase("video")) {
									%>
									<li><a style="font-size: <%=tag.getWeight()%>px"
										href="javascript:playVideo('<%=fileName%>','<%=fileId%>','<%=caption%>','<%=description%>','<%=endorseCount%>','<%=cpf%>',0,0,'<%=passionName%>','<%=subPassionName%>')"><%=tag.getName()%></a></li>
									<%
										}
											if (type.equalsIgnoreCase("docs")) {
									%>
									<li><a style="font-size: <%=tag.getWeight()%>px"
										href="javascript:viewUserDetailPdf('<%=fileName%>','<%=fileId%>','<%=caption%>','<%=description%>','<%=endorseCount%>','<%=cpf%>',0,0,'<%=passionName%>','<%=subPassionName%>')"><%=tag.getName()%></a></li>
									<%
										}
									%>
									<%-- <li><a href="#"><%=tag.getName()%></a></li> --%>
									<%
										}
									%>

								</ul>
							</div>


						</li>

						<li>
							<h3>Passion Cloud</h3>
							<div class=abcd id="PSNmyCanvasContainer">
								<canvas width="235" height="230" id="PSNmyCanvas"></canvas>
							</div>
							<div style="display: none;" id="PSNtags">
								<ul>
									<%
										Cloud cloud3 = tc.enrolledPassionTag();
										cloud3.setMaxTagsToDisplay(20);
										cloud3.setMaxWeight(20.0);
										cloud3.setMinWeight(10.0);
										// cycle through output tags
										for (Tag tag : cloud3.tags()) {
									%>
									<%-- <li><s:a cssStyle="font-size: <%=tag.getWeight()%>px"
									action="showPassion">
									<s:param name="subPsn"><%=tag.getName()%></s:param>
									<%=tag.getName()%></s:a></li> --%>
									<portlet:renderURL var="viewPassionCloud">
										<portlet:param name="mvcRenderCommandName"
											value="show_passion" />
										<portlet:param name="subPsn" value="<%=tag.getName()%>" />
									</portlet:renderURL>
									<li><a href="<%=viewPassionCloud%>"><%=tag.getName()%></a></li>
									<%
										}
									%>
								</ul>
							</div>
						</li>

					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="col-md-3">
		<%@ include file="/trending/viewTrending.jsp"%>
		<!-- <liferay-portlet:runtime portletName="com_ongc_liferay_passion_TrendingPortlet_INSTANCE_z0F5vZB47d53"/> -->
	</div>
</div>



<portlet:renderURL var="viewPhoto"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/jsp/viewPhoto.jsp" />
</portlet:renderURL>

<portlet:renderURL var="viewPdfPhoto"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/jsp/viewPdfPhoto.jsp" />

</portlet:renderURL>

<portlet:renderURL var="playAudio"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/jsp/playAudio.jsp" />

</portlet:renderURL>

<portlet:renderURL var="playVideo"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/jsp/playVideo.jsp" />

</portlet:renderURL>
<script src="<%=request.getContextPath()%>/js/tagcanvas.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

		TagCanvas.Start('DBmyCanvas', 'DBtags', {
			weight : true,
			textColour : '#ff0000',
			depth : 0.5,
			stretchX : 0.7,
			stretchY : 1.1,
			initial : [ 0, 0.1 ],
			clickToFront : 300
		});

		TagCanvas.Start('WImyCanvas', 'WItags', {
			weight : true,
			textColour : '#000080',
			depth : 0.5,
			stretchX : 0.7,
			stretchY : 1.1,
			initial : [ 0, 0.1 ],
			clickToFront : 300
		});

		TagCanvas.Start('PSNmyCanvas', 'PSNtags', {
			weight : true,
			textColour : '#3cb371',
			depth : 0.5,
			stretchX : 0.7,
			stretchY : 1.1,
			initial : [ 0, 0.1 ],
			clickToFront : 300
		});

	});

	function viewPost(mform, id) {
		document.getElementById("myValue").value = id;

		document.mform.action = "viewPost";
		mform.submit();

	}
	function viewUserDetail(a,pId,itemCaption,pDesc,pEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
		var url="<%=viewPhoto%>&<portlet:namespace />pId="+pId+"&<portlet:namespace />url="+a+"&<portlet:namespace />itemCaption="+itemCaption+"&<portlet:namespace />photoDesc="+pDesc+"&<portlet:namespace />photoEndrs="+pEndrs+"&<portlet:namespace />cpfNo="+cpf+"&<portlet:namespace />passionId="+passionId+"&<portlet:namespace />subPassionId="+subPassionId+"&<portlet:namespace />passionName="+passionName+"&<portlet:namespace />subPassionName="+subPassionName+"&<portlet:namespace />userName="+"";
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 1000,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "View User Detail",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         location.reload();
	     },
	     ['liferay-util-window']
	     ); 
		
		
		<%-- $.ajax({
	      type: "POST",
	      url:  "<%=viewPhoto%>", 
	      data:'pId='+pId+'&url='+a+'&itemCaption='+itemCaption+'&photoDesc='+pDesc+'&photoEndrs='+pEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
	      success: function( response ) {
		      var abc=$.trim(response);
			if(abc=='logout'){
				alert('Your Session has been expired. Please Login again.')
				window.location.href='/wps/PA_ONGC_Passion/redirect.jsp';
			}else{

	      	$("#lightbox, #userDetais").fadeIn(300); 
			var top = ($(window).height()/2 - $("#userDetais").outerHeight()) / 2;
	        var left = ($(window).width() - $("#userDetais").outerWidth()) / 2;
	        $("#userDetais").css({position:'fixed', margin:0, top: (top < 0 ? top + 85 : 0)+'px', left: (left > 0 ? left : 0)+'px'});
	     	$("#userDetais").prepend(response);	  
	     	$("#closePanel").show();	
	     	$("#closePanel").css('top', top+75);   
			$("#closePanel").css('margin-left', ($("#userDetais").outerWidth() / 2)-35); 
			
	      }}
	    }); --%>
	    }

	function viewUserDetailPdf(pdfFilename,pId,itemCaption,pDesc,pEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
		var url="<%=viewPdfPhoto%>&<portlet:namespace />fileName="+pdfFilename+"&<portlet:namespace />pId="+pId+"&<portlet:namespace />itemCaption="+itemCaption+"&<portlet:namespace />photoDesc="+pDesc+"&<portlet:namespace />photoEndrs="+pEndrs+"&<portlet:namespace />cpfNo="+cpf+"&<portlet:namespace />passionId="+passionId+"&<portlet:namespace />subPassionId="+subPassionId+"&<portlet:namespace />passionName="+passionName+"&<portlet:namespace />subPassionName="+subPassionName+"&<portlet:namespace />userName="+'<%=userData.getEmpName()%>';
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 1000,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "View User Detail Pdf",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         location.reload();
	     },
	     ['liferay-util-window']
	     ); 
		//alert('2   '+pId);
		<%-- $.ajax({
	      type: "POST",      
	      url: "<%=viewPdfPhoto%>",       
	      data:'fileName='+pdfFilename+'&pId='+pId+'&itemCaption='+itemCaption+'&photoDesc='+pDesc+'&photoEndrs='+pEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
	      success: function( response ) {
	    	  //alert(response);
		var abc=$.trim(response);
				if(abc=='logout'){
				alert('Your Session has been expired. Please Login again.')
				window.location.href='/wps/PA_ONGC_Passion/redirect.jsp';
				}else{

	      	$("#lightbox, #userDetais").fadeIn(300); 
			var top = ($(window).height()/2 - $("#userDetais").outerHeight()) / 2;
	        var left = ($(window).width() - $("#userDetais").outerWidth()) / 2;
	        $("#userDetais").css({position:'fixed', margin:0, top: (top < 0 ? top + 85 : 0)+'px', left: (left > 0 ? left : 0)+'px'});
	     	$("#userDetais").prepend(response);	  
	     	$("#closePanel").show();	
	     	$("#closePanel").css('top', top+75);   
			$("#closePanel").css('margin-left', ($("#userDetais").outerWidth() / 2)-35); 
			}
	      }
	    }); --%>
	    }
	function playAudio(audioFileName,aId,itemCaption,audioDesc,audioEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
		console.log(audioFileName +"-----"+aId+"-----"+itemCaption+"-----"+audioDesc+"-----"+audioEndrs+"-----"+cpf+"-----"+passionId+"-----"+subPassionId+"-----"+passionName+"-----"+subPassionName);
		var url="<%=playAudio%>&<portlet:namespace />fileName="+audioFileName+"&<portlet:namespace />aId="+aId+"&<portlet:namespace />itemCaption="+itemCaption+"&<portlet:namespace />audioDesc="+audioDesc+"&<portlet:namespace />audioEndrs="+audioEndrs+"&<portlet:namespace />cpfNo="+cpf+"&<portlet:namespace />passionId="+passionId+"&<portlet:namespace />subPassionId="+subPassionId+"&<portlet:namespace />passionName="+passionName+"&<portlet:namespace />subPassionName="+subPassionName+"&<portlet:namespace />userName="+<%=userData.getCpfNo()%>;
		console.log(url);
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 1000,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "Play Audio",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         location.reload();
	     },
	     ['liferay-util-window']
	     );
<%-- 		$.ajax({
	      type: "POST",
	      url:  "<%=playAudio%>", 
	      data:'fileName='+audioFileName+'&aId='+aId+'&itemCaption='+itemCaption+'&audioDesc='+audioDesc+'&audioEndrs='+audioEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
	      success: function( response ) {
			var abc=$.trim(response);
				if(abc=='logout'){
				alert('Your Session has been expired. Please Login again.')
				window.location.href='/wps/PA_ONGC_Passion/redirect.jsp';
				}else{

	    	  $("#lightbox, #userDetais").fadeIn(300); 
	  		var top = ($(window).height()/2 - $("#userDetais").outerHeight()) / 2;
	          var left = ($(window).width() - $("#userDetais").outerWidth()) / 2;
	          $("#userDetais").css({position:'fixed', margin:0, top: (top < 0 ? top + 85 : 0)+'px', left: (left > 0 ? left : 0)+'px'});
	       	$("#userDetais").prepend(response);	  
	       	$("#closePanel").show();	
	       	$("#closePanel").css('top', top+75);   
	  		$("#closePanel").css('margin-left', ($("#userDetais").outerWidth() / 2)-35); 
			}}
		}); --%>
	}

	function playVideo(videoFileName,vId,itemCaption,videoDesc,videoEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
		var url="<%=playVideo%>&<portlet:namespace />fileName="+videoFileName+"&<portlet:namespace />vId="+vId+"&<portlet:namespace />itemCaption="+itemCaption+"&<portlet:namespace />videoDesc="+videoDesc+"&<portlet:namespace />videoEndrs="+videoEndrs+"&<portlet:namespace />cpfNo="+cpf+"&<portlet:namespace />passionId="+passionId+"&<portlet:namespace />subPassionId="+subPassionId+"&<portlet:namespace />passionName="+passionName+"&<portlet:namespace />subPassionName="+subPassionName+"&<portlet:namespace />userName="+'<%=userData.getCpfNo()%>';
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 1000,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	            resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "Play Video",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	         Liferay.Util.Window.getById(popUpId).destroy();
	         location.reload();
	     },
	     ['liferay-util-window']
	     );
			<%-- $.ajax({
				type: "POST",
				url:  "<%=playVideo%>", 
				data:'fileName='+videoFileName+'&vId='+vId+'&itemCaption='+itemCaption+'&videoDesc='+videoDesc+'&videoEndrs='+videoEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
				success: function( response ) {
				var abc=$.trim(response);
				if(abc=='logout'){
				alert('Your Session has been expired. Please Login again.')
				window.location.href='/wps/PA_ONGC_Passion/redirect.jsp';
				}else{
					$("#lightbox, #userDetais").fadeIn(300); 
					var top = ($(window).height()/2 - $("#userDetais").outerHeight()) / 2;
			        var left = ($(window).width() - $("#userDetais").outerWidth()) / 2;
			        $("#userDetais").css({position:'fixed', margin:0, top: (top < 0 ? top + 85 : 0)+'px', left: (left > 0 ? left : 0)+'px'});
			     	$("#userDetais").prepend(response);	  
			     	$("#closePanel").show();	
			     	$("#closePanel").css('top', top+75);   
					$("#closePanel").css('margin-left', ($("#userDetais").outerWidth() / 2)-35); 
						
					}}
			}); --%>
	}
</script>

<%}else if(url.equals("discussion-board")){%>

<%@ include file="/jsp/PassionDiscussion.jsp"%>

<%}else{%>

<%@ include file="/jsp/PassionGroup.jsp"%>

<%}%>