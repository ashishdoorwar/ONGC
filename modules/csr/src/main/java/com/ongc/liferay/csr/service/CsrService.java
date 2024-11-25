package com.ongc.liferay.csr.service;

import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;

import java.util.List;

/**
 * @author Ranjeet
 */
public interface CsrService {

	List<CSRProgramModel> findAll(FilterBean filterBean);
	CSRProgramModel findById(int id); 
	public  String save(CSRProgramModel model);
	public  Boolean update(CSRProgramModel model);
}
