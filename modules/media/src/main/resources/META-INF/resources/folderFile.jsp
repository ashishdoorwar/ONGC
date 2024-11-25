<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>
<%@page
	import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileVersion"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page
	import="com.liferay.document.library.kernel.util.PDFProcessorUtil"%>
<%@page import="com.liferay.portal.kernel.model.ResourceConstants"%>
<%@page
	import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page
	import="com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page
	import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
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
<%
String folderName = null;
//List<DLFolder> folders = (List<DLFolder>) request.getAttribute("folders");
//List<DLFileEntry> allFileLink = (List<DLFileEntry>) request.getAttribute("allFileLink");
long folderId = (long) request.getAttribute("folderId");
//System.out.println("folderId=======================>"+folderId);
String backURL = (String) request.getAttribute("backURL");
DLFolder folderObj=DLFolderLocalServiceUtil.getFolder(folderId);
folderName = folderObj.getName();
List<Long> userAllRoles = new ArrayList<Long>();
List<UserGroup> groups = UserGroupLocalServiceUtil.getUserUserGroups(themeDisplay.getUserId());
List<Role> groupRoles = new ArrayList<Role>();

DLFolder dlFolder = DLFolderLocalServiceUtil.getDLFolder(folderId);
//List<Role> userRoles= themeDisplay.getUser().getRoles();

for(UserGroup group:groups) {
	//groupRoles= RoleLocalServiceUtil.getGroupRoles(group.getGroupId());	
	groupRoles.addAll(RoleLocalServiceUtil.getGroupRoles(group.getGroupId()));
	//out.println(group.getName());
}

for(Role userGroupRole:groupRoles){ 
	//out.println("Role Name========>"+userGroupRole.getName());
	userAllRoles.add(userGroupRole.getRoleId()); }
/* out.println(userAllRoles);
for(long userRegRole:userAllRoles){
out.println(RoleLocalServiceUtil.getRole(userRegRole));} */
//out.println(groups+"<br> User Group Roles"+groupRoles);
//List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
//for(Role userRegRole:userRoles){ userAllRoles.add(userRegRole.getRoleId()); }


%>

	
<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcRenderCommandName" value="getFolderFile" />	   
	 <portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
	<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/> 
</liferay-portlet:renderURL>

