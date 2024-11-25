package com.ongc.liferay.vigilance.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ongc.liferay.vigilance.constants.VigilancePortletKeys;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
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
				"mvc.command.name=adminHome"
		}, service = MVCRenderCommand.class
		)
public class AdminHomeRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
		List<VigilanceComplaint> complaintList=null;
		complaintList=comp.getComplaintList();
		renderRequest.setAttribute("complaintList", complaintList);
		return "/jsp/adminHome.jsp";
	}

}
