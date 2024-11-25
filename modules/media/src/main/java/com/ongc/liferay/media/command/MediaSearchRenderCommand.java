package com.ongc.liferay.media.command;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.media.constants.MediaPortletKeys;
import com.ongc.liferay.media.listner.Downloads;
import com.ongc.liferay.media.util.MediaUtil;
import com.ongc.liferay.mediatags.model.Tag;
import com.ongc.liferay.mediatags.service.TagLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ MediaPortletKeys.MEDIAPORTLET,
		 "mvc.command.name=searchDownloadDocuments"
		 }, service = MVCRenderCommand.class
		 )
public class MediaSearchRenderCommand implements MVCRenderCommand{

	public final static String renderingDownloadePagePath = "/searchView.jsp";

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<Tag> tags = null;
		DLFolder dlFolder = null;
		List<DLFolder> folders = null;
		Group group;
//		 for (int i = 0; i < AssetTagLocalServiceUtil.getAssetEntryPrimaryKeys(65006).length; i++) {
//			long l = AssetTagLocalServiceUtil.getAssetEntryPrimaryKeys(67043)[i];
//			System.out.println("assetEntryPrimaryKeys========================>"+AssetTagLocalServiceUtil.getAssetEntryPrimaryKeys(67043)[i]);
//		}
		try {
			dlFolder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getLayout().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Downloads");
			folders = MediaUtil.getChildFolders(themeDisplay.getLayout().getGroupId(),dlFolder.getFolderId());
			group = GroupLocalServiceUtil.getGroup(themeDisplay.getLayout().getGroupId());
			tags = TagLocalServiceUtil.getTags(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("currentSiteTags", tags);
		renderRequest.setAttribute("folderIDs", folders);
		String backURL = ParamUtil.getString(renderRequest, "backURL");
		renderRequest.setAttribute("backURL", backURL);	
		LinkedHashMap<String, String> searchParam = getSearchParam(renderRequest);
		PortletURL iteratorUrl = renderResponse.createRenderURL();
		SearchContainer<DLFileEntry> searchContainer = new SearchContainer<DLFileEntry>(renderRequest,iteratorUrl, null, "empty-items");
		//System.out.println("searchContainer.getStart()================>"+searchContainer.getStart() +"<br>searchContainer.getEnd()================>"+ searchContainer.getEnd());
		List<Long> fileEntryIds = MediaUtil.getFileEntryIds(themeDisplay.getLayout().getGroupId(), dlFolder.getFolderId());
		if(ListUtil.isNotEmpty(fileEntryIds)) {
			List<DLFileEntry> results = MediaUtil.getDLFileEntriesByTagName(fileEntryIds,searchContainer.getStart(), searchContainer.getEnd(),searchParam);
			int total = MediaUtil.getDLFileEntriesCountByTagName(fileEntryIds,searchContainer.getStart(), searchContainer.getEnd(),searchParam);
			renderRequest.setAttribute("results", results);
			renderRequest.setAttribute("total", total);
		}else {
			renderRequest.setAttribute("results", new ArrayList<DLFileEntry>());
			renderRequest.setAttribute("total", 0);
		}
		
		renderRequest.setAttribute("searchParam", searchParam);
		
		return renderingDownloadePagePath;
	}
	
	private LinkedHashMap<String, String> getSearchParam(RenderRequest renderRequest) {
		LinkedHashMap<String, String> searchParam = new LinkedHashMap<String, String>();
		searchParam.put("status", "0");
		String folderId = ParamUtil.getString(renderRequest, "folderName");
		String tagId = ParamUtil.getString(renderRequest, "tagId");
		String duration = ParamUtil.getString(renderRequest, "duration");
		String keyword = ParamUtil.getString(renderRequest, "keyword");

		if (folderId != null && folderId != "" && !folderId.equals("-1")) {
			searchParam.put("folderId", folderId);
		}

		if (tagId != null && tagId != "" && !tagId.equals("-1")) {
			searchParam.put("tagId", tagId);
		} 
		
		if (duration != null && duration != "" && !duration.equals("-1")) {
			searchParam.put("duration", duration);
		}else {
			searchParam.put("duration", "365");
		}
		
		if (keyword != null && !keyword.equals("")) {
			searchParam.put("keyword", keyword);
		}
		return searchParam;
	}
	
	
	
//	@Override
//	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
//		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		List<DLFileEntry> fileEntries = new ArrayList<DLFileEntry>();
//		String folderId = ParamUtil.getString(renderRequest, "folderName");
//		String tagId = ParamUtil.getString(renderRequest, "tagId");
//		String duration = ParamUtil.getString(renderRequest, "duration");
//		String keyword = ParamUtil.getString(renderRequest, "keyword");
//		System.out.println(themeDisplay.getScopeGroup().getGroupKey());
//		List<AssetTag> tags = AssetTagLocalServiceUtil.getTags();
//		List<Long> fileEntryIds = getFileEntryIds(themeDisplay.getLayout().getGroupId(),tags);
//	System.out.println(fileEntryIds);
//		
//		if (folderId != null && folderId != "" && !folderId.equals("-1")) {
//			System.out.println("Inside FolderIdd"+folderId+"ThemeDisplay========="+themeDisplay.getLayout().getGroupId());
//			fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(themeDisplay.getLayout().getGroupId(), Long.parseLong(folderId));
//			for(DLFileEntry fileEntry:fileEntries) {
//				if(fileEntry.getStatus()==0) {
//				System.out.println("By Folder ID==========>"+fileEntry.getExtension());}}
//		}
//		
//		if (tagId != null && tagId != "" && !tagId.equals("-1")) {
//			fileEntries = getDLFileEntriesByTagName(themeDisplay.getLayout().getGroupId(),tagId);
//			for(DLFileEntry dlFileEntrieByTagName:fileEntries) {
//				System.out.println("By Tag Name==========>"+dlFileEntrieByTagName.getTitle());
//			}
//		}
//
//		//System.out.println("folderName=====>"+folderId+"<br>tagId==========>"+tagId+"<br>duration========>"+duration+"<br>keyword===========>"+keyword);
//		
//		return renderingDownloadePagePath;
//	}
//	
//	private List<Long> getFileEntryIds(long groupId, List<AssetTag> tags) {
//		List<Long> fileEntryIds = new ArrayList<Long>();
//		List<Long> tagIds = tags.parallelStream().map(AssetTag::getTagId).collect(Collectors.toList());
//		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
//		
//			long newarr[] = new long[tagIds.size()];
////			Long newarr[] =(Long[]) tagIds.toArray();
//
//			for (int i = 0; i < tagIds.size(); i++) {
//				System.out.println(tagIds.get(i));
//				newarr[i] = (long) tagIds.get(i);
//			}
//			assetEntryQuery.setAllTagIds(newarr);
//			List<AssetEntry> assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
//			for (AssetEntry assetEntry : assetEntryList) {
//				fileEntryIds.add(assetEntry.getClassPK());
//			}
//		return fileEntryIds;
//	}
//	
//	public List<DLFileEntry> getDLFileEntriesByTagName(long groupId, String tagId) {
//		List<DLFileEntry> fileEntries = new ArrayList<DLFileEntry>();
//		
//		if (tagId != null) {
//			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
//			long[] tagIds = { Long.parseLong(tagId) };
//			assetEntryQuery.setAnyTagIds(tagIds);
//			List<AssetEntry> assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
//			try {
//				for (AssetEntry assetEntry : assetEntryList) {
//					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(assetEntry.getClassPK());
//					fileEntries.add(dlFileEntry);
//				}
//			} catch (PortalException e) {
//			}
//		}
//		return fileEntries;
//	}
	
}
