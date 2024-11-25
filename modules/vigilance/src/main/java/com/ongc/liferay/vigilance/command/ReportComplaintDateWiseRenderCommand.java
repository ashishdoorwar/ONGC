package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
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
				"mvc.command.name=reportComplaintDateWise"
		}, service = MVCRenderCommand.class
		)
public class ReportComplaintDateWiseRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		VigilanceAdminUser userData = VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(login);
		renderRequest.setAttribute("userData", userData);
		String startDate=ParamUtil.getString(renderRequest, "startDate");
		String endDate=ParamUtil.getString(renderRequest, "endDate");
		String action=ParamUtil.getString(renderRequest, "action");
		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		List<VigilanceComplaint> complaintList=null;
		if(startDate!=null && endDate!=null)
			complaintList=comp.reportDateComplaintList(startDate, endDate,action); 
			renderRequest.setAttribute("complaintList", complaintList);
			renderRequest.setAttribute("startDate", startDate);
			renderRequest.setAttribute("endDate", endDate);
			renderRequest.setAttribute("action", action);
		return "/jsp/dateWiseComplaintReport.jsp";
	}

}
