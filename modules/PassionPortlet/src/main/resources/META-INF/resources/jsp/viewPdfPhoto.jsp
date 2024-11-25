<%@page import="com.ongc.liferay.passion.dao.Impl.PassionDocDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.PassionDocDao"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.PassionPhotoDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.PassionPhotoDao"%>
<%@page import="com.ongc.liferay.passion.model.PassionPhoto"%>
<%@page import="com.ongc.liferay.passion.model.PassionDoc"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.UserDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.UserDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp"%>

<%
	String loggedUser=(String)session.getAttribute("loggedInUserCpfNo");
		System.out.println("cpf number in view photo:::::"+loggedUser);
			if(loggedUser==null || loggedUser.equalsIgnoreCase("")){
		out.println("logout");
		}else{

	
	String photoCommentsBy = null;
	String photoComments = null;
	String endrsCount=null;
	String userName=null;
	String empCPF=null;
	User user=null;
	boolean flag=false;
	
	//comment and endorse action done by logged user.
		UserDao uDao=new UserDaoImpl();
	User loggedInUser=(User)uDao.getUserByCPFNumber(loggedUser);
	
	PassionPhotoDaoImpl passionPhotoDao=new PassionPhotoDaoImpl();
	List<PassionPhoto> passionPhoto = null;
	List<PassionPhoto> passionPdfEndrsCnt = null;
	String cpf = (String)request.getAttribute("cpfNo");	
	
	user=(User)uDao.getUserByCPFNumber(cpf);
	String strPassionId=(String)request.getAttribute("passionId");
	String strSubPassionId=(String)request.getAttribute("subPassionId");
	String strPassionName=(String)request.getAttribute("passionName");
	String strSubPassionName=(String)request.getAttribute("subPassionName");

	String photoId =(String) request.getAttribute("pId");
	//String abc=request.getAttribute("url");
	//String fileName=request.getAttribute("fileName");	
	
	passionPhoto = passionPhotoDao.getPassionPdfComments(photoId);
	System.out.println(photoId+"   vvvvvvvvaaaaaaa      fileName  "+passionPhoto.size());
	flag=passionPhotoDao.checkEndorsePdf(photoId,loggedUser);
	passionPdfEndrsCnt=passionPhotoDao.getEndorseCountPdf(photoId);
	for (int i = 0; i < passionPdfEndrsCnt.size(); i++) {
		endrsCount =passionPdfEndrsCnt.get(i).getEndorsedCount();
	}
	
	
	PassionDoc prevDoc=null;
	PassionDoc nextDoc=null;
	String prevDocName="";
	String prevFileName="";
	String prevFileDesc="";
	String prevEndorse="";
	String nextDocName="";
	String nextFileName="";
	String nextFileDesc="";
	String nextEndorse="";
	PassionDocDaoImpl pdd=new PassionDocDaoImpl();
	List al1=new ArrayList();
	if(!strPassionId.equalsIgnoreCase("0") && !strSubPassionId.equalsIgnoreCase("0")){
	List<PassionDoc> passionDoc=pdd.getPassionDocumentFile(cpf, strPassionId, strSubPassionId);
	
	Iterator itr=passionDoc.iterator();
	while(itr.hasNext()){
		PassionDoc doc=(PassionDoc)itr.next();
		al1.add(doc.getDocId());
		
	}}
	int k=0;
	if(al1.size()!=0)
	k=al1.indexOf(photoId);
	
	String prevDocId="";
	String nextDocId="";
	if(k>=1){
		prevDocId=(String)al1.get(k-1);
		System.out.println("prev"+al1.get(k-1));
		prevDoc=pdd.getDocDetail(prevDocId);
		prevDocName=prevDoc.getDocName();
		prevFileName=prevDoc.getDocFileName();
		prevFileDesc=prevDoc.getDocDescription();
		prevEndorse=prevDoc.getEndorsedCount();

		prevDocName=prevDocName.replace("'", "\\'").replace("\"", "&quot;");

		if(prevFileDesc!=null){

		prevFileDesc=prevFileDesc.replace("'", "\\'").replace("\"", "&quot;");

		}

	}
	if(k<=al1.size()-2){
		nextDocId=(String)al1.get(k+1);
		System.out.println("next"+al1.get(k+1));
		nextDoc=pdd.getDocDetail(nextDocId);
		nextDocName=nextDoc.getDocName();
		nextFileName=nextDoc.getDocFileName();
		nextFileDesc=nextDoc.getDocDescription();
		nextEndorse=nextDoc.getEndorsedCount();

		
		nextDocName=nextDocName.replace("'", "\\'").replace("\"", "&quot;");

		if(nextFileDesc!=null){

		nextFileDesc=nextFileDesc.replace("'", "\\'").replace("\"", "&quot;");

		}

	}
	
	
%>
<style>
	.blocker{
	position:absolute;
	top: 0;
	left: 0;
	width: 309px;
	height: 462px;
	text-align: center;
	z-index: 1010;	
	background:rgba(0, 0, 0, 0.42);
	color: #fff;
	font-family:Verdana, Geneva, sans-serif;
	font-size: 10px;
	display:none;
}
.blocker img{
	margin-top: 190px;	
}
	
	</style>

<script type="text/javascript">
/*
	function updateEndorse() {
	alert(1111);
		var params="";
		alert(5555);
		var _ajaxCall = new AjaxRequest('/wps/PA_ONGC_Report_M/photoEndorse.action',params, 'POST', 'endorseCount');
	}
*/
	
function updateEndorse(){
	$("#Endorse").attr('disabled','disabled');
         $(".blocker").show();
	$.ajax( {
	      type: "POST",
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/viewEndorsedPdf.jsp", 
	        data:'pId='+'<%=photoId%>'+'&cpf='+'<%=loggedUser%>'+'',
	      		success: function( response ) {
                        $(".blocker").hide();
			var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

	    	  $("#endorseCount").html(response);
	    	   $("#Endorse").html('<a class="endorsedbtn">Endorsed</a>');
		    $("#carousel_ulpop").append('<li><a href="viewProfile?empCpf=<%=cpf%>"><img style="align:center" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=loggedUser%>" alt="" height="30px" width="30px" title="you"></a> </li>');	
			}
	      }
	} );
}

