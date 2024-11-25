package com.ongc.liferay.passion.util;


import java.util.Iterator;
import java.util.List;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

import com.ongc.liferay.passion.dao.TagsDao;
import com.ongc.liferay.passion.dao.Impl.TagsDaoImpl;
import com.ongc.liferay.passion.model.DiscussionTags;
import com.ongc.liferay.passion.model.PassionTags;
import com.ongc.liferay.passion.model.WorkItemTags;

public class TagCloud {

	
	public Cloud discussionTag(String cpfNo) {
		TagsDao tDao = new TagsDaoImpl();
		Cloud cloud = new Cloud();
		
		List words = tDao.fetchDiscussionTags(cpfNo);
		
		Iterator itr=words.iterator();
		while(itr.hasNext()){
			DiscussionTags dTags=(DiscussionTags)itr.next();
			int i=dTags.getTagsNo();
			String str=dTags.getTags();
			
			
			for(int j=1; j<=i; j++){
				
				cloud.addTag(str);
			}
			
		}
		return cloud;
		}
	
	public Cloud workItemTag() {
		TagsDao tDao = new TagsDaoImpl();
		Cloud cloud = new Cloud();
		List words = tDao.fetchWorkItemTags();
		
		Iterator itr=words.iterator();
		while(itr.hasNext()){
			WorkItemTags wTags=(WorkItemTags)itr.next();
			int i=wTags.getTagsNo();
			String tag=wTags.getTags();
			String url=wTags.getUrl();
			for(int j=1; j<=i; j++){
				cloud.addTag(tag,url);
			}
		}
		return cloud;
		}
	
	public Cloud enrolledPassionTag() {
		TagsDao tDao = new TagsDaoImpl();
		Cloud cloud = new Cloud();
		
		List words = tDao.fetchPassionTags();
		
		Iterator itr=words.iterator();
		while(itr.hasNext()){
			PassionTags pTags=(PassionTags)itr.next();
			int i=pTags.getTagsNo();
			String tag=pTags.getTags();
			String subPassionId=pTags.getSubPassionId();
			for(int j=1; j<=i; j++){
				cloud.addTag(tag,subPassionId);
			}
		}
		
		return cloud;
		}
	
	public static void main(String[] args) {
		TagCloud tc=new TagCloud();
		Cloud cloud=tc.enrolledPassionTag();
		NameFormatter nf=new NameFormatter();
		for (Tag tag : cloud.tags()) {
			////system.out.println(nf.InitialCaps(tag.getName())+"  "+tag.getWeight()+"  "+tag.getLink());
		}
	}
		
		
}
