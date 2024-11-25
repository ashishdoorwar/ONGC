package com.ongc.liferay.service;

import com.ongc.liferay.model.ReportQuizBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IReportQuiz {
	public boolean ReportQuizAns(Connection conn, ReportQuizBean objBean) throws SQLException;
	public ArrayList ReportQuizRes(Connection conn) throws SQLException;
	public String getSingleAns(String  cpf) throws SQLException;

}
