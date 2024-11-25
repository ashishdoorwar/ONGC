package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.BirthDayWishDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;
import com.ongc.liferay.util.GetBirthdayUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.annotations.RenderMethod;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Birthday",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/birthday/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Birthday,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class BirthdayPortlet extends MVCPortlet {

	private UserDao userDao=new UserDao();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		GetBirthdayUtil getBirthdayUtil=new GetBirthdayUtil();
		List<User> birthday = getBirthdayUtil.getBirthday(renderRequest);
		renderRequest.setAttribute("birthday", birthday);
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
//		System.out.println("tocpfequalsIgnoreCasefromcpf===============>"+tocpf.equalsIgnoreCase(fromcpf)+"tocpf============>"+tocpf+"<br>FromCpf=====>"+fromcpf);
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
