package com.ongc.liferay.sponsorship.dao.Impl;
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


import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.sponsorship.command.CreateUserRenderCommand;
import com.ongc.liferay.sponsorship.connection.DatasourceConnection;
import com.ongc.liferay.sponsorship.dao.SponsorshipDao;
import com.ongc.liferay.sponsorship.model.FilterBean;
import com.ongc.liferay.sponsorship.model.SponStatus;
import com.ongc.liferay.sponsorship.model.SponsorshipBean;
import com.ongc.liferay.sponsorship.model.SubjectBean;
/**
 * @author Ranjeet
 */
public class SponsorshipDaoImpl implements SponsorshipDao {

	private static final Log LOGGER = LogFactoryUtil.getLog(SponsorshipDaoImpl.class);
	public static SponsorshipDaoImpl instance;

	public static SponsorshipDaoImpl getInstance() {
		instance = new SponsorshipDaoImpl();
		return instance;
	}

	public List<SubjectBean> getSubjectList() {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		String query = "SELECT SUBID , SUBNAME FROM SUBJECT_MASTER ORDER BY SUBNAME";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SubjectBean subject;
			while (rs.next()) {
				subject = new SubjectBean();
				subject.setSubId(rs.getInt("SUBID"));
				subject.setSubName(rs.getString("SUBNAME"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	public String getSubjectName(String subid) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String subname = null;
		String query = "SELECT SUBNAME FROM SUBJECT_MASTER where SUBID= ?";
		int subjectid = Integer.parseInt(subid);
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				subname = rs.getString("SUBNAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		return subname;
	}

	public List<SponStatus> getProposalStatus() {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		List<SponStatus> list = new ArrayList<SponStatus>();
		String query = "SELECT * FROM STATUS_MASTER";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SponStatus sponstatus;
			while (rs.next()) {
				sponstatus = new SponStatus();
				sponstatus.setStatid(rs.getInt("STATID"));
				sponstatus.setStatus(rs.getString("STATUS"));
				list.add(sponstatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	public String getSponsorshipStatusName(int statid) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String status = "";
		String query = "select status from status_master where statid=?";

		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, statid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				status = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}

		return status;
	}

	public String insertSponDetails(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String rtFlag="FAILED";
		String refNo = "REF";
		String insertQuery = "INSERT INTO SPON_DETAILS (SPONID,REFNO,EMAILID,PUBLICATIONNAME,RECOMMENDEDBY,SUBJECT,"
				+ "PROPAMT,OFFEREDVAL,DELIVERABLE,STATUS,PURPOSE,EXPENDITURE,OTHERSTATUS,APPROVALAUTH,RO_NUMBER,"
				+ "RO_DATE,EVENTDATE,RECEIVEDON,FILENUMBER,FRNUMBER,TRACKING_NUMBER,"
				+ "MOBILE_NO,INTERNAL_RECOMENDATION,PROPOSAL_FILENAME,PROPOSAL_CONTENT_TYPE,"
				+ "RELEASE_ORDER_FILENAME,RELEASE_ORDER_CONTENT_TYPE,CREATED_BY,SPONTYPE,SPONTYPEOTHER,DEALING_OFFICER,UPDATEDON,FLAG,CREATEDON)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,cast(? as timestamp),?,cast(? as timestamp),?,?,?,?,?,?,?,?,?,?,?,?,?,now(),'A',now())";
		int i = 0;
		int sponid = getMaxIdFromTable("SPON_DETAILS", "SPONID");
		refNo = refNo + String.valueOf(sponid);
		
		
		
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, sponid);
			pstmt.setString(2, refNo);
			pstmt.setString(3, sponBean.getEmailid());
			pstmt.setString(4, sponBean.getPublicationname());
			pstmt.setString(5, sponBean.getRecommendedby());
			pstmt.setString(6, sponBean.getSubject());
			pstmt.setString(7, sponBean.getProposalval());
			// pstmt.setString(8, sponBean.getOfferedval());
			pstmt.setString(8, "");
			pstmt.setString(9, sponBean.getDeliverable());
			pstmt.setString(10, sponBean.getStatus());
			pstmt.setString(11, sponBean.getPurpose());
			// pstmt.setString(12, sponBean.getExpenditure());
			pstmt.setString(12, "");
			pstmt.setString(13, sponBean.getOtherStatus());
			pstmt.setString(14, sponBean.getApprovalAuth());
			pstmt.setString(15, sponBean.getRonum());
			// Date todate=stm.parse(sponBean.getRodt());
			SimpleDateFormat stm = new SimpleDateFormat("dd-MMM-yyyy");
			// SimpleDateFormat stm1 = new SimpleDateFormat("dd-MM-yyyy");
			if (sponBean.getRodt() != null && sponBean.getRodt().length() > 0) {
				//Date rodate = stm.parse(sponBean.getRodt());
				pstmt.setString(16, sponBean.getRodt());
				
			} else {
				pstmt.setTimestamp(16, null);
			}
			if (sponBean.getEventDate() != null && sponBean.getEventDate().length() > 0) {
//				Date eventdate = stm.parse(sponBean.getEventDate());
				pstmt.setTimestamp(17,null);
			
			} else {
				pstmt.setTimestamp(17, null);
			}

//			Date receivedonDate = stm.parse(sponBean.getReceivedDate());
			if (sponBean.getReceivedDate() != null && sponBean.getReceivedDate().length() > 0) {
				//Date receivedDate = stm.parse(sponBean.getReceivedDate());
				pstmt.setString(18,sponBean.getReceivedDate());
			
			} else {
				pstmt.setTimestamp(18, null);
			}
			
			pstmt.setString(19, sponBean.getFilenumber());
			
			// add new field
			pstmt.setString(20, sponBean.getFrNumber());
			
			pstmt.setString(21, sponBean.getTrackingNumber());
			pstmt.setString(22, sponBean.getMobileNo());
			pstmt.setString(23, sponBean.getInternalRecomendation());
			pstmt.setString(24, sponBean.getProposalDocFileName());
			pstmt.setString(25, sponBean.getProposalDocContentType());
			pstmt.setString(26, sponBean.getReleaseOrderDocFileName());
			pstmt.setString(27, sponBean.getReleaseOrderDocContentType());
			pstmt.setString(28, sponBean.getCreatedBy());
			pstmt.setString(29,sponBean.getSponsType());
			pstmt.setString(30,sponBean.getSponsTypeOther());
			pstmt.setString(31,sponBean.getDealingOfficer());
			LOGGER.info(pstmt);
			i = pstmt.executeUpdate();
			if (i > 0) {
				rtFlag=refNo;
				String subject = "Reference No: " + refNo;
				String subname = this.getSubjectName(sponBean.getSubject());
				String mailbody = "Dear Representative,<br/><br/>We have received your proposal for '<strong>"
						+ subname
						+ "</strong>'.<br/><br/>The reference number for your application is <strong> "
						+ refNo
						+ "</strong>. You can track the status of your proposal in \"<strong> <a href=\"https://sponsorship.ongc.co.in\" >Track status</a></strong>\" by using this reference code .<br/><br/>Regards<br/>ONGC CC Team";
				// Temporary Stop as client requirement 12/11/2018
				 sendEmail(subject, mailbody,sponBean.getEmailid());
			}
		} /*catch (SQLException e) {
			log.info("Error in insertSponDetails" + e.getMessage());
		}*/ catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn );
		}
		
		return refNo;
	}

	public boolean checkSponsorship(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String query = "SELECT * FROM SPON_DETAILS where SUBJECT = ?  AND lower(PUBLICATIONNAME) = lower(?) ";
		StringBuffer sql = new StringBuffer(query);

		if (sponBean.getEventDate() != null && !sponBean.getEventDate().trim().equalsIgnoreCase("")) {
			sql.append("AND EVENTDATE= to_timestamp(?, 'DD-MON-YYYY')");
		}
		sql.append(" and flag =?");
		boolean check = false;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sponBean.getSubject().trim());
			pstmt.setString(2, sponBean.getPublicationname().trim());
			if (sponBean.getEventDate() != null && !sponBean.getEventDate().trim().equalsIgnoreCase("")) {
				pstmt.setString(3, sponBean.getEventDate());
				pstmt.setString(4, "A");
			} else {
				pstmt.setString(3, "A");
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return check;
	}

	
	public float checkSponsorshipLimit(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String query = "SELECT PROPAMT FROM DB2INST1.SPON_DETAILS where lower(APPROVALAUTH) = lower('ED-Chief CC') " +
				" AND  createdon > to_timestamp('2019-10-02', 'YYYY-MM-DD') AND createdon < " +
				" to_timestamp('2020-04-01', 'YYYY-MM-DD') " +
				" AND flag ='A' order by createdon";
		StringBuffer sql = new StringBuffer(query);
		float totalamount = 0;
		float propAmount = 0.0F;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				propAmount = Float.valueOf(rs.getString("PROPAMT"));
				totalamount = totalamount + propAmount;
			}
			// totalamount=totalamount+Integer.valueOf(sponBean.getProposalval());
		} catch (Exception e) {

		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return totalamount;
	}

