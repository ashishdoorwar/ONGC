package com.ongc.liferay.passion.dao;

import java.util.List;

public interface TagsDao {

	List fetchDiscussionTags(String cpfNo);

	List fetchWorkItemTags();

	List fetchPassionTags();

}
