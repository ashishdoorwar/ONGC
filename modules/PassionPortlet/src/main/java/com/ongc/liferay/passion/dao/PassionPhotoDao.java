package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.PassionPhoto;

import java.util.List;

public interface PassionPhotoDao {

	List<PassionPhoto> getPassionPhoto(String cpfNo, String strPassionId, String strSubPassionId);

	boolean insertPhotoComments(String photoId, String cpfNo, String comments);

	List<PassionPhoto> getPassionPhotoComments(String photoId);

	List<PassionPhoto> getEndorseCount(String photoId);

	boolean updateEndorseCount(String photoId, String cpfNo);

}
