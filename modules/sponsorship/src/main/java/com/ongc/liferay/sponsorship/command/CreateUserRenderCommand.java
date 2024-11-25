package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.User;
import com.ongc.liferay.sponsorship.service.UserService;
import com.ongc.liferay.sponsorship.service.Impl.UserServiceImpl;
import com.ongc.liferay.sponsorship.util.CommonUtil;

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
		 "mvc.command.name=createUser"
		 }, service = MVCRenderCommand.class
		 )
public class CreateUserRenderCommand implements MVCRenderCommand{
	private UserService userServie=new UserServiceImpl();
	
	private static final Log LOGGER = LogFactoryUtil.getLog(CreateUserRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo=ParamUtil.getString(renderRequest, "cpfNo");
		String userName=ParamUtil.getString(renderRequest, "userName");
		String location=ParamUtil.getString(renderRequest, "location");
		String emailId=ParamUtil.getString(renderRequest, "emailId");
		String designation=ParamUtil.getString(renderRequest, "designation");
		String mobileNo=ParamUtil.getString(renderRequest, "mobileNo");
		String aboutMe=ParamUtil.getString(renderRequest, "aboutMe");
		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
			return "/sponsorship/createuserForm.jsp";
			}
		else {
			
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		User user=new User();
		
		user.setCpfNo(cpfNo);
		user.setUserName(userName);
		user.setLocation(location);
		user.setLoginId(emailId);
		user.setMobileNo(mobileNo);
		user.setAboutMe(aboutMe);
		user.setDesignation(designation);
		
		LOGGER.info(user);
		userServie.saveUserDetails(user);
		List<User> userList = userServie.findAll();
		renderRequest.setAttribute("userList", userList);
		return "/sponsorship/listUsers.jsp";
		}
	}

}
