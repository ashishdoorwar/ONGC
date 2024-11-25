package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;

import java.util.List;


public interface ComplaintService {
	
	public String submitComplaintDetail (VigilanceComplaint complaint , String otp , String ipAddress, VigilanceUser vigilanceUser);

	public void sendOPT(String email, String otp);
	
	public String varifyComplaint(long uniquKey) ;

	public List<VigilanceComplaint> getComplaintByUserID(int registrationId);

}
