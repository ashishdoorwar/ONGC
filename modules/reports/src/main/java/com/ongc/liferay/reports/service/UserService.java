package com.ongc.liferay.reports.service;

import com.ongc.liferay.reports.model.User;

import java.io.File;
import java.util.List;

public interface UserService {

	public List<User> getBirthday(String date , String month, String locations);
	public User getUserByCPFNumber(String cpfNo);
	public boolean saveThanksnote(String fromCpf, String toCpf, String message);
	public boolean updateUser(String cpf, String chk);
	public int getKCCount(String cpf,String chk);
	public boolean saveUser(String cpf, String chk);
	public boolean check_cpf(String cpf);
	public boolean updateUserProfile(User user);
	public User getUser();
}
