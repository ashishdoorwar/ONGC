package com.ongc.liferay.dao;


import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.model.FacultyBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FacultyDao {
	
	public ArrayList<FacultyBean> getFacData(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		ArrayList<FacultyBean> alist = new ArrayList<FacultyBean>();
		String query ="";
		try {
			if(id==0)
				query = "select b.facid,b.cpfno,b.name,b.designation,b.mob,b.email,b.fax,b.address,b.city,b.specialisation,b.academics,b.superannuation,b.program_title,b.program_code,b.description,b.posted_date from rt_faculty_details b where b.status='Y' order by b.posted_date desc ";
			else
				query = "select b.facid,b.cpfno,b.name,b.designation,b.mob,b.email,b.fax,b.address,b.city,b.specialisation,b.academics,b.superannuation,b.program_title,b.program_code,b.description,b.posted_date from rt_faculty_details b where b.status='Y' and facid="+id+" order by b.posted_date desc ";	 
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			res = pstmt.executeQuery();
			while (res.next()) {
				FacultyBean fbean = new FacultyBean();
				fbean.setFacid(res.getInt("facid"));
				fbean.setCpfno(res.getString("cpfno"));
				fbean.setName(res.getString("name"));
				fbean.setDesignation(res.getString("designation"));
				fbean.setMob(res.getString("mob"));
				fbean.setEmail(res.getString("email"));
				fbean.setFax(res.getString("fax"));
				fbean.setAddress(res.getString("address"));
				fbean.setCity(res.getString("city"));
				fbean.setSpecialisation(res.getString("specialisation"));
				fbean.setAcademics(res.getString("academics"));
				SimpleDateFormat stm = new SimpleDateFormat("MM/dd/yyyy");
				String dos="";
				if(res.getDate("superannuation")!=null)
				{
					dos = stm.format(res.getDate("superannuation"));
				}
				fbean.setDos(dos);
				// fbean.setDos(res.getString("superannuation"));
				fbean.setTitle(res.getString("program_title"));
				fbean.setCode(res.getString("program_code"));
				fbean.setDescription(res.getString("description"));
				String posdate = stm.format(res.getDate("posted_date"));
				fbean.setPosted_date(posdate);
				alist.add(fbean);
			}

		} catch (Exception e) {
			//system.out.println("getFacData Exception  "+e.getMessage());
			e.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, res);
		}
		return alist;
	}



	public FacultyBean getFacDataById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		FacultyBean fbean = new FacultyBean();
		String query ="";
		try {
			query = "select b.facid,b.cpfno,b.name,b.designation,b.mob,b.email,b.fax,b.address,b.city,b.specialisation,b.academics,b.superannuation,b.program_title,b.program_code,b.description,b.posted_date from rt_faculty_details b where b.status='Y' and facid="+id+" order by b.posted_date desc ";	 
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);

			res = pstmt.executeQuery();
			while (res.next()) {


				fbean.setFacid(res.getInt("facid"));
				fbean.setCpfno(res.getString("cpfno"));
				fbean.setName(res.getString("name"));
				fbean.setDesignation(res.getString("designation"));
				fbean.setMob(res.getString("mob"));

				fbean.setEmail(res.getString("email"));
				fbean.setFax(res.getString("fax"));
				fbean.setAddress(res.getString("address"));

				if(res.getString("city")!=null)
				{
					fbean.setCity(res.getString("city"));
				}
				else
					fbean.setCity("");
				fbean.setSpecialisation(res.getString("specialisation"));
				fbean.setAcademics(res.getString("academics"));

				SimpleDateFormat stm = new SimpleDateFormat("MM/dd/yyyy");
				String dos="";
				if(res.getDate("superannuation")!=null)
				{
					dos = stm.format(res.getDate("superannuation"));
				}
				fbean.setDos(dos);
				// fbean.setDos(res.getString("superannuation"));

				fbean.setTitle(res.getString("program_title"));
				fbean.setCode(res.getString("program_code"));
				if(res.getString("description")!=null)
				{
					fbean.setDescription(res.getString("description"));
				}
				else
					fbean.setDescription("");


				String posdate = stm.format(res.getDate("posted_date"));
				fbean.setPosted_date(posdate);


			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, res);
		}
		return fbean;
	}

}
