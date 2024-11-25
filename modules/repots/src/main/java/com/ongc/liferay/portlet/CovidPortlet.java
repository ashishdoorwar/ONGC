package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;
import com.ongc.liferay.util.CommonUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Covid",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/covid/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Covid,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class CovidPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doView(renderRequest, renderResponse);
	}
	
	public void saveCovidSewa(ActionRequest actionRequest,ActionResponse actionResponse) {
		String cpf = ParamUtil.getString(actionRequest,"cpf");
		String designation = ParamUtil.getString(actionRequest,"designation");
		String location = ParamUtil.getString(actionRequest,"location");
		String mobile = ParamUtil.getString(actionRequest,"mobile");
		String name = ParamUtil.getString(actionRequest,"name");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/covid/covidSewa.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean b = saveRecord(cpf, name, location, designation, mobile);
			if(b) {
				try {
					User user = new User();
					String smtpHost = ResourceBundle.getBundle("mailProperties")
							.getString("SMTP_HOST");
					String sToEmailID = "vijayaraj_jr@ongc.co.in";
					String MailFrom = "noreply@ongc.co.in";
					String MailSubject = "Covid Sewa Volunteer Details";
					String mailCC = "";

					String MailBody = "Dear Sir/Madam,<br/><br/>An employee with the following details has Volunteered for Covid Sewa on reports.ongc.co.in:<br/>"	
							+ "<br />1. CPF No.: "
							+ cpf
							+ "<br />2. Name: "
							+ name
							+ "<br />3. Location: "
							+ location
							+ "<br />4. Designation: "
							+ designation
							+ "<br />5. Mobile Number: "
							+ mobile
							+ "</i><br /><br/>"
							+ " Team ONGCReports "
							+ "</i><br/><br/>"
							+ " P.S. This is a system-generated mail. Please do not reply.<br /><br />";

					java.util.Properties props = System.getProperties();
					props.put("mail.smtp.host", smtpHost);
					javax.mail.Session mailersession = javax.mail.Session
							.getInstance(props, null);
					Message message = new MimeMessage(mailersession);
					if (sToEmailID != null && sToEmailID.length() > 1) {
						message.setFrom(new javax.mail.internet.InternetAddress(
								MailFrom));
						String sEmailToCC = "";
						if (mailCC != null)
							sEmailToCC = mailCC;
						message.addRecipient(javax.mail.Message.RecipientType.TO,
								new javax.mail.internet.InternetAddress(sToEmailID));
						if (sEmailToCC != null && sEmailToCC.indexOf("@") != -1)
							message.addRecipient(
									javax.mail.Message.RecipientType.CC,
									new javax.mail.internet.InternetAddress(
											sEmailToCC));
						message.setSubject(MailSubject);
						message.setContent(MailBody, "text/html");
						javax.mail.Transport.send(message);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private boolean saveRecord(String cpf, String name, String location, String designation, String mobile) {
		boolean flage = false;
		String query = " INSERT INTO COVID_SEVA (CPF_NO, NAME, MOBILE_NO, DESIGNATION, LOCATION, CREATED_ON) VALUES (?,?,?,?, ?,NOW()) ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setString(2, name);
			pstmt.setString(3, mobile);
			pstmt.setString(4, designation);
			pstmt.setString(5, location);
			flage = 0 < pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt);
		}
		return flage;
	}
	
	public void savecovidPlasma(ActionRequest actionRequest,ActionResponse actionResponse) {
		String cpf=ParamUtil.getString(actionRequest,"cpfNo");
		String name=ParamUtil.getString(actionRequest,"name");
		String location=ParamUtil.getString(actionRequest,"location");
		String designation=ParamUtil.getString(actionRequest,"desig");
		String mobileno=ParamUtil.getString(actionRequest,"mobileno");
		String bloodgroup=ParamUtil.getString(actionRequest,"bg");
		String dr=ParamUtil.getString(actionRequest,"dr");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/covid/covidSewa.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean b=savePlasmaRecord(cpf,name,location,designation,mobileno,bloodgroup,dr);
			if(b) {	
				try {
					User user = new User();
					String smtpHost = ResourceBundle.getBundle("mailProperties")
							.getString("SMTP_HOST");
					String sToEmailID = "medical_delhi@ongc.co.in";
					String MailFrom = "noreply@ongc.co.in";
					String MailSubject = "Plasma Donation Volunteer Details";
					String mailCC = "";
					String MailBody = "Dear Doctor,<br/><br/>An employee with the following details has registered for Plasma Donation:<br/>"	
							+ "<br />1. CPF No.: "
							+ cpf
							+ "<br />2. Name: "
							+ name
							+ "<br />3. Location: "
							+ location
							+ "<br />4. Designation: "
							+ designation
							+ "<br />5. Mobile Number: "
							+ mobileno
							+ "<br />6. Blood Group: "
							+ bloodgroup
							+ "<br />7. Date of Recovery from Covid: "
							+ dr
							+ "</i><br /><br/>"
							+ " Team ONGCReports "
							+ "</i><br/><br/>"
							+ " P.S. This is a system-generated mail. Please do not reply.<br /><br />";

					java.util.Properties props = System.getProperties();
					props.put("mail.smtp.host", smtpHost);
					javax.mail.Session mailersession = javax.mail.Session
							.getInstance(props, null);
					Message message = new MimeMessage(mailersession);
					if (sToEmailID != null && sToEmailID.length() > 1) {
						message.setFrom(new javax.mail.internet.InternetAddress(
								MailFrom));
						String sEmailToCC = "";
						if (mailCC != null)
							sEmailToCC = mailCC;
						message.addRecipient(javax.mail.Message.RecipientType.TO,
								new javax.mail.internet.InternetAddress(sToEmailID));
						if (sEmailToCC != null && sEmailToCC.indexOf("@") != -1)
							message.addRecipient(
									javax.mail.Message.RecipientType.CC,
									new javax.mail.internet.InternetAddress(
											sEmailToCC));
						message.setSubject(MailSubject);
						message.setContent(MailBody, "text/html");
						javax.mail.Transport.send(message);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public boolean savePlasmaRecord(String CPFNo, String name,String location,String designation,String mobileno,String bg,String dr){
		boolean flage=false;
		String query=" INSERT INTO COVID_PLASMA (CPF_NO, NAME, MOBILE_NO, DESIGNATION, LOCATION, BLOOD_GROUP, DATE_OF_RECOVERY, CREATED_ON) VALUES (?,?,?,?, ?,?,?,NOW()) ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,CPFNo);
			pstmt.setString(2,name);
			pstmt.setString(3,mobileno);
			pstmt.setString(4,designation);
			pstmt.setString(5,location);
			pstmt.setString(6,bg);
			pstmt.setString(7,dr);
		   flage=0<pstmt.executeUpdate();
		}
		catch (Exception e) {
	     	e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt);
		}	
		return flage;
	} 
	
	public void saveCounsellingRequest(ActionRequest actionRequest,ActionResponse actionResponse) {
		String cpf=ParamUtil.getString(actionRequest,"cpf");
		String name=ParamUtil.getString(actionRequest,"name");
		String designation=ParamUtil.getString(actionRequest,"designation");
		String location=ParamUtil.getString(actionRequest,"location");
		String mobile=ParamUtil.getString(actionRequest,"mobile");
		String address=ParamUtil.getString(actionRequest,"address");
		String spo2Level=ParamUtil.getString(actionRequest,"spo2Level");
		String requirement=ParamUtil.getString(actionRequest,"req");
		String wHome=ParamUtil.getString(actionRequest,"dependant");
		String oxyNeeded=ParamUtil.getString(actionRequest,"oxyNeeded");
		String requirementOther=ParamUtil.getString(actionRequest,"reqqothers");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/covid/covidSewa.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean b = saveCounsellingRecord(cpf, name, location, designation, mobile,requirement, requirementOther, wHome, address, spo2Level,oxyNeeded);
			if(b) {			
				try {
					User user = new User();
					String smtpHost = ResourceBundle.getBundle("mailProperties").getString("SMTP_HOST");
					String sToEmailID = "medical_delhi@ongc.co.in";
					String MailFrom = "noreply@ongc.co.in";
					String MailSubject = "Requirements Related to Covid";
					String mailCC = "";

					String MailBody = "Dear Sir/Madam,<br/><br/>Employee with following details has filled the requirements form:<br/>"	
							+ "<br />1. CPF No.: "
							+ cpf
							+ "<br />2. Name: "
							+ name
							+ "<br />3. Location: "
							+ location
							+ "<br />4. Address: "
							+ address
							+ "<br />5. Designation: "
							+ designation
							+ "<br />6. Mobile Number: "
							+ mobile
							+ "<br />7. SpO2 level in Room Air: "
							+ spo2Level
							+ "<br />8. Need: Beds/Oxygen: "
							+ requirement
							+ "<br />9. Litres of Oxygen per day (if applicable): "
							+ oxyNeeded
							+ "</i><br /><br/>"
							+ " Team ONGCReports "
							+ "</i><br/><br/>"
							+ " P.S. This is a system-generated mail. Please do not reply.<br /><br />";

					java.util.Properties props = System.getProperties();
					props.put("mail.smtp.host", smtpHost);
					javax.mail.Session mailersession = javax.mail.Session
							.getInstance(props, null);
					Message message = new MimeMessage(mailersession);
					if (sToEmailID != null && sToEmailID.length() > 1) {
						message.setFrom(new javax.mail.internet.InternetAddress(
								MailFrom));
						String sEmailToCC = "";
						if (mailCC != null)
							sEmailToCC = mailCC;
						message.addRecipient(javax.mail.Message.RecipientType.TO,
								new javax.mail.internet.InternetAddress(sToEmailID));
						if (sEmailToCC != null && sEmailToCC.indexOf("@") != -1)
							message.addRecipient(
									javax.mail.Message.RecipientType.CC,
									new javax.mail.internet.InternetAddress(
											sEmailToCC));
						message.setSubject(MailSubject);
						message.setContent(MailBody, "text/html");
						//javax.mail.Transport.send(message);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public boolean saveCounsellingRecord(String CPFNo, String name, String location,
			String designation, String mobileno, String requirement,
			String requirementOther, String wHome, String address,
			String spo2Level,String oxyNeeded) {
		boolean flage = false;
		String query = " INSERT INTO REQUEST_COUNSELLING (CPF_NO, NAME, MOBILE_NO, DESIGNATION, LOCATION, REQUIREMENT, REQUIREMENT_FOR_OTHER, OXYGEN_NEEDED, WHOM, ADDRESS, SPO2_LEVEL, CREATED_ON) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,NOW())";
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, CPFNo);
			pstmt.setString(2, name);
			pstmt.setString(3, mobileno);
			pstmt.setString(4, designation);
			pstmt.setString(5, location);
			pstmt.setString(6, requirement);
			pstmt.setString(7, requirementOther);
			pstmt.setString(8, oxyNeeded);
			pstmt.setString(9, wHome);
			pstmt.setString(10, address);
			pstmt.setString(11, spo2Level);

			flage = 0 < pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt);
		}
		return flage;
	}
	
}
