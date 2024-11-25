package com.ongc.liferay.reports.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.Impl.FeedbackPostDaoImpl;
import com.ongc.liferay.reports.model.FeedbackCategory;
import com.ongc.liferay.reports.model.FeedbackComment;
import com.ongc.liferay.reports.model.FeedbackHrEnablers;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.FeedbackCategoryService;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCategoryServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackCommentServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.PassioSendMail;
import com.ongc.liferay.reports.util.ReportConstant;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;

@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=ONGC REPORT",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=Feedback",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/feedback/viewFeedback.jsp",
				"javax.portlet.name=" + ReportsPortletKeys.Feedback,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)

public class FeedbackPortlet extends MVCPortlet{

	private static final Log LOGGER = LogFactoryUtil.getLog(FeedbackPortlet.class);

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private FeedbackCategoryService feedbackCategoryService =new FeedbackCategoryServiceImpl();
	private FeedbackCommentService feedbackCommentService=new FeedbackCommentServiceImpl();
	private UserService userService= new UserServiceImpl();

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		String page = httpReq.getParameter("page");
		if(page==null) {
			page ="1";
		}
		User user= userService.getUser();		
		String cpf=user.getCpfNo();
		FeedbackPost post = null;
		FeedbackCategory category = null;
		int stratFrom = 0;
		if(page != null) {
			stratFrom = Integer.parseInt(page);
			stratFrom = (stratFrom - 1) * 10;
		}
		int showRow = 10, noOfRecords = 0;
		String catId = renderRequest.getParameter("category.categoryId");
		String type = renderRequest.getParameter("type");
		


