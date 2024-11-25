package com.ongc.liferay.vigilance.dao;

import java.util.Date;

public interface OPTManagementDao {
	
	public int insertOPT(String mobileNumber, String otp , String otpType);
	
	public String getLatestOTP(String phoneNumber , String otpType);
	public int insertLoginOPT(String otp,String emailId);
	public String getLoginOtpVerified(String emailID);
}
