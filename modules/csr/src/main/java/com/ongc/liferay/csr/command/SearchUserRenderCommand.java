package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.service.UserService;
import com.ongc.liferay.csr.service.Impl.UserServiceImpl;

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
		 "mvc.command.name=search_user"
		 }, service = MVCRenderCommand.class
		 )
public class SearchUserRenderCommand  implements MVCRenderCommand {
	private UserService userServie=new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo=ParamUtil.getString(renderRequest, "cpfNo");
		String location=ParamUtil.getString(renderRequest, "location");
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		User user=new User();
		user.setCpfNo(cpfNo);
		user.setLocation(location);
		
		List<User> userList = userServie.getSearchUserList(user);
		
		renderRequest.setAttribute("userList", userList);
		return "/csr/listUsers.jsp";
	}

}
