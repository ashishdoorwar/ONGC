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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TagsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TagsLocalService
 * @generated
 */
public class TagsLocalServiceWrapper
	implements ServiceWrapper<TagsLocalService>, TagsLocalService {

	public TagsLocalServiceWrapper(TagsLocalService tagsLocalService) {
		_tagsLocalService = tagsLocalService;
	}

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
	@Override
	public com.ongc.liferay.mediatags.model.Tags addTags(
		com.ongc.liferay.mediatags.model.Tags tags) {

		return _tagsLocalService.addTags(tags);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new tags with the primary key. Does not add the tags to the database.
	 *
	 * @param tagId the primary key for the new tags
	 * @return the new tags
	 */
	@Override
	public com.ongc.liferay.mediatags.model.Tags createTags(long tagId) {
		return _tagsLocalService.createTags(tagId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.deletePersistedModel(persistedModel);
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
	@Override
	public com.ongc.liferay.mediatags.model.Tags deleteTags(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.deleteTags(tagId);
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
	@Override
	public com.ongc.liferay.mediatags.model.Tags deleteTags(
		com.ongc.liferay.mediatags.model.Tags tags) {

		return _tagsLocalService.deleteTags(tags);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _tagsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _tagsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tagsLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _tagsLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _tagsLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _tagsLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _tagsLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _tagsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.ongc.liferay.mediatags.model.Tags fetchTags(long tagId) {
		return _tagsLocalService.fetchTags(tagId);
	}

	/**
	 * Returns the tags matching the UUID and group.
	 *
	 * @param uuid the tags's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tags, or <code>null</code> if a matching tags could not be found
	 */
	@Override
	public com.ongc.liferay.mediatags.model.Tags fetchTagsByUuidAndGroupId(
		String uuid, long groupId) {

		return _tagsLocalService.fetchTagsByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _tagsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _tagsLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _tagsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tagsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the tags with the primary key.
	 *
	 * @param tagId the primary key of the tags
	 * @return the tags
	 * @throws PortalException if a tags with the primary key could not be found
	 */
	@Override
	public com.ongc.liferay.mediatags.model.Tags getTags(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.getTags(tagId);
	}

	/**
	 * Returns the tags matching the UUID and group.
	 *
	 * @param uuid the tags's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tags
	 * @throws PortalException if a matching tags could not be found
	 */
	@Override
	public com.ongc.liferay.mediatags.model.Tags getTagsByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tagsLocalService.getTagsByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.ongc.liferay.mediatags.model.Tags> getTagses(
		int start, int end) {

		return _tagsLocalService.getTagses(start, end);
	}

	/**
	 * Returns all the tagses matching the UUID and company.
	 *
	 * @param uuid the UUID of the tagses
	 * @param companyId the primary key of the company
	 * @return the matching tagses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.ongc.liferay.mediatags.model.Tags>
		getTagsesByUuidAndCompanyId(String uuid, long companyId) {

		return _tagsLocalService.getTagsesByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.ongc.liferay.mediatags.model.Tags>
		getTagsesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.ongc.liferay.mediatags.model.Tags> orderByComparator) {

		return _tagsLocalService.getTagsesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of tagses.
	 *
	 * @return the number of tagses
	 */
	@Override
	public int getTagsesCount() {
		return _tagsLocalService.getTagsesCount();
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
	@Override
	public com.ongc.liferay.mediatags.model.Tags updateTags(
		com.ongc.liferay.mediatags.model.Tags tags) {

		return _tagsLocalService.updateTags(tags);
	}

	@Override
	public TagsLocalService getWrappedService() {
		return _tagsLocalService;
	}

	@Override
	public void setWrappedService(TagsLocalService tagsLocalService) {
		_tagsLocalService = tagsLocalService;
	}

	private TagsLocalService _tagsLocalService;

}