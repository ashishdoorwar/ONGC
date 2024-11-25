package com.ongc.liferay.passion.model;

import java.util.Date;
import java.util.List;


public class User {

	private String cpfNo;
	private String titlem;
	private String empName;
	private String empLevel;
	private String password;
	private Date dateOfBirth;
	private String birthPlace;
	private String homeTown;
	private String religion;
	private String category;
	private String state;
	private String nationality;
	private Date dateOfJoining;
	private String bloodGroup;
	private String emailIdPersonal;
	private String emailIdOfficial;
	private String designation;
	private String department;
	private String mobileNo;
	private String phoneNumberHome;
	private String phoneNumberOffice;
	private String faxNumber;
	private String currentPlace;
	private String workPlace;
	private String officeAddress;
	private String residenceAddress;
	private String maritalStatus;
	private Date dtOfMarraige;
	private String nameOfSpouse;
	private String cpfNoSpouse;
	private Date dtOfJoiningTrsfr;
	private String placePostingTrsfr;
	private String localAddress;
	private String boardNumber;
	private String extensionNumber;
	private String orgUnitNum;
	private String icnetNumber;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;	
	private String modifiedBy;	
	private String boardNoRes;
	private String extNumberRes;
	private String icnetNumRes;
	private String captchaVal;
	private String placePosting;
	private List<OrgLocation> orgLocations;
	private String aboutMe;
	private String passionId;
	private String passionName;
	private String subPassionId;
	private String subPassion;
	private String photoId;
	private String photoName;
	private String photoDescription;
	private String fileName;
	
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPlacePosting() {
		return placePosting;
	}
	public void setPlacePosting(String placePosting) {
		this.placePosting = placePosting;
	}
	
	public String getCaptchaVal() {
		return captchaVal;
	}
	public void setCaptchaVal(String captchaVal) {
		this.captchaVal = captchaVal;
	}

	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getTitlem() {
		return titlem;
	}
	public void setTitlem(String title) {
		this.titlem = title;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpLevel() {
		return empLevel;
	}
	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getEmailIdPersonal() {
		return emailIdPersonal;
	}
	public void setEmailIdPersonal(String emailIdPersonal) {
		this.emailIdPersonal = emailIdPersonal;
	}
	public String getEmailIdOfficial() {
		return emailIdOfficial;
	}
	public void setEmailIdOfficial(String emailIdOfficial) {
		this.emailIdOfficial = emailIdOfficial;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPhoneNumberHome() {
		return phoneNumberHome;
	}
	public void setPhoneNumberHome(String phoneNumberHome) {
		this.phoneNumberHome = phoneNumberHome;
	}
	public String getPhoneNumberOffice() {
		return phoneNumberOffice;
	}
	public void setPhoneNumberOffice(String phoneNumberOffice) {
		this.phoneNumberOffice = phoneNumberOffice;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getCurrentPlace() {
		return currentPlace;
	}
	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getResidenceAddress() {
		return residenceAddress;
	}
	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	public List<OrgLocation> getOrgLocations() {
		return orgLocations;
	}
	public void setOrgLocations(List<OrgLocation> orgLocations) {
		this.orgLocations = orgLocations;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(String boardNumber) {
		this.boardNumber = boardNumber;
	}
	public String getExtensionNumber() {
		return extensionNumber;
	}
	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}
	public String getOrgUnitNum() {
		return orgUnitNum;
	}
	public void setOrgUnitNum(String orgUnitNum) {
		this.orgUnitNum = orgUnitNum;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Date getDtOfMarraige() {
		return dtOfMarraige;
	}
	public void setDtOfMarraige(Date dtOfMarraige) {
		this.dtOfMarraige = dtOfMarraige;
	}
	public String getNameOfSpouse() {
		return nameOfSpouse;
	}
	public void setNameOfSpouse(String nameOfSpouse) {
		this.nameOfSpouse = nameOfSpouse;
	}
	public String getCpfNoSpouse() {
		return cpfNoSpouse;
	}
	public void setCpfNoSpouse(String cpf_no_spouse) {
		this.cpfNoSpouse = cpf_no_spouse;
	}
	public Date getDtOfJoiningTrsfr() {
		return dtOfJoiningTrsfr;
	}
	public void setDtOfJoiningTrsfr(Date dtOfJoiningTrsfr) {
		this.dtOfJoiningTrsfr = dtOfJoiningTrsfr;
	}
	public String getPlacePostingTrsfr() {
		return placePostingTrsfr;
	}
	public void setPlacePostingTrsfr(String placePostingTrsfr) {
		this.placePostingTrsfr = placePostingTrsfr;
	}
	public String getIcnetNumber() {
		return icnetNumber;
	}
	public void setIcnetNumber(String icnetNumber) {
		this.icnetNumber = icnetNumber;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getBoardNoRes() {
		return boardNoRes;
	}
	public void setBoardNoRes(String boardNoRes) {
		this.boardNoRes = boardNoRes;
	}
	public String getExtNumberRes() {
		return extNumberRes;
	}
	public void setExtNumberRes(String extNumberRes) {
		this.extNumberRes = extNumberRes;
	}
	public String getIcnetNumRes() {
		return icnetNumRes;
	}
	public void setIcnetNumRes(String icnetNumRes) {
		this.icnetNumRes = icnetNumRes;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getPassionId() {
		return passionId;
	}
	public void setPassionId(String passionId) {
		this.passionId = passionId;
	}
	public String getPassionName() {
		return passionName;
	}
	public void setPassionName(String passionName) {
		this.passionName = passionName;
	}
	public String getSubPassionId() {
		return subPassionId;
	}
	public void setSubPassionId(String subPassionId) {
		this.subPassionId = subPassionId;
	}
	public String getSubPassion() {
		return subPassion;
	}
	public void setSubPassion(String subPassion) {
		this.subPassion = subPassion;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoDescription() {
		return photoDescription;
	}
	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	


}
