package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.service.FeedbackCommentService;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.PageLikeService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackCommentServiceImpl;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.PageLikeServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.Feedback,
		 "mvc.command.name=likeFeedback"
		 }, service = MVCResourceCommand.class
		 )
public class FeedbackLikeResourseCommand implements MVCResourceCommand{

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();
	private PageLikeService pageLikeService = new PageLikeServiceImpl();

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		String postId = ParamUtil.getString(resourceRequest, "postid");
		String cpfNum = ParamUtil.getString(resourceRequest, "cpfNum");
		String status = ParamUtil.getString(resourceRequest, "status");
		int likecount = 0;
		int dislikecount = 0;
		if(postId != null && cpfNum != null){
			boolean flag = feedbackPostService.saveFeedbackLike(postId,cpfNum,status);
			if(flag){
				boolean flag1=false;
				flag1=pageLikeService.check_cpf(cpfNum);
				if(flag1)
				{
					if("L".equalsIgnoreCase(status)) {
						pageLikeService.saveUser(cpfNum,"likes");
					}else {
						pageLikeService.saveDislikeUser(cpfNum,"likes");
					} 
				}else {
					if("L".equalsIgnoreCase(status)){
						pageLikeService.updateUser(cpfNum,"likes");
					}else {
						pageLikeService.updateDislike(cpfNum,"likes");
					}
				}
				
			} 
			
			resourceRequest.setAttribute("flag", String.valueOf(flag));
		}
		likecount = feedbackPostService.getFeedbackLikeCount(postId);
		dislikecount = feedbackPostService.getFeedbackDisLikeCount(postId);
		resourceRequest.setAttribute("likecount", likecount);
		resourceRequest.setAttribute("dislikecount", dislikecount);
		return false;
	}
}