		try {
			if (category == null) {
				category = new FeedbackCategory();
				category.setFeedbackId(1);
			}
			if (category.getFeedbackId() == 0)
				category.setFeedbackId(1);
			List<FeedbackPost> postList = new ArrayList<FeedbackPost>();
			List<FeedbackCategory> categoryList = feedbackCategoryService.getCategoryListByFeedbakId(category.getFeedbackId());
			boolean roleCheck;
			if (user == null) {
				roleCheck = feedbackCategoryService.roleCheck(user.getCpfNo());
				if (cpf != null && ( cpf.equals("78619") || cpf.equals("122379"))) {
					postList = feedbackPostService.getCommentListByStatus(category.getFeedbackId(), stratFrom, showRow,category);
					noOfRecords = feedbackPostService.getNoOfFeedbacks(category, "Inactive");
				} else {
					postList = feedbackPostService.getPostListByFeedbackId(category.getFeedbackId(), stratFrom, showRow,category);
					noOfRecords = feedbackPostService.getNoOfFeedbacks(category, "Active");
				}
			} else {
				roleCheck = feedbackCategoryService.roleCheck(user.getCpfNo());
				if (user != null && ( user.getCpfNo().equals("78619") || user.getCpfNo().equals("122379") ) ) {
					//postList = feedbackPostService.getCommentListByStatus(category.getFeedbackId(), stratFrom, showRow,category);
					noOfRecords = feedbackPostService.getNoOfFeedbacks(category, "Inactive");
					postList = feedbackPostService.getCommentListByStatus();
				} else {
					postList = feedbackPostService.getPostListByFeedbackId(category.getFeedbackId(), stratFrom, showRow,category);
					noOfRecords = feedbackPostService.getNoOfFeedbacks(category, "Active");
				}
			}
			//sendOpenPostToChief(postList);
			if(page == null) { page = "0";}
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / showRow);
			renderRequest.setAttribute("categoryList", categoryList);
			renderRequest.setAttribute("postList", postList);
			renderRequest.setAttribute("rolecheck", roleCheck);
			renderRequest.setAttribute("noOfPages", noOfPages);
			renderRequest.setAttribute("countRow", noOfRecords);
			renderRequest.setAttribute("page", page);
			renderRequest.setAttribute("pageStart", Integer.parseInt(page));
			if (catId != null && !"".equalsIgnoreCase(catId)) {
				String catdesc = feedbackPostService.getCatDesc(catId);
				renderRequest.setAttribute("catId", catId);
				renderRequest.setAttribute("catDesc", catdesc);
			} else {
				renderRequest.setAttribute("catId", "");
				renderRequest.setAttribute("catDesc", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		super.doView(renderRequest, renderResponse);
	}

	public void feedbackPostForm(ActionRequest actionRequest, ActionResponse actionResponse) {
		String cpfNo= ParamUtil.getString(actionRequest, "cpfNo");
		String postSubject= ParamUtil.getString(actionRequest, "subject");
		int category= ParamUtil.getInteger(actionRequest, "category");
		String content= ParamUtil.getString(actionRequest, "content");
		if(checkString(content) || checkPara(String.valueOf(category)) || checkPara(cpfNo) || checkPara(postSubject)) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/feedback/feedbackPostForm.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			User user = new User();
			user.setCpfNo(cpfNo);
			FeedbackPost post = new FeedbackPost();
			post.setUser(user);
			post.setSubject(postSubject);
			post.setCategoryId(category);
			post.setMessage(content);
			String status = null;
			List<FeedbackHrEnablers> HREnablers = new ArrayList<FeedbackHrEnablers>();
			try {
				feedbackPostService.saveFeedbackPost(post);
				boolean flag1 = false;
				flag1 = feedbackPostService.check_cpf(cpfNo);
				if (flag1) {
					feedbackPostService.saveUser(cpfNo, "feedback_post");
				} else {
					feedbackPostService.updateUser(user.getCpfNo(), "feedback_post");
				}

				HREnablers = feedbackPostService.getHREnablersList();
				Iterator<FeedbackHrEnablers> itr = HREnablers.iterator();
				while (itr.hasNext()) {
					FeedbackHrEnablers enablers = (FeedbackHrEnablers) itr.next();
					if (enablers.getRole()!= null && enablers.getRole().trim().equalsIgnoreCase("L1") && enablers.getSubLocation().trim().equalsIgnoreCase(user.getSubLocation().trim())) {

						String enablerCpf = enablers.getCpfNo();
						String enablerEmail = enablerCpf + "@ongc.co.in";
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
						String dateStr = sdf.format(date);
						String message = "Dear Sir/Madam,<br /><br />The following Post [Id "
								+ post.getPostId()
								+ " dated "
								+ dateStr
								+ "] has been posted on Feedback Forum of ONGCReports.<br /><br /><br/>"
								+ "<i>Subject: "
								+ post.getSubject()
								+ "<br />'"
								+ post.getMessage()
								+ "'<br /><br />Posted by: "
								+ user.getEmpName()
								+ "<br />CPF No.: "
								+ user.getCpfNo()
								+ "<br />"
								+ "Mobile No.: "
								+ user.getMobileNo()
								+ "</i><br /><br /><br/>You are requested to visit the portal and respond to the query/ issue within 3 working days.<br /><br /><br /><br/>"
								+ "P.S. This is a system-generated mail from ONGCReports Team.  Please do not reply. <br /><br />";
							PassioSendMail sendData = new PassioSendMail();
							String subject = "Posting of a Feedback query on ONGCReports - to be responded within 3 working days";
						if (post.getCategoryId() == 42
								|| post.getCategoryId() == 62
								|| post.getCategoryId() == 63
								|| post.getCategoryId() == 64
								|| post.getCategoryId() == 65
								|| post.getCategoryId() == 66
								|| post.getCategoryId() == 67
								|| post.getCategoryId() == 68
								|| post.getCategoryId() == 61) {
							// enablerEmail="mithun.7790@yahoo.com";
								 sendData.sendEmail(subject, message, enablerEmail);
							String smstext = "The following Post [Id "
									+ post.getPostId()
									+ " dated "
									+ dateStr
									+ "] has been posted on Feedback Forum of ONGCReports.\n\nYou are requested to visit the portal and respond to the query/ issue within 3 working days.\n\nONGCReports Team";
							// String
							// recieverMobileNo=postDao.getReceiverMobNo(enablerCpf);
							String recieverMobileNo = "09650463270";
							if (recieverMobileNo.length() >= 10) {
								recieverMobileNo = "91"+ recieverMobileNo.substring(recieverMobileNo.length() - 10,recieverMobileNo.length());
								//	sendSMStoEnabler(recieverMobileNo, smstext);
							}

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void sendOpenPostToChief(List<FeedbackPost> postList) {
		List<FeedbackHrEnablers> HREnablers = new ArrayList<FeedbackHrEnablers>();
		String chiefCpf = feedbackPostService.getChiefCpf();
		String chiefEmail = chiefCpf + "@ongc.co.in";
		String assetManager = "";
		HREnablers = feedbackPostService.getHREnablersList();
		Iterator<FeedbackPost> itr = postList.iterator();
		try {
			while (itr.hasNext()) {
				FeedbackPost post = (FeedbackPost) itr.next();
				if ((post.getCategoryId() == 42 || post.getCategoryId() == 62
						|| post.getCategoryId() == 63
						|| post.getCategoryId() == 64
						|| post.getCategoryId() == 65
						|| post.getCategoryId() == 66
						|| post.getCategoryId() == 67
						|| post.getCategoryId() == 68 || post.getCategoryId() == 61)
						&& post.getChiefEr().equalsIgnoreCase("NO")
						&& post.getOpStatus().equalsIgnoreCase("OPEN")) {
					FeedbackPost feedbackPost = new FeedbackPost();
					User postUser = userService.getUserByCPFNumber(post.getUser().getCpfNo());
					Date date = post.getPostDate();
					SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
					String dateStr = sdf.format(date);
					String subject = "Posting of a Feedback query on ONGCReports - pending with Enabler.";
					String message = "";
					int workDays = 0;
					String enablerName = "";
					String localEnablerName = "";

					if (post.getRvStatus().equalsIgnoreCase("LOCAL")) {
						try {
							Date startDate = post.getPostDate();
							Date endDate = new Date();

							Calendar startCal = Calendar.getInstance();
							startCal.setTime(startDate);

							Calendar endCal = Calendar.getInstance();
							endCal.setTime(endDate);

							do {
								// excluding start date
								startCal.add(Calendar.DAY_OF_MONTH, 1);
								if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
										&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
									++workDays;
								}
							} while (startCal.getTimeInMillis() < endCal
									.getTimeInMillis()); // excluding end date

							Iterator<FeedbackHrEnablers> itr1 = HREnablers
									.iterator();
							feedbackPost = feedbackPostService.getFeedbackPost(post);
							while (itr1.hasNext()) {
								//log.info("FeedBack Action Test Inside While loop:::::");
								FeedbackHrEnablers enablers = (FeedbackHrEnablers) itr1.next();

								String enableRole=enablers.getRole();
								//log.info(":::FeedBack Action Role::<<<<enableRole>>>>:"+enableRole); 
								String enablerSubLocation=enablers.getSubLocation();
								String userSubLocation=feedbackPost.getUser().getSubLocation();
								if (enablers.getRole().equalsIgnoreCase("L1")
										&& enablers.getSubLocation().trim().equalsIgnoreCase(feedbackPost.getUser().getSubLocation())) {
									String L1enablerCpf = enablers.getCpfNo();
									User L1enablerUser = userService.getUserByCPFNumber(L1enablerCpf);
									enablerName = L1enablerUser.getEmpName();
								}
								if (enablers.getRole().equalsIgnoreCase("L11")
										&& enablers.getSubLocation().trim().equalsIgnoreCase(feedbackPost.getUser().getSubLocation())) {
									String L1enablerCpf = enablers.getCpfNo();
									assetManager = L1enablerCpf + "@ongc.co.in";
								}
							}
							message = "Dear Sir,<br /><br />The following Post [Id "
									+ post.getPostId()
									+ " dated "
									+ dateStr
									+ "]  has been posted on Feedback Forum of ONGCReports. <br /><br /><br/>"
									+ "<i>Subject: "
									+ post.getSubject()
									+ "<br />'"
									+ post.getMessage()
									+ "'<br /><br />Posted by: "
									+ postUser.getEmpName()
									+ "<br />CPF No.: "
									+ postUser.getCpfNo()
									+ "<br />"
									+ "Mobile No.: "
									+ postUser.getMobileNo()
									+ "</i><br /><br/><br/>The Post is pending with Local Enabler Mr/Ms "
									+ enablerName
									+ ", "
									+ postUser.getCurrentPlace()
									+ ".<br/><br /><br/>You are requested to kindly advise Mr/Ms "
									+ enablerName
									+ " to respond to the query immediately.<br /><br /><br /><br/>"
									+ "P.S. This is a system-generated mail from ONGCReports Team.  Please do not reply. <br /><br />";
							if (workDays > 3) {
								PassioSendMail sendData = new PassioSendMail();
								feedbackPostService.sendPostToChief(post.getPostId());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (post.getRvStatus().equalsIgnoreCase("CORPORATE")) {
						try {

							Date startDate = feedbackPostService.getPostForwardDate(post.getPostId());
							Date endDate = new Date();
							Calendar startCal = Calendar.getInstance();
							startCal.setTime(startDate);
							Calendar endCal = Calendar.getInstance();
							endCal.setTime(endDate);
							do {
								startCal.add(Calendar.DAY_OF_MONTH, 1);
								if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
										&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
									++workDays;
								}
							} while (startCal.getTimeInMillis() < endCal
									.getTimeInMillis()); // excluding end date

							Iterator<FeedbackHrEnablers> itr2 = HREnablers
									.iterator();
							feedbackPost = feedbackPostService.getFeedbackPost(post);
							while (itr2.hasNext()) {
								FeedbackHrEnablers enablers = (FeedbackHrEnablers) itr2
										.next();
								if (enablers.getRole().trim()
										.equalsIgnoreCase("L2")
										&& enablers.getHrCatId() == feedbackPost
										.getHrCatId()) {

									String L2enablerCpf = enablers.getCpfNo();
									User L2enablerUser = userService.getUserByCPFNumber(L2enablerCpf);
									enablerName = L2enablerUser.getEmpName();
								}
								if (enablers.getRole().trim().equalsIgnoreCase("L1")&& enablers.getSubLocation()
										.trim()
										.equalsIgnoreCase(
												feedbackPost
												.getUser()
												.getSubLocation()
												.trim())) {

									String localEnablerCpf = enablers
											.getCpfNo();
									User localEnablerUser = userService.getUserByCPFNumber(localEnablerCpf);
									localEnablerName = localEnablerUser.getEmpName();
								}
							}
							message = "Dear Sir,<br /><br />The following Post [Id "
									+ post.getPostId()
									+ " dated "
									+ dateStr
									+ "] has been posted on Feedback Forum of ONGCReports.<br/><br /><br />"
									+ "<i>Subject: "
									+ post.getSubject()
									+ "<br />'"
									+ post.getMessage()
									+ "'<br /><br />Posted by: "
									+ postUser.getEmpName()
									+ "<br />CPF No.: "
									+ postUser.getCpfNo()
									+ "<br />"
									+ "Mobile No.: "
									+ postUser.getMobileNo()
									+ "</i><br /><br/> <br/><br/>The Post has been forwarded by Mr/Ms "
									+ localEnablerName
									+ ", "
									+ postUser.getCurrentPlace()
									+ " and is pending with Corporate Enabler Mr/Ms "
									+ enablerName
									+ ".<br /><br /><br/>You are requested to kindly advise Mr/Ms "
									+ enablerName
									+ " to respond to the query immediately.<br /><br /><br /><br/>"
									+ "P.S. This is a system-generated mail from ONGCReports Team.  Please do not reply. <br /><br />";
							if (workDays > 4) {
								PassioSendMail sendData = new PassioSendMail();
								feedbackPostService.sendPostToChief(post.getPostId());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}	

	public void feedbackPostCommentForm(ActionRequest actionRequest, ActionResponse actionResponse ) {
		int postId = ParamUtil.getInteger(actionRequest, "postId");
		String fcomment = ParamUtil.getString(actionRequest, "comment");
		User user = userService.getUser();
		if(checkString(fcomment) || checkPara(String.valueOf(postId)) ) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/feedback/feedbackCommentForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			FeedbackPost post = new FeedbackPost();
			FeedbackComment comment = new FeedbackComment();
			post.setPostId(postId);
			comment.setPostId(post.getPostId());
			comment.setUser(user);
			comment.setCommentText(fcomment);
			if (feedbackCommentService.saveFeedbackComment(comment)) {
				feedbackPostService.updateNoOfComment(post.getPostId(), true);
				boolean flag1 = false;
				flag1 = feedbackPostService.check_cpf(user.getCpfNo());
				if (flag1) {

					if (comment.isAuthResp()) {
						feedbackPostService.saveUser(user.getCpfNo(), "auth_response");

					} else {
						feedbackPostService.saveUser(user.getCpfNo(), "feedback_comment");
					}
				} else {
					if (comment.isAuthResp()) {
						feedbackPostService.updateUser(user.getCpfNo(), "auth_response");
					} else {
						feedbackPostService.updateUser(user.getCpfNo(), "feedback_comment");
					}
				}
			}
		}
	}

	public void forwardToCorporate(ActionRequest actionRequest, ActionResponse actionResponse) {
		String postId = ParamUtil.getString(actionRequest, "postId");
		String category = ParamUtil.getString(actionRequest, "category");
		String comment = ParamUtil.getString(actionRequest, "comment");
		if(checkString(comment) || checkPara(String.valueOf(postId)) || checkPara(category) ) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/feedback/feedbackCommentForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			FeedbackPost post = new FeedbackPost();
			User user = userService.getUser();
			post.setUser(user);
			post.setPostId(Integer.parseInt(postId));
			post.setHrCatId(Integer.parseInt(category));
			post.setRevertReason(comment);
			post.setRvStatus(ReportConstant.POST_STATUS_CORPORATE);
			feedbackPostService.escalateCorporate(post);
			String corporateCpf = feedbackPostService.getCorporateCpf(post);
			String corporateEmail = corporateCpf + "@ongc.co.in";
			LOGGER.info("corporateEmail::::::::" + corporateEmail);
			FeedbackPost feedbackPost = feedbackPostService.getFeedbackPost(post);
			//UserDao udao = new UserDao();
			User postUser = userService.getUserByCPFNumber(feedbackPost.getUser().getCpfNo());
			Date date = feedbackPost.getPostDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			String dateStr = sdf.format(date);
			String message = "Dear Sir/Madam,<br /><br />The following Post Id ["
					+ (feedbackPost.getPostId() - 1)
					+ " dated "
					+ dateStr
					+ "] has been posted on Feedback Forum of ONGCReports.<br /><br /><br />"
					+ "<br /><i>Subject: "
					+ feedbackPost.getSubject()
					+ "<br />'"
					+ feedbackPost.getMessage()
					+ "'<br /><br />Posted by: "
					+ postUser.getEmpName()
					+ "<br />CPF No.: "
					+ postUser.getCpfNo()
					+ "<br />"
					+ "Mobile No.: "
					+ postUser.getMobileNo()
					+ "</i><br /><br /><br />"
					+ " The post has been forwarded by the I/c HR-ER, "
					+ postUser.getCurrentPlace()
					+ " for your Response as Corporate Enabler.<br /><br /><br/>"
					+ "You are requested to visit the portal and respond to the query/ issue within 3 working days.<br /><br /><br /><br />"
					+ "P.S. This is a system-generated mail from ONGCReports Team.  Please do not reply. <br /><br />";
			LOGGER.info("message::::::::" + message);
			PassioSendMail sendData = new PassioSendMail();
			String subject = "Posting of a Feedback query on ONGCReports - to be responded within 3 working days";
			sendData.sendEmail(subject, message, corporateEmail);
		}
	}

	public void CommentLocal(ActionRequest actionRequest, ActionResponse actionResponse) {
		String postId = ParamUtil.getString(actionRequest, "postID");
		String comment = ParamUtil.getString(actionRequest, "comment");
		if(checkString(comment) || checkPara(String.valueOf(postId)) ) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/feedback/feedbackCommentForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			User user = userService.getUser();
			FeedbackPost post = new FeedbackPost();
			post.setUser(user);
			post.setRevertReason(comment);
			post.setPostId(Integer.parseInt(postId));
			post.setOpStatus(ReportConstant.POST_STATUS_CLOSE);
			boolean flag = feedbackPostService.closeComment(post);

			if (flag) {
				boolean flag1 = false;
				flag1 = feedbackPostService.check_cpf(user.getCpfNo());
				if (flag1) {
					feedbackPostService.saveUser(user.getCpfNo(), "enabler_close");
				} else {
					feedbackPostService.updateUser(user.getCpfNo(), "enabler_close");
				}
			}
		}
	}
	
	
	public void commentByCorporate(ActionRequest actionRequest, ActionResponse actionResponse) {
		int postId = ParamUtil.getInteger(actionRequest, "postId");
		String comment = ParamUtil.getString(actionRequest, "comment");
		if(checkString(comment) || checkPara(String.valueOf(postId)) ) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/feedback/feedbackCommentForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			User user = userService.getUser();
			FeedbackPost post = new FeedbackPost();
			post.setUser(user);
			post.setPostId(postId);
			post.setRevertReason(comment);
			post.setOpStatus(ReportConstant.POST_STATUS_CLOSE);
			boolean flag = feedbackPostService.closeComment(post);
			if (flag) {
				boolean flag1 = false;
				flag1 = feedbackPostService.check_cpf(user.getCpfNo());
				if (flag1) {
					feedbackPostService.saveUser(user.getCpfNo(), "enabler_close");
				} else {
					feedbackPostService.updateUser(user.getCpfNo(), "enabler_close");
				}
			}
		}
	}
	
	private boolean checkString(String message) {
		boolean parameterCheck=false;
		if(message.toLowerCase().contains("<script>") || message.toLowerCase().contains("</script>") || message.toLowerCase().contains("&lt;script&gt;")|| message.toLowerCase().contains("alert")) {
			parameterCheck = true;
		}
		return parameterCheck;
	}
	
	private boolean checkPara(String message) {
		boolean parameterCheck=false;
		if (!Pattern.matches("[^<>]*", message)) {
			//System.out.println(parameterCheck+"========================>"+Pattern.matches("[^<>]*", (message)));
			parameterCheck = true;
		}
		return parameterCheck;
	}	
}

