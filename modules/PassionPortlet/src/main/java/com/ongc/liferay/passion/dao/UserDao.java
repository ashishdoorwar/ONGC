package com.ongc.liferay.passion.dao;

import com.ongc.liferay.passion.model.User;

public interface UserDao {

	User getUserByEmailId(String emailId);

	boolean validateUser(String cpfNumber);

	Object getUserByCPFNumber(String cpfNo);

}
