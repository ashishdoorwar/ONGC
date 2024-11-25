package com.ongc.liferay.csr.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
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
import com.ongc.liferay.csr.util.Base64Decoder;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;

/**
 * @author Ranjeet
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Csr",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CsrPortletKeys.CSR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CsrPortlet extends MVCPortlet {
	
	private CsrService csrService =new CsrServiceImpl();
	private StatusService statusService =new StatusServiceImpl();
	private SubjectService subjectService =new SubjectServiceImpl();
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String programName =ParamUtil.getString(renderRequest, "programName");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String subject =ParamUtil.getString(renderRequest, "subject");
		String status =ParamUtil.getString(renderRequest, "status");
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
		super.doView(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
		String act=ParamUtil.getString(resourceRequest, "captchaAct");
		if(act!=null && act.contentEquals("image")) {
			captchaImg(resourceRequest, resourceResponse);	
		}else {
			audioCaptcha(resourceRequest, resourceResponse);
		}
		}catch (Exception e) {
			e.printStackTrace();
				}
	}


	public void captchaImg(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			resourceResponse.setContentType("image/png");
			int x=0;
			String text="";
			Random rend = new SecureRandom();
			while(x<6) {
				text+=rend.nextInt(10)+"";
				x++;
			}
			
			Captcha captcha = new Captcha.Builder(200, 50)
					 .addText(text)
					 .addNoise()
					 .addNoise().addNoise()
					 .addNoise()
					 .addBackground()
					 .build();
		CaptchaServletUtil.writeImage(resourceResponse.getPortletOutputStream(), captcha.getImage());
		PortletSession sess=resourceRequest.getPortletSession(true);
		sess.setAttribute("ongc.captchaObj", captcha);
		}catch (Exception e) {
			e.printStackTrace();
				}
	}
	
	private boolean audioCaptcha(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			PortletSession sess=resourceRequest.getPortletSession();
			resourceResponse.setContentType("audio/wave");
			Captcha capt=(Captcha)	sess.getAttribute("ongc.captchaObj");
				AudioCaptcha captcha = new AudioCaptcha.Builder().addAnswer(capt.getAnswer())
						 .addVoice()
						  .addNoise()
						  .build();
				
				nl.captcha.servlet.CaptchaServletUtil.writeAudio(resourceResponse.getPortletOutputStream(),  captcha.getChallenge());
		}catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
		
}