package com.ongc.liferay.media.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.media.constants.MediaPortletKeys;
import com.ongc.liferay.media.util.MediaUtil;
import com.ongc.liferay.mediatags.model.Tag;
import com.ongc.liferay.mediatags.service.TagLocalServiceUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adjecti
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=Media Portlet",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + MediaPortletKeys.MEDIAPORTLET,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class MediaPortlet extends MVCPortlet {

	private static final Log LOGGER = LogFactoryUtil.getLog(MediaPortlet.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<DLFolder> folders = null;
		DLFolder dlFolder;
//		List<AssetTag> tags = null;
		List<Tag> tags = null;
		Group group;
		String currentSiteName=themeDisplay.getScopeGroup().getName(themeDisplay.getLocale());
		try {
			dlFolder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getLayout().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Downloads");
			folders = MediaUtil.getChildFolders(themeDisplay.getLayout().getGroupId(),dlFolder.getFolderId());
			//group = GroupLocalServiceUtil.getGroup(themeDisplay.getLayout().getGroupId());
			//tags = MediaUtil.getTags(themeDisplay.getLayout().getGroupId());
			tags = TagLocalServiceUtil.getTags(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("folderIDs", folders);
		renderRequest.setAttribute("total", folders.size());
		renderRequest.setAttribute("currentSiteTags", tags);		
		super.doView(renderRequest, renderResponse);
	}


}
