<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@ include file="/init.jsp" %>
<style>
@-webkit-keyframes spin {
    0% {
        -webkit-transform: rotate(0);
        transform: rotate(0)
    }

    100% {
        -webkit-transform: rotate(359deg);
        transform: rotate(359deg)
    }
}

@keyframes spin {
    0% {
        -webkit-transform: rotate(0);
        transform: rotate(0)
    }

    100% {
        -webkit-transform: rotate(359deg);
        transform: rotate(359deg)
    }
}

.faa-spin {
    -webkit-animation: spin 1.5s linear infinite;
    animation: spin 1.5s linear infinite
}

.loader{ display:none; position: fixed; top: 0; left: 0; right: 0; bottom: 0; height:100%; width: 100%; margin: auto; background: rgb(0,0,0,0.5); z-index: 9999;}

.loader .icon { position: fixed;
    left: 0;
    top: 0;
    z-index: 99999;
    width: 150px;
    height: 100px;
    bottom: 0;
    right: 0;
    margin: auto;
	}
.loader .icon i { font-size:100px;
		color: #000;
		}

</style>
<%
	String count = (String) request.getAttribute("xmlBuffer");
	String checkCpf = (String) request.getAttribute("xmlBufferCheckCPf");
	String cpfNo = (String) request.getAttribute("cpfNo");
	String fileName = cpfNo+".pdf";
	String userName = (String) request.getAttribute("userName");
	 %>
 <liferay-ui:error key="error" message="Illegal argument found" /> 
 <portlet:actionURL var="safetyPledgeURL" name="safetyPledge" ></portlet:actionURL>

<div style="text-align: right; padding: 0 10px 10px 0; color: #8c000d;">
	<strong> No. of Pledge Taken:: </strong><span id="totalCount"
		style="color: black;"> <%=count%>
	</span>
</div>
<%
String checkContent="true";
if (checkCpf!=null && checkCpf.contentEquals(checkContent)) {
%>
<div class="alert alert-primary">You have already taken Safety
	Pledge.</div>
	<div id="cft" class="alert alert-info">
	<p style="color: #000;">
		<strong>Message:</strong>
	</p>
	<p>
		<strong>Chief HSE congratulates and appreciates your taking
			the Safety Pledge. (Your 'Certificate of Appreciation' has been sent
			on your official email id)</strong>
	</p>
	<p>
		<a href="<%= themeDisplay.getURLPortal() %>/o/blade/safetyservlet/pdfSafety?safetyPledge=<%= fileName %>" target="_blank" title="certificate"
			class="btn btn-primary mt5">Download Certificate</a>
	</p>
</div>
<%
	} else {
%>
 <div id="pledgeText" class="alert alert-info">
<p><strong>
I, <span id="uname"></span> an employee of Oil and Natural Gas Corporation Ltd. do hereby solemnly resolve that 
I will adhere to Ten Safety Rules, Standard Operating Procedures and encourage my co-workers to be vigilant  and follow the same.
I also commit to do risk assessment before commencement of work, stop unsafe act and fix unsafe conditions at work place.
</strong>

<br/>
<a href="javascript:readSafety()" class="btn btn-primary mt5" id="readPledge" title="Read Pledge">Click here to read Safety Pledge</a>
</p>
</div> 
<div id="pledge" style="display:none;" class="alert alert-info">
	<div id="hndi">
<liferay-ui:message key="safety.pledge"/>
	</div>

	<hr />

	<div id="eng">
		<p>On this Day, I solemnly affirm that I will rededicate myself to
			the cause of safety, health and protection of environment and will do
			my best to observe rules, regulations and procedures and develop
			attitudes and habits conducive for achieving these objectives.</p>
		<p>I fully realise that accidents and diseases are a drain on the
			national economy and may lead to disablements, deaths, harm to health
			and damage to property, social suffering and general degradation of
			environment.</p>
		<p>I will do everything possible for the prevention of accidents
			and occupational diseases and protection of environment in the
			interest of self, family, community, organization and the nation at
			large.</p>
	</div>
	<p>
		<br />
		<aui:form method="post" name="fProf" action="">
			 
<%-- <aui:input type="text" name="cpfno" value="<%= cpfNo %>"/>
<aui:input type="text" name="userName" value="<%= userName %>"/>  --%>
			<button name="submit" class="btn btn-primary mt5"
				onClick="takePledge('<%= cpfNo %>','<%= userName %>')" id="submit">Take
				Pledge</button>
		</aui:form>
</div>
<%
	}
%>



<div class="loader">
	<div class="icon">
		<i class="fa fa-spinner faa-spin animated"></i>Please wait...
	</div>
</div>

<script>

function readSafety(){
	 $("#pledge").css('display', 'block');  
		$("#pledgeText").hide();
}
function takePledge(cpfNo,userName){
 var type='SAFETY';
 $('.loader').show();
// var cpfNo= $('#<portlet:namespace/>cpfno').val();
// var userName= $('#<portlet:namespace/>userName').val();
   $.ajax({
		  type: 'post',
		  url: '<%=safetyPledgeURL %>&<portlet:namespace/>cpfNo='+cpfNo+'&<portlet:namespace/>userName='+userName+'&<portlet:namespace/>type='+type,
		  success: function(response){
			$("#cft").show();
			$("#pledgeText").hide();
			$("#takePledge").hide();
			$('.loader').hide();
			$("#pledge").show();
		  }
	});
  }
function viewCertificate(){
	alert("having issue in repository to build pdf...")
}
	</script>
