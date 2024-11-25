package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.FeedbackCategory;

import java.util.ArrayList;
import java.util.List;

public interface FeedbackCategoryDao {

	public boolean saveFeedbackCategory(FeedbackCategory category);
	public List<FeedbackCategory> getCategoryListByFeedbakId(int feedbackId);
	public boolean roleCheck(String userCpf);
	public String getHREnablersRole(String cpf);
	public String getLocation(String userCpf);
	public String getsubLocation(String userCpf);
	public ArrayList getCatId(String userCpf);
}
