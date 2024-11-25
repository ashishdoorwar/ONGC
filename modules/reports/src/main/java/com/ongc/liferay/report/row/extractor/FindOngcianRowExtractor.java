package com.ongc.liferay.report.row.extractor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.dao.Impl.FindOngcianManagementDaoImpl;
import com.ongc.liferay.reports.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FindOngcianRowExtractor {
	
	private static final Log LOGGER = LogFactoryUtil.getLog(FindOngcianRowExtractor.class);
	
	public static List<User> extractRow(ResultSet resultSet,Connection conn) {
		
		User user; 
		List<User> users = new ArrayList<User>();
		int row=0;
		FindOngcianManagementDaoImpl findOngcianManagementDaoImpl=new FindOngcianManagementDaoImpl();
		try {
			while (resultSet.next()) {
				user = new User();
				String s=resultSet.getString("CPF_NUMBER");
				user.setCpfNo(s);				
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
				//user.setSubPassion(fomi.subPassion(s,conn));
				user.setWorkPlace(resultSet.getString("WORKPLACE"));
				user.setOfficeAddress(resultSet.getString("OFFICE_ADDRESS"));
				user.setResidenceAddress(resultSet.getString("RESIDENCE_ADDRESS"));
				user.setTitlem(resultSet.getString("TITLE"));	
				user.setAboutMe(resultSet.getString("ABOUT_ME"));
				users.add(user);
			}
			if(resultSet!=null){
				resultSet.close();
			}
			for(User u:users){
				u.setSubPassion(findOngcianManagementDaoImpl.subPassion(u.getCpfNo(),conn));
			}
			
			
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage() , e);
		}
		return users;
	}
}
