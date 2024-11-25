package com.ongc.liferay.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ongc.liferay.constants.RepotsPortletKeys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=SMALL Report",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=PDF Pledge",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + RepotsPortletKeys.PDF,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class PdfPortlet extends MVCPortlet {
	
    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
    		throws IOException, PortletException {
    	
    	 try{
    		 PDDocument doc = new PDDocument();
             PDPage page = new PDPage();
             doc.addPage(page);
             PDPageContentStream contentStream = new PDPageContentStream(doc, page);
             contentStream.beginText();
             contentStream.setFont(PDType1Font.HELVETICA, 12);
             contentStream.newLineAtOffset(100, 700);
             contentStream.showText("Checking outer file");
             contentStream.endText();
             contentStream.close();

             // embedded files are stored in a named tree
             PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();

             // first create the file specification, which holds the embedded file
             PDComplexFileSpecification fs = new PDComplexFileSpecification();
             fs.setFile("D:/ONGC/example-document.txt");

             // create a dummy file stream, this would probably normally be a FileInputStream
             byte[] data = "This is the contents of the embedded file".getBytes("ISO-8859-1");
             ByteArrayInputStream fakeFile = new ByteArrayInputStream(data);

             // now lets some of the optional parameters
             PDEmbeddedFile ef = new PDEmbeddedFile(doc, fakeFile);
             ef.setSubtype("text/plain");
             ef.setSize(data.length);
             ef.setCreationDate(Calendar.getInstance());
             fs.setEmbeddedFile(ef);

             // create a new tree node and add the embedded file 
             PDEmbeddedFilesNameTreeNode treeNode = new PDEmbeddedFilesNameTreeNode();
             treeNode.setNames(Collections.singletonMap("My first attachment",  fs));

             // add the new node as kid to the root node
             List<PDEmbeddedFilesNameTreeNode> kids = new ArrayList<PDEmbeddedFilesNameTreeNode>();
             kids.add(treeNode);
             efTree.setKids(kids);

             // add the tree to the document catalog
             PDDocumentNameDictionary names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
             names.setEmbeddedFiles(efTree);
             doc.getDocumentCatalog().setNames(names);
            
             doc.save(new File("D:/ONGC/checkAttachment.pdf"));
         } catch (IOException e){
             System.err.println("Exception while trying to create pdf document - " + e);
         }
    	
        super.doView(renderRequest, renderResponse);
    }
    
}
