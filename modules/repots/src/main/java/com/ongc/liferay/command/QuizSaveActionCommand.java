package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.DaoImpl.ReportQuizDaoImpl;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.model.QnBean;
import com.ongc.liferay.model.ReportQuizBean;
import com.ongc.liferay.service.IReportQuiz;
import com.ongc.liferay.serviceImpl.ReportQuizServiceImpl;
import com.ongc.liferay.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.Quiz,
			"mvc.command.name=saveAns"
		},
		service = MVCActionCommand.class
	)
public class QuizSaveActionCommand extends BaseMVCActionCommand{
	
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		Connection conn=null;
		conn = DatasourceConnection.getConnection();
		String question1 = null;
		String answerRecord="";
		ReportQuizDaoImpl quiz = new ReportQuizDaoImpl();
		long seed = System.nanoTime();
		List<QnBean> list1 = quiz.getRandomQuestionList();
//		for(QnBean ques:list1) {System.out.println(ques.getOrgUnit()+ques.getQid()+ques.getQnDesc()+ques.getQnNo());}
		Collections.shuffle(list1, new Random(seed));
		String questions = ParamUtil.getString(actionRequest, "questions");
		for (String qnBean : questions.split(",")) {
			question1= ParamUtil.getString(actionRequest, qnBean);
			answerRecord+=qnBean.replace("question","")+"-"+question1+",";
		}	
//		System.out.println(answerRecord);
		String cpfno= ParamUtil.getString(actionRequest, "cpfno");
//		System.out.println("CPFNO==============="+cpfno);
		ReportQuizBean ReportQuizObj = new ReportQuizBean();
		ReportQuizObj.setCpfno(cpfno);
		ReportQuizObj.setQuesans(answerRecord);
		IReportQuiz objReportQuizDao = new ReportQuizServiceImpl();
	    
			try {
				boolean flag = objReportQuizDao.ReportQuizAns(conn, ReportQuizObj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}
