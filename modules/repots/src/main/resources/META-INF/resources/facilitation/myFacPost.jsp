<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="com.ongc.liferay.model.User"%>
<%@page import="com.ongc.liferay.dao.UserDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ include file="/init.jsp" %>
<script type="text/javascript">
	$(function() {
		var screenTop;
		function top() {
			screenTop = $(document).scrollTop();
			$('.bigImage').css({
				'top' : screenTop - 220,
			});
		}
		$(".thImage").mouseover(function() {
			$(".bigImage").show();
			var imgsrc = $(this).attr('src');
			$(".bigImage img").attr('src', imgsrc);
			top();
		});
		$(".thImage").mouseout(function() {
			$(".bigImage").hide();
			$(".bigImage").css({
				'top' : '',
				'left' : ''
			})
			$(".bigImage img").attr('src', " ");
		});
	});
</script>

<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>

<portlet:resourceURL id="archive" var="archiveURL"></portlet:resourceURL>

<portlet:renderURL var="facilitationPostOffer" > 
 	<portlet:param name="mvcPath" value="/facilitation/facilitationPostForm.jsp"/>
 </portlet:renderURL>
<portlet:renderURL var="facilitationOfferSummery" > 
 	<portlet:param name="mvcPath" value="/facilitation/facilitationSummery.jsp"/>
 </portlet:renderURL>
<portlet:renderURL var="facilitation" > 
 	<portlet:param name="mvcPath" value="/facilitation/facilitation.jsp"/>
 </portlet:renderURL>
	<div class="row">
	<div class="col-md-12 text-right">
	<a href="<%= facilitation %>" class="btn btn-primary">View Offers</a>
<a href="<%= facilitationOfferSummery %>" class="btn btn-primary">Offer Summary</a>
<a href="<%=facilitationPostOffer %>" class="btn btn-primary">Post Offer</a>
	</div>
	</div>

	<div id="paging_container3" class="fc-container facilitationPost">
		<div class="alt_page_navigation"></div>
	<div class="row">
	<div class="col-lg-12">
			<div class="alert alert-danger" role="alert" style="display: none;"
				id="alert"></div>
		</div>
  <div class="col-sm-12">
			<%
				Statement stmt = null;
				ResultSet res = null;
				Connection conn = null;
				try {
					UserDao userDao = new UserDao();
					User userOngc = userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress(),themeDisplay.getUser().getScreenName());
					String cpf = userOngc.getCpfNo();
					String queryPart = "select id, cpf_no, name, mobile, email, location, category, title, discription, TO_CHAR(created_on, 'DD-MON-YYYY'), status, photos from ongc_facilitation where status='A' and cpf_no='"
							+ cpf + "' ORDER BY Created_on DESC";
					//System.out.println("TEST: " + queryPart);
					conn = DatasourceConnection.getConnection();
					stmt = conn.createStatement();
					res = stmt.executeQuery(queryPart);
					boolean empty = true;
					while (res.next()) {
						// System.out.println("TEST: "+queryPart);
						empty = false;
			%>
	<div class="my-post" id="data-<%=res.getString(1)%>">	
				<div class="d-flex justify-content-between">
							 <h3 class="heading-clr mt0 mb0 col-xs-6"><%=res.getString(8)%></h3>
							<%
								if (res.getString(2).equalsIgnoreCase(cpf)) {
							%>
							<a href="javascript:archive('<%=res.getString(1)%>')" class="btn btn-primary">Archive</a>
							<%-- <form class="col-xs-6" id="facilitation-archive" method="post"
								action="pg-home-Facilitation-Archive">
								<input type="hidden" value=<%=res.getString(1)%> name="posId" />
								<input type="hidden" value=<%=cpf%> name="cpf" /> <span>
									<input class="btn btn-primary" type="submit" value="Archive">
								</span>
							</form> --%>
							<%
								}
							%>
</div>
						<div class="date"> <%=res.getString(10)%>
							<%-- <%
								if (res.getBlob(12) != null) {
							%> --%>
							<div class="imageWrapper">
								 <img src="<%= themeDisplay.getURLPortal() %>/o/blade/commonServlet/imageFile?imgID=<%= res.getString(1)%>&type=facilitation" class="thImage"/>
							</div> <%-- <%
 	}
 %> --%>
						</div>
						<div class="blank1"></div>
					<p><%=res.getString(9)%></p>
					<ul class="list-inline ul-r-bdr m">
					<li><%=res.getString(7)%> </li>
					<li  title="<%=res.getString(6)%>"><%=res.getString(6)%></li>
					<li> <a title="View Profile" data-toggle="modal" data-target="#myModal" href="#" onclick="javascript:popup(<%=res.getString(2)%>);" rel="nofollow" ><%=res.getString(3)%></a> </li>
					 <li class="bdr-af-none"><%=res.getString(4)%> </li>
						<div class="blank1"></div>
					</ul>
					<div class="blank1"></div>
					</div>
			<%
				}
					if (empty) {
			%>
			<div class="alert alert-danger">No Post Available</div>
			<%
				}
			%>
			<%
				} catch (Exception e) {
					System.out.println("Exception myFacPost jsp " + e);
					e.printStackTrace();
				} finally {
					if (res != null)
						res.close();
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
				}
			%>
		</div>
		</div>
		<div class="blank1"></div>
	</div>
	<script>
	function archive(postId) {
		AUI().use(
				'aui-io-request',
				function(A) {
					A.io.request('${archiveURL}', {
						method : 'POST',
						dataType : "json",
						cache : true,
						data : {
							<portlet:namespace/>postId : postId,
						},
						on : {
							success : function() {
								 var responseData = new Array();
									responseData = this.get('responseData');
									for (var i = 0; i < responseData.length; i++) {
										var id = responseData[i]['flageKey'];
										 $('#alert').append('Your post archived successfully.');
											$('#alert').css("display", "block");
										setTimeout(function(){
								            $('#alert').slideUp(500, function(){ $(this).remove(); });
								        }, 5000);
										if(id){
										$('#data-'+postId).remove();}
									}
							},
							error : function() {
								alert("Error occured on server.");
							}
						}
					});
				});
	}

	function popup(cpfNo){
		 var url="<%=getEmployeeData %>&<portlet:namespace />cpfNo="+cpfNo;
		Liferay.Util.openWindow({
		    dialog: {
		        centered: true,
		        height: 600,
		        modal: true,
		        width: 800,
		        style:"background-color: #8c000d;color: #fff !important;",
		        cssClass: "ui-model",
		        destroyOnHide: true,
	        resizable: false,
		    },
		    id: "<portlet:namespace />popUpId",
		    title: "Employee Details",
		    uri: url
		}); 
		  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
	     Liferay.Util.Window.getById(popUpId).destroy();
	     location.reload();
	 },
	 ['liferay-util-window']
	 ); 
	}
</script>