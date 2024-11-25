package com.ongc.liferay.dao;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.util.RepotsConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class PledgeDao {
	

	private static final Log log = LogFactoryUtil.getLog(PledgeDao.class);
	ResourceBundle szResBundl = ResourceBundle.getBundle("/content/mailProperties");

	public boolean submitPledge(String cpf, String type) {
		String query = "INSERT INTO RPTT_PLEDGE(PLEDGE_ID,PLEDGE_BY,PLEDGE_TYPE,PLEDGE_ON) VALUES(?, ?, ?,NOW())";
		boolean flag = false;
		int id = getMaxIdFromTable("rptt_pledge", "pledge_id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, cpf);
			pstmt.setString(3, type);
			log.info("submitPledge:::::::::::::: "+pstmt);
			flag = pstmt.executeUpdate() > 0;

		} catch (Exception ex) {
			log.info("submitPledge Exception " + ex + ":: " + flag);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}

	public boolean checkCpf(String cpf, String type) {
		String query = "SELECT * FROM RPTT_PLEDGE WHERE PLEDGE_BY=? AND PLEDGE_TYPE=?";
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setString(2, type);
			set = pstmt.executeQuery();
			log.info("**************checkCpf:"+pstmt);
			flag = set.next();

		} catch (Exception ex) {
			log.info("checkCpf in pledgedao Exception " + ex + ":: "+ flag);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}

	public int getPledgeCount(String type) {
		String query = "SELECT COUNT(DISTINCT (PLEDGE_BY)) FROM RPTT_PLEDGE WHERE  PLEDGE_TYPE=?";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type);
			set = pstmt.executeQuery();
			log.info("save_record "+pstmt);
			while (set.next()) {
				count = set.getInt(1);
			}

		} catch (Exception ex) {
			log.info("getPledgeCount in pledgedao Exception " + ex);
		} finally {
			DatasourceConnection.closeConnection( conn,pstmt);
		}
		return count;
	}

	private int getMaxIdFromTable(String tableName, String colName) {
		int id = 0;

		String query = "select max(" + colName + ") from " + tableName;
		Connection connection = null;
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
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return ++id;
	}

	public boolean createPDF(String usName, String pdfFileName,String htmlInPutFileName, String htmlOutPutFileName) {
		log.info("createPDF ");
		log.info("pdfFileName: "+pdfFileName);
		log.info("htmlInPutFileName :"+htmlInPutFileName);
		log.info("htmlOutPutFileName: "+htmlOutPutFileName);
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			String crrDt=sdf.format(new Date());
			log.info("Here createPDF " + usName);
			if (usName != null) {
				usName = usName.toUpperCase();
			}
			log.info("Here 1 createPDF " + usName);
			// InputStream ips = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\certificate\\cert.html"));

			InputStream ips = new FileInputStream(new File(htmlInPutFileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(ips));
			StringBuffer flBuff = new StringBuffer();
			 log.info("Here 3");
			int flag = 0;
			while ((flag = br.read()) != -1) {
				flBuff.append((char) flag);
			}
			String s = flBuff.toString();
			s = s.replaceAll("~NAME~", usName);
			s = s.replaceAll("~crrDt~", crrDt);
			StringBuffer cafBuff = new StringBuffer(s);
			FileOutputStream fout = new FileOutputStream(htmlOutPutFileName);
			fout.write(cafBuff.toString().getBytes());
			//Document document = new Document(PageSize.A10, 50.0F, 50.0F, 50.0F,50.0F);
			Document document = new Document(PageSize.A4, 50.0F, 50.0F, 50.0F,50.0F);
			 log.info("Here 4");
			ITextRenderer renderer = new ITextRenderer();
			 log.info("Here 5");
			File url = new File(htmlOutPutFileName);
			renderer.setDocument(url);
			renderer.layout();
			OutputStream os = new FileOutputStream(pdfFileName);
			PdfWriter.getInstance(document, os);
			renderer.createPDF(os);
			os.close();
			log.info(" createPDF PDF Done ");
			return true;
		}
//		catch (DocumentException e) {
//			log.info("DocumentException in createPDF  " + e);
//			e.printStackTrace();
//		}
		catch (IOException e) {
			log.info("IOException in createPDF  " + e);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Exception in createPDF  " + e);
			e.printStackTrace();
		}
		return false;

	}

	public boolean sendEmail(final String mailSubject,String msg, String toName,String sToEmailID, String filepath) {
		 log.info("In sendMail Method in :::"+sToEmailID);
		try {

			String SMTP_HOST = szResBundl.getString("SMTP_HOST").toString().trim();
			final String username = szResBundl.getString("Username").toString().trim();
			final String password = szResBundl.getString("Password").toString().trim();
			String uname = toName.toUpperCase();

			
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

			String MailFrom = "noreply@ongc.co.in";
			if (sToEmailID != null && sToEmailID.length() > 1) {
				message.setFrom(new javax.mail.internet.InternetAddress(MailFrom));
				message.addRecipient(javax.mail.Message.RecipientType.TO,
						new javax.mail.internet.InternetAddress(sToEmailID));
				message.setSubject(mailSubject);
				htmlPart.setDataHandler(new DataHandler(msg, "text/html"));
				multipart.addBodyPart(htmlPart);
				// message.setContent(multipart);

				if (filepath != null && filepath.length() > 1) {
					FileDataSource filesrc = new FileDataSource(filepath);
					attachmentPart.setDataHandler(new DataHandler(filesrc));
					attachmentPart.setFileName(filesrc.getName());
					multipart.addBodyPart(attachmentPart);
				}

				message.setContent(multipart);
				message.setSentDate(new Date());
				javax.mail.Transport.send(message);
				log.info("Mail successfully send in pledge dao..");
			}
			return true;
		} catch (MessagingException ex) {
			log.info(ex + " Exception in Sending Mail in Pledge");
		}
		return false;
	}

	public boolean sendPassOnSMS(String MSISDN,String sUserMsg) {
		log.info("sendPassOnSMS");
		log.info("sUserMsg:"+sUserMsg);
		String response = "";
		BufferedReader in = null;
		boolean flag = false;

		try {
			System.getProperties().put("http.nonProxyHosts",RepotsConstant.SMS_HOST);

			String strEncUser = RepotsConstant.SMS_USER;
			String strEncPass = RepotsConstant.SMS_PASS;
			StringBuffer smsUrl = new StringBuffer();

			smsUrl.append("http://").append(RepotsConstant.SMS_HOST);
			smsUrl.append(":").append(RepotsConstant.SMSC_PORT);
			smsUrl.append("/cgi-bin/sendsms?username=");
			smsUrl.append(strEncUser).append("&password=").append(strEncPass);
			smsUrl.append("&from=ONGC&to=");
			//smsUrl.append(URLEncoder.encode(MSISDN, "UTF-8")).append("&text=").append(URLEncoder.encode(sUserMsg, "UTF-8"));
			smsUrl.append(URLEncoder.encode(MSISDN , "UTF-8")).append("&text=").append(URLEncoder.encode(sUserMsg , "UTF-8")).append("&meta-data=%3Fsmpp%3FEntityID%3D1001186049255234740%26ContentID%3D1407163783512229017");

			URL url = new URL(removeWhiteSpaces(smsUrl.toString()));
			log.info(" Test :: " + smsUrl.toString());
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String tempStr = null;
			while ((tempStr = in.readLine()) != null) {
				response = response + tempStr;
			}

			log.info("Status - Message send To User: " + response);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e1) {
				log.info(e1.toString());
			}
		}
		return flag;
	}

	private String removeWhiteSpaces(String smsText) {
		int i;
		do {
			i = smsText.indexOf(" ");
			if (i > 0) {
				smsText = smsText.substring(0, i) + "%20"
						+ smsText.substring(i + 1);
			}
		} while (i > 0);
		return smsText;
	}
	
//	public static void main(String []args){
//		PledgeDao dao=new PledgeDao();
//		//String pdfFileName="D:/ONGC_BACKUP/html/Certificate/Certificate.pdf";
//		String pdfFileName = "D:/ONGC_BACKUP/pdf/" + "username_A4"+ ".pdf";
//		String htmlInPutFileName = "D:/ONGC_BACKUP/safetypledge/template/cert.html";
//		String htmlOutPutFileName = "D:/ONGC_BACKUP/safetypledge/template/" + "user_newA4_output"+ ".html";
//		dao.createPDF("user name", pdfFileName, htmlInPutFileName, htmlOutPutFileName);
//	}

}
