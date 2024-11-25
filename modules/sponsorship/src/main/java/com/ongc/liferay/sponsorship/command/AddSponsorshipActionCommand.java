package com.ongc.liferay.sponsorship.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.sponsorship.constants.SponsorshipPortletKeys;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
import com.ongc.liferay.sponsorship.service.SponsorshipService;
import com.ongc.liferay.sponsorship.service.Impl.SponsorshipServiceImpl;
import com.ongc.liferay.sponsorship.util.CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.apache.commons.io.FileUtils;
import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +SponsorshipPortletKeys.SPONSORSHIP,
				"mvc.command.name=add_sponsorship"
		},
		service = MVCActionCommand.class
		)
public class AddSponsorshipActionCommand extends BaseMVCActionCommand {


	private SponsorshipService sponsorshipService=new SponsorshipServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/sponsorship");
		String filePath=szResBundl.getString("file-path");
		int id=ParamUtil.getInteger(actionRequest, "id");
		String subject= ParamUtil.getString(actionRequest, "subject");
		String purpose=ParamUtil.getString(actionRequest, "purpose");
		String sponsType=ParamUtil.getString(actionRequest, "sponsType");
		String publicationname=ParamUtil.getString(actionRequest, "publicationname");
		String dealingOfficer=ParamUtil.getString(actionRequest, "dealingOfficer");
		String emailid=ParamUtil.getString(actionRequest, "emailid");
		String mobileNo=ParamUtil.getString(actionRequest, "mobileNo");
		String receivedDate=ParamUtil.getString(actionRequest, "receivedDate");
		String eventDate=ParamUtil.getString(actionRequest, "eventDate");
		String recommendedby=ParamUtil.getString(actionRequest, "recommendedby");
		String internalRecomendation=ParamUtil.getString(actionRequest, "internalRecomendation");
		String proposalval=ParamUtil.getString(actionRequest, "proposalval");
		String deliverable=ParamUtil.getString(actionRequest, "deliverable");
		String approvalAuth=ParamUtil.getString(actionRequest, "approvalAuth");
		String filenumber=ParamUtil.getString(actionRequest, "filenumber");
		String frNumber=ParamUtil.getString(actionRequest, "frNumber");
		String trackingNumber=ParamUtil.getString(actionRequest, "trackingNumber");
		String status=ParamUtil.getString(actionRequest, "status");
		String otherStatus=ParamUtil.getString(actionRequest, "otherStatus");
		String ronum=ParamUtil.getString(actionRequest, "ronum");
		String rodt=ParamUtil.getString(actionRequest, "rodt");
		System.out.println(receivedDate);
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		
		FilterBean filterBean=new FilterBean();
		List<SponsorshipBean> sponsorshipListData =sponsorshipService.displayAllSponship(filterBean);
		actionRequest.setAttribute("sponsorshipListData", sponsorshipListData);
		List<SubjectBean> subjectBeans= sponsorshipService.getSubjectList();
		actionRequest.setAttribute("subjectBeans", subjectBeans);

		List<FilterBean> filterBeans= sponsorshipService.getStatusList() ;
		actionRequest.setAttribute("filterBeans", filterBeans);
		List<SubjectBean> createdbyList= sponsorshipService.getLocation();
		actionRequest.setAttribute("createdbyList", createdbyList);
		PortletSession psession = actionRequest.getPortletSession();
		String login =(String)psession.getAttribute("loginId", PortletSession.APPLICATION_SCOPE);
		actionRequest.setAttribute("loginId", login);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			//actionResponse.setRenderParameter("mvcPath", "/sponsorship/editSponsorship.jsp");
			actionResponse.setRenderParameter("mvcPath", "/sponsorship/listSponsorship.jsp");
		}
		else {
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			File releaseOrderDoc = uploadPortletRequest.getFile("releaseOrderDoc");
			String releaseOrderDocFileName=uploadPortletRequest.getFileName("releaseOrderDoc");
			String to = releaseOrderDoc.getName();

			File proposalDoc = uploadPortletRequest.getFile("proposalDoc");
			String proposalDocFileName=uploadPortletRequest.getFileName("proposalDoc");
			String to1 = proposalDoc.getName();
			SponsorshipBean sponsorshipBean=new SponsorshipBean();
			sponsorshipBean.setSponid(id);
			sponsorshipBean.setSubject(subject);
			sponsorshipBean.setPurpose(purpose);
			sponsorshipBean.setSponsType(sponsType);
			sponsorshipBean.setPublicationname(publicationname);
			sponsorshipBean.setDealingOfficer(dealingOfficer);
			sponsorshipBean.setEmailid(emailid);
			sponsorshipBean.setMobileNo(mobileNo);
			sponsorshipBean.setReceivedDate(receivedDate);
			sponsorshipBean.setEventDate(eventDate);
			sponsorshipBean.setRecommendedby(recommendedby);
			sponsorshipBean.setInternalRecomendation(internalRecomendation);
			sponsorshipBean.setProposalval(proposalval);
			sponsorshipBean.setDeliverable(deliverable);
			sponsorshipBean.setApprovalAuth(approvalAuth);
			sponsorshipBean.setFilenumber(filenumber);
			sponsorshipBean.setFrNumber(frNumber);
			sponsorshipBean.setTrackingNumber(trackingNumber);
			sponsorshipBean.setOtherStatus(otherStatus);
			sponsorshipBean.setRonum(ronum);
			sponsorshipBean.setRodt(rodt);
			if(!Validator.isBlank(releaseOrderDocFileName)) {
				sponsorshipBean.setReleaseOrderDocFileName(releaseOrderDoc.getName());
			}
			if(!Validator.isBlank(proposalDocFileName)) {
				sponsorshipBean.setProposalDocFileName(proposalDoc.getName());
			}
			sponsorshipBean.setStatus(status);
			if(id==0) {
				sponsorshipService.insertSponDetails(sponsorshipBean);	
				if(proposalDoc.exists()) {FileUtils.copyFile(uploadPortletRequest.getFile("proposalDoc"), new File(filePath,to1));}
				if(releaseOrderDoc.exists()) {FileUtils.copyFile(uploadPortletRequest.getFile("releaseOrderDoc"), new File(filePath,to));}
			}else {
				sponsorshipService.updateSponsorshipDetails(sponsorshipBean);
				if(proposalDoc.exists()) {
				FileUtils.copyFile(uploadPortletRequest.getFile("proposalDoc"), new File(filePath,to1));}
				if(releaseOrderDoc.exists()) {
				FileUtils.copyFile(uploadPortletRequest.getFile("releaseOrderDoc"), new File(filePath,to));}
			}
		}
	}
}
