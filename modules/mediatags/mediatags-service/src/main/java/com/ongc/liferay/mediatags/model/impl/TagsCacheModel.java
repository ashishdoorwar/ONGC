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

package com.ongc.liferay.mediatags.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ongc.liferay.mediatags.model.Tags;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Tags in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TagsCacheModel implements CacheModel<Tags>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TagsCacheModel)) {
			return false;
		}

		TagsCacheModel tagsCacheModel = (TagsCacheModel)object;

		if (tagId == tagsCacheModel.tagId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tagId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", tagId=");
		sb.append(tagId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", tagName=");
		sb.append(tagName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Tags toEntityModel() {
		TagsImpl tagsImpl = new TagsImpl();

		if (uuid == null) {
			tagsImpl.setUuid("");
		}
		else {
			tagsImpl.setUuid(uuid);
		}

		tagsImpl.setTagId(tagId);
		tagsImpl.setGroupId(groupId);
		tagsImpl.setCompanyId(companyId);
		tagsImpl.setUserId(userId);

		if (userName == null) {
			tagsImpl.setUserName("");
		}
		else {
			tagsImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			tagsImpl.setCreateDate(null);
		}
		else {
			tagsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			tagsImpl.setModifiedDate(null);
		}
		else {
			tagsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (tagName == null) {
			tagsImpl.setTagName("");
		}
		else {
			tagsImpl.setTagName(tagName);
		}

		tagsImpl.resetOriginalValues();

		return tagsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		tagId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		tagName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(tagId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (tagName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tagName);
		}
	}

	public String uuid;
	public long tagId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String tagName;

}