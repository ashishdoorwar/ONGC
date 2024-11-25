package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.PassionDoc;

import java.util.List;

public interface PassionDocDao {

	List<PassionDoc> getPassionDocumentFile(String cpfNo, String strPassionId, String strSubPassionId);

}