	public boolean checkUpdatedValue(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		SimpleDateFormat stm1 = new SimpleDateFormat("dd-MMM-yyyy");
		String query = "SELECT * FROM SPON_DETAILS  WHERE SPONID = ?";
		StringBuffer sql = new StringBuffer(query);

		boolean check = true;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sponBean.getSponid());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (sponBean.getEventDate()!=null && rs.getDate("EVENTDATE")!=null) {
					if(sponBean.getEventDate().equalsIgnoreCase(stm1.format(rs.getDate("EVENTDATE")))){
					check = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return check;
	}

	public SponsorshipBean selectSponsorshipById(int id) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String query = "SELECT * FROM SPON_DETAILS where SPONID = ?";
		StringBuffer sql = new StringBuffer(query);
		SponsorshipBean sbean = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sbean = new SponsorshipBean();
				sbean.setSponid(rs.getInt("SPONID"));
				sbean.setEmailid(rs.getString("EMAILID"));
				sbean.setRefno(rs.getString("REFNO"));
				sbean.setPublicationname(rs.getString("PUBLICATIONNAME"));
				sbean.setRecommendedby(rs.getString("RECOMMENDEDBY"));
				sbean.setSubject(rs.getString("SUBJECT"));
				sbean.setProposalval(rs.getString("PROPAMT"));
				sbean.setOfferedval(rs.getString("OFFEREDVAL"));
				sbean.setDeliverable(rs.getString("DELIVERABLE"));
				sbean.setStatus(rs.getString("STATUS"));
				sbean.setPurpose(rs.getString("PURPOSE"));
				sbean.setExpenditure(rs.getString("EXPENDITURE"));
				sbean.setOtherStatus(rs.getString("OTHERSTATUS"));
				sbean.setApprovalAuth(rs.getString("APPROVALAUTH"));
				sbean.setRonum(rs.getString("RO_NUMBER"));
				sbean.setFilenumber(rs.getString("FILENUMBER"));
				sbean.setFrNumber(rs.getString("FRNUMBER"));
				sbean.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
				sbean.setSponsType(rs.getString("SPONTYPE"));
				sbean.setSponsTypeOther(rs.getString("SPONTYPEOTHER")!=null?rs.getString("SPONTYPEOTHER"):"");
			//	SimpleDateFormat stm1 = new SimpleDateFormat("dd-MMM-yyyy");
			//	SimpleDateFormat stm1 = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat stm1 = new SimpleDateFormat("yyyy-MM-dd");
				if (rs.getDate("RO_DATE") != null) {
					sbean.setRodt(stm1.format(rs.getDate("RO_DATE")));
				} else {
					sbean.setRodt("");
				}
				if (rs.getDate("CREATEDON") != null) {
					sbean.setCreatedon(stm1.format(rs.getDate("CREATEDON")));
				} else {
					sbean.setCreatedon("");
				}
				if (rs.getDate("EVENTDATE") != null) {
					sbean.setEventDate(stm1.format(rs.getDate("EVENTDATE")));
				} else {
					sbean.setEventDate("");
				}

				if (rs.getDate("RECEIVEDON") != null) {
					sbean.setReceivedDate(stm1.format(rs.getDate("RECEIVEDON")));
				} else {
					sbean.setReceivedDate("");
				}
				sbean.setFrNumber(rs.getString("FRNUMBER")!=null?rs.getString("FRNUMBER"):"");
				sbean.setTrackingNumber(rs.getString("TRACKING_NUMBER")!=null?rs.getString("TRACKING_NUMBER"):"");
				sbean.setInternalRecomendation(rs.getString("INTERNAL_RECOMENDATION")!=null?rs.getString("INTERNAL_RECOMENDATION"):"");
				sbean.setMobileNo(rs.getString("MOBILE_NO")!=null?rs.getString("MOBILE_NO"):"");
				sbean.setProposalDocFileName(rs.getString("PROPOSAL_FILENAME")!=null?rs.getString("PROPOSAL_FILENAME"):"");
				sbean.setProposalDocContentType(rs.getString("PROPOSAL_CONTENT_TYPE")!=null?rs.getString("PROPOSAL_CONTENT_TYPE"):"");
				sbean.setReleaseOrderDocFileName(rs.getString("RELEASE_ORDER_FILENAME")!=null?rs.getString("RELEASE_ORDER_FILENAME"):"");
				sbean.setReleaseOrderDocContentType(rs.getString("RELEASE_ORDER_CONTENT_TYPE")!=null?rs.getString("RELEASE_ORDER_CONTENT_TYPE"):"");
				sbean.setCreatedBy(rs.getString("CREATED_BY")!=null?rs.getString("CREATED_BY"):"");
				sbean.setDealingOfficer(rs.getString("DEALING_OFFICER")!=null?rs.getString("DEALING_OFFICER"):"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return sbean;
	}

	public int updateSponsorshipDetails(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String query="";
		int i = 0;
		//List<SponsorshipBean> spList=getreleasedorderDate(sponBean.getSponid());
		//log.info("********555******"+sponBean.getStatus());
		/*if(sponBean.getStatus().equals("5")){
			log.info("********555******"+sponBean.getRonum());
		}else{
			
			log.info("********splist******"+spList.get(0));
			log.info("********splist******"+spList.get(1));
		}*/
		if(sponBean.getStatus().equals("5")){
			if(sponBean.getProposalDocFileName()!=null){
				 query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
						"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON= ?, OTHERSTATUS=? , " +
						"APPROVALAUTH=?, RO_NUMBER=?, RO_DATE=?, EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
						"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?,"+
						"MOBILE_NO=?, INTERNAL_RECOMENDATION=? ,DEALING_OFFICER=?,PROPOSAL_FILENAME=?, PROPOSAL_CONTENT_TYPE=?,UPDATED_BY=? "+
						"WHERE SPONID = ?";
				}
				if(sponBean.getReleaseOrderDocFileName()!=null){
				 query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
						"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON=?, OTHERSTATUS=? , " +
						"APPROVALAUTH=?, RO_NUMBER=?, RO_DATE=?, EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
						"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?," +
						"MOBILE_NO=?, INTERNAL_RECOMENDATION=?,DEALING_OFFICER=?,"+
						"RELEASE_ORDER_FILENAME=?, RELEASE_ORDER_CONTENT_TYPE=?,UPDATED_BY=?  " +
						"WHERE SPONID = ?";
				}
				if(sponBean.getReleaseOrderDocFileName()==null && sponBean.getProposalDocFileName()==null){
					query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
							"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON= ?, OTHERSTATUS=? , " +
							"APPROVALAUTH=?, RO_NUMBER=?, RO_DATE=?, EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
							"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?," +
							"MOBILE_NO=?, INTERNAL_RECOMENDATION=?,DEALING_OFFICER=?,"+
							"UPDATED_BY=?  " +
							"WHERE SPONID = ?";
				}
				
				String refNo = sponBean.getRefno();
				int sponid = sponBean.getSponid();
				// SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
				try {
					conn = DatasourceConnection.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, sponBean.getPurpose());
					pstmt.setString(2, sponBean.getEmailid());
					pstmt.setString(3, sponBean.getRecommendedby());
					pstmt.setString(4, sponBean.getDeliverable());
					pstmt.setString(5, sponBean.getProposalval());
					pstmt.setString(6, sponBean.getStatus());
					pstmt.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
					pstmt.setString(8, sponBean.getOtherStatus());
					pstmt.setString(9, sponBean.getApprovalAuth());
					pstmt.setString(10, sponBean.getRonum());


					SimpleDateFormat stm = new SimpleDateFormat("dd-MMM-yyyy");

					if (sponBean.getRodt() != null && sponBean.getRodt().length() > 0) {
						Date rodate = stm.parse(sponBean.getRodt());
						pstmt.setTimestamp(11, new java.sql.Timestamp(rodate.getTime()));
					} else {
						pstmt.setTimestamp(11, null);
					}
					if (sponBean.getEventDate() != null && sponBean.getEventDate().length() > 0) {
						//Date eventdate = stm.parse(sponBean.getEventDate());
						pstmt.setString(12,sponBean.getEventDate());
					} else {
						pstmt.setTimestamp(12, null);
					}
//					Date receivedonDate = stm.parse(sponBean.getReceivedDate());
					pstmt.setString(13,sponBean.getReceivedDate());
					pstmt.setString(14, sponBean.getPublicationname());
					pstmt.setString(15, sponBean.getFilenumber());
					pstmt.setString(16, sponBean.getFrNumber());
					pstmt.setString(17, sponBean.getTrackingNumber());
					pstmt.setString(18, sponBean.getSponsType());
					pstmt.setString(19, sponBean.getSponsTypeOther());			
					pstmt.setString(20, sponBean.getMobileNo());
					pstmt.setString(21, sponBean.getInternalRecomendation());
					pstmt.setString(22, sponBean.getDealingOfficer());
					
					if(sponBean.getProposalDocFileName()!=null){
						pstmt.setString(23, sponBean.getProposalDocFileName());
						pstmt.setString(24, sponBean.getProposalDocContentType());
						pstmt.setString(25, sponBean.getUpdatedBy());
						pstmt.setInt(26, sponBean.getSponid());
					}
					if(sponBean.getReleaseOrderDocFileName()!=null){
						pstmt.setString(23, sponBean.getReleaseOrderDocFileName());
						pstmt.setString(24, sponBean.getReleaseOrderDocContentType());
						pstmt.setString(25, sponBean.getUpdatedBy());
						pstmt.setInt(26, sponBean.getSponid());
					}
					if(sponBean.getReleaseOrderDocFileName()==null && sponBean.getProposalDocFileName()==null){
						pstmt.setString(23, sponBean.getUpdatedBy());
						pstmt.setInt(24, sponBean.getSponid());
					}
					System.out.println(pstmt);
					i = pstmt.executeUpdate();
				
					if (i > 0 && sponBean.getTrackingNumber().length()>0 ) {
//						sendEmail(getMailSubject(sponBean),getMailBody(sponBean),sponBean.getEmailid());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DatasourceConnection.closeConnection(pstmt, conn);
				}
	
		}else{
			if(sponBean.getProposalDocFileName()!=null){
				 query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
						"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON= ?, OTHERSTATUS=? , " +
						"APPROVALAUTH=?,RO_NUMBER=?,RO_DATE=cast(? as timestamp), EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
						"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?,"+
						"MOBILE_NO=?, INTERNAL_RECOMENDATION=? ,DEALING_OFFICER=?,PROPOSAL_FILENAME=?, PROPOSAL_CONTENT_TYPE=?,UPDATED_BY=? "+
						"WHERE SPONID = ?";
				}
				if(sponBean.getReleaseOrderDocFileName()!=null){
				 query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
						"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON= ?, OTHERSTATUS=? , " +
						"APPROVALAUTH=?,RO_NUMBER=?,RO_DATE=cast(? as timestamp), EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
						"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?," +
						"MOBILE_NO=?, INTERNAL_RECOMENDATION=?,DEALING_OFFICER=?,"+
						"RELEASE_ORDER_FILENAME=?, RELEASE_ORDER_CONTENT_TYPE=?,UPDATED_BY=?  " +
						"WHERE SPONID = ?";
				}
				if(sponBean.getReleaseOrderDocFileName()==null && sponBean.getProposalDocFileName()==null){
					query = "UPDATE SPON_DETAILS SET PURPOSE=?, EMAILID=?, RECOMMENDEDBY=?, " +
							"DELIVERABLE=?, PROPAMT=?, STATUS = ? , UPDATEDON= ?, OTHERSTATUS=? , " +
							"APPROVALAUTH=?,RO_NUMBER=?,RO_DATE=cast(? as timestamp), EVENTDATE=cast(? as timestamp), RECEIVEDON=cast(? as timestamp), PUBLICATIONNAME=?," +
							"FILENUMBER=?,FRNUMBER=?,TRACKING_NUMBER=?,SPONTYPE=?,SPONTYPEOTHER=?," +
							"MOBILE_NO=?, INTERNAL_RECOMENDATION=?,DEALING_OFFICER=?,"+
							"UPDATED_BY=?  " +
							"WHERE SPONID = ?";
				}
				
				String refNo = sponBean.getRefno();
				int sponid = sponBean.getSponid();
				// SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
				try {
					conn = DatasourceConnection.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, sponBean.getPurpose());
					pstmt.setString(2, sponBean.getEmailid());
					pstmt.setString(3, sponBean.getRecommendedby());
					pstmt.setString(4, sponBean.getDeliverable());
					pstmt.setString(5, sponBean.getProposalval());
					pstmt.setString(6, sponBean.getStatus());
					pstmt.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
					pstmt.setString(8, sponBean.getOtherStatus());
					pstmt.setString(9, sponBean.getApprovalAuth());
					pstmt.setString(10, sponBean.getRonum());


					SimpleDateFormat stm = new SimpleDateFormat("dd-MMM-yyyy");

					if (sponBean.getRodt() != null && sponBean.getRodt().length() > 0) {
						//Date rodate = stm.parse(sponBean.getRodt());
						pstmt.setString(11, sponBean.getRodt());
					} else {
						pstmt.setTimestamp(11, null);
					}
					if (sponBean.getEventDate() != null && sponBean.getEventDate().length() > 0) {
//						Date eventdate = stm.parse(sponBean.getEventDate());
						pstmt.setString(12,sponBean.getEventDate());
					} else {
						pstmt.setTimestamp(12, null);
					}
//					Date receivedonDate = stm.parse(sponBean.getReceivedDate());
					pstmt.setString(13,sponBean.getReceivedDate());
					pstmt.setString(14, sponBean.getPublicationname());
					pstmt.setString(15, sponBean.getFilenumber());
					pstmt.setString(16, sponBean.getFrNumber());
					pstmt.setString(17, sponBean.getTrackingNumber());
					pstmt.setString(18, sponBean.getSponsType());
					pstmt.setString(19, sponBean.getSponsTypeOther());			
					pstmt.setString(20, sponBean.getMobileNo());
					pstmt.setString(21, sponBean.getInternalRecomendation());
					pstmt.setString(22, sponBean.getDealingOfficer());
					
					if(sponBean.getProposalDocFileName()!=null){
						pstmt.setString(23, sponBean.getProposalDocFileName());
						pstmt.setString(24, sponBean.getProposalDocContentType());
						pstmt.setString(25, sponBean.getUpdatedBy());
						pstmt.setInt(26, sponBean.getSponid());
					}
					if(sponBean.getReleaseOrderDocFileName()!=null){
						pstmt.setString(23, sponBean.getReleaseOrderDocFileName());
						pstmt.setString(24, sponBean.getReleaseOrderDocContentType());
						pstmt.setString(25, sponBean.getUpdatedBy());
						pstmt.setInt(26, sponBean.getSponid());
					}
					if(sponBean.getReleaseOrderDocFileName()==null && sponBean.getProposalDocFileName()==null){
						pstmt.setString(23, sponBean.getUpdatedBy());
						pstmt.setInt(24, sponBean.getSponid());
					}
					i = pstmt.executeUpdate();
					if (i > 0 && sponBean.getTrackingNumber().length()>0 ) {
//						sendEmail(getMailSubject(sponBean),getMailBody(sponBean),sponBean.getEmailid());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DatasourceConnection.closeConnection(pstmt, conn);
				}
	
		}
				return i;
	}

	public int deleteSponsorshipDetails(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String query = "UPDATE SPON_DETAILS SET FLAG = 'D'  WHERE SPONID = ?";
		int i = 0;

		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			;
			pstmt.setInt(1, sponBean.getSponid());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return i;
	}
	
	/*****************************************************************************
	 * removeFile
	 *****************************************************************************/
	public int removeFile(SponsorshipBean sponBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int i = 0;
		String query ="";
		if("remove".equalsIgnoreCase(sponBean.getProposalDocFileName())){
			query = "UPDATE SPON_DETAILS SET PROPOSAL_FILENAME = '',PROPOSAL_CONTENT_TYPE=''  WHERE SPONID = ?";
		}else if("remove".equalsIgnoreCase(sponBean.getReleaseOrderDocFileName())){
			query = "UPDATE SPON_DETAILS SET RELEASE_ORDER_FILENAME = '',RELEASE_ORDER_CONTENT_TYPE=''  WHERE SPONID = ?";
		}
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sponBean.getSponid());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		
		return i;
	}


