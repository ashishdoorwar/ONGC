<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.model.PassionAudio"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.PassionAudioDaoImpl"%>
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
	
	
	UserDao uDao=new UserDaoImpl();
	User loggedInUser=(User)uDao.getUserByCPFNumber(loggedUser);
	
	PassionAudioDaoImpl passionAudioDao=new PassionAudioDaoImpl();
	List<PassionAudio> passionAudio = null;
	List<PassionAudio> passionAudioEndrsCnt = null;
	String cpf = ParamUtil.getString(request,"cpfNo");
	String strPassionId = ParamUtil.getString(request,"passionId");
	String strSubPassionId =ParamUtil.getString(request,"subPassionId");
	String strPassionName=ParamUtil.getString(request,"passionName");
	String strSubPassionName=ParamUtil.getString(request,"subPassionName");
	System.out.println(cpf);
	System.out.println(strPassionId);
	System.out.println(strSubPassionId);
	System.out.println(strPassionName);
	System.out.println(strSubPassionName);
	userData=uerService.getUser();
	String audioId = ParamUtil.getString(request,"aId");
	
	passionAudio = passionAudioDao.getPassionAudioComments(audioId);
	flag=passionAudioDao.checkEndorse(audioId,loggedUser);
	passionAudioEndrsCnt=passionAudioDao.getEndorseCount(audioId);
	for (int i = 0; i < passionAudioEndrsCnt.size(); i++) {
		endrsCount =passionAudioEndrsCnt.get(i).getAudioEndorsedCount();
	}
	
	
	PassionAudio prevAudio=null;
	PassionAudio nextAudio=null;
	String prevAudioName="";
	String prevFileName="";
	String prevFileDesc="";
	String prevEndorse="";
	String nextAudioName="";
	String nextFileName="";
	String nextFileDesc="";
	String nextEndorse="";
	PassionAudioDaoImpl passionPhotoDao =new PassionAudioDaoImpl();
	List al1=new ArrayList();
	if(!strPassionId.equalsIgnoreCase("0") && !strSubPassionId.equalsIgnoreCase("0")){
	List<PassionAudio> passionAudio1=passionAudioDao.getPassionAudio(cpf, strPassionId, strSubPassionId);
	System.out.println("sizeeeeeeee::::::::::"+passionAudio1.size());
	Iterator itr=passionAudio1.iterator();
	while(itr.hasNext()){
		PassionAudio audio=(PassionAudio)itr.next();
		al1.add(audio.getAudioId());
		
	}}
	int k=0;
	if(al1.size()!=0)
	k=al1.indexOf(audioId);
	
	String prevAudioId="";
	String nextAudioId="";
	if(k>=1){
		prevAudioId=(String)al1.get(k-1);
		System.out.println("prev"+al1.get(k-1));
		prevAudio=passionAudioDao.getAudioDetail(prevAudioId);
		prevAudioName=prevAudio.getAudioName();
		prevFileName=prevAudio.getAudioFileName();
		prevFileDesc=prevAudio.getAudioDescription();
		prevEndorse=prevAudio.getAudioEndorsedCount();

		prevAudioName=prevAudioName.replace("'", "\\'").replace("\"", "&quot;");

		if(prevFileDesc!=null){

		prevFileDesc=prevFileDesc.replace("'", "\\'").replace("\"", "&quot;");

		}


	}
	if(k<=al1.size()-2){
		nextAudioId=(String)al1.get(k+1);
		System.out.println("next"+al1.get(k+1));
		nextAudio=passionAudioDao.getAudioDetail(nextAudioId);
		nextAudioName=nextAudio.getAudioName();
		nextFileName=nextAudio.getAudioFileName();
		nextFileDesc=nextAudio.getAudioDescription();
		nextEndorse=nextAudio.getAudioEndorsedCount();
		
		nextAudioName=nextAudioName.replace("'", "\\'").replace("\"", "&quot;");

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

	function updateEndorse(){		
		$("#Endorse").attr('disabled','disabled');
                $(".blocker").show();
		$.ajax( {
			      type: "POST",
			      url:  "/wps/PA_ONGC_Passion/jspPages/web/endorseAudio.jsp", 
			      data:'aId='+'<%=audioId%>'+'&cpf='+'<%=loggedUser%>'+'',
			      success: function( response ) {
                               $(".blocker").hide();
					var abc=$.trim(response );
				if(abc=='logout'){
				alert('Your Session has been expired. Please Login again.')
				window.location.href='redirect.jsp';
				}else{

			    	  $("#endorseCount").html(response);
			    	  $("#Endorse").html('<a class="endorsedbtn">Endorsed</a>');
			    	  $("#carousel_ulpop").append('<li><a href="/wps/portal/PassionProfile?empCpfReport=<%=cpf%>"><img height="30px" width="30px" style="align:center" src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=loggedUser%>" alt="" title="you" /></a></li>');

				}
			      }
			} );
		}
	
	function playNextAudio(audioFileName,aId,itemCaption,audioDesc,audioEndrs,cpf,passionId,subPassionId,passionName,subPassionName) {
				
		$.ajax({
	      type: "POST",
	      url:  "/wps/PA_ONGC_Passion/jspPages/web/playAudio.jsp", 
	      data:'fileName='+audioFileName+'&aId='+aId+'&itemCaption='+itemCaption+'&audioDesc='+audioDesc+'&audioEndrs='+audioEndrs+'&cpfNo='+cpf+'&passionId='+passionId+'&subPassionId='+subPassionId+'&passionName='+passionName+'&subPassionName='+subPassionName+'&userName=<s:property value="user.empName"/>',
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
	function validateAudioData(){
		var messagString="";
		messagString=ErrorAudioMessage(messagString);
		if(messagString!="") {
			alert(messagString);
			return false;
		}
		return true;
	}
	function ErrorAudioMessage(messagString) {
		//var nameSpchar=/^[a-zA-Z0-9.-.,-,---_-_(-()-) ]*$/;
                 var nameSpchar = /^[0-9a-zA-Z\s\r\n@!#\$\^*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;	
	 	messagString=FieldValiDationWithFocuse("audioComments",nameSpchar,"Special Characters are not allowed.","Please Enter Comments", messagString);
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
	
	function audioComments(){
		var a=document.getElementById("audioComments").value;
		if(!validateAudioData()){
			return false;
		}
                $(".blocker").show();
		$.ajax( {
		      type: "POST",
		      url:  "/wps/PA_ONGC_Passion/jspPages/web/audioComments.jsp", 
		      data:'aId='+'<%=audioId%>'+'&cmnts='+a+'&cpf='+'<%=loggedUser%>'+'',
		      success: function( response ) {
                        $(".blocker").hide();
			var abc=$.trim(response );
			if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
				}else{

		    	  $("#commentsAudio").html(response);
		    	  $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="audioComments" value="" id="audioComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="audioComments()">Post</span>');
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
		      url:  "/wps/PA_ONGC_Passion/jspPages/web/deleteAudioComments.jsp", 
		      data:'aId='+'<%=audioId%>'+'&cmntId='+sno+'&cpf='+'<%=loggedUser%>'+'',
		      success: function( response ) {
                       $(".blocker").hide();
			var abc=$.trim(response );
		if(abc=='logout'){
			alert('Your Session has been expired. Please Login again.')
			window.location.href='redirect.jsp';
		}else{

		    	  $("#commentsAudio").html(response);
		    	  $("#commentsText").html('<input type="text" maxlength="100" placeholder="Your Comment" name="audioComments" value="" id="audioComments"/> <span style="cursor: pointer;" class="brown_btn" onclick="audioComments()">Post</span>');
		      }}
		} );
	}
	}
	</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Photo</title>
<script
	src="<%=request.getContextPath()%>/embedFLV_files/flowplayer-3.js"></script>
<link href="<%=request.getContextPath()%>/video-js/video-js.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/video-js/video.js"></script>
</head>
<body>
	<div class="popup"></div>
	<div class="popupBox">
		<div id="popupContent">
		<div class="row">
			<div class="col-md-8">
			<div class="img" style="position: relative;">
				<audio id="audio_example" class="video-js vjs-default-skin" controls
					preload="auto" width="650" height="470"
					poster="<%=request.getContextPath()%>/video-js/music.gif"
					data-setup='{"controls" : true, "autoplay" : true, "preload" : "auto"}'>
				<source
					src="<%=request.getContextPath()%>/jspPages/passionAudio.jsp?fileName=<%=ParamUtil.getString(request,"fileName")%>&number=<%=cpf%>"
					type="audio/mpeg"> Your browser does not support the audio
				element. </audio>
				<%
					if (nextAudio != null) {
				%>
				<div id="btn-next" style="position: absolute;">
					<img id="nextFile"
						onclick="playNextAudio('<%=nextFileName%>','<%=nextAudioId%>','<%=nextAudioName.replace("'", "\\'")%>','<%=nextFileDesc.replace("'", "\\'")%>','<%=nextEndorse%>','<%=cpf%>','<%=strPassionId%>','<%=strSubPassionId%>','<%=strPassionName%>','<%=strSubPassionName%>')"
						src="images/right.png" width="24" height="18" alt="image" />
				</div>
				<%
					}
						if (prevAudio != null) {
				%>
				<div id="btn-prev" style="position: absolute;">
					<img id="prevFile"
						onclick="playNextAudio('<%=prevFileName%>','<%=prevAudioId%>','<%=prevAudioName.replace("'", "\\'")%>','<%=prevFileDesc.replace("'", "\\'")%>','<%=prevEndorse%>','<%=cpf%>','<%=strPassionId%>','<%=strSubPassionId%>','<%=strPassionName%>','<%=strSubPassionName%>')"
						src="images/left.png" width="24" height="18" alt="image" />
				</div>
				<%
					}
				%>
			</div>
		</div>

		<div class="col-md-4">
			<div class="comentpop">
				<div class="blocker">
					<img width="40" src="/wps/PA_ONGC_Passion/images/processing.gif" />
					<p>Processing...</p>
				</div>
				<div class="d-flex">
					<div class="user">
						<img height="50px" width="50px"
							src="http://reports1.ongc.co.in/o/blade/servlet/imageServlet?cpfno=<%=cpf%>"
							alt="" />
					</div>
					<h2 style="padding-bottom: 0 !important;">
						<a
							href="/wps/portal/PassionProfile?empCpfReport=<%=userData.getCpfNo()%>"><%=userData.getEmpName()%></a><span><%=strPassionName%>&nbsp;|&nbsp;<%=strSubPassionName%></span>
					</h2>

				</div>

				<div class="">
					<h3><%=ParamUtil.getString(request,"itemCaption")%></h3>
					<%
						String description = (String) ParamUtil.getString(request,"audioDesc");
					%>
					<p style="overflow-y: auto; height: 40px;"><%=(description.equalsIgnoreCase("null")) ? "" : description%></p>

					<%
						if (!loggedUser.equalsIgnoreCase(cpf)) {
								if (flag == false) {
					%>
					<div id="Endorse">
						<a class="endorsebtn" onclick="updateEndorse()" href="#">Endorse</a>
					</div>
					<%
						} else {
					%>
					<div id="Endorse">
						<a class="endorsedbtn">Endorsed</a>
					</div>
					<%
						}
							}
					%>
					<div style="margin: 10px 0;">
						<small>Endorse By</small>
					</div>
				</div>

				<div class="endorseBox">
					<span class="numbers" id="endorseCount"><%=endrsCount%></span>

					<div class="thumbnailer">
						<div id="carousel_containerpop">
							<div id="left_scrollpop">
								<a title="Slide left" onclick="slide('left');"><img
									src="/wps/PA_ONGC_Passion/images/left-arrow.jpg"
									alt="left arrow"></a>
							</div>
							<div id="carousel_innerpop">
								<ul id="carousel_ulpop" style="left: 0px;">
									<%
										ArrayList<User> al = passionAudioDao.getAudioEndorsedByUserName(audioId);
											System.out.println(" al al ::::::::   " + al.size());
											for (int i = 0; i < al.size(); i++) {
												userData = al.get(i);
												userName = userData.getEmpName();
												empCPF = userData.getCpfNo();
												if (!endrsCount.equals("0")) {
									%>
									<li><a
										href="/wps/portal/PassionProfile?empCpfReport=<%=empCPF%>"><img
											height="30px" width="30px" style="align: center"
											src="/wps/PA_ONGC_Passion/jspPages/web/viewUserProfile.jsp?empCPF=<%=empCPF%>"
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
									src="/wps/PA_ONGC_Passion/images/right-arrow.jpg"
									alt="right arrow"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="" id="commentsText">
					<input maxlength="100" type="text" placeholder="Your Comment"
						name="audioComments" id="audioComments" /> <span
						style="cursor: pointer;" class="brown_btn"
						onclick="audioComments()">Post</span>
				</div>
				<small>Comments</small>
				<div class="parent">
					<div class="scrollbar"></div>
					<div class="scrollable">
						<div id="commentsAudio">
							<%
								if (passionAudio.size() > 0) {
										for (int i = 0; i < passionAudio.size(); i++) {
											if (passionAudio.get(i).getAudioComments() != null) {
							%>
							<div class="commentBox">
								<div class="img">
									<img height="50px" width="50px"
										src="/wps/PA_ONGC_Passion/jspPages/web/viewUserProfile.jsp?empCPF=<%=passionAudio.get(i).getAudioEndorsedByCpf()%>"
										alt="" />
								</div>
								<div class="right">
									<a
										href="/wps/portal/PassionProfile?empCpfReport=<%=passionAudio.get(i).getAudioEndorsedByCpf()%>"><%=passionAudio.get(i).getAudioEndorsedBy()%>
									</a>
									<p><%=passionAudio.get(i).getAudioComments()%>
										<%
											if (passionAudio.get(i).getAudioEndorsedByCpf().equalsIgnoreCase(loggedUser)) {
										%>
										&nbsp;&nbsp;&nbsp;<span style="float: right;"><a
											style="cursor: pointer;"
											onclick="deleteCmnt('<%=passionAudio.get(i).getCommentId()%>')"><img
												alt="" src="/wps/PA_ONGC_Passion/images/delete.jpg" />
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
</body>
</html>
<%
}
%>