package com.ongc.liferay.vigilance.util;

import com.ongc.liferay.vigilance.dao.ComplaintDao;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.dao.ContentManagementDao;
import com.ongc.liferay.vigilance.dao.CountryManagementDao;
import com.ongc.liferay.vigilance.dao.OPTManagementDao;
import com.ongc.liferay.vigilance.dao.VigilanceAdminUserDao;
import com.ongc.liferay.vigilance.dao.VigilanceAdminUserManagementDao;
import com.ongc.liferay.vigilance.dao.VigilanceUserDao;
import com.ongc.liferay.vigilance.dao.VigilanceUserManagementDao;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.ContentManagementDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.CountryManagementDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.OPTManagementDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.VigilanceAdminUserDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.VigilanceAdminUserManagementDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.VigilanceUserDaoImpl;
import com.ongc.liferay.vigilance.dao.Impl.VigilanceUserManagementDaoImpl;


public class VigilanceFactory {

	private static ComplaintDao complaintService;
	private static ComplaintManagementDao complaintManagement;

	private static VigilanceUserManagementDao userManagement;
	private static VigilanceAdminUserManagementDao adminUserManagement;
	private static VigilanceUserDao userService;
	private static VigilanceAdminUserDao aDaorService;
	private static VigilanceAdminUserDao adminUserService;
	private static OPTManagementDao optManagement;

	private static CountryManagementDao countryManagement;

	private static ContentManagementDao contentManagement;

	public static ContentManagementDao getContentManagementInstance() {
		if (contentManagement == null) {
			return contentManagement = new ContentManagementDaoImpl();
		}
		return contentManagement;
	}

	public static VigilanceUserManagementDao getUserManagementInstance() {
		if (userManagement == null) {
			return new VigilanceUserManagementDaoImpl();
		}
		return userManagement;
	}
	
	public static VigilanceAdminUserManagementDao getAdminUserManagementInstance() {
		if (adminUserManagement == null) {
			return new VigilanceAdminUserManagementDaoImpl();
		}
		return adminUserManagement;
	}

	public static ComplaintDao getComplaintServiceInstance() {
		if (complaintService == null) {
			complaintService = new ComplaintDaoImpl();
		}
		return complaintService;
	}

	public static ComplaintManagementDao getComplaintManagementInstance() {
		if (complaintManagement == null) {
			complaintManagement = new ComplaintManagementDaoImpl();
		}
		return complaintManagement;
	}

	public static VigilanceUserDao getUserServiceInstance() {
		if (userService == null) {
			userService = new VigilanceUserDaoImpl();
		}
		return userService;
	}
	public static VigilanceAdminUserDao getAdminUserServiceInstance() {
		if (adminUserService == null) {
			adminUserService = new VigilanceAdminUserDaoImpl();
		}
		return adminUserService;
	}

	public static OPTManagementDao getOptManagementInstance() {
		if (optManagement == null) {
			optManagement = new OPTManagementDaoImpl();
		}
		return optManagement;
	}

	public static CountryManagementDao getCountryManagementInstance() {
		if (countryManagement == null) {
			countryManagement = new CountryManagementDaoImpl();
		}
		return countryManagement;
	}

}
