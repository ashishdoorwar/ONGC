package com.ongc.liferay.media.util;

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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.ThemeLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.ongc.liferay.media.listner.Downloads;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.ThreadLocalUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MediaUtil {
	private static final Log LOGGER = LogFactoryUtil.getLog(MediaUtil.class);

	public static List<Long> getFileEntryIds(long groupId, long folderId) {
		List<DLFolder> folders = getChildFolders(groupId, folderId);
		List<Long> fileEntryIds = new ArrayList<Long>();
		for(DLFolder folder:folders) {
		List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, folder.getFolderId(), 0, -1, -1, null);//(groupId, folder.getFolderId());
		fileEntryIds.addAll(fileEntries.stream().map(DLFileEntry::getFileEntryId).collect(Collectors.toList()));
		}
		//LOGGER.info("getFileEntryIds Method=========================>"+fileEntryIds);
		return fileEntryIds;
	}
	
	public static List<Long> getFolderFileEntryIds(long groupId, long folderId) {
		List<Long> fileEntryIds = new ArrayList<Long>();
		List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, folderId, 0, -1, -1, null);//(groupId, folder.getFolderId());
		fileEntryIds.addAll(fileEntries.stream().map(DLFileEntry::getFileEntryId).collect(Collectors.toList()));
		//LOGGER.info("getFolderFileEntryIds Method=========================>"+fileEntryIds);
		return fileEntryIds;
	}
	
	public static List<AssetEntry> getAssetEntry(){
//		List<AssetEntry> fileEntries = null;
//		try {
//		DynamicQuery fileVersionQuery = DynamicQueryFactoryUtil.forClass(AssetEntry.class, "assetEntry");
//		ClassName nameClass = ClassNameLocalServiceUtil.getClassName(DLFileEntry.class.getName());
//		long classNameIdDLFileEntry = nameClass.getClassNameId();
//		Criterion fileEntryCriterion = null;
//		fileEntryCriterion= RestrictionsFactoryUtil.eq("assetEntry.classNameId", classNameIdDLFileEntry);
//		fileVersionQuery.add(fileEntryCriterion);
//		System.out.println(fileEntryCriterion+"\n"+fileVersionQuery);
//		fileEntries = AssetEntryLocalServiceUtil.dynamicQuery(fileVersionQuery);
//		System.out.println(classNameIdDLFileEntry);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		AssetEntryQuery queryCategoriesFilter = new AssetEntryQuery();
		long[] groupIds = {serviceContext.getScopeGroupId()};
		queryCategoriesFilter.setGroupIds(groupIds);
		ClassName nameClass = ClassNameLocalServiceUtil.getClassName(DLFileEntry.class.getName());
		long classNameIdDLFileEntry = nameClass.getClassNameId();
		long[] types = {classNameIdDLFileEntry};
		queryCategoriesFilter.setClassNameIds(types);
		List<AssetEntry> results = AssetEntryLocalServiceUtil.getEntries(queryCategoriesFilter);
		List<Long> assetEntryIds = results.stream().map(AssetEntry::getEntryId).collect(Collectors.toList());
		List<AssetTag> tags = new ArrayList<AssetTag>();
		for(Long assetEntryId:assetEntryIds) {
		tags.addAll(AssetTagLocalServiceUtil.getTags(DLFileEntry.class.getName(), assetEntryId));
		}
	//	LOGGER.info(tags);
		return results;
	}
	
	public static List<DLFolder> getChildFolders(long groupId, long parentFolderId) {
		List<DLFolder> nestedFolderIds = new ArrayList<DLFolder>(); 
		List<DLFolder> folders = DLFolderLocalServiceUtil.getFolders(groupId, parentFolderId);
		 if (!ListUtil.isEmpty(folders)) {
			 nestedFolderIds.addAll(folders);
			 for(DLFolder folder:folders) {
				 if (folder.isInactive())
						continue;
				 	nestedFolderIds.addAll(getChildFolders(groupId,folder.getFolderId()));
			 }
		 }
		return nestedFolderIds;

	}
	
	public static List<DLFileEntry> getDLFileEntriesByTagName(List<Long> fileEntryIds, int start, int end,
			LinkedHashMap<String, String> searchParam) {
		List<DLFileEntry> downloadsData = new ArrayList<DLFileEntry>();

		DynamicQuery fileVersionQuery = DynamicQueryFactoryUtil.forClass(DLFileVersion.class, "dlVersion");
		fileVersionQuery.add(PropertyFactoryUtil.forName("dlVersion.fileEntryId").eqProperty("entry.fileEntryId"));
		fileVersionQuery.add(PropertyFactoryUtil.forName("dlVersion.version").eqProperty("entry.version"))
				.setProjection(PropertyFactoryUtil.forName("dlVersion.fileEntryId"));

		Criterion fileVersionCriterion = null;
		fileVersionCriterion = prapareFileVersionSearchCriterion(searchParam);
		fileVersionQuery.add(fileVersionCriterion);

		DynamicQuery fileEntryQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, "entry");
		fileEntryQuery.add(PropertyFactoryUtil.forName("entry.fileEntryId").eq(fileVersionQuery));

		Criterion fileEntryCriterion = null;
		fileEntryCriterion = RestrictionsFactoryUtil.in("entry.fileEntryId", fileEntryIds);//}
		fileEntryCriterion = prapareFileEntrySearchCriterion(searchParam, fileEntryCriterion);
		fileEntryQuery.add(fileEntryCriterion);
		Order order = OrderFactoryUtil.desc("entry.createDate");
		fileEntryQuery.addOrder(order);
		fileEntryQuery.setLimit(start, end);
		List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.dynamicQuery(fileEntryQuery);
		LOGGER.info("Total Number of Files Found=>"+fileEntries.size());
		//downloadsData = convertDLFileEntryToDownloads(fileEntries);
		return fileEntries;

	}
	
	
	private static Criterion prapareFileVersionSearchCriterion(LinkedHashMap<String, String> searchParam) {
		Criterion fileVersionCriterion = null;
		for (Map.Entry<String, String> entry : searchParam.entrySet()) {
			if (Validator.isNotNull(entry.getKey())) {
				switch (entry.getKey()) {

				case "status":
					fileVersionCriterion = RestrictionsFactoryUtil.eq("dlVersion.status",
							Integer.parseInt(entry.getValue()));
					break;
				default:
					break;
				}
			}
		}
		return fileVersionCriterion;
	}
	
	
	private static Criterion prapareFileEntrySearchCriterion(LinkedHashMap<String, String> searchParam,
			Criterion fileEntryCriterion) {
		
		for (Map.Entry<String, String> entry : searchParam.entrySet()) {
			if (Validator.isNotNull(entry.getKey())) {
				switch (entry.getKey()) {
				case "folderId":
					fileEntryCriterion = RestrictionsFactoryUtil.and(fileEntryCriterion,
							RestrictionsFactoryUtil.eq("entry.folderId", Long.parseLong(entry.getValue())));
					break;
				case "tagId":
					ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
					AssetEntryQuery queryCategoriesFilter = new AssetEntryQuery();
					long[] groupIds = {serviceContext.getScopeGroupId()};
					queryCategoriesFilter.setGroupIds(groupIds);
					ClassName nameClass = ClassNameLocalServiceUtil.getClassName(DLFileEntry.class.getName());
					long classNameIdDLFileEntry = nameClass.getClassNameId();
					long[] types = {classNameIdDLFileEntry};
					queryCategoriesFilter.setClassNameIds(types);
					long[] categoryIds = {Long.parseLong(entry.getValue())};
					queryCategoriesFilter.setAllTagIds((categoryIds));
					List<AssetEntry> results = AssetEntryLocalServiceUtil.getEntries(queryCategoriesFilter);
					List<Long> assetEntryIds = results.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
					
					fileEntryCriterion = 
							RestrictionsFactoryUtil.in("entry.fileEntryId", assetEntryIds);
					break;
				case "keyword":
					fileEntryCriterion = RestrictionsFactoryUtil.and(fileEntryCriterion,
							RestrictionsFactoryUtil.like("entry.description", "%" + entry.getValue() + "%"));
					fileEntryCriterion = RestrictionsFactoryUtil.or(fileEntryCriterion,
							RestrictionsFactoryUtil.like("entry.title", "%" + entry.getValue() + "%"));
					break;
				case "duration":
					if("-1".equals(entry.getValue())) {
						break;
					}
					Calendar calendar = Calendar.getInstance();
					Date currentDateTime = calendar.getTime();
					calendar.add(Calendar.DATE, -Integer.parseInt(entry.getValue()));
					Date beforeDateTime = calendar.getTime();

					fileEntryCriterion = RestrictionsFactoryUtil.and(fileEntryCriterion,
							RestrictionsFactoryUtil.between("entry.createDate", beforeDateTime, currentDateTime));
					break;
				default:
					break;
				}
			}
		}
		//LOGGER.info(fileEntryCriterion.toString());
		return fileEntryCriterion;
	}
	
	public static int getDLFileEntriesCountByTagName(List<Long> fileEntryIds, int start, int end,
			LinkedHashMap<String, String> searchParam) {
		int totalresult = 0;

		DynamicQuery fileVersionQuery = DynamicQueryFactoryUtil.forClass(DLFileVersion.class, "dlVersion");
		fileVersionQuery.add(PropertyFactoryUtil.forName("dlVersion.fileEntryId").eqProperty("entry.fileEntryId"));
		fileVersionQuery.add(PropertyFactoryUtil.forName("dlVersion.version").eqProperty("entry.version"))
				.setProjection(PropertyFactoryUtil.forName("dlVersion.fileEntryId"));

		Criterion fileVersionCriterion = null;
		fileVersionCriterion = prapareFileVersionSearchCriterion(searchParam);
		fileVersionQuery.add(fileVersionCriterion);

		DynamicQuery fileEntryQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, "entry");
		fileEntryQuery.add(PropertyFactoryUtil.forName("entry.fileEntryId").eq(fileVersionQuery));

		Criterion fileEntryCriterion = null;
		fileEntryCriterion = RestrictionsFactoryUtil.in("entry.fileEntryId", fileEntryIds);
		fileEntryCriterion = prapareFileEntrySearchCriterion(searchParam, fileEntryCriterion);
		fileEntryQuery.add(fileEntryCriterion);

		totalresult = (int) DLFileEntryLocalServiceUtil.dynamicQueryCount(fileEntryQuery);
		return totalresult;
	}
	
	public static List<DLFolder> getFolders(long groupId,long parentFolderId){
		List<DLFolder> folderIds;
		folderIds=DLFolderLocalServiceUtil.getFolders(groupId, parentFolderId).stream().filter(c -> c.getUserId() == 38969).collect(Collectors.toList());
		return folderIds;
	}
	
	public static List<AssetTag> getTags(long groupId){
		List<AssetTag> currentSiteTags = null;
		List<AssetTag> tags = AssetTagLocalServiceUtil.getTags();
		currentSiteTags = tags.stream().filter(c -> c.getGroupId() == groupId).collect(Collectors.toList());
		return currentSiteTags;
	}
}