function viewNextPdf(pdfFilename,pId,itemCaption,pDesc,pEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
	
	$.ajax({
      type: "POST",
      url:  "/wps/PA_ONGC_Passion/jspPages/web/viewPdfPhoto.jsp", 
      data:'fileName='+pdfFilename+'&pId='+pId+'&itemCaption='+itemCaption+'&photoDesc='+pDesc+'&photoEndrs='+pEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
      success: function( response ) {
    	 var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{
 
     $("#popupContent").html(response);
		
      }}
    });
    }

function validatePdfData(){
	var messagString="";
	messagString=ErroPdfMessage(messagString);
	if(messagString!="") {
		alert(messagString);
		return false;
	}
	return true;
}
function ErroPdfMessage(messagString) {
	//var nameSpchar=/^[a-zA-Z0-9.-.,-,---_-_(-()-) ]*$/;
        var nameSpchar = /^[0-9a-zA-Z\s\r\n@!#\$\^*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;	
 	messagString=FieldValiDationWithFocuse("photoComments",nameSpchar,"Special Characters are not allowed.","Please Enter Comments", messagString);
	if(trim(messagString)!="")
	return messagString ;
	return messagString ;
}

function FieldValiDationWithFocuse(FieldId,RegXp,RegMsg,BlankMesg, messagString)
{	
   var FieldValue=document.getElementById(FieldId).value; 
  
	   if(BlankMesg!="")
	   {
	       if(trim(FieldValue)==""){
	    	  
	    	   document.getElementById(FieldId).focus();
	           messagString=messagString+BlankMesg;
	           return messagString;
	         }
	   }
	   
	   if(FieldValue.search(RegXp)==-1)
		{
		  document.getElementById(FieldId).focus();
		  messagString=messagString+RegMsg;
		  return messagString;
		}
	return messagString;
}

function trim(s) {
	var l = 0;
	var r = s.length - 1;
	while (l < s.length && s[l] == ' ') {
		l++;
	}
	while (r > l && s[r] == ' ') {
		r -= 1;
	}
	return s.substring(l, r + 1);
}

function photoComments(){
	var a=document.getElementById("photoComments").value;
	if(!validatePdfData()){
		return false;
	}
        $(".blocker").show();
	$.ajax( {

	      type: "POST",
	      url:  "/wps/PA_ONGC_Passion/jspPages/web/viewCommentsPdf.jsp", 
	      data:'pId='+'<%=photoId%>'+'&cmnts='+a+'&cpf='+'<%=loggedUser%>'+'',
	      //dataType: "html",
	      success: function( response ) {	
                $(".blocker").hide();
			var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{
    	 
	    	  $("#commentsPopup").html(""+response);
	    	  $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="photoComments" value="" id="photoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="photoComments()">Post</span>');
	      }}
	} );
}
	
	function deleteCmnt(sno){
		var r = confirm("Are You Sure, you want to delete!");
		if (r == false) {
			return false;
		} else {
		 
		$(".blocker").show();
		$.ajax( {

		      type: "POST",
		      url:  "/wps/PA_ONGC_Passion/jspPages/web/deleteCommentsPdf.jsp", 
		      data:'pId='+'<%=photoId%>'+'&cmntId='+sno+'&cpf='+'<%=loggedUser%>'+'',
		      //dataType: "html",
		      success: function( response ) {
                        $(".blocker").hide();	
				var abc=$.trim(response );
			if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
			}else{
    	 
		    	  $("#commentsPopup").html(""+response);
		    	  $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="photoComments" value="" id="photoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="photoComments()">Post</span>');
		      }}
		} );
	}
	}
	</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Pdf</title>
