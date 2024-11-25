package com.ongc.liferay.vigilance.service.Impl;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.service.ComplaintService;

import java.util.List;


public class ComplaintServiceImpl implements ComplaintService{

	@Override
	public String submitComplaintDetail(VigilanceComplaint complaint, String otp, String ipAddress,
			VigilanceUser vigilanceUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendOPT(String email, String otp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String varifyComplaint(long uniquKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VigilanceComplaint> getComplaintByUserID(int registrationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
