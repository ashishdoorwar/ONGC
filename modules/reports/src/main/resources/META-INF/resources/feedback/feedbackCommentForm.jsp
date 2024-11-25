<%@page import="com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.FeedbackPostService"%>
<%@page import="com.ongc.liferay.reports.dao.Impl.FeedbackPostDaoImpl"%>
<%@page import="com.ongc.liferay.reports.dao.FeedbackPostDao"%>
<%@page import="com.ongc.liferay.reports.model.FeedbackPost"%>
<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="com.ongc.liferay.reports.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.reports.service.UserService"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@ include file="/init.jsp" %>

<portlet:defineObjects />
<% 
int postId= ParamUtil.getInteger(request, "postId");
FeedbackPost post = new FeedbackPost();
FeedbackPostService feedbackPostService = new FeedbackPostServiceImpl();
post.setPostId(postId);
post=feedbackPostService.getFeedbackPost(post);
UserService userService = new UserServiceImpl();
User employee= userService.getUser();
	//User user = (User) request.getSession(true).getAttribute(CSRConstant.ONGC_REPORT_EMPLOYEE);
	String AUTHUSER[] = {"121229", "57392", "58987", "90245", "94592",
	"95903", "77242", "64996", "121841", "122385", "76121",
	"126952", "48482", "72390", "68749", "93177", "78619",
	"46889", "webteam", "67543", "95687"}; //"35962","82277","81098",94314
	boolean flag = false;
	for (int i = 0; i < AUTHUSER.length; i++) {
		if (employee != null && employee.getCpfNo().equals(AUTHUSER[i])) {
	flag = true;
		}
	}
%>
  <div class="container formWrapper">
 <div class="row">
 <div class="col-lg-12">
 
 <portlet:actionURL var="saveFeedbackComment" name="feedbackPostCommentForm" >
 
 </portlet:actionURL>
  <aui:form action="<%= saveFeedbackComment %>" method="post" name="myForm">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="Message">
			<aui:row>
					<aui:input label="Post ID" name="postId" type="hidden" value="<%= postId %>"/>
				<aui:col width="20">
				<label for="">Subject</label>	
				</aui:col>
				<aui:col width="80">
				<label for=""><%= post.getSubject() %></label>	
				</aui:col>
			</aui:row>
			<aui:row>
			<aui:col width="20">
				<label for="">Suggestion</label>	
				</aui:col>
				<aui:col width="80">
				<label for=""><%= post.getMessage() %></label>	
				</aui:col>
				</aui:row>
			<aui:row>
			<aui:col width="20">
					Post Comments*
				</aui:col>
					<aui:col width="80">
					<liferay-ui:input-editor name="comment" initMethod="initEditor" width="100" height="400" resizable="true" toolbarSet="liferay-article">
					<aui:validator name="required" />
					</liferay-ui:input-editor>
				</aui:col>
			</aui:row>
		
			<aui:button-row cssClass="text-center">
				<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn-primary btn btn-sm"/>
					<aui:button name="resetButton" type="button"  onClick="this.form.reset()"  value="Reset" cssClass="btn-primary btn btn-sm" />
	<a onclick="javascript:history.go(-1)" class="btn-primary btn btn-sm">Back</a>
			</aui:button-row>
		</aui:fieldset>
		<hr>
		<aui:row>
	<strong>Note</strong>
		<ul class="red_note">       
		  <li>Please use above text editor to write your comments.</li>
	      <li>Copying /pasting text directly from word doc file should be avoided as that may result in exceeding permissible character length (due to hidden tags).  </li>
	      <li>Instead notepad can be used for this purpose. Or copy word document to notepad first before pasting to above text editor </li>
      	</ul>	
    </aui:row>
	</aui:fieldset-group>
	
</aui:form>
</div>
</div>
</div> 
