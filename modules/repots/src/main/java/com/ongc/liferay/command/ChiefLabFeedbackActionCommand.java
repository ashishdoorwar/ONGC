package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.Covid19Dao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.util.CommonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.Feedback,
			"mvc.command.name=savechieflabFeedForm"
		},
		service = MVCActionCommand.class
	)
public class ChiefLabFeedbackActionCommand extends BaseMVCActionCommand{

	private UserDao userDao = new UserDao();
	private Covid19Dao cdao = new Covid19Dao();	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String cpfNo= ParamUtil.getString(actionRequest, "cpfNo");
		String firstName= ParamUtil.getString(actionRequest, "firstName");
		String lastName= ParamUtil.getString(actionRequest, "lastName");
		String mobile= ParamUtil.getString(actionRequest, "mobile");
		String email= ParamUtil.getString(actionRequest, "email");
		String comment= ParamUtil.getString(actionRequest, "comment");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.sendRedirect("mvcPath", "/chiefLabs/view.jsp");
		} else {
			//System.out.println("Inside Action Command===================>"+cpfNo+ firstName+ lastName+ mobile+ email+ comment);
			boolean flag = cdao.insertChiefLabFeedback(cpfNo, firstName, lastName, mobile, email, comment);
			String msg;
			if(flag) {
				msg = "successfully";
				actionRequest.setAttribute("msg",msg);
			}else {
				msg = "failed";
				actionRequest.setAttribute("msg",msg);
			}
		}
	}
}
