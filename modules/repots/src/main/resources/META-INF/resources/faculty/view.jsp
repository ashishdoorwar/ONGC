<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.ongc.liferay.model.FacultyBean"%>
<%@page import="com.ongc.liferay.dao.FacultyDao"%>
<%@ include file="/init.jsp" %>
<%@ page import="java.util.List"%>
<%
FacultyDao _fdao= new FacultyDao();

String cpf=null;
List<FacultyBean> fclist;

fclist=  _fdao.getFacData(0);
if(fclist.size() >0  ){
 HttpSession res=request.getSession();
 res.setAttribute("fclist", fclist);
}
		
 %>
<portlet:renderURL var="getEmployeeData" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcRenderCommandName" value="getEmployeeInfo"/>
</portlet:renderURL>
<script>
$( document ).ready(function() {
	//  var uCpfId = $("#uniqueCpf");
	    var uCpfId = '122515';
		   if( uCpfId=='76121' || uCpfId=='78619' || uCpfId=='122515' )
      {
        $('#link').attr('style','display: block');
      }
		});
</script>
 <hr size="4" color="gray"/>
 <div id="link" style="display: none;">
 <div class="text-right mb10">
  <%-- <a href="<%= userCSVDataUploadURL %>" class="btn btn-primary">Export to Excel</a> --%>
   <button id="btnExport" class="btn btn-primary" onclick="ExcelReport();">Export to Excel</button>
  </div>
 <table id="tableData" class="table table-striped "> 
 <thead>
 <tr >
			<th >S No.</th>
            <th>CPF No</th>
            <th >Name</th>
			<th >Specialization</th>
			<!-- <th width="15%">Program title</th>  -->
			<th >Program code</th>
			<th>Posted On</th>
			<th>Action</th>
			   
 </tr>
</thead>
<tbody>
    <c:forEach items="${fclist}" var="faculty" varStatus="fl">
        	<tr>
			<td >
			<c:out value="${fl.index+1}" />
			</td>
			<td >
			<c:out value="${faculty.cpfno}" />
			</td>
			<td  >
			<c:out value="${faculty.name}" />
			</td>
			
			<td >
			<c:out value="${faculty.specialisation}" />
			</td>
			<!-- <td width="15%">
			<c:out value="${faculty.title}" />
			</td>
			 -->
			<td  >
			<c:out value="${faculty.code}" />
			</td>
			<td>
			<c:out value="${faculty.posted_date}" />
			</td>
			 <td><a href="#" onclick="javascript:popup('${faculty.facid}');" rel="nofollow" class="defultchi2 blue">View</a></td>
			
			
			</tr>
    </c:forEach>
    </tbody>	
	
</table>
</div>
<script type="text/javascript">
function popup(facid){
	 var url="<%=getEmployeeData %>&<portlet:namespace />facid="+facid;
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
	    title: "Faculty Details",
	    uri: url
	}); 
	  Liferay.provide(window, '<portlet:namespace />closePopup', function(popUpId , id) {
        Liferay.Util.Window.getById(popUpId).destroy();
        location.reload();
    },
    ['liferay-util-window']
    ); 
}

			 $(document).ready(function() {
				 var table = $('#tableData').DataTable( {
					 lengthChange: false,bFilter: false, bInfo: false
				    });
				}); 
			 </script>

  <script type="text/javascript">
         function ExcelReport()
         {
         var tab_text="<table border='2px'><tr bgcolor='#FFFFFF'>";
         var textRange; var j=0;
         tab = document.getElementById('tableData'); // id of table

for(j = 0 ; j < tab.rows.length ; j++) 
{     
    tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";       
}

tab_text=tab_text+"</table>";
tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");
tab_text= tab_text.replace(/<img[^>]*>/gi,""); 
tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); 
var ua = window.navigator.userAgent;
var msie = ua.indexOf("MSIE "); 

if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      
{
    txtArea1.document.open("txt/html","replace");
    txtArea1.document.write(tab_text);
    txtArea1.document.close();
    txtArea1.focus(); 
    sa=txtArea1.document.execCommand("SaveAs",true,"facultydata.xls");
}  
else                 
    sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

   return (sa);
  }

  </script>      