
function validateCommentForm() {
	   
	        //  var nameSpchar=/^[a-zA-Z0-9\n.-.,-,---_-_(-()-) ]*$/;
	         var nameSpchar = /^[0-9a-zA-Z\s\r\n@!%#\$\^%&*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;  
	   	var messagString="";
		messagString=FieldValiDationWithFocuse("comment",nameSpchar,"Special Characters are not allowed.","Please Enter Comment.", messagString);
   		if(trim(messagString)!=""){
   			alert(messagString);
   			return false;
   		}
   		messagString=checkLength("comment","Comment should less than 100 characters.",100,messagString);
   		if(trim(messagString)!=""){
   			alert(messagString);
   			return false;
   		}
   		return true;
}

function validateReplyForm(idNum) {
   		
   		//var nameSpchar=/^[a-zA-Z0-9.-.,-,---_-_(-()-) ]*$/;	
                 var nameSpchar = /^[0-9a-zA-Z\s\r\n@!%#\$\^%&*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;  	
   		var messagString="";
   	 	messagString=FieldValiDationWithFocuse("rplyat"+idNum,nameSpchar,"Special Characters are not allowed.","Please Enter Reply.", messagString);
   		if(trim(messagString)!=""){
   			alert(messagString);
   			return false;
   		}
   		messagString=checkLength("rplyat"+idNum,"Reply should less than 100 characters.",100,messagString);
   		if(trim(messagString)!=""){
   			alert(messagString);
   			return false;
   		}
   		return true;
}
	
function validateTopicForm() {
	   
	   var messagString="";
		messagString=ErroSuggestMessage(messagString);
		if(messagString!="") {
			alert(messagString);
			return false;
		}
		return true;
	   
}

function ErroSuggestMessage(messagString) {
	//var nameSpchar=/^[a-zA-Z0-9.$-.,-,---_-_(-()-) ]*$/;
       var nameSpchar = /^[0-9a-zA-Z\s\r\n@!#\$\^*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/; 
     //  var textAreaSpchar=/^[a-zA-Z0-9\n?@&()$/:;!#.-.,-,---_-_(-()-) ]*$/;
        var textAreaSpchar= /^[0-9a-zA-Z\s\r\n@!%#\$\^%&*()~+_=\-\[\]\\\';,\.\/\{\}\|\":\?]+$/;  	
	var tagSpchar=/^[a-zA-Z0-9]*$/;
 	messagString=FieldValiDationWithFocuse("topicName",nameSpchar,"Special Characters are not allowed.","Please Enter Topic Name.", messagString);
	if(trim(messagString)!="")
	return messagString ;
	
	messagString=FieldValiDationWithFocuse("topicDesc",textAreaSpchar,"Special Characters are not allowed.","Please Enter Topic Description.", messagString);
	if(trim(messagString)!="")
	return messagString ;
	
	messagString=checkLength("topicDesc","Topic Description should less than 250 characters.",250,messagString);
	if(trim(messagString)!="")
	return messagString ;
	
	if($("input:radio[name='disboard.visibleTo']").is(":checked")==false){
		messagString="Please select Type of Visibility";
		return messagString ;
	}
	
	var visbleTo=$("input[name='disboard.visibleTo']:checked").val();
	var selectGroup = $('.dd-selected-value').val();
	document.getElementById("groupId").value=selectGroup;
	if(visbleTo=="G"){
		
		if (selectGroup == "-1" || selectGroup=="") {
			document.getElementById("demo-htmlselect-basic").focus();
			messagString='Please select Group.';
			return messagString;
		}
	}
	
	messagString=FieldValiDationWithFocuse("tags",tagSpchar,"Special Characters are not allowed.","Please Enter Tags.", messagString);
	if(trim(messagString)!="")
	return messagString ;
	
	return messagString ;
}


function FieldValiDationWithFocuse(FieldId,RegXp,RegMsg,BlankMesg, messagString)
{
   var FieldValue=document.getElementById(FieldId).value; 
	   if(BlankMesg!="")
	   {
	       if(FieldValue==""|| FieldValue==null){
	    	   document.getElementById(FieldId).focus();
	           messagString=messagString+BlankMesg;
	           return messagString;
	         }
	   }
	   
	   if(FieldValue.search(RegXp)==-1)
		{
		  document.getElementById(FieldId).focus();
		  messagString=messagString+RegMsg;
		  return messagString;
		}
	return messagString;
}
function checkLength(fieldId,mesg,fieldlength,messagString){
	 
	   var FieldValue=document.getElementById(fieldId).value;
		   if(FieldValue.length>fieldlength){
		   document.getElementById(fieldId).focus();
		   messagString=messagString+mesg;
		   
		   }
	       return messagString;
	}
function trim(s) {
	var l = 0;
	var r = s.length - 1;
	while (l < s.length && s[l] == ' ') {
		l++;
	}
	while (r > l && s[r] == ' ') {
		r -= 1;
	}
	return s.substring(l, r + 1);
}

function deleteComment(cform,id){
	document.getElementById("comid").value=id;
	var r = confirm("Are You Sure, you want to delete!");
	if (r == true) {
		cform.submit();
	} else {
	    return false;
	}
	
    
}
function deleteReply(rform,id){
	document.getElementById("repid").value=id;
	var r = confirm("Are You Sure, you want to delete!");
	if (r == true) {	
		rform.submit();
	} else {
	    return false;
	}
	
    
}

var flg="";
$(function(){
$(".reply").click(function(e){
if(flg!="" && flg!=this.id.replace("reply","")){
$("#post_reply"+flg).slideUp();
}
flg=this.id.replace("reply","");
$("#post_reply"+this.id.replace("reply","")).slideToggle();
 event.preventDefault();
});
});	 
