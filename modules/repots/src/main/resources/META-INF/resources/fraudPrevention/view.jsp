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
	String userName = (String) request.getAttribute("userName");
%>
<portlet:renderURL var="fraudPledgeURL">
	<portlet:param name="mvcRenderCommandName" value="fraudPledge" />
</portlet:renderURL>
<%
String checkContent="true";
if (checkCpf.contentEquals(checkContent)) {
%>
<div class="alert alert-primary">You have already taken Pledge.</div>
<%
	} else {
%>

<div id="pledgeText" class="alert alert-info">
<p><strong>I, <span id="uname"></span>  an employee of Oil and Natural Gas Corporation do hereby solemnly pledge that 
I will adhere to the Fraud Prevention Policy of ONGC and shall not indulge myself or allow others to indulge in fraudulent actvities and that 
I will immediately apprise ONGC of the fraud/suspected fraud as soon as it comes to my notice.</strong></p>
</div>
<aui:form method="post" name="fProf" action="">
	<button name="submit" class="btn btn-primary mt5"
		onClick="takePledge('<%=cpfNo%>','<%=userName%>')" id="submit">Take	Pledge</button>
</aui:form>
<%
	}
%>
<a href="<%= themeDisplay.getURLPortal() %>/webdav/reports_en/document_library/fraud211201.pdf" target="_blank" title="Read Policy">Click here</a> to read Policy.
<div class="loader">
	<div class="icon">
		<i class="fa fa-spinner faa-spin animated"></i>Please wait...
	</div>
</div>

<script>
	 function takePledge(cpfNo,userName){
		 var type='FRAUD';
		 $('.loader').show();
		   $.ajax({
				  type: 'post',
				  url: '<%=fraudPledgeURL %>&<portlet:namespace/>cpfNo='+cpfNo+'&<portlet:namespace/>userName='+userName+'&<portlet:namespace/>type='+type,
				  success: function(response){
					$("#cft").show();
					$("#pledgeText").hide();
					$("#takePledge").hide();
					$('.loader').hide();
					$("#pledge").show();
				  }
			});
	   }
	</script>
