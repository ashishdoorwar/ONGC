<%@ include file="/init.jsp"%>
<%@ include file="/init.jsp"%>
<style>
#myNavbar .navbar-nav {
	float: left !important
}

.wpthemeHiddenPlusControlHeaderParent {
	display: none;
}
</style>
 <portlet:actionURL var="insertQues" name="insertQuestions" ></portlet:actionURL>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<%@ include file="/admin/adminMenu.jsp"%>
		</div>
		
 <liferay-ui:error key="error" message="Illegal argument found" /> 
			<aui:form action="<%= insertQues %>" method="POST"
				cssClass="form-horizontal">
				<aui:fieldset-group markupView="lexicon">
				<!-- 	<aui:fieldset label="Add Questions"> -->
						<aui:row>
							<aui:col width="100">
								<aui:input label="Question" type="textarea" name="qdesc"
									cols="100" rows="10" class="form-control">
									<aui:validator name="required" />
								</aui:input>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="100">
								<aui:input label="Option 1" type="textarea" cols="70"
									cssClass="form-control" name="option1"></aui:input>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="100">
								<aui:input type="textarea" label="Option 2" cols="70"
									cssClass="form-control" name="option2"></aui:input>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="100">
								<aui:input type="textarea" label="Option 3" cols="70"
									cssClass="form-control" name="option3"></aui:input>
							</aui:col>
						</aui:row>
						<aui:row>
							<aui:col width="100">
								<aui:input type="textarea" label="Option 4" cols="70"
									cssClass="form-control" name="option4"></aui:input>
							</aui:col>
						</aui:row>
						<aui:button-row style="float:right;">
							<aui:button name="submitButton" type="submit" value="Submit Question"
								style="background-color: #8c000d;color: #fff !important;" />
						</aui:button-row>
					</aui:fieldset>
				</aui:fieldset-group>

			</aui:form>

		</div>
	</div>
