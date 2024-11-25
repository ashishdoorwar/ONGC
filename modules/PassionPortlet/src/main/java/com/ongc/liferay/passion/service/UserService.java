package com.ongc.liferay.passion.service;

import com.ongc.liferay.passion.model.User;

public interface UserService {

	User getUser();

	Object getUserByCPFNumber(String cpfNo);

}
