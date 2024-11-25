package com.ongc.liferay.DaoImpl;


import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.dao.IReportQuizDao;
import com.ongc.liferay.model.QnBean;
import com.ongc.liferay.model.QnBeanCpf;
import com.ongc.liferay.model.QnOptionBean;
import com.ongc.liferay.model.ReportQuizBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReportQuizDaoImpl implements IReportQuizDao{
	
	@Override
	public boolean ReportQuizAns(Connection conn, ReportQuizBean objBean){
		boolean flag = false;
		try {
			String sql="update qn_res set QN_NO =1,ANS_OPT =?, RES_DATE=NOW() WHERE CPF_NO= ?";
			PreparedStatement prepped = conn.prepareStatement(sql);
			prepped.setString(1, objBean.getQuesans());
			prepped.setString(2, objBean.getCpfno());
			int i = prepped.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList ReportQuizRes(Connection conn) throws SQLException {
		ArrayList al = new ArrayList();
		String query ="select cpf_no,initcap(emp_name),designation,initcap(place_posting), ans_opt, ans, TO_CHAR(res_date, 'DD-MM-YYYY HH24:MI') as res_date, diff from(SELECT qn_res.cpf_no, qn_res.ans_opt,qn_res.res_date, ongc_user_master.emp_name,ongc_user_master.designation,ongc_user_master.place_posting,extract(minute from qn_res.res_date - qn_res.strt_date) as diff FROM qn_res INNER JOIN ongc_user_master ON qn_res.cpf_no=ongc_user_master.cpf_number) as R, qn_ans";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				ReportQuizBean objReportQuizBean = new ReportQuizBean();
				objReportQuizBean.setCpfno(res.getString(1));
				objReportQuizBean.setUsname(res.getString(2));
				objReportQuizBean.setDesignation(res.getString(3));
				objReportQuizBean.setLocation(res.getString(4));
				objReportQuizBean.setQuesans(res.getString(5));
				objReportQuizBean.setAnswers(res.getString(6));
				objReportQuizBean.setResdate(res.getString(7));
				objReportQuizBean.setTimetaken(res.getString(8));
				al.add(objReportQuizBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}

		return al;
	}

	@Override
	public List<QnBean> getRandomQuestionList() {
		Connection conn = null;
		Connection conn1 = null;
		List<QnOptionBean> optionSet = null;
		List<QnBean> qnlist = null;
		String qnQuery = null;
		String optQuery = null;

		PreparedStatement pmst1 = null;
		ResultSet rs1 = null;

		PreparedStatement pmst2 = null;
		ResultSet rs2 = null;
		QnBean qn = null;
		QnOptionBean optionBean;
		String quid = null;
		try {

			qnQuery = "SELECT * FROM ques_master order by qid";
			qnlist = new LinkedList<QnBean>();
			conn = DatasourceConnection.getConnection();
			conn1 = DatasourceConnection.getConnection();
			pmst1 = conn.prepareStatement(qnQuery);
			rs1 = pmst1.executeQuery();
			while (rs1.next()) {
				qn = new QnBean();
				optionSet = new LinkedList<QnOptionBean>();
				qn.setQnDesc(rs1.getString("QDESC"));
				qn.setQnNo(rs1.getString("QN_NO"));
				quid = rs1.getString("QID");
				optQuery = "SELECT * FROM ques_option where QID=? order by optid";
				pmst2 = conn1.prepareStatement(optQuery);
				pmst2.setInt(1, Integer.parseInt(quid));
				rs2 = pmst2.executeQuery();
				while (rs2.next()) {
					optionBean = new QnOptionBean();
					optionBean.setQnOption(rs2.getString("QN_OPTION"));
					optionBean.setOp(rs2.getString("OP"));
					optionSet.add(optionBean);
				}
				qn.setQnOpList(optionSet);
				qnlist.add(qn);
				//for(QnBean q:qnlist) {System.out.println("Question==================================="+q);}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return qnlist;

	}

	@Override
	public List<QnBeanCpf> getcpfQuiz() {
		Connection conn = null;
		ArrayList<QnBeanCpf> al = new ArrayList<QnBeanCpf>();
		String query =" select * from CPF_QUIZ ";
		conn = DatasourceConnection.getConnection();
		QnBeanCpf qn = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
			qn = new QnBeanCpf();
			qn.setId(res.getString(1));
			qn.setCpfno(res.getString(2));
				
			   al.add(qn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	public String getCPFNoQuiz(String cpfNo) {
		Connection conn = null;
		String query =" SELECT CPF_NO FROM CPF_QUIZ WHERE CPF_NO='"+cpfNo+"'";
		conn = DatasourceConnection.getConnection();
		String cpf="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
			cpf=res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cpf;
	}
	
	@Override
	public List getcpfQuizresponse() {
		Connection conn = null;
		ArrayList al = new ArrayList();
		String query =" select CPF_NO from QN_RES ";
		conn = DatasourceConnection.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
			   al.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return al;
	}
	public String cpfQuizResponse(String cpfNo) {
		Connection conn = null;
		String query ="SELECT CPF_NO FROM QN_RES WHERE CPF_NO='"+cpfNo+"'";
		conn = DatasourceConnection.getConnection();
		String cpf="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
			   cpf=res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cpf;
	}

	@Override
	public String getSingleAns(String cpf) {
		Connection conn11 = null;
		PreparedStatement pstmt11=null;
		conn11 = DatasourceConnection.getConnection();
		ResultSet res11=null;
		String markrs="";
			try {
				String query11 =" select cpf_no,initcap(emp_name),designation,initcap(place_posting), ans_opt, ans, TO_CHAR(res_date, 'DD-MM-YYYY HH24:MI') as res_date, diff from(SELECT qn_res.cpf_no, qn_res.ans_opt,qn_res.res_date, ongc_user_master.emp_name,ongc_user_master.designation,ongc_user_master.place_posting,MINUTE(qn_res.res_date - qn_res.strt_date) as diff FROM qn_res INNER JOIN ongc_user_master ON qn_res.cpf_no=ongc_user_master.cpf_number  where qn_res.cpf_no='"+cpf+"'), qn_ans ";
			pstmt11 = conn11.prepareStatement(query11);
			res11 = pstmt11.executeQuery();
			
			String userX="";
			String originalXX="";
			while (res11.next()) {
				userX=res11.getString(5);
				originalXX=res11.getString(6);
			}
			int count=0;
			String userArr[]=userX.split(",");
			int userLength=userArr.length;
			String originalArr[]=originalXX.split(",");
			int originalLength=originalArr.length;
			int i=0,j=0;
			if(userLength !=0)
			{
			for(i=0;i<originalLength;i++)
			{
			for(j=0;j<userLength;j++)
			{
			if(originalArr[i].equals(userArr[j])) {
			count++;
			}
			}
			}
			}else {
			count=0;
			}
			  markrs=String.valueOf(count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn11.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return markrs;
	}



	/*	
	public ArrayList ReportQuizRes(Connection conn) throws SQLException {
		ArrayList al = new ArrayList();


		String query = "SELECT qn_res.cpf_no, ongc_user_master.emp_name, ongc_user_master.designation,ongc_user_master.place_posting,ongc_user_master.emp_level, qn_res.ans_opt, to_char(qn_res.res_date, 'dd-mm-yyyy') FROM qn_res INNER JOIN ongc_user_master ON qn_res.cpf_no=ongc_user_master.cpf_number";

		//system.out.println(query);
		try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();

		while (res.next()) {
			ReportQuizBean objReportQuizBean = new ReportQuizBean();
			objReportQuizBean.setCpfno(res.getString(1));
			objReportQuizBean.setUsname(res.getString(2));
			objReportQuizBean.setDesignation(res.getString(3));
			objReportQuizBean.setLocation(res.getString(4));
			objReportQuizBean.setEmplevel(res.getString(5));
			objReportQuizBean.setQuesans(res.getString(6));
			objReportQuizBean.setResdate(res.getString(7));
			al.add(objReportQuizBean);
		}

	} catch (Exception e) {
		//system.out.println(e);
	}finally{
		conn.close();
	}

		return al;
	}
	 */






}