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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ongc.liferay.mediatags.exception.NoSuchTagsException;
import com.ongc.liferay.mediatags.model.Tags;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the tags service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TagsUtil
 * @generated
 */
@ProviderType
public interface TagsPersistence extends BasePersistence<Tags> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TagsUtil} to access the tags persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the tagses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tagses
	 */
	public java.util.List<Tags> findByUuid(String uuid);

	/**
	 * Returns a range of all the tagses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @return the range of matching tagses
	 */
	public java.util.List<Tags> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the tagses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tagses
	 */
	public java.util.List<Tags> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tagses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tagses
	 */
	public java.util.List<Tags> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tags in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tags
	 * @throws NoSuchTagsException if a matching tags could not be found
	 */
	public Tags findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Returns the first tags in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns the last tags in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tags
	 * @throws NoSuchTagsException if a matching tags could not be found
	 */
	public Tags findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Returns the last tags in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns the tagses before and after the current tags in the ordered set where uuid = &#63;.
	 *
	 * @param tagId the primary key of the current tags
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tags
	 * @throws NoSuchTagsException if a tags with the primary key could not be found
	 */
	public Tags[] findByUuid_PrevAndNext(
			long tagId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Removes all the tagses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of tagses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tagses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the tags where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTagsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tags
	 * @throws NoSuchTagsException if a matching tags could not be found
	 */
	public Tags findByUUID_G(String uuid, long groupId)
		throws NoSuchTagsException;

	/**
	 * Returns the tags where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the tags where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the tags where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the tags that was removed
	 */
	public Tags removeByUUID_G(String uuid, long groupId)
		throws NoSuchTagsException;

	/**
	 * Returns the number of tagses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching tagses
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the tagses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching tagses
	 */
	public java.util.List<Tags> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the tagses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @return the range of matching tagses
	 */
	public java.util.List<Tags> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the tagses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tagses
	 */
	public java.util.List<Tags> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tagses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tagses
	 */
	public java.util.List<Tags> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tags in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tags
	 * @throws NoSuchTagsException if a matching tags could not be found
	 */
	public Tags findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Returns the first tags in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns the last tags in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tags
	 * @throws NoSuchTagsException if a matching tags could not be found
	 */
	public Tags findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Returns the last tags in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public Tags fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns the tagses before and after the current tags in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param tagId the primary key of the current tags
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tags
	 * @throws NoSuchTagsException if a tags with the primary key could not be found
	 */
	public Tags[] findByUuid_C_PrevAndNext(
			long tagId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tags>
				orderByComparator)
		throws NoSuchTagsException;

	/**
	 * Removes all the tagses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of tagses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching tagses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the tags in the entity cache if it is enabled.
	 *
	 * @param tags the tags
	 */
	public void cacheResult(Tags tags);

	/**
	 * Caches the tagses in the entity cache if it is enabled.
	 *
	 * @param tagses the tagses
	 */
	public void cacheResult(java.util.List<Tags> tagses);

	/**
	 * Creates a new tags with the primary key. Does not add the tags to the database.
	 *
	 * @param tagId the primary key for the new tags
	 * @return the new tags
	 */
	public Tags create(long tagId);

	/**
	 * Removes the tags with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags that was removed
	 * @throws NoSuchTagsException if a tags with the primary key could not be found
	 */
	public Tags remove(long tagId) throws NoSuchTagsException;

	public Tags updateImpl(Tags tags);

	/**
	 * Returns the tags with the primary key or throws a <code>NoSuchTagsException</code> if it could not be found.
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags
	 * @throws NoSuchTagsException if a tags with the primary key could not be found
	 */
	public Tags findByPrimaryKey(long tagId) throws NoSuchTagsException;

	/**
	 * Returns the tags with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags, or <code>null</code> if a tags with the primary key could not be found
	 */
	public Tags fetchByPrimaryKey(long tagId);

	/**
	 * Returns all the tagses.
	 *
	 * @return the tagses
	 */
	public java.util.List<Tags> findAll();

	/**
	 * Returns a range of all the tagses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @return the range of tagses
	 */
	public java.util.List<Tags> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the tagses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tagses
	 */
	public java.util.List<Tags> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tagses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TagsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tagses
	 */
	public java.util.List<Tags> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tags>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the tagses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of tagses.
	 *
	 * @return the number of tagses
	 */
	public int countAll();

}