package com.ongc.liferay.vigilance.service.Impl;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.service.ComplaintManagementService;

import java.sql.SQLException;
import java.util.List;

public class ComplaintManagementServiceImpl  implements ComplaintManagementService{

	@Override
	public int insertComplaint(VigilanceComplaint complaint, String complaintAgainst, String ipAddress)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getComplaintID(long uniqueKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int verifyComplaintDetail(long uniquKey, String complaintStatusActive) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<VigilanceComplaint> selectComplaintByUserID(int registrationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
