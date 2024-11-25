package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.DiscussionDao;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl;
import com.ongc.liferay.passion.dao.Impl.UserDaoImpl;
import com.ongc.liferay.passion.model.DiscussionBoard;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		 property = {
		 "javax.portlet.name="+PassionPortletKeys.PASSION,
		 "mvc.command.name=view_Discussion_Cloud"
		 }, service = MVCRenderCommand.class
		 )
public class DiscussionViewRenderCommand implements MVCRenderCommand{

	private DiscussionDao dDao=new DiscussionDaoImpl();		
	private UserService userService=new UserServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		User user=userService.getUser();
		System.out.println("Inside render command");
		String q=ParamUtil.getString(renderRequest, "q");
		List<DiscussionBoard> discussionBoards=dDao.fetchTopics(q, user.getCpfNo());
		renderRequest.setAttribute("discussionBoards", discussionBoards);
		return "/jsp/viewDiscussion.jsp";
	}

}
