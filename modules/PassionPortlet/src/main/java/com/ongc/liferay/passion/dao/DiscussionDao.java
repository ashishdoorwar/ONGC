package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.DiscussionBoard;
import com.ongc.liferay.passion.model.User;

import java.util.List;

public interface DiscussionDao {

	List<DiscussionBoard> fetchTopics(String q, String cpfNo);

	List<DiscussionBoard> topicDetail(String tid);

	List<DiscussionBoard> fetchComments(String tid);

	boolean postComment(User user, DiscussionBoard disboard, String tid);

	boolean postReply(User user, DiscussionBoard disboard, String tid, String cid);
	
	public List fetchTrendingTopics(String cpf);

}
