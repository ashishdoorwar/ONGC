package com.ongc.liferay.vigilance.row.extractor;

import com.ongc.liferay.vigilance.model.VigilanceUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class VigilanceUserRowExtractor {
	
	
	public static List<VigilanceUser> extractRow(ResultSet resultSet) {
		VigilanceUser user; 
		List<VigilanceUser> users = new ArrayList<VigilanceUser>();
		try {
			while (resultSet.next()) {
				
				user = new VigilanceUser();
			
				user.setRegistrationId(resultSet.getInt("REGISTRATION_ID"));
				user.setTitle(resultSet.getString("TITLE"));
				user.setFirstName(resultSet.getString("FIRST_NAME"));
				user.setMiddleName(resultSet.getString("MIDDLE_NAME"));
				user.setLastName(resultSet.getString("LAST_NAME"));
				user.setGender(resultSet.getString("GENDER"));
				user.setDob(resultSet.getString("DOB"));
				user.setFirstAddress(resultSet.getString("FIRST_ADDRESS"));
				user.setSecondAddress(resultSet.getString("SECOND_ADDRESS"));
				user.setCountry(resultSet.getString("COUNTRY"));
				user.setState(resultSet.getString("STATE"));
				user.setPincode(resultSet.getString("PINCODE"));
				user.setMobile(resultSet.getString("MOBILE"));
				user.setLandlineNumber(resultSet.getString("LANDLINE_NUMBER"));
				user.setEmailId(resultSet.getString("EMAIL_ID"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setSecurityQuestion(resultSet.getString("SECURITY_QUESTION"));
				user.setSecurityAnswer(resultSet.getString("SECURITY_ANSWER"));	
				user.setRegistrationDate(resultSet.getDate("REGISTRATION_DATE"));
				user.setIpAddress(resultSet.getString("IP_ADDRESS"));
				user.setVarifiedStatus(resultSet.getString("VARIFIED_STATUS"));
				user.setVerificationDate(resultSet.getDate("VERIFICATION_DATE"));
				user.setUserType(resultSet.getString("USER_TYPE"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
