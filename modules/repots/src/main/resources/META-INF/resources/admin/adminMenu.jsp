<%@ include file="/init.jsp" %>
<% UserDao userDao = new UserDao();
User u= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName()); %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-2" style="background-color: #dcb2b5;">
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
       <li class="nav-item active">
        <portlet:renderURL var="reportAdmin">
        		<portlet:param name="mvcPath" value="/admin/view.jsp" />
		</portlet:renderURL>
        <a class="nav-link" href="<%=reportAdmin%>">Report Admin </a>
      </li>
      <li class="nav-item dropdown">
        <portlet:renderURL var="mobileAdmin">
        		<portlet:param name="mvcRenderCommandName" value="getmobileAdmin"/>
        		<!-- <portlet:param name="mvcPath" value="/admin/mobileAdmin.jsp" /> -->
		</portlet:renderURL>
		<a class="nav-link" href="<%=mobileAdmin%>" >
          Mobile Admin
        </a>
      </li>
       <li class="nav-item dropdown">
        <portlet:renderURL var="quizAdmin">
        		<portlet:param name="mvcPath" value="/admin/quizAdmin.jsp" />
		</portlet:renderURL>
		<a class="nav-link" href="<%=quizAdmin%>" >
          Quiz Admin
        </a>
      </li>
      <% if(u.getCpfNo()=="editor1"){ %>
      <li class="nav-item dropdown">
        <portlet:renderURL var="quizResult">
        		<portlet:param name="mvcPath" value="/admin/result.jsp" />
		</portlet:renderURL>
		<a class="nav-link" href="<%=quizResult%>" >
          Quiz Result
        </a>
      </li>
      <% } %>
       <c:if test="<%=themeDisplay.isSignedIn()%>">
			
			<li class="nav-item" style="float:right;">
					 <div style="float:right;"><a href="<%= themeDisplay.getURLPortal() %>/c/portal/logout">Log out</a></div>
			</li>
		</c:if>
    </ul>
  </div>
</nav>