package com.ongc.liferay.sponsorship.service;

import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponStatus;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;

import java.util.List;
/**
 *  
 * @author Ranjeet
 */
public interface SponsorshipService {

	List<SponsorshipBean> displayAllSponship(FilterBean filterBean);

	List<SubjectBean> getSubjectList();
	String getSubjectName(String subid);

	List<FilterBean> getStatusList();

	List<SubjectBean> getLocation();

	List<SponStatus> getProposalStatus() ;
	
	SponsorshipBean selectSponsorshipById(int id);
	
	String insertSponDetails(SponsorshipBean sponBean);
	
	int updateSponsorshipDetails(SponsorshipBean sponBean);
	String getSponsorshipStatusName(int statid);
	int deleteSponsorshipDetails(SponsorshipBean sponBean);
}
