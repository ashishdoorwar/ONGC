package com.ongc.liferay.media.listner;

import java.util.Date;

public class Downloads {

	private long companyId;
	private long groupId;
	private long divisionId;
	private String divisionName;
	private long unitId;
	private String unitName;
	private long sectionId;
	private String sectionName;
	private long fileEntryId;
	private long fileEntryTypeId;
	private String fileEntryTypeName;
	private String mimeType;
	private String title;
	private String description;
	private long status;
	private String name;
	private Date createDate;
	private Date modifiedDate;
	private String userName;
	private long fileSize;
	private String version;
	private long repositoryId;
	private long folderId;
	private String uuid;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName!=null?divisionName:"";
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName!=null?unitName:"";
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName!=null?sectionName:"";
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public long getFileEntryTypeId() {
		return fileEntryTypeId;
	}

	public void setFileEntryTypeId(long fileEntryTypeId) {
		this.fileEntryTypeId = fileEntryTypeId;
	}

	public String getFileEntryTypeName() {
		return fileEntryTypeName;
	}

	public void setFileEntryTypeName(String fileEntryTypeName) {
		this.fileEntryTypeName = fileEntryTypeName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		this.repositoryId = repositoryId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
