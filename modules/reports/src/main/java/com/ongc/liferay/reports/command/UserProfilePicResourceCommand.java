package com.ongc.liferay.reports.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;

import java.io.File;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;


@Component(
		property = {
				"javax.portlet.name="+ ReportsPortletKeys.EDirectory,
				"mvc.command.name=getUserProfileInfo"
		},
		service = MVCResourceCommand.class
		)
public class UserProfilePicResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		String fileName=uploadPortletRequest.getFileName("uploadedFile");
		File file = uploadPortletRequest.getFile("uploadedFile");
		String title = fileName;
		resourceRequest.setAttribute("absolutePath", file.getAbsolutePath());

		return false;
	}

}
