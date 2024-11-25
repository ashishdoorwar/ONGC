/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ongc.liferay.mediatags.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Tags}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Tags
 * @generated
 */
public class TagsWrapper
	extends BaseModelWrapper<Tags> implements ModelWrapper<Tags>, Tags {

	public TagsWrapper(Tags tags) {
		super(tags);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("tagId", getTagId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tagName", getTagName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long tagId = (Long)attributes.get("tagId");

		if (tagId != null) {
			setTagId(tagId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String tagName = (String)attributes.get("tagName");

		if (tagName != null) {
			setTagName(tagName);
		}
	}

	@Override
	public Tags cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this tags.
	 *
	 * @return the company ID of this tags
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this tags.
	 *
	 * @return the create date of this tags
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this tags.
	 *
	 * @return the group ID of this tags
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this tags.
	 *
	 * @return the modified date of this tags
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this tags.
	 *
	 * @return the primary key of this tags
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the tag ID of this tags.
	 *
	 * @return the tag ID of this tags
	 */
	@Override
	public long getTagId() {
		return model.getTagId();
	}

	/**
	 * Returns the tag name of this tags.
	 *
	 * @return the tag name of this tags
	 */
	@Override
	public String getTagName() {
		return model.getTagName();
	}

	/**
	 * Returns the user ID of this tags.
	 *
	 * @return the user ID of this tags
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this tags.
	 *
	 * @return the user name of this tags
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this tags.
	 *
	 * @return the user uuid of this tags
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this tags.
	 *
	 * @return the uuid of this tags
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this tags.
	 *
	 * @param companyId the company ID of this tags
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this tags.
	 *
	 * @param createDate the create date of this tags
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this tags.
	 *
	 * @param groupId the group ID of this tags
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this tags.
	 *
	 * @param modifiedDate the modified date of this tags
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this tags.
	 *
	 * @param primaryKey the primary key of this tags
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the tag ID of this tags.
	 *
	 * @param tagId the tag ID of this tags
	 */
	@Override
	public void setTagId(long tagId) {
		model.setTagId(tagId);
	}

	/**
	 * Sets the tag name of this tags.
	 *
	 * @param tagName the tag name of this tags
	 */
	@Override
	public void setTagName(String tagName) {
		model.setTagName(tagName);
	}

	/**
	 * Sets the user ID of this tags.
	 *
	 * @param userId the user ID of this tags
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this tags.
	 *
	 * @param userName the user name of this tags
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this tags.
	 *
	 * @param userUuid the user uuid of this tags
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this tags.
	 *
	 * @param uuid the uuid of this tags
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected TagsWrapper wrap(Tags tags) {
		return new TagsWrapper(tags);
	}

}