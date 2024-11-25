package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.Passion;
import com.ongc.liferay.reports.model.SubPassion;
import com.ongc.liferay.reports.model.User;

import java.sql.Connection;
import java.util.List;

public interface PassionDao {

	public List<Passion> getUserPassion(String cpfNo);
	public String getUserPassionMPID(String pID,String subID,String cpf);
	public List<Passion> getPassionName();
	public List<SubPassion> getSubPassionName(String passionID);
	public void updatePassionCat1ByPsnId(Passion passionFirst,String cpfNumber, String passionFirstPsnMpId,Connection con);
	public boolean submitUserPassion(User user, Passion passion);
	//public int getMaxIdFromTable(String tableName, String colName);
}
