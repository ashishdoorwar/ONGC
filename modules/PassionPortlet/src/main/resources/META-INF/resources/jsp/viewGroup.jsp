<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.model.Group"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.GroupDaoImpl"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>

<div class="contentarea container">

    	<div class="row">
			<div class="col-md-12">
<%
GroupDaoImpl gDao=new GroupDaoImpl();
String groupName=null;
String gadmin=ParamUtil.getString(request,"gadmin");
String gid=ParamUtil.getString(request,"gid");
String gname=gDao.getGroupNameById(gid);
int topicCount=ParamUtil.getInteger(request,"topiccount");
	List mList=gDao.fetchMembers(gid);
	UserService userService = new UserServiceImpl();
	User userData = userService.getUser();
if (gname.contains("_@S@_")) {
gname=gname.replaceAll("_@S@_","'");

}

if (gname.contains("_@D@_")) {
gname=gname.replaceAll("_@D@_", "\"");

}
if (gname.contains("\"")) {
groupName=gname.replaceAll("\"", "_@D@_");

}
if (gname.contains("'")) {
groupName=gname.replaceAll("'","_@S@_");
}
%> 
<h2>Group/Categories</h2></div></div>
<div class="row groupName">
	<div class="col-md-12 text-right">
<span class="detail_text"><%=gname %></span>
<span class="groupBack"><s:a action="showGroup"><img src="<%=request.getContextPath()%>/images/back.jpg" title="Back"></s:a></span>
<%
 
	if(userData.getCpfNo().equalsIgnoreCase(gadmin)){
%>

<span><a onclick="editGroup(document.eform,'<%= gid %>')" href="#"><img src="<%=request.getContextPath()%>/images/group-edit.png" alt="" title="Edit"/></a></span>
<span><a onclick="deleteGroup(document.delform,'<%= gid %>','<%= topicCount %>')" href="#"><img src="<%=request.getContextPath()%>/images/group-close.png" title="Delete" /></a></span>
<%} %>
</div>
</div>
<div class="discusstionLeft" style="margin-top:10px;">
<s:form name="eform" id="eform" method="get" action="editGroup">
<s:hidden name="gid" id="gIde"></s:hidden>
</s:form>
<s:form name="delform" id="delform" method="post" action="deleteGroup">
<s:hidden name="gid" id="gIdDel"></s:hidden>
</s:form>

        		<!-- <div class="row"><h5> Creat New Group</h5></div>-->
              <div class="table-responsive">
				  <table class="table table-bordered">
					<thead>
						<tr>
							<th>Members Photo</th>
							<th>Members Name</th>
							<th>Members Passion</th>
						</tr>
					</thead>
					<tbody>
						<%
						Iterator it=mList.iterator();
						while(it.hasNext())
						{
						Group group=(Group)it.next();
					%>  
						<tr>
							<td><div class="tpbox bd">
								<s:a action="viewProfile">
								<s:param name="empCpf"><%=group.getMemberCpf() %></s:param>
								<img height="45px" width="45px" src="<%=request.getContextPath()%>/jspPages/web/viewUserProfile.jsp?empCPF=<%= group.getMemberCpf()%>" alt="" class="spoimg" /></s:a></div></td>
							<td> <div class="pbox bd" >
								<s:a action="viewProfile">
								<s:param name="empCpf"><%=group.getMemberCpf() %></s:param>
								<%= group.getMemberName()%></s:a></div></td>
							<td><div class="rpbox bd" style="width:auto;"><%= group.getSubPassionName() %></div></td>
						</tr>
						<%
					}
				%>    
					</tbody>
				  </table>
			  </div>  
            
   

</div>
       </div>


<!-------------Discussion Board Section start----------------------->
