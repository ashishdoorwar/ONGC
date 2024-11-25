
<%@ include file="/init.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">

			<div class="table-responsive mt5" id="expertList"></div>

			<portlet:actionURL var="saveAskExpert" name="feedbackPostCommentForm"></portlet:actionURL>
			<%
				AskExpertService askExpertService = new AskExpertServiceImpl();
				List<Domain> domainList = askExpertService.getDomainList();
			%>

			<div id="form">
				<h2 class="h2 heading-clr mt0">Query</h2>
				<aui:form action="<%=saveAskExpert%>" theme="simple" method="post"
					id="">
					<div class="table-responsive">
						<table class="table table-bordered table-striped">
							<tr>
								<td><strong><label for="domain">Domain</label></strong></td>
								<td colspan="3"><aui:select name="domain"
										cssClass="smalltext form-control" label="" required="true">
										<aui:option value="">Select</aui:option>
										<%
											for (Domain domain : domainList) {
										%>
										<aui:option value="<%=domain.getDomainId()%>"><%=domain.getDomainName()%></aui:option>
										<%
											}
										%>
									</aui:select></td>
							</tr>
							<tr>
								<td><strong><label for="message">Message</label></strong><span
									class="mand">*</span></td>
								<td colspan="3"><liferay-ui:input-editor name="message"
										initMethod="initEditor" width="100" height="400"
										resizable="true" toolbarSet="liferay-article">
										<aui:validator name="required" />
									</liferay-ui:input-editor> <small><strong>NOTE:</strong> This forum is moderated.
										Please use the facility judiciously to realize collaborative
										benefits for overall progress of the organisation and the
										employees.</small></td>
							</tr>
							<tr>
								<td><label for="authResp"><strong>Priority</strong></label></td>
								<td><aui:input name="priority" type="radio" label="Urgent"
										value="urgent" /> <aui:input name="priority" type="radio"
										label="Normal" value="normal" checked="true" /></td>
							</tr>
							<tr>
								<td colspan="4" class="text-center"><aui:button
										name="submitButton" type="submit" value="Submit"
										cssClass="btn btn-primary btn-sm" /> <aui:button
										name="resetButton" type="button" onClick="this.form.reset()"
										value="Reset" cssClass="btn btn-primary btn-sm" /> <a
									onclick="javascript:history.go(-1)"
									class="btn btn-primary btn-sm">Back</a>
							</tr>
							<tr>
								<td colspan="4">
									<p class="red_note">
										<strong>Note</strong>
									</p>
									<ul class="red_note">
										<li>Please use above text editor to write your comments..</li>
										<li>Copying /pasting text directly from word doc file
											should be avoided as that may result in exceeding permissible
											character length (due to hidden tags).</li>
										<li>Instead notepad can be used for this purpose. Or copy
											word document to notepad first before pasting to above text
											editor</li>
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