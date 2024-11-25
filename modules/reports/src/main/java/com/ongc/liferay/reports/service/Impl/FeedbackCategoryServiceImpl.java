package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.FeedbackCategoryDao;
import com.ongc.liferay.reports.dao.Impl.FeedbackCategoryDaoImpl;
import com.ongc.liferay.reports.model.FeedbackCategory;
import com.ongc.liferay.reports.service.FeedbackCategoryService;

import java.util.ArrayList;
import java.util.List;

public class FeedbackCategoryServiceImpl implements FeedbackCategoryService{

	private FeedbackCategoryDao feedbackCategoryDao=new FeedbackCategoryDaoImpl();
	
	@Override
	public boolean saveFeedbackCategory(FeedbackCategory category) {
		
		return feedbackCategoryDao.saveFeedbackCategory(category);
	}

	@Override
	public List<FeedbackCategory> getCategoryListByFeedbakId(int feedbackId) {
		
		return feedbackCategoryDao.getCategoryListByFeedbakId(feedbackId);
	}

	@Override
	public boolean roleCheck(String userCpf) {
		
		return feedbackCategoryDao.roleCheck(userCpf);
	}

	@Override
	public String getHREnablersRole(String cpf) {
		
		return feedbackCategoryDao.getHREnablersRole(cpf);
	}

	@Override
	public String getLocation(String userCpf) {
		
		return feedbackCategoryDao.getLocation(userCpf);
	}

	@Override
	public String getsubLocation(String userCpf) {
		
		return feedbackCategoryDao.getsubLocation(userCpf);
	}

	@Override
	public ArrayList getCatId(String userCpf) {
		
		return feedbackCategoryDao.getCatId(userCpf);
	}

}
