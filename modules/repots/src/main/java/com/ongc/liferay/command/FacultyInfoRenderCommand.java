package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PassionDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.Passion;
import com.ongc.liferay.model.User;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Faculty,
		 "mvc.command.name=getEmployeeInfo"
		 }, service = MVCRenderCommand.class
		 )

public class FacultyInfoRenderCommand implements MVCRenderCommand{
	public final static String renderingFacultyPagePath = "/faculty/viewFacultyDataForm.jsp";
	
	private UserDao userDao=new UserDao();
	private PassionDao passionDao= new PassionDao();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String facid= ParamUtil.getString(renderRequest, "facid");
		renderRequest.setAttribute("facid", facid);
		return renderingFacultyPagePath;
	}
}
