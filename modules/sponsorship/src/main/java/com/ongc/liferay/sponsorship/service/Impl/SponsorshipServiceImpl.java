package com.ongc.liferay.sponsorship.service.Impl;

import com.ongc.liferay.sponsorship.dao.SponsorshipDao;
import com.ongc.liferay.sponsorship.dao.Impl.SponsorshipDaoImpl;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponStatus;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;

import java.util.List;
/**
 *  
 * @author Ranjeet
 */
public class SponsorshipServiceImpl implements SponsorshipService{

	private SponsorshipDao sponsorshipDao=new SponsorshipDaoImpl();
	@Override
	public List<SponsorshipBean> displayAllSponship(FilterBean filterBean) {
		return sponsorshipDao.displayAllSponship(filterBean);
	}
	@Override
	public List<SubjectBean> getSubjectList() {
		// TODO Auto-generated method stub
		return sponsorshipDao.getSubjectList();
	}
	@Override
	public List<FilterBean> getStatusList() {
		// TODO Auto-generated method stub
		return sponsorshipDao.getStatusList();
	}
	@Override
	public List<SubjectBean> getLocation() {
		// TODO Auto-generated method stub
		return sponsorshipDao.getLocation();
	}
	@Override
	public List<SponStatus> getProposalStatus() {
		// TODO Auto-generated method stub
		return sponsorshipDao.getProposalStatus();
	}
	@Override
	public SponsorshipBean selectSponsorshipById(int id) {
		// TODO Auto-generated method stub
		return sponsorshipDao.selectSponsorshipById(id);
	}
	@Override
	public String insertSponDetails(SponsorshipBean sponBean) {
		// TODO Auto-generated method stub
		return sponsorshipDao.insertSponDetails(sponBean);
	}
	@Override
	public int updateSponsorshipDetails(SponsorshipBean sponBean) {
		// TODO Auto-generated method stub
		return sponsorshipDao.updateSponsorshipDetails(sponBean);
	}
	@Override
	public int deleteSponsorshipDetails(SponsorshipBean sponBean) {
		// TODO Auto-generated method stub
		return sponsorshipDao.deleteSponsorshipDetails(sponBean);
	}
	@Override
	public String getSubjectName(String subid) {
		// TODO Auto-generated method stub
		return sponsorshipDao.getSubjectName(subid);
	}
	@Override
	public String getSponsorshipStatusName(int statid) {
		// TODO Auto-generated method stub
		return sponsorshipDao.getSponsorshipStatusName(statid);
	}

}
