package com.ongc.liferay.vigilance.dao;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;

import java.util.List;



public interface ComplaintDao {
	
	public String submitComplaintDetail (VigilanceComplaint complaint , String otp , String ipAddress, VigilanceUser vigilanceUser);
	public void sendOPT(String email, String otp);
	public void sendOTP(String email, String otp,VigilanceComplaint complaint,String complaintNumber,VigilanceUser vigilanceUser);
	public String varifyComplaint(long uniquKey) ;
	public List<VigilanceComplaint> getComplaintByUserID(int registrationId);

}
