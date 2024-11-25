<%@page import="com.ongc.liferay.passion.model.PassionPhoto"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.PassionPhotoDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.PassionPhotoDao"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	String cpf=(String)request.getSession().getAttribute("loggedInUserCpfNo");
		if(cpf==null || cpf.equalsIgnoreCase("")){
	out.print("logout");
	}else{


		PassionPhotoDaoImpl passionPhotoDao=new PassionPhotoDaoImpl();
	List<PassionPhoto> passionPdfEndrsCnt = null;
	String pdfEndrsCount=null;
		boolean flage=false;
		String photoId = request.getParameter("pId");
	flage=passionPhotoDao.updateEndorsePdfCount(photoId,cpf);
	passionPdfEndrsCnt=passionPhotoDao.getEndorsePdfCount(photoId);
	for (int i = 0; i < passionPdfEndrsCnt.size(); i++) {
		pdfEndrsCount =passionPdfEndrsCnt.get(i).getEndorsedCount();
	}
	
%>
<%=pdfEndrsCount%>
<%}%>