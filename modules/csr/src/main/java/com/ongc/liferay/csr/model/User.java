package com.ongc.liferay.csr.model;

import java.io.File;
import java.util.Date;
/**
 *  
 * @author Ranjeet
 */
public class User {
	private String cpfNo;
	private String tittle;
	private String userName;
	private String designation;
	private Date dateOfJoining;
	private Date dateOfBirth;
	private Date dateOfSep;
	private String mobileNo;
	private String emailId;
	private String panNumber;
	private String location;
	private String address;
	private String communicatedAddress;
	private String aboutMe;
	private byte[] profilePic;
	private File userPic;
	private String createdBy;
	private Date creationDate;
	private String landLineNo;
	private int active;
	private String loginId;
	private String password;
	private int flag;//for check first time login.
	
	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfSep() {
		return dateOfSep;
	}
	public void setDateOfSep(Date dateOfSep) {
		this.dateOfSep = dateOfSep;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCommunicatedAddress() {
		return communicatedAddress;
	}
	public void setCommunicatedAddress(String communicatedAddress) {
		this.communicatedAddress = communicatedAddress;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public File getUserPic() {
		return userPic;
	}
	public void setUserPic(File userPic) {
		this.userPic = userPic;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getLandLineNo() {
		return landLineNo;
	}
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}
