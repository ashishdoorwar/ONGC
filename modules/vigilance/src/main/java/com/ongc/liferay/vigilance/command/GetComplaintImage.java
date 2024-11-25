package com.ongc.liferay.vigilance.command;

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
	        "osgi.http.whiteboard.context.path=/imageComplaint",
	        "osgi.http.whiteboard.servlet.pattern=/blade/servletComplaint/*"
	    },
	    service = Servlet.class
	)

public class GetComplaintImage extends HttpServlet{
	

	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		
		String fileName = request.getParameter("fileName");
		ResourceBundle szLable = ResourceBundle.getBundle("/content/vigilance");
		response.setContentType("image/jpeg");
		File file = new File(szLable.getString("complaintFilePath").toString().trim()+fileName+".jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
