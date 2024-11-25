package com.ongc.liferay.passion.model;

public class Group {
	
	private int rowNum;
	private String groupId;
	private String groupName;
	private int noOfMembers;
	private String createdOn;
	private String createdByName;
	private String createdByCpf;
	private String empName;
	private String empCpf;
	private String members[];
	private String joiningStatus;
	private String memberCpf;
	private String memberName;
	private String subPassionName;
	private int topicCount;
	
	public String getMemberCpf() {
		return memberCpf;
	}
	public void setMemberCpf(String memberCpf) {
		this.memberCpf = memberCpf;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getSubPassionName() {
		return subPassionName;
	}
	public void setSubPassionName(String subPassionName) {
		this.subPassionName = subPassionName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCpf() {
		return empCpf;
	}
	public void setEmpCpf(String empCpf) {
		this.empCpf = empCpf;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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
	public int getNoOfMembers() {
		return noOfMembers;
	}
	public void setNoOfMembers(int noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreatedByCpf() {
		return createdByCpf;
	}
	public void setCreatedByCpf(String createdByCpf) {
		this.createdByCpf = createdByCpf;
	}
	public String[] getMembers() {
		return members;
	}
	public void setMembers(String[] members) {
		this.members = members;
	}
	public String getJoiningStatus() {
		return joiningStatus;
	}
	public void setJoiningStatus(String joiningStatus) {
		this.joiningStatus = joiningStatus;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	
	

}
