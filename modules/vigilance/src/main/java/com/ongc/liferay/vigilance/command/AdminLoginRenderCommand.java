package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
import com.ongc.liferay.vigilance.exception.UserLoginException;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.TEA;
import com.ongc.liferay.vigilance.util.VigilanceConstant;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import nl.captcha.servlet.CaptchaServletUtil;

/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=login"
		}, service = MVCRenderCommand.class
		)
public class AdminLoginRenderCommand implements MVCRenderCommand  {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String userName=ParamUtil.getString(renderRequest, "username");
		String password=ParamUtil.getString(renderRequest, "password");
		if (userName.trim().equals("") || password.trim().equals("")) {
			SessionErrors.add(renderRequest, "Enter UserName");	
			SessionErrors.add(renderRequest, "Enter Password");	
		} 
		String answer= 	ParamUtil.getString(renderRequest,"captchText");

		if(! CaptchaServletUtil.checkCaptcha(answer, renderRequest)) {
			SessionErrors.add(renderRequest, "errorMsg");
			return "/jsp/adminLogin.jsp";
		}
		else {
			String encPass = TEA.encrypt(password, userName);
			VigilanceAdminUser adminUser = VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(userName);
			if(adminUser!=null) {

				if(encPass.equals(adminUser.getPassword())) {
					ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
					List<VigilanceComplaint> complaintList=null;
					complaintList=comp.getComplaintList();
					renderRequest.setAttribute("complaintList", complaintList);
					final PortletSession psession = renderRequest.getPortletSession();
					psession.setAttribute("loginId", userName, PortletSession.APPLICATION_SCOPE);
					renderRequest.setAttribute("loginId", userName);
					if(adminUser != null ) {
						return "/jsp/adminHome.jsp";	
					}
				}else {
					SessionErrors.add(renderRequest, "Incorrect password");	
				}
			}else {
				SessionErrors.add(renderRequest, "Incorrect Username");	
			}
		}
		return "/jsp/adminLogin.jsp";
	}


}
