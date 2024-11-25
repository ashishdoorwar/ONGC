package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.DiscussionDao;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.dao.Impl.DiscussionDaoImpl;
import com.ongc.liferay.passion.dao.Impl.UserDaoImpl;
import com.ongc.liferay.passion.model.DiscussionBoard;

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
				"mvc.command.name=view_post"
		}, service = MVCRenderCommand.class
		)
public class ViewPostRenderCommand implements MVCRenderCommand {

	private DiscussionDao dDao=new DiscussionDaoImpl();
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		
		String tid=ParamUtil.getString(renderRequest, "tid");
		List<DiscussionBoard> tDetail=dDao.topicDetail(tid);
		List<DiscussionBoard> cList=dDao.fetchComments(tid);
		renderRequest.setAttribute("tDetail", tDetail);		
		renderRequest.setAttribute("cList", cList);		
		return "/jsp/viewPost.jsp";

	}

}
