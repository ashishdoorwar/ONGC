<%@ include file="/init.jsp"%>
<liferay-theme:defineObjects />
<portlet:defineObjects />
<%
	long preiousAssetEntryId = (long) request.getAttribute("previousEntryId");
	long nextEntryId = (long) request.getAttribute("nextEntryId");
	long nextClassPk = (long) request.getAttribute("nextClassPk");
	long previousClassPk = (long) request.getAttribute("previousClassPk");
	String previousTitleUrl = (String) request.getAttribute("previousTitleUrl");
	String nextTitleUrl = (String) request.getAttribute("nextTitleUrl");
%>
<div>
	<div class="row">
		<div class="col-md-12 text-right">
			<%
			try{
			if(assetEntryURL!=null){
				if (preiousAssetEntryId != 0) {
			%><a onclick="previous()">Previous
				Story</a>
			<%
				}
			%>
			|
			<%
				if (nextEntryId != 0) {
			%><a href="javascript:next()">Next Story</a>
			<%
				}} else{
			%>
			<a onclick="previousTitleUrl()">Previous
				Story</a> | <a href="javascript:nextTitleUrl()">Next Story</a>
				<%}} catch(Exception e){e.printStackTrace();} %>
		</div>
	</div>
</div>
<script>
function previous(){
	window.location.href='<%=portalUrl%><%=currentPage%>?assetEntry=<%=preiousAssetEntryId%>&assetClassPK=<%=previousClassPk%>';}
function next(){
	window.location.href='<%=portalUrl%><%=currentPage%>?assetEntry=<%=nextEntryId%>&assetClassPK=<%=nextClassPk%>';
	}
function previousTitleUrl(){
	window.location.href='<%=portalUrl%>/web/reports_en/w/<%=previousTitleUrl%>';}
function nextTitleUrl(){
	window.location.href='<%=portalUrl%>/web/reports_en/w/<%=nextTitleUrl%>';
	}
</script>