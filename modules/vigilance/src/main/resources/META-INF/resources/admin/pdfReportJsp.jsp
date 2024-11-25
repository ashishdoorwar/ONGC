<%@page import="com.ongc.liferay.vigilance.dao.core.ConvertHtmlToPdf"%>
<%@page import="com.ongc.liferay.vigilance.model.VigilanceComplaint"%>
<%@page import="java.io.*,java.util.*"%>
<link rel="stylesheet"   href='../css/style.css") %>' type="text/css">
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
String pageTitle="Complaints Reports";
String pageHeader="reports.pdf";
try{
String[] mnLst={"January","February","March","April","May","June","July","August","September","October","November","December"};
if(request.getParameter("type").equals("periodic"))
{
   pageTitle="Report From "+request.getParameter("startDate")+" to "+request.getParameter("endDate");
   pageHeader="report_"+request.getParameter("startDate")+"_"+request.getParameter("endDate")+".pdf";
}
else if(request.getParameter("type").equals("monthly"))
{
   pageTitle="Report of Month "+mnLst[Integer.parseInt(request.getParameter("month"))-1]+" "+request.getParameter("year");
   pageHeader="report_"+mnLst[Integer.parseInt(request.getParameter("month"))-1]+"_"+request.getParameter("year")+".pdf";
}
String month=request.getParameter("month");
java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
if(request.getSession().getAttribute("complaintList")!=null)
{

List<VigilanceComplaint> list=(List<VigilanceComplaint>)request.getSession().getAttribute("complaintList");
String content="";
for(int i=0;i<list.size();i++)
{
VigilanceComplaint vc=(VigilanceComplaint)list.get(i);
String action=vc.getAction()==null?"":vc.getAction();
content=content+"<tr><td width=\"40%\" align='center' valign='middle'>"+(i+1)+"</td><td align='center' valign='middle'>"+ vc.getComplaintActNo() +" </td><td align='center' valign='middle'>"+ dateFormat.format(vc.getComplaintDate()) +"</td><td align='center' valign='middle'>"+ vc.getComplaintSubject() +"</td><td align='center' valign='middle'>"+vc.getComplainBy().getTitle()+" "+vc.getComplainBy().getFirstName()+"</td><td align='center' valign='middle'>"+ vc.getComplainBy().getMobile()+"</td><td align='center' valign='middle'>"+action +"</td></tr>";
}
content="<div align='center' valign='middle'><b>"+pageTitle+"</b></div><br><table width='100%' border='1' cellspacing='2' cellpadding='2'><tr><th bgcolor='#ddd'  width=\"40%\" align='center' valign='middle'><b>S.No</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Complaint No</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Complaint Date</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Subject</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Name</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Mobile No</b></th><th bgcolor='#ddd'  align='center' valign='middle'><b>Status</b></th></tr>"+content+"</table>";
	response.setContentType("application/pdf");
	response.setHeader("Content-Disposition", "attachment;filename="+pageHeader);
	ConvertHtmlToPdf.toPdf(pageHeader,content);
	try{
		byte[] buf = new byte[8192];   
    FileInputStream in = new FileInputStream(pageHeader);  
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
}
}
catch(Exception e)
{
	e.printStackTrace(System.out);
}
%>