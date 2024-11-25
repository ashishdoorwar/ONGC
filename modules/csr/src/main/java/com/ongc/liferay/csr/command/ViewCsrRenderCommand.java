package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.service.CsrService;
import com.ongc.liferay.csr.service.Impl.CsrServiceImpl;

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
		 "javax.portlet.name="+CsrPortletKeys.CSR,
		 "mvc.command.name=view_csr_program_model"
		 }, service = MVCRenderCommand.class
		 )
public class ViewCsrRenderCommand  implements MVCRenderCommand{

	private CsrService csrService=new CsrServiceImpl();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int id=ParamUtil.getInteger(renderRequest, "id");
		CSRProgramModel cSRProgramModel = csrService.findById(id);
		renderRequest.setAttribute("cSRProgramModel", cSRProgramModel);
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		return "/csr/listCsr.jsp";
	}

}
