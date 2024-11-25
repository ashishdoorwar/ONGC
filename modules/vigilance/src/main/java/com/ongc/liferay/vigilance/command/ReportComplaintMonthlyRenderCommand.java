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
				"mvc.command.name=reportComplaintMonthly"
		}, service = MVCRenderCommand.class
		)
public class ReportComplaintMonthlyRenderCommand implements MVCRenderCommand{

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
		 int month=ParamUtil.getInteger(renderRequest, "month");		  
		 int year=ParamUtil.getInteger(renderRequest, "year");		  
		 String action=ParamUtil.getString(renderRequest, "action");		  
		  if(month!= 0 && year!= 0)
		  complaintList=comp.reportMonthComplaintList(month, year,action); 
		  renderRequest.setAttribute("complaintList", complaintList);
		  renderRequest.setAttribute("month", month);
		  renderRequest.setAttribute("year", year);
		return "/jsp/monthlyComplaintReport.jsp";
	}

}
