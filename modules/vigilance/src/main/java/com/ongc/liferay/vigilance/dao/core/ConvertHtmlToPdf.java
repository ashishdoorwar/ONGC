package com.ongc.liferay.vigilance.dao.core;

import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker; 
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("deprecation")

public class ConvertHtmlToPdf {
	 // itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
	  public static void toPdf(String  pdfFileName,String html ) {
	    try {
	      Document document = new Document(PageSize.LETTER);
	      PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
	      document.open();
	      @SuppressWarnings("deprecation")
		HTMLWorker htmlWorker = new HTMLWorker(document);
	      htmlWorker.parse(new StringReader(html));
	      document.close();
	      ////system.out.println("ConvertHtmlToPdf Done");
	      }
	    catch (Exception e) {
	    	 // //system.out.println("ConvertHtmlToPdf ");
	      e.printStackTrace();
	    }
	  }
}