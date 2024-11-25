var req;


function ajaxFunction()
{
    var xmlHttp
    try
    {
        xmlHttp = new XMLHttpRequest()
    }
    catch (e)
    {
        try
        {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")
        }
        catch (e)
        {
            try
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")
            }
            catch (e)
            {
                alert("Your browser does not support AJAX!")
                return false
            } 
        } 
    }
    return xmlHttp
}
function getHitCountData(){
/*	var URL='/wps/PA_Vigilance/admin/hitCount.jsp';
req= ajaxFunction();
req.open('GET',URL , false);
//alert(URL);
req.onreadystatechange =refreshHitCount;
req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
req.setRequestHeader("Content-length","12");
req.setRequestHeader("Connection", "close");
req.send(this.Params);*/

}

function refreshHitCount(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
	
	xmlDoc = req.responseXML;
	var ss=xmlDoc.getElementsByTagName("visitor");
	var node1=ss[0].getElementsByTagName("hitcount");
	var count=node1[0].firstChild.nodeValue;
	 var x=count.length;
	 var _html='';
	 for(var a=0;a<9;a++){
		 if(a<x){
		_html=_html+'<span>'+count.charAt(a)+'</span>';	 
		 }
		 else{
			 _html='<span>'+0+'</span>'+_html;	
		 }
	 }
	
	document.getElementById("hitcount").innerHTML=_html;	
		
		}
		
		}
	
}




getHitCountData();

