package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.FeedbackPostDao;
import com.ongc.liferay.reports.model.FeedbackPost;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.FeedbackPostService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.FeedbackPostServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		property = {
				"javax.portlet.name="+ ReportsPortletKeys.Feedback,
				"mvc.command.name=getMyPosts"
		}, service = MVCRenderCommand.class
		)

public class GetMyFeedbackRenderCommand implements MVCRenderCommand{

	public final static String renderingMyPostsPagePath = "/feedback/myPosts.jsp";

	private FeedbackPostService feedbackPostService=new FeedbackPostServiceImpl();

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			UserService userService = new UserServiceImpl();
			User user= userService.getUser();

			String cpf = "";
			if (user == null) {

				//				Principal currentUser = (Principal) request.getUserPrincipal();
				//				String s1 = currentUser.toString();
				//				cpf = s1.substring(s1.indexOf("-") + 1, s1.indexOf(","));
			} else {
				cpf = user.getCpfNo();
			}

			List<FeedbackPost> mypostList = new ArrayList<FeedbackPost>();
			mypostList = feedbackPostService.getMyPosts(cpf);
			//this.pName = request.getParameter("pageName");
			renderRequest.setAttribute("mypostList", mypostList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return renderingMyPostsPagePath;
	}

}
