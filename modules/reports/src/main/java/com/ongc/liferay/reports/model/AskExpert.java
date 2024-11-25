package com.ongc.liferay.reports.model;


import java.util.Date;

public class AskExpert {
	
	private int queryid;
	private int domainId;
    private String cpfno;
    private String message;
    private int noofreplies;
    private String priority;
    private String isActive;
    private String expertResponse;
    private Date posted_on;
    private Date modified_on;
    private Date lastcommented_on;
    private String lastcommented_by;
    private String username;
    private String domainName;
    private String lastcommented_name;
    
	public int getQueryid() {
		return queryid;
	}
	public void setQueryid(int queryid) {
		this.queryid = queryid;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public String getCpfno() {
		return cpfno;
	}
	public void setCpfno(String cpfno) {
		this.cpfno = cpfno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNoofreplies() {
		return noofreplies;
	}
	public void setNoofreplies(int noofreplies) {
		this.noofreplies = noofreplies;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getExpertResponse() {
		return expertResponse;
	}
	public void setExpertResponse(String expertResponse) {
		this.expertResponse = expertResponse;
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
	public Date getLastcommented_on() {
		return lastcommented_on;
	}
	public void setLastcommented_on(Date lastcommented_on) {
		this.lastcommented_on = lastcommented_on;
	}
	public String getLastcommented_by() {
		return lastcommented_by;
	}
	public void setLastcommented_by(String lastcommented_by) {
		this.lastcommented_by = lastcommented_by;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getLastcommented_name() {
		return lastcommented_name;
	}
	public void setLastcommented_name(String lastcommented_name) {
		this.lastcommented_name = lastcommented_name;
	}
	@Override
	public String toString() {
		return "AskExpert [queryid=" + queryid + ", domainId=" + domainId + ", cpfno=" + cpfno + ", message=" + message
				+ ", noofreplies=" + noofreplies + ", priority=" + priority + ", isActive=" + isActive
				+ ", expertResponse=" + expertResponse + ", posted_on=" + posted_on + ", modified_on=" + modified_on
				+ ", lastcommented_on=" + lastcommented_on + ", lastcommented_by=" + lastcommented_by + ", username="
				+ username + ", domainName=" + domainName + ", lastcommented_name=" + lastcommented_name + "]";
	}

	
}
