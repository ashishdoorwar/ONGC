package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.QuizAdminDao;
import com.ongc.liferay.util.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			"javax.portlet.display-name=Admin",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/admin/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Admin,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}
	
	public void insertQuestions(ActionRequest actionRequest,ActionResponse actionResponse) {
		
		String option4 = ParamUtil.getString(actionRequest, "option4");
		String option3 = ParamUtil.getString(actionRequest, "option3");
		String option2 = ParamUtil.getString(actionRequest, "option2");
		String option1 = ParamUtil.getString(actionRequest, "option1");
		String qdesc = ParamUtil.getString(actionRequest, "qdesc");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/admin/quizAdmin.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
//		System.out.println("option4===>"+option4+"option3====>"+option3+"option2===>"+option2+"option1===>"+option1+"quetion=====>"+qdesc);
		QuizAdminDao qDao= new QuizAdminDao();
		int i=0;
		List<String> lst = new ArrayList<String>();
		lst.add(option1);
		lst.add(option2);
		lst.add(option3);
		lst.add(option4);		
		i=qDao.insertQues(qdesc,lst);
		}
	}
}
