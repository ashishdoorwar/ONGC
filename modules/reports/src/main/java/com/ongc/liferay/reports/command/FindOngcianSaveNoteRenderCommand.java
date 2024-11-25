package com.ongc.liferay.reports.command;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
		 "javax.portlet.name="+ ReportsPortletKeys.FindOngcian,
		 "mvc.command.name=saveThanks"
		 }, service = MVCRenderCommand.class
		 )

public class FindOngcianSaveNoteRenderCommand implements MVCRenderCommand{

	public final static String renderingThankNotePagePath = "/findOngcian/thankNote.jsp";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cpfNo = ParamUtil.getString(renderRequest, "cpfNo");
		renderRequest.setAttribute("cpfNo", cpfNo);
		return renderingThankNotePagePath;
	}

}
