<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.model.HomeElite"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<div class="contentarea">
	<div class="left" style="width: 100%">
		<div class="row">
			<div class="col-md-12">
			<h2>Elite Group</h2>
		</div>
		</div>
		<div class="discusstionLeft">
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>User</th>
							<th>Passion</th>
							<th>Work Item</th>
							<th>Endorsements</th>
						</tr>
					</thead>
					<tbody>
						<%
                HomeDao hdao=new HomeDaoImpl();
                List elite=hdao.fetchElite();
				
				Iterator it5=elite.iterator();
					while(it5.hasNext())
					{
						HomeElite helite=(HomeElite)it5.next();
						String Caption=helite.getFileCaption();
						Caption=Caption.replace("'", "\\'").replace("\"", "&quot;");

						String Description=helite.getFileDesc();
						if(Description!=null){
						Description=Description.replace("'", "\\'").replace("\"", "&quot;");
						}
				%>
				<portlet:renderURL var="viewProfile">
					<portlet:param name="mvcRenderCommandName" value="view_profile" />
					<portlet:param name="cpfno" value="<%=helite.getEmpCpf()%>" />
				</portlet:renderURL>
						<tr>
							<td>
								<div class="elitetpbox bd">
									<a href="<%= viewProfile%>"><img height="44px" width="44px"
										src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=helite.getEmpCpf()%>"
										
										alt="" class="spoimg" />
									</a>
										<p>
											<a href="<%=viewProfile%>"><%=helite.getEmpName()%></a>
									</p>
								</div>
							</td>
							<td>
								<div class="elitepbox bd"><%=helite.getEpmPassion() %>
									|
									<%=helite.getEpmSubPassion() %></div>
							</td>
							<td>
								<div class="eliterpbox bd">
									<% if(helite.getFileType().equalsIgnoreCase("photo")){ %>
									<a
										href="javascript:viewUserDetail('<%=helite.getFileName()%>','<%=helite.getFileId()%>','<%=Caption%>','<%= Description%>','<%=helite.getEndorse()%>','<%=helite.getEmpCpf()%>',0,0,'<%=helite.getEpmPassion()%>','<%=helite.getEpmSubPassion()%>')"><%=helite.getFileCaption() %></a>
									<% } else if(helite.getFileType().equalsIgnoreCase("audio")){ %>
									<a
										href="javascript:playAudio('<%=helite.getFileName()%>','<%=helite.getFileId()%>','<%=Caption%>','<%= Description%>','<%=helite.getEndorse()%>','<%=helite.getEmpCpf()%>',0,0,'<%=helite.getEpmPassion()%>','<%=helite.getEpmSubPassion()%>')"><%=helite.getFileCaption() %></a>
									<% } else if(helite.getFileType().equalsIgnoreCase("video")){ %>
									<a
										href="javascript:playVideo('<%=helite.getFileName()%>','<%=helite.getFileId()%>','<%=Caption%>','<%= Description%>','<%=helite.getEndorse()%>','<%=helite.getEmpCpf()%>',0,0,'<%=helite.getEpmPassion()%>','<%=helite.getEpmSubPassion()%>')"><%=helite.getFileCaption() %></a>
									<% } else if(helite.getFileType().equalsIgnoreCase("docs")){ %>
									<a
										href="javascript:viewUserDetailPdf('<%=helite.getFileName()%>','<%=helite.getFileId()%>','<%=Caption%>','<%= Description%>','<%=helite.getEndorse()%>','<%=helite.getEmpCpf()%>',0,0,'<%=helite.getEpmPassion()%>','<%=helite.getEpmSubPassion()%>')"><%=helite.getFileCaption() %></a>
									<% } %>
								</div>
							</td>
							<td>
								<div class="elitevibox bd"><%=helite.getEndorse() %></div>
							</td>
						</tr>
						<%
					}
                 %>
					</tbody>
				</table>
			</div>

			<div class="clearboth"></div>

		</div>


	</div>


</div>