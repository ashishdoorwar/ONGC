<%@page import="com.ongc.liferay.passion.model.HomeTrending"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<div class="contentarea">
	<div class="left" style="width:100%">
    	<div class=""><h2>Trending</h2></div>
        <div class="discusstionLeft">
        	
				<div class="table-responsive">
					<table class="table table-bordered trendingList">
<thead>
	<tr>
		<th>Passion</th>
		<th>First Highest</th>
		<th>Second Highest</th>
		<th>Third Highest</th>
	</tr>
</thead>
<tbody>
	<%
	HomeDao homeDao=new HomeDaoImpl();
List<HomeTrending> tl=homeDao.trending();

Iterator it2=tl.iterator();
while(it2.hasNext())
{
	HomeTrending htrending=(HomeTrending)it2.next();
	if(!htrending.getData().isEmpty())
	{
%>
	<tr>
		<td><div class="trendingtpbox bd"><p><%= htrending.getPassion() %> | <%= htrending.getSubPassion() %></p></div></td>
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
		<td>
			<div class="d-flex">
				<s:a action="viewProfile">
				<img height="44px" width="44px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=htrending1.getCpfNo()%>" alt="" class="spoimg" />
				</s:a>
	 <%
	 if(htrending1.getFileType().equalsIgnoreCase("photo")){
	%>
		<p class="trendingUser"><a href="javascript:viewUserDetail('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')" ><%=htrending1.getEmpName() %></a><span class="trend-count"><%= htrending1.getEndorseCount() %></span></p>
	
	<%
		
		} 
			if(htrending1.getFileType().equalsIgnoreCase("audio")){
				%>
					<p class="trendingUser"><a href="javascript:playAudio('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')" ><%=htrending1.getEmpName() %></a><span class="trend-count"><%= htrending1.getEndorseCount() %></span></p>
				<%
					
					}
			if(htrending1.getFileType().equalsIgnoreCase("video")){
				%>
					<p class="trendingUser"><a href="javascript:playVideo('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')" ><%=htrending1.getEmpName() %></a><span class="trend-count"><%= htrending1.getEndorseCount() %></span></p>
				<%
					
					} 
			if(htrending1.getFileType().equalsIgnoreCase("docs")){
				%>
					<p class="trendingUser"><a href="javascript:viewUserDetailPdf('<%=htrending1.getFileName()%>','<%=htrending1.getFileId()%>','<%=Caption%>','<%=Description%>','<%=htrending1.getEndorseCount()%>','<%=htrending1.getCpfNo()%>',0,0,'<%=htrending.getPassion()%>','<%=htrending.getSubPassion()%>')" ><%=htrending1.getEmpName() %></a><span class="trend-count"><%= htrending1.getEndorseCount() %></span></p>
				<%
					
					} 
			%>
	 </div>

		</td>
		<%
	}
	%>
	</tr>
	<%
}}
%>   

</tbody>
					</table>
				</div>
                
              
                
               
                	
                <div class="clearboth"> </div>
            
        </div>
        
         
    </div>
    
    
</div>