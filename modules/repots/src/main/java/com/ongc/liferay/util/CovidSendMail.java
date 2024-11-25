package com.ongc.liferay.util;

import com.ongc.liferay.model.User;

import java.util.Date;
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

public class CovidSendMail {
	ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");

	static {
	}
	public CovidSendMail(){
	}
	
	public static boolean  sendCovid19Mail(final String mailSubject,String msg,User user ) {
		try {
			String location= user.getOfficeAddress();
			String name=user.getEmpName(); 
			String cpfno=user.getCpfNo();
			String fromEmail=user.getEmailIdOfficial();
			
			String sToEmailID = "hr_help@ongc.co.in";
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST = szResBundl.getString("SMTP_HOST").toString().trim();
			final String username = szResBundl.getString("Username").toString().trim();
			final String password = szResBundl.getString("Password").toString().trim();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			String sFinalMsg = "";

			String MailFrom = fromEmail!=null?fromEmail:szResBundl.getString("Username").toString().trim();
			String[] to = { sToEmailID};//"awal_hk@ongc.co.in","mukherjee_d@ongc.co.in", "s.singh@velocis.co.in"
			javax.mail.internet.InternetAddress[] toAddress = new javax.mail.internet.InternetAddress[to.length];
			 // To get the array of toaddresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new javax.mail.internet.InternetAddress(to[i]);
            }
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				//message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				message.addRecipients(javax.mail.Message.RecipientType.TO, toAddress);

				if (mailSubject.toLowerCase().contains("domain expertise")) {
					message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("mithun.7790@gmail.com"));
				}
				

				message.setSubject(mailSubject);
				String MailBody = "<html>" + "<body>" + "<table>" + "<tr>"
						+ "<td>Dear Representative</td>" + "</tr><td></td><tr>"
						+ "</tr><td></td>" + "<tr>"
						+ "<td><b>Cpf No: </b></td>" + "<td>"
						+ cpfno
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Name: </b></td>"
						+ "<td>"
						+ name
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Date & Time: </b></td>"
						+ "<td>"
						+ new Date()
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Settlement Location: </b></td>"
						+ "<td>"
						+ location
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Subject: </b></td>"
						+ "<td>"
						+ mailSubject
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><b>Message: </b></td>"
						+ "<td>"
						+ msg
						+ "</td>"
						+ "</tr>"
						+ "<tr></tr>"
						+ "<tr></tr>"
						+ "<tr>"
						+ "<td><b>Regards </b></td></tr>"
						+ "<tr><td>"
						+ name
						+ "</td></tr>"
						+ "</tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td>This is system generated Mail</td></tr>"
						+ "</table>" + "</body>" + "</html>";