</head>
<body>
<div class="popup"></div>

<div class="popupBox">
<div id="popupContent">
	<div class="img">
	
	<%
	int ext=((String)request.getAttribute("fileName")).lastIndexOf(".");
	String extnsn=((String)request.getAttribute("fileName")).substring(ext+1);
	if(extnsn.equalsIgnoreCase("pdf")){
	
	%>
	<iframe src="<%=request.getContextPath()%>/jspPages/passionShowPdf.jsp?fileName=<%=request.getAttribute("fileName")%>&number=<%=cpf %>" style="width:650px; height:470px;" frameborder="0"></iframe>
	<%}else{ %>
	<a href="<%=request.getContextPath()%>/jspPages/passionShowDoc.jsp?fileName=<%=request.getAttribute("fileName")%>&number=<%=cpf %>" style="width:650px; height:470px;"><img  style="width:100px !important;" class="doc-icon" src="<%=request.getContextPath()%>/images/word-icon.png" alt="Document" title="Click here to Download"></img></a>
	<%} %>
		<%if(nextDoc!=null){ %>
		<div id="btn-next" style="position:absolute;">
		<img id="nextFile" onclick="viewNextPdf('<%=nextFileName %>','<%=nextDocId %>','<%=nextDocName%>','<%=nextFileDesc%>','<%=nextEndorse %>','<%=cpf %>','<%=strPassionId %>','<%=strSubPassionId %>','<%=strPassionName %>','<%=strSubPassionName %>');" src="<%=request.getContextPath()%>/images/right.png" width="24" height="18" alt="image"/>
		</div>
		<%} if(prevDoc!=null){ %>
		<div id="btn-prev" style="position:absolute; left:-10px;">
		<img id="prevFile" onclick="viewNextPdf('<%=prevFileName %>','<%=prevDocId %>','<%=prevDocName%>','<%=prevFileDesc%>','<%=prevEndorse %>','<%=cpf %>','<%=strPassionId %>','<%=strSubPassionId %>','<%=strPassionName %>','<%=strSubPassionName %>');" src="<%=request.getContextPath()%>/images/left.png" width="24" height="18" alt="image"/>
		</div>
	<%} %>
	</div>
    <div class="comentpop">
    <div class="blocker">
  <img width="40" src="/wps/PA_ONGC_Passion/images/processing.gif" />
