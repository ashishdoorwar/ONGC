
<%@ include file="/init.jsp"%>
	<!-- 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
  
<script src='<%=request.getContextPath() %>/css/jquery.marquee.js'></script>
<style>
a {
    color: #333;
    text-decoration: none;
}
.carousel-caption {
    padding: 20px 10px 0 10px;
    position: static;
    text-align: center;
    text-shadow: none;
    font-size: 13px;
    right: 20%;
    left: 20%;
}
.btnSec {
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
   <portlet:renderURL var="showBirthday" > 
 	<portlet:param name="mvcRenderCommandName" value="gettodayBirthday"/>
 </portlet:renderURL>
 <portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<div class="happyBirthday">
	<h2>Happy Birthday</h2>
	<div class="row mn">
		<div class="col-md-12 pn">
			<div class="carousel slide text-center" id="carousellogo" data-ride="carousel">
				<div class="carousel-inner">
					<%
try{
	List<User> list=(List<User>) request.getAttribute("birthday");
 %>
					<%if(list!=null ){ 
 for(int i=0;i<list.size();i++){
 User userOngc=list.get(i);
 String Designation=userOngc.getDesignation();
 String Email=userOngc.getEmailIdOfficial();
 String WorkPlace=userOngc.getWorkPlace();
 if(Designation==null)
 Designation="-";
 if(Email==null)
 Email="-";
 if(WorkPlace==null)
 WorkPlace="-";
 %>
<%if(i==0){ %>
									<div class="carousel-item active">
								<%}else{ %>
								
					<div class="carousel-item"><%} %>
						<div class="col-xs-6">
							<div class="boxBlue">
								<div class="imgSec">
									<a title="<%=userOngc.getEmpName() %>"
										href="javascript:popup(<%= userOngc.getCpfNo() %>);">
										<img src="<%=request.getContextPath() %>/images/birthday-cake-clipart.jpg" alt="User"
										class="blueBdr">
									</a>
									<div class="icon bgGreen">
										<i class="fa fa-briefcase "></i>
									</div>
								</div>
								<div class="carousel-caption">
									<a title="<%=userOngc.getEmpName() %>"
										href="javascript:popup(<%= userOngc.getCpfNo() %>);"
										rel="nofollow"> <%=userOngc.getEmpName() %></a>
								</div>

							</div>
						</div>
					</div>
					<%} }
}
catch(Exception e)
{

}
%>
				</div>
				<a class="left carousel-control" href="#carousellogo"
					data-slide="prev"> <img src="<%=request.getContextPath() %>/images/prevIcon.png"
					alt="left Arrow">
				</a> <a class="right carousel-control" href="#carousellogo"
					data-slide="next"> <img src="<%=request.getContextPath() %>/images/nextIcon.png"
					alt="left Arrow">
				</a>
			</div>
			</div>
			<div class="col-lg-12 text-right" style="float:right">
				<a
					href="<%= showBirthday %>"><div
						class="btnSec bgBlue mn mt10">
						View All <i class="fa fa-chevron-right darkBlue"
							aria-hidden="true"></i>
					</div></a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
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