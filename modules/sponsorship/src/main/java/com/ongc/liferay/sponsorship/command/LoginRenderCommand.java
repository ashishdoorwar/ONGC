package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.model.User;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.UserService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;
import com.ongc.liferay.sponsorship.service.Impl.UserServiceImpl;
import com.ongc.liferay.sponsorship.util.Base64Decoder;
import com.ongc.liferay.sponsorship.util.Base64Encoder;
import com.ongc.liferay.sponsorship.util.CommonUtil;

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
				"javax.portlet.name="+SponsorshipPortletKeys.SPONSORSHIP,
				"mvc.command.name=login"
		}, service = MVCRenderCommand.class
		)
public class LoginRenderCommand  implements MVCRenderCommand{
	private UserService userService=new UserServiceImpl();
	private SponsorshipService  sponsorshipService= new SponsorshipServiceImpl();
	private static final Log LOGGER = LogFactoryUtil.getLog(LoginRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String username=ParamUtil.getString(renderRequest, "username");

		String password=ParamUtil.getString(renderRequest, "password");
		String subName =ParamUtil.getString(renderRequest, "subName");
		String publicationname =ParamUtil.getString(renderRequest, "publicationname");
		String recommendedby =ParamUtil.getString(renderRequest, "recommendedby");
		String purpose =ParamUtil.getString(renderRequest, "purpose");
		String createdBy =ParamUtil.getString(renderRequest, "createdBy");
		String refNo =ParamUtil.getString(renderRequest, "refNo");
		String status =ParamUtil.getString(renderRequest, "status");
		String answer= 	ParamUtil.getString(renderRequest,"captchText");

		boolean checkParameter = CommonUtil.checkParameter(renderRequest);
		if(checkParameter) {
			SessionErrors.add(renderRequest,"error");
			return "/view.jsp";
		}
		else {

			if(! CaptchaServletUtil.checkCaptcha(answer, renderRequest)) {
				SessionErrors.add(renderRequest, "errorMsg");
				return "/view.jsp";
			}
			final PortletSession psession = renderRequest.getPortletSession();
			psession.setAttribute("loginId", username, PortletSession.APPLICATION_SCOPE);
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
			String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
			renderRequest.setAttribute("loginId", login);
			List<FilterBean> filterBeans= sponsorshipService.getStatusList() ;
			renderRequest.setAttribute("filterBeans", filterBeans);
			List<SubjectBean> createdbyList= sponsorshipService.getLocation();
			renderRequest.setAttribute("createdbyList", createdbyList);

			//		User user = userService.findByLoginIdAndPassword(username, password);
			LOGGER.info(Base64Decoder.decode("cGFzc3dvcmQ=")+"-----------");
			User user  =userService.findByLoginIdAndPassword(username,Base64Encoder.encode(password));
			if(user!=null) {

				return "/sponsorship/listSponsorship.jsp";
			}else {
				SessionErrors.add(renderRequest, "Enter valid UserName");
				SessionErrors.add(renderRequest, "Enter valid Password");
				return "/view.jsp";
			}
		}

	}

}
