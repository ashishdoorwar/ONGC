package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;

import java.util.List;
import java.util.Map;

public interface AskExpertDao {
	public List<AskExpert> getAllQueryList(AskExpertSearch askExpertSearch);
	public List<Domain> getDomainList();
	public List<Map<String, String>> getDomainExpertList(int domainId);
	public boolean saveAskExpert(final AskExpert askExpert);
	public AskExpert viewAskExpert(int queryId);
	public List<ExpertReply> getAskExpertReplyList(int queryId);
	public boolean updateAskExpert(AskExpert askExpert);
	//private int getNoOfReply(int queryid, Connection conn2) ;
	public boolean saveAskExpertComment(ExpertReply expertReply);
	public List<AskExpert> getPendingPostList(int domainId)	;
	public List<AskExpert> getMyPostList(String cpfNo)	;
	public int getDomainByCpf(String cpfNo)	;
	public boolean checkExpertRole(String cpfNo);
	public boolean sendMailToExpert(AskExpert askexpert);
	//private int getMaxIdFromTable(String tableName, String colName, Connection conn);
	public boolean checkExpertRoleForDomain(String cpfNo, int domainId);
}
