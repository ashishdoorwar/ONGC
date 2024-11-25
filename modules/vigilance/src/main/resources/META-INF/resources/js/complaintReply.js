function replyformvalidation(thisform)
{	
	
	with (thisform)	
	{
	//	alert(112);
		if (emptyvalidation(replytxt,"Reply Text can't be empty")==false) { return false;}
		if (emptyvalidation(captchaVal,"Captcha can't be empty")==false) { return false;}
		
		captchaVal.value=Base64.tripleEncoding(captchaVal.value);
		replytxt.value=Base64.tripleEncoding(replytxt.value);
	
	}
	return true;
}

