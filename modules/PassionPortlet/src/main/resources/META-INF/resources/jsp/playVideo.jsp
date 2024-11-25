<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.model.PassionVideo"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.PassionVideoDaoImpl"%>
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
			String endrsCount = null;
			String userName=null;
			String empCPF=null;
			boolean flag = false;
			User userData=null;
			
			UserDao uDao=new UserDaoImpl();
			User loggedInUser=(User)uDao.getUserByCPFNumber(loggedUser);
			
			PassionVideoDaoImpl passionVideoDao = new PassionVideoDaoImpl();
			
			List<PassionVideo> passionVideo = null;
			List<PassionVideo> passionVideoEndrsCnt = null;
			String cpf = ParamUtil.getString(request,"cpfNo");
			String strPassionId = ParamUtil.getString(request,"passionId");
			String strSubPassionId = ParamUtil.getString(request,"subPassionId");
			String strPassionName=ParamUtil.getString(request,"passionName");
			String strSubPassionName=ParamUtil.getString(request,"subPassionName");

			userData=uerService.getUser();
			String videoId = ParamUtil.getString(request,"vId");
			
			passionVideo = passionVideoDao.getPassionVideoComments(videoId);
			flag = passionVideoDao.checkEndorse(videoId, loggedUser);
			passionVideoEndrsCnt = passionVideoDao.getEndorseCount(videoId);
			for (int i = 0; i < passionVideoEndrsCnt.size(); i++) {
				endrsCount = passionVideoEndrsCnt.get(i)
				.getVideoEndorsedCount();
			}
			
			
			PassionVideo prevVideo=null;
			PassionVideo nextVideo=null;
			String prevVideoName="";
			String prevFileName="";
			String prevFileDesc="";
			String prevEndorse="";
			String nextVideoName="";
			String nextFileName="";
			String nextFileDesc="";
			String nextEndorse="";
			
			List al1=new ArrayList();
			if(!strPassionId.equalsIgnoreCase("0") && !strSubPassionId.equalsIgnoreCase("0")){
			List<PassionVideo> passionVideo1=passionVideoDao.getPassionVideo(cpf, strPassionId, strSubPassionId);
			
			Iterator itr=passionVideo1.iterator();
			while(itr.hasNext()){
				PassionVideo video=(PassionVideo)itr.next();
				al1.add(video.getVideoId());
				
			}}
			int k=0;
			if(al1.size()!=0)
			k=al1.indexOf(videoId);
			
			String prevVideoId="";
			String nextVideoId="";
			if(k>=1){
				prevVideoId=(String)al1.get(k-1);
				System.out.println("prev"+al1.get(k-1));
				prevVideo=passionVideoDao.getVideoDetail(prevVideoId);
				prevVideoName=prevVideo.getVideoName();
				prevFileName=prevVideo.getVideoFileName();
				prevFileDesc=prevVideo.getVideoDescription();
				prevEndorse=prevVideo.getVideoEndorsedCount();
					prevVideoName=prevVideoName.replace("'", "\\'").replace("\"", "&quot;");

					if(prevFileDesc!=null){

					prevFileDesc=prevFileDesc.replace("'", "\\'").replace("\"", "&quot;");

					}

			}
			if(k<=al1.size()-2){
				nextVideoId=(String)al1.get(k+1);
				System.out.println("next"+al1.get(k+1));
				nextVideo=passionVideoDao.getVideoDetail(nextVideoId);
				nextVideoName=nextVideo.getVideoName();
				nextFileName=nextVideo.getVideoFileName();
				nextFileDesc=nextVideo.getVideoDescription();
				nextEndorse=nextVideo.getVideoEndorsedCount();
		
				nextVideoName=nextVideoName.replace("'", "\\'").replace("\"", "&quot;");

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

	function videoEndorse(){
	$("#Endorse").attr('disabled','disabled');    
        $(".blocker").show(); 
	$.ajax( {
	      type: "POST",
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/endorseVideo.jsp", 
	        data:'vId='+'<%=videoId%>'+'&cpf='+'<%=loggedUser%>'+'',
	      success: function( response ) {
                $(".blocker").hide();
			var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

	    	  $("#endorseCount").html(response);
	    	   $("#Endorse").html('<a class="endorsedbtn">Endorsed</a>');
	    	   $("#carousel_ulpop").append('<li><a href="viewProfile?empCpf=<%=cpf%>"><img height="30px" width="30px" style="align:center" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=loggedUser%>" alt="" title="you" /></a></li>');
			}
	      }
		} );
	}
	
	
	function validateVideoData(){
		var messagString="";
		messagString=ErrorVideoMessage(messagString);
		if(messagString!="") {
			alert(messagString);
			return false;
		}
		return true;
	}
	
	function playNextVideo(videoFileName,vId,itemCaption,videoDesc,videoEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
		
		$.ajax({
	      type: "POST",
	      url:  "/wps/PA_ONGC_Passion/jspPages/web/playVideo.jsp", 
		data:'fileName='+videoFileName+'&vId='+vId+'&itemCaption='+itemCaption+'&videoDesc='+videoDesc+'&videoEndrs='+videoEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
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
	
	
	function ErrorVideoMessage(messagString) {
		//var nameSpchar=/^[a-zA-Z0-9.-.,-,---_-_(-()-) ]*$/;
                 var nameSpchar = /^[0-9a-zA-Z\s\r\n@!#\$\^*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;	
	 	messagString=FieldValiDationWithFocuse("videoComments",nameSpchar,"Special Characters are not allowed.","Please Enter Comments", messagString);
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
	
	
	function videoComments(){
	var a=document.getElementById("videoComments").value;
	
	if(!validateVideoData()){
		return false;
	}
	$(".blocker").show();
	$.ajax( {

	      type: "POST",
	       url:  "/wps/PA_ONGC_Passion/jspPages/web/videoComments.jsp", 
	        data:'vId='+'<%=videoId%>'+'&cmnts='+a+'&cpf='+'<%=loggedUser%>'+'',
	      success: function( response ) {
                $(".blocker").hide();
		var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

	    	  $("#commentsVideo").html(response);
	    	    $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="videoComments" value="" id="videoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="videoComments()">Post</span>');  
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
		       url:  "/wps/PA_ONGC_Passion/jspPages/web/deleteVideoComments.jsp", 
		        data:'vId='+'<%=videoId%>'+'&cmntId='+sno+'&cpf='+'<%=loggedUser%>'+'',
		      success: function( response ) {
                        $(".blocker").hide();
			var abc=$.trim(response );
			if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
			}else{

		    	  $("#commentsVideo").html(response);
		    	    $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="videoComments" value="" id="videoComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="videoComments()">Post</span>');  
		      }}
			} );
		}
	}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Play Video</title>
<script src="<%=request.getContextPath()%>/embedFLV_files/flowplayer-3.js"></script>
<link href="<%=request.getContextPath()%>/video-js/video-js.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/video-js/video.js"></script>
</head>
	
	
	
	
	<body>
<div class="popup"></div>

<div class="popupBox">
<div id="popupContent">
	<div class="row">
	<div class="col-md-8">
<div class="img" style="height:470px;">
<%
	int ext=(ParamUtil.getString(request,"fileName")).lastIndexOf(".");
	String fName=ParamUtil.getString(request,"fileName");
	String extnsn=(ParamUtil.getString(request,"fileName")).substring(ext+1);
	if(extnsn.equalsIgnoreCase("mp4")){
	
	%>

<video id="mb" class="video-js vjs-default-skin" width="650" height="470" data-setup='{"controls" : true, "autoplay" : false, "preload" : "auto"}'>
  	<source src="/wps/PA_ONGC_Passion/jspPages/passionVideo.jsp?fileName=<%=ParamUtil.getString(request,"fileName")%>@<%=cpf %>" type="video/mp4">
</video>
<% } else{ %>
<a id="mb" href="<%=request.getContextPath()%>/jspPages/passionVideo.jsp?fileName=<%=fName%>@<%=cpf %>"></a>
<script>
$f("mb", "<%=request.getContextPath()%>/embedFLV_files/flowplayer-3.2.10.swf",
{ clip: {
        // these two configuration variables does the trick
        autoPlay: false,
        autoBuffering: true // <- do not place a comma here
    }
});

   
</script>
<%}%>
					<%if(nextVideo!=null){ %>
		<div id="btn-next" style="position:absolute;">
		<img id="nextFile" onclick="playNextVideo('<%=nextFileName%>','<%=nextVideoId%>','<%=nextVideoName%>','<%=nextFileDesc%>','<%=nextEndorse%>','<%=cpf%>','<%=strPassionId %>','<%=strSubPassionId %>','<%=strPassionName %>','<%=strSubPassionName %>')" src="/wps/PA_ONGC_Passion/images/right.png" width="24" height="18" alt="image"/>
		</div>
		<%} if(prevVideo!=null){ %>
		<div id="btn-prev" style="position:absolute;left:-11px;">
		<img id="prevFile" onclick="playNextVideo('<%=prevFileName%>','<%=prevVideoId%>','<%=prevVideoName%>','<%=prevFileDesc%>','<%=prevEndorse%>','<%=cpf%>','<%=strPassionId %>','<%=strSubPassionId %>','<%=strPassionName %>','<%=strSubPassionName %>')" src="/wps/PA_ONGC_Passion/images/left.png" width="24" height="18" alt="image"/>
		</div>
		<%} %>
					
					</div>
					</div>
	<div class="col-md-4">
   <div class="comentpop">
    <div class="blocker">
  <img width="40" src="/wps/PA_ONGC_Passion/images/processing.gif" />
<p>Processing...</p>
    </div>
    	<div class="d-flex">
        	<div class="user"><img height="50px" width="50px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=cpf%>" alt=""  /></div>
        	<h2 style="padding-bottom: 0 !important;"><a href="/wps/portal/PassionProfile?empCpf=<%=userData.getCpfNo()%>"><%=userData.getEmpName()%></a><span><%=strPassionName%>&nbsp;|&nbsp;<%=strSubPassionName%></span></h2>    
		            
        </div>
        
        <div class="row">
        	<h3><%=ParamUtil.getString(request,"itemCaption")%></h3>
<% String description=ParamUtil.getString(request,"videoDesc"); %>
            <p style="overflow-y:auto; height:40px;"><%=(description.equalsIgnoreCase("null"))?"":description%></p>
            
           <%
                                        if(!loggedUser.equalsIgnoreCase(cpf)){
					if (flag == false) {
					%>
					<div id="Endorse">
						<a class="endorsebtn" onclick="videoEndorse()" href="#">Endorse</a>
					</div>
					<%
					} else {
					%>
					<div id="Endorse">
						<a class="endorsedbtn">Endorsed</a>
					</div>
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
  <a title="Slide left" onclick="slide('left');"><img src="/wps/PA_ONGC_Passion/images/left-arrow.jpg" alt="left arrow"></a></div>
            <div id="carousel_innerpop">
            <ul id="carousel_ulpop" style="left: 0px; ">
            	<%
							ArrayList<User> al = passionVideoDao.getVideoEndorsedByUserName(videoId);
							System.out.println(videoId+"  al al videoId ::::::::   "+al.size());
							for (int i = 0; i < al.size(); i++) {
								userData = al.get(i);
								userName = userData.getEmpName();
								empCPF = userData.getCpfNo();
								if (!endrsCount.equals("0")) {
						%>
						<li>
						<a href="/wps/portal/PassionProfile?empCpf=<%=empCPF%>"><img height="30px" width="30px" style="align:center" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=empCPF%>" alt="" title="<%=userName%>" /></a>
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
        	<input maxlength="100" type="text" placeholder="Your Comment" name="videoComments"
							id="videoComments" />
						<span style="cursor: pointer;" class="brown_btn" onclick="videoComments()">Post</span>
        </div>
        <small>Comments</small>
        <div class="parent">
    	<div class="scrollbar"></div>
        <div class="scrollable">
        <div id="commentsVideo">        	
            <%
								if (passionVideo.size() > 0) {
								for (int i = 0; i < passionVideo.size(); i++) {
									if (passionVideo.get(i).getVideoComments() != null) {
							%>
							<div class="commentBox">
							<div class="img">
									<img height="50px" width="50px" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=passionVideo.get(i).getVideoEndorsedByCpf() %>" alt=""  />
							</div>
							<div class="right">
							<a href="viewProfile?empCpf=<%=passionVideo.get(i).getVideoEndorsedByCpf()%>"><%=passionVideo.get(i).getVideoEndorsedBy()%></a>								
								<p><%=passionVideo.get(i).getVideoComments()%>
								<%
									if(passionVideo.get(i).getVideoEndorsedByCpf().equalsIgnoreCase(loggedUser)){
									%>
									&nbsp;&nbsp;&nbsp;<span style="float:right;"><a style="cursor:pointer;" onclick="deleteCmnt('<%= passionVideo.get(i).getCommentId()%>')"><img alt="" src="/wps/PA_ONGC_Passion/images/delete.jpg"/> Delete</a></span>
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
								<td>
									No Comments
								</td>

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
</body>
</html>
<%
}
%>
