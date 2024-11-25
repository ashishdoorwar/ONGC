package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.DiscussionDao;
import com.ongc.liferay.passion.dao.PassionPhotoDao;
import com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl;
import com.ongc.liferay.passion.dao.Impl.PassionPhotoDaoImpl;
import com.ongc.liferay.passion.model.PassionPhoto;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;
import com.ongc.liferay.passion.util.CommonUtil;

import java.util.List;

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
				"mvc.command.name=submit_passion_photo_comment"
		},
		service = MVCActionCommand.class
		)
public class AddPassionPhotoCommentActionCommand extends BaseMVCActionCommand {
	UserService userDao = new UserServiceImpl(); 
	DiscussionDao dDao=new DiscussionDaoImpl();
	PassionPhotoDao passionPhotoDao=new PassionPhotoDaoImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		User user = userDao.getUser();

		boolean flage=false;
		String photoId = ParamUtil.getString(actionRequest,"pId");
		String comments = ParamUtil.getString(actionRequest,"cmnts");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/viewPhoto.jsp");
		}
		else {
		flage=passionPhotoDao.insertPhotoComments(photoId,user.getCpfNo(),comments);
		}
//		passionPhotoDao.getPassionPhotoComments(photoId);
	}

}
