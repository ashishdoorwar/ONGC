package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.dao.Impl.UserDaoImpl;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.service.UserService;
import com.ongc.liferay.csr.service.Impl.UserServiceImpl;
import com.ongc.liferay.csr.util.CommonUtil;

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
		 "javax.portlet.name="+CsrPortletKeys.CSR,
		 "mvc.command.name=reset_password"
		 }, service = MVCRenderCommand.class
		 )
public class ResetPasswordRenderCommand implements MVCRenderCommand{

	
	
	
	UserService userDao = new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		String loginId=ParamUtil.getString(renderRequest, "loginId");
		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
			return "/csr/listUsers.jsp";
			}
		else {
			
		if(userDao.resetPassword(loginId)) {
			SessionMessages.add(renderRequest, "success");
//			addActionMessage("Password reset Successfully.");
		}
		else {
//			addActionMessage("some thing went wrong.");
		}
		List<User> userList = userDao.findAll();
		renderRequest.setAttribute("userList", userList);
		return "/csr/listUsers.jsp";
		}
		
	}
}

