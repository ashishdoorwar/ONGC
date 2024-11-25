<%@page import="java.util.ArrayList"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="com.ongc.liferay.model.QnBeanCpf"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.model.QnOptionBean"%>
<%@page import="com.ongc.liferay.model.QnBean"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Collections"%>
<%@page import="com.ongc.liferay.DaoImpl.ReportQuizDaoImpl"%>
<%@ include file="/init.jsp"%>

<portlet:actionURL name="saveAns" var="saveAnsURL"></portlet:actionURL>
<body onload="javascript:countdown(1);">
	<% UserDao userDao = new UserDao();
	User u= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
	List<QnBean> list=null;
	List<String> userAllRoles = new ArrayList<String>();
	List<Role> userRoles= themeDisplay.getUser().getRoles();
	for(Role userRegRole:userRoles){ userAllRoles.add(userRegRole.getName()); }
	boolean showQuizActions=false;
	for(String userRole:userAllRoles){ 
	  if(userRole.equals("Quiz"))//Site Owner
	  {  
		  showQuizActions=true;
	  		break;
	  }
	} 
	Statement stmt =null;
	ResultSet res=null;
	Connection conn=null;
	if(showQuizActions){
	/* boolean chck = (boolean) request.getAttribute("chck");
	System.out.println(chck);
	if(chck){  */

	boolean chck=true;
	String checpfno = u.getCpfNo();
			String query = "select cpf_no from qn_res where cpf_no='"+ checpfno + "'";
	        conn = DatasourceConnection.getConnection();
			stmt = conn.createStatement();
		    res = stmt.executeQuery(query);
			if (!res.next()) {
				chck = userDao.saveCpf(checpfno);
		%>
	<aui:form method="POST" id="myquiz" name="myquiz">
		<aui:input type="hidden" name="cpfno" value="<%= u.getCpfNo() %>" />
		<h3 class="pull-right">
			<span id="timertext">Time Left:&nbsp;</span><span id="timer"></span>
		</h3>
		<%
				ReportQuizDaoImpl quiz = new ReportQuizDaoImpl();
				long seed = System.nanoTime();
				List<QnBean> list1 = quiz.getRandomQuestionList();
				Collections.shuffle(list1, new Random(seed));
				list = list1.subList(0, 2);
				Collections.shuffle(list, new Random(seed));
				int count = 0;
				for (QnBean b : list) {
					count++;
			%>
		<br />
		<aui:row>
				<aui:col width="100">
			<strong> <%=String.valueOf(count) + ") " + b.getQnDesc()%></strong>
		</aui:col>
		<aui:col width="100">
		<%
				for (QnOptionBean c : b.getQnOpList()) {
			%>
			
		<aui:input type="radio" cssClass="<%=b.getQnNo()%>"
					value="<%=c.getOp()%>" label="<%=c.getQnOption()%>"
					name="<%=b.getQnNo()%>"></aui:input>
		</aui:col>
		</aui:row>
		<%
				}
				}
			%>
			<div align="center">
			<button name="submit" class="btn btn-primary" id="submit">Submit</button>
			<!-- <aui:button name="submitButton" type="submit" value="Submit" style="background-color: #8c000d;color: #fff !important;"/>  -->
			<!-- <input class="btn btn-primary" type="button" value="Reset" name="B2" onClick="document.myquiz.reset()"/> --></div>
	</aui:form>
	
	</body>
	<script>
function countdown(minutes) {
	var seconds = 60;
	var mins = minutes;
	function tick() {
		var current_minutes = mins - 1;
		var counter = document.getElementById("timer");
		seconds--;
		counter.innerHTML = current_minutes.toString() + ":"+ (seconds < 10 ? "0" : "") + String(seconds);
		if (seconds > 0) {
			setTimeout(tick, 1000);
		} else {

			if (mins > 1) {
				setTimeout(function() {
					countdown(mins - 1);
				}, 1000);

			} else {
				formSubmit();
			}
		}
	}
	tick();
}
</script>
<script>
	$("#submit").click(function(e) {
	  	           formSubmit();   
	});
	function formSubmit(){
		let selectedAns;var postData ="";
		let questions = "";
		<% for (QnBean b : list) { %>
		var questionAns= document.getElementsByClassName('<%=b.getQnNo()%>');
		questions += '<%=b.getQnNo()%>,';
		selectedAns = $("input[name='<portlet:namespace/><%=b.getQnNo()%>']:checked").val();
		postData += '&<portlet:namespace/><%=b.getQnNo()%>='+selectedAns;
	//	console.log(selectedAns);
		/* for (let i = 0; i < questionAns.length; i++) {
			  console.log(questionAns[i].value);
			} */
		<% } %>
		let postUrl ='<%= saveAnsURL%>'+postData+'&<portlet:namespace/>cpfno='+'<%= u.getCpfNo() %>'+'&<portlet:namespace/>questions='+questions;
		//console.log(postUrl+"/"+postData);
	  	             $.ajax({
	            url: postUrl,
	            type: 'POST',
	            cache: false,
	            processData: false,
	            contentType: false,
	            success: function(){
	            	 window.location.href='<%= themeDisplay.getURLPortal()%>/group/reports_en/quiz-time';
	            }
	            });
	}
</script>
	<% }else{%>
		<div class="alert alert-danger text-center" role="alert"><h1>You already participated in the quiz</h1></div>
	<%}
			}else{%>
			<div class="alert alert-danger text-center" role="alert"><h1>You don't have role to take quiz</h1></div><% } 
			DatasourceConnection.closeConnection(conn, stmt,res);
			%>
	