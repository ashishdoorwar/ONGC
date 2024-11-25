package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
import com.ongc.liferay.vigilance.model.VigilanceAdminUser;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.VigilanceFactory;

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
				"javax.portlet.name="+VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=updateComplaint"
		}, service = MVCRenderCommand.class
		)
public class UpdateComplaintRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		VigilanceAdminUser userData = VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(login);
		renderRequest.setAttribute("userData", userData);
		
		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		  List<VigilanceComplaint> complaintList=null;
			  complaintList=comp.getUpdateComplaintList();
			  renderRequest.setAttribute("complaintList", complaintList);
		return "/jsp/updateComplaintList.jsp";
	}

}
