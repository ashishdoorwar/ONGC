package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+PassionPortletKeys.PASSION,
				"mvc.command.name=update_endorse"
		}, service = MVCRenderCommand.class
		)
public class UpdateEndorseRenderCommand implements MVCRenderCommand {
	PassionPhotoDao passionPhotoDao=new PassionPhotoDaoImpl();
	UserService userDao = new UserServiceImpl(); 
	DiscussionDao dDao=new DiscussionDaoImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		User user = userDao.getUser();
		List<PassionPhoto> passionPhotoEndrsCnt = null;
		String endrsCount=null;
		boolean flage=false;
		String photoId = ParamUtil.getString(renderRequest, "pid");
		flage=passionPhotoDao.updateEndorseCount(photoId,user.getCpfNo());
		passionPhotoEndrsCnt=passionPhotoDao.getEndorseCount(photoId);
		for (int i = 0; i < passionPhotoEndrsCnt.size(); i++) {
			endrsCount =passionPhotoEndrsCnt.get(i).getEndorsedCount();
		}
		return null;
	}
	

}
