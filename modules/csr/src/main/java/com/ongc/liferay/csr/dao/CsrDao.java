package com.ongc.liferay.csr.dao;

import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;

import java.util.List;

/**
 *  
 * @author Ranjeet
 */
public interface CsrDao {

	public List<CSRProgramModel> getAll(FilterBean flterBean);
	public CSRProgramModel findByByRefNo(String refNo) ;
	public CSRProgramModel findById(int id) ;
	public CSRProgramModel view(CSRProgramModel model);
	public  String save(CSRProgramModel model);
	public  Boolean update(CSRProgramModel model);
}
