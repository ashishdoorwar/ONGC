package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;
import com.ongc.liferay.csr.model.StatusBean;
import com.ongc.liferay.csr.model.SubjectBean;
import com.ongc.liferay.csr.model.User;
import com.ongc.liferay.csr.service.CsrService;
import com.ongc.liferay.csr.service.StatusService;
import com.ongc.liferay.csr.service.SubjectService;
import com.ongc.liferay.csr.service.UserService;
import com.ongc.liferay.csr.service.Impl.CsrServiceImpl;
import com.ongc.liferay.csr.service.Impl.StatusServiceImpl;
import com.ongc.liferay.csr.service.Impl.SubjectServiceImpl;
import com.ongc.liferay.csr.service.Impl.UserServiceImpl;
import com.ongc.liferay.csr.util.Base64Decoder;
import com.ongc.liferay.csr.util.Base64Encoder;
import com.ongc.liferay.csr.util.CommonUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.Cookie;

import org.osgi.service.component.annotations.Component;

import nl.captcha.servlet.CaptchaServletUtil;
/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+CsrPortletKeys.CSR,
				"mvc.command.name=login"
		}, service = MVCRenderCommand.class
		)
public class LoginRenderCommand  implements MVCRenderCommand{
	private UserService userService=new UserServiceImpl();
	private CsrService csrService =new CsrServiceImpl();
	private StatusService statusService =new StatusServiceImpl();
	private SubjectService subjectService =new SubjectServiceImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String username=ParamUtil.getString(renderRequest, "username");

		String password=ParamUtil.getString(renderRequest, "password");
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String programName =ParamUtil.getString(renderRequest, "programName");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String subject =ParamUtil.getString(renderRequest, "subject");
		String status =ParamUtil.getString(renderRequest, "status");
		String answer= 	ParamUtil.getString(renderRequest,"captchText");
		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
			return "/view.jsp";
		}else {

			
			final PortletSession psession = renderRequest.getPortletSession();
			psession.setAttribute("loginId", username, PortletSession.APPLICATION_SCOPE);

			FilterBean filterBean=new FilterBean();
			filterBean.setCreatedBy(createdBy);
			filterBean.setProgramName(programName);
			filterBean.setRefNo(refNo);
			filterBean.setSubject(subject);
			filterBean.setStatus(status);

			List<CSRProgramModel> cSRProgramModels =csrService.findAll(filterBean);
			renderRequest.setAttribute("cSRProgramModels", cSRProgramModels);

			List<StatusBean>  statusBeans= statusService.getAllStatus();
			List<SubjectBean>  subjectBeans= subjectService.getSubjectList();
			renderRequest.setAttribute("statusBeans", statusBeans);
			renderRequest.setAttribute("subjectBeans", subjectBeans);
			User u = userService.findByLoginIdAndPassword(username,
					Base64Encoder.encode(password));

			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			renderRequest.setAttribute("loginId", login);
			if(u!=null) {
				if(! CaptchaServletUtil.checkCaptcha(answer, renderRequest)) {
					SessionErrors.add(renderRequest, "errorMsg");
					return "/view.jsp";
				}
				return "/csr/viewCsr.jsp";
			}else {
				SessionErrors.add(renderRequest, "Enter valid UserName");
				SessionErrors.add(renderRequest, "Enter valid Password");

				return "/view.jsp";
			}
		}

	}

}
