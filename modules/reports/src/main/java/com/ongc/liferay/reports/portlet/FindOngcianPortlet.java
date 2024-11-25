package com.ongc.liferay.reports.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.model.Passion;
import com.ongc.liferay.reports.model.SubPassion;
import com.ongc.liferay.reports.service.PassionService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.PassionServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.CommonUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;


@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ONGC REPORT",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Find Ongcian",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/findOngcian/viewFindOngcian.jsp",
		"javax.portlet.name=" + ReportsPortletKeys.FindOngcian,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FindOngcianPortlet extends MVCPortlet {
	private UserService userService = new UserServiceImpl();
	private PassionService passionService = new PassionServiceImpl(); 
		
	public void thankNote(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		String tocpfNo= ParamUtil.getString(actionRequest, "tocpfNo");
		String fromcpfNo= ParamUtil.getString(actionRequest, "fromcpfNo");
		String message= ParamUtil.getString(actionRequest, "message");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);

		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.sendRedirect("mvcPath", "/findOngcian/viewFindOngcian.jsp");
		} else {		
		boolean flag=false;
		String msg=null;
		flag = userService.saveThanksnote(fromcpfNo, tocpfNo, message);
		if (flag) {
			boolean flag1 = false;
			flag1 = userService.check_cpf(tocpfNo);
			if (flag1) {
				userService.saveUser(tocpfNo, "thanknote1");
			} else{
				userService.updateUser(tocpfNo, "thanknote1");
			}
			boolean flag2 = false;
			flag2 = userService.check_cpf(fromcpfNo);
			if (flag2) {
				userService.saveUser(fromcpfNo, "thanknote2");
			} else{
				userService.updateUser(fromcpfNo, "thanknote2");
			}
			msg = "Your thank note is submitted successfully.";
		} else {
			msg = "Oops !! Please try again";
		}
			//super.processAction(actionRequest, actionResponse);
	}
}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		String passionId = ParamUtil.getString(resourceRequest, "passionId");
		List<SubPassion> subPassionName = passionService.getSubPassionName(passionId);
		resourceRequest.setAttribute("subPassionName", subPassionName);
		JSONArray jsonArr = JSONFactoryUtil.createJSONArray();
	    for (SubPassion user : subPassionName)
	    {
			   JSONObject json = JSONFactoryUtil.createJSONObject();
	           json.put("id", user.getSubPassionId());
	           json.put("name", user.getSubPassion());
	           jsonArr.put(json);
		}
        resourceResponse.getWriter().write(jsonArr.toString());
		super.serveResource(resourceRequest, resourceResponse);
	}

}
