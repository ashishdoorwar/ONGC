package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
import com.ongc.liferay.vigilance.model.ComplaintReply;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;

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
				"javax.portlet.name="+VigilancePortletKeys.VIGILANCE,
				"mvc.command.name=readComplaintDetails"
		}, service = MVCRenderCommand.class
		)
public class ReadComplaintDetailsRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int complaintNo=ParamUtil.getInteger(renderRequest, "pid");
		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		VigilanceComplaint complaintDetails = comp.getComplaintDetails(complaintNo);
		comp.updateComplaintStatus(complaintNo);
		List<ComplaintReply> replyList=comp.getComplaintReplyList(complaintNo);
		renderRequest.setAttribute("replyList", replyList);
		renderRequest.setAttribute("Complain_By", complaintDetails.getComplainBy());
		return "/jsp/readComplaintDetail.jsp";
	}

}
