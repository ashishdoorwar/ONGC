package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.DiscussionDao;
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
			"mvc.command.name=submit_Reply"
		},
		service = MVCActionCommand.class
	)
public class SubmitReplyActionCommand  extends BaseMVCActionCommand {
	UserService userDao = new UserServiceImpl(); 
	DiscussionDao dDao=new DiscussionDaoImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		String key;
		String tid =ParamUtil.getString(actionRequest, "tid");
		String reply=ParamUtil.getString(actionRequest, "reply");
		String cid=ParamUtil.getString(actionRequest, "cid");
		User user = userDao.getUser();
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/viewPost.jsp");
		}
		else {
		DiscussionBoard disboard=new DiscussionBoard();
		disboard.setReply(reply);
		if(dDao.postReply(user, disboard, tid, cid)){
		} else {
			key="failure";
		}
		}
	}

}
