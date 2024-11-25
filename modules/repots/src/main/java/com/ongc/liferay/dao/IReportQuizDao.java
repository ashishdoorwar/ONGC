package com.ongc.liferay.dao;

import com.ongc.liferay.model.QnBean;
import com.ongc.liferay.model.QnBeanCpf;
import com.ongc.liferay.model.ReportQuizBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IReportQuizDao {
	
	public boolean ReportQuizAns(Connection conn, ReportQuizBean objBean) throws SQLException;
	public ArrayList ReportQuizRes(Connection conn) throws SQLException;
    public List<QnBean> getRandomQuestionList();
    public List<QnBeanCpf> getcpfQuiz();
    public List getcpfQuizresponse();
    public String getSingleAns(String cpf);
} 
