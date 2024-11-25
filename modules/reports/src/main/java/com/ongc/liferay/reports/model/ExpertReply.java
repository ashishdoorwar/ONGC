package com.ongc.liferay.reports.model;

import java.util.Date;

public class ExpertReply {
	private int replyId;
	private int queryId;
	private String replyMessage;
	private String cpfno;
	private String isActive;
	private String isExpert;
	private Date posted_on;
	private Date modified_on;
	private String userName;
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	public String getCpfno() {
		return cpfno;
	}
	public void setCpfno(String cpfno) {
		this.cpfno = cpfno;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsExpert() {
		return isExpert;
	}
	public void setIsExpert(String isExpert) {
		this.isExpert = isExpert;
	}
	public Date getPosted_on() {
		return posted_on;
	}
	public void setPosted_on(Date posted_on) {
		this.posted_on = posted_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ExpertReply [replyId=" + replyId + ", queryId=" + queryId + ", replyMessage=" + replyMessage
				+ ", cpfno=" + cpfno + ", isActive=" + isActive + ", isExpert=" + isExpert + ", posted_on=" + posted_on
				+ ", modified_on=" + modified_on + ", userName=" + userName + "]";
	}
	
}
