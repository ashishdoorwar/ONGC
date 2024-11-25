package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ongc.liferay.constants.RepotsPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Thank Note",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/thankNote/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.ThankNoteArticle,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class ThankNotePortlet extends MVCPortlet {
	
}
