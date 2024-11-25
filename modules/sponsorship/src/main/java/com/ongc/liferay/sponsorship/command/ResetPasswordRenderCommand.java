package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.User;
import com.ongc.liferay.sponsorship.service.UserService;
import com.ongc.liferay.sponsorship.service.Impl.UserServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		 property = {
				 "javax.portlet.name="+SponsorshipPortletKeys.SPONSORSHIP,
		 "mvc.command.name=reset_password"
		 }, service = MVCRenderCommand.class
		 )
public class ResetPasswordRenderCommand implements MVCRenderCommand{

	
	
	
	UserService userDao = new UserServiceImpl();
	private UserService userServie=new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String loginId=ParamUtil.getString(renderRequest, "loginId");
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		if(userDao.resetPassword(loginId)) {
			
//			addActionMessage("Password reset Successfully.");
			
			SessionMessages.add(renderRequest, "success");
		}
		else {
//			addActionMessage("some thing went wrong.");
		}
		List<User> userList = userServie.findAll();
		renderRequest.setAttribute("userList", userList);
		return "/sponsorship/listUsers.jsp";
		
	}
}

