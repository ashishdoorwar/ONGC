package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PassionDao;
import com.ongc.liferay.dao.PledgeDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.Passion;
import com.ongc.liferay.model.User;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.FraudPlegde,
		 "mvc.command.name=fraudPledge"
		 }, service = MVCRenderCommand.class
		 )

public class FraudPledgeRenderCommand implements MVCRenderCommand{
	public final static String renderingEmployeePagePath = "/fraudPrevention/view.jsp";
	private PledgeDao dao = new PledgeDao();
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		String cpfNo=ParamUtil.getString(renderRequest,"cpfNo");
		String type=ParamUtil.getString(renderRequest,"type");
		String userName=ParamUtil.getString(renderRequest,"userName");
		
		PledgeDao dao = new PledgeDao();
			boolean flag = false;
			flag = dao.submitPledge(cpfNo,type);
		return renderingEmployeePagePath;
	}
}
