package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.Covid19Dao;
import com.ongc.liferay.dao.IncidentDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.Covid19;
import com.ongc.liferay.model.IncidentBean;
import com.ongc.liferay.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adjecti
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=SMALL Report",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Chief Labs Feedback",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/chiefLabs/view.jsp",
		"javax.portlet.name=" + RepotsPortletKeys.Feedback,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FeedbackPortlet extends MVCPortlet {
	
}