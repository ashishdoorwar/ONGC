package com.ongc.liferay.sponsorship.command;

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
	        "osgi.http.whiteboard.context.path=/imageSponsorship",
	        "osgi.http.whiteboard.servlet.pattern=/blade/servletSponsorship/*"
	    },
	    service = Servlet.class
	)

public class GetComplaintImage extends HttpServlet{

	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		String fileName = request.getParameter("fileName");
		ResourceBundle szLable = ResourceBundle.getBundle("/content/sponsorship");
		response.setContentType("image/jpeg");
		System.out.println("GetComplaintImage====================>"+szLable.getString("file-path").toString().trim()+fileName+".jpg");
		File file = new File(szLable.getString("file-path").toString().trim()+fileName+".jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
