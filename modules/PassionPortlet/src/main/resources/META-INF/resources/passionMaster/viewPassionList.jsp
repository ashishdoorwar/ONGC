<%@page import="com.ongc.liferay.passion.model.SubPassion"%>
<%@page import="com.ongc.liferay.passion.model.Passion"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<html>

  <head>
<link href="<%=request.getContextPath()%>/adminCss/style.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/simplePagination.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.simplePagination.js"></script>
<style type="text/css">
ul { 
     width: 980px;
    display: block;
    white-space: nowrap;
     overflow: auto; 
     padding:0;
     }
 ul > li { 
   color:#720000;
   font-weight:900;
 }
 
 .plistblock{
    display: inline-block;
    min-height: 200px;
    width: 102px;
    vertical-align: top;
    margin: 10px;
 }
 ul > li > ul {
   display: block;
   width: 100%;
 }
 ul > li > ul > li {
   display: block;
   float: none;
   color: black;
   font-weight:500;
 }
</style>
<title>ONGC::Passion List</title>
   </head>
  
  <body>
<div class="header" style="float:left;">
<div class="color"></div>

<div class="inside">
	<div class="left"><a href="#"><img src="<%=request.getContextPath()%>/images/logo.jpg" alt="" /></a></div>
 
</div>
</div>

<div style="clear:both"></div>
<div class="blankBorder"></div>
<div class="contentarea">

<!--------------------------------------left nav------------------------------------------------>
<div class="content-sec">
<!-- ---   BODY PART -->
<div class="right-sec" style="width:100%; float:none;">

    	<div class="view-header">Passion List</div>
    	
    	<div >
    	<ul>
    	<% 
				List<Passion> passionList = (List)request.getAttribute("allPassionList");
    	System.out.println(passionList);
				Iterator itr=passionList.iterator();
				while(itr.hasNext()){
					Passion psn=(Passion)itr.next();
					
			%>
			
			<li class="plistblock"><%= psn.getPassionName() %>
			<%
			if(psn.getSubPassionList().size()!=0){
				%>
				<ul>
				<%
				Iterator itr1=psn.getSubPassionList().iterator();
				while(itr1.hasNext()){
					SubPassion subPsn=(SubPassion)itr1.next();
				%>
				<li><%= subPsn.getSubPassion() %></li>
				<%
				}
				%>
				</ul>
			<%
			}
			%>
			
			</li>
			
			
			
			<%
				}
			%>
			</ul>		

			
			</div></div>
</div>
</div>
<div style="clear:both"></div>
<div class="footer"><p>&copy; Oil and Natural Gas Corporation Limited</p></div>
  </body>
<script>
$(function() {
    $('#Table1 > tbody').pagination({
        items: 100,
        itemsOnPage: 10,
        cssStyle: 'light-theme'
    });
});
</script>
</html>
	