package com.ongc.liferay.vigilance.model;

import java.sql.Date;
import java.util.List;

public class VigilanceComplaint {
	
	private int complaintNo;
	private String complaintActNo;
	private int userId;
	private String complaintAgainst;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String designation;
	private String departmetn;
	private String complaintSubject;
	private String tenderNumber;
	private String workCentre;
	private String complaintDetail;
	private Date complaintDate;
	private String ipAddress;
	private String otp;
	private String verified;
	private Date verifiedDate;
	private String country;
	private String state;
	private String status;
	private String pincode;
	private String action;
	private String sapNo;
	private String remarks;
	private List<ComplaintAttachment> attachmentList;
	private VigilanceUser complainBy;
	private int noOfAttachments;
	private String attachment;
	private String otherStatus;
	

	public String getOtherStatus() {
		return otherStatus;
	}

	public void setOtherStatus(String otherStatus) {
		this.otherStatus = otherStatus;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public int getNoOfAttachments() {
		return noOfAttachments;
	}

	public void setNoOfAttachments(int noOfAttachments) {
		this.noOfAttachments = noOfAttachments;
	}

	public VigilanceUser getComplainBy() {
		return complainBy;
	}

	public void setComplainBy(VigilanceUser complainBy) {
		this.complainBy = complainBy;
	}

	public List<ComplaintAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<ComplaintAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public int getComplaintNo() {
		return complaintNo;
	}

	

	public void setComplaintNo(int complaintNo) {
		this.complaintNo = complaintNo;
	}
	
	public String getAction() {
		return action;
	}
	

	public void setAction(String action) {
		this.action = action;
	}
	public String getSapNo() {
		return sapNo;
	}

	

	public void setSapNo(String sapNo) {
		this.sapNo = sapNo;
	}
	public String getRemarks() {
		return remarks;
	}

	

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
public void setComplaintActNo(String complaintActNo) {
		this.complaintActNo = complaintActNo;
	}
	public String getComplaintActNo() {
		return complaintActNo;
	}
	
	public String getComplaintAgainst() {
		return complaintAgainst;
	}

	public void setComplaintAgainst(String complaintAgainst) {
		this.complaintAgainst = complaintAgainst;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getWorkCentre() {
		return workCentre;
	}

	public void setWorkCentre(String workCentre) {
		this.workCentre = workCentre;
	}

	public String getComplaintDetail() {
		return complaintDetail;
	}

	public void setComplaintDetail(String complaintDetail) {
		this.complaintDetail = complaintDetail;
	}

		public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartmetn() {
		return departmetn;
	}

	public void setDepartmetn(String departmetn) {
		this.departmetn = departmetn;
	}

	public String getComplaintSubject() {
		return complaintSubject;
	}

	public void setComplaintSubject(String complaintSubject) {
		this.complaintSubject = complaintSubject;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTenderNumber() {
		return tenderNumber;
	}

	public void setTenderNumber(String tenderNumber) {
		this.tenderNumber = tenderNumber;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "VigilanceComplaint [complaintNo=" + complaintNo + ", complaintActNo=" + complaintActNo + ", userId="
				+ userId + ", complaintAgainst=" + complaintAgainst + ", title=" + title + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", designation=" + designation
				+ ", departmetn=" + departmetn + ", complaintSubject=" + complaintSubject + ", tenderNumber="
				+ tenderNumber + ", workCentre=" + workCentre + ", complaintDetail=" + complaintDetail
				+ ", complaintDate=" + complaintDate + ", ipAddress=" + ipAddress + ", otp=" + otp + ", verified="
				+ verified + ", verifiedDate=" + verifiedDate + ", country=" + country + ", state=" + state
				+ ", status=" + status + ", pincode=" + pincode + ", action=" + action + ", sapNo=" + sapNo
				+ ", remarks=" + remarks + ", attachmentList=" + attachmentList + ", complainBy=" + complainBy
				+ ", noOfAttachments=" + noOfAttachments + ", attachment=" + attachment + "]";
	}

	
}
