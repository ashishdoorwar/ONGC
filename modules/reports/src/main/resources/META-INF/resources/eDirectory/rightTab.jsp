<%@ include file="/init.jsp"%>
<div class="p-3 border border-dark">
	<div id="fix_box">
		<div class="std_links">
			<portlet:renderURL var="searchStdForm">
				<portlet:param name="mvcPath" value="/eDirectory/searchStdForm.jsp" />
			</portlet:renderURL>
			<a href="<%=searchStdForm%>"> <img
				src="<%=request.getContextPath()%>/images/std.jpg" width="62"
				height="29" alt="std" />
			</a>
			<portlet:renderURL var="searchMscForm">
				<portlet:param name="mvcPath" value="/eDirectory/searchMscForm.jsp" />
			</portlet:renderURL>
			<a href="<%=searchMscForm%>"> <img
				src="<%=request.getContextPath()%>/images/msc.jpg" width="62"
				height="29" alt="mis" />
			</a>
			<portlet:renderURL var="searchIsdForm">
				<portlet:param name="mvcPath" value="/eDirectory/searchIsdForm.jsp" />
			</portlet:renderURL>
			<a href="<%=searchIsdForm%>"> <img
				src="<%=request.getContextPath()%>/images/isd.jpg" width="58"
				height="29" alt="isd" />
			</a>
		</div>
		<div>
			<ul>
				<!--  <li>Updation of Transferees? contact nos./ New nos. while issuing.(Letter dated 20.02.2009 from CIO) </li> -->
				<li style="padding: 10px 0px 10px 0px;">Please Update your
					profile.</li>
				<li>
				
				<portlet:renderURL var="getUserProfileInfo">
				<portlet:param name="mvcRenderCommandName"
				value="getUserProfileInfo" />
     			</portlet:renderURL>
				<a href="<%=getUserProfileInfo%>"><b>Click
							here</b> </a>to update your contact details</li>
			</ul>
		</div>
	</div>
</div>
