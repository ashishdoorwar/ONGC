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

package com.ongc.liferay.mediatags.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.ongc.liferay.mediatags.model.Tag;
import com.ongc.liferay.mediatags.service.base.TagLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ashish
 */
@Component(
		property = "model.class.name=com.ongc.liferay.mediatags.model.Tag",
		service = AopService.class
		)
public class TagLocalServiceImpl extends TagLocalServiceBaseImpl {

	public Tag addTag(Tag tag) {
		Tag tagDetail=null;
		long tagId=0;
		User user= null;
		long userId = 0;
		long companyId = 0;
		long groupId= 0;
		Date createDate = null;
		try {
			ServiceContext serviceContext=ServiceContextThreadLocal.getServiceContext();
			userId=serviceContext.getUserId();
			companyId=serviceContext.getCompanyId();
			createDate= serviceContext.getCreateDate();
			user=userLocalService.getUser(userId);
			groupId=user.getGroupId();
			tagDetail = tagPersistence.findByPrimaryKey(tag.getTagId());
		}catch (Exception e) {
			if(tagDetail == null) {
				tagId = CounterLocalServiceUtil.increment(Tag.class.getName());
				tagDetail = tagPersistence.create(tagId);
				tagDetail.setUserName(user.getFullName());
				tagDetail.setGroupId(groupId);
				tagDetail.setUserId(userId);
				tagDetail.setCompanyId(companyId);
				tagDetail.setCreateDate(createDate);
				tagDetail.setTagId(tag.getTagId());
				tagDetail.setTagName(tag.getTagName());
				tagPersistence.update(tagDetail);
			}
		}
		return tag;
	}

}