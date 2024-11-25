<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.reports.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@ include file="/init.jsp" %>

<%
String url=(themeDisplay.getURLCurrent().substring(themeDisplay.getURLCurrent(). lastIndexOf("/") + 1). trim());
if(url.equals("happy-birthday")){%>
<%@ include file="/birthday/showBirthdayPage.jsp"%>
<% } else {
List<User> users=(List<User>)request.getAttribute("users");
%>
<style>
.btnSec{
	font-family: 'Open Sans', sans-serif;
    font-size: 16px;
    margin-right: 20px;
    display: inline-block;
    color: white;
    padding-left: 15px;
    text-align: right;
    margin-bottom: 10px;
    }
    .bgBlue {
    background: #3190d4;
}.mt10 {
    margin-top: 10px !important;
}
.mn {
    margin: 0 !important;
}
</style>
 <portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<portlet:renderURL var="showBirthday" > 
 	<portlet:param name="mvcRenderCommandName" value="gettodayBirthday"/>
 </portlet:renderURL>
<div class="birthdaySec">
	<div class="row">
	<div class="col-md-12 pl-0">
		<div class="happyBirthBox">
			<div class=" position-relative">
				<div class="row mn birthDaySlider">
					<div class="col-md-12 pn">
						<div class="carousel slide text-center" id="carousellogo" data-ride="carousel">
							<div class="carousel-inner">
							<%int count=0;
								if (!users.isEmpty()){
							%>
							<%for(int i=0;i<users.size()/4;i++){ %>
								
								<%if(i==0){ %>
									<div class="carousel-item active">
								<%}else{ %>
								
									<div class="carousel-item ">
								<%} %>
									<div class="col-sm-3" id="<%= users.get(count).getCpfNo()%>">
										<div class="boxBlue">
											<div class="imgSec">
												<a href="javascript:popup('<%= users.get(count).getCpfNo()%>')" title="">
													<img src="<%=request.getContextPath()%>/images/birthDayIcon.png" alt="User" class="blueBdr">
												</a>
											</div>
	                                       	<div class="carousel-caption">
												<a onclick="javascript:popup('<%= users.get(count).getCpfNo()%>')"><%=users.get(count).getEmpName() %> </a>
											</div>
										</div>
									</div>
									<%if(count<users.size()) { count++;  %>
									<div class="col-sm-3" id="<%= users.get(count).getCpfNo()%>">
										<div class="boxBlue">
											<div class="imgSec">
												<a  href="javascript:popup('<%= users.get(count).getCpfNo()%>')" title="">
													<img src="<%=request.getContextPath()%>/images/birthDayIcon.png" alt="User" class="blueBdr">
												</a>
											</div>
	                                       	<div class="carousel-caption">
												<a onclick="javascript:popup('<%= users.get(count).getCpfNo()%>')"><%=users.get(count).getEmpName() %> </a>
											</div>
										</div>
									</div>
									<%} if(count<users.size()) { count++; %>
									<div class="col-sm-3" id="<%= users.get(count).getCpfNo()%>">
										<div class="boxBlue">
											<div class="imgSec">
												<a  href="javascript:popup('<%= users.get(count).getCpfNo()%>')" title="">
													<img src="<%=request.getContextPath()%>/images/birthDayIcon.png" alt="User" class="blueBdr">
												</a>
											</div>
	                                       	<div class="carousel-caption">
												<a onclick="javascript:popup('<%= users.get(count).getCpfNo()%>')"><%=users.get(count).getEmpName() %> </a>
											</div>
										</div>
									</div>
									<%} if(count<users.size()) { count++; %>
									<div class="col-sm-3" id="<%= users.get(count).getCpfNo()%>">
										<div class="boxBlue">
											<div class="imgSec">
												<a  href="javascript:popup('<%= users.get(count).getCpfNo()%>')" title="">
													<img src="<%=request.getContextPath()%>/images/birthDayIcon.png" alt="User" class="blueBdr">
												</a>
											</div>
	                                       	<div class="carousel-caption">
												<a onclick="javascript:popup('<%= users.get(count).getCpfNo()%>')"><%=users.get(count).getEmpName() %> </a>
											</div>
										</div>
									</div>
									<%} count++; %>
								</div>
	                         <%}} %>
	                           </div>
	                          <a class="carousel-control-prev" href="#carousellogo" data-slide="prev">
                                <img src="<%=request.getContextPath()%>/images/prevIcon.jpg" alt="left Arrow">
                            </a>
                            <a class="carousel-control-next" href="#carousellogo" data-slide="next">
                                <img src="<%=request.getContextPath()%>/images/nextIcon.jpg" alt="left Arrow">
                            </a>
	                       </div>
	                   </div>
	               </div>
	           	</div>
	           	  </div> 	  
	 <a href="<%=themeDisplay.getURLPortal() %>/group/reports_en/happy-birthday">
	 <div class="btnSec bgBlue mn mt10">View All <i class="fa fa-chevron-right darkBlue" aria-hidden="true"></i></div></a>
		</div>
	</div>
	</div>
<script>
function popup(cpfNo){
	 var url="<%=getEmployeeData%>&<portlet:namespace />cpfNo="+cpfNo;
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
<% } %>