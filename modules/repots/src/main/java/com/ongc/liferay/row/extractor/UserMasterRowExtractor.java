package com.ongc.liferay.row.extractor;

import com.ongc.liferay.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserMasterRowExtractor {
	
	
	public static List<User> extractRow(ResultSet resultSet) {
		User user; 
		List<User> users = new ArrayList<User>();
		try {
			while (resultSet.next()) {
				user = new User();
				
				user.setCpfNo(resultSet.getString("CPF_NUMBER"));
				
				user.setEmpName(resultSet.getString("EMP_NAME"));
				user.setEmpLevel(resultSet.getString("EMP_LEVEL"));
				user.setDesignation(resultSet.getString("DESIGNATION"));
				user.setCurrentPlace(resultSet.getString("PLACE_POSTING"));
				user.setOrgUnitNum(resultSet.getString("ORG_UNIT_NUM"));
				user.setDateOfJoining(resultSet.getDate("DT_JOINING"));
				user.setDateOfBirth(resultSet.getDate("DT_BIRTH"));
				user.setBirthPlace(resultSet.getString("BIRTH_PLACE"));
				user.setHomeTown(resultSet.getString("HOMETOWN"));
				user.setReligion(resultSet.getString("RELIGION"));
				user.setCategory(resultSet.getString("CATEGORY"));
				user.setState(resultSet.getString("STATE"));
				user.setNationality(resultSet.getString("NATIONALITY"));
				user.setMaritalStatus(resultSet.getString("MARITAL_STATUS"));
				user.setDtOfMarraige(resultSet.getDate("DT_OF_MARRIAGE"));
				user.setNameOfSpouse(resultSet.getString("NAME_OF_SPOUSE"));
				user.setCpfNoSpouse(resultSet.getString("CPF_NO_SPOUSE"));
				user.setDtOfJoiningTrsfr(resultSet.getDate("DT_OF_JOINING_TRSFR"));
				user.setPlacePostingTrsfr(resultSet.getString("PLACE_POSTING_TRSFR"));
				user.setLocalAddress(resultSet.getString("LOCAL_ADDRESS"));
				user.setPhoneNumberHome(resultSet.getString("PHONE_NUM_RES"));
				user.setPhoneNumberOffice(resultSet.getString("PHONE_NUM_OFF"));
				user.setBoardNumber(resultSet.getString("BOARD_NUMBER"));
				user.setExtensionNumber(resultSet.getString("EXT_NUMBER"));
				user.setFaxNumber(resultSet.getString("FAX_NUMBER"));			
				user.setMobileNo(resultSet.getString("MOBILE_NUMBER"));
				user.setEmailIdOfficial(resultSet.getString("E_MAILID_OFF"));
				user.setEmailIdPersonal(resultSet.getString("E_MAILID_PER"));
				user.setBloodGroup(resultSet.getString("BLOOD_GROUP"));
				user.setCreatedOn(resultSet.getDate("CREATED_ON"));
				user.setCreatedBy(resultSet.getString("CREATED_BY"));
				user.setModifiedOn(resultSet.getDate("MODIFIED_ON"));
				user.setModifiedBy(resultSet.getString("MODIFIED_BY"));
				user.setBoardNoRes(resultSet.getString("BOARD_NO_RES"));
				user.setExtNumberRes(resultSet.getString("EXT_NUMBER_RES"));
				user.setIcnetNumber(resultSet.getInt("ICNET_NUM_RES")+"");
				user.setDepartment(resultSet.getString("DEPT"));
//				user.setProfilePicPath(resultSet.getString("PROFILE_PIC_PATH"));
				user.setWorkPlace(resultSet.getString("WORKPLACE"));
				user.setOfficeAddress(resultSet.getString("OFFICE_ADDRESS"));
				user.setResidenceAddress(resultSet.getString("RESIDENCE_ADDRESS"));
				user.setTitlem(resultSet.getString("TITLE"));	
				user.setAboutMe(resultSet.getString("ABOUT_ME"));
				user.setSubLocation(resultSet.getString("SUB_LOCATION"));
				
				users.add(user); 
				//log.info("Lenght of list "+ users.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
