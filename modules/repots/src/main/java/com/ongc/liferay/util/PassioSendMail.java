package com.ongc.liferay.util;

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

import javax.mail.Message;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class PassioSendMail {
	//static Logger szLogger = Logger.getLogger(PassioSendMail.class);
	ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
	

	public boolean sendEmail(final String mailSubject, final String Msg,String sToEmailID) {
		try {
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST=szResBundl.getString("SMTP_HOST").toString().trim();
			final	String username=szResBundl.getString("Username").toString().trim();
			final String password=szResBundl.getString("Password").toString().trim();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart(); 
			MimeBodyPart htmlPart = new MimeBodyPart();
			String sFinalMsg="";
			
			String  MailFrom= szResBundl.getString("Username").toString().trim();
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				if(mailSubject.toLowerCase().contains("domain expertise"))
				{
				
				message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("mithun.7790@gmail.com"));
				
				}
				//message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("mithun.7790@gmail.com"));
				//message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("78619@ongc.co.in"));
				
				message.setSubject(mailSubject);
				//message.setText(Msg);
				htmlPart.setDataHandler(new DataHandler(Msg, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				// String MailBody ="Dear Representative<br/><br/>Name: "+ Name + "<br/>Date & Time: "+ new Date() + "<br/>Settlement Location: "+ Location + "<br/>Subject: "+ mailSubject + "<br/>Message:<br/>"+mailBody+"<br/><br/>Regards<br/>"+Name;
				//message.setContent(MailBody, "text/html");
				message.setSentDate(new Date());

				javax.mail.Transport.send(message);
			}
			return true;     
		} catch (MessagingException ex) {
		ex.printStackTrace();
		}
		return false;
	}
	
	public boolean sendEmail1(final String mailSubject, final String Msg,String sFromEmailID,String sToEmailID) {
		try {
			ResourceBundle szResBundl = ResourceBundle.getBundle("mailProperties");
			String SMTP_HOST=szResBundl.getString("SMTP_HOST").toString().trim();
			final	String username=szResBundl.getString("Username").toString().trim();
			final String password=szResBundl.getString("Password").toString().trim();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			Session mailersession = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			Message message = new MimeMessage(mailersession);
			MimeMultipart multipart = new MimeMultipart(); 
			MimeBodyPart htmlPart = new MimeBodyPart();
			String sFinalMsg="";
			
			String  MailFrom= sFromEmailID;
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				message.addRecipient(javax.mail.Message.RecipientType.TO,new javax.mail.internet.InternetAddress(sToEmailID));
				//message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("76121@ongc.co.in"));
				//message.addRecipient(javax.mail.Message.RecipientType.CC,new javax.mail.internet.InternetAddress("78619@ongc.co.in"));
				
				message.setSubject(mailSubject);
				//message.setText(Msg);
				htmlPart.setDataHandler(new DataHandler(Msg, "text/html"));
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);
				// String MailBody ="Dear Representative<br/><br/>Name: "+ Name + "<br/>Date & Time: "+ new Date() + "<br/>Settlement Location: "+ Location + "<br/>Subject: "+ mailSubject + "<br/>Message:<br/>"+mailBody+"<br/><br/>Regards<br/>"+Name;
				//message.setContent(MailBody, "text/html");
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
