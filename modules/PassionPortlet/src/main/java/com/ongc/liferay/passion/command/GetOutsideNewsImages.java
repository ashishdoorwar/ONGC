package com.ongc.liferay.passion.command;

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
	        "osgi.http.whiteboard.context.path=/newsImageServlet",
	        "osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
	    },
	    service = Servlet.class
	)

public class GetOutsideNewsImages extends HttpServlet{
	

	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		
		String newId = request.getParameter("newId");
		ResourceBundle szLable = ResourceBundle.getBundle("/content/passion");
		System.out.println(newId);
		response.setContentType("image/jpeg");
		File file = new File(szLable.getString("newFilePath").toString().trim()+newId+".jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "jpg", response.getOutputStream());
		
		
        
    }
	
	

}