	private int getMaxIdFromTable(String tableName, String colName) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int id = 0;

		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		try {
			conn = DatasourceConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}

		return ++id;
	}

	public List<SponsorshipBean> displayAllSponship(FilterBean flterBean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SponsorshipBean> list = new ArrayList<SponsorshipBean>();
		// String
		// query="SELECT  SD.SPONID, SD.EMAILID,SD.REFNO,SD.PUBLICATIONNAME,SD.RECOMMENDEDBY,PN.PROPOSALNAME, SM.STATUS,SD.CREATEDON, SD.UPDATEDON,SD.PROPAMT FROM SPON_DETAILS SD,PROPOSAL_CATEGORY PN,STATUS_MASTER SM WHERE FLAG='A' AND SD.PROPOSALVAL=PN.PROPOSALID AND SD.STATUS=SM.STATID";

		String query = "SELECT  SD.SPONID, SD.EMAILID,SD.REFNO,SD.PUBLICATIONNAME,SD.RECOMMENDEDBY,PN.SUBNAME, SM.STATUS,SD.CREATEDON, SD.UPDATEDON,SD.PROPAMT,SD.OFFEREDVAL,SD.DELIVERABLE,SD.PURPOSE,SD.EXPENDITURE,SD.OTHERSTATUS,SD.APPROVALAUTH, SD.RO_NUMBER,SD.RO_DATE,SD.EVENTDATE,SD.RECEIVEDON, SD.FILENUMBER,SD.FRNUMBER,SD.TRACKING_NUMBER,SD.CREATED_BY,SD.DEALING_OFFICER FROM SPON_DETAILS SD,SUBJECT_MASTER PN,STATUS_MASTER SM WHERE FLAG='A' AND CAST(SD.SUBJECT as INTEGER)=PN.SUBID AND CAST(SD.STATUS as INTEGER)=SM.STATID";
		StringBuffer sql = new StringBuffer(query);
		if (flterBean != null && flterBean.getSubjectid() > 0) {
			sql.append(" and SD.SUBJECT='" + flterBean.getSubjectid() + "' ");
		}
		if (flterBean != null && !"".equalsIgnoreCase(flterBean.getPublicationname()) && flterBean.getPublicationname() != null) {
			sql.append(" and lower(SD.PUBLICATIONNAME) like lower('%"+ flterBean.getPublicationname() + "%')  ");
		}
		if (flterBean != null && !"".equalsIgnoreCase(flterBean.getRecommendedby()) && flterBean.getRecommendedby() != null) {
			sql.append(" and lower(SD.RECOMMENDEDBY) like lower('%" + flterBean.getRecommendedby() + "%')  ");
		}
		if (flterBean != null && !"".equalsIgnoreCase(flterBean.getPurpose()) && flterBean.getPurpose() != null) {
			sql.append(" and lower(SD.PURPOSE) like lower('%" + flterBean.getPurpose() + "%')  ");
		}
		if(flterBean != null && !Validator.isBlank(flterBean.getCreatedBy()) &&!"-1".equalsIgnoreCase(flterBean.getCreatedBy()) && flterBean.getCreatedBy()!=null){
			sql.append(" and lower(SD.CREATED_BY) like lower('%" + flterBean.getCreatedBy() + "%')  ");
		}
		if(flterBean != null && !"".equalsIgnoreCase(flterBean.getRefNo()) && flterBean.getRefNo()!=null){
			sql.append(" and lower(SD.REFNO) like lower('%" + flterBean.getRefNo() + "%')  ");
		}
		if(flterBean != null && !Validator.isBlank(flterBean.getStatus()) && !"-1".equalsIgnoreCase(flterBean.getStatus()) && flterBean.getStatus()!=null){
			sql.append(" and lower(SM.STATUS) like lower('%" + flterBean.getStatus() + "%')  ");
		}		
		
		sql.append(" order by SD.UPDATEDON desc  ");
		SponsorshipBean sbean = null;
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			LOGGER.info(sql.toString());
			rs = statement.executeQuery(sql.toString());

			while (rs.next()) {
				sbean = new SponsorshipBean();
				sbean.setSponid(rs.getInt("SPONID"));
				sbean.setEmailid(rs.getString("EMAILID"));
				sbean.setRefno(rs.getString("REFNO"));
				sbean.setPublicationname(rs.getString("PUBLICATIONNAME"));
				sbean.setRecommendedby(rs.getString("RECOMMENDEDBY"));
				sbean.setSubject(rs.getString("SUBNAME"));
				sbean.setStatus(rs.getString("STATUS"));
				sbean.setCreatedon(rs.getString("CREATEDON"));
				//sbean.setCreatedon(stm.format(rs.getTimestamp("CREATEDON")));
				sbean.setUpdatedon(rs.getString("UPDATEDON"));
				sbean.setProposalval(rs.getString("PROPAMT"));
				sbean.setOfferedval(rs.getString("OFFEREDVAL"));
				sbean.setDeliverable(rs.getString("DELIVERABLE"));
				sbean.setPurpose(rs.getString("PURPOSE"));
				sbean.setExpenditure(rs.getString("EXPENDITURE"));
				sbean.setOtherStatus(rs.getString("OTHERSTATUS"));
				sbean.setApprovalAuth(rs.getString("APPROVALAUTH"));
				sbean.setRonum(rs.getString("RO_NUMBER"));
				SimpleDateFormat stm1 = new SimpleDateFormat("dd-MMM-yyyy");

				sbean.setFrNumber(rs.getString("FRNUMBER") != null ? rs.getString("FRNUMBER") : "");
				sbean.setTrackingNumber(rs.getString("TRACKING_NUMBER") != null ? rs.getString("TRACKING_NUMBER") : "");

				if (rs.getDate("RO_DATE") != null) {
					sbean.setRodt(stm1.format(rs.getDate("RO_DATE")));
				} else {
					sbean.setRodt("");
				}
				// sbean.setRodt(rs.getString("RO_DATE"));
				// sbean.setEventDate(rs.getString("EVENTDATE"));
				if (rs.getDate("EVENTDATE") != null) {

					sbean.setEventDate(stm1.format(rs.getDate("EVENTDATE")));
				} else {
					sbean.setEventDate("");
				}

				if (rs.getDate("RECEIVEDON") != null) {

					sbean.setReceivedDate(stm1.format(rs.getDate("RECEIVEDON")));
				} else {
					sbean.setReceivedDate("");
				}
				sbean.setFilenumber(rs.getString("FILENUMBER"));
				sbean.setCreatedBy(rs.getString("CREATED_BY")!=null?rs.getString("CREATED_BY"):"");
				sbean.setDealingOfficer(rs.getString("DEALING_OFFICER")!=null?rs.getString("DEALING_OFFICER"):"");
				
				list.add(sbean);
			}
			LOGGER.info(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;

	}

	public List<SponsorshipBean> sponsorshipByRef(SponsorshipBean sbean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SponsorshipBean> list = new ArrayList<SponsorshipBean>();
		// String
		// query="SELECT  SD.SPONID, SD.EMAILID,SD.REFNO,SD.PUBLICATIONNAME,SD.RECOMMENDEDBY,PN.PROPOSALNAME, SM.STATUS,SD.CREATEDON, SD.UPDATEDON,SD.PROPAMT FROM SPON_DETAILS SD,PROPOSAL_CATEGORY PN,STATUS_MASTER SM WHERE FLAG='A' AND SD.PROPOSALVAL=PN.PROPOSALID AND SD.STATUS=SM.STATID";

		String query = "SELECT  SD.SPONID, SD.EMAILID,SD.REFNO,SD.PUBLICATIONNAME,SD.RECOMMENDEDBY,PN.SUBNAME, SM.STATUS,SD.CREATEDON, SD.UPDATEDON,SD.PROPAMT,SD.OFFEREDVAL,SD.DELIVERABLE,SD.PURPOSE,SD.EXPENDITURE,SD.OTHERSTATUS,SD.APPROVALAUTH,SD.RO_NUMBER,SD.RO_DATE,SD.EVENTDATE,SD.RECEIVEDON, SD.FILENUMBER,SD.FRNUMBER,SD.TRACKING_NUMBER,SD.CREATED_BY,SD.DEALING_OFFICER FROM SPON_DETAILS SD,SUBJECT_MASTER PN,STATUS_MASTER SM WHERE FLAG='A' AND SD.SUBJECT=PN.SUBID AND SD.STATUS=SM.STATID";
		StringBuffer sql = new StringBuffer(query);

		sql.append(" and lower(SD.REFNO)= lower('" + sbean.getRefno() + "') and lower(SD.EMAILID)= lower('" + sbean.getEmailid()+ "') ");

		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());

			while (rs.next()) {
				sbean = new SponsorshipBean();
				sbean.setSponid(rs.getInt("SPONID"));
				sbean.setEmailid(rs.getString("EMAILID"));
				sbean.setRefno(rs.getString("REFNO"));
				sbean.setPublicationname(rs.getString("PUBLICATIONNAME"));
				sbean.setRecommendedby(rs.getString("RECOMMENDEDBY"));
				sbean.setSubject(rs.getString("SUBNAME"));
				sbean.setStatus(rs.getString("STATUS"));
				sbean.setCreatedon(stm.format(rs.getTimestamp("CREATEDON")));
				sbean.setUpdatedon(stm.format(rs.getTimestamp("UPDATEDON")));
				sbean.setProposalval(rs.getString("PROPAMT"));
				sbean.setOfferedval(rs.getString("OFFEREDVAL"));
				sbean.setDeliverable(rs.getString("DELIVERABLE"));
				sbean.setPurpose(rs.getString("PURPOSE"));
				sbean.setExpenditure(rs.getString("EXPENDITURE"));
				sbean.setOtherStatus(rs.getString("OTHERSTATUS"));
				sbean.setApprovalAuth(rs.getString("APPROVALAUTH"));
				sbean.setRonum(rs.getString("RO_NUMBER"));
				sbean.setFilenumber(rs.getString("FILENUMBER"));
				sbean.setFrNumber(rs.getString("FRNUMBER") != null ? rs.getString("FRNUMBER") : "");
				sbean.setTrackingNumber(rs.getString("TRACKING_NUMBER") != null ? rs.getString("TRACKING_NUMBER") : "");

				SimpleDateFormat stm1 = new SimpleDateFormat("dd-MMM-yyyy");
				// SimpleDateFormat stm2 = new SimpleDateFormat("dd-MMM-yyyy");
				if (rs.getDate("RO_DATE") != null) {

					sbean.setRodt(stm1.format(rs.getDate("RO_DATE")));
				} else {
					sbean.setRodt("");
				}

				if (rs.getDate("EVENTDATE") != null) {

					sbean.setEventDate(stm1.format(rs.getDate("EVENTDATE")));
				} else {
					sbean.setEventDate("");
				}
				if (rs.getDate("RECEIVEDON") != null) {

					sbean.setReceivedDate(stm1.format(rs.getDate("RECEIVEDON")));
				} else {
					sbean.setReceivedDate("");
				}
				sbean.setCreatedBy(rs.getString("CREATED_BY"));
				sbean.setDealingOfficer(rs.getString("DEALING_OFFICER")!=null?rs.getString("DEALING_OFFICER"):"");
				list.add(sbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;

	}

	public SponsorshipBean viewSponship(SponsorshipBean sbean) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SponsorshipBean> list = new ArrayList<SponsorshipBean>();

		String query = "SELECT  SD.SPONID, SD.EMAILID,SD.REFNO,SD.PUBLICATIONNAME,SD.RECOMMENDEDBY,PN.SUBNAME, " +
				"SM.STATUS,SD.CREATEDON, SD.UPDATEDON,SD.PROPAMT,SD.OFFEREDVAL,SD.DELIVERABLE,SD.PURPOSE," +
				"SD.EXPENDITURE,SD.OTHERSTATUS,SD.RO_DATE,SD.EVENTDATE,SD.RECEIVEDON, SD.FILENUMBER," +
				"SD.FRNUMBER,SD.TRACKING_NUMBER,SD.INTERNAL_RECOMENDATION,SD.MOBILE_NO,SD.PROPOSAL_FILENAME,SD.PROPOSAL_CONTENT_TYPE," +
				"SD.RELEASE_ORDER_FILENAME,SD.RELEASE_ORDER_CONTENT_TYPE,SD.CREATED_BY,SD.DEALING_OFFICER " +
				" FROM SPON_DETAILS SD,SUBJECT_MASTER PN,STATUS_MASTER SM " +
				" WHERE FLAG='A' AND SD.SUBJECT=PN.SUBID AND SD.STATUS=SM.STATID";
		StringBuffer sql = new StringBuffer(query);

		
		
		sql.append(" and lower(SD.SPONID)= lower('" + sbean.getSponid() + "') ");
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());
			

			while (rs.next()) {
				sbean = new SponsorshipBean();
				
				sbean.setSponid(rs.getInt("SPONID"));
				sbean.setEmailid(rs.getString("EMAILID"));
				sbean.setRefno(rs.getString("REFNO"));
				sbean.setPublicationname(rs.getString("PUBLICATIONNAME"));
				sbean.setRecommendedby(rs.getString("RECOMMENDEDBY"));
				sbean.setSubject(rs.getString("SUBNAME"));
				if(rs.getString("STATUS").equals("Others")){
				
					if(rs.getString("OTHERSTATUS") !=null){
						sbean.setStatus("Other_"+rs.getString("OTHERSTATUS"));	
					}else{
						sbean.setStatus("Other_");
					}
					
					
				}else{
					sbean.setStatus(rs.getString("STATUS"));	
					
				}
				
				
				sbean.setCreatedon(stm.format(rs.getTimestamp("CREATEDON")));
				sbean.setUpdatedon(stm.format(rs.getTimestamp("UPDATEDON")));
				sbean.setProposalval(rs.getString("PROPAMT"));
				sbean.setOfferedval(rs.getString("OFFEREDVAL"));
				sbean.setDeliverable(rs.getString("DELIVERABLE"));
				sbean.setPurpose(rs.getString("PURPOSE"));
				sbean.setExpenditure(rs.getString("EXPENDITURE"));
				sbean.setOtherStatus(rs.getString("OTHERSTATUS"));
				sbean.setFilenumber(rs.getString("FILENUMBER")!=null?rs.getString("FILENUMBER"):"");
				SimpleDateFormat stm1 = new SimpleDateFormat("dd-MMM-yyyy");
				// SimpleDateFormat stm2 = new SimpleDateFormat("dd-MMM-yyyy");
				if (rs.getDate("RO_DATE") != null) {
					sbean.setRodt(stm1.format(rs.getDate("RO_DATE")));
				} else {
					sbean.setRodt("");
				}
				if (rs.getDate("EVENTDATE") != null) {
					sbean.setEventDate(stm1.format(rs.getDate("EVENTDATE")));
				} else {
					sbean.setEventDate("");
				}
				if (rs.getDate("RECEIVEDON") != null) {
					sbean.setReceivedDate(stm1.format(rs.getDate("RECEIVEDON")));
				} else {
					sbean.setReceivedDate("");
				}
				sbean.setFrNumber(rs.getString("FRNUMBER")!=null?rs.getString("FRNUMBER"):"");
				sbean.setTrackingNumber(rs.getString("TRACKING_NUMBER")!=null?rs.getString("TRACKING_NUMBER"):"");
				sbean.setInternalRecomendation(rs.getString("INTERNAL_RECOMENDATION")!=null?rs.getString("INTERNAL_RECOMENDATION"):"");
				sbean.setMobileNo(rs.getString("MOBILE_NO")!=null?rs.getString("MOBILE_NO"):"");
				sbean.setProposalDocFileName(rs.getString("PROPOSAL_FILENAME")!=null?rs.getString("PROPOSAL_FILENAME"):"");
				sbean.setProposalDocContentType(rs.getString("PROPOSAL_CONTENT_TYPE")!=null?rs.getString("PROPOSAL_CONTENT_TYPE"):"");
				sbean.setReleaseOrderDocFileName(rs.getString("RELEASE_ORDER_FILENAME")!=null?rs.getString("RELEASE_ORDER_FILENAME"):"");
				sbean.setReleaseOrderDocContentType(rs.getString("RELEASE_ORDER_CONTENT_TYPE")!=null?rs.getString("RELEASE_ORDER_CONTENT_TYPE"):"");
				sbean.setCreatedBy(rs.getString("CREATED_BY")!=null?rs.getString("CREATED_BY"):"");
				sbean.setDealingOfficer(rs.getString("DEALING_OFFICER")!=null?rs.getString("DEALING_OFFICER"):"");
				
			}
		} catch (SQLException e) {
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return sbean;

	}

	public boolean sendEmail(final String mailSubject, final String msg,
			String sToEmailID) {
		try {
			// String SMTP_HOST="10.205.48.55";
			String SMTP_HOST = ResourceBundle.getBundle("/content/sponsorship").getString("SMTP_HOST");
			final String username=ResourceBundle.getBundle("/content/sponsorship").getString("Username").toString().trim();
			final String password=ResourceBundle.getBundle("/content/sponsorship").getString("Password").toString().trim();
			
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String sFinalMsg = "";

			String MailFrom = "noreply@ongc.co.in";
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
			
				/*String[] to = {"s.singh@velocis.co.in","awal_hk@ongc.co.in","mukherjee_d@ongc.co.in","ratan_Shrey@ongc.co.in"};
				javax.mail.internet.InternetAddress[] toAddress = new javax.mail.internet.InternetAddress[to.length];
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new javax.mail.internet.InternetAddress(to[i]);
	            }
	           message.addRecipients(javax.mail.Message.RecipientType.TO, toAddress);*/
	            
				message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				
				message.setSubject(mailSubject);
				// message.setText(Msg);
				htmlPart.setDataHandler(new DataHandler(msg, "text/html"));
				multipart.addBodyPart(htmlPart);
				// message.setContent(multipart);
				message.setContent(multipart);
				message.setSentDate(new Date());
				// Temporary Stop as client requirement 12/11/2018
				 javax.mail.Transport.send(message);

			}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public String getMailSubject(SponsorshipBean  sponBean){
		String subname = this.getSubjectName(sponBean.getSubject());
		String subject = "Reference No: " + sponBean.getRefno();
		return subject;
	}
	public String getMailBody(SponsorshipBean  sponBean){
		StringBuilder mailbody =new StringBuilder();
		mailbody.append("Dear Beneficiary,<br/><br/>");
		mailbody.append("The Tracking Number generated for your invoice is <strong>"+sponBean.getTrackingNumber()+"</strong><br/><br/>");
		mailbody.append("You can track the processing status of your invoice by typing in this 15-digit Tracking Number on webice.ongc.co.in/ims#.<br/><br/>");
		mailbody.append("Regards<br/><br/>ONGC CC Team");
		
		
		
		return mailbody.toString();
	}
	
	public List<SubjectBean> getRoNo() {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		String query = "SELECT RO_NUMBER,RO_DATE FROM SPON_DETAILS where refno='REF51'";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SubjectBean subject;
			while (rs.next()) {
				subject = new SubjectBean();
				subject.setSubId(rs.getInt("SUBID"));
				subject.setSubName(rs.getString("SUBNAME"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}
	
	public List<SponsorshipBean> getreleasedorderDate(int refNo) {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SponsorshipBean> list = new ArrayList<SponsorshipBean>();
		String query = "SELECT RO_NUMBER,EVENTDATE FROM SPON_DETAILS where sponid="+refNo+" ";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SponsorshipBean sponsorshipBean;
			while (rs.next()) {
				sponsorshipBean = new SponsorshipBean();
				sponsorshipBean.setRonum(rs.getString("RO_NUMBER"));
				sponsorshipBean.setEventDate(rs.getString("EVENTDATE"));
				list.add(sponsorshipBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}
	
	public List<SubjectBean> getLocation() {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		String query = "SELECT DISTINCT CREATED_BY FROM SPON_DETAILS WHERE CREATED_BY IS NOT NULL ";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			SubjectBean subject;
			while (rs.next()) {
				subject = new SubjectBean();
				subject.setLocation(rs.getString("CREATED_BY"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}
	
	public List<FilterBean> getStatusList() {
		Connection conn=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<FilterBean> list = new ArrayList<FilterBean>();
		String query = "SELECT DISTINCT B.STATUS FROM SPON_DETAILS A INNER JOIN STATUS_MASTER B ON B.STATID::varchar=A.STATUS";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			FilterBean subject;
			while (rs.next()) {
				subject = new FilterBean();
				subject.setStatus(rs.getString("STATUS"));
				//log.info("Status>>>>>>"+rs.getString("STATUS"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}
}
