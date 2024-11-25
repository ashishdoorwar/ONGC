package com.ongc.liferay.passion.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.AdminDelegateDao;
import com.ongc.liferay.passion.dao.Impl.AdminDelegateDaoImpl;
import com.ongc.liferay.passion.model.Passion;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=Passion",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=PassionMaster",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/passionMaster/viewPassionList.jsp",
			"javax.portlet.name=" + PassionPortletKeys.PassionMaster,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class PassionMasterPortlet  extends MVCPortlet{
	AdminDelegateDao adminDelegateDao=new AdminDelegateDaoImpl();
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		List<Passion> allPassionList = adminDelegateDao.getAllPassionList();
		renderRequest.setAttribute("allPassionList", allPassionList);
		
		super.doView(renderRequest, renderResponse);
	}

}