<p>Processing...</p>
    </div>
    	<div class="">
        	<div class="user"><img height="50px" width="50px" src="<%=request.getContextPath()%>/jspPages/web/viewUserProfile.jsp?empCPF=<%=cpf%>" alt=""  /></div>
        	<h2 style="padding-bottom: 0 !important;"><a href="/wps/portal/PassionProfile?empCpf=<%=user.getCpfNo()%>"><%=user.getEmpName()%></a><span><%=strPassionName%>&nbsp;|&nbsp;<%=strSubPassionName%></span></h2>    
		            
        </div>
        
        <div class="">
        	<h3><%=request.getAttribute("itemCaption")%></h3>
<% String description=(String)request.getAttribute("photoDesc"); %>
            <p style="overflow-y:auto; height:40px;"><%=(description.equalsIgnoreCase("null"))?"":description%></p>

                <%
if(!loggedUser.equalsIgnoreCase(cpf)){
if (flag == false) {
%>
<div id="Endorse"><a onclick="updateEndorse()" class="endorsebtn" href="#">Endorse</a></div>
<%
} else {
%>
<div id="Endorse"><a class="endorsedbtn">Endorsed</a></div>
<%
}}
%>
<div style="margin:10px 0;"><small>Endorse By</small></div>
</div>
        
        <div class="endorseBox">
        	<span class="numbers" id="endorseCount"><%=endrsCount%></span>
            
            <div class="thumbnailer">
	<div id="carousel_containerpop">
     <div id="left_scrollpop">
  <a title="Slide left" onclick="slide('left');"><img src="<%=request.getContextPath()%>/images/left-arrow.jpg" alt="left arrow"></a></div>
            <div id="carousel_innerpop">
            <ul id="carousel_ulpop" style="left: 0px; ">
            	<%
	ArrayList<User> al = passionPhotoDao.getPdfEndorsedByUserName(photoId);
	System.out.println(" al al ::::::::   "+al.size());
	for (int i = 0; i < al.size(); i++) {
		user = al.get(i);
		userName = user.getEmpName();
		empCPF = user.getCpfNo();
		if (!endrsCount.equals("0")) {
%>
<li>
<a href="/wps/portal/PassionProfile?empCpf=<%=empCPF%>"><img height="30px" width="30px" style="align:center" src="<%=request.getContextPath()%>/jspPages/web/viewUserProfile.jsp?empCPF=<%=empCPF%>" alt="" title="<%=userName%>" /></a>
</li>
<%
}
%>
<%
}
%>
            </ul>
            </div>            
            <div id="right_scrollpop"><a title="Slide right" onclick="slide('right');"><img src="/wps/PA_ONGC_Passion/images/right-arrow.jpg" alt="right arrow"></a></div>
            </div>
            </div>           
        </div>        
        <div class="" id="commentsText">
        	<input maxlength="100" type="text" placeholder="Your Comment" name="photoComments" id="photoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="photoComments()">Post</span>
        </div>
        <small>Comments</small>
        <div class="parent"> 
        <div class="scrollbar"></div>
        <div class="scrollable">        
        <div id="commentsPopup">        
        <%
//System.out.println("helloooooooooooooooooooooooooo   "+al.size());
		if (passionPhoto.size() > 0) {
		for (int i = 0; i < passionPhoto.size(); i++) {
if (passionPhoto.get(i).getPhotoComments() != null) {
%>
<div class="commentBox">
<div class="img">
<img height="50px" width="50px" src="/http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=passionPhoto.get(i).getEndorsedByCpf()%>" alt=""  />
</div>
<div class="right">
<a href="/wps/portal/PassionProfile?empCpf=<%=passionPhoto.get(i).getEndorsedByCpf()%>"><%=passionPhoto.get(i).getEndorsedBy()%></a>
<p><%=passionPhoto.get(i).getPhotoComments()%>
<%
if(passionPhoto.get(i).getEndorsedByCpf().equalsIgnoreCase(loggedUser)){
%>
&nbsp;&nbsp;&nbsp;<span style="float:right;"><a style="cursor:pointer;" onclick="deleteCmnt('<%= passionPhoto.get(i).getCommentId()%>')"><img alt="" src="images/delete.jpg"/> Delete</a></span>
<%
}
%>
</p>
</div>
</div>
<%
}}}else{ %>
<table>
<tr>
<td>No Comments</td>
</tr>
</table>
<%} %>
  </div>        
    </div> </div> </div>  </div>  </div>
</body></html>

<%} %>
