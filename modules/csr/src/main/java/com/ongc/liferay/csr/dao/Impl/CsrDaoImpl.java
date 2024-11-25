package com.ongc.liferay.csr.dao.Impl;

import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.csr.connection.DatasourceConnection;
import com.ongc.liferay.csr.dao.CsrDao;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.model.FilterBean;

import java.text.ParseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Validation;
/**
 * @author Ranjeet
 */

public class CsrDaoImpl implements CsrDao{
	
	@Override
	public List<CSRProgramModel> getAll(FilterBean flterBean) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<CSRProgramModel> list = new ArrayList<CSRProgramModel>();
		CSRProgramModel m=null;
		String query=szResBundl.getString("GETALLCSRDTAIL");
		StringBuffer sql = new StringBuffer(query);
		if (flterBean != null && flterBean.getCreatedBy()!=null && flterBean.getCreatedBy()!="" && !"".equals(flterBean.getCreatedBy())) {
			sql.append(" and C.CREATED_BY='" + flterBean.getCreatedBy() + "' ");
		}
		if (flterBean != null && flterBean.getProgramName()!=null && flterBean.getProgramName()!="" && !"".equals(flterBean.getProgramName())) {
			sql.append(" and C.PROGRAM_NAME like ('%" + flterBean.getProgramName() + "%') ");
		}
		if (flterBean != null && flterBean.getRefNo()!=null && flterBean.getRefNo()!="" && !"".equals(flterBean.getRefNo())) {
			sql.append(" and C.REF_NO='" + flterBean.getRefNo() + "' ");
		}
		if (flterBean != null && flterBean.getSubject() !=null && !Validator.isBlank(flterBean.getSubject()) && !"-1".equals(flterBean.getSubject())) {
			sql.append(" and C.SUBJECT ='" + flterBean.getSubject() + "'  ");
		}
		if (flterBean != null && flterBean.getStatus() !=null && !Validator.isBlank(flterBean.getStatus()) && !"-1".equals(flterBean.getStatus())) {
			sql.append(" and C.STATUS='" + flterBean.getStatus() + "' ");
		}
		sql.append("ORDER BY C.CREATED_ON DESC");
		try{
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			
			rs = statement.executeQuery(sql.toString());
			while (rs.next()) {
				m= new CSRProgramModel();
				m.setId(rs.getInt("CSR_ID"));
				m.setSubject(rs.getString("SUBJECT"));
				m.setActivityNature(rs.getString("ACTIVITY_NATURE"));
				m.setApplicableGst(rs.getString("APPLICABLE_GST"));
				m.setApprovingAuthorityAsPerBDM(rs.getString("APPROVING_ATHORITY_PER_BDM"));
				m.setContactPerson(rs.getString("CONTACT_PERSION"));				
				m.setDesignarionInAgency(rs.getString("DESIGNATION_IN_AGENCY"));
				m.setExemption80G(rs.getString("IT_EXEMPTION_80G"));
				m.setExemption12AA(rs.getString("IT_EXEMPTION_12AA"));
				m.setFundingFrmOngc(rs.getString("FUNDING_FROM_ONGC"));
				m.setMobileNo(rs.getString("MOBILE_NO"));
				m.setImplementingAgencyName(rs.getString("IMPLEMENTING_AGENCY_NAME"));
				m.setLegalStatus(rs.getString("LEGAL_STATUS"));																				
				m.setProgramCost(rs.getString("PROGRAM_COST"));
				m.setProgramName(rs.getString("PROGRAM_NAME"));
				m.setProposalReciptDate(rs.getString("PROPOSAL_RECEIPT_DT"));
				m.setProgramDate(rs.getString("PROGRAM_DATE"));
				m.setRefNo(rs.getString("REF_NO"));				
				m.setCreatedBy(rs.getString("CREATED_BY"));
				m.setCreatedOn(rs.getString("CREATED_ON"));				
				m.setStatus(rs.getString("STATUS"));	
				m.setStatusName(rs.getString("STATUS_NAME"));
				m.setProgramNatureName(rs.getString("PRN_NAME"));
				m.setSubjectName(rs.getString("SUBNAME"));			
				m.setActive(Integer.parseInt(rs.getString("ACTIVE")));
				m.setApprovingAuthorityAsPerBDMCode(rs.getString("AUTH_CODE"));
				m.setApprovingAuthorityAsPerBDMName(rs.getString("AUTH_NAME"));

				m.setDishaFileNumber(rs.getString("DISHA_FILENUMBER"));
				m.setFrNumber(rs.getString("FRNUMBER"));
				m.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
				m.setProposalDocFileName(rs.getString("PROPOSAL_FILENAME"));
				m.setReleaseOrderDocFileName(rs.getString("RELEASE_ORDER_FILENAME"));
				m.setFilecurrentlywith(rs.getString("FILECURRENTLYWITH"));
				m.setProgramDateTo(rs.getString("PROGRAM_DATE_TO"));
				m.setNameofimpelementingajency(rs.getString("NAME_OF_IMPLEMENTING_AJENCY"));
				m.setCompany(rs.getString("COMPANY"));				
				m.setNgo(rs.getString("NGO"));
				m.setTrust(rs.getString("TRUST"));
				m.setSection8(rs.getString("SECTION8"));
				m.setReceiptsDocFileName(rs.getString("RCPT_FILENAME"));
				m.setReceiptsDocContentType(rs.getString("RCPT_CONTENT_TYPE"));
				m.setEightyGDocContentType(rs.getString("EXM_80G_CONTENT_TYPE"));
				m.setEightyGDocFileName(rs.getString("EXC_80G_FILENAME"));
				m.setTwelveAADocFileName(rs.getString("EXM_12AA_FILENAME"));
				m.setTwelveAADocContentType(rs.getString("EXM_12AA_CONTENT_TYPE"));

				list.add(m);
			}



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement,rs);
		}
		return list;

	}


	public CSRProgramModel findByByRefNo(String refNo) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		CSRProgramModel m=null;
		String sql=szResBundl.getString("FINDCSRBYREFNO");
		
		List<CSRProgramModel> list = new ArrayList<CSRProgramModel>();
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");


		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, refNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m = new CSRProgramModel();
				m.setId(rs.getInt("CSR_ID"));
				m.setSubject(rs.getString("SUBJECT"));
				m.setActivityNature(rs.getString("ACTIVITY_NATURE"));
				m.setApplicableGst(rs.getString("APPLICABLE_GST"));
				m.setApprovingAuthorityAsPerBDM(rs.getString("APPROVING_ATHORITY_PER_BDM"));
				m.setContactPerson(rs.getString("CONTACT_PERSION"));				
				m.setDesignarionInAgency(rs.getString("DESIGNATION_IN_AGENCY"));
				m.setExemption80G(rs.getString("IT_EXEMPTION_80G"));
				m.setFundingFrmOngc(rs.getString("FUNDING_FROM_ONGC"));
				m.setMobileNo(rs.getString("MOBILE_NO"));
				m.setImplementingAgencyName(rs.getString("IMPLEMENTING_AGENCY_NAME"));
				m.setLegalStatus(rs.getString("LEGAL_STATUS"));																				
				m.setProgramCost(rs.getString("PROGRAM_COST"));
				m.setProgramName(rs.getString("PROGRAM_NAME"));
				m.setProposalReciptDate(rs.getString("PROPOSAL_RECEIPT_DT"));
				m.setProgramDate(rs.getString("PROGRAM_DATE"));
				m.setRefNo(rs.getString("REF_NO"));				
				m.setCreatedBy(rs.getString("CREATED_BY"));
				m.setCreatedOn(rs.getString("CREATED_ON"));				
				m.setStatus(rs.getString("STATUS"));	
				m.setStatusName(rs.getString("STATUS_NAME"));
				m.setProgramNatureName(rs.getString("PRN_NAME"));
				m.setSubjectName(rs.getString("SUBNAME"));
				m.setActive(Integer.parseInt(rs.getString("ACTIVE")));			
				m.setApprovingAuthorityAsPerBDMCode(rs.getString("AUTH_CODE"));
				m.setApprovingAuthorityAsPerBDMName(rs.getString("AUTH_NAME"));
				m.setFilecurrentlywith(rs.getString("FILECURRENTLYWITH"));
				m.setProgramDateTo(rs.getString("PROGRAM_DATE_TO"));
				m.setNameofimpelementingajency(rs.getString("NAME_OF_IMPLEMENTING_AJENCY"));
				m.setCompany(rs.getString("COMPANY"));				
				m.setNgo(rs.getString("NGO"));
				m.setTrust(rs.getString("TRUST"));
				m.setSection8(rs.getString("SECTION8"));
				m.setReceiptsDocFileName(rs.getString("RCPT_FILENAME"));
				m.setReceiptsDocContentType(rs.getString("RCPT_CONTENT_TYPE"));
				m.setEightyGDocContentType(rs.getString("EXM_80G_CONTENT_TYPE"));
				m.setEightyGDocFileName(rs.getString("EXC_80G_FILENAME"));
				m.setTwelveAADocFileName(rs.getString("EXM_12AA_FILENAME"));
				m.setTwelveAADocContentType(rs.getString("EXM_12AA_CONTENT_TYPE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return m;

	}
	public CSRProgramModel findById(int id) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		CSRProgramModel m=null;
		String sql=szResBundl.getString("FINDCSRBYID");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");

		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m = new CSRProgramModel();
				m.setId(rs.getInt("CSR_ID"));
				m.setRefNo(rs.getString("REF_NO"));		
				m.setSubject(rs.getString("SUBJECT"));
				m.setActivityNature(rs.getString("ACTIVITY_NATURE"));
				m.setApplicableGst(rs.getString("APPLICABLE_GST"));
				m.setApprovingAuthorityAsPerBDM(rs.getString("APPROVING_ATHORITY_PER_BDM"));
				m.setContactPerson(rs.getString("CONTACT_PERSION"));				
				m.setDesignarionInAgency(rs.getString("DESIGNATION_IN_AGENCY"));
				m.setExemption80G(rs.getString("IT_EXEMPTION_80G"));
				m.setFundingFrmOngc(rs.getString("FUNDING_FROM_ONGC"));
				m.setMobileNo(rs.getString("MOBILE_NO"));
				m.setImplementingAgencyName(rs.getString("IMPLEMENTING_AGENCY_NAME"));
				m.setLegalStatus(rs.getString("LEGAL_STATUS"));																				
				m.setProgramCost(rs.getString("PROGRAM_COST"));
				m.setProgramName(rs.getString("PROGRAM_NAME"));
				m.setProposalReciptDate(rs.getString("PROPOSAL_RECEIPT_DT"));
				m.setProgramNature(rs.getString("PROGRAM_NATURE"));
				m.setProgramDate(rs.getString("PROGRAM_DATE"));
				m.setCreatedBy(rs.getString("CREATED_BY"));
				m.setCreatedOn(rs.getString("CREATED_ON"));				
				m.setStatus(rs.getString("STATUS"));
				m.setStatusName(rs.getString("STATUS_NAME"));
				m.setProgramNatureName("PRN_NAME");
				m.setSubjectName(rs.getString("SUBNAME"));
				m.setProgramNatureName(rs.getString("PRN_NAME"));
				m.setActive(Integer.parseInt(rs.getString("ACTIVE")));		
				m.setProposalDocFileName(rs.getString("PROPOSAL_FILENAME"));
				m.setProposalDocContentType(rs.getString("PROPOSAL_CONTENT_TYPE"));
				m.setReleaseOrderDocFileName(rs.getString("RELEASE_ORDER_FILENAME"));
				m.setReleaseOrderDocContentType(rs.getString("RELEASE_ORDER_CONTENT_TYPE"));
				m.setDishaFileNumber(rs.getString("DISHA_FILENUMBER"));
				m.setFrNumber(rs.getString("FRNUMBER"));
				m.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
				m.setExemption12AA(rs.getString("IT_EXEMPTION_12AA"));
				m.setEmail(rs.getString("EMAIL"));
				m.setRemark(rs.getString("REMARK"));
				m.setFilecurrentlywith(rs.getString("FILECURRENTLYWITH"));
				m.setProgramDateTo(rs.getString("PROGRAM_DATE_TO"));
				m.setNameofimpelementingajency(rs.getString("NAME_OF_IMPLEMENTING_AJENCY"));
				m.setCompany(rs.getString("COMPANY"));				
				m.setNgo(rs.getString("NGO"));
				m.setTrust(rs.getString("TRUST"));
				m.setSection8(rs.getString("SECTION8"));

				/*m.setProgramd(convertStringToDate(rs.getString("PROGRAM_DATE")));
				m.setProgramdto(convertStringToDate(rs.getString("PROGRAM_DATE_TO")));*/
				m.setReceiptsDocFileName(rs.getString("RCPT_FILENAME"));
				m.setReceiptsDocContentType(rs.getString("RCPT_CONTENT_TYPE"));
				m.setEightyGDocContentType(rs.getString("EXM_80G_CONTENT_TYPE"));
				m.setEightyGDocFileName(rs.getString("EXC_80G_FILENAME"));
				m.setTwelveAADocFileName(rs.getString("EXM_12AA_FILENAME"));
				m.setTwelveAADocContentType(rs.getString("EXM_12AA_CONTENT_TYPE"));




			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return m;

	}


	public CSRProgramModel view(CSRProgramModel model) {
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<CSRProgramModel> list = new ArrayList<CSRProgramModel>();//FINDCSRBYID
		String query = "";
		StringBuffer sql = new StringBuffer(query);
		sql.append(" and lower(SD.SPONID)= lower('" + model.getId() + "') ");
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());
			while (rs.next()) {
				model = new CSRProgramModel();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return model;

	}

	synchronized public  String save(CSRProgramModel model) {
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Boolean rtFlag=false;
		String refNo = "REF";
		String insertQuery=szResBundl.getString("INSCSRDTAILS");
		
		try {
			int i = 0;
			int id = CommonDaoImpl.getInstance().getMaxIdFromTable("TT_CSR_DTL", "CSR_ID");
			refNo = refNo + id;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1,id);
			pstmt.setString(2, refNo);
			pstmt.setString(3, model.getSubject());
			pstmt.setString(4, model.getProgramName());
			pstmt.setDate(5,convertStringToDate(model.getProposalReciptDate()));
			pstmt.setDate(6, convertStringToDate(model.getProgramDate()));
			pstmt.setString(7, String.valueOf(model.getProgramCost()));
			pstmt.setString(8, model.getFundingFrmOngc());
			pstmt.setString(9, model.getApplicableGst());
			pstmt.setString(10, model.getImplementingAgencyName());
			pstmt.setString(11, model.getLegalStatus());
			pstmt.setString(12, model.getExemption80G());
			pstmt.setString(13, model.getContactPerson());
			pstmt.setString(14, model.getDesignarionInAgency());
			pstmt.setString(15, model.getMobileNo());
			pstmt.setString(16, model.getActivityNature());
			pstmt.setString(17, model.getProgramNature());
			pstmt.setString(18, model.getApprovingAuthorityAsPerBDM());
			pstmt.setString(19, model.getStatus());
			pstmt.setString(20, model.getCreatedBy());
			model.setActive(1);
			pstmt.setInt(21, model.getActive());

			pstmt.setString(22, model.getProposalDocFileName());
			pstmt.setString(23, model.getProposalDocContentType());
			pstmt.setString(24, model.getReleaseOrderDocFileName());
			pstmt.setString(25, model.getReleaseOrderDocContentType());
			pstmt.setString(26, model.getDishaFileNumber());
			pstmt.setString(27, model.getFrNumber());
			pstmt.setString(28, model.getTrackingNumber());
			pstmt.setString(29, model.getExemption12AA());
			pstmt.setString(30, model.getEmail());
			pstmt.setString(31, model.getRemark());
			pstmt.setString(32, model.getFilecurrentlywith());

			pstmt.setDate(33,convertStringToDate( model.getProgramDateTo()));
			pstmt.setString(34, model.getNameofimpelementingajency());

			pstmt.setString(35,model.getCompany());				
			pstmt.setString(36,model.getNgo());
			pstmt.setString(37,model.getTrust());
			pstmt.setString(38,model.getSection8());

			pstmt.setString(39,"");
			pstmt.setString(40,"");
			pstmt.setString(41,model.getReceiptsDocFileName());
			pstmt.setString(42,model.getReceiptsDocContentType());
			pstmt.setString(43,model.getEightyGDocFileName());
			pstmt.setString(44,model.getEightyGDocContentType());
			pstmt.setString(45,model.getTwelveAADocFileName());
			pstmt.setString(46,model.getTwelveAADocContentType());

			rtFlag=pstmt.executeUpdate()>0?true:false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}

		return rtFlag?refNo:"";
	}

	synchronized public  Boolean update(CSRProgramModel model){
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/csr");
		Connection conn=null;
		Connection connection=null;
		Statement statement = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Boolean rtFlag=false;
		String updateQuery=szResBundl.getString("UPDCSRDETAILS");
		
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(updateQuery);

			pstmt.setString(1, model.getProgramName());
			pstmt.setDate(2, convertStringToDate(model.getProposalReciptDate()));
			//pstmt.setString(3, model.getProgramDate());

			pstmt.setDate(3, convertStringToDate(model.getProgramDate()));
			//pstmt.setDate(6, convertStringToDate(model.getProgramDate()));

			pstmt.setString(4, model.getProgramCost());
			pstmt.setString(5, model.getFundingFrmOngc());
			pstmt.setString(6, model.getApplicableGst());
			pstmt.setString(7, model.getImplementingAgencyName());
			pstmt.setString(8, model.getLegalStatus());
			pstmt.setString(9, model.getExemption80G());
			pstmt.setString(10, model.getContactPerson()!=null?model.getContactPerson():"");
			pstmt.setString(11, model.getDesignarionInAgency());
			pstmt.setString(12, model.getMobileNo()!=null?model.getMobileNo():"");
			pstmt.setString(13, model.getActivityNature());
			pstmt.setString(14, model.getProgramNature());
			pstmt.setString(15, model.getApprovingAuthorityAsPerBDM());
			pstmt.setString(16, model.getStatus());
			pstmt.setString(17, model.getUpdatedBy());
			pstmt.setString(18, model.getProposalDocFileName());
			pstmt.setString(19, model.getProposalDocContentType());
			pstmt.setString(20, model.getReleaseOrderDocFileName());
			pstmt.setString(21, model.getReleaseOrderDocContentType());
			pstmt.setString(22, model.getDishaFileNumber());
			pstmt.setString(23, model.getFrNumber());
			pstmt.setString(24, model.getTrackingNumber());	
			pstmt.setString(25, model.getExemption12AA());	
			pstmt.setString(26, model.getEmail());
			pstmt.setString(27, model.getRemark());
			pstmt.setString(28, model.getFilecurrentlywith());
			//pstmt.setString(30, model.getProgramDateTo());
			pstmt.setString(29, model.getNameofimpelementingajency());
			pstmt.setString(30,model.getCompany());				
			pstmt.setString(31,model.getNgo());
			pstmt.setString(32,model.getTrust());
			pstmt.setString(33,model.getSection8());
			pstmt.setDate(34, convertStringToDate(model.getProgramDateTo()));
			pstmt.setString(35,"");
			pstmt.setString(36,"");
			pstmt.setString(37,model.getReceiptsDocFileName());
			pstmt.setString(38,model.getReceiptsDocContentType());
			pstmt.setString(39,model.getEightyGDocFileName());
			pstmt.setString(40,model.getEightyGDocContentType());
			pstmt.setString(41,model.getTwelveAADocFileName());
			pstmt.setString(42,model.getTwelveAADocContentType());
			pstmt.setInt(43, model.getId());
			rtFlag=pstmt.executeUpdate()>0?true:false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}

		return rtFlag;
	}




	java.sql.Date convertStringToDate(String dt) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date d=null;
		String s[]=dt.split("-");
		if(s[1].length()==2){
			d=format1.parse(dt); 
		}else{
			d=sf.parse(dt); 
		}


		return new java.sql.Date(d.getTime());
	}




}


