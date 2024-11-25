<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@ include file="/init.jsp" %>

<% 
UserDao userDao = new UserDao();
User userOngc= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
Boolean existRecord = (boolean) request.getAttribute("existRecord");
//out.println(existRecord);
String msg= (String) request.getAttribute("msg"); 

if(existRecord){
	%>
	<div class="alert alert-danger">You Already Submitted Details</div>
	<%-- <script>
	setTimeout(function(){
	    window.location.href = '<%=themeDisplay.getURLPortal() %><%=themeDisplay.getURLCurrent()%>';
	 }, 5000);</script>
	</script> --%>
	<%
}else{
if(msg=="Success"){
%>
<div class="alert alert-success">Your Details Are Submitted Successfully.</div>

<script>
setTimeout(function(){
    window.location.href = '<%=themeDisplay.getURLPortal() %><%=themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"))%>';
 }, 5000);</script>
<%}else{
%>
<portlet:actionURL var="addfeaturedProfileURL" name="addfeaturedProfile"></portlet:actionURL>
<liferay-ui:success key="success" message="Greeting saved successfully!"/>
		<aui:form  method="post" name="fProf" action="<%= addfeaturedProfileURL %>"	enctype="multipart/form-data">
		<aui:input name="redirect" type="hidden" value="<%=themeDisplay.getURLCurrent() %>"/>
	<aui:row>
				<aui:col width="100">
			<aui:input type="hidden" name="cpfno1" value="<%= userOngc.getCpfNo() %>" />
</aui:col>
			<aui:col width="100">
<label>1. Give some unique highlights (you can give out-of-office achievements also) about yourself (at least two).</label></aui:col>
<aui:col width="100">
<aui:input name="high1" label="" type="textarea" cssClass="form-control mb10" required="true" maxlength="200" placeholder="Max 200 char"></aui:input></aui:col>
<aui:col width="100">
<aui:input name="high2" label="" type="textarea" cssClass="form-control mb10" required="true" maxlength="200" placeholder="Max 200 char"></aui:input></aui:col>
<aui:col width="100">
<aui:input name="high3" label="" type="textarea" cssClass="form-control mb10" maxlength="200" placeholder="Max 200 char"></aui:input></aui:col>
<aui:col width="100">
<aui:input name="high4" label="" type="textarea" cssClass="form-control mb10" maxlength="200" placeholder="Max 200 char"></aui:input></aui:col>
<aui:col width="100">
<aui:input name="high5" label="" type="textarea" cssClass="form-control mb10" maxlength="200" placeholder="Max 200 char"></aui:input></aui:col>
<aui:col width="100">			
<aui:input name="about" type="textarea" label="2. Tell us about your family (Optional)"  maxlength="500" placeholder="Max 500 char"></aui:input></aui:col>
<aui:col width="100">			
<aui:input name="passion" type="textarea" label="3. What are your passions?" required="true" maxlength="500" placeholder="Max 500 char"></aui:input></aui:col>
<aui:col width="100">			
<aui:input  name="others" type="textarea" label="4. Any other interesting things about you which you want to share with your colleagues?(Optional)"  maxlength="500" placeholder="Max 500 char"></aui:input></aui:col>
<aui:col width="100">
<aui:input type="file" name="imgfile" label="5. Upload Photo" required="true"/></aui:col>
</aui:row>
<aui:button-row cssClass="text-center">
  <aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary" /> 
  <input type="button" value="Reset" class="btn btn-primary" onClick="this.form.reset()">
  </aui:button-row>
  		</aui:form>
<%}
}%>