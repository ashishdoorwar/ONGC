package com.ongc.liferay.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	    immediate = true,
	    property = {
	        "osgi.http.whiteboard.context.path=/pdf",
	        "osgi.http.whiteboard.servlet.pattern=/blade/pdfservlet/*"
	    },
	    service = Servlet.class
	)

public class GetOutsidePdf extends HttpServlet{

	ResourceBundle szLable = ResourceBundle.getBundle("/content/Repots");
	@Override
	protected void doGet(
	        HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
		
		response.setContentType("application/pdf");		
		response.setHeader("Content-Disposition", "inline; filename="+request.getParameter("pdfname")+";");
		OutputStream out=response.getOutputStream();
		try {
		FileInputStream in = new FileInputStream(szLable.getString("ordercircularPdfFilePath").toString().trim()+request.getParameter("pdfname"));
		int content;
		while((content=in.read())!=-1) {
			out.write(content);
		}}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		out.close();
		
		
	}
	
	
	

}
