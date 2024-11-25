package com.ongc.liferay.sponsorship.dao;

import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponStatus;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;

import java.util.List;

/**
 * @author Ranjeet
 */
public interface SponsorshipDao {

	List<SponsorshipBean> displayAllSponship(FilterBean filterBean);

	List<SubjectBean> getSubjectList();

	List<FilterBean> getStatusList();

	List<SubjectBean> getLocation();
	
	List<SponStatus> getProposalStatus() ;
	
	SponsorshipBean selectSponsorshipById(int id);
	
	String insertSponDetails(SponsorshipBean sponBean);
	
	int updateSponsorshipDetails(SponsorshipBean sponBean); 
	
	int deleteSponsorshipDetails(SponsorshipBean sponBean);

	String getSubjectName(String subid);
	String getSponsorshipStatusName(int statid);
}
