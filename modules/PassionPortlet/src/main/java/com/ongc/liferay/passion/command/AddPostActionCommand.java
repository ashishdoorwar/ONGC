package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl;
import com.ongc.liferay.passion.model.DiscussionBoard;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;
import com.ongc.liferay.passion.util.CommonUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +PassionPortletKeys.PASSION,
				"mvc.command.name=submitPost"
		},
		service = MVCActionCommand.class
		)
public class AddPostActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		UserService userService = new UserServiceImpl();
		
		User userData = userService.getUser();
		DiscussionBoard dBoard =new DiscussionBoard();
		String topicName=ParamUtil.getString(actionRequest, "topicName");
		String topicDesc=ParamUtil.getString(actionRequest, "topicDesc");
		String visibleTo=ParamUtil.getString(actionRequest, "visibleTo");
		String groupId=ParamUtil.getString(actionRequest, "groupId");
		String tags=ParamUtil.getString(actionRequest, "tags");
		
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/PostNewTopic.jsp");
		}
		else {
		DiscussionDaoImpl discussionBoard=new DiscussionDaoImpl();
		dBoard.setTopicName(topicName);
		dBoard.setTopicDesc(topicDesc);
		dBoard.setVisibleTo(visibleTo);
		dBoard.setGroupId(groupId);
		dBoard.setTags(tags);
		
		discussionBoard.insertPostNew(userData, dBoard);
		}
		
	}

}
