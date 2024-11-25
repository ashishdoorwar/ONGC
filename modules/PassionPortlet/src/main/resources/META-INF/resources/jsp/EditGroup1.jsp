<%@page import="com.ongc.liferay.passion.model.Group"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.ongc.liferay.passion.model.Employee"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.GroupDaoImpl"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp"%>
<link href="<%=request.getContextPath()%>/css/multiselect-box-style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/multiselect-style.css" rel="stylesheet">
<script type="text/javascript">
$(window).bind("pageshow", function() {
	 $('form').get(0).reset();
   
});
</script>
<style>
      
	  .container{
			width: 403px;
			cursor:text;  
	  }
	  .k-input{
			border: none !important;  
	  }
	  .k-multiselect-wrap{
			clear: both !important;   
	  }
</style>
<!-------------Create Group Section start----------------------->
<%
GroupDaoImpl gDao=new GroupDaoImpl();
String gId=ParamUtil.getString(request, "gId");
List memList=gDao.fetchMembers(gId);
String gName=gDao.getGroupNameById(gId);
if(gName.contains("\"")) {
gName=gName.replaceAll("\"","&quot;");
}


%>
<div class="contentarea">

    	<div class="row"><div class="col-md-12"><h2>Group/Categories</h2></div></div>
        <div class="discusstionLeft">
        		 <div class="row"><div class="col-md-12"><h5> Modify Group</h5></div></div>
                
            <div class="post">
                <div class="grp_crt_table">
                
<s:form theme="simple" action="updateGroup" onsubmit="return(validateGroupForm());" method="post">
<input type="hidden" name="group.groupId" value="<%= gId%>"/>
<table class="grp_crt_table01">
<colgroup>
<col width="27%"/>
<col width="73%"/>
</colgroup>

<tr><td>Group Name <%= memList.size() %> <span style="color: red;">*</span></td><td><input id="groupName" name="group.groupName" class="grp_inpt" type="text" value="<%= gName%>" maxlength="50" /></td></tr>
<tr><td>Add Members<span style="color: red;">*</span></td><td> <div class="container">
<div class="row">
<div id="emplist" class="span4">
<select id="members" multiple="multiple" name="group.members">
        
<%
	Iterator it1=memList.iterator();
	while(it1.hasNext())
	{   
		Group groupmem=(Group)it1.next();
%>
	<option value="<%=groupmem.getMemberCpf() %>" selected > <%=groupmem.getMemberName()%></option>
<%    
	}
%>
</select>
</div>    
</div>
</div>
</td></tr>
  
<tr><td>&#160;</td><td><input class="grp_btn" type="submit"  value="Submit"/>
<s:a action="showGroup"><input type="button" value="Cancel" class="grp_btn"/></s:a>
</td></tr>

</table>
</s:form>
<br/>
<div class="mandatoryField">Fields marked as (<span style="color: red;">*</span>) are mandatory</div>
</div>
                
              </div>
            
        </div>
        
  
    
    
</div>



<script src="<%=request.getContextPath()%>/js/multiselect.js"></script>
     <script>
     $(document).ready(function() {
    	 
    	// alert($("#emplist").children('div').children('div').find("input"));
    	
    	    $("#members").kendoMultiSelect({
    	    	placeholder: "Select...",
    	    	autoClose: false,
    	        dataTextField: "empName",
    	        dataValueField: "empCpf"
    	        
    	    });
    	    
    	    $("#emplist").children('div').children('div').find("input").keyup(function(e) {
    	   	var a=$(this).val();
    	   	var cpfNo='100002';
    	   	if(a.length==1){
    	    var dataSource = 
    	    	{
    	            transport: {
    	                read: {
    	                    dataType: "json",
    	                    url: "/wps/PA_ONGC_Passion/jspPages/web/test.jsp?q="+a+"&cpfNo="+cpfNo
    	                }
    	            }
    	        }
    	    	
    	    	var multiselect = $("#members").data("kendoMultiSelect");
    	    	multiselect.setDataSource(dataSource);
    	   
    	   	}
    	 });
    	    $(".k-floatwrap").append("<div style='clear:both'></div>");
    	});
    	
    </script>




<!-------------Create Section end----------------------->