package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.MailStoriesDao;
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
			"javax.portlet.display-name=Email Article",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/admin/emailArticle.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.EmailArticle,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class EmailArticlePortlet extends MVCPortlet {

	public void saveemailArticle(ActionRequest actionRequest,ActionResponse actionResponse) {
		
		String name = ParamUtil.getString(actionRequest, "name");
		String email = ParamUtil.getString(actionRequest, "email");
		String message = ParamUtil.getString(actionRequest, "message");
		String fromName = ParamUtil.getString(actionRequest, "fromName");
		String fromCpf = ParamUtil.getString(actionRequest, "fromCpf");
		String pageurl = ParamUtil.getString(actionRequest, "pageurl");
		String pagetitle = ParamUtil.getString(actionRequest, "pagetitle");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/admin/emailArticle.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		//System.out.println("option4===>"+name+"option3====>"+email+"option2===>"+message+"option1===>"+fromCpf+"quetion=====>"+fromName);
		MailStoriesDao mailDao = new MailStoriesDao();
		boolean flag = mailDao.saveMailArticleRecord(fromCpf, fromName, name, email, message, pageurl,pagetitle);
	}
	}

	
}