				htmlPart.setDataHandler(new DataHandler(MailBody, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				message.setSentDate(new Date());

				javax.mail.Transport.send(message);
			}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	
	
	/*
	 * Send Chief Lab User Mail to User Start
	 * 
	 */
	
	public static boolean  chiefLabFeedbackMailToUser(final String mailSubject,String msg,User user ) {
		try {
			String location= user.getOfficeAddress();
			String name=user.getEmpName(); 
			String cpfno=user.getCpfNo();
			String fromEmail=user.getEmailIdOfficial();
			String sToEmailID = user.getEmailIdOfficial();
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST = szResBundl.getString("SMTP_HOST").toString().trim();
			final String username = szResBundl.getString("Username").toString().trim();
			final String password = szResBundl.getString("Password").toString().trim();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			String sFinalMsg = "";

			String MailFrom = fromEmail!=null?fromEmail:szResBundl.getString("Username").toString().trim();
			String[] to = { sToEmailID};//"awal_hk@ongc.co.in","mukherjee_d@ongc.co.in", "s.singh@velocis.co.in"
			javax.mail.internet.InternetAddress[] toAddress = new javax.mail.internet.InternetAddress[to.length];
			 // To get the array of toaddresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new javax.mail.internet.InternetAddress(to[i]);
            }
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				//message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				message.addRecipients(javax.mail.Message.RecipientType.TO, toAddress);

				if (mailSubject.toLowerCase().contains("domain expertise")) {
					message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("bpsingh7412@gmail.com"));
				}
				message.setSubject(mailSubject);
				String MailBody = "<html>" + "<body>" + "<table>" + "<tr>"
						+ "<td>Dear "+name+"</td>" + "</tr><td></td><tr>"
						+ "</tr><td></td>" + "<tr>"
						+ "<td><b>You have successfully submitted ChiefLab Feedback Details </b></td>" 
						+ "</tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td>This is system generated Mail</td></tr>"
						+ "</table>" + "</body>" + "</html>";

				htmlPart.setDataHandler(new DataHandler(MailBody, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				message.setSentDate(new Date());

				javax.mail.Transport.send(message);
			}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}


	
	/*
	 * Send Chief Lab User Mail to Admin Start
	 * 
	 */
	
	public static boolean  chiefLabFeedbackMailToAdmin(final String mailSubject,String msg,User user ) {
		try {
			String location= user.getOfficeAddress();
			String name=user.getEmpName(); 
			String cpfno=user.getCpfNo();
			String fromEmail=user.getEmailIdOfficial();
			String sToEmailID = user.getEmailIdOfficial();
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST = szResBundl.getString("SMTP_HOST").toString().trim();
			final String username = szResBundl.getString("Username").toString().trim();
			final String password = szResBundl.getString("Password").toString().trim();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			String sFinalMsg = "";

			String MailFrom = fromEmail!=null?fromEmail:szResBundl.getString("Username").toString().trim();
			String[] to = { sToEmailID};//"awal_hk@ongc.co.in","mukherjee_d@ongc.co.in", "s.singh@velocis.co.in"
			javax.mail.internet.InternetAddress[] toAddress = new javax.mail.internet.InternetAddress[to.length];
			 // To get the array of toaddresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new javax.mail.internet.InternetAddress(to[i]);
            }
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				//message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				message.addRecipients(javax.mail.Message.RecipientType.TO, toAddress);

				if (mailSubject.toLowerCase().contains("domain expertise")) {
					message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("bpsingh7412@gmail.com"));
				}
				message.setSubject(mailSubject);
				String MailBody = "<html>" + "<body>" + "<table>" + "<tr>"
						+ "<td>Dear Admin</td>" + "</tr><td></td><tr>"
						+ "</tr><td></td>" + "<tr>"
						+ "<td><b>"+name+" has submitted ChiefLab Feedback Details </b></td>" 
						+ "</tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td>This is system generated Mail</td></tr>"
						+ "</table>" + "</body>" + "</html>";

				htmlPart.setDataHandler(new DataHandler(MailBody, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				message.setSentDate(new Date());

				javax.mail.Transport.send(message);
			}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	
	
	
	/*****************************************************************************
	 * 
	 * @param mailSubject
	 * @param Msg
	 * @param sFromEmailID
	 * @param sToEmailID
	 * @return
	 ***************************************************************************/
	public boolean sendEmail1(final String mailSubject, final String Msg,
			String sFromEmailID, String sToEmailID) {
		try {
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST = szResBundl.getString("SMTP_HOST").toString().trim();
			final String username = szResBundl.getString("Username").toString().trim();
			final String password = szResBundl.getString("Password").toString().trim();
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
			String sFinalMsg = "";

			String MailFrom = sFromEmailID;
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(
						MailFrom));
				message.addRecipient(javax.mail.Message.RecipientType.TO,
						new javax.mail.internet.InternetAddress(sToEmailID));
				// message.addRecipient(javax.mail.Message.RecipientType.CC,new
				// javax.mail.internet.InternetAddress("76121@ongc.co.in"));
				// message.addRecipient(javax.mail.Message.RecipientType.CC,new
				// javax.mail.internet.InternetAddress("78619@ongc.co.in"));

				message.setSubject(mailSubject);
				// message.setText(Msg);
				htmlPart.setDataHandler(new DataHandler(Msg, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				// String MailBody ="Dear Representative<br/><br/>Name: "+ Name
				// + "<br/>Date & Time: "+ new Date() +
				// "<br/>Settlement Location: "+ Location + "<br/>Subject: "+
				// mailSubject +
				// "<br/>Message:<br/>"+mailBody+"<br/><br/>Regards<br/>"+Name;
				// message.setContent(MailBody, "text/html");
				message.setSentDate(new Date());

				javax.mail.Transport.send(message);
			}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}