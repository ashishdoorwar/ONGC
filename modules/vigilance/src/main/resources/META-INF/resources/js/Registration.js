
function formvalidation(thisform)
{		
	clearMessage('errorDiv');
	with (thisform)	
	{
		if (emptyvalidation(emailId,"Enter Username")==false) { return false;}
		if (emailvalidation(emailId,"Invalid Username")==false) { return false;}
		
		if (emptyvalidation(password,"Enter Password")==false) { return false;}
		if (checkPassLength(password)==false) { return false;}
		if (emptyvalidation(confirmPass,"Enter Confirm Password")==false) { return false;}
		if (checkPassLength(confirmPass)==false) { return false;}
		if (checkpass(password,confirmPass,"Password does not match")==false) { return false;}
			
		if (emptySelectBox(securityQuestion,"Select Security Question")==false) { return false;}
		if (emptyvalidation(securityAnswer,"Enter Security Answer")==false) { return false;}
		if (checkSpecChar(securityAnswer,"none","Special Characters are not allowed")==false) { return false;}
		
		if (emptySelectBox(userTitle,"Select Title")==false) { return false;}
		
		if (emptyvalidation(firstName,"Enter First Name")==false) { return false;}
		if (checkSpecChar(firstName,"none","Special Characters are not allowed")==false) { return false;}
		if (allnumeric(firstName,"First Name can contain alphanumeric values")==false) { return false;}
		
		if (checkSpecChar(middleName,"none","Special Characters are not allowed")==false) { return false;}
		if (allnumeric(middleName,"Middle Name can contain alphanumeric values")==false) { return false;}
		
		if (emptyvalidation(lastName,"Enter Last Name")==false) { return false;}
		if (checkDOB(dbirth,"DOB")==false) { return false;}
		if (checkSpecChar(lastName,"none","Special Characters are not allowed")==false) { return false;}
		if (allnumeric(lastName,"Last Name can contain alphanumeric values")==false) { return false;}
		
		if (phoneValidation(phoneNumber,"Phone Number")==false) { return false;}
		if (emptyvalidation(mobile,"Enter Mobile Number")==false) { return false;}
		if (mobileValidation(mobile,"Mobile")==false) { return false;}
		if (emptyvalidation(address1,"Enter Address (Line 1)")==false) { return false;}
		//if (allnumeric(address1,"Address (Line 1) can contain alphanumeric values")==false) { return false;}
		if (checkSpecCharAddress(address1,"none","Special Characters are not allowed in Address (Line 1)")==false) { return false;}
		if (checkSpecCharAddress(address2,"none","Special Characters are not allowed in Address (Line 2)")==false) { return false;}
		//if (allnumeric(address2,"Address (Line 2) can contain alphanumeric values")==false) { return false;}
		if (emptySelectBox(countryID,"Select Country")==false) { return false;}
		if (checkSpecChar(state,"none","Special Characters are not allowed")==false) { return false;}
		if (onlynum(pincode,"Invalid Pincode")==false) { return false;}
		if (testcheckbox(TermsCondition,"Select Terms and Conditions")==false) { return false;}
		
		if (middleName.value == "Middle Name") {
			middleName.value = "";
		}
		
		emailId.value = Base64.tripleEncoding(emailId.value);
		password.value = Base64.tripleEncoding(password.value);
		securityAnswer.value = Base64.tripleEncoding(securityAnswer.value);
		firstName.value = Base64.tripleEncoding(firstName.value);
		middleName.value = Base64.tripleEncoding(middleName.value);
		lastName.value = Base64.tripleEncoding(lastName.value);
		phoneNumber.value = Base64.tripleEncoding(phoneNumber.value);
		mobile.value = Base64.tripleEncoding(mobile.value);
		address1.value = Base64.tripleEncoding(address1.value);
		address2.value = Base64.tripleEncoding(address2.value);
		state.value = Base64.tripleEncoding(state.value);
		pincode.value = Base64.tripleEncoding(pincode.value);
		
	}
	return true;
}


function loginFormValidation(thisform){
	with(thisform){
		if (emptyvalidation(userid,"Enter Username")==false) { return false;}
		if (emailvalidation(userid,"Invalid Username")==false) { return false;}
		
		if (emptyvalidation(password,"Enter Password")==false) { return false;}
		if (checkPassLength(password)==false) { return false;}
		if (emptyvalidation(captcha,"Enter Captcha")==false) { return false;}
		userid.value= Base64.tripleEncoding(userid.value);
		encodepassFeild(pass,password,uval.value);
	}
	return true;
}


function forgotPassValidation(thisform){
	clearMessage('errorDiv');
	with(thisform){
		if (emptyvalidation(userid,"Enter Username")==false) { return false;}
		if (emailvalidation(userid,"Invalid Username")==false) { return false;}
		if (allnumeric(userid,"First Name can contain alphanumeric values")==false) { return false;}
		
		/*if (emptyvalidation(password,"Enter Password")==false) { return false;}
		if (checkPassLength(password)==false) { return false;}
		if (emptyvalidation(confirmPass,"Enter Confirm Password")==false) { return false;}
		if (checkPassLength(confirmPass)==false) { return false;}
		if (checkpass(password,confirmPass,"Password does not matched")==false) { return false;}
	*/
		if (emptySelectBox(securityQuestion,"Select Security Question")==false) { return false;}
		if (emptyvalidation(securityAnswer,"Enter Security Answer")==false) { return false;}
		if (checkSpecChar(securityAnswer,"none","Special Characters are not allowed")==false) { return false;}	
	}
}

