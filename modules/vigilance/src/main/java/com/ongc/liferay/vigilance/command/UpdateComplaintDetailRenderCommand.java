package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;

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
				"javax.portlet.name="+VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=updateComplaintDetail"
		}, service = MVCRenderCommand.class
		)
public class UpdateComplaintDetailRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		int complaintNo=ParamUtil.getInteger(renderRequest, "pid");
		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		VigilanceComplaint complaintDetail = comp.getComplaintDetails(complaintNo);
		comp.updateComplaintStatus(complaintNo);
		renderRequest.setAttribute("complaintDtl", complaintDetail);
		renderRequest.setAttribute("Complain_By", complaintDetail.getComplainBy());
		return "/jsp/updateComplaintDetail.jsp";
	}

}
