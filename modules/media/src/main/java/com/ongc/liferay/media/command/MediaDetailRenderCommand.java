package com.ongc.liferay.media.command;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.util.comparator.RepositoryModelCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.media.constants.MediaPortletKeys;
import com.ongc.liferay.media.util.MediaUtil;
import com.ongc.liferay.mediatags.model.Tag;
import com.ongc.liferay.mediatags.service.TagLocalServiceUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ MediaPortletKeys.MEDIAPORTLET,
		 "mvc.command.name=getFolderFile"
		 }, service = MVCRenderCommand.class
		 )
public class MediaDetailRenderCommand implements MVCRenderCommand{

	public final static String renderingFolderFilePagePath = "/folderFile.jsp";

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long folderId = ParamUtil.getLong(renderRequest, "folderId");
		String backURL = ParamUtil.getString(renderRequest, "backURL");
//		List<DLFolder> folders = DLFolderLocalServiceUtil.getFolders(themeDisplay.getLayout().getGroupId(), folderId);
//		List<DLFileEntry> allFileLink = getAllFileLink(themeDisplay.getLayout().getGroupId(), folderId);
//		renderRequest.setAttribute("folders", folders);
//		renderRequest.setAttribute("allFileLink", allFileLink);
//		renderRequest.setAttribute("total", allFileLink.size());
		renderRequest.setAttribute("backURL", backURL);			
		renderRequest.setAttribute("folderId", folderId);

		
		List<DLFolder> folders = null;
		DLFolder dlFolder;
		List<Tag> tags = null;
		try {
			dlFolder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getLayout().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Downloads");
			folders = MediaUtil.getChildFolders(themeDisplay.getLayout().getGroupId(),dlFolder.getFolderId());
			tags = TagLocalServiceUtil.getTags(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("folderIDs", folders);
		renderRequest.setAttribute("currentSiteTags", tags);
		
		
		
		LinkedHashMap<String, String> searchParam = getSearchParam(renderRequest);
		PortletURL iteratorUrl = renderResponse.createRenderURL();
		SearchContainer<DLFileEntry> searchContainer = new SearchContainer<DLFileEntry>(renderRequest,iteratorUrl, null, "empty-items");
		List<Long> fileEntryIds = MediaUtil.getFolderFileEntryIds(themeDisplay.getLayout().getGroupId(), folderId);
		if(ListUtil.isNotEmpty(fileEntryIds)) {
			List<DLFileEntry> results = MediaUtil.getDLFileEntriesByTagName(fileEntryIds,searchContainer.getStart(), searchContainer.getEnd(),searchParam);
			int total = MediaUtil.getDLFileEntriesCountByTagName(fileEntryIds,searchContainer.getStart(), searchContainer.getEnd(),searchParam);
			renderRequest.setAttribute("results", results);
			renderRequest.setAttribute("total", total);	
			renderRequest.setAttribute("folderId", folderId);
		}else {
			renderRequest.setAttribute("results", new ArrayList<DLFileEntry>());
			renderRequest.setAttribute("total", 0);	
			renderRequest.setAttribute("folderId", folderId);
		}
		
		return renderingFolderFilePagePath;
	}
	
	private LinkedHashMap<String, String> getSearchParam(RenderRequest renderRequest) {
		LinkedHashMap<String, String> searchParam = new LinkedHashMap<String, String>();
		searchParam.put("status", "0");
		String folderId = ParamUtil.getString(renderRequest, "folderId");

		if (folderId != null && folderId != "" && !folderId.equals("-1")) {
			searchParam.put("folderId", folderId);
		}
		return searchParam;
	}
	
	
//	private List<DLFileEntry> getAllFileLink(long groupId, long folderId) {
//		List<DLFileEntry> fileEntries = new ArrayList<>();
//		try {
//			fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, folderId, 0, QueryUtil.ALL_POS,
//					QueryUtil.ALL_POS, new RepositoryModelCreateDateComparator<DLFileEntry>(false));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fileEntries;
//
//	}
	
}
