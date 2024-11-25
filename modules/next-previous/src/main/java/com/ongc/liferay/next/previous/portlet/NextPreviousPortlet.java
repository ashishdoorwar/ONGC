package com.ongc.liferay.next.previous.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ongc.liferay.next.previous.constants.NextPreviousPortletKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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
				"javax.portlet.display-name=NextPrevious",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + NextPreviousPortletKeys.NEXTPREVIOUS,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class NextPreviousPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		long nextArticle = 0;
		long nextClassPk = 0;
		long nextAssetEntryId = 0;
		long preiousArticle = 0;
		long previousClassPk = 0;
		long preiousAssetEntryId = 0;String assetEntryURL = null;String previousTitleUrl = null;String nextTitleUrl = null;
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(renderRequest);
			HttpServletRequest httpOrigReq = PortalUtil.getOriginalServletRequest(httpReq);
			String currentUrl = PortalUtil.getCurrentURL(renderRequest);
			String articleUrl =currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
			JournalArticle article = null;
			assetEntryURL = httpOrigReq.getParameter("assetEntry");
			if(httpOrigReq.getParameter("assetEntry")!=null) {
			long assetEntryId = Long.parseLong(httpOrigReq.getParameter("assetEntry"));
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
			List<JournalArticle> articlesResource = JournalArticleLocalServiceUtil.getArticlesByResourcePrimKey(assetEntry.getClassPK());//(themeDisplay.getLayout().getGroupId(),41957);
			article = articlesResource.get(0);
			} else {
				article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getLayout().getGroupId(), articleUrl);
			}
			
			List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(themeDisplay.getLayout().getGroupId(), article.getFolderId());
			Set<Long> numbers = new TreeSet<Long>();

			for (JournalArticle articl:articles) {
				if(articl.getStatus()==0) {
				numbers.add(Long.parseLong(articl.getArticleId()));
				}
			}
			long getCurrentArticleId = Long.parseLong(article.getArticleId());
			List<Long> arr = new ArrayList<>(numbers);
			if(!(arr.indexOf(getCurrentArticleId) <= 0)) {
				preiousArticle = arr.get(arr.indexOf(getCurrentArticleId)-1);
				JournalArticle article2 = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getLayout().getGroupId(), String.valueOf(preiousArticle));
				AssetEntry entry = AssetEntryLocalServiceUtil.getEntry("com.liferay.journal.model.JournalArticle", article2.getResourcePrimKey());
				previousClassPk = article2.getResourcePrimKey();
				preiousAssetEntryId= entry.getEntryId();
				previousTitleUrl = article2.getUrlTitle();
			}
			if(arr.indexOf(getCurrentArticleId) < arr.size()-1) {
				nextArticle = arr.get(arr.indexOf(getCurrentArticleId)+1);
				JournalArticle article2 = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getLayout().getGroupId(), String.valueOf(nextArticle));
				AssetEntry entry = AssetEntryLocalServiceUtil.getEntry("com.liferay.journal.model.JournalArticle", article2.getResourcePrimKey());
				nextAssetEntryId= entry.getEntryId();
				nextClassPk = article2.getResourcePrimKey();
				nextTitleUrl = article2.getUrlTitle();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("previousEntryId",preiousAssetEntryId);
		renderRequest.setAttribute("nextEntryId",nextAssetEntryId);
		renderRequest.setAttribute("nextClassPk",nextClassPk);
		renderRequest.setAttribute("previousClassPk",previousClassPk);
		renderRequest.setAttribute("assetEntryURL",assetEntryURL);
		renderRequest.setAttribute("previousTitleUrl",previousTitleUrl);
		renderRequest.setAttribute("nextTitleUrl",nextTitleUrl);
		super.doView(renderRequest, renderResponse);
	}
}