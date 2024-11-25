<%@page import="com.ongc.liferay.vigilance.dao.core.ConvertHtmlToPdf"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="com.ongc.liferay.vigilance.dao.Impl.ComplaintManagementDaoImpl"%>
<%@page import="com.ongc.liferay.vigilance.dao.ComplaintManagementDao"%>
<%@page import="com.ongc.liferay.vigilance.model.ComplaintAttachment"%>
<%@page import="java.io.*"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%!
private String getContentType(String fileName){
		if(fileName.toUpperCase().contains(".PNG"))
		return "image/png";
		else if(fileName.toUpperCase().contains(".JPG"))
		return "image/jpeg";
		else if(fileName.toUpperCase().contains(".JPEG"))
			return "image/jpeg";
		else if(fileName.toUpperCase().contains(".BMP"))
				return "image/bmp";
		else if(fileName.toUpperCase().contains(".PDF"))
			return "application/pdf";
			else
				
			return "";
	}
 %>
<%
//ComplaintAttachment attachment=new ComplaintAttachment();

String attId=request.getParameter("compId");
if(attId==null)
attId="1";

System.out.println("Download JSP 45 :::    "+attId);
//attachment.setAttachmentId(Integer.parseInt(attId));
ComplaintManagementDao comp=new ComplaintManagementDaoImpl();
	VigilanceComplaint complaint=comp.getComplaintDetails(Integer.parseInt(attId));
	//attachment=comp.getAttachmentById(attachment.getAttachmentId());
	//String fileName=attachment.getFileName();
	java.util.List<ComplaintAttachment> lst=complaint.getAttachmentList();
	String attachmentLst="";
	for(int i=0;i<lst.size();i++){
	attachmentLst=attachmentLst+" "+lst.get(i).getFileName();
	}
	if(attachmentLst.equals(""))
	attachmentLst="NA";
	String cntNmae=complaint.getComplainBy().getCountry()==null?"":complaint.getComplainBy().getCountry();
	String MiddleName=complaint.getComplainBy().getMiddleName()==null?"":complaint.getComplainBy().getMiddleName();
	String TenderNumber=complaint.getTenderNumber()==null?"":complaint.getTenderNumber();
	String FirstName=complaint.getFirstName()==null?"":complaint.getFirstName();
	String lastName=complaint.getComplainBy().getMiddleName()==null?"":complaint.getComplainBy().getMiddleName();
	String Departmetn=complaint.getDepartmetn()==null?"":complaint.getDepartmetn();
	String title = complaint.getTitle()==null?"":complaint.getTitle();
	String complaintagaint = complaint.getComplaintAgainst()==null?"":complaint.getComplaintAgainst();
	String Designation=complaint.getDesignation()==null?"":complaint.getDesignation();
	String address=complaint.getWorkCentre()==null?"":complaint.getWorkCentre();
	String byCmpAddress=complaint.getComplainBy().getFirstAddress()==null?"":complaint.getComplainBy().getFirstAddress();
	String byCmpAddress2=complaint.getComplainBy().getSecondAddress()==null?"":complaint.getComplainBy().getSecondAddress();
	String byCmpState=complaint.getComplainBy().getState()==null?"":complaint.getComplainBy().getState();
	java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
	String content="<div align='center'><b>Details of Complaint No: "+complaint.getComplaintActNo()+"</b></div><br><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
		      "<tr><td><b>Complaint No:</b>&nbsp;"+complaint.getComplaintActNo()+"</td><td><b>Complaint Date:</b>&nbsp;"+dateFormat.format(complaint.getComplaintDate())+"</td></tr>"+
		      "<tr><td colspan='2'><b>&nbsp;</b></td> </tr><tr><td colspan='2'><b>Complainant::</b></td> </tr><tr>"+

		     " <td><b>Name:</b>"+complaint.getComplainBy().getFirstName()+"&nbsp; "+MiddleName+"&nbsp; "+complaint.getComplainBy().getLastName()+""+
		      "  </td>"+
		       
		        "<td><b>Phone No:</b>&nbsp;"+complaint.getComplainBy().getMobile()+" </td></tr>"+
		       " <tr><td> <b>Address:</b>&nbsp;"+byCmpAddress+"  "+byCmpAddress2+"</td>"+
		       " <td><b>Email id:</b>&nbsp;"+complaint.getComplainBy().getEmailId()+" </td>"+
		       " </tr>"+
		       "<tr><td><b>Country:</b>&nbsp;"+cntNmae+" </td><td><b>State:</b>&nbsp;"+byCmpState+" </td></tr>"+
		          "<tr><td colspan='2'><b>&nbsp;</b></td> </tr><tr><td colspan='2'><b>Complaint Against::</b></td> </tr>"+
		      "<tr>"+
		      "   <td>"+
		       " <b>Name:</b>&nbsp;"+title+" "+complaintagaint+
		       " </td>"+
		       " <td><b>Address:</b>&nbsp;"+address+" </td></tr>"+
		       " <tr><td><b>Department:</b>&nbsp;"+Departmetn+" </td>"+
		       " <td><b>Designation:</b>&nbsp;"+Designation+" </td>"+
		       " </tr>"+
		        
		       
		        "<tr><td colspan='2'><b>&nbsp;</b></td> </tr><tr><td colspan='2'><b>Complaint  Details::</b></td> </tr>"+
				"<tr>"+
		    "<td >Subject:</td>"+
		   " <td ><p> "+complaint.getComplaintSubject()+" </p></td>"+
	     " </tr>"+
		  "<tr>"+
		   " <td >Allegations/Complaint Details:</td>"+
		    "<td ><p> "+complaint.getComplaintDetail()+" </p></td>"+
	     " </tr>"+
	      " <tr>"+
		  "  <td >Attachment:</td>"+
		 "   <td><p><span>"+attachmentLst+
		       
		   "     </span></p></td>"+
	     " </tr>"+
	     "   </table>";
	     System.out.println("Download JSP 45 content :::    "+content);
	response.setContentType("application/pdf");
	response.setHeader("Content-Disposition", "attachment;filename=Print.pdf");
	ConvertHtmlToPdf.toPdf("Print.pdf",content);
	 System.out.println("Download JSP 45 content :::    "+content);
	try{
		byte[] buf = new byte[8192];   
    FileInputStream in = new FileInputStream("Print.pdf");  
    OutputStream out2 = response.getOutputStream();  
     int c;    
     while ((c = in.read(buf, 0, buf.length)) > 0) {    
    out2.write(buf, 0, c);    
     
      
       
     }    
   out2.flush();
   out2.close();
	}catch (Exception e) {
	 
		e.printStackTrace(System.out);
	}

%>