<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.PassionPhotoDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.PassionPhotoDao"%>
<%@page import="com.ongc.liferay.passion.model.PassionPhoto"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.UserDaoImpl"%>
<%@page import="com.ongc.liferay.passion.dao.UserDao"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp"%>
<%
	UserService uerService=new UserServiceImpl();

	String loggedUser=uerService.getUser().getCpfNo();

	System.out.println("cpf number in view photo:::::"+loggedUser);
	if(loggedUser==null || loggedUser.equalsIgnoreCase("")){
	out.println("logout");
	}else{
	String photoCommentsBy = null;
	String photoComments = null;
	String endrsCount=null;
	String userName=null;
	String empCPF=null;
	User userData=null;
	boolean flag=false;
	
	
	String strPassionId=ParamUtil.getString(request,"passionId");
	String strSubPassionId=ParamUtil.getString(request,"subPassionId");
	String photoId =  ParamUtil.getString(request, "pId");
	String strPassionName=ParamUtil.getString(request,"passionName");
	String strSubPassionName=ParamUtil.getString(request,"subPassionName");

	//comment and endorse action done by logged user.
	
	
	UserDao uDao=new UserDaoImpl();
	User loggedInUser=(User)uDao.getUserByCPFNumber(loggedUser);
	String cpf = ParamUtil.getString(request,"cpfNo");
	
	userData=(User)uDao.getUserByCPFNumber(cpf);
	List<PassionPhoto> passionPhoto = null;
	
	List<PassionPhoto> passionPhotoEndrsCnt = null;
	
	PassionPhotoDaoImpl passionPhotoDao =new PassionPhotoDaoImpl();
	passionPhoto = passionPhotoDao.getPassionPhotoComments(photoId);
	
	System.out.println("Inside the jsp"+photoId);
	System.out.println("Inside the jsp"+loggedUser);
	
	flag=passionPhotoDao.checkEndorse(photoId,loggedUser);
	passionPhotoEndrsCnt=passionPhotoDao.getEndorseCount(photoId);
	for (int i = 0; i < passionPhotoEndrsCnt.size(); i++) {
		endrsCount =passionPhotoEndrsCnt.get(i).getEndorsedCount();
	}
	
	
	
	
	PassionPhoto prevPhoto=null;
	PassionPhoto nextPhoto=null;
	String prevPhotoName="";
	String prevFileName="";
	String prevFileDesc="";
	String prevEndorse="";
	String nextPhotoName="";
	String nextFileName="";
	String nextFileDesc="";
	String nextEndorse="";
	
	List al1=new ArrayList();
	if(!strPassionId.equalsIgnoreCase("0") && !strSubPassionId.equalsIgnoreCase("0")){
	List<PassionPhoto> passionPhoto1=passionPhotoDao.getPassionPhoto(cpf, strPassionId, strSubPassionId);
	System.out.println("sizeeeeeeee::::::::::"+passionPhoto1.size());
	Iterator itr=passionPhoto1.iterator();
	while(itr.hasNext()){
		PassionPhoto photo=(PassionPhoto)itr.next();
		al1.add(photo.getPhotoId());
		
	}}
	int k=0;
	if(al1.size()!=0)
	k=al1.indexOf(photoId);
	
	String prevPhotoId="";
	String nextPhotoId="";
	if(k>=1){
		prevPhotoId=(String)al1.get(k-1);
		System.out.println("prev"+al1.get(k-1));
		prevPhoto=passionPhotoDao.getPhotoDetail(prevPhotoId);
		prevPhotoName=prevPhoto.getPhotoName();
		prevFileName=prevPhoto.getFileName();
		prevFileDesc=prevPhoto.getPhotoDescription();
		prevEndorse=prevPhoto.getEndorsedCount();

		prevPhotoName=prevPhotoName.replace("'", "\\'").replace("\"", "&quot;");

		if(prevFileDesc!=null){

		prevFileDesc=prevFileDesc.replace("'", "\\'").replace("\"", "&quot;");

		}
	}
	if(k<=al1.size()-2){
		nextPhotoId=(String)al1.get(k+1);
		System.out.println("next"+al1.get(k+1));
		nextPhoto=passionPhotoDao.getPhotoDetail(nextPhotoId);
		nextPhotoName=nextPhoto.getPhotoName();
		nextFileName=nextPhoto.getFileName();
		nextFileDesc=nextPhoto.getPhotoDescription();
		nextEndorse=nextPhoto.getEndorsedCount();
		
		nextPhotoName=nextPhotoName.replace("'", "\\'").replace("\"", "&quot;");

		if(nextFileDesc!=null){

		nextFileDesc=nextFileDesc.replace("'", "\\'").replace("\"", "&quot;");

		}

	}
		
%>
<style>
.blocker {
	position: absolute;
	top: 0;
	left: 0;
	width: 309px;
	height: 462px;
	text-align: center;
	z-index: 1010;
	background: rgba(0, 0, 0, 0.42);
	color: #fff;
	font-family: Verdana, Geneva, sans-serif;
	font-size: 10px;
	display: none;
}

.blocker img {
	margin-top: 190px;
}
</style>


<script type="text/javascript">



function viewNextPhoto(a,pId,itemCaption,pDesc,pEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
	
	$.ajax({
      type: "POST",
      url:  "/wps/PA_ONGC_Passion/jspPages/web/viewPhoto.jsp", 
      data:'pId='+pId+'&url='+a+'&itemCaption='+itemCaption+'&photoDesc='+pDesc+'&photoEndrs='+pEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
      success: function( response ) {
    	 var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{
 
     $("#popupContent").html(response);
		}
      }
    });
    }


function updateEndorse(){
	
	$("#Endorse").attr('disabled','disabled');	
	$(".blocker").show();
	$.ajax( {
	      type: "POST",
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/viewPhoto1.jsp", 
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
	    	   $("#carousel_ulpop").append('<li><a href="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=loggedUser%>" alt="" title="you"/></a> </li>');
	       }}
	} );
}

function validatePhotoData(){
	
	var messagString="";
	messagString=ErroPhotoMessage(messagString);
	if(messagString!="") {
		alert(messagString);
		return false;
	}
	return true;
}

function ErroPhotoMessage(messagString) {
	
//	var nameSpchar=/^[a-zA-Z0-9.-.,-,---_-_(-()-) ]*$/;	
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
	if(!validatePhotoData()){
		return false;
	}
	$(".blocker").show();
	$.ajax( {

	      type: "POST",
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/viewPhoto2.jsp", 
	        data:'pId='+'<%=photoId%>'+'&cmnts='+a+'&cpf='+'<%=loggedUser%>'+'',
	      success: function( response ) {
                   $(".blocker").hide();
	    	  var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

	    	  $("#divcommentsPopup").html(response);
	    	    $("#commentsText").html('<input maxlength="100" type="text" placeholder="Your Comment" name="photoComments" value="" id="photoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="photoComments()">Post</span>');
	    	  
	    	  }
	      }
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
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/viewPhoto3.jsp", 
	       data:'pId='+'<%=photoId%>'+'&cmntId='+sno+'&cpf='+'<%=loggedUser%>'+'',
	      success: function( response ) {
                  $(".blocker").hide();
	    	 var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

	    	  $("#divcommentsPopup").html(response);
	    	    $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="photoComments" value="" id="photoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="photoComments()">Post</span>');
	    	  
	    	  }
	      }
	} );
	}
}

	</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Photo</title>
</head>

<body>
	<div class="popup"></div>
	<div id="popupContent">
		<div class="popupBox">
<div class="row">
<div class="col-md-8">
			<div class="img" style="position: relative;">
				<img
					src="<%=request.getContextPath()%>/jspPages/passionPhoto.jsp?fileName=<%=request.getParameter("url")%>&number=<%=cpf%>"
					alt="" />
				<%
					if (nextPhoto != null) {
				%>
				<div id="btn-next">
					<img id="nextFile"
						onclick="viewNextPhoto('<%=nextFileName%>','<%=nextPhotoId%>','<%=nextPhotoName%>','<%=nextFileDesc%>','<%=nextEndorse%>','<%=cpf%>','<%=strPassionId%>','<%=strSubPassionId%>','<%=strPassionName%>','<%=strSubPassionName%>');"
						src="/wps/PA_ONGC_Passion/images/right.png" width="24" height="18"
						alt="image" />
				</div>
				<%
					}
						if (prevPhoto != null) {
				%>
				<div id="btn-prev">
					<img id="prevFile"
						onclick="viewNextPhoto('<%=prevFileName%>','<%=prevPhotoId%>','<%=prevPhotoName%>','<%=prevFileDesc%>','<%=prevEndorse%>','<%=cpf%>','<%=strPassionId%>','<%=strSubPassionId%>','<%=strPassionName%>','<%=strSubPassionName%>');"
						src="/wps/PA_ONGC_Passion/images/left.png" width="24" height="18"
						alt="image" />
				</div>
				<%
					}
				%>
			</div>
		</div>
<div class="col-md-4">

			<div class="comentpop">
				<div class="blocker">
					<img width="40"
						src="<%=request.getContextPath()%>/images/processing.gif" />
					<p>Processing...</p>
				</div>
				<portlet:renderURL var="viewProfile">
									<portlet:param name="mvcRenderCommandName" value="view_profile" />
									<portlet:param name="cpfno"
										value="<%=userData.getCpfNo()%>" />
								</portlet:renderURL>
				
				<div class="d-flex">
					<div class="user">
						<img height="50px" width="50px"
							src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=cpf%>"
							alt="" />
					</div>
					<h2 style="padding-bottom: 0 !important;">
						<a
							href="${viewProfile}"><%=userData.getEmpName()%></a><span><%=strPassionName%>&nbsp;|&nbsp;<%=strSubPassionName%></span>
					</h2>

				</div>
				<portlet:renderURL var="updateEndorse">
					<portlet:param name="mvcRenderCommandName" value="update_endorse" />
					<portlet:param name="pid" value="<%=photoId%>" />
				</portlet:renderURL>
				<div class="">
					<h3><%=request.getParameter("itemCaption")%></h3>
					<%
						String description = request.getParameter("photoDesc");
					%>
					<p style="overflow-y: auto; height: 40px;"><%=(description.equalsIgnoreCase("null")) ? "" : description%></p>

					<%
						if (!loggedUser.equalsIgnoreCase(cpf)) {
								if (flag == false) {
					%>
					<div id="Endorse">
						<a class="endorsebtn" href="${updateEndorse}">Endorse</a>
					</div>
					<%
						} else {
					%>
					<div id="Endorse">
						<a class="endorsedbtn" >Endorsed</a>
					</div>
					<%
						}
							}
					%>
					<div style="margin-top: 10px;">
						<small>Endorse By</small>
					</div>
				</div>

				<div class="endorseBox">
					<span class="numbers" id="endorseCount"><%=endrsCount%></span>

					<div class="thumbnailer">
						<div id="carousel_containerpop">
							<div id="left_scrollpop">
								<a title="Slide left" onclick="slide('left');"><img
									src="<%=request.getContextPath()%>/images/left-arrow.jpg"
									alt="left arrow"></a>
							</div>
							<div id="carousel_innerpop">
								<ul id="carousel_ulpop" style="left: 0px;">
									<%
										ArrayList<User> al = passionPhotoDao.getEndorsedByUserName(photoId);
											System.out.println(" al al ::::::::   " + al.size());
											for (int i = 0; i < al.size(); i++) {
												userData = al.get(i);
												userName = userData.getEmpName();
												empCPF = userData.getCpfNo();
												if (!endrsCount.equals("0")) {
									%>
									<li><a
										href="/wps/portal/PassionProfile?empCpf=<%=empCPF%>"><img
											height="30px" width="30px" style="align: center"
											src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=empCPF%>"
											alt="" title="<%=userName%>" /></a></li>
									<%
										}
									%>
									<%
										}
									%>
								</ul>
							</div>
							<div id="right_scrollpop">
								<a title="Slide right" onclick="slide('right');"><img
									src="<%=request.getContextPath()%>/images/right-arrow.jpg"
									alt="right arrow"></a>
							</div>
						</div>

					</div>
				</div>
				<portlet:actionURL var="submitReply" name="submit_passion_photo_comment">
				</portlet:actionURL>
				<div class="" id="commentsText">
					<aui:form action="${submitReply}" method="post">
						<aui:input type="hidden" name="pId" value="<%=photoId%>"
							placeholder="Your Comment" />
						<aui:input type="text" name="cmnts" label="Comment"
							placeholder="Your Comment" />
						<aui:button type="submit" value="Post" cssClass="btn btn-sm btn-primary" />
					</aui:form>
				</div>
				<div class="parent">
					<div class="scrollbar"></div>
					<div class="scrollable">
						<div id="divcommentsPopup">
							<%
								if (passionPhoto.size() > 0) {
										for (int i = 0; i < passionPhoto.size(); i++) {
											if (passionPhoto.get(i).getPhotoComments() != null) {
							%>
							<div class="commentBox">
								<div class="img">
									<img height="50px" width="50px"
										src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=passionPhoto.get(i).getEndorsedByCpf()%>"
										alt="" />
								</div>
								<div class="right">
									<a
										href="${viewProfile}"><%=passionPhoto.get(i).getEndorsedBy()%></a>
									<p><%=passionPhoto.get(i).getPhotoComments()%>
										<%
											if (passionPhoto.get(i).getEndorsedByCpf().equalsIgnoreCase(loggedUser)) {
										%>
										&nbsp;&nbsp;&nbsp;<span style="float: right;"><a
											style="cursor: pointer;"
											onclick="deleteCmnt(<%=passionPhoto.get(i).getCommentId()%>);"><img
												alt="" src="<%=request.getContextPath()%>/images/delete.jpg" />
												Delete</a></span>
										<%
}
%>
									</p>
								</div>
							</div>
							<%
}}} else {
%>
							<table>

								<tr>
									<td>No Comments</td>

								</tr>
							</table>
							<%
}
%>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
<%
}
%>