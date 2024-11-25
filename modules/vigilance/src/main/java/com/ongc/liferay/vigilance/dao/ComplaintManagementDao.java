package com.ongc.liferay.vigilance.dao;

import com.ongc.liferay.vigilance.model.ComplaintReply;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintManagementDao {

public int insertComplaint(VigilanceComplaint complaint, String complaintAgainst ,String ipAddress)  throws SQLException;
	
	public String getComplaintID(long uniqueKey);

	public int verifyComplaintDetail(long uniquKey,	String complaintStatusActive)  throws SQLException;

	public List<VigilanceComplaint> selectComplaintByUserID(int registrationId);
	
	public List<VigilanceComplaint> getComplaintList();
	
	public List<VigilanceComplaint> getUpdateComplaintList() ;
	
	public List<VigilanceComplaint> searchComplaintList(String startdate,
			String enddate, String complaintNo, String complaintStatus);
	
	public List<VigilanceComplaint> reportDateComplaintList(String startdate,
			String enddate, String act) ;
	
	public List<VigilanceComplaint> reportMonthComplaintList(int month,
			int year, String act);
	public VigilanceComplaint getComplaintDetails(int complaintId);
	
	public void updateComplaintStatus(int complaintId) ;
	
	public List<ComplaintReply> getComplaintReplyList(int complaintId);
	public int updateSAPComplaint(String startDate, String action,String remarks, String sapNo, int complaintId, String otherStatus);
}
