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

package com.ongc.liferay.mediatags.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ongc.liferay.mediatags.model.Tags;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Tags. This utility wraps
 * <code>com.ongc.liferay.mediatags.service.impl.TagsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TagsLocalService
 * @generated
 */
public class TagsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ongc.liferay.mediatags.service.impl.TagsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the tags to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TagsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tags the tags
	 * @return the tags that was added
	 */
	public static Tags addTags(Tags tags) {
		return getService().addTags(tags);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new tags with the primary key. Does not add the tags to the database.
	 *
	 * @param tagId the primary key for the new tags
	 * @return the new tags
	 */
	public static Tags createTags(long tagId) {
		return getService().createTags(tagId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the tags with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TagsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags that was removed
	 * @throws PortalException if a tags with the primary key could not be found
	 */
	public static Tags deleteTags(long tagId) throws PortalException {
		return getService().deleteTags(tagId);
	}

	/**
	 * Deletes the tags from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TagsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tags the tags
	 * @return the tags that was removed
	 */
	public static Tags deleteTags(Tags tags) {
		return getService().deleteTags(tags);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.ongc.liferay.mediatags.model.impl.TagsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.ongc.liferay.mediatags.model.impl.TagsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Tags fetchTags(long tagId) {
		return getService().fetchTags(tagId);
	}

	/**
	 * Returns the tags matching the UUID and group.
	 *
	 * @param uuid the tags's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tags, or <code>null</code> if a matching tags could not be found
	 */
	public static Tags fetchTagsByUuidAndGroupId(String uuid, long groupId) {
		return getService().fetchTagsByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the tags with the primary key.
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags
	 * @throws PortalException if a tags with the primary key could not be found
	 */
	public static Tags getTags(long tagId) throws PortalException {
		return getService().getTags(tagId);
	}

	/**
	 * Returns the tags matching the UUID and group.
	 *
	 * @param uuid the tags's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tags
	 * @throws PortalException if a matching tags could not be found
	 */
	public static Tags getTagsByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getTagsByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the tagses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.ongc.liferay.mediatags.model.impl.TagsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @return the range of tagses
	 */
	public static List<Tags> getTagses(int start, int end) {
		return getService().getTagses(start, end);
	}

	/**
	 * Returns all the tagses matching the UUID and company.
	 *
	 * @param uuid the UUID of the tagses
	 * @param companyId the primary key of the company
	 * @return the matching tagses, or an empty list if no matches were found
	 */
	public static List<Tags> getTagsesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getTagsesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of tagses matching the UUID and company.
	 *
	 * @param uuid the UUID of the tagses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of tagses
	 * @param end the upper bound of the range of tagses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching tagses, or an empty list if no matches were found
	 */
	public static List<Tags> getTagsesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tags> orderByComparator) {

		return getService().getTagsesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of tagses.
	 *
	 * @return the number of tagses
	 */
	public static int getTagsesCount() {
		return getService().getTagsesCount();
	}

	/**
	 * Updates the tags in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TagsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tags the tags
	 * @return the tags that was updated
	 */
	public static Tags updateTags(Tags tags) {
		return getService().updateTags(tags);
	}

	public static TagsLocalService getService() {
		return _service;
	}

	private static volatile TagsLocalService _service;

}