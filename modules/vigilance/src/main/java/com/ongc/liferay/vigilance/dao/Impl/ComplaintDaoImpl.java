package com.ongc.liferay.vigilance.dao.Impl;
import com.ongc.liferay.vigilance.dao.ComplaintDao;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.service.ComplaintService;
import com.ongc.liferay.vigilance.util.ONGCUtil;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.sql.SQLException;
import java.util.List;


public class ComplaintDaoImpl implements ComplaintDao{
	
	@Override
	public String submitComplaintDetail(VigilanceComplaint complaint , String otp , String ipAddress ,VigilanceUser vigilanceUser) {
		
		StringBuffer com_against = new StringBuffer();
		com_against.append(complaint.getTitle()==null?"":complaint.getTitle()).append("  "+ complaint.getFirstName()==null?" ":complaint.getFirstName()).append("  "+ complaint.getMiddleName()==null?" ":complaint.getMiddleName()).append("  "+complaint.getLastName()==null?" ":complaint.getLastName());
		int i;
		try {
			i = VigilanceFactory.getComplaintManagementInstance().insertComplaint(complaint,com_against.toString(),ipAddress);
			if (i > 0 ) {
				ONGCUtil.getInstance().sendComplaintOnAdminEmail(complaint.getComplaintSubject(), VigilanceConstant.REGISTER_COMPLAINT);
				//return VigilanceConstant.SUCCESS;
				return String.valueOf(i);
			} else {
				return VigilanceConstant.FALIURE; 
			}
		} catch (SQLException e) {
			return VigilanceConstant.FALIURE;
		}
	}

	@Override
	public void sendOPT(String email , String otp) {
		ONGCUtil.getInstance().sendOTPOnEmail(email, otp, "loginOtp");
	}
	
	@Override
	public void sendOTP(String email , String otp, VigilanceComplaint complaint,String complaintNumber,VigilanceUser vigilanceUser) {
		ONGCUtil.getInstance().sendComplaintOTPOnEmail(email, otp, complaint,complaintNumber,vigilanceUser);
	}

	@Override
	public String varifyComplaint(long uniquKey) {
		try {
			int i = VigilanceFactory.getComplaintManagementInstance().verifyComplaintDetail(uniquKey, VigilanceConstant.COMPLAINT_STATUS_ACTIVE);
			if (i == 1) {
				// mail send code 
			}
			return VigilanceConstant.SUCCESS;
			
		} catch (SQLException e) {
			return VigilanceConstant.FALIURE;
		}
	}

	@Override
	public List<VigilanceComplaint> getComplaintByUserID(int registrationId) {
		return VigilanceFactory.getComplaintManagementInstance().selectComplaintByUserID(registrationId);
	}


}
