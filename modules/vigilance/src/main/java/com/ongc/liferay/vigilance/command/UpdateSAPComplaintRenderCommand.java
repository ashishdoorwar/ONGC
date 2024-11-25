package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
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
				"mvc.command.name=updateSAPComplaint"
		}, service = MVCRenderCommand.class
		)
public class UpdateSAPComplaintRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		VigilanceAdminUser userData = VigilanceFactory.getAdminUserServiceInstance().getUserByEmailId(login);
		renderRequest.setAttribute("userData", userData);
		
		int complaintNo = ParamUtil.getInteger( renderRequest,"complaintActNo");
		String sapNo = ParamUtil.getString(renderRequest,"sapNo");
		String startDate = ParamUtil.getString(renderRequest,"startDate");
		String action = ParamUtil.getString(renderRequest,"action");
		String remarks = ParamUtil.getString(renderRequest,"remarks");
		String otherStatus = ParamUtil.getString(renderRequest, "otherStatus");
		//System.out.println("complaintActNo=>"+complaintNo+"\nsapNo=>"+sapNo+"\nstartDate=>"+startDate+"\naction=>"+action+"\nremarks=>"+remarks);

		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		int res=comp.updateSAPComplaint(startDate, action,remarks,sapNo,complaintNo,otherStatus);
		VigilanceComplaint complaintDetail = comp.getComplaintDetails(complaintNo);
		comp.updateComplaintStatus(complaintNo);
		renderRequest.setAttribute("complaintDtl", complaintDetail);
		renderRequest.setAttribute("Complain_By", complaintDetail.getComplainBy());
		if(res>0){
			SessionMessages.add(renderRequest, "success");
		}
		else
		{
			SessionErrors.add(renderRequest, "error");
		}
		
		return "/jsp/updateComplaintDetail.jsp";
	}

}
