package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
				"mvc.command.name=search_user"
		}, service = MVCRenderCommand.class
		)
public class SearchUserRenderCommand  implements MVCRenderCommand {
	private UserService userServie=new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo=ParamUtil.getString(renderRequest, "cpfNo");
		String location=ParamUtil.getString(renderRequest, "location");
		User user=new User();
		user.setCpfNo(cpfNo);
		user.setLocation(location);
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		List<User> userList = userServie.getSearchUserList(user);
		renderRequest.setAttribute("userList", userList);
		return "/sponsorship/listUsers.jsp";
	}

}
