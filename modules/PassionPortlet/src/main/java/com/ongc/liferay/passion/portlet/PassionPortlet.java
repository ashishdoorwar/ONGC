package com.ongc.liferay.passion.portlet;

import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.Impl.GroupDaoImpl;
import com.ongc.liferay.passion.model.Employee;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author jiten
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Passion",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PassionPortletKeys.PASSION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PassionPortlet extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		System.out.println("Inside serveResource");
		JSONArray jsonArr = JSONFactoryUtil.createJSONArray();
		UserService userService=new UserServiceImpl();
		User userData=userService.getUser();
	    String q=ParamUtil.getString(resourceRequest,"q");
	    System.out.println(q);
	    GroupDaoImpl gDao = new GroupDaoImpl();
	    List<Employee> employeeName=new ArrayList<Employee>();
	    if(q!=null && !q.equals("")) {
	    	
	    	employeeName = gDao.getEmployeeName(userData.getCpfNo(), q);
	    }
	    System.out.println(employeeName.size());
	    for (Employee user : employeeName)
	    {
			   JSONObject json = JSONFactoryUtil.createJSONObject();
	           json.put("empCpf", user.getEmpCpf());
	           json.put("empName", user.getEmpName());
	           jsonArr.put(json);
		}
	    
        resourceResponse.getWriter().write(jsonArr.toString());
	}
}