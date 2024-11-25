<%@page import="org.mcavallo.opencloud.Tag"%>
<%@page import="org.mcavallo.opencloud.Cloud"%>
<%@page import="com.ongc.liferay.passion.model.Group"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.ongc.liferay.passion.dao.Impl.GroupDaoImpl"%>
<%@page import="com.ongc.liferay.passion.model.User"%>
<%@page import="com.ongc.liferay.passion.service.Impl.UserServiceImpl"%>
<%@page import="com.ongc.liferay.passion.service.UserService"%>
<%@page import="com.ongc.liferay.passion.util.TagCloud"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@ include file="/init.jsp"%>
<script>
function showDiscussion(tagform,id){
	document.getElementById("tagKey").value=id;
	
	document.tagform.action="showDiscussion";	
	tagform.submit();
}

$(window).bind("pageshow", function() {
	 $('form').get(0).reset();
   
});

</script>

<%
UserService userService = new UserServiceImpl();
User userData = userService.getUser();
String loggedInCpf=userData.getCpfNo();
GroupDaoImpl gDao = new GroupDaoImpl();
List glist = gDao.fetchGroups(userData.getCpfNo());


 %>
 
<portlet:actionURL var="submitPost" name="submitPost">
		</portlet:actionURL>
<!-------------Discussion Board Section start----------------------->
<div class="contentarea">
	<div class="left">
    	<div class="row"><h2>Discussion Board</h2></div>
        <div class="discusstionLeft">
        		 <div class="row"><h5>Post Topic</h5></div>
        		 <div class="post">
        		 
        		  <liferay-ui:error key="error" message="Illegal argument found" /> 
        		 <aui:form action="${submitPost}" name="fm" method="post">
	<aui:fieldset-group markupView="lexicon">
		<aui:row>
			<aui:col width="100">
				<aui:input label="Topic Name" name="topicName" type="text" />
			</aui:col>

		</aui:row>
		<aui:row>
			<aui:col width="100">
				<aui:input label="Topic Description" name="topicDesc" type="text" />
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="100">
				<aui:select label="Select Group" name="groupId" >
				<aui:option value="">Select</aui:option>
				<%Iterator it = glist.iterator();
				while (it.hasNext()) {
					Group group = (Group) it.next();
					%>
					<aui:option value="<%=group.getGroupId()%>"><%=group.getGroupName() %></aui:option>
				<%} %>
				
				</aui:select>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="100">
				<aui:select name="visibleTo" label="Type of Visibility">
				<aui:option value="">Select</aui:option>
				<aui:option value="P">Public</aui:option>
				<aui:option value="G">Group</aui:option>
				</aui:select>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="100">
				<aui:input label="Tags" name="tags" type="text" />
			</aui:col>
		</aui:row>
	</aui:fieldset-group>
	<aui:button-row cssClass="text-center mt-2">

		<aui:button cssClass="btn btn-primary" type="submit" value="Submit" />

		<aui:button cssClass="btn btn-primary" value="Cancel" />

	</aui:button-row>
</aui:form>
<%-- <s:form action="submitPost.action" onsubmit="return(validateTopicForm());" method="post" theme="simple">
<div style="margin-bottom:15px; margin-top:20px;">
<label class="lable">Topic Name<span style="color: red;">*</span></label>
<s:textfield id="topicName" name="disboard.topicName" value="" maxlength="80" placeholder="maxlength 80 character" onfocus="this.placeholder = ''" onblur="this.placeholder = 'maxlength 80 character'"></s:textfield>
</div><div>
<label class="lable">Topic Description<span style="color: red;">*</span></label>
<s:textarea id="topicDesc" name="disboard.topicDesc" value="" maxlength="250" style="width:380px;" placeholder="maxlength 250 character" onfocus="this.placeholder = ''" onblur="this.placeholder = 'maxlength 250 character'"></s:textarea>
</div><div>
<label class="lable">Type of Visibility<span style="color: red;">*</span></label>
<s:radio id="visibleTo" name="disboard.visibleTo" list="#{'P':'Public','G':'Group'}" onclick="myFunction()" />
</div>
<script>
function myFunction() {
	
			if (document.getElementById('visibleToG').checked) {
         	
         	document.getElementById('abcd').style.display = 'block';
         	}
         	
         	if (document.getElementById('visibleToP').checked) {
         	
         	document.getElementById('abcd').style.display = 'none';
         	}
}
</script>

<div id="abcd" style="display:none">
<label class="lable">Select Group<span style="color: red;">*</span></label>
<s:select id="demo-htmlselect-basic" name="disboard.groupId" list="gList" headerKey="-1" headerValue="Select Group" listValue="groupName" listKey="groupId" theme="simple">
</s:select>
</div>
<input type="hidden" name="disboard.groupId" id="groupId"/>
<div>
<label class="lable">Tags<span style="color: red;">*</span></label>
<s:textfield id="tags" name="disboard.tags" maxlength="20" placeholder="maxlength 20 character" onfocus="this.placeholder = ''" onblur="this.placeholder = 'maxlength 20 character'"></s:textfield></div>
<div class="disTopics">
<s:submit name="buttonName" value="Publish" cssClass="button" />
<s:a action="showDiscussion"><input type="button" value="Cancel" class="button"/></s:a></div>
</s:form> --%>
<div class="mandatoryField">Fields marked as (<span style="color: red;">*</span>) are mandatory</div>
</div>

<div class="clearboth"> </div>
            
        </div>
        
         
    </div>
    
    <div class="right">
    	
        <div class="discusstionLeft mtop">
        
	<div class="row" style="margin-top:5px;"><h5><s:a style="color:#fff;" action="createGroup">Create New Group</s:a></h5></div></div>

        <div class="discusstionLeft mtop" style="margin-top:10px;">
        	<ul class="cloud">
        	<%
				TagCloud tc=new TagCloud();
				Cloud cloud=tc.discussionTag(loggedInCpf);
				cloud.setMaxTagsToDisplay(10);
				cloud.setMaxWeight(20.0);
				cloud.setMinWeight(10.0);
				// cycle through output tags
				for (Tag tag : cloud.tags()) {
				    // add an HTML link for each tag
			%>
				<portlet:renderURL var="viewDissCloud1">
							<portlet:param name="mvcRenderCommandName"
								value="view_Discussion_Cloud" />
							<portlet:param name="q" value="<%=tag.getName()%>" />
						</portlet:renderURL>
						<li><a href="<%=viewDissCloud1%>"><%=tag.getName()%></a></li>
			<%
				} 
			%>
            </ul>
    </div>
</div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ddslick.min.js"></script>
	<script type="text/javascript">
    /*
  jQuery Document ready
*/
$(function()
{	
	$('#demo-htmlselect-basic').ddslick(
	{
		onSelected: function(data)
		{
			displaySelectedData("Callback Function on Dropdown Selection" , data);
		}
	});
	
	$('#make-it-custom').on('click', function()
	{
	
		$('#demo-htmlselect').ddslick(
		{
			onSelected: function(data)
			{
				displaySelectedData("Callback Function on Dropdown Selection" , data);
			}
		});
	});

	$('#restore').on('click', function()
	{
		$('#demo-htmlselect').ddslick('destroy');
		$('#dd-display-data').empty();
	});
	
	function displaySelectedData(demoIndex, ddData)
	{
		$('#dd-display-data').html("<h3>Data from Demo " + demoIndex + "</h3>");
		$('#dd-display-data').append('<strong>selectedIndex:</strong> ' + ddData.selectedIndex + '<br/><strong>selectedItem:</strong> Check your browser console for selected "li" html element');
		
		if (ddData.selectedData)
		{
			$('#dd-display-data').append
			(
				'<br/><strong>Value:</strong>  ' + ddData.selectedData.value +
				'<br/><strong>Description:</strong>  ' + ddData.selectedData.description +
				'<br/><strong>ImageSrc:</strong>  ' + ddData.selectedData.imageSrc
			);
		}
		
	}
	
	
})
 </script>
 
 
 <script type="text/javascript">
    function customRadio(radioName){
        var radioButton = $('input[type="'+ radioName +'"]');
        $(radioButton).each(function(){
            $(this).wrap( "<span class='radio'></span>" );
            if($(this).is(':checked')){
                $(this).parent().addClass("radio-on");
            }
        });
        $(radioButton).click(function(){
            if($(this).is(':checked')){
                $(this).parent().addClass("radio-on");
            }
            $(radioButton).not(this).each(function(){
                $(this).parent().removeClass("radio-on");
            });
        });
    }
    $(document).ready(function (){
        customRadio("radio");
        customRadio("search-engine");            
        customRadio("confirm");
    })
</script>