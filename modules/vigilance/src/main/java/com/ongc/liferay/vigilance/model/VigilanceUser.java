package com.ongc.liferay.vigilance.model;

import java.util.Date;

public class VigilanceUser {

	private int registrationId;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String firstAddress;
	private String secondAddress;
	private String country;
	private String state;
	private String pincode;
	private String mobile;
	private String landlineNumber;
	private String emailId;
	private String password;
	private String securityQuestion;
	private String securityAnswer;
	private Date registrationDate;
	private String ipAddress;
	private String otp;
	private String varifiedStatus;
	private Date verificationDate;
	private String confirmPasswrod;
    private String userType;
    
    
    
    
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	 
	public String getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(String firstAddress) {
		this.firstAddress = firstAddress;
	}

	public String getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(String secondAddress) {
		this.secondAddress = secondAddress;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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

	public String getVarifiedStatus() {
		return varifiedStatus;
	}

	public void setVarifiedStatus(String varifiedStatus) {
		this.varifiedStatus = varifiedStatus;
	}

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getLandlineNumber() {
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getConfirmPasswrod() {
		return confirmPasswrod;
	}

	public void setConfirmPasswrod(String confirmPasswrod) {
		this.confirmPasswrod = confirmPasswrod;
	}

	@Override
	public String toString() {
		return "VigilanceUser [registrationId=" + registrationId + ", title=" + title + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", dob=" + dob
				+ ", firstAddress=" + firstAddress + ", secondAddress=" + secondAddress + ", country=" + country
				+ ", state=" + state + ", pincode=" + pincode + ", mobile=" + mobile + ", landlineNumber="
				+ landlineNumber + ", emailId=" + emailId + ", password=" + password + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer + ", registrationDate=" + registrationDate
				+ ", ipAddress=" + ipAddress + ", otp=" + otp + ", varifiedStatus=" + varifiedStatus
				+ ", verificationDate=" + verificationDate + ", confirmPasswrod=" + confirmPasswrod + ", userType="
				+ userType + "]";
	}
	
}
