package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.UserDao;
import com.ongc.liferay.reports.dao.Impl.UserDaoImpl;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.util.GetBirthdayUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Birthday,
		 "mvc.command.name=getBirthdayInfo"
		 }, service = MVCRenderCommand.class
		 )

public class GetBirthdayDateWiseRenderCommand implements MVCRenderCommand{
	public final static String renderingBirthdayPagePath = "/birthday/showBirthdayPage.jsp";
	
	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		GetBirthdayUtil getBirthdayUtil=new GetBirthdayUtil();
		List<User> birthday = getBirthdayUtil.getBirthday(renderRequest);
		renderRequest.setAttribute("birthday", birthday);
		return renderingBirthdayPagePath;
	}
}
