<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page
	import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page
	import="com.liferay.document.library.kernel.model.DLFolderConstants"%>
<%@page
	import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="searchMedia.jsp" %> 
<style>
div img {
	display: block;
}

div.show-icon i.fa {
	display: inline-block;
}
.card1, .card-horizontal1 {
    background-color: #fff; 
    border-color: rgba(0, 0, 0, 0.125); 
    border-style: solid;
    border-width: 0px;
    border-radius: 0.25rem;
    box-shadow: 0 1px 1px -1px rgb(0 0 0 / 46%);
    display: block;
    min-width: 0;
    position: relative;
    word-wrap: break-word;
}
</style>
<%
long parentFolderId = 0;
List<DLFolder> folderIDs = (List<DLFolder>) request.getAttribute("folderIDs");
for(DLFolder folderID: folderIDs ){
	parentFolderId = folderID.getParentFolderId();
	}

%>

<portlet:renderURL var="getVersionLinkURL">
	<portlet:param name="mvcRenderCommandName" value="getFolderFile" /><!-- value="searchDownloadDocuments"  value="getFolderFile"-->
	<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/> 
</portlet:renderURL>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet" />
<div class="container dam">

	<div class="row" >
			<c:if test="<%=themeDisplay.isSignedIn()%>">
			<%-- 	<div class="card-header col-md-12 show-icon" align="right"
					style="display: inline-block;">
					<a
						style="color: white; background-color: #ff4f1e; padding: 0.22rem 0.5rem;"
						href="/group<%=siteName%>/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName=%2Fdocument_library%2Fedit_folder&_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect=<%=encodedCurrentUrl + quesryStringSign%>_com_liferay_document_library_web_portlet_DLAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName%3D%252Fdocument_library%252Fview_folder%26p_p_auth%3DlF80sVrN%26_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId%3D38458&_com_liferay_document_library_web_portlet_DLAdminPortlet_portletResource=com_liferay_document_library_web_portlet_DLAdminPortlet&_com_liferay_document_library_web_portlet_DLAdminPortlet_repositoryId=<%= groupId%>&_com_liferay_document_library_web_portlet_DLAdminPortlet_parentFolderId=<%=parentFolderId%>&_com_liferay_document_library_web_portlet_DLAdminPortlet_ignoreRootFolder=true&p_p_auth=lF80sVrN"
						class="btn"><i class="fa fa-plus" style="color: white;"></i>
						Add Folder</a>
				</div> --%>
			</c:if>
				<% for (DLFolder folderID: folderIDs){ %>
					
				<div class="col-md-3 p-2" style="display: inline-block;">
<a
									onClick="commentDetails(<%= folderID.getFolderId() %>)">
					<div class="card oc card-horizontal">
						<div class="card-body">
						<div class="card-row">
						<div class="autofit-col">
							<link data-senna-track="temporary" href="/o/frontend-taglib/css/card.css?browserId=chrome&amp;themeId=dialect_WAR_dialecttheme&amp;languageId=en_US&amp;b=7413&amp;t=1652071346000" rel="stylesheet" type="text/css">
							<span class="sticker sticker-secondary sticker-rounded"><span class="sticker-overlay"><svg class="lexicon-icon lexicon-icon-folder" role="presentation" viewBox="0 0 512 512">
							<use xlink:href="http://localhost:8081/o/dialect-theme/images/clay/icons.svg#folder"></use></svg></span></span>
						</div>
							<div class="autofit-col autofit-col-expand autofit-col-gutters">
				<p class="card-title text-truncate"><a
									onClick="commentDetails(<%= folderID.getFolderId() %>)"><%= folderID.getName() %></a>
						</p>
			</div>
						</div>
					</div>
					</div>
				</a>
				</div>
				<% } %>
			</div>
		</div> 

							<%-- <liferay-ui:search-container cssClass="search-container-height" delta="20" deltaConfigurable="true" 
						emptyResultsMessage="No documents found" >
						<liferay-ui:search-container-results>
						<%
							results = (List<DLFolder>) request.getAttribute("folderIDs");
							total = (int)request.getAttribute("total");
							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
							searchContainer.setTotal(total); 
							searchContainer.setResults(results);
						%>
						</liferay-ui:search-container-results>
						<liferay-ui:search-container-row 
					        className="com.liferay.document.library.kernel.model.DLFolder"
					        keyProperty="fileEntryId" modelVar="downloads" escapedModel="<%=true%>">
				<div class="row col-md-3 p-3" style="display: inline-block;">
				<a onClick="commentDetails(<%= downloads.getFolderId() %>)">
					<div class="card1">
						<div class="card-body col-lg-12"
							style="padding: 10px; align-items: center;">
								<div class="show-icon">
								<h1 class="card-text" style="color: #fccc77;">
									<i class="fa fa-folder" aria-hidden="true"
										style="font-size: 213px;"></i>
								</h1>
							</div>

						</div>
						<div class="card-footer show-icon">
							<h5 class="card-text" style="color: #fccc77;">
								<i class="fa fa-folder fa-lg"></i> &nbsp;<a
									onClick="commentDetails(<%= downloads.getFolderId() %>)"><%= downloads.getName() %></a>
							</h5>
						</div>
					</div>
				</a>
				</div>	   				   
					    </liferay-ui:search-container-row>
					</liferay-ui:search-container> --%>
		
<script>
function commentDetails(folderId){
		 window.location.href="<%= getVersionLinkURL %>&<portlet:namespace />folderId="+folderId;
	 } 
</script>
