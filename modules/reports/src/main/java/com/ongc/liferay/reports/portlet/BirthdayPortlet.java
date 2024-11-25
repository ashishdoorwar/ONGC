package com.ongc.liferay.reports.portlet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.BirthDayWishDao;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.CommonUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;




/**
 * @author Ashish 
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ONGC REPORT",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Birthday",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/birthday/viewBirthday.jsp",
		"javax.portlet.name=" + ReportsPortletKeys.Birthday,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BirthdayPortlet extends MVCPortlet {
	 
	final static Log LOGGER = LogFactoryUtil.getLog(BirthdayPortlet.class.getName());
	private UserService userService=new UserServiceImpl();; 
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		byte[] jsonData =Files.readAllBytes(Paths.get("/ongcrevampdata/appdata/BirthDayJson/Birthday.json"));
		ObjectMapper mapper = new ObjectMapper();
		List<User> participantJsonList = mapper.readValue(jsonData,new TypeReference<List<User>>() {});
		renderRequest.setAttribute("users", participantJsonList);
		renderRequest.setAttribute("birthday", participantJsonList);
		super.doView(renderRequest, renderResponse);
	}
	
	public void saveBirthdayWishes(ActionRequest actionRequest,ActionResponse actionResponse) {
		String tocpf = ParamUtil.getString(actionRequest, "tocpf");
		String toname = ParamUtil.getString(actionRequest,"toname");
		String fromcpf = ParamUtil.getString(actionRequest,"fromcpf");
		String fromname = ParamUtil.getString(actionRequest,"fromname");
		StringBuffer xmlBuffer = new StringBuffer();
		boolean flag = false;
		String msg = null;
		BirthDayWishDao bdao = new BirthDayWishDao();
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/birthday/birthdayMsg.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {		
		
		if(tocpf.equalsIgnoreCase(fromcpf))
		{
			xmlBuffer.append("<pagecount>");
			xmlBuffer.append("<status>").append("false")
			.append("</status>");
			xmlBuffer.append("<errormsg2>").append("Sorry ! You can't send birthday wish to yourself.")
			.append("</errormsg2>");
			xmlBuffer.append("</pagecount>");
		}
		else {

			flag = bdao.saveBirthdayWishes(fromcpf,tocpf,fromname,toname);
		   if(flag){
		      msg = "Your message sent successfully.";
		        xmlBuffer.append("<pagecount>");
		    	xmlBuffer.append("<status>").append("true").append("</status>");
		     	xmlBuffer.append("<errormsg2>").append(msg).append("</errormsg2>");
	            xmlBuffer.append("</pagecount>");
			}else {
		        msg = "Oops !! Please try again";
		        xmlBuffer.append("<pagecount>");
		    	xmlBuffer.append("<status>").append("false").append("</status>");
		     	xmlBuffer.append("<errormsg2>").append(msg).append("</errormsg2>");
	            xmlBuffer.append("</pagecount>");
			}

		}
	}
	}
}