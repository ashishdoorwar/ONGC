package com.ongc.liferay.reports.service.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.dao.FindOngcianManagementDao;
import com.ongc.liferay.reports.dao.Impl.FindOngcianManagementDaoImpl;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.FindOngcianService;

import java.util.List;

public class FindOngcianServiceImpl implements FindOngcianService {
	

	private static final Log LOGGER = LogFactoryUtil.getLog(FindOngcianServiceImpl.class);
	
	@Override
	public List<User> getOngcian(User user) {
		
/*
		LOGGER.info("ongcianFinderDao--------->"+user.getCpfNo().length());
		StringBuffer query=null;
		
		//Needs to change the query for parameters which is sent by front end
	//	if(user.getCpfNo().length() > 0 && user.getCpfNo()!=null) {
		if(user.getCpfNo().length()==5 || user.getCpfNo().length()==6){
			 query = new StringBuffer("  UPPER(oum.CPF_NUMBER) = '").append(user.getCpfNo()).append("' ");				
		}else{
			query = new StringBuffer(" UPPER(oum.CPF_NUMBER) like ('%").append(user.getCpfNo()).append("%') ");
			}
//		}

		query.append(" and EMP_LEVEL not in ('Retd','Seprtd','Rsgnd','Demise')");

		System.out.println("user.getEmpName() user.getEmpName() " + user.getEmpName());
		if (user.getEmpName() != null && user.getEmpName().trim().length() > 0) {
			query.append(" or oum.EMP_NAME like ('%").append(user.getEmpName()).append("%') ");
		}

		if (user.getEmpLevel() != null && user.getEmpLevel().trim().length() > 0 && !user.getEmpLevel().equals("-1") && !user.getEmpLevel().equalsIgnoreCase("Select")) {
			query.append(" or oum.EMP_LEVEL = '").append(user.getEmpLevel()).append("'");
		}

		if (user.getCurrentPlace() != null && user.getCurrentPlace().trim().length() > 0 && !user.getCurrentPlace().equalsIgnoreCase("Select")) {
			query.append(" or (UPPER(oum.PLACE_POSTING) like UPPER('%").append(user.getCurrentPlace()).append("%')");
			if (user.getCurrentPlace().equals("Vadodara")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Baroda%')");
			}
			if (user.getCurrentPlace().equals("Mumbai")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Bombay%') or UPPER(oum.PLACE_POSTING) like UPPER('%Neelam%') ");
			}
			if (user.getCurrentPlace().equals("Ahmedabad")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%IRS%')");
			}
			if (user.getCurrentPlace().equals("Kakinada")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%E.O. ASSET%')");
			}
			if (user.getCurrentPlace().equals("Dehradun")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%INSTITUTE DRILLING TECHNOLOGY%') or UPPER(oum.PLACE_POSTING) like UPPER('%IPE%')");
			}
			if (user.getCurrentPlace().equals("Kolkata")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Calcutta%')");
			}

			if (user.getCurrentPlace().equals("Delhi")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%New Delhi%') or UPPER(oum.PLACE_POSTING) like UPPER('%OVL%')");
			}
			query.append(")");
		}
		if (user.getDepartment() != null && user.getDepartment().trim().length() > 0) {
			query.append(" or UPPER(oum.DEPT) like UPPER('%").append(user.getDepartment()).append("%')");
		}
	
		if (user.getMobileNo() != null && !user.getMobileNo().isEmpty()) {
			query.append(" or UPPER(oum.MOBILE_NUMBER) like UPPER('%").append(user.getMobileNo()).append("%')");
		}
		// Changes By Lokesh
		if (user.getPassionId() != null && !user.getPassionId().equals("-1") && !user.getPassionId().isEmpty()) {
			query.append(" or UPPER(pmp.passion_category) = UPPER('").append(user.getPassionId()).append("')");
		}
		if (user.getSubPassionId() != null && !user.getSubPassionId().equals("-1") && !user.getSubPassionId().isEmpty()) {
			query.append(" or UPPER(pmp.passion_sub_category) = UPPER('").append(user.getSubPassionId()).append("')");
		}
		
		// End Changes
		//query.append(" AND ( DOS > CURRENT_DATE OR DOS IS NULL)");
		
		// query.append(" and  floor(months_between(sysdate,dt_birth)) >1 and floor(months_between(sysdate,dt_birth)) <722 ");
		query.append(" ORDER BY EMP_NAME");
		
		LOGGER.info("ongcianFinderDao--------->"+ query.toString());
		FindOngcianManagementDao ongcianManagement = new FindOngcianManagementDaoImpl();
		// system.out.println("Lenght of list  users");
		List<User> users = ongcianManagement.findOngcian(query.toString());
		// system.out.println("Lenght of list  users"+ users.size());
		
		*/
		
		StringBuffer query=null;
		if(user.getCpfNo().length()==5 || user.getCpfNo().length()==6){
			 query = new StringBuffer(" where UPPER(oum.CPF_NUMBER) = '");
				query.append(user.getCpfNo()).append("' ");	
		}else{
	   query = new StringBuffer(" where UPPER(oum.CPF_NUMBER) like ('%");
		query.append(user.getCpfNo()).append("%') ");
		}

		query.append(" and EMP_LEVEL not in ('Retd','Seprtd','Rsgnd','Demise') ");

		if (user.getEmpName() != null && user.getEmpName().trim().length() > 0) {
			query.append(" and UPPER(oum.EMP_NAME) like UPPER('%");
			query.append(user.getEmpName()).append("%')");
		}

		if (user.getEmpLevel() != null && user.getEmpLevel().trim().length() > 0 && !user.getEmpLevel().equals("-1") && !user.getEmpLevel().equalsIgnoreCase("Select")) {
				query.append(" and oum.EMP_LEVEL = '").append(user.getEmpLevel()).append("'");
		}
		
		if (user.getCurrentPlace() != null && user.getCurrentPlace().trim().length() > 0 && !user.getCurrentPlace().equalsIgnoreCase("Select")) {
			query.append(" and (UPPER(oum.PLACE_POSTING) like UPPER('%")
					.append(user.getCurrentPlace()).append("%')");
			if (user.getCurrentPlace().equals("Vadodara")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Baroda%')");
			}
			if (user.getCurrentPlace().equals("Mumbai")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Bombay%') or UPPER(oum.PLACE_POSTING) like UPPER('%Neelam%') ");
			}
			if (user.getCurrentPlace().equals("Ahmedabad")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%IRS%')");
			}
			if (user.getCurrentPlace().equals("Kakinada")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%E.O. ASSET%')");
			}
			if (user.getCurrentPlace().equals("Dehradun")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%INSTITUTE DRILLING TECHNOLOGY%') or UPPER(oum.PLACE_POSTING) like UPPER('%IPE%')");
			}
			if (user.getCurrentPlace().equals("Kolkata")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%Calcutta%')");
			}

			if (user.getCurrentPlace().equals("Delhi")) {
				query.append(" or UPPER(oum.PLACE_POSTING) like UPPER('%New Delhi%') or UPPER(oum.PLACE_POSTING) like UPPER('%OVL%')");
			}
			query.append(")");
		}
		if (user.getDepartment() != null && user.getDepartment().trim().length() > 0) {
			query.append(" and UPPER(oum.DEPT) like UPPER('%").append(user.getDepartment()).append("%')");
		}
	
		if (user.getMobileNo() != null && !user.getMobileNo().isEmpty()) {
			query.append(" and UPPER(oum.MOBILE_NUMBER) like UPPER('%").append(user.getMobileNo()).append("%')");
		}
		
		if (user.getPassionId() != null && !user.getPassionId().equals("-1")
				&& !user.getPassionId().isEmpty()) {
			query.append(" and UPPER(pmp.passion_category) = UPPER('").append(user.getPassionId()).append("')");
		}
		if (user.getSubPassionId() != null && !user.getSubPassionId().equals("-1")
				&& !user.getSubPassionId().isEmpty()) {
			query.append(" and UPPER(pmp.passion_sub_category) = UPPER('").append(user.getSubPassionId()).append("')");
		}
		query.append(" AND ( DOS > Date(NOW()) OR DOS IS NULL)");
		//query.append(" ORDER BY EMP_NAME");
		FindOngcianManagementDao ongcianManagement = new FindOngcianManagementDaoImpl();
		List<User> users = ongcianManagement.findOngcian(query.toString());
		return users;
	}

}
