package com.ongc.liferay.serviceImpl;

import com.ongc.liferay.DaoImpl.ReportQuizDaoImpl;
import com.ongc.liferay.dao.IReportQuizDao;
import com.ongc.liferay.model.ReportQuizBean;
import com.ongc.liferay.service.IReportQuiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportQuizServiceImpl implements IReportQuiz{

	@Override
	public boolean ReportQuizAns(Connection conn, ReportQuizBean objBean)
			throws SQLException {
		IReportQuizDao objIReportQuizDao = new ReportQuizDaoImpl();
		return objIReportQuizDao.ReportQuizAns(conn, objBean);
	}

	@Override
	public ArrayList ReportQuizRes(Connection conn) throws SQLException {
		IReportQuizDao objIReportQuizDao = new ReportQuizDaoImpl();
		return objIReportQuizDao.ReportQuizRes(conn);

	}

	@Override
	public String getSingleAns(String cpf) throws SQLException {
		IReportQuizDao objIReportQuizDao = new ReportQuizDaoImpl();
		return objIReportQuizDao.getSingleAns(cpf);
	}
	


}
