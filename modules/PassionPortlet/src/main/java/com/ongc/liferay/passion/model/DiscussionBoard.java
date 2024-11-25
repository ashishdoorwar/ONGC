package com.ongc.liferay.passion.model;

import java.util.List;
import java.io.Serializable;

public class DiscussionBoard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int rowNum;
	private String topicId;
	private String commentId;
	private String replyId;
	private String topicName;
	private int repliesCount;
	private String visibleTo;
	private String publishedOn;
	private String topicDesc;
	private String tags;
	private String groupId;
	private String groupName;
	private String createdByCpf;
	private String createdByName;
	private String comment;
	private String reply;
	private String commentBy;
	private String commentByName;
	private String replyBy;
	private String replyByName;
	private List<DiscussionBoard> replyList;
	
	public String getCreatedByCpf() {
		return createdByCpf;
	}
	public void setCreatedByCpf(String createdByCpf) {
		this.createdByCpf = createdByCpf;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getTopicDesc() {
		return topicDesc;
	}
	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPublishedOn() {
		return publishedOn;
	}
	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getRepliesCount() {
		return repliesCount;
	}
	public void setRepliesCount(int repliesCount) {
		this.repliesCount = repliesCount;
	}
	public String getVisibleTo() {
		return visibleTo;
	}
	public void setVisibleTo(String visibleTo) {
		this.visibleTo = visibleTo;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
	}
	public String getReplyBy() {
		return replyBy;
	}
	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}
	public List<DiscussionBoard> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<DiscussionBoard> replyList) {
		this.replyList = replyList;
	}
	public String getCommentByName() {
		return commentByName;
	}
	public void setCommentByName(String commentByName) {
		this.commentByName = commentByName;
	}
	public String getReplyByName() {
		return replyByName;
	}
	public void setReplyByName(String replyByName) {
		this.replyByName = replyByName;
	}
	
	

}
