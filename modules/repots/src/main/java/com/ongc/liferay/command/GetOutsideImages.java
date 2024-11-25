package com.ongc.liferay.command;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"osgi.http.whiteboard.context.path=/imageRepots",
				"osgi.http.whiteboard.servlet.pattern=/blade/servletProfileRepots/*"
		},
		service = Servlet.class
		)

public class GetOutsideImages extends HttpServlet{


	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		String cpfnumber = request.getParameter("cpfno");
		ResourceBundle szLable = ResourceBundle.getBundle("/content/Repots");
		response.setContentType("image/jpeg");
		System.out.println(cpfnumber);
		File file = new File(szLable.getString("profileFilePath").toString().trim()+cpfnumber+".jpg");
		if(file.exists()){}else {file = new File(szLable.getString("profileFilePath").toString().trim()+"default.jpg");}
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "jpg", response.getOutputStream());

	}
}
