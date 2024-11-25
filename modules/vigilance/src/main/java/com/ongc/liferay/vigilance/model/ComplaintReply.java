package com.ongc.liferay.vigilance.model;

import java.util.Date;

public class ComplaintReply {

	private int replyId;
	private int complaintId;
	private String replyText;
	private Date replyDate;
	private int replyBy ;
	private VigilanceUser user;
	private String captchaVal;
	
	
	
	
	
	public String getCaptchaVal() {
		return captchaVal;
	}
	public void setCaptchaVal(String captchaVal) {
		this.captchaVal = captchaVal;
	}
	public VigilanceUser getUser() {
		return user;
	}
	public void setUser(VigilanceUser user) {
		this.user = user;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public int getReplyBy() {
		return replyBy;
	}
	public void setReplyBy(int replyBy) {
		this.replyBy = replyBy;
	}
}
