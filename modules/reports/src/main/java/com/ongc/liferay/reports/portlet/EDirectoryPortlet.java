package com.ongc.liferay.reports.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=ONGC REPORT",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=EDirectory",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/eDirectory/viewEDirectory.jsp",
			"javax.portlet.name=" + ReportsPortletKeys.EDirectory,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class EDirectoryPortlet extends MVCPortlet {

}
