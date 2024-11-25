<%@page import="com.liferay.asset.kernel.service.AssetTagLocalServiceUtil"%>
<%@page import="com.ongc.liferay.mediatags.model.Tag"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.ongc.liferay.media.constants.MediaPortletKeys"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
    <script type="text/javascript">
    $("#resetButton").click(function () {
    	$("#<portlet:namespace/>category").val('-1');
    	$("#<portlet:namespace/>folderName").val('-1');
    	$("#<portlet:namespace/>tagId").val('-1');
    	$("#<portlet:namespace/>duration").val('-1');
    	$("#<portlet:namespace/>keyword").val('');
       
    });
</script>
<%
TreeMap<Long,String> folderData = new TreeMap<Long,String>(); 
List<DLFolder> folders = (List<DLFolder>) request.getAttribute("folderIDs");
List<Tag> currentSiteTags = (List<Tag>) request.getAttribute("currentSiteTags");
for(DLFolder folder: folders){
	folderData.put(folder.getFolderId(), folder.getName());
}
List<Long> folderIds = new ArrayList<>(folderData.keySet());
Collections.sort(folderIds);
%>
<portlet:defineObjects />

<portlet:renderURL var="searchDocumentURL">
	<portlet:param name="mvcRenderCommandName" value="searchDownloadDocuments" />
	<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/> 
	<portlet:param name="performSearch" value="true" />
</portlet:renderURL>
<div class="container formWrapper">
	<div class="row">
		<div class="col-lg-12">
			<portlet:renderURL var="searchDocuments">
				<portlet:param name="mvcRenderCommandName"
					value="searchDocuments" />
			</portlet:renderURL>
			<aui:form  method="post" action="<%= searchDocumentURL %>" name="myForm" > 
				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset label="Search Documents">
						<aui:row>
							<aui:col width="25">
								<aui:select label="Select Type" name="folderName">
									<aui:option value="-1">Select</aui:option>
									<% //for(Map.Entry m:folderData.entrySet()){ 
									for(long v:folderIds){
									%>
									<aui:option value="<%= v %>"><%= folderData.get(v) %></aui:option>
									<%} %>
								</aui:select>
							</aui:col>
							
							<aui:col width="25">
								<aui:select label="Select Tags" name="tagId" ><!-- multiple="true" -->
									<aui:option value="-1">Select</aui:option>
									<% for(Tag currentSiteTag:currentSiteTags){
										//AssetTagLocalServiceUtil.getAssetEntryPrimaryKeys(currentSiteTag.getTagId());
										%>
									<aui:option value="<%= currentSiteTag.getTagId() %>"><%= currentSiteTag.getTagName() %></aui:option>
									<% } %>
								</aui:select>
							</aui:col>
							
							<aui:col width="20">
								<aui:select class="form-control" label="Duration"
									name="duration" id="duration">
									<aui:option value="-1">All</aui:option>
									<aui:option value="7">Last Week</aui:option>
									<aui:option value="90">Last 3 Months</aui:option>
									<aui:option value="180">Last 6 Months</aui:option>
									<aui:option value="365">Last 1 Year</aui:option>
									<aui:option value="730">Last 2 Years</aui:option>
									<aui:option value="1825">Last 5 Years</aui:option>
									<aui:option value="3650">Last 10 Years</aui:option>
								</aui:select>
							</aui:col>
						
							<aui:col width="20">
								<aui:input label="Keywords" name="keyword" type="text" placeholder="Search..." size="30"/>
							</aui:col>
							
							<aui:col width="20">
						<div id="searchButton" class="py-4" style="">
						<aui:button-row>
							<button name="submitButton" type="submit" 
								class="btn btn-primary mt-1" onclick="checkParameter()" ><i class="fa fa-search" aria-hidden="true" ></i></button>
								<button name="resetButton" type="button" onclick="reset();" class="btn btn-primary mt-1"><i class="fa fa-refresh" aria-hidden="true"></i></button>
						</aui:button-row>
						</div>
						</aui:col>
						</aui:row>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</div>
	</div>
</div>