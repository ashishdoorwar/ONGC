var err="errorMsg";

function checkDate(fld,alertbox){
var s=fld.value.split('-');
var x=new Date();
var today = new Date();
x.setFullYear(s[2],s[1]-1,s[0]);
if(x>today){
	return true;
}
else{
	if(err!=''){
	document.getElementById(err).innerHTML=alertox;
	pageScroll();
	}
	else{ alert(alertbox);}
	 fld.focus();
	 return false;
	
}
}

function phoneValidation(entered) {
	with (entered) {
		var x = trim(value);
		if (x != null && x.length >0) {
		//document.getElementById(err).innerHTML="Invalid Phone Number";
//			alert("Phone no. cannot be left blank");
		//	entered.focus();
		//	return false;
		

		if (isNaN(x)) {
			document.getElementById(err).innerHTML="Invalid Phone Number";
			pageScroll();
//			alert("Enter numeric value");
			entered.focus();
			return false;
		}
		if (x.length < 10 || x.length > 14) {
	        document.getElementById(err).innerHTML="Maximum length of phone number is 14 and minimum length is 10";
	        entered.focus();
	        pageScroll();
			return false;
		}
		}
	}
	return true;
}

function checkPassLength(entered) {
		var x = entered.value;
		if(x.length < 8 ){
			 document.getElementById(err).innerHTML="Password should be minimum 8 character long";
			 entered.focus();
			 return false;
		} else if(x.length > 14){
			 document.getElementById(err).innerHTML="Password should not contain more then 14 character";
			 entered.focus();
			 return false;
		} else {
			return true;
		}
	
}
function checkPassSpecialChar(entered)
{
	with (entered) {
		var x = entered.value;
		if(x.indexOf("!")<0 || x.indexOf("@")<0 || x.indexOf("$")<0 || x.indexOf("#")<0 ){
			 document.getElementById(err).innerHTML="Password should contain at least one number and one special character (!,@,$,#).";
			 entered.focus();
			 pageScroll();
			 return false;
		}  else {
			return true;
		}
	}
}


function checkDOB(entered, alertbox) {
	with (entered) {
	var s = entered.value.split('-');
	var x = new Date();
	var today = new Date();
	x.setFullYear(s[2], s[1] - 1, s[0]);
	if (x > today) {
		if (alertbox!="") {
			if(err!=''){
				document.getElementById(err).innerHTML='DOB should not be greater than current date';
				pageScroll();
				}
				else{ alert(alertbox);}
			focus();
		} return false;
	} else {return true;}
   }
}




function testcheckbox(entered, alertbox) {
	with (entered) {
		if (checked == false) {
			if (alertbox != "") {
				document.getElementById(err).innerHTML = alertbox;
				pageScroll();
				focus();
			}
			return false;
		} else {
			return true;
		}
	}
}


function timevalidate(from,to,alertbox){
	var x=parseInt(from.value);
	var y=parseInt(to.value);
	if(x>=y){
		if(err!=''){
			document.getElementById(err).innerHTML=alertox;
			pageScroll();
			}
			else{ alert(alertbox);}
		to.focus();
		return false;
	}else{
		return true;
	}
}


function onlychar(entered, alertbox)
{
var char1=/^[a-zA-Z ]+$/;
if(!entered.value.match(char1))
{
	if(err!=''){
		entered.focus();
		document.getElementById(err).innerHTML=alertbox;
		pageScroll();
		}
		else{ alert(alertbox);}
   return false;
   }
else {return true;}
}

function onlynum(entered, alertbox)
{
var char1=/^[0-9]+$/;
if(entered.value.length > 0 && !entered.value.match(char1))
{
	if(err!=''){
		document.getElementById(err).innerHTML=alertbox;
		pageScroll();
		entered.focus();
		}
		else{ alert(alertbox);}

   return false;
   }
else {return true;}
}


function allnumeric(entered, alertbox){  
  var numbers = /^[0-9]+$/;  
  var inputtxt = entered.valueOf();
  if((inputtxt.value.match(numbers))){  
	  document.getElementById(err).innerHTML=alertbox;
	  pageScroll();
	  entered.focus();
      return false;  
  } else {  
    return true;  
}  
}

function onlynumwithsign(entered, alertbox)
{

return true;
}

function mobileValidation(entered,alertbox){
	var val=entered.value;
	var char1=/^[0-9]+$/;
	var x=val.substring(0,1);
	if(x=='+'){
		var num=val.substring(1,val.length);
		
		if(!num.match(char1))
		{
			if(err!=''){
				document.getElementById(err).innerHTML='Invalid Mobile Number';
				pageScroll();
				}
				else{ alert('Invalid Mobile Number');}
			entered.focus();
			return false;
		}
		if(num.length>13||num.length<11){
		
			if(err!=''){
				document.getElementById(err).innerHTML="Mobile No. should be 12 to 14 digit with '+' sign in front";
				}
				else{alert("Mobile No. should be 12 to 14 digit with '+' sign in front");
				}
			entered.focus();
			pageScroll();
			return false;
		}
	}else{
		if(!val.match(char1))
		{
			if(err!=''){
				document.getElementById(err).innerHTML='Invalid Mobile Number';
				pageScroll();
				}
				else{ alert('Invalid Mobile Number');}
			entered.focus();
			return false;
		}
		document.getElementById(err).innerHTML='Mobile No. should be 12 to 14 digit with '+' sign in front';
		entered.focus();
		pageScroll();
		return false;
//		if(val.length!=10){
//			
//			if(err!=''){
//				document.getElementById(err).innerHTML="Mobile No. should be 10 digit without '+' sign";
//				}
//				else{ alert("Mobile No. should be 10 digit without '+' sign");}
//			entered.focus();
//			return false;
//		}
	}
}

function emptyvalidation(entered, alertbox)
{
with (entered)
{
	value=trim(value);
	if (value==null || value=="")
	{
		if (alertbox!="") {
			if(err!=''){
//				clearMsg();
//				var next = document.getElementById(id).nextSibling;
//				next.id = "xyz";
//				next.innerHTML = alertbox;
				document.getElementById(err).innerHTML=alertbox;
				pageScroll();
				}
				else{ alert(alertbox);}
			focus();
		} return false;
	} else {return true;}
   }
}

function namevalidation(entered, alertbox)
{
with (entered)
{
	value=trim(value);
	alert("   -- >  " + value);
	if (value == null || value=="" || value== 'First Name' || value== 'Last Name')
	{
		if (alertbox!="") {
			if(err!=''){
				document.getElementById(err).innerHTML=alertbox;
				pageScroll();
				}
				else{ alert(alertbox);}
			focus();
		} return false;
	} else {return true;}
   }
}

function emptySelectBox(entered, alertbox) {
	with (entered) {
		value = trim(value);
		 
		if (value == null || value == -1) {
			if (alertbox != "") {
				if (err != '') {
//					clearMsg();
//					var next = document.getElementById(id).nextSibling;
//					next.id = "xyz";
//					next.innerHTML = alertbox;
					document.getElementById(err).innerHTML=alertbox;
					pageScroll();
				} else {
					alert(alertbox);
				}
				focus();
			}
			return false;
		} else {
			return true;
		}
	}
}

function clearMsg(){
	var id = "xyz";
	var xyz = document.getElementById(id);
	if (xyz != undefined ) {
		xyz.id="abc";
		xyz.innerHTML="";
	}
}

function emailvalidation(entered, alertbox)
{

with (entered)
{
apos=value.indexOf("@"); 
dotpos=value.lastIndexOf(".");
lastpos=value.length-1;
if (apos<1 || dotpos-apos<2 || lastpos-dotpos>3 || lastpos-dotpos<2) 
{if (alertbox) {if(err!=''){
document.getElementById(err).innerHTML=alertbox;
pageScroll();
}
else{ alert(alertbox);} focus();} return false;}
else {return true;}
}
}

function count(entered,num, alertbox)
{

with (entered)
{

var len=entered.value;
if (len.length < num) {if(err!=''){
	document.getElementById(err).innerHTML=alertox;
	pageScroll();
}
else{ alert(alertbox);} return false;}
else {return true;}
}
}



function alphanum(entered)
{
re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
      if(!re.test(entered.value)) {
        alert("Error: password must contain at least one number (0-9),one lower case letter (a-z) and one upper case letter(A-Z)");
        entered.focus();
        pageScroll();
        return false;
} 
}

var passesckey="opBcdefgh1iwxy0UV2E67MNOPqQR3STAXHI5JbY4ZaKjklmWzr8stCD9nFGuvL";
var passesckey2="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
function flayer(str,num){
	var nval='';
	var x=0;
	num=num%40;
	while(num>x){
	for(var i=0;i<str.length;i++){
	
		var ch=str.charAt(i);
		if(ch!="="){
			ch=passesckey2.charAt(passesckey.indexOf(ch));
		}
		nval=nval+ch;
	}
	x++;
	str=nval; nval='';
	}
	return str;
}

function encodepassFeild(enfld,fld,num){
	//alert(fld.name+"       ---   "+fld.value);
	//removeSpchar(fld);
	var val=fld.value;
	if(val!=''){
	enfld.value=flayer(Base64.tripleEncoding(val),parseInt(num));
	fld.value='';
	}else{
		enfld.value='';
	}
}

function checkoldpass(entered,entered1, alertbox)
{
if (entered1.value == entered.value)
{
	if(err!=''){
		entered1.focus();
		document.getElementById(err).innerHTML=alertbox;
		}
		else{ alert(alertbox);
		}
	pageScroll();
  return false;
  }
else 
{
	return true;}

}


function checkpass(entered,entered1, alertbox)
{
if (entered1.value != entered.value)
{
	if(err!=''){
		entered1.focus();
		document.getElementById(err).innerHTML=alertbox;
		}
		else{ alert(alertbox);
		}
	pageScroll();
  return false;
  }
else 
{
	return true;}

}
function trim(s)
{
	var l=0; var r=s.length -1;
	while(l < s.length && s[l] == ' ')
	{	l++; }
	while(r > l && s[r] == ' ')
	{	r-=1;	}
	return s.substring(l, r+1);
}
var spCharAddress=/^[a-zA-Z0-9.():,-/\r\n ]*$/;
//var commentspchar=/^[a-zA-Z0-9.(),""@${}&%-/\r\n ]*$/;
var spCharComplant=/^[a-zA-Z0-9.():@#";{}!$*+_=?,-/\r\n ]*$/;
function searchSpecialChars(val,exp)
{
	var SpecialChars="";
	for(var i=0;i<val.length;i++)
	{
		if(val[i].search(exp))
		SpecialChars=SpecialChars+" "+val[i];
	}
	return SpecialChars;
}
function checkSpecCharComplaint(entered,exp,msg){
	var falge=true;
	var val=entered.value;
	if (val.search(spCharComplant)==-1)
	{   
		if(err!=''){
			document.getElementById(err).innerHTML=msg+"<br>("+searchSpecialChars(val,spCharComplant)+")";
			}
			else{
				alert(msg+"<br>("+searchSpecialChars(val,spCharComplant)+")");
			}
		
		entered.focus();
		falge= false;
	}
	return falge;
	
}
function checkSpecCharAddress(entered,exp,msg){
	var falge=true;
	var val=entered.value;
	if (val.search(spCharAddress)==-1)
	{   
		if(err!=''){
			document.getElementById(err).innerHTML=msg+"<br>("+searchSpecialChars(val,spCharComplant)+")";
			}
			else{
				alert(msg+"<br>("+searchSpecialChars(val,spCharAddress)+")");
			}
		
		entered.focus();
		falge= false;
	}
	return falge;
	
}
var spChar="~`^#|@#$%-'?/,:\\;\"!";
function checkSpecChar(entered,exp,msg){
	var falge=true;
	var val=entered.value;
	if(exp=='none'){
		exp=spChar;
	} 
	
	var spch='';
	for(var i=0;i<exp.length;i++){
	var ch=exp.charAt(i);
	for(var j=0;j<val.length;j++){
		var ch2=val.charAt(j);
		//alert(ch+'   --    '+ch2)
		if(ch==ch2){
			if(spch=='')
				spch=ch;
			else
				spch=spch+' , '+ch
			falge=false;
		}
	}
	}
	if(!falge){
		if(err!=''){
			document.getElementById(err).innerHTML=msg;
			}
			else{ alert(msg+' not allowed special character : '+spch);}
		
		entered.focus();
		
	}
	if(!falge){
		pageScroll();
	}
	return falge;
	
}

function radioValidation(entered, alertbox){
	 	 var radios = entered; 
		 var flg=false;
	 for (var i=0; i <radios.length; i++) { 
	  if (radios[i].checked) { 
flg=true; 
	  }  } 
	  if(!flg){
		  if(err!=''){
				document.getElementById(err).innerHTML=alertox;
				}
				else{ alert(alertbox);}
		  entered.focus();
	  }
	  if(!flg){
		  pageScroll();  
	  }
	  return flg;
	
}




function pageScroll() {
	window.scrollBy(0,-window.pageYOffset); // horizontal and vertical scroll increments
	//scrolldelay = setTimeout('pageScroll()',100); // scrolls every 100 milliseconds
}
function checkCharLength(obj,lengths)
{
	if(obj.value.length>lengths)
	{
	  alert("Maximum number of character is "+lengths);
       obj.value=(obj.value).substring(0,lengths);
       obj.focus();
	  return false;
	}
}
function checkChar(obj)
{
	if(obj.value.length>1000)
	{
	  alert("Maximum number of character is 1000");
       obj.value=(obj.value).substring(0,1000);
       obj.focus();
	  return false;
	}
}