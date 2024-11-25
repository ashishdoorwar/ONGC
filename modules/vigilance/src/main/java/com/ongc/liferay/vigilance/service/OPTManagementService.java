package com.ongc.liferay.vigilance.service;


public interface OPTManagementService {
	
	public int insertOPT(String mobileNumber, String otp , String otpType);
	
	public String getLatestOTP(String phoneNumber , String otpType);
	public String getLoginOtpVerified(String emailID);

}
