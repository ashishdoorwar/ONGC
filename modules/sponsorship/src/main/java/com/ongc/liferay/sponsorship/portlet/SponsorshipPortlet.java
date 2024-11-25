package com.ongc.liferay.sponsorship.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.dao.Impl.UserDaoImpl;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;

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
		"javax.portlet.display-name=Sponsorship",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SponsorshipPortletKeys.SPONSORSHIP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SponsorshipPortlet extends MVCPortlet {
	
	private SponsorshipService  sponsorshipService= new SponsorshipServiceImpl();
	private static final Log LOGGER = LogFactoryUtil.getLog(SponsorshipPortlet.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String subName =ParamUtil.getString(renderRequest, "subName");
		String publicationname =ParamUtil.getString(renderRequest, "publicationname");
		String recommendedby =ParamUtil.getString(renderRequest, "recommendedby");
		String purpose =ParamUtil.getString(renderRequest, "purpose");
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String status =ParamUtil.getString(renderRequest, "status");
		
		FilterBean filterBean=new FilterBean();
		filterBean.setSubject(subName);
		filterBean.setPublicationname(publicationname);
		filterBean.setPurpose(purpose);
		filterBean.setCreatedBy(createdBy);
		filterBean.setRefNo(refNo);
		filterBean.setStatus(status);
		filterBean.setRecommendedby(recommendedby);
		List<SponsorshipBean> sponsorshipListData =sponsorshipService.displayAllSponship(filterBean);
		renderRequest.setAttribute("sponsorshipListData", sponsorshipListData);
		List<SubjectBean> subjectBeans= sponsorshipService.getSubjectList();
		renderRequest.setAttribute("subjectBeans", subjectBeans);
		
		List<FilterBean> filterBeans= sponsorshipService.getStatusList() ;
		renderRequest.setAttribute("filterBeans", filterBeans);
		List<SubjectBean> createdbyList= sponsorshipService.getLocation();
		renderRequest.setAttribute("createdbyList", createdbyList);
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
			LOGGER.info("Captcha Obj >> "+capt);
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