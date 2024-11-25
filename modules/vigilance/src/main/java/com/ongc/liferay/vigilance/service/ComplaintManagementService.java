package com.ongc.liferay.vigilance.service;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintManagementService {
public int insertComplaint(VigilanceComplaint complaint, String complaintAgainst ,String ipAddress)  throws SQLException;
	
	public String getComplaintID(long uniqueKey);

	public int verifyComplaintDetail(long uniquKey,	String complaintStatusActive)  throws SQLException;

	public List<VigilanceComplaint> selectComplaintByUserID(int registrationId);
}
