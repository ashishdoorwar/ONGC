<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.model.Covid19"%>
<%@page import="com.ongc.liferay.dao.Covid19Dao"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@ include file="/init.jsp" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
 <portlet:renderURL var="viewCovid19" > 
 	<portlet:param name="mvcPath" value="/covid/viewcovid19.jsp"/>
 </portlet:renderURL>
 <portlet:renderURL var="formCovid19" > 
 	<portlet:param name="mvcPath" value="/covid/covid19Form.jsp"/>
 </portlet:renderURL>
<liferay-ui:error key="error" message="Illegal argument found" /> 
<div id="datadiv"></div>
<div id="covidHome">
	<p align="justify">
		<b>Please click on "Post Concern" button to post your query/concern/suggestion. We will get back to you.<br />
			 For medical queries please contact us on <span style="color:blue;">+01352793161</span>
			 and for other queries contact us on  <span style="color:blue;"> +01352793163</span>.
			</b><br />
	</p>
	<%-- for production --%>
	<a class="btn btn-primary btn-sm" href="<%=viewCovid19 %>" target="" title="">View All</a>
	<a class="btn btn-primary btn-sm" href="<%=formCovid19 %>" target="" title="">Post Concern </a>
</div>


<input type="hidden" name="resparam" id="resparam" value="<%=request.getParameter("errmsg") %>">
<%
try{
	UserDao userDao = new UserDao();
	User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
	Covid19Dao cdao = new Covid19Dao();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		List<Covid19> list =new ArrayList<Covid19>();
		if(userOngc!=null)
		{
		list=cdao.getCovid19DetailsUserByCpfNo(userOngc.getCpfNo());
		}else
		{
		list=cdao.getCovid19DetailsUserByCpfNo(userOngc.getCpfNo());
		}
	%>
	<%--********************************************************************************************************************** --%>
	<div align="center" id="responcemsg" style="color: red;">
			
		</div>
	<div class="mainform" id="concrnlist">
		<%
			if (list != null && list.size() > 0) {
		%>
		<div class="table-responsive">
			<table class="table table-striped" id="homecovid19">
				<thead>
					<tr>
						<th>S No.</th>
						<th>CPF No</th>
						<th>Concern</th>
						<th>Posted On</th>
						
					</tr>
				</thead>
				<%
					int i = 1;
						//Paginator pg = new Paginator(list, 10, null);
						Iterator itr= list.iterator();
						while (itr.hasNext()) {
							Covid19 c19 = (Covid19) itr.next();
				%>

				<tbody>

					<tr>
						<td><%=i%></td>
						<td><%=c19.getCpfNo()%></td>
						<td><%=c19.getConcern()%></td>
						<td><%=sdf1.format(sdf.parse(c19.getCreatedDate().toString()))%></td>
					</tr>
					<%
						i++;
							}
					%>
				</tbody>
			</table>
		</div>
		<%
		} else {
		}}catch(Exception e){
		e.printStackTrace();
		}
	%>
	</div>

<script>
			 $(document).ready(function() {
				 var table = $('#homecovid19').DataTable( {
					 lengthChange: false,bFilter: false, bInfo: false
				    });
				}); 
			 </script>