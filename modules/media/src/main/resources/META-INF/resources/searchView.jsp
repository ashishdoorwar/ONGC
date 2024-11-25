     <%@ include file="searchMedia.jsp" %>
     <% 
     String backURL = (String) request.getAttribute("backURL"); %>
<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcRenderCommandName" value="searchDownloadDocuments" />
	<%
	LinkedHashMap<String, String> searchParam = (LinkedHashMap<String, String>)request.getAttribute("searchParam");
	for(Map.Entry<String, String> entry : searchParam.entrySet()) {
	%>
	<portlet:param name="<%=entry.getKey() %>" value="<%=entry.getValue() %>" />
	<%
	}
	%>
	
</liferay-portlet:renderURL>
<portlet:renderURL var="getVersionLinkURL">
	<portlet:param name="mvcRenderCommandName" value="getFolderFile" />
	<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>" />
</portlet:renderURL>
<div class="container ffile">
<div class="row">
<div class="card-header col-md-12 show-icon" align="right"
					style="display: inline-block;">
<a href=<%=backURL %> class="btn dropdown-toggle btn-primary"
						style="padding: 0.22rem 0.5rem;"> <i
						class="fa fa-arrow-left" style="color: white;"></i>
					</a>
				</div>
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
				<div class="row col-md-3 p-3" style="display: inline-block;">
					<div class="card oc">
						<div class="card-body col-lg-12 show-icon"
							style="padding: 6px; align-items: center;">
							<a class=""
								href='javascript:videoPopup("<%= fileLink.getRepositoryId()%>","<%= fileLink.getFolderId()%>","<%= fileLink.getFileName()%>")'>
								<%-- <video id="videoPreview" width="100%" height="100%" controls poster="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1">
									<source src="" type="video/mp4">
								</video>  --%> <img class="ml-1 mr-4" style="max-height: 150px;max-width: 254px;"
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>?videoThumbnail=1"
								alt="<%= fileLink.getFileName()%>">
								<div id='play' class='idle'></div>
							</a>
						</div>
					</div>
				</div>
				<%}	else if(fileLink.getExtension().equalsIgnoreCase("docx") || fileLink.getExtension().equalsIgnoreCase("xlsx") || fileLink.getExtension().equalsIgnoreCase("csv") || fileLink.getExtension().equalsIgnoreCase("doc")|| fileLink.getExtension().equalsIgnoreCase("ppt") || fileLink.getExtension().equalsIgnoreCase("txt")){ %>
				<div class="row col-md-3 p-3" style="display: inline-block;">
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
				<div class="row col-md-3 p-3" style="display: inline-block;">
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
				<div class="row col-md-3 p-3" style="display: inline-block;">
					<div class="card oc" style="max-width: 252px;">
						<div class="card-body col-lg-12 show-icon"
							style="padding: 10px; align-items: center;max-height: 150px;max-width: 254px;">
								<a href="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>">
							<img class="img-fluid zoom" style="max-height: 150px;max-width: 232px;"
								src="/documents/<%= fileLink.getRepositoryId() %>/<%= fileLink.getFolderId()%>/<%= fileLink.getFileName()%>/<%= fileLink.getUuid()%>"
								alt="<%= fileLink.getFileName()%>" >
								</a>
						</div>
					</div>
				</div>
				<%} else {  %>
				<div class="row col-md-3 p-3" style="display: inline-block;">
					<div class="card oc" style="max-width: 252px;">
						<div class="card-body col-lg-12 show-icon"
							style="padding: 10px; align-items: center;max-height: 150px;max-width: 254px;">
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
function commentDetails(folderId){
		 window.location.href="<%= getVersionLinkURL %>&<portlet:namespace />folderId="+folderId;
	 } 

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