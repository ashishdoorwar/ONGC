package com.ongc.liferay.vigilance.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * 
 * @author SD0057937
 */
public class Captcha extends HttpServlet {
	private int height = 30;

	private int width = 135;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req,resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req,resp);
	}
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		response.setContentType("image/jpeg");
		String paramKey = request.getParameter("paramKey");
	
		try {

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
		//	image.set
			Graphics2D graphics2D = image.createGraphics();
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			String ch = token.substring(0, 6);
			Color c = new Color(0.6662f, 0.4569f, 0.3232f);
			//Color c=Color.WHITE;
			GradientPaint gp = new GradientPaint(30, 30, c, 15, 25,
					Color.white, true);
			graphics2D.setPaint(gp);
			Font font = new Font("Verdana", Font.CENTER_BASELINE, 26);
			graphics2D.setFont(font);
			graphics2D.drawString(ch, 10, 23);
			graphics2D.dispose();
			HttpSession session = request.getSession(true);
			
			if (paramKey == null || paramKey.length() < 1) {
				session.removeAttribute("CAPTCHA_KEY");
				session.setAttribute("CAPTCHA_KEY", ch);
			} else {
				session.removeAttribute(paramKey);
				session.setAttribute(paramKey, ch);
			//	Cookie cookie = new Cookie(paramKey, Base64Encoder.encode(ch));
				Cookie cookie = new Cookie(paramKey, ch);
				response.addCookie(cookie);
			}
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(image, "jpeg", outputStream);
			outputStream.flush();
			outputStream.close();

		} finally {

		}
	}

	

	public  void processRequest2(int xy) throws ServletException, IOException {
	
		try {

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			String ch = token.substring(0, 6);
			Color c = new Color(0.6662f, 0.4569f, 0.3232f);
			GradientPaint gp = new GradientPaint(30, 30, c, 15, 25,
					Color.white, true);
			graphics2D.setPaint(gp);
			Font font = new Font("Verdana", Font.CENTER_BASELINE, 26);
			graphics2D.setFont(font);
			graphics2D.drawString(ch, 10, 23);
			graphics2D.dispose();
			
			OutputStream outputStream = new FileOutputStream("f:/capimg/cap_"+xy+".jpeg");
			ImageIO.write(image, "jpeg", outputStream);
			outputStream.flush();
			outputStream.close();

		} finally {

		}
	}
public static void main(String[] args)throws Exception {
	Captcha c =new Captcha();
	
	for(int i=0;i<500;i++){
		c.processRequest2(i);
	}
	
	//system.out.println("Finish");
}
	
}
