package com.ongc.liferay.reports.model;

public class AskExpertSearch {
	
	private String queryid;
	private String domainId;
    private String startDate;
   	private String endDate;
   	private String keyword;
   	
	public String getQueryid() {
		return queryid;
	}
	public void setQueryid(String queryid) {
		this.queryid = queryid;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "AskExpertSearch [queryid=" + queryid + ", domainId=" + domainId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", keyword=" + keyword + "]";
	}

}
