package com.ongc.liferay.csr.service.Impl;

import com.ongc.liferay.csr.dao.SubjectDao;
import com.ongc.liferay.csr.dao.Impl.SubjectDaoImpl;
import com.ongc.liferay.csr.model.SubjectBean;
import com.ongc.liferay.csr.service.SubjectService;

import java.util.List;

/**
 * @author Ranjeet
 */
public class SubjectServiceImpl implements SubjectService {

	private SubjectDao subjectService=new SubjectDaoImpl();
	@Override
	public List<SubjectBean> getSubjectList() {
		// TODO Auto-generated method stub
		return subjectService.getSubjectList();
	}

}
