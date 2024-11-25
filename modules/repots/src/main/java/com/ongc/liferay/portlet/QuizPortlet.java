package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.DaoImpl.ReportQuizDaoImpl;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.QnBean;
import com.ongc.liferay.model.ReportQuizBean;
import com.ongc.liferay.model.User;
import com.ongc.liferay.service.IReportQuiz;
import com.ongc.liferay.serviceImpl.ReportQuizServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Quiz",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/admin/quez.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Quiz,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class QuizPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
//		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		UserDao userDao = new UserDao();
//		User u= userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress());
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet res = null;
//		boolean chck=false;
//		try {
//		conn= DatasourceConnection.getConnection();
//		System.out.println(conn);
//		String checpfno = u.getCpfNo();
//		String query = "select cpf_no from qn_res where cpf_no='"+ checpfno + "'";
//		stmt = conn.createStatement();
//		res = stmt.executeQuery(query);
//		if (!res.next()) {
//			chck = userDao.saveCpf(checpfno);
//		}
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		finally {
//			DatasourceConnection.closeConnection(conn, stmt, res);
//		}
//		renderRequest.setAttribute("chck", chck);
//		System.out.println("asdfadsf  ggggggggggggg"+chck);
		super.doView(renderRequest, renderResponse);
	}
	/*public void saveAns(ActionRequest actionRequest,ActionResponse actionResponse) {
		Connection conn=null;
		try {
		System.out.println("*********************** Inside Quiz save action command ***********************");
		ReportQuizDaoImpl quiz = new ReportQuizDaoImpl();
		long seed = System.nanoTime();
		List<QnBean> list1 = quiz.getRandomQuestionList();
		//for(QnBean ques:list1) {System.out.println(ques.getOrgUnit()+ques.getQid()+ques.getQnDesc()+ques.getQnNo());}
		Collections.shuffle(list1, new Random(seed));
		String question1 = null;
		String answerRecord="";
		conn = DatasourceConnection.getConnection();
//		String query="select ans from QN_ANS";
//		try{
//			Statement st= conn.createStatement(); 
//			ResultSet rs=st.executeQuery(query);
//			if(rs.next()) 
//			{ 
//				answerRecord = rs.getString(1);
//				}
//			}
//			catch (Exception e) {
//			e.printStackTrace();
//			}
		System.out.println("Getting data from DB====================>"+answerRecord);
		Integer quesans= ParamUtil.getInteger(actionRequest, "quesans");
		for (QnBean qnBean : list1.subList(0, 2)) {
			question1= ParamUtil.getString(actionRequest, qnBean.getQnNo());
			answerRecord+=qnBean.getQnNo().replace("question","")+"-"+question1+",";
		}
		System.out.println(answerRecord);
		
		String cpfno= ParamUtil.getString(actionRequest, "cpfno");
		ReportQuizBean ReportQuizObj = new ReportQuizBean();
		ReportQuizObj.setCpfno(cpfno);
		ReportQuizObj.setQuesans(answerRecord);
		IReportQuiz objReportQuizDao = new ReportQuizServiceImpl();
	    
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
	}*/
	
//	public void saveAns(ActionRequest actionRequest,ActionResponse actionResponse) {
//		
//	}
	
}
