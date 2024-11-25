<%@ include file="/init.jsp"%>
<%
	String toname = (String) request.getAttribute("name");
	String tocpfNo = (String) request.getAttribute("cpfNo");
	UserDao userDao = new UserDao();
	User userOngc = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
	String mailmsg = "I wish you a very happy birthday. It has been a great experience working with you. I wish you have many more years to your life and much more life in those years.";
%>

<script>
$(document).ready(function(e) {
 
 var tocpf =<%=tocpfNo%>;
 var toname ='<%=toname%>';
 var wish='<%=mailmsg%>';

				$("#toName").text(toname);
				$("#wishes").text(wish);
				var fromname = $("#getUserName").val();
				$("#toName").text(toname);

				$("#fromName").text(fromname);

				$('#submitWishes').click(function() {
					var uniqueCpf1 = $("#uniqueCpf");
					$('#tocpf').val(tocpf);
					$('#fromcpf').val(uniqueCpf1.val());
					$('#toname').val(toname);
					$('#fromname').val(fromname);

				});

				var frm1 = $("#bdform");
				frm1.submit(function() {
					$('.loader').show();
					$("#submitWishes").hide();
					$.ajax({
						type : frm1.attr('method'),
						url : frm1.attr('action'),
						data : frm1.serialize(),
						success : function(response) {
							var flag = $(response).find("status").text();
							if (flag == "false") {
								$('.loader').hide();
								$("#submitWishes").show();
								$("#bderorr").text(
										$(response).find("errormsg2").text());
								$("#bderorr").addClass("alert alert-danger");
								$("#bderorr").show();
							} else {

								$("#bdform").hide();
								$('.loader').hide();
								$("#msgbox").text(
										$(response).find("errormsg2").text());
								$("#msgbox").addClass("alert alert-success");
								$("#msgbox").show();
							}
						}

					});
					return false;
				});
			});
</script>
<div class="row">
<div class="col-lg-1"></div>
<div class="col-lg-10">
<portlet:actionURL var="saveWishes" name="saveBirthdayWishes" ></portlet:actionURL>

	<aui:form method="POST" name="bdform" action="<%=saveWishes%>">
		<aui:input type="hidden" name="tocpf" id="tocpf" value="<%=tocpfNo%>" /> 
		<aui:input type="hidden" name="fromcpf" id="fromcpf"  value="<%=userOngc.getCpfNo()%>" /> 
		<aui:input type="hidden" name="toname" id="toname" value="<%=toname%>" /> 
		<aui:input type="hidden" name="fromname" id="fromname" value="<%=userOngc.getEmpName()%>" />
		<div class="col-lg-12">
			<div class="col-lg-6" style="display:inline-block;">
				<strong>To: </strong><span id="toName"><%=toname%></span>
			</div>
			<div class="col-lg-6 text-right" style="display:inline-block;">
				<strong>From: </strong><span id="fromName"><%=userOngc.getEmpName()%></span>
			</div>
		</div>
		<div class="col-lg-10">
			<strong>Message:</strong>
			<textarea id="wishes" name="wishes" class="form-control" id="high1"
			style="resize: none;" disabled></textarea>
		</div>
		
		<div class="text-center mt20">
			<input type="submit" value="Send" id="submitWishes"
				class="btn btn-primary">
		</div>
	</aui:form>
</div>
</div>
<script>
<%-- function submitBdayWish(){
	var tocpfNo=$('#<portlet:namespace />tocpf').val();
	var toname=$('<portlet:namespace />toname').val();
	var fromcpf=$('<portlet:namespace />fromcpf').val();
	var fromname=$('<portlet:namespace />fromname').val();
	alert(tocpfNo);
	 var url="<%=saveWishes %>&<portlet:namespace />tocpfNo="+tocpfNo+"&<portlet:namespace />toname="+toname+"&<portlet:namespace />fromcpf="+fromcpf+"&<portlet:namespace />fromname="+fromname;
	Liferay.Util.openWindow({
	    dialog: {
	        centered: true,
	        height: 400,
	        modal: true,
	        width: 600,
	        style:"background-color: #8c000d;color: #fff !important;",
	        cssClass: "ui-model",
	        destroyOnHide: true,
        resizable: false,
	    },
	    id: "<portlet:namespace />popUpId",
	    title: "Birthday Wishes",
	    uri: url
	}); 
	  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
     Liferay.Util.Window.getById(popUpId).destroy();
     location.reload();
 },
 ['liferay-util-window']
 ); 
} --%>
</script>