
package com.ongc.liferay.vigilance.util;

import com.ongc.liferay.vigilance.model.VigilanceComplaint;
import com.ongc.liferay.vigilance.model.VigilanceUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import javax.activation.DataHandler;
import javax.activation.DataSource;


//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

/**
 * Singleton class.
 *  
 * @author Sanjay
 * @author Shyam Sunder
 * @author Parvesh Singh
 * @version 1.0 23/Aug/2012
 */
public class ONGCUtil {
	private static ONGCUtil instance = null;
	
	
	
	/**
	 * check and return single object of Apogee class.
	 * @return
	 */
	public static ONGCUtil getInstance(){
		if(instance==null){
			instance = new ONGCUtil();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param sToEmailID is an email id to which mail will be sended
	 * @param otp is One Time Password generated from client and it will be sending
	 * @param iso decides from which country request is comming.
	 * @return boolean which means whether email has sent or not.
	 */
	public boolean sendOTPOnEmail(String sToEmailID,String otp, String iso){
		boolean isSend = false;
		String otpMsg = null;
		String MailBody = null;
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			final String username=ResourceBundle.getBundle("/content/vigilance").getString("Username").toString().trim();
			final String password=ResourceBundle.getBundle("/content/vigilance").getString("Password").toString().trim();
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			if(iso.equals("loginOtp")){
				MailBody = otp+ " is the OTP to access Vigilance Portal. This is valid for 10 minutes.\nRegards Ongc";
				}
			String MailFrom ="noreply@ongc.co.in";
			String MailSubject = getOTPSubject(bundle, iso);
			String mailCC = "";
			java.util.Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			
			 Session mailersession = Session.getInstance(props, new
			 javax.mail.Authenticator() { protected PasswordAuthentication
			 getPasswordAuthentication() { return new
			 PasswordAuthentication(username,password); } });
			
			Message message = new MimeMessage(mailersession);
			
			if(sToEmailID!=null && sToEmailID.length()>1)
			{
			message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
				String sEmailToCC="";
				if(mailCC!=null)
					sEmailToCC=mailCC;
				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
				message.setSubject(MailSubject);
				message.setText(MailBody);
				System.out.println("Message with Name:\n\n");
				javax.mail.Transport.send(message);
				System.out.println("Mail Subject=======================>"+MailSubject+"sToEmailID===================>"+sToEmailID+"message================================================>"+message);
				isSend = true;
				System.out.println(isSend);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			isSend = false;
		}
		return isSend;
	}
	
	public boolean sendComplaintOTPOnEmail(String sToEmailID,String otp, VigilanceComplaint complaint,String complaintNumber,VigilanceUser vigilanceUser) {
		boolean isSend = false;
		String otpMsg = null;
		String MailBody = null;
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			final String username=ResourceBundle.getBundle("/content/vigilance").getString("Username").toString().trim();
			final String password=ResourceBundle.getBundle("/content/vigilance").getString("Password").toString().trim();
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			MailBody = "Name:"+vigilanceUser.getFirstName()+" "+vigilanceUser.getMiddleName()+" "+vigilanceUser.getLastName()+",\n Subject:"+complaint.getComplaintSubject()+",\n Address:"+complaint.getWorkCentre() ;
			String MailFrom ="noreply@ongc.co.in";
			String MailSubject = "Complain Number VIG0"+complaintNumber+" registered";
			String mailCC = "";
			java.util.Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			
			 Session mailersession = Session.getInstance(props, new
			 javax.mail.Authenticator() { protected PasswordAuthentication
			 getPasswordAuthentication() { return new
			 PasswordAuthentication(username,password); } });
			
			Message message = new MimeMessage(mailersession);
			
			if(sToEmailID!=null && sToEmailID.length()>1)
			{
			message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
				String sEmailToCC="";
				if(mailCC!=null)
					sEmailToCC=mailCC;
				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
				message.setSubject(MailSubject);
				message.setText(MailBody);
				System.out.println("Message with Name:\n\n");
				javax.mail.Transport.send(message);
				System.out.println("Mail Subject=======================>"+MailSubject+"sToEmailID===================>"+sToEmailID+"message================================================>"+message);
				isSend = true;
				System.out.println(isSend);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			isSend = false;
		}
		return isSend;
	}
	
	public boolean sendPasswordOnEmail(String sToEmailID,String otp){
		boolean isSend = false;
		
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			//  String MailFrom = "afadmin@africa.airtel.com"; //getMailFromEmailId(iso);
			//String MailFrom = getFromEmailId(bundle, iso);
			String MailFrom ="noreply@ongc.co.in";
			//	String MailSubject = "Apogee: One Time Password"; //getOTPSubject(iso);
			String MailSubject = "Forgot Password";
			String mailCC = "";
			//	String MailBody = "Dear Customer,\n\nYour Password is: ~OTP~ for online registration as apogee customer.\n\nThanks & Regards\nSystem Admin";
			String MailBody = "Dear Customer,\n\nYour New Password is: ~OTP~ for online registration as apogee customer.\n\nThanks & Regards\nSystem Admin";
			MailBody = prepareOTPMailBody(otp, MailBody);
			java.util.Properties props = System.getProperties();
			final String username = "noreply@ongc.co.in";
			final String password = "A16@ongc";
			// Setup mail server
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
//			log.info("smtpHost ::: "+smtpHost);
//			Session mailersession = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username,password);
//					}
//				});
			// Get session
			//javax.mail.Session mailersession = javax.mail.Session.getInstance(props, null);
			// Define message
			//javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailersession);
//			Message message = new MimeMessage(mailersession);
//			
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				
//				message.setSubject(MailSubject);
//				
//				//mailBuffer.append(sRefNumber+" : "+sRefNo);
//				message.setText(MailBody);
//				
//				log.info("Message with Name:\n\n");
//				javax.mail.Transport.send(message);
//				isSend = true;
//			}
		}
		catch(Exception ex)
		{
			isSend = false;
		}
		return isSend;
	}
	public boolean sendComplaintOnAdminEmail(String Subject, String iso){
		boolean isSend = false;
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			//  String MailFrom = "afadmin@africa.airtel.com"; //getMailFromEmailId(iso);
			//String MailFrom = getFromEmailId(bundle, iso);
			String MailFrom ="noreply@ongc.co.in";
			//	String MailSubject = "Apogee: One Time Password"; //getOTPSubject(iso);
			String MailSubject = getOTPSubject(bundle, iso);
			String mailCC = "";
			//	String MailBody = "Dear Customer,\n\nYour Password is: ~OTP~ for online registration as apogee customer.\n\nThanks & Regards\nSystem Admin";
			String MailBody = getOTPMailBody(bundle, iso);
			MailBody = prepareComlaintMailBody(Subject, MailBody);
			final String username = "noreply@ongc.co.in";
			final String password = "A16@ongc";		
			java.util.Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
	 
//			Session mailersession = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username,password);
//					}
//				});
//			// Get session
//			//javax.mail.Session mailersession = javax.mail.Session.getInstance(props, null);
//			// Define message
//			//javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailersession);
//			Message message = new MimeMessage(mailersession);
//			String sToEmailID = ResourceBundle.getBundle("/content/vigilanceconfig").getString("VIG_COMPLAINT_ADMIN_FROMEMAILID");
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				
//				message.setSubject(MailSubject);
//				
//				//mailBuffer.append(sRefNumber+" : "+sRefNo);
//				message.setText(MailBody);
//				
//				log.info("Message with Name:\n\n");
//				javax.mail.Transport.send(message);
//				isSend = true;
//			}
		}
		catch(Exception ex)
		{
			isSend = false;
		}
		return isSend;
	}
	

	public boolean sendComplaintOnGrievanceAdminEmail(VigilanceComplaint complaint, String iso){
		boolean isSend = false;
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			
			String MailFrom = getFromEmailId(bundle, iso);
			String MailSubject = getOTPSubject(bundle, iso);
			String mailCC = "";
			String MailBody = getOTPMailBody(bundle, iso);
			MailBody = prepareGrievanceComlaintMailBody(complaint, MailBody);
			final String username = "noreply@ongc.co.in";
			final String password = "A16@ongc";		
			java.util.Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
	 
//			Session mailersession = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username,password);
//					}
//				});
//
//			MimeMessage message = new MimeMessage(mailersession);
//			String sToEmailID = ResourceBundle.getBundle("/content/vigilanceconfig").getString("VIG_COMPLAINT_GRIEVANCE_TOEMAILID");
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				
//				message.setSubject(MailSubject);
//				
//				MimeMultipart multipart = new MimeMultipart();
//				MimeBodyPart txt = new MimeBodyPart(); 
//				txt.setDataHandler(new DataHandler(MailBody, "text/html"));
//				multipart.addBodyPart(txt);
//				for(ComplaintAttachment ca:complaint.getAttachmentList()){
//
//					ComplaintAttachment attachment=new ComplaintAttachment();
//					ComplaintManagementImpl comp=new ComplaintManagementImpl();
//					attachment=comp.getAttachmentById(ca.getAttachmentId());
//					MimeBodyPart att = new MimeBodyPart();
//					ByteArrayDataSource bds = new ByteArrayDataSource(attachment.getFileData(), "application/pdf"); 
//					att.setDataHandler(new DataHandler(bds)); 
//					att.setFileName(attachment.getFileName());				
//					multipart.addBodyPart(att);
//				}
//		        message.setContent(multipart);
//				javax.mail.Transport.send(message);
//				isSend = true;
//			}
		}
		catch(Exception ex)
		{
			isSend = false;
			ex.printStackTrace();
		}
		return isSend;
	}

	public boolean sendComplaintOnAdminEmailBackup(String Subject, String iso){
		boolean isSend = false;
		try
		{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			//  String MailFrom = "afadmin@africa.airtel.com"; //getMailFromEmailId(iso);
			String MailFrom = getFromEmailId(bundle, iso);
			//	String MailSubject = "Apogee: One Time Password"; //getOTPSubject(iso);
			String MailSubject = getOTPSubject(bundle, iso);
			String mailCC = "";
			//	String MailBody = "Dear Customer,\n\nYour Password is: ~OTP~ for online registration as apogee customer.\n\nThanks & Regards\nSystem Admin";
			String MailBody = getOTPMailBody(bundle, iso);
			MailBody = prepareComlaintMailBody(Subject, MailBody);
			java.util.Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.host", smtpHost);
			// Get session
//			javax.mail.Session mailersession = javax.mail.Session.getInstance(props, null);
//			// Define message
//			//javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailersession);
//			Message message = new MimeMessage(mailersession);
//			String sToEmailID = ResourceBundle.getBundle("/content/vigilanceconfig").getString("VIG_COMPLAINT_ADMIN_FROMEMAILID");
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				
//				message.setSubject(MailSubject);
//				
//				//mailBuffer.append(sRefNumber+" : "+sRefNo);
//				message.setText(MailBody);
//				
//				log.info("Message with Name:\n\n");
//				javax.mail.Transport.send(message);
//				isSend = true;
//			}
		}
		catch(Exception ex)
		{
			isSend = false;
		}
		return isSend;
	}
	
	/**
	 * 
	 * @param MSISDN
	 * @param otp
	 */
	public void sendOTPOnSMS(String MSISDN, String otp, String iso ){
		String response = "";
		BufferedReader in = null;
		String strUser=null;
		String strPassword=null;
		String sUserMsg="";
		String otpMsg = "";
		try
		{
			ResourceBundle rBundle = ResourceBundle.getBundle("/content/vigilance");
			//System.getProperties().put("http.nonProxyHosts","10.205.48.190");
			String sMsg=URLEncoder.encode(sUserMsg);
			String SMS_HOST=rBundle.getString("SMS_HOST");
			String SMS_PORT=rBundle.getString("SMS_PORT");
			String strEncUser=rBundle.getString("SMS_USERNAME");
//			strUser=TriplerDecBase64.tripleDecoding(strEncUser);
			String strEncPass=rBundle.getString("SMS_PASSWORD");
//			strPassword=TriplerDecBase64.tripleDecoding(strEncPass);
			if (iso.equals(VigilanceConstant.REGISTRATION)) {
				otpMsg=rBundle.getString("registrationOTP");
			} else if(iso.equals(VigilanceConstant.OPT_TYPE_COMPLAINT)){
				otpMsg=rBundle.getString("complaintOTP");
			}	else if(iso.equals(VigilanceConstant.OPT_TYPE_LOGIN)){
				otpMsg=rBundle.getString("loginOTP");
			}
			else if(iso.equals("pass")){
				otpMsg="Dear Customer \nYour New Password is ~OTP~ for online complaint registration.\nRegards\nVigilance Admin";
			}
				
		
			sUserMsg = prepareOTPMailBody(otp, otpMsg);
			StringBuffer smsUrl = new StringBuffer();
			
//			http://10.205.48.190:13013/cgi-bin/sendsms?username=ongc&password=ongc12&from=ONGC&to=" & User_Mobile1 & "&text=" & otp_sendtext & " 
			smsUrl.append("http://").append(rBundle.getString("SMS_HOST")).append(":").append(rBundle.getString("SMS_PORT"));
			smsUrl.append("/cgi-bin/sendsms?username=").append(strEncUser).append("&password=").append(strEncPass).append("&from=ONGC&to=");
			smsUrl.append(URLEncoder.encode(MSISDN , "UTF-8")).append("&text=").append(URLEncoder.encode(sUserMsg , "UTF-8")).append("&meta-data=%3Fsmpp%3FEntityID%3D1001186049255234740%26ContentID%3D1407165363567411666");
			//smsUrl.append(URLEncoder.encode(MSISDN , "UTF-8")).append("&text=").append("ONGCIN").append(URLEncoder.encode(sUserMsg , "UTF-8")).append("&meta-data=%3Fsmpp%3FEntityID%3D1001186049255234740%26ContentID%3D1407163783620024546");
			//			String smsUrl ="http://10.5.45.39:56000?login="+strEncUser+"&pass="+strEncPass+"&sms="+sMsg+"&msisdn="+MSISDN+"&src=54325&type=text";
			//http://10.205.48.190:13013/cgi-bin/sendsms?username=ongc&password=ongc12&from=ONGC&to=NUMBERS&text=MESSAGE&charset=UTF-8&meta-data=%3Fsmpp%3FEntityID%3D1001186049255234740%26ContentID%3DTEMPLATEID
				 
			URL url = new URL(removeWhiteSpaces(smsUrl.toString()));
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String tempStr = null;
			while ((tempStr = in.readLine()) != null) 
			{
				response = response + tempStr;
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(in!=null)
					in.close();
			}
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * removes white spaces from the url
	 * @param smsText
	 * @return
	 */
	private String removeWhiteSpaces(String smsText)
	{
		int i;
		do 
		{
			i = smsText.indexOf(" ");
			if (i > 0)
			{
				smsText =smsText.substring(0, i) +"%20"+smsText.substring(i + 1);
			}
		} 
		while (i > 0);
		return smsText;
	}
	
	/**
	 * 
	 * @param otp
	 * @param MailBody
	 * @return
	 */
	private String prepareOTPMailBody(String otp, String MailBody){
		MailBody  = MailBody.replaceAll("~OTP~", otp);
		return MailBody;
	}

	private String prepareComlaintMailBody(String subject, String MailBody){
		MailBody  = MailBody.replaceAll("~SUBJECT~", subject);
		return MailBody;
	}
	
	private String prepareGrievanceComlaintMailBody(
			VigilanceComplaint complaint, String mailBody) {
		mailBody  = mailBody.replaceAll("~COMPLAINTACTNO~", complaint.getComplaintActNo()==null ? "" :  complaint.getComplaintActNo());
		mailBody  = mailBody.replaceAll("~COMPLAINTDATE~", complaint.getComplaintDate()==null ? "" :  complaint.getComplaintDate().toString());
		mailBody  = mailBody.replaceAll("~TITLE~", complaint.getComplainBy()==null || complaint.getComplainBy().getTitle()==null ? "" : complaint.getComplainBy().getTitle());
		mailBody  = mailBody.replaceAll("~FIRSTNAME~", complaint.getComplainBy()==null || complaint.getComplainBy().getFirstName()==null ? "" : complaint.getComplainBy().getFirstName());
		mailBody  = mailBody.replaceAll("~MIDDLENAME~", complaint.getComplainBy()==null || complaint.getComplainBy().getMiddleName()==null ? "" : complaint.getComplainBy().getMiddleName());
		mailBody  = mailBody.replaceAll("~LASTNAME~", complaint.getComplainBy()==null || complaint.getComplainBy().getLastName()==null ? "" : complaint.getComplainBy().getLastName());
		mailBody  = mailBody.replaceAll("~PHONENO~", complaint.getComplainBy()==null || complaint.getComplainBy().getMobile() == null ? "" :  complaint.getComplainBy().getMobile());
		mailBody  = mailBody.replaceAll("~EMAILID~", complaint.getComplainBy()==null || complaint.getComplainBy().getEmailId() == null  ? "" :  complaint.getComplainBy().getEmailId());
		mailBody  = mailBody.replaceAll("~FIRSTADDRESS~", complaint.getComplainBy()==null || complaint.getComplainBy().getFirstAddress()== null ? "" : complaint.getComplainBy().getFirstAddress());
		mailBody  = mailBody.replaceAll("~SECONDADDRESS~", complaint.getComplainBy()==null || complaint.getComplainBy().getSecondAddress()== null ? "" : complaint.getComplainBy().getSecondAddress());
		mailBody  = mailBody.replaceAll("~COUNTRY~", complaint.getComplainBy()==null || complaint.getComplainBy().getCountry() == null ? "" :  complaint.getComplainBy().getCountry());
		mailBody  = mailBody.replaceAll("~STATE~", complaint.getComplainBy()==null || complaint.getComplainBy().getState() == null ? "" :  complaint.getComplainBy().getState());
		mailBody  = mailBody.replaceAll("~COMPLAINTAGAINST~", complaint.getComplaintAgainst()==null ? "" :  complaint.getComplaintAgainst());
		mailBody  = mailBody.replaceAll("~WORKCENTER~", complaint.getWorkCentre()==null ? "" :  complaint.getWorkCentre());
		mailBody  = mailBody.replaceAll("~DEPARTMENT~", complaint.getDepartmetn()==null ? "" :  complaint.getDepartmetn());
		mailBody  = mailBody.replaceAll("~DESIGNATION~", complaint.getDesignation()==null ? "" :  complaint.getDesignation());
		mailBody  = mailBody.replaceAll("~COMPLAINTSUBJECT~", complaint.getComplaintSubject()==null ? "" :  complaint.getComplaintSubject());
		mailBody  = mailBody.replaceAll("~COMPLAINTDETAIL~", complaint.getComplaintDetail()==null ? "" :  complaint.getComplaintDetail());
		
		
		return mailBody;
	}
	/**
	 * Function to generate OTP
	 * @return
	 */
	public  String generateOTP(){
		String otp = RandomString.getRandomString(6,"dummy");
		return otp;
	}
	public  String generatePassword(){
		String otp = RandomString.getRandomString(8,"dummy");
		return otp;
	}
	
	/**
	 * send confirmation email to customer after successfull registration.
	 * @param sToEmailID to whom email will be send
	 * @param iso decides country
	 */
	public void sendConfirmOnEmail(String iso1, String iso){
		try{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			//	String MailFrom = "afadmin@africa.airtel.com";
			String sToEmailID= getFromEmailId(bundle, iso1);
			//String MailFrom = getFromEmailId(bundle, iso);
			String MailFrom = "noreply@ongc.co.in";
			//	String MailSubject = "Congratulations!!! Successful Registration";
			String MailSubject = getRegSuccessSubject(bundle, iso);
			String mailCC = "";
			//	String MailBody = "Dear Customer,\n\nCongratulations!!! You have registered successfully as Apogee Customer.\n\nThanks & Regards\nSystem Admin";
			String MailBody = getRegSuccessMailBody(bundle, iso);
			final String username = "noreply@ongc.co.in";
			final String password = "A16@ongc";			
			java.util.Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
	 
//			Session mailersession = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username,password);
//					}
//				});
//			// Get session
//			//javax.mail.Session mailersession = javax.mail.Session.getInstance(props, null);
//			// Define message
//			//javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailersession);
//			Message message = new MimeMessage(mailersession);
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				message.setSubject(MailSubject);
//				//mailBuffer.append(sRefNumber+" : "+sRefNo);
//				message.setText(MailBody);
//				//	//system.out.println("Message with Name:\n\n"+MailBody.toString().replaceAll("@ZYX@","\n"));
//				javax.mail.Transport.send(message);
//			}
		}
		catch(Exception ex)
		{
		}
	}
	public void sendConfirmOnEmailBackup(String iso1, String iso){
		try{	
			String smtpHost = ResourceBundle.getBundle("/content/vigilance").getString("SMTP_HOST");
			ResourceBundle bundle = ResourceBundle.getBundle("/content/vigilanceconfig");
			//	String MailFrom = "afadmin@africa.airtel.com";
			String sToEmailID= getFromEmailId(bundle, iso1);
			String MailFrom = getFromEmailId(bundle, iso);
			//	String MailSubject = "Congratulations!!! Successful Registration";
			String MailSubject = getRegSuccessSubject(bundle, iso);
			String mailCC = "";
			//	String MailBody = "Dear Customer,\n\nCongratulations!!! You have registered successfully as Apogee Customer.\n\nThanks & Regards\nSystem Admin";
			String MailBody = getRegSuccessMailBody(bundle, iso);
						
			java.util.Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.host", smtpHost);
			// Get session
//			javax.mail.Session mailersession = javax.mail.Session.getInstance(props, null);
//			// Define message
//			//javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailersession);
//			Message message = new MimeMessage(mailersession);
//			if(sToEmailID!=null && sToEmailID.length()>1)
//			{
//				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));				    
//				// set the mail to field
//				String sEmailToCC="";
//				if(mailCC!=null)
//					sEmailToCC=mailCC;
//				message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(sToEmailID));
//				if(sEmailToCC!=null && sEmailToCC.indexOf("@")!=-1)		
//					message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress(sEmailToCC));
//				message.setSubject(MailSubject);
//				//mailBuffer.append(sRefNumber+" : "+sRefNo);
//				message.setText(MailBody);
//				//	//system.out.println("Message with Name:\n\n"+MailBody.toString().replaceAll("@ZYX@","\n"));
//				javax.mail.Transport.send(message);
//			}
		}
		catch(Exception ex)
		{
		}
	}
	/**
	 * returns sender email id to caller
	 * @param bundle
	 * @param iso
	 * @return
	 */
	private String getFromEmailId(ResourceBundle bundle, String iso){
		String email = "";
		if (iso.equalsIgnoreCase("registration")) {
			email = bundle.getString("VIG_REGISTRATION_OTPMAIL_FROMEMAILID");
		} else if (iso.equalsIgnoreCase("complaintOtp")) {
			email = bundle.getString("VIG_REGISTRATION_OTPMAIL_FROMEMAILID");
		} else if (iso.equalsIgnoreCase(VigilanceConstant.CHANGE_PASSWORD)) {
			email = bundle.getString("VIG_CHANGEPASS_OTPMAIL_FROMEMAILID");
		} else if (iso.equalsIgnoreCase("registerComplaint")) {
			email = bundle.getString("VIG_COMPLAINT_ADMIN_FROMEMAILID");
		} else if (iso.equalsIgnoreCase("ConfirmComplaint")) {
			email = bundle.getString("VIG_COMPLAINT_SUCCESS_FROMEMAILID");
		} else if (iso.equalsIgnoreCase("ConfirmComplaintTo")) {
			email = bundle.getString("VIG_COMPLAINT_ADMIN_TOEMAILID");
		} else if (iso.equalsIgnoreCase("SendToGrievance")) {
			email = bundle.getString("VIG_COMPLAINT_GRIEVANCE_FROMEMAILID");
		}
		return email;
	}
	
	/**
	 * returns one time password subject to caller
	 * @param bundle
	 * @param iso
	 * @return
	 */
	private String getOTPSubject(ResourceBundle bundle, String iso){
		String subject ="";
		
		if (iso.equalsIgnoreCase("registration")) {
			subject = bundle.getString("VIG_REGISTRATION_OTPMAIL_SUBJECT");
		} else if (iso.equalsIgnoreCase("complaintOtp")) {
			subject = bundle.getString("VIG_COMPLAINT_OTPMAIL_SUBJECT");
		} else if (iso.equalsIgnoreCase("registerComplaint")) {
			subject = bundle.getString("VIG_COMPLAINT_ADMIN_SUBJECT");
		} else if (iso.equalsIgnoreCase("SendToGrievance")) {
			subject = bundle.getString("VIG_COMPLAINT_GRIEVANCE_SUBJECT");
		} else if (iso.equalsIgnoreCase("loginOtp")) {
			subject = bundle.getString("VIG_LOGIN_OTPMAIL_SUBJECT");
		}
		
		/*if(iso.equalsIgnoreCase("NG")){
			subject = bundle.getString("NG_OTPMAIL_SUBJECT");
		}
		else if(iso.equalsIgnoreCase("ZM")){
			subject = bundle.getString("ZM_OTPMAIL_SUBJECT");
		}
		else if(iso.equalsIgnoreCase("GA")){
			subject = bundle.getString("GA_OTPMAIL_SUBJECT");
		}	*/	
		return subject;
	}
	
	/**
	 * returns one time password mail body to caller
	 * @param bundle
	 * @param iso
	 * @return
	 */
	private String getOTPMailBody(ResourceBundle bundle, String iso){
		String mailBody="";
		if (iso.equalsIgnoreCase("registration")) {
			mailBody = bundle.getString("VIG_REGISTRATION_OTPMAIL_BODY");
		} else if (iso.equalsIgnoreCase("complaintOtp")) {
			mailBody = bundle.getString("VIG_COMPLAINT_OTPMAIL_BODY");
		} else if (iso.equalsIgnoreCase("registerComplaint")) {
			mailBody = bundle.getString("VIG_COMPLAINT_ADMIN_BODY");
		}  else if (iso.equalsIgnoreCase("SendToGrievance")) {
			mailBody = bundle.getString("VIG_COMPLAINT_GRIEVANCE_BODY");
		}
		
		/*if(iso.equalsIgnoreCase("NG")){
			mailBody = bundle.getString("NG_OTPMAIL_BODY");
		}
		else if(iso.equalsIgnoreCase("ZM")){
			mailBody = bundle.getString("ZM_OTPMAIL_BODY");
		}
		else if(iso.equalsIgnoreCase("GA")){
			mailBody = bundle.getString("GA_OTPMAIL_BODY");
		}*/		
		return mailBody;
	}
	
	/**
	 * returns mail subject for successfull registration
	 * @param bundle
	 * @param iso
	 * @return
	 */
	private String getRegSuccessSubject(ResourceBundle bundle, String iso){
		String subject = "";
		if(iso.equalsIgnoreCase(VigilanceConstant.CHANGE_PASSWORD)){
			subject = bundle.getString("VIG_CHANGEPASS_OTPMAIL_SUBJECT");
		} else if(iso.equalsIgnoreCase("ConfirmComplaint")){
			subject = bundle.getString("VIG_COMPLAINT_SUCCESS_SUBJECT");
		} 		
		return subject;
	}
	
	/**
	 * returns mail body for successfull registration
	 * @param bundle
	 * @param iso
	 * @return
	 */
	private String getRegSuccessMailBody(ResourceBundle bundle, String iso){
		String mailBody = "";
		if (iso.equalsIgnoreCase(VigilanceConstant.CHANGE_PASSWORD)) {
			mailBody = bundle.getString("VIG_CHANGEPASS_OTPMAIL_BODY");
		} else if (iso.equalsIgnoreCase("ConfirmComplaint")) {
				mailBody = bundle.getString("VIG_COMPLAINT_SUCCESS_BODY");
			} 	
		return mailBody;
	}
	
	public static boolean isAdmin(String userId){
		boolean chkStatus=false;
		String admins = ResourceBundle.getBundle("/content/vigilanceconfig").getString("VIG_ADMIN_USERIDS");
		String adminLists[]=admins.split(",");
		for(int i=0;i<adminLists.length;i++){
		if(userId.equals(adminLists[i])){
			chkStatus=true;
			break;
		}
		}
		
			return chkStatus;
	}
	
	
}
