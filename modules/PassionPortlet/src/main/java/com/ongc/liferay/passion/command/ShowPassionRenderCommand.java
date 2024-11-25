package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.HomeDao;
import com.ongc.liferay.passion.dao.Impl.HomeDaoImpl;

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
		 "mvc.command.name=show_passion"
		 }, service = MVCRenderCommand.class
		 )
public class ShowPassionRenderCommand implements MVCRenderCommand{
	private HomeDao hDao=new HomeDaoImpl();		

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		
		//system.out.println("subPsn::"+subPsn);
		String subPsn=ParamUtil.getString(renderRequest, "subPsn");
		List getPassionData=hDao.getPassion(subPsn);
		List getPassionEnrlUser=hDao.getPassionEnrlUser(subPsn);
		renderRequest.setAttribute("getPassionData", getPassionData);
		renderRequest.setAttribute("getPassionEnrlUser", getPassionEnrlUser);
		return "/jsp/showPassion.jsp";
	}

}
