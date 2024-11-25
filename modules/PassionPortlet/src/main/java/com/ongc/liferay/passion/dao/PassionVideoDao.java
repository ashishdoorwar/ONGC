package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.PassionVideo;

import java.util.List;

public interface PassionVideoDao {

	List<PassionVideo> getPassionVideo(String cpfNo, String strPassionId, String strSubPassionId);

}
