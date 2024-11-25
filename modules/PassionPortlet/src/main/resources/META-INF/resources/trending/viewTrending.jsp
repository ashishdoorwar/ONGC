<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.passion.model.HomeTrending"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<div class="trendingSec">
	<h2>Trending</h2>
	<%
			List<HomeTrending> tl=homeDao.myTrending(userData.getCpfNo());
			
			Iterator it2=tl.iterator();
			while(it2.hasNext())
			{
				HomeTrending htrending=(HomeTrending)it2.next();
				if(!htrending.getData().isEmpty())
				{
		%>
	<h3><%= htrending.getPassion() %>
		| <span><%= htrending.getSubPassion() %></span>
	</h3>
	<div class="row">
		<div class="col-md-12">
			<%
				List data=htrending.getData();
				
				Iterator it3=data.iterator();
					while(it3.hasNext())
					{
						HomeTrending htrending1=(HomeTrending)it3.next();
											String Caption=htrending1.getCaption();
											Caption=Caption.replace("'", "\\'").replace("\"", "&quot;");

											String Description=htrending1.getDescription();
											if(Description!=null){
											Description=Description.replace("'", "\\'").replace("\"", "&quot;");
											}
			%>
			<div class="clearboth trendingList">
				<s:a action="viewProfile">
					<s:param name="empCpf"><%=htrending1.getCpfNo()%></s:param>
					<img height="44px" width="44px"
						src=http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=htrending1.getCpfNo()%>
						alt="" class="spoimg" />
				</s:a>
				<%
         	if(htrending1.getFileType().equalsIgnoreCase("photo")){
			%>
				<p>
					<a
						href="javascript:viewUserDetail('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')"><%=htrending1.getEmpName() %></a><span><%= htrending1.getEndorseCount() %></span>
				</p>

				<%
				
				} 
				    if(htrending1.getFileType().equalsIgnoreCase("audio")){
						%>
				<p>
					<a
						href="javascript:playAudio('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')"><%=htrending1.getEmpName() %></a><span><%= htrending1.getEndorseCount() %></span>
				</p>
				<%
							
							}
				    if(htrending1.getFileType().equalsIgnoreCase("video")){
						%>
				<p>
					<a
						href="javascript:playVideo('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')"><%=htrending1.getEmpName() %></a><span><%= htrending1.getEndorseCount() %></span>
				</p>
				<%
							
							} 
				    if(htrending1.getFileType().equalsIgnoreCase("docs")){
						%>
				<p>
					<a
						href="javascript:viewUserDetailPdf('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')"><%=htrending1.getEmpName() %></a><span><%= htrending1.getEndorseCount() %></span>
				</p>
				<%
							
							} 
				    %>
			</div>
			<%
					}
				%>
		</div>
	</div>
	<%
			}}
		 %>
	<div class="row">
		<div class="col-md-12">
			<portlet:renderURL var="viewTrendingPassion">
				<portlet:param name="mvcPath" value="/trending/TrendingPassion.jsp" />
			</portlet:renderURL>
			<div class="moreLink text-right">
				<a href="<%=viewTrendingPassion %>">View All</a>
			</div>
		</div>
	</div>
</div>

