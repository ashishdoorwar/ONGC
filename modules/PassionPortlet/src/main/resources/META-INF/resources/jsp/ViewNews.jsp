<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.model.HomeNews"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<div class="contentarea">
	<div class="left" style="width:100%">
    	<div><h2>List of News</h2></div>
        <div class="discusstionLeft">
			<div class="table-responsive">
				<table class="table table-bordered">
                <thead>
					<tr>
						<th>News Title</th>
						<th>Published On</th>
					</tr>
				</thead>
				<tbody>
					<%
						HomeDao homeDao=new HomeDaoImpl();
						List<HomeNews> elite=homeDao.news();
						
						Iterator it=elite.iterator();
							while(it.hasNext())
							{
								HomeNews hNews=(HomeNews)it.next();
						%>
						<portlet:renderURL var="viewNewsDetail">
												<portlet:param name="mvcPath" value="/jsp/ViewNewsDetail.jsp" />
												<portlet:param name="newsId" value="<%=hNews.getNewsId()%>" />
											</portlet:renderURL>
						  
					<tr>
						
						<td><div class="newstpbox bd">
							<a href="<%=viewNewsDetail%>">
							<%=hNews.getNewsTitle() %></a></div></td>
						<td><div class="newsvibox bd"><%=hNews.getNewsCreatedOn() %></div></td>
					</tr>
					<%
				}
			 %> 
				</tbody>
				</table>
			</div>
        		
                
               
                	
                <div class="clearboth"> </div>
            
        </div>
        
         
    </div>
    
    
</div>