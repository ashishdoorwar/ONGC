<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.HomeDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.HomeDao"%>
<%@page import="com.ongc.liferay.passion.model.HomeNews"%>
<%@ include file="/init.jsp"%>
<div class="contentarea">
		<%
		HomeDao hdao=new HomeDaoImpl();
		String newsId=ParamUtil.getString(request, "newsId");
		HomeNews news=hdao.newsDetail(newsId);
		%>
    	<div class="discusstionNewsHead">
<h2>News</h2>
<div class="backNews"><span><s:a action="viewNews"><img src="<%=request.getContextPath()%>/images/back.jpg" alt="" title="Back" /></s:a></span></div>
<div style="clear:both;"></div>
<h3><%=news.getNewsTitle() %></h3>
<h4><%= news.getNewsCreatedOn() %></h4>	
</div>

        <div class="discusstionNews">
           <div class="row">
            <div class="col-md-6">

            	 <p><%=news.getNewsDesc().replaceAll("\n","<br />") %></p>
            </div>
			<div class="col-md-6">
				<div class="newsimg">
					<img src="http://reports1.ongc.co.in/o/blade/servlet/newsImageServlet?newsId=<%=news.getNewsId() %>" width="450" height="300" />
					<span><%=news.getFileCaption() %></span>      
				</div>
			</div>

        </div>
        </div>
  
    
    
</div>