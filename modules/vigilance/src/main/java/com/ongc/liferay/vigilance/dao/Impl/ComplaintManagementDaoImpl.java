package com.ongc.liferay.vigilance.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.flash.FlashMagicBytesUtil.Result;
import com.liferay.portal.kernel.util.Base64;
import com.ongc.liferay.vigilance.connection.DatasourceConnection;
import com.ongc.liferay.vigilance.dao.ComplaintManagementDao;
import com.ongc.liferay.vigilance.model.ComplaintAttachment;
import com.ongc.liferay.vigilance.model.ComplaintReply;
import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;
import com.ongc.liferay.vigilance.util.VigilanceConstant;

public class ComplaintManagementDaoImpl implements ComplaintManagementDao {

	private Connection connection;

	private static String sql = "INSERT INTO COMPLAINT_DETAILS(USERID,COMPLAINT_AGAINST,DESIGNATION,DEPARTMENT,WORK_CENTRE,COMPLAINT_SUBJECT,COMPLAINT_DETAIL,COMPLAINT_COUNTRY,COMPLAINT_STATE,TENDER_DOCUMENT,PINCODE,VERIFIED,IP_ADDRESS,COMPLAINT_NO,COMPLAINT_NUMBER,ATTACHMENT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	//private static String insertAttachment = "INSERT INTO COMPLAIN_ATTACHMENT(FILENAME,FILEDATA,COMPLAINT_ID,ID) VALUES (?, ?,?,?)";
	private static String insertAttachment = "INSERT INTO COMPLAIN_ATTACHMENT(FILENAME,FILEDATA,COMPLAINT_ID,ID) VALUES (?,?,?,?)";

	// registerComplaintOPT
	// registerComplaint
	@Override
	public synchronized int insertComplaint(VigilanceComplaint complaint,
			String complaintAgainst, String ipAddress) throws SQLException {
		
		int id = getMaxIdFromTable("COMPLAINT_DETAILS", "COMPLAINT_NUMBER");
		connection = DatasourceConnection.getConnection();
		String COMPLAINT_NO = "VIG00001";
		PreparedStatement pstm1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstm =null;
		ResultSet resultSet =null;
		int repl_veg = 0;
		try {
			String selectSql1 = "SELECT COMPLAINT_NO  FROM COMPLAINT_DETAILS WHERE COMPLAINT_NUMBER=(SELECT max ( COMPLAINT_NUMBER ) as COMPLAINT_NUMBER FROM COMPLAINT_DETAILS)";
			Statement stm1=connection.createStatement();
			ResultSet resultSet1 = stm1.executeQuery(selectSql1);
			while (resultSet1.next()) {
				COMPLAINT_NO = resultSet1.getString(1);
				repl_veg = Integer.parseInt(COMPLAINT_NO.replace("VIG", "")) + 1;
				String newCOMPLAINT_NO = "";
				for (int i = 0; i < (COMPLAINT_NO.length())
						- ("" + repl_veg).length(); i++) {
					newCOMPLAINT_NO = newCOMPLAINT_NO + COMPLAINT_NO.charAt(i);
				}
				COMPLAINT_NO = (newCOMPLAINT_NO + repl_veg);
			}
			if (complaintAgainst.trim().equals("-1"))
				complaintAgainst = "";
			//id=getMaxIdFromTable("COMPLAINT_DETAILS", "COMPLAINT_NUMBER");
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, complaint.getUserId());
			pstmt.setString(2, complaintAgainst);
			pstmt.setString(3, complaint.getDesignation());
			pstmt.setString(4, complaint.getDepartmetn());
			pstmt.setString(5, complaint.getWorkCentre());
			pstmt.setString(6, Base64.encode(complaint.getComplaintSubject().getBytes()));
			pstmt.setString(7, Base64.encode(complaint.getComplaintDetail().getBytes()));
			pstmt.setString(8, complaint.getCountry());
			pstmt.setString(9, complaint.getState());
			pstmt.setString(10, complaint.getTenderNumber());
			pstmt.setString(11, complaint.getPincode());
			pstmt.setString(12, VigilanceConstant.COMPLAINT_STATUS_ACTIVE);
			pstmt.setString(13, ipAddress);
			pstmt.setString(14, COMPLAINT_NO);
			pstmt.setInt(15, id);
			pstmt.setString(16, complaint.getAttachment());
			int i = pstmt.executeUpdate();
			if (i > 0 && complaint.getAttachmentList() != null && complaint.getAttachmentList().size() > 0) {
				String selectSql = "SELECT max ( COMPLAINT_NUMBER ) as COMPLAINT_NUMBER FROM COMPLAINT_DETAILS";
				resultSet  = connection.createStatement().executeQuery(selectSql);
				 pstm =connection.prepareStatement(insertAttachment);
				while (resultSet.next()) {
					id = resultSet.getInt("COMPLAINT_NUMBER");
				}
				List<ComplaintAttachment> attachments = complaint.getAttachmentList();
				int attachment_id = 0;
				for (int j = 0; j < attachments.size(); j++) {				
					attachment_id = getMaxIdFromTable("complain_attachment", "id");
					pstm.setString(1,COMPLAINT_NO+"_"+ attachments.get(j).getFileName());
					pstm.setBytes(2, null);//attachments.get(j).getFileData()
					pstm.setInt(3, id);
					pstm.setInt(4, attachment_id);
					pstm.executeUpdate();
				}
			} else {
				    id = i;
			       }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				DatasourceConnection.closeConnection(connection,pstmt ,resultSet);
			if (pstm1 != null)
				DatasourceConnection.closeConnection(connection,pstm1 ,resultSet);
		}
//		System.out.println(repl_veg+"COMPLAINT_NUMBER======>"+id);
		return repl_veg;
	}

	public String getComplaintID(long uniqueKey) {
		String sql = "SELECT COMPLAINT_NUMBER FROM COMPLAINT_DETAILS where COMPLAINT_NUMBER = "
				+ uniqueKey;
		String complaintId = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				complaintId = rs.getString("COMPLAINT_NUMBER");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,stmt ,rs);
		}
		return complaintId;
	}

	@Override
	public int verifyComplaintDetail(long uniquKey, String complaintStatusActive) {
		ResultSet resultSet=null;
		String sql = "UPDATE COMPLAINT_DETAILS SET VERIFIED = ? , VERIFIED_DATE = ? WHERE UNIQUE_KEY = ? ";
		int i = 0;
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, complaintStatusActive);
			pstmt.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
			pstmt.setLong(3, uniquKey);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,resultSet);
		}
		return i;
	}

	public List<VigilanceComplaint> getUpdateComplaintList() {
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		// String
		// query="SELECT C.COMPLAINT_NUMBER, C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,"+
		// " C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,CU.COUNTRY_NAME,U.TITLE,"+
		// " U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,U.MOBILE FROM COMPLAINT_DETAILS C,COUNTRY_MASTER CU, VIGILANCE_USER_MASTER U "+
		// "    WHERE CU.ID_KEY=C.COMPLAINT_COUNTRY AND U.REGISTRATION_ID=C.USERID";
		ResultSet set = null;
		String query = "SELECT C.COMPLAINT_NUMBER , C.COMPLAINT_COUNTRY,   C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,U.MOBILE,C.COMPLAINT_NO FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE  U.REGISTRATION_ID=C.USERID and C.STATUS='READ' ORDER BY C.COMPLAINT_DATE DESC";
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				complaint.setComplainBy(new VigilanceUser());

				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(set
						.getString("COMPLAINT_AGAINST"));
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
//				complaint.getComplainBy().setTitle((set.getString("TITLE").equals("-1")) ? "" : set.getString("TITLE"));
				complaint.getComplainBy().setFirstName(
						set.getString("FIRST_NAME"));
				complaint.getComplainBy().setMiddleName(
						set.getString("MIDDLE_NAME"));
				complaint.getComplainBy().setLastName(
						set.getString("LAST_NAME"));
				complaint.getComplainBy().setEmailId(set.getString("EMAIL_ID"));
				complaint.getComplainBy().setMobile(set.getString("MOBILE"));
				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));
				complaint.setAttachmentList(getAttachmentByComplaintId(set
						.getInt("COMPLAINT_NUMBER")));

				complaintList.add(complaint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	// viewcomplaintpage
	public List<VigilanceComplaint> getComplaintList() {
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		// String
		// query="SELECT C.COMPLAINT_NUMBER, C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,"+
		// " C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,CU.COUNTRY_NAME,U.TITLE,"+
		// " U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,U.MOBILE FROM COMPLAINT_DETAILS C,COUNTRY_MASTER CU, VIGILANCE_USER_MASTER U "+
		// "    WHERE CU.ID_KEY=C.COMPLAINT_COUNTRY AND U.REGISTRATION_ID=C.USERID";
		ResultSet set=null;
		String query = "SELECT C.COMPLAINT_NUMBER , C.COMPLAINT_COUNTRY,   C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,U.MOBILE,C.COMPLAINT_NO FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE  U.REGISTRATION_ID=C.USERID and C.STATUS='NEW'";
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query
					+ " ORDER BY C.COMPLAINT_DATE DESC");
			set = pstmt.executeQuery();
			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				complaint.setComplainBy(new VigilanceUser());
				String conplaintAgnst = set.getString("COMPLAINT_AGAINST");
				if (conplaintAgnst != null && conplaintAgnst.equals("-1"))
					conplaintAgnst = "";
				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(conplaintAgnst);
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
				complaint.getComplainBy().setTitle(
						(set.getString("TITLE").equals("-1")) ? "" : set
								.getString("TITLE"));
				complaint.getComplainBy().setFirstName(
						set.getString("FIRST_NAME"));
				complaint.getComplainBy().setMiddleName(
						set.getString("MIDDLE_NAME"));
				complaint.getComplainBy().setLastName(
						set.getString("LAST_NAME"));
				complaint.getComplainBy().setEmailId(set.getString("EMAIL_ID"));
				complaint.getComplainBy().setMobile(set.getString("MOBILE"));
				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));
				complaint.setAttachmentList(getAttachmentByComplaintId(set
						.getInt("COMPLAINT_NUMBER")));

				complaintList.add(complaint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	public int updateSAPComplaint(String startDate, String action,String remarks, String sapNo, int complaintId,String otherStatus)
	{
		ResultSet resultSet=null;
		String sDate = "";
		Date d1=null,d2;
		SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stm1 = new SimpleDateFormat("MM-dd-yyyy");

	
		try{
		d1 = stm.parse(startDate);
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		sDate=stm1.format(d1);
		int i = -1;
		String query = "UPDATE COMPLAINT_DETAILS SET ACTION=?,VERIFIED_DATE=cast(? as timestamp),SAPNO=?,REMARKS=?,OTHERSTATUS=? WHERE COMPLAINT_NUMBER=?";
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, action);
			pstmt.setString(2, sDate);
			pstmt.setString(3, sapNo);
			pstmt.setString(4, remarks);
			pstmt.setString(5, otherStatus);
			pstmt.setInt(6, complaintId);
			i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,resultSet);
		}
		return i;
	}
  
	
	//forward complaint
	
	public int updateForwardComplaint(int complaintId)
	{
		ResultSet resultSet=null;
		int i = -1;
		String query = "UPDATE COMPLAINT_DETAILS SET STATUS='FORWARD' WHERE COMPLAINT_NUMBER=?";
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, complaintId);
			i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,resultSet);
		}
		return i;
	}
	
	
	
	
	//reportComplaintDateWise
	
	public List<VigilanceComplaint> searchComplaintList(String startdate,
			String enddate, String complaintNo, String complaintStatus) {
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		String query = "SELECT C.COMPLAINT_NUMBER , C.COMPLAINT_COUNTRY,  C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID, U.MOBILE,C.COMPLAINT_NO,C.STATUS FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE  U.REGISTRATION_ID=C.USERID";
		if (complaintStatus != null) {
			if (complaintNo.trim().equals(""))
				complaintNo = null;
			if (startdate.trim().equals(""))
				startdate = null;
			if (enddate.trim().equals(""))
				enddate = null;
			// TIMESTAMP(CAST(? AS VARCHAR(10)),'00:00:00')
			// CHAR(date(C.COMPLAINT_DATE), USA)
			//Password Incorrect
			if (startdate != null && enddate != null && complaintNo != null && !complaintStatus.equals("-1")) {
				query = query+ " AND date(C.COMPLAINT_DATE) between date('"+startdate+"') and date('"+enddate+"') and C.COMPLAINT_NO='"+complaintNo+"'  and C.STATUS like '%"+complaintStatus+"%'";
			} else if (startdate != null && enddate != null && complaintNo != null) {
				query = query+ " AND date(C.COMPLAINT_DATE) between date('"+startdate+"') and date('"+enddate+"')  and C.COMPLAINT_NO like '%"+complaintNo+"%'";
			} else if (startdate != null && enddate != null
					&& !complaintStatus.equals("-1")) {
				query = query+ " AND date(C.COMPLAINT_DATE) between date('"+startdate+"') and date('"+enddate+"') and C.STATUS like '%"+complaintStatus+"%'";
			} else if (complaintNo != null && !complaintStatus.equals("-1")) {
				query = query + " and C.COMPLAINT_NO=?  and C.STATUS=?";
			} else if (startdate != null && enddate != null) {				
				query = query+ " AND date(C.COMPLAINT_DATE) between date('"+startdate+"') and date('"+enddate+"')";
			} else if (complaintNo != null) {
				query = query + " and C.COMPLAINT_NO like '%"+complaintNo+"%'";
			} else if (!complaintStatus.equals("-1")) {
				query = query + "  and C.STATUS like '%"+complaintStatus+"%'";
			}
		}
		//
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query+ " ORDER BY C.COMPLAINT_DATE DESC");
			if (complaintStatus != null) {
				if (startdate != null && enddate != null && complaintNo != null
						&& !complaintStatus.equals("-1")) {					
					//pstmt.setString(1, startdate);
					//pstmt.setString(2, enddate);
					//pstmt.setString(1, complaintNo);
					//pstmt.setString(2, complaintStatus);
				} else if (startdate != null && enddate != null
						&& complaintNo != null) {
					//startdate = startdate.replace('-', '/');
					//enddate = enddate.replace('-', '/');
					//pstmt.setString(1, startdate);
					//pstmt.setString(2, enddate);
					//pstmt.setString(1, complaintNo);

				} else if (startdate != null && enddate != null
						&& !complaintStatus.equals("-1")) {
					//startdate = startdate.replace('-', '/');
					//enddate = enddate.replace('-', '/');
					//pstmt.setString(1, startdate);
					//pstmt.setString(2, enddate);
					//pstmt.setString(1, complaintStatus);
				} else if (complaintNo != null && !complaintStatus.equals("-1")) {

					//pstmt.setString(1, complaintNo);
					//pstmt.setString(2, complaintStatus);
				} /*else if (startdate != null && enddate != null) {					
					pstmt.setString(1, startdate);
					pstmt.setString(2, enddate);

				}*/ else if (complaintNo != null) {
					//pstmt.setString(1, complaintNo);

				} else if (!complaintStatus.equals("-1")) {

					//pstmt.setString(1, complaintStatus);
				}
			}
			set = pstmt.executeQuery();
			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				VigilanceUser vigilanceUser = new VigilanceUser();
				complaint.setComplainBy(vigilanceUser);
				System.out.println(complaint.getComplainBy());
				String conplaintAgnst = set.getString("COMPLAINT_AGAINST");
				if (conplaintAgnst != null && conplaintAgnst.equals("-1"))
					conplaintAgnst = "";
				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(conplaintAgnst);
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
				//complaint.getComplainBy().setTitle((set.getString("TITLE").equals("-1")) ? "" : set.getString("TITLE"));
				System.out.println("------> " +set.getString("TITLE"));
				complaint.getComplainBy().setFirstName(
						set.getString("FIRST_NAME"));
				complaint.getComplainBy().setMiddleName(
						set.getString("MIDDLE_NAME"));
				complaint.getComplainBy().setLastName(
						set.getString("LAST_NAME"));
				complaint.getComplainBy().setEmailId(set.getString("EMAIL_ID"));
				complaint.getComplainBy().setMobile(set.getString("MOBILE"));
				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));

				complaint.setStatus(set.getString("STATUS"));
				complaint.setAttachmentList(getAttachmentByComplaintId(set
						.getInt("COMPLAINT_NUMBER")));
				complaintList.add(complaint);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	public VigilanceComplaint getComplaintDetails(int complaintId) {
		VigilanceComplaint complaint = null;
		// String
		// query="SELECT C.COMPLAINT_NUMBER, C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,"+
		// "C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,CU.COUNTRY_NAME,U.TITLE,"+
		// "U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,U.MOBILE FROM COMPLAINT_DETAILS C,COUNTRY_MASTER CU, VIGILANCE_USER_MASTER U "+
		// "WHERE CU.ID_KEY=C.COMPLAINT_COUNTRY AND U.REGISTRATION_ID=C.USERID AND C.COMPLAINT_NUMBER=?";
		String query = "SELECT C.COMPLAINT_NUMBER,C.COMPLAINT_COUNTRY, C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,"
				+ "C.COMPLAINT_SUBJECT,C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS, C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,"
				+ "U.LAST_NAME,U.EMAIL_ID,U.FIRST_ADDRESS,U.SECOND_ADDRESS,U.MOBILE,U.STATE,(SELECT COUNTRY_NAME FROM COUNTRY_MASTER WHERE ID_KEY= U.COUNTRY) COUNTRY_NAME,C.COMPLAINT_NO,C.STATUS,C.ACTION,C.REMARKS,C.SAPNO,C.VERIFIED_DATE,C.ATTACHMENT FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE U.REGISTRATION_ID=C.USERID AND C.COMPLAINT_NUMBER= ?";
		PreparedStatement pstmt = null;// COUNTRY_MASTER CM WHERE
										// U.REGISTRATION_ID=C.USERID and
										// CM.ID_KEY=U.COUNTRY
		ResultSet set =null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, complaintId);
			set = pstmt.executeQuery();
			VigilanceUser vu = null;
			if (set.next()) {
				complaint = new VigilanceComplaint();
				vu = new VigilanceUser();

				String conplaintAgnst = set.getString("COMPLAINT_AGAINST");
				if (conplaintAgnst != null && conplaintAgnst.equals("-1"))
					conplaintAgnst = "";
				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(conplaintAgnst);
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setState(set.getString("COMPLAINT_STATE"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
//				vu.setTitle((set.getString("TITLE").equals("-1")) ? "" : set.getString("TITLE"));
				vu.setFirstName(set.getString("FIRST_NAME"));
				vu.setMiddleName(set.getString("MIDDLE_NAME"));
				vu.setLastName(set.getString("LAST_NAME"));
				vu.setEmailId(set.getString("EMAIL_ID"));
				vu.setMobile(set.getString("MOBILE"));
				vu.setState(set.getString("STATE"));
				vu.setCountry(set.getString("COUNTRY_NAME"));
				vu.setFirstAddress(set.getString("FIRST_ADDRESS"));
				vu.setSecondAddress(set.getString("SECOND_ADDRESS"));
				complaint.setComplainBy(vu);
				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));
				complaint.setStatus(set.getString("STATUS"));
				complaint.setAction(set.getString("ACTION"));
				complaint.setSapNo(set.getString("SAPNO"));
				complaint.setRemarks(set.getString("REMARKS"));
				complaint.setVerifiedDate(set.getDate("VERIFIED_DATE"));
				complaint.setAttachment(set.getString("ATTACHMENT"));
				complaint.setAttachmentList(getAttachmentByComplaintId(complaintId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaint;
	}

	public void updateComplaintStatus(int complaintId) {
		PreparedStatement pstmt1 = null;
		ResultSet set=null;
		try {
			String query = "UPDATE COMPLAINT_DETAILS SET STATUS='READ' WHERE COMPLAINT_NUMBER= ?";
			connection = DatasourceConnection.getConnection();
			pstmt1 = connection.prepareStatement(query);
			pstmt1.setInt(1, complaintId);
			int i = pstmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt1 ,set );
		}
	}

	// COMPLAINT_DETAILS
	public List<ComplaintAttachment> getAttachmentByComplaintId(int complaintId) {
		List<ComplaintAttachment> attachmentList = new ArrayList<ComplaintAttachment>();
		ComplaintAttachment attachment = null;
		String query = "SELECT ID, COMPLAINT_ID, FILENAME FROM  COMPLAIN_ATTACHMENT  WHERE COMPLAINT_ID=?";
		PreparedStatement pstmt = null;
		ResultSet set=null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, complaintId);
			set = pstmt.executeQuery();
			while (set.next()) {
				attachment = new ComplaintAttachment();
				attachment.setAttachmentId(set.getInt("ID"));
				attachment.setComplaintId(set.getInt("COMPLAINT_ID"));
				attachment.setFileName(set.getString("FILENAME"));

				attachmentList.add(attachment);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return attachmentList;
	}

	public boolean saveComplaintReply(ComplaintReply reply) {
		boolean flage = false;
		ResultSet resultSet=null;
		String query = "INSERT INTO COMPLAINT_REPLIES (COMPLAINT_ID , REPLY_TEXT, REPLY_BY) VALUES(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, reply.getComplaintId());
			pstmt.setString(2, reply.getReplyText());
			pstmt.setInt(3, reply.getReplyBy());
			flage = 0 < pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,resultSet);
		}
		return flage;
	}

	public List<ComplaintReply> getComplaintReplyList(int complaintId) {
		List<ComplaintReply> replyList = new ArrayList<ComplaintReply>();
		String query = "SELECT R.REPLY_ID,R.COMPLAINT_ID,R.REPLY_TEXT,R.REPLY_DATE, U.TITLE, U.FIRST_NAME FROM COMPLAINT_REPLIES R ,VIGILANCE_USER_MASTER U WHERE U.REGISTRATION_ID=R.REPLY_BY AND R.COMPLAINT_ID=?";
		PreparedStatement pstmt = null;
		ResultSet set =null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, complaintId);
			set = pstmt.executeQuery();
			ComplaintReply reply = null;
			while (set.next()) {
				reply = new ComplaintReply();
				reply.setUser(new VigilanceUser());
				reply.setReplyId(set.getInt("REPLY_ID"));
				reply.setComplaintId(set.getInt("COMPLAINT_ID"));
				reply.setReplyText(set.getString("REPLY_TEXT"));
				reply.setReplyDate(set.getDate("REPLY_DATE"));
				reply.getUser().setTitle(set.getString("TITLE"));
				reply.getUser().setFirstName(set.getString("FIRST_NAME"));
				replyList.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return replyList;
	}

	public ComplaintAttachment getAttachmentById(int attachmentId) {

		ComplaintAttachment attachment = null;
		String query = "SELECT ID, COMPLAINT_ID, FILENAME, FILEDATA FROM  COMPLAIN_ATTACHMENT  WHERE ID=?";
		PreparedStatement pstmt = null;
		ResultSet set =null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, attachmentId);
			set = pstmt.executeQuery();
			while (set.next()) {
				attachment = new ComplaintAttachment();
				attachment.setAttachmentId(set.getInt("ID"));
				attachment.setComplaintId(set.getInt("COMPLAINT_ID"));
				attachment.setFileName(set.getString("FILENAME"));
				attachment.setFileData(set.getBytes("FILEDATA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return attachment;
	}

	@Override
	public List<VigilanceComplaint> selectComplaintByUserID(int registrationId) {
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		String query = "SELECT CD.COMPLAINT_NUMBER, CD.COMPLAINT_AGAINST, CD.DEPARTMENT,CD.DESIGNATION,CD.COMPLAINT_COUNTRY,CD.COMPLAINT_SUBJECT,"
				+ "CD.COMPLAINT_DETAIL,CD.COMPLAINT_DATE, CD.WORK_CENTRE,CD.COMPLAINT_NO,CD.STATUS,CD.ACTION,CD.REMARKS,CD.VERIFIED_DATE,CD.SAPNO FROM COMPLAINT_DETAILS CD WHERE CD.USERID = ? ORDER BY CD.COMPLAINT_DATE DESC";
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, registrationId);
			set = pstmt.executeQuery();

			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(set
						.getString("COMPLAINT_AGAINST"));
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));

				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));
				complaint.setStatus(set.getString("STATUS"));
				complaint.setAction(set.getString("ACTION"));
				complaint.setRemarks(set.getString("REMARKS"));
				complaint.setVerifiedDate(set.getDate("VERIFIED_DATE"));
				complaint.setSapNo(set.getString("SAPNO"));
				complaintList.add(complaint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	public static void main(String[] arg) {
		String encodedBytes = Base64.encode("Test".getBytes());
		byte[] decodedBytes = Base64.decode(encodedBytes);

	}

	public List<VigilanceComplaint> reportDateComplaintList(String startdate,
			String enddate, String act) {
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		// VERIFIED_DATE=TO_DATE(?,'DD-MM-YYYY'),SAPNO=?,REMARKS
		ResultSet set = null;
		Date d1=null,d2=null;
		SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stm1 = new SimpleDateFormat("MM-dd-yyyy");
		try{
			if(startdate!=null && !startdate.equals(""))
			d1 = stm.parse(startdate);
			if(enddate!=null && !enddate.equals(""))
			d2 = stm.parse(enddate);
			
			}
			catch(Exception e){
				
				e.printStackTrace();
			}
		if(d1!=null)
		startdate=stm1.format(d1);
		if(d2!=null)
		enddate=stm1.format(d2);
		
		
		
		
		String query = "SELECT C.COMPLAINT_NUMBER , C.COMPLAINT_COUNTRY,   C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT, C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID, U.MOBILE,U.STATE,U.FIRST_ADDRESS,(SELECT COUNTRY_NAME FROM COUNTRY_MASTER WHERE ID_KEY= U.COUNTRY) COUNTRY_NAME,C.COMPLAINT_NO,C.STATUS,C.ACTION,C.VERIFIED_DATE,C.SAPNO,C.REMARKS,C.OTHERSTATUS FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE  U.REGISTRATION_ID=C.USERID ";
		if (!startdate.trim().equals("") && !enddate.trim().equals("")) {
			query = query+ " AND date(C.COMPLAINT_DATE) between date('"+startdate+"') and date('"+enddate+"')";
		}

		if (act != null && !act.equalsIgnoreCase("-1")) {
			query = query + " AND  C.ACTION ='" + act + "'";}
		if(act != null && act.equalsIgnoreCase("-1")) {
			query = query + " AND  C.ACTION in ('Under Examination','Under Investigation','Closed','Others')";
		}

		PreparedStatement pstmt = null;

		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query
					+ " ORDER BY C.COMPLAINT_DATE DESC");
			System.out.println(pstmt);
			//startdate = startdate.replace('-', '/');
			//enddate = enddate.replace('-', '/');
			//pstmt.setString(1, startdate);
			//pstmt.setString(2, enddate);
			set = pstmt.executeQuery();
			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				complaint.setComplainBy(new VigilanceUser());

				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(set.getString("COMPLAINT_AGAINST"));
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
				complaint.getComplainBy().setTitle(
						(set.getString("TITLE").equals("-1")) ? "" : set
								.getString("TITLE"));
				complaint.getComplainBy().setFirstName(
						set.getString("FIRST_NAME"));
				complaint.getComplainBy().setMiddleName(
						set.getString("MIDDLE_NAME"));
				complaint.getComplainBy().setLastName(
						set.getString("LAST_NAME"));
				complaint.getComplainBy().setEmailId(set.getString("EMAIL_ID"));
				complaint.getComplainBy().setMobile(set.getString("MOBILE"));
				complaint.getComplainBy().setState(set.getString("STATE"));
				complaint.getComplainBy().setCountry(
						set.getString("COUNTRY_NAME"));
				complaint.getComplainBy().setFirstAddress(
						set.getString("FIRST_ADDRESS"));

				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));

				complaint.setStatus(set.getString("STATUS"));
				complaint.setAction(set.getString("ACTION"));
				complaint.setRemarks(set.getString("REMARKS"));
				complaint.setVerifiedDate(set.getDate("VERIFIED_DATE"));
				complaint.setSapNo(set.getString("SAPNO"));
				complaint.setAttachmentList(getAttachmentByComplaintId(set
						.getInt("COMPLAINT_NUMBER")));
				complaint.setOtherStatus(set.getString("OTHERSTATUS"));
				complaintList.add(complaint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	public List<VigilanceComplaint> reportMonthComplaintList(int month,
			int year, String act) {
		ResultSet set=null;
		List<VigilanceComplaint> complaintList = new ArrayList<VigilanceComplaint>();
		// VERIFIED_DATE=TO_DATE(?,'DD-MM-YYYY'),SAPNO=?,REMARKS
		String query = "SELECT C.COMPLAINT_NUMBER , C.COMPLAINT_COUNTRY,   C.COMPLAINT_AGAINST, C.DEPARTMENT,C.DESIGNATION,C.PINCODE,C.COMPLAINT_STATE,C.COMPLAINT_SUBJECT,"
				+ " C.COMPLAINT_DETAIL,C.COMPLAINT_DATE,C.IP_ADDRESS,C.WORK_CENTRE,U.TITLE,U.FIRST_NAME,U.MIDDLE_NAME,U.LAST_NAME,U.EMAIL_ID,"
				+ " U.MOBILE,C.COMPLAINT_NO,C.STATUS,C.ACTION,C.VERIFIED_DATE,C.SAPNO,C.REMARKS,C.OTHERSTATUS FROM COMPLAINT_DETAILS C, VIGILANCE_USER_MASTER U WHERE  U.REGISTRATION_ID=C.USERID";
		if (month > -1 && year > -1) {

			query = query + " AND EXTRACT(month from C.COMPLAINT_DATE)=? and EXTRACT(YEAR from C.COMPLAINT_DATE)=?";
			//AND trim(char(month(date(C.COMPLAINT_DATE))))=3   and trim(char(year(date(C.COMPLAINT_DATE))))=2014
		}
		if (act != null && !act.equalsIgnoreCase("-1")) {
			query = query + " AND  C.ACTION ='" + act + "'";}
		if(act != null && act.equalsIgnoreCase("-1")) {
			query = query + " AND  C.ACTION in ('Under Examination','Under Investigation','Closed','Others')";
		}
		PreparedStatement pstmt = null;

		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query
					+ " ORDER BY C.COMPLAINT_DATE DESC");
			//String mnths = "" + month;
			
			
			pstmt.setInt(1, month);
		 pstmt.setInt(2,year);
		 System.out.println(pstmt);
			set = pstmt.executeQuery();
			VigilanceComplaint complaint = null;
			while (set.next()) {
				complaint = new VigilanceComplaint();
				complaint.setComplainBy(new VigilanceUser());

				complaint.setComplaintNo(set.getInt("COMPLAINT_NUMBER"));
				complaint.setComplaintAgainst(set
						.getString("COMPLAINT_AGAINST"));
				complaint.setDepartmetn(set.getString("DEPARTMENT"));
				complaint.setDesignation(set.getString("DESIGNATION"));
				complaint.setCountry(set.getString("COMPLAINT_COUNTRY"));
				complaint.setComplaintSubject(new String(Base64.decode(set
						.getString("COMPLAINT_SUBJECT"))));
				complaint.setComplaintDetail(new String(Base64.decode(set
						.getString("COMPLAINT_DETAIL"))));
				complaint.setComplaintDate(set.getDate("COMPLAINT_DATE"));
				complaint.setPincode(set.getString("PINCODE"));
				complaint.setIpAddress(set.getString("IP_ADDRESS"));
				complaint.setWorkCentre(set.getString("WORK_CENTRE"));
				complaint.getComplainBy().setTitle(
						(set.getString("TITLE").equals("-1")) ? "" : set
								.getString("TITLE"));
				complaint.getComplainBy().setFirstName(
						set.getString("FIRST_NAME"));
				complaint.getComplainBy().setMiddleName(
						set.getString("MIDDLE_NAME"));
				complaint.getComplainBy().setLastName(
						set.getString("LAST_NAME"));
				complaint.getComplainBy().setEmailId(set.getString("EMAIL_ID"));
				complaint.getComplainBy().setMobile(set.getString("MOBILE"));
				complaint.setComplaintActNo(set.getString("COMPLAINT_NO"));

				complaint.setStatus(set.getString("STATUS"));
				complaint.setAction(set.getString("ACTION"));
				complaint.setRemarks(set.getString("REMARKS"));
				complaint.setVerifiedDate(set.getDate("VERIFIED_DATE"));
				complaint.setSapNo(set.getString("SAPNO"));
				complaint.setAttachmentList(getAttachmentByComplaintId(set
						.getInt("COMPLAINT_NUMBER")));
				complaint.setOtherStatus(set.getString("OTHERSTATUS"));
				complaintList.add(complaint);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection,pstmt ,set);
		}
		return complaintList;
	}

	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;

		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DatasourceConnection.closeConnection(rs, stmt, connection);
		}

		return ++id;
	}

}
