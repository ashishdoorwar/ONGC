package com.ongc.liferay.media.listner;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.ongc.liferay.mediatags.model.Tag;
import com.ongc.liferay.mediatags.service.TagLocalServiceUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DLFileListener extends BaseModelListener<AssetEntry> {

	private static final Log LOGGER = LogFactoryUtil.getLog(DLFileListener.class);
	
	@Override
	public void onAfterCreate(AssetEntry assetEntry) throws ModelListenerException {

		if(assetEntry.getClassName().equals("com.liferay.document.library.kernel.model.DLFileEntry")) {
			List<AssetTag> assetEntryAssetTags = AssetTagLocalServiceUtil.getAssetEntryAssetTags(assetEntry.getEntryId());
			for(AssetTag assetEntryAssetTag: assetEntryAssetTags) {
				Tag tag = null;
				long tagId = 0;
				tagId = CounterLocalServiceUtil.increment(Tag.class.getName());
				tag = TagLocalServiceUtil.createTag(tagId);
				tag.setTagId(assetEntryAssetTag.getTagId());
				tag.setTagName(assetEntryAssetTag.getName());
				TagLocalServiceUtil.addTag(tag);
			}
		}
		super.onAfterCreate(assetEntry);
	}
	
}