function complaintFormValidation(thisform){

	with(thisform){
		
		//if (emptySelectBox(title,"Select Title")==false) { return false;}
		
		//if (emptyvalidation(firstName,"Enter First Name")==false) { return false;}
		//if (checkSpecChar(firstName,"none","Special Characters are not allowed in First Name")==false) { return false;}
		//if (allnumeric(firstName,"First Name can contain alphanumeric values")==false) { return false;}
	
		if (emptyvalidation(complaintSubject,"Enter Subject of Complaint")==false) { return false;}
		//if (checkSpecChar(complaintSubject,"none","Special Characters are not allowed")==false) { return false;}
		if (allnumeric(complaintSubject,"Subject of Complaint can contain alphanumeric values")==false) { return false;}
		
		if (emptyvalidation(complaintDetail,"Enter Allegations/Complaint Details")==false) { return false;}
		if (checkSpecCharComplaint(complaintDetail,"none","Special Characters are not allowed in Allegations/Complaint Details")==false) { return false;}
		//if (allnumeric(complaintDetail,"Allegations/Complaint Details can contain alphanumeric values")==false) { return false;}
		if (testcheckbox(TermsCondition,"Select Terms and Conditions")==false) { return false;}

var fnm=dataFile.value;
console.log("fnm   "+fnm);
if(fnm)
	{
var fExt=fnm.substring(fnm.lastIndexOf("."),fnm.length);
				   if(fExt.toLowerCase()!=".jpg" && fExt.toLowerCase()!=".jpeg" && fExt.toLowerCase()!=".pdf")
					   {
					   
					   document.getElementById("errorMsg").innerHTML="Please upload file of valid format (i.e. .pdf ,.jpeg or .jpg)";
					   dataFile.focus();
					   return false;
					   }

	}
		
		//if (checkSpecChar(middleName,"none","Special Characters are not allowed in Middle Name")==false) { return false;}
		//if (allnumeric(middleName,"Last Name can contain alphanumeric values")==false) { return false;}
		
		//if (emptyvalidation(lastName,"Enter Last Name")==false) { return false;}
		//if (checkSpecChar(lastName,"none","Special Characters are not allowed in Last Name")==false) { return false;}
		//if (allnumeric(lastName,"Last Name can contain alphanumeric values")==false) { return false;}
		
		//if (checkSpecChar(departmentTxt,"none","Special Characters are not allowed")==false) { return false;}
		//if (allnumeric(departmentTxt,"Department/Section can contain alphanumeric values")==false) { return false;}
		
		//if (checkSpecChar(locationTxt,"none","Special Characters are not allowed")==false) { return false;}
		//if (allnumeric(locationTxt,"Location can contain alphanumeric values")==false) { return false;}
		
		
		//	if (emptySelectBox(country,"Selecgt Country")==false) { return false;}
		
		//if (checkSpecChar(state,"none","Special Characters are not allowed")==false) { return false;}
		//if (allnumeric(state,"State can contain alphanumeric values")==false) { return false;}
		
		//if (testcheckbox(TermsCondition,"Select Terms and Condition")==false) { return false;}
//		if (middleName.value == "Middle Name") {
//			middleName.value = "";
//		}
/*
for(var i=0;i<dataFile.length;i++)
		{
		alert('a');
			if(dataFile[i].files[0]!= undefined){
			var fnm=dataFile[i].files[0].name;
			   if(fnm!="" && document.getElementById("file"+(i+1)).style.display=="")
				   {
				   var fExt=fnm.substring(fnm.lastIndexOf("."),fnm.length);
				   if(fExt.toLowerCase()!=".jpg" && fExt.toLowerCase()!=".jpeg" && fExt.toLowerCase()!=".pdf")
					   {
					   alert('b');
					   document.getElementById("errorMsg").innerHTML="Please upload file of valid format (i.e. .pdf ,.jpeg or .jpg)";
					   dataFile[i].focus();
					   return false;
					   }
				   }
			}
		}
		
		for(var i=0;i<dataFile.length;i++)
		{
			if(dataFile[i].files[0]!= undefined){
			
			if(document.getElementById("file"+(i+1)).style.display=="none" && dataFile[i].files[0].name!="")
			{
		       document.getElementById("file"+(i+1)).innerHTML="";
			  
		    }
			}
		}
*/





	}
	
}

function changePassValidation(){
	var thisform=document.changepass_form;
	clearMessage('errorDiv');
	with(thisform){
		if (emptyvalidation(oldPassword,"Enter old Password")==false) { return false;}
		if (checkPassLength(oldPassword)==false) { return false;}
		if (emptyvalidation(newPassword,"Enter new Password")==false) { return false;}
		if (checkPassLength(newPassword)==false) { return false;}
		if (emptyvalidation(confirmPasswrod,"Enter Confirm Password")==false) { return false;}
		if (checkoldpass(newPassword,oldPassword,"New Password should not same as Old Password")==false) { return false;}
		if (checkpass(newPassword,confirmPasswrod,"Password does not match")==false) { return false;}
			}
	thisform.submit();
	return true;
}

function clearMessage(divId){
	if (document.getElementById(divId) != null && document.getElementById(divId) != undefined ) {
		document.getElementById('errorDiv').innerHTML="";
	}
	
}



setTimeout(function(){
	$('.BDC_CaptchaImageDiv, .BDC_CaptchaDiv, #captchaCode, .BDC_CaptchaIconsDiv').attr('style','');
},500);