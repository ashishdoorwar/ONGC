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

package com.ongc.liferay.mediatags.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ongc.liferay.mediatags.model.Tag;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the tag service. This utility wraps <code>com.ongc.liferay.mediatags.service.persistence.impl.TagPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TagPersistence
 * @generated
 */
public class TagUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Tag tag) {
		getPersistence().clearCache(tag);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Tag> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Tag> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Tag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Tag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Tag> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Tag update(Tag tag) {
		return getPersistence().update(tag);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Tag update(Tag tag, ServiceContext serviceContext) {
		return getPersistence().update(tag, serviceContext);
	}

	/**
	 * Returns all the tags where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tags
	 */
	public static List<Tag> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the tags where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @return the range of matching tags
	 */
	public static List<Tag> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the tags where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tags
	 */
	public static List<Tag> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tag> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tags where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tags
	 */
	public static List<Tag> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tag> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first tag in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tag
	 * @throws NoSuchTagException if a matching tag could not be found
	 */
	public static Tag findByUuid_First(
			String uuid, OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first tag in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUuid_First(
		String uuid, OrderByComparator<Tag> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last tag in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tag
	 * @throws NoSuchTagException if a matching tag could not be found
	 */
	public static Tag findByUuid_Last(
			String uuid, OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last tag in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUuid_Last(
		String uuid, OrderByComparator<Tag> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the tags before and after the current tag in the ordered set where uuid = &#63;.
	 *
	 * @param tagId the primary key of the current tag
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tag
	 * @throws NoSuchTagException if a tag with the primary key could not be found
	 */
	public static Tag[] findByUuid_PrevAndNext(
			long tagId, String uuid, OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_PrevAndNext(
			tagId, uuid, orderByComparator);
	}

	/**
	 * Removes all the tags where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of tags where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tags
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the tag where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTagException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tag
	 * @throws NoSuchTagException if a matching tag could not be found
	 */
	public static Tag findByUUID_G(String uuid, long groupId)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the tag where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the tag where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the tag where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the tag that was removed
	 */
	public static Tag removeByUUID_G(String uuid, long groupId)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of tags where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching tags
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the tags where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching tags
	 */
	public static List<Tag> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the tags where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @return the range of matching tags
	 */
	public static List<Tag> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the tags where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tags
	 */
	public static List<Tag> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tag> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tags where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tags
	 */
	public static List<Tag> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tag> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first tag in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tag
	 * @throws NoSuchTagException if a matching tag could not be found
	 */
	public static Tag findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first tag in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUuid_C_First(
		String uuid, long companyId, OrderByComparator<Tag> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last tag in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tag
	 * @throws NoSuchTagException if a matching tag could not be found
	 */
	public static Tag findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last tag in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tag, or <code>null</code> if a matching tag could not be found
	 */
	public static Tag fetchByUuid_C_Last(
		String uuid, long companyId, OrderByComparator<Tag> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the tags before and after the current tag in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param tagId the primary key of the current tag
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tag
	 * @throws NoSuchTagException if a tag with the primary key could not be found
	 */
	public static Tag[] findByUuid_C_PrevAndNext(
			long tagId, String uuid, long companyId,
			OrderByComparator<Tag> orderByComparator)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByUuid_C_PrevAndNext(
			tagId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the tags where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of tags where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching tags
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the tag in the entity cache if it is enabled.
	 *
	 * @param tag the tag
	 */
	public static void cacheResult(Tag tag) {
		getPersistence().cacheResult(tag);
	}

	/**
	 * Caches the tags in the entity cache if it is enabled.
	 *
	 * @param tags the tags
	 */
	public static void cacheResult(List<Tag> tags) {
		getPersistence().cacheResult(tags);
	}

	/**
	 * Creates a new tag with the primary key. Does not add the tag to the database.
	 *
	 * @param tagId the primary key for the new tag
	 * @return the new tag
	 */
	public static Tag create(long tagId) {
		return getPersistence().create(tagId);
	}

	/**
	 * Removes the tag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tagId the primary key of the tag
	 * @return the tag that was removed
	 * @throws NoSuchTagException if a tag with the primary key could not be found
	 */
	public static Tag remove(long tagId)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().remove(tagId);
	}

	public static Tag updateImpl(Tag tag) {
		return getPersistence().updateImpl(tag);
	}

	/**
	 * Returns the tag with the primary key or throws a <code>NoSuchTagException</code> if it could not be found.
	 *
	 * @param tagId the primary key of the tag
	 * @return the tag
	 * @throws NoSuchTagException if a tag with the primary key could not be found
	 */
	public static Tag findByPrimaryKey(long tagId)
		throws com.ongc.liferay.mediatags.exception.NoSuchTagException {

		return getPersistence().findByPrimaryKey(tagId);
	}

	/**
	 * Returns the tag with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tagId the primary key of the tag
	 * @return the tag, or <code>null</code> if a tag with the primary key could not be found
	 */
	public static Tag fetchByPrimaryKey(long tagId) {
		return getPersistence().fetchByPrimaryKey(tagId);
	}

	/**
	 * Returns all the tags.
	 *
	 * @return the tags
	 */
	public static List<Tag> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the tags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @return the range of tags
	 */
	public static List<Tag> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the tags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tags
	 */
	public static List<Tag> findAll(
		int start, int end, OrderByComparator<Tag> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tags
	 * @param end the upper bound of the range of tags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tags
	 */
	public static List<Tag> findAll(
		int start, int end, OrderByComparator<Tag> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tags from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tags.
	 *
	 * @return the number of tags
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TagPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TagPersistence _persistence;

}