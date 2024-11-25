package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.Covid19Dao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;
import com.ongc.liferay.util.CommonUtil;
import com.ongc.liferay.util.CovidSendMail;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
			"javax.portlet.display-name=Covid-19",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/covid/homeCovid19.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Covid19,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class Covid19Portlet extends MVCPortlet{
	
	private UserDao dao = new UserDao();
	private Covid19Dao cdao = new Covid19Dao();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doView(renderRequest, renderResponse);
	}
	
	public void savecovidForm(ActionRequest actionRequest,ActionResponse actionResponse) {
		String msg = ParamUtil.getString(actionRequest, "comment");
		String cpfNo = ParamUtil.getString(actionRequest, "cpfNo");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/covid/homeCovid19.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String subject ="ONGC COVID-19 Assistance for you OCAY";
			CovidSendMail covidSendMail = new CovidSendMail();
			//flag=CovidSendMail.sendCovid19Mail(subject,msg,user);
			String str = "";
			boolean flag = false;
			try {
				User user = dao.getUserByCPFNumber(cpfNo);
				if (user == null) {
					//String cn = renderRequest.getRemoteUser();
					//user=dao.getUserByCPFNumber(cn);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
				String cpfno = user.getCpfNo();
				flag = cdao.insertCovid19(cpfno, msg);
				if (flag ) //&& covidSendMail.sendCovid19Mail(subject,msg,user)
				{
					str = "successfully";
				} else {
					str = "failed";

				}

			} catch (Exception e) {
				str = "error";
			}
		}
	}
}
