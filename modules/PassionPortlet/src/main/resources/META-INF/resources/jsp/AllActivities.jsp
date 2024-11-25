<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.model.HomeUpdates"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<div class="contentarea">
	<div class="left">
		<div>
			<h2>Earlier Activities</h2>
		</div>
		<div class="discusstionLeft">



			<ul class="odd earl_activities">

				<%
				UserService userService = new UserServiceImpl();
				User userData = userService.getUser();
				String cpfNo=userData.getCpfNo();
				HomeDao homeDao = new HomeDaoImpl();
				List<HomeUpdates> updates=homeDao.fetchUpdates(cpfNo);
        
        	if(updates.size()==0) { 
        		%>
				<span>Currently no recent activities.</span>
				<%}
            
        	 	Iterator it6=updates.iterator();
				int listRow1=1;
				while(it6.hasNext())
				{
					HomeUpdates hUpdates=(HomeUpdates)it6.next();
					%>
					<portlet:renderURL var="viewProfile">
										<portlet:param name="mvcRenderCommandName"
											value="view_profile" />
										<portlet:param name="cpfno"
											value="<%=hUpdates.getEmpCpf()%>" />
									</portlet:renderURL>
					<%
					
					if(hUpdates.getAction().equalsIgnoreCase("enrolled")){
						
					
			%>
			
			
				<li>
					<div class="bd"><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param> <%=hUpdates.getEmpName() %></a>
						Enrolled for Passion '<span class="sub-txt"><%= hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion() %></span>'
					</div>
				</li>
				<%
					}if(hUpdates.getAction().equalsIgnoreCase("newtopic")){
        	%>
				<li>
					<div class="bd"><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a>
						started a topic <s:a action="viewPost?tid=<%=hUpdates.getTopicId()%>">'<%=hUpdates.getTopicName() %>'</a>
					</div>
				</li>
				<%
					}

						String Caption=hUpdates.getCaption();
						if(Caption!=null){
						Caption=Caption.replace("'", "\\'").replace("\"", "&quot;");
						}
	
						String Description=hUpdates.getFileDesc();
						if(Description!=null){
						Description=Description.replace("'", "\\'").replace("\"", "&quot;");
						}
					
					if(hUpdates.getAction().equalsIgnoreCase("photoendorsed")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has endorsed your work item 
            <a href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("audioendorsed")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has endorsed your work item 
            <a href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("videoendorsed")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has endorsed your work item 
            <a href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("docsendorsed")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has endorsed your work item 
            <a href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("photocommented")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has posted comment on your work item 
            <a href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("audiocommented")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has posted comment on your work item 
            <a href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("videocommented")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has posted comment on your work item 
            <a href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("docscommented")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has posted comment on your work item 
            <a href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=cpfNo%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            </small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("photoadded")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has added 
            <a href="javascript:viewUserDetail('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
            on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion()%></span>'</small></a></li>
            <%
					}if(hUpdates.getAction().equalsIgnoreCase("audioadded")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has added 
            <a href="javascript:playAudio('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
             on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion() %></span>'</small></a></li>
             <%
					}if(hUpdates.getAction().equalsIgnoreCase("videoadded")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has added 
            <a href="javascript:playVideo('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
             on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion() %></span>'</small></a></li>
             <%
					}if(hUpdates.getAction().equalsIgnoreCase("docsadded")){
        	%>
            <li><a><span>&bull;</span><a href="<%= viewProfile%>"><s:param name="empCpf"><%=hUpdates.getEmpCpf()%></s:param><%=hUpdates.getEmpName() %></a> <small>has added 
            <a href="javascript:viewUserDetailPdf('<%=hUpdates.getFileName()%>','<%=hUpdates.getFileId()%>','<%=Caption%>','<%= Description%>','<%=hUpdates.getEndorseCount()%>','<%=hUpdates.getEmpCpf()%>',0,0,'<%=hUpdates.getPassionName()%>','<%=hUpdates.getSubPassion()%>')">'<%=hUpdates.getCaption() %>'</a>
             on passion '<span class="sub-txt"><%=hUpdates.getPassionName()%> | <%=hUpdates.getSubPassion() %></span>'</small></a></li> 
				<%
					}
					listRow1++;
            	}
            %>


			</ul>




		</div>


	</div>


</div>