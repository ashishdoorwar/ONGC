package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.AskExpertDao;
import com.ongc.liferay.reports.dao.Impl.AskExpertDaoImpl;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;
import com.ongc.liferay.reports.service.AskExpertService;

import java.util.List;
import java.util.Map;

public class AskExpertServiceImpl implements AskExpertService{

	private AskExpertDao askExpertDao = new AskExpertDaoImpl();
	
	@Override
	public List<AskExpert> getAllQueryList(AskExpertSearch askExpertSearch) {
		// TODO Auto-generated method stub
		return askExpertDao.getAllQueryList(askExpertSearch);
	}

	@Override
	public List<Domain> getDomainList() {
		// TODO Auto-generated method stub
		return askExpertDao.getDomainList();
	}

	@Override
	public List<Map<String, String>> getDomainExpertList(int domainId) {
		// TODO Auto-generated method stub
		return askExpertDao.getDomainExpertList(domainId);
	}

	@Override
	public boolean saveAskExpert(AskExpert askExpert) {
		// TODO Auto-generated method stub
		return askExpertDao.saveAskExpert(askExpert);
	}

	@Override
	public AskExpert viewAskExpert(int queryId) {
		// TODO Auto-generated method stub
		return askExpertDao.viewAskExpert(queryId);
	}

	@Override
	public List<ExpertReply> getAskExpertReplyList(int queryId) {
		// TODO Auto-generated method stub
		return askExpertDao.getAskExpertReplyList(queryId);
	}

	@Override
	public boolean updateAskExpert(AskExpert askExpert) {
		// TODO Auto-generated method stub
		return askExpertDao.updateAskExpert(askExpert);
	}

	@Override
	public boolean saveAskExpertComment(ExpertReply expertReply) {
		// TODO Auto-generated method stub
		return askExpertDao.saveAskExpertComment(expertReply);
	}

	@Override
	public List<AskExpert> getPendingPostList(int domainId) {
		// TODO Auto-generated method stub
		return askExpertDao.getPendingPostList(domainId);
	}

	@Override
	public List<AskExpert> getMyPostList(String cpfNo) {
		// TODO Auto-generated method stub
		return askExpertDao.getMyPostList(cpfNo);
	}

	@Override
	public int getDomainByCpf(String cpfNo) {
		// TODO Auto-generated method stub
		return askExpertDao.getDomainByCpf(cpfNo);
	}

	@Override
	public boolean checkExpertRole(String cpfNo) {
		// TODO Auto-generated method stub
		return askExpertDao.checkExpertRole(cpfNo);
	}

	@Override
	public boolean sendMailToExpert(AskExpert askexpert) {
		// TODO Auto-generated method stub
		return askExpertDao.sendMailToExpert(askexpert);
	}

	@Override
	public boolean checkExpertRoleForDomain(String cpfNo, int domainId) {
		// TODO Auto-generated method stub
		return askExpertDao.checkExpertRoleForDomain(cpfNo, domainId);
	}

}
