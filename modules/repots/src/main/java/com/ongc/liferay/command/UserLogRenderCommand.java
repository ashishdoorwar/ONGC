package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.SessionDto;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Admin,
		 "mvc.command.name=userLog"
		 }, service = MVCRenderCommand.class
		 )
public class UserLogRenderCommand implements MVCRenderCommand{

	private UserDao userDao=new UserDao();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		List<SessionDto> userLogRecord = userDao.getUserLogRecord();
		renderRequest.setAttribute("userLogRecord", userLogRecord);
		return "/admin/userLog.jsp";
	}

}