<div class="container ffile">
	<div class="row">
		<div class="card1 col-md-12">
			<c:if test="<%=themeDisplay.isSignedIn()%>">
				<%
				String singleImageAddUrl = "/group"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName=%2Fdocument_library%2Fedit_file_entry&_com_liferay_document_library_web_portlet_DLAdminPortlet_cmd=add&_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect="+encodedCurrentUrl + quesryStringSign+"_com_liferay_document_library_web_portlet_DLAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName%3D%252Fdocument_library%252Fview_folder%26_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect%3D"+encodedPortalUrl+"%252Fgroup"+encodedSiteName+"%252F~%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_document_library_web_portlet_DLAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D38446%2526p_p_auth%253D32aytWCy%26_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId%3D"+folderId+"%26p_p_auth%3D32aytWCy&_com_liferay_document_library_web_portlet_DLAdminPortlet_portletResource=com_liferay_document_library_web_portlet_DLAdminPortlet&_com_liferay_document_library_web_portlet_DLAdminPortlet_repositoryId="+groupId+"&_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId="+folderId+"&_com_liferay_document_library_web_portlet_DLAdminPortlet_fileEntryTypeId=0&p_p_auth=32aytWCy";
				String multipleImageAddUrl = "/group"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName=%2Fdocument_library%2Fupload_multiple_file_entries&_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect="+encodedCurrentUrl + quesryStringSign+"_com_liferay_document_library_web_portlet_DLAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName%3D%252Fdocument_library%252Fview_folder%26_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect%3D%252Fgroup%252Fdms%252F~%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_document_library_web_portlet_DLAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D43702%2526p_p_auth%253DgKkpHsdg%26_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId%3D"+folderId+"%26p_p_auth%3DgKkpHsdg&_com_liferay_document_library_web_portlet_DLAdminPortlet_portletResource=com_liferay_document_library_web_portlet_DLAdminPortlet&_com_liferay_document_library_web_portlet_DLAdminPortlet_repositoryId="+groupId+"&_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId="+folderId+"&p_p_auth=gKkpHsdg";
				String createFolderAddUrl = "/group"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName=%2Fdocument_library%2Fedit_folder&_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect="+encodedCurrentUrl + quesryStringSign+"_com_liferay_document_library_web_portlet_DLAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName%3D%252Fdocument_library%252Fview_folder%26p_p_auth%3DlF80sVrN%26_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId%3D38458&_com_liferay_document_library_web_portlet_DLAdminPortlet_portletResource=com_liferay_document_library_web_portlet_DLAdminPortlet&_com_liferay_document_library_web_portlet_DLAdminPortlet_repositoryId="+groupId+"&_com_liferay_document_library_web_portlet_DLAdminPortlet_parentFolderId="+folderId+"&_com_liferay_document_library_web_portlet_DLAdminPortlet_ignoreRootFolder=true&p_p_auth=lF80sVrN";
				%>

				<div class="card-header col-md-12 show-icon" align="right">
						<div class="col-md-5 show-icon" align="left" style="display: inline-block;"><i class="fa fa-home "></i> <i class="fa fa-caret-right" aria-hidden="true"></i> <a class=""><%= dlFolder.getName() %></a></div>
				<div class="col-md-6 show-icon" align="right" style="display: inline-block;">
					
					<% 
					 boolean hasFolderPermission = false;
		            final String actionsFilter = ActionKeys.ADD_DOCUMENT;
		            for(long userRegRole:userAllRoles){
		            	//out.println("Role ID"+userRegRole); 
		            	//out.println(ResourcePermissionLocalServiceUtil.hasResourcePermission(themeDisplay.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(folderId), userRegRole, actionsFilter));
		            final boolean hasPermission = ResourcePermissionLocalServiceUtil.hasResourcePermission(themeDisplay.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(folderId), userRegRole, actionsFilter);
					if (hasPermission) {
	                    hasFolderPermission = true;
	                    %>
					<a class="btn dropdown-toggle btn-primary"
						style="padding: 0.22rem 0.5rem;"
						href="#" role="button" id="dropdownMenuLink"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fa fa-plus" style="color: white;"></i>
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item" href="<%=singleImageAddUrl%>"> <svg
								class="lexicon-icon lexicon-icon-upload" focusable="false"
								role="presentation">
								<use
									xlink:href="<%=portalUrl %>/o/admin-theme/images/clay/icons.svg#upload"></use></svg>&nbsp;&nbsp;File
							Upload
						</a> <a class="dropdown-item" href="<%=multipleImageAddUrl%>"> <svg
								class="lexicon-icon lexicon-icon-upload-multiple"
								focusable="false" role="presentation">
								<use
									xlink:href="<%=portalUrl %>/o/admin-theme/images/clay/icons.svg#upload-multiple"></use></svg>&nbsp;&nbsp;Multiple
							Files Upload
						</a>
						<%--  <% if(!folderName.equalsIgnoreCase("Public")){	%> 
				     <a class="dropdown-item" href="<%=createFolderAddUrl%>">
				    	<svg class="lexicon-icon lexicon-icon-folder" focusable="false" role="presentation"><use xlink:href="<%=portalUrl %>/o/admin-theme/images/clay/icons.svg#folder"></use></svg>&nbsp;&nbsp;Folder
				    </a>
				    <% } %>  --%>
						<%--  <% if(!folderName.equalsIgnoreCase("Public")){	%> 
					<a style="color: white; background-color: #ff4f1e; padding: 0.22rem 0.5rem;"
						href="/group<%=siteName%>/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName=%2Fdocument_library%2Fedit_folder&_com_liferay_document_library_web_portlet_DLAdminPortlet_redirect=<%=encodedCurrentUrl + quesryStringSign%>_com_liferay_document_library_web_portlet_DLAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_document_library_web_portlet_DLAdminPortlet_mvcRenderCommandName%3D%252Fdocument_library%252Fview_folder%26p_p_auth%3DlF80sVrN%26_com_liferay_document_library_web_portlet_DLAdminPortlet_folderId%3D38458&_com_liferay_document_library_web_portlet_DLAdminPortlet_portletResource=com_liferay_document_library_web_portlet_DLAdminPortlet&_com_liferay_document_library_web_portlet_DLAdminPortlet_repositoryId=<%= groupId%>&_com_liferay_document_library_web_portlet_DLAdminPortlet_parentFolderId=<%=folderId%>&_com_liferay_document_library_web_portlet_DLAdminPortlet_ignoreRootFolder=true&p_p_auth=lF80sVrN"
						class="btn"><i class="fa fa-plus" style="color: white;"></i>
						Add Folder</a>
					 <% } %>  --%>
					</div>
					<%  break;
					} else {
	                    hasFolderPermission = false;
	                   // break;
	                }
		            }
		            %>
					<a href=<%=backURL %> class="btn dropdown-toggle btn-primary"
						style="padding: 0.22rem 0.5rem;"> <i
						class="fa fa-arrow-left" style="color: white;"></i>
					</a>
				</div>
				</div>
			</c:if>
			<%-- <div class="card-body">
				<% for (DLFolder folder: folders){%>
				<div class="row col-md-3 p-3" style="display: inline-block;">

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
									onClick="commentDetails(<%= folder.getFolderId() %>)"><%= folder.getName() %></a>
							</h5>
						</div>
					</div>
				</div>
				<% } %>
			</div> --%>
			<hr>
			<div class="row">
			<liferay-ui:search-container cssClass="search-container-height" delta="20" deltaConfigurable="true" 
						emptyResultsMessage="No documents found" iteratorURL="<%=iteratorURL%>">
						<liferay-ui:search-container-results>
						<%
							results = (List<DLFolder>) request.getAttribute("results");
							total = (int)request.getAttribute("total");
							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
							searchContainer.setTotal(total); 
							searchContainer.setResults(results);
						%>
						</liferay-ui:search-container-results>
						<liferay-ui:search-container-row className="com.liferay.document.library.kernel.model.DLFileEntry"
					        keyProperty="fileEntryId" modelVar="fileLink" escapedModel="<%=true%>">
					        
					          
					       <% if(fileLink.getExtension().equalsIgnoreCase("mp4") || fileLink.getExtension().equalsIgnoreCase("wmv") || fileLink.getExtension().equalsIgnoreCase("mkv")){ %>
				<div class="col-md-3">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon">
							<a class=""
								href='javascript:videoPopup("<%= fileLink.getRepositoryId()%>","<%= fileLink.getFolderId()%>","<%= fileLink.getFileName()%>")'>
								<%-- <video id="videoPreview" width="100%" height="100%" controls poster="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1">
									<source src="" type="video/mp4">
								</video>  --%> <img 
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1"
								alt="<%= fileLink.getFileName()%>">
								<div id='play' class='idle'></div>
							</a>
						</div>
					</div>
				</div>
				<%}	else if(fileLink.getExtension().equalsIgnoreCase("docx") || fileLink.getExtension().equalsIgnoreCase("xlsx") || fileLink.getExtension().equalsIgnoreCase("csv") || fileLink.getExtension().equalsIgnoreCase("doc")|| fileLink.getExtension().equalsIgnoreCase("ppt") || fileLink.getExtension().equalsIgnoreCase("txt")){ %>
				<div class="col-md-3">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon"
							align="center">
							<a href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>" download title="<%= fileLink.getFileName()%>">
								<% if(fileLink.getExtension().equalsIgnoreCase("docx") || fileLink.getExtension().equalsIgnoreCase("doc")){ %>
								 <i class="fa fa-file-word-o fa-4" aria-hidden="true"></i>
								<%} else if(fileLink.getExtension().equalsIgnoreCase("xlsx") || fileLink.getExtension().equalsIgnoreCase("csv")) {%>
								<i class="fa fa-file-excel-o fa-4" aria-hidden="true"></i>
								<%} else if(fileLink.getExtension().equalsIgnoreCase("ppt")){ %>
								<i class="fa fa-file-powerpoint-o fa-4" aria-hidden="true"></i>
								<%} else if(fileLink.getExtension().equalsIgnoreCase("txt")){ %>
								<i class="fa fa-file-text fa-4" aria-hidden="true"></i>
								<%} else{ %>
								<i class="fa fa-file-o fa-4" aria-hidden="true"></i>
								<%} %>
							</a>
						</div>
					</div>
				</div>
				<% } else if(fileLink.getExtension().equalsIgnoreCase("pdf")){ %>
				<div class="col-md-3">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon"
							align="center" <%-- onclick="javascript:href(<%= fileLink.getRepositoryId() %>,<%= fileLink.getFolderId()%>,'<%= fileLink.getFileName()%>','<%= fileLink.getUuid()%>');" --%>>
							<a id="pdf" href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>" title="<%= fileLink.getFileName()%>">
								<i class="fa fa-file-pdf-o fa-4" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
				<% 	} else if(fileLink.getExtension().equalsIgnoreCase("jpg") || fileLink.getExtension().equalsIgnoreCase("jpeg") || fileLink.getExtension().equalsIgnoreCase("png")){%>
				<div class="col-md-3">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon"
							>
								<a href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>">
							<img class="img-fluid zoom"
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>"
								alt="<%= fileLink.getFileName()%>" >
								</a>
						</div>
					</div>
				</div>
				<%} else {  %>
				<div class="col-md-3">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon"
							>
						<a href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>">
							<i class="fa fa-file fa-4" aria-hidden="true"></i>
								</a>
						</div>
					</div>
				</div>
 				   <% } %>
 				     </liferay-ui:search-container-row>
					    <div class="col-lg-12">
					    <liferay-ui:search-iterator markupView="lexicon" searchContainer="<%= searchContainer %>" /></div>
					</liferay-ui:search-container>
					        
			<%-- <div class="card-body">
				<% for (DLFileEntry fileLink: allFileLink){ 
					//out.println(fileLink.getExtension()+",");
					if(fileLink.getExtension().equalsIgnoreCase("mp4")){
				%>
				<div class="col-md-3">
					<div class="card">
						<div class="card-body col-lg-12 show-icon"
							>
							<a class=""
								href='javascript:videoPopup("<%= fileLink.getRepositoryId()%>","<%= fileLink.getFolderId()%>","<%= fileLink.getFileName()%>")'>
								<video id="videoPreview" width="100%" height="100%" controls poster="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1">
									<source src="" type="video/mp4">
								</video>  <img class="img-fluid img-responsive"
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1"
								alt="<%= fileLink.getFileName()%>">
								<div id='play' class='idle'></div>
							</a>
						</div>
					</div>
				</div>
				<%}	else if(fileLink.getExtension().equalsIgnoreCase("docx") || fileLink.getExtension().equalsIgnoreCase("xlsx")){ %>
				<div class="col-md-3">
					<div class="card">
						<div class="card-body col-lg-12 show-icon"
							align="center">
							<a href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>" download>
								<% if(fileLink.getExtension().equalsIgnoreCase("docx")){ %>
								 <img class="img-fluid " src="<%= request.getContextPath() %>/images/word.png"
								alt="<%= fileLink.getFileName()%>">
								<%} else if(fileLink.getExtension().equalsIgnoreCase("xlsx")) {%>
								<img class="img-fluid " src="<%= request.getContextPath() %>/images/excel.png"
								alt="<%= fileLink.getFileName()%>">
								<%} else{ %>
								<img class="img-fluid " src="<%= request.getContextPath() %>/images/file.png"
								alt="<%= fileLink.getFileName()%>">
								<%} %>
							</a>
						</div>
					</div>
				</div>
				<% } else if(fileLink.getExtension().equalsIgnoreCase("pdf")){ %>
				<div class="col-md-3">
					<div class="card">
						<div class="card-body col-lg-12 show-icon"
							align="center" onclick="javascript:href(<%= fileLink.getRepositoryId() %>,<%= fileLink.getFolderId()%>,'<%= fileLink.getFileName()%>','<%= fileLink.getUuid()%>');">
							<a id="pdf" href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>">
								<img
								class="img-fluid img-responsive" src="<%= request.getContextPath() %>/images/pdf.png"
								alt="<%= fileLink.getFileName()%>"> 
							</a>
						</div>
					</div>
				</div>
				<% 	} else {%>
				<div class="col-md-3">
					<div class="card">
						<div class="card-body col-lg-12 show-icon"
							>
								<a 
								href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>">
							<img class="img-fluid zoom"
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>"
								alt="<%= fileLink.getFileName()%>" >
								</a>
								
						</div>
					</div>
				</div>
				<%} 
					} %>
			</div> --%>
			</div>
		</div>
	</div>
