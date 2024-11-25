function ComplaintAjaxRequest(l_URL, l_Params, l_FrmMethod, l_ResponseMethod){
	this.URL = l_URL;
	this.Params = l_Params;
	this.FrmMethod = l_FrmMethod;
	this.RespMethod = l_ResponseMethod;
	this.makeRequest();
}
ComplaintAjaxRequest.prototype = {
	makeRequest: function(){
		var _httpRequest = false;
		if (window.XMLHttpRequest) {
			_httpRequest = new XMLHttpRequest();
			if (_httpRequest.overrideMimeType) {
			// set type accordingly to anticipated content type            //http_request.overrideMimeType('text/xml');            			http_request.overrideMimeType('text/html');
			}
		} else if (window.ActiveXObject) { // IE
			try {
				_httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					_httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
		if (!_httpRequest) {
			alert('Cannot create XMLHTTP instance');
			return false;
		}
		var _tx = this;
		_httpRequest.onreadystatechange = function() {
			
			if (_httpRequest.readyState == 4) {
				if (_httpRequest.status == 200) {
				//	eval(_tx.RespMethod + "(_httpRequest)");
					document.getElementById(_tx.RespMethod).innerHTML = _httpRequest.responseText;
				} else {
					alert('There was a problem with the request.');
				}
			}
		};
		_httpRequest.open(this.FrmMethod, this.URL , true);
		_httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    _httpRequest.setRequestHeader("Content-length", this.Params.length);
		_httpRequest.setRequestHeader("Connection", "close");
		_httpRequest.send(this.Params);
	}
};



function callComplaintPage(index){
	
	var url="/admin/paggingAdminHome.jsp";
 var	params="startWith="+ index;
	var pagecall=new ComplaintAjaxRequest(url,params,"post","complaintTD");
}
function callCompltUpdatePage(index){
	
	var url="/admin/paggingUpdateComplaintList.jsp";
 var	params="startWith="+ index;
	var pagecall=new ComplaintAjaxRequest(url,params,"post","complaintTD");
}
function callCompltSearchPage(index){
	console.log(window.location);
	console.log("/admin/paggingMonthlyRptCompltList.jsp");
	
	var url="/admin/paggingSearchCompltList.jsp";
 var	params="startWith="+ index;
	var pagecall=new ComplaintAjaxRequest(url,params,"post","complaintTD");
}
function callPeriodicRptPage(index){
	
	var url="<%= renderRequest.getContextPath() %>/admin/paggingPeridicRptCompltList.jsp";
 var	params="startWith="+ index;
	var pagecall=new ComplaintAjaxRequest(url,params,"post","complaintTD");
}
function callMonthlyRptPage(index){
	
	var url="<%= renderRequest.getContextPath() %>/admin/paggingMonthlyRptCompltList.jsp";
 var	params="startWith="+ index;
	var pagecall=new ComplaintAjaxRequest(url,params,"post","complaintTD");
}
function viewcomplaintDtlpage(pid){
	document.complaintForm.compId.value=pid;
	document.complaintForm.submit();
}
function viewcomplaintpage(pid){
	document.complaintForm.compId.value=pid;
	document.complaintForm.submit();
}

// added by Bhanu

function viewcomplaintpage1(pid){
	document.complaintForm.compId.value=pid;
	document.complaintForm.submit();
}
