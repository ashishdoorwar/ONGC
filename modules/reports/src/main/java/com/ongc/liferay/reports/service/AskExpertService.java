package com.ongc.liferay.reports.service;

import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;

import java.util.List;
import java.util.Map;

public interface AskExpertService {
	public List<AskExpert> getAllQueryList(AskExpertSearch askExpertSearch);
	public List<Domain> getDomainList();
	public List<Map<String, String>> getDomainExpertList(int domainId);
	public boolean saveAskExpert(final AskExpert askExpert);
	public AskExpert viewAskExpert(int queryId);
	public List<ExpertReply> getAskExpertReplyList(int queryId);
	public boolean updateAskExpert(AskExpert askExpert);
	public boolean saveAskExpertComment(ExpertReply expertReply);
	public List<AskExpert> getPendingPostList(int domainId)	;
	public List<AskExpert> getMyPostList(String cpfNo)	;
	public int getDomainByCpf(String cpfNo)	;
	public boolean checkExpertRole(String cpfNo);
	public boolean sendMailToExpert(AskExpert askexpert);
	public boolean checkExpertRoleForDomain(String cpfNo, int domainId);
}
