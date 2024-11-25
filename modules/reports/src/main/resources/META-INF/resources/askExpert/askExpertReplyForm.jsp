<%@ include file="/init.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-md-12">
		<% 
		AskExpert askExpert = (AskExpert) request.getAttribute("viewAskExpert");
		String backURL = (String) request.getAttribute("backURL");
		%>
		 <portlet:actionURL var="saveAskExpertReply" name="saveAskExpertComment" ></portlet:actionURL>
		<div  class="table-responsive mt5" id="expertList"></div>
			<div id="form">
				<aui:form action="<%=saveAskExpertReply %>" theme="simple" method="post" >
					<div class="table-responsive">
					<h2 class="h2 heading-clr">Comment </h2>
						<table class="table table-bordered table-striped">
						    <tr><td><strong><label for="domain">Domain</label></strong></td>
						    	<td colspan="3"><aui:input type="hidden" name="queryId" value="<%= askExpert.getQueryid() %>"/>
						    					<%= askExpert.getDomainName() %></td></tr>
						   	<tr><td><strong><label for="message">Message</label></strong></td><td colspan="3"><%= askExpert.getMessage() %></td></tr>    
							<tr>
						     <td><strong><label for="message">Post Comment</label></strong><span class="mand">*</span></td>
						     <td colspan="3">
						     
							    <liferay-ui:input-editor name="comment" initMethod="initEditor" width="100" height="400" resizable="true" toolbarSet="liferay-article">
									<aui:validator name="required" />
								</liferay-ui:input-editor>
								<small><strong>NOTE:</strong> This forum is moderated. Please use the facility judiciously to realize collaborative benefits for overall progress of the  organisation and the employees.</small>
						     </td>
							</tr>      
						    <tr>
						     <td colspan="4" class="text-center">
						     <aui:button-row cssClass="text-center mt-4">
							   	<aui:button name="submitButton" type="submit" value="Submit" cssClass="btn btn-primary btn-sm" />
								<a onclick="reset()" class="btn btn-primary btn-sm" >Reset</a>
								<a href="<%= backURL %>" class="btn btn-primary btn-sm">Back</a>
								</aui:button-row>
						    </tr>
						    <tr>
						      <td colspan="4"> <p class="red_note"><strong>Note</strong></p>
							      <ul class="red_note">       
								  	<li>Please use above text editor to write your comments..</li>
							      	<li>Copying /pasting text directly from word doc file should be avoided as that may result in exceeding permissible character length (due to hidden tags).  </li>
							      	<li>Instead notepad can be used for this purpose. Or copy word document to notepad first before pasting to above text editor </li>
							      </ul>
						      </td>
						    </tr>
						</table>
					</div>
				</aui:form>
			</div>
		</div>
	</div>
</div>