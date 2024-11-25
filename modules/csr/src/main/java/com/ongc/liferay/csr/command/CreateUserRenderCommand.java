package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.service.UserService;
import com.ongc.liferay.csr.service.Impl.UserServiceImpl;
import com.ongc.liferay.csr.util.Base64Decoder;
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
		 "mvc.command.name=createUser"
		 }, service = MVCRenderCommand.class
		 )
public class CreateUserRenderCommand implements MVCRenderCommand{
	private UserService userServie=new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo=ParamUtil.getString(renderRequest, "cpfNo");
		String userName=ParamUtil.getString(renderRequest, "userName");
		String location=ParamUtil.getString(renderRequest, "location");
		String loginId=ParamUtil.getString(renderRequest, "loginId");
		
		User user=new User();
		
		user.setCpfNo(cpfNo);
		user.setUserName(userName);
		user.setLocation(location);
		user.setLoginId(loginId);
		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
			return "/csr/createuserForm.jsp";
			}
		else {
			
		userServie.saveUserDetails(user);
		
		List<User> userList = userServie.findAll();
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		renderRequest.setAttribute("userList", userList);
		return "/csr/listUsers.jsp";
		}
	}

}
