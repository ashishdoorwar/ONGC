package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.PassionDao;
import com.ongc.liferay.reports.dao.Impl.PassionDaoImpl;
import com.ongc.liferay.reports.model.Passion;
import com.ongc.liferay.reports.model.SubPassion;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.PassionService;

import java.sql.Connection;
import java.util.List;

public class PassionServiceImpl implements PassionService{
	private PassionDao passionDao=new PassionDaoImpl();
	@Override
	public List<Passion> getUserPassion(String cpfNo) {
		return this.passionDao.getUserPassion(cpfNo);
	}
	@Override
	public String getUserPassionMPID(String pID, String subID, String cpf) {
		// TODO Auto-generated method stub
		return passionDao.getUserPassionMPID(pID, subID, cpf);
	}
	@Override
	public List<Passion> getPassionName() {
		// TODO Auto-generated method stub
		return passionDao.getPassionName();
	}
	@Override
	public List<SubPassion> getSubPassionName(String passionID) {
		// TODO Auto-generated method stub
		return passionDao.getSubPassionName(passionID);
	}
	@Override
	public void updatePassionCat1ByPsnId(Passion passionFirst, String cpfNumber, String passionFirstPsnMpId,
			Connection con) {
		passionDao.updatePassionCat1ByPsnId(passionFirst, cpfNumber, passionFirstPsnMpId, con);
		
	}
	@Override
	public boolean submitUserPassion(User user, Passion passion) {
		// TODO Auto-generated method stub
		return passionDao.submitUserPassion(user, passion);
	}

}
