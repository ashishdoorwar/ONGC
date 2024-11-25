package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.PassionAudio;

import java.util.List;

public interface PassionAudioDao {

	List<PassionAudio> getPassionAudio(String cpfNo, String strPassionId, String strSubPassionId);

}
