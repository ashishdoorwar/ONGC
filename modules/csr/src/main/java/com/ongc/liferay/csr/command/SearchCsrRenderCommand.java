package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;
import com.ongc.liferay.csr.model.StatusBean;
import com.ongc.liferay.csr.model.SubjectBean;
import com.ongc.liferay.csr.service.CsrService;
import com.ongc.liferay.csr.service.StatusService;
import com.ongc.liferay.csr.service.SubjectService;
import com.ongc.liferay.csr.service.Impl.CsrServiceImpl;
import com.ongc.liferay.csr.service.Impl.StatusServiceImpl;
import com.ongc.liferay.csr.service.Impl.SubjectServiceImpl;

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
		 "javax.portlet.name="+CsrPortletKeys.CSR,
		 "mvc.command.name=search_csr"
		 }, service = MVCRenderCommand.class
		 )
public class SearchCsrRenderCommand implements MVCRenderCommand{

	private CsrService csrService =new CsrServiceImpl();
	private StatusService statusService =new StatusServiceImpl();
	private SubjectService subjectService =new SubjectServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String programName =ParamUtil.getString(renderRequest, "programName");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String subject =ParamUtil.getString(renderRequest, "subName");
		String status =ParamUtil.getString(renderRequest, "status");
		FilterBean filterBean=new FilterBean();
		filterBean.setCreatedBy(createdBy);
		filterBean.setProgramName(programName);
		filterBean.setRefNo(refNo);
		filterBean.setSubject(subject);
		filterBean.setStatus(status);
		
		List<CSRProgramModel> cSRProgramModels =csrService.findAll(filterBean);
		renderRequest.setAttribute("cSRProgramModels", cSRProgramModels);
		PortletSession psession = renderRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		renderRequest.setAttribute("loginId", login);
		List<StatusBean>  statusBeans= statusService.getAllStatus();
		List<SubjectBean>  subjectBeans= subjectService.getSubjectList();
		renderRequest.setAttribute("statusBeans", statusBeans);
		renderRequest.setAttribute("subjectBeans", subjectBeans);
		return "/csr/viewCsr.jsp";
	}

}