</div>
<div id="videoModal" class="upload-registration " tabindex="-1"
	role="dialog">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		role="document" style="max-width: 100%;">
		<div class="modal-content">
			<div>
				<button type="button" class="close text-white" data-dismiss="modal"
					aria-label="Close">
					<span class="close">&times;</span>
				</button>
			</div>
			<aui:input type="hidden" name="repositoryId" id="repositoryId"></aui:input>
			<aui:input type="hidden" name="folderId" id="folderId"></aui:input>
			<aui:input type="hidden" name="title" id="title"></aui:input>
			<video id="videoPreview" width="100%" height="100%" controls
				poster="">
				<source type="video/mp4">
			</video>
		</div>
	</div>
</div>
<script>
<%-- function commentDetails(folderId){
		 window.location.href="<%= getVersionLinkURL %>&<portlet:namespace />folderId="+folderId;
	 }  --%>

	$(document).ready(function() {
		var modalV = document.getElementById("videoModal");
		var span = document.getElementsByClassName("close")[0];

		span.onclick = function() {
			modalV.style.display = "none";
			var video = document.getElementById('videoPreview');
			video.pause();
		}

		window.onclick = function(event) {
			if (event.target == modalV) {
				modalV.style.display = "none";
				var video = document.getElementById('videoPreview');
				video.pause();
			}
		}
	});

	function videoPopup(repositoryId, folderId, title) {
		document.getElementById("<portlet:namespace />repositoryId").value = repositoryId;
		document.getElementById("<portlet:namespace />folderId").value = folderId;
		document.getElementById("<portlet:namespace />title").value = title;
		var video = document.getElementById('videoPreview');
		video.src = '/documents/' + repositoryId + "/" + folderId + "/" + title;
		video.play();
		//video.pause();
		var modalV = document.getElementById("videoModal");
		modalV.style.display = "block";
	}
	
	function href(repositoryId,folderId,fileName,uuid){
	    var newUrl = "/documents/"+repositoryId+"/"+folderId+"/"+fileName+"/"+uuid;
	    window.location.href($("#pdf").attr("href", newUrl));
	}
</script>