var Base64 = 
{
		// private property
		_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
	 
		// public method for encoding
		encode : function (input) 
		{
			var output = "";
			var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
			var i = 0;
	 
			input = Base64._utf8_encode(input);
	 
			while (i < input.length) 
			{
				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);
	 
				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;
	 
				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}
				output = output +
				this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
				this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
			}
			return output;
		},
	 
		// public method for decoding
		decode : function (input) 
		{
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
	 
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	 
			while (i < input.length) 
			{
				enc1 = this._keyStr.indexOf(input.charAt(i++));
				enc2 = this._keyStr.indexOf(input.charAt(i++));
				enc3 = this._keyStr.indexOf(input.charAt(i++));
				enc4 = this._keyStr.indexOf(input.charAt(i++));
	 
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
	 
				output = output + String.fromCharCode(chr1);
	 
				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}
			}
			output = Base64._utf8_decode(output);
			return output;
		},
	 
		// private method for UTF-8 encoding
		_utf8_encode : function (string) 
		{
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";
	 
			for (var n = 0; n < string.length; n++) {
	 
				var c = string.charCodeAt(n);
	 
				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}
			}
			return utftext;
		},
	 
		// private method for UTF-8 decoding
		_utf8_decode : function (utftext) 
		{
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;
	 
			while ( i < utftext.length ) {
	 
				c = utftext.charCodeAt(i);
	 
				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				}
				else if((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i+1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				}
				else {
					c2 = utftext.charCodeAt(i+1);
					c3 = utftext.charCodeAt(i+2);
					string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}
			}
			return string;
		},
	
		reverseString : function (theString)
		{
			 var newString = ""; 
			 var counter = theString.length; 

			 for (counter  ;counter > 0 ;counter--) 
			 { 
				 newString += theString.substring(counter-1, counter); 
			 }
			 return newString;
		} ,

		stringManpEncode : function (strValue)
		{
			var strRepVal="";
			var capsAlpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			var repCapsAlpha="ZYXWVUTSRQPONMLKJIHGFEDCBA";
			var smalAlpha="abcdefghijklmnopqrstuvwxyz";	
			var repSmalAlpha="zyxwvutsrqponmlkjihgfedcba";
			var digVal="0123456789=";
			var repDigVal="0@87$!*3^1#"

			for(i=0; i<strValue.length; i++)
			{
				var chVal=strValue.charAt(i);
				
				if(capsAlpha.indexOf(chVal)!=-1)
				{
					var iPos=capsAlpha.indexOf(chVal);
					strRepVal=strRepVal+repSmalAlpha.charAt(iPos);
				}
				else if(smalAlpha.indexOf(chVal)!=-1)
				{
					var iPos=smalAlpha.indexOf(chVal);
					strRepVal=strRepVal+repCapsAlpha.charAt(iPos);
				}
				else if(digVal.indexOf(chVal)!=-1)
				{
					var iPos=digVal.indexOf(chVal);
					strRepVal=strRepVal+repDigVal.charAt(iPos);
				}
				else
				{
					strRepVal=strRepVal+chVal;
				}
			}
			return strRepVal;
		},

		stringManpDecode : function (strValue)
		{	  
			var strRepVal="";
			var capsAlpha="ZYXWVUTSRQPONMLKJIHGFEDCBA";		 
			var smalAlpha="zyxwvutsrqponmlkjihgfedcba";
			var repCapsAlpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			var repSmalAlpha="abcdefghijklmnopqrstuvwxyz";
			var digVal="0@87$!*3^1#";
			var repDigVal="0123456789="

			var strRepVal="";
			for(i=0; i<strValue.length; i++)
			{
				var chVal=strValue.charAt(i);
				
				if(capsAlpha.indexOf(chVal)!=-1)
				{
					var iPos=capsAlpha.indexOf(chVal);
					strRepVal=strRepVal+repSmalAlpha.charAt(iPos);
				}
				else if(smalAlpha.indexOf(chVal)!=-1)
				{
					var iPos=smalAlpha.indexOf(chVal);
					strRepVal=strRepVal+repCapsAlpha.charAt(iPos);
				}
				else if(digVal.indexOf(chVal)!=-1)
				{
					var iPos=digVal.indexOf(chVal);
					strRepVal=strRepVal+repDigVal.charAt(iPos);
				}
				else
				{
					strRepVal=strRepVal+chVal;
				}
			}
			return strRepVal;
		},

		finalManEncodeValue : function (strEncodeVal)
		{
			var x=strEncodeVal.length;
			var z=x%2;
			var s=(x-z)/2;
			var strPartOneVal=strEncodeVal.substring(0, s);
			var strPartTwoVal=strEncodeVal.substring(s, x);

			var revEncValOne=Base64.reverseString(strPartOneVal);	
			var revEncValTwo=Base64.reverseString(strPartTwoVal);

			var encOne=Base64.stringManpEncode(revEncValOne);
			var encTwo=Base64.stringManpEncode(revEncValTwo);
			return 	encOne+encTwo;
		},

		finalManDecodeValue : function (strEncodeVal)
		{
			var x=strEncodeVal.length;
			var z=x%2;
			var s=(x-z)/2;
			var strPartOneVal=strEncodeVal.substring(0, s);
			var strPartTwoVal=strEncodeVal.substring(s, x);
			
			var revEncValOne=Base64.reverseString(strPartOneVal);
			var revEncValTwo=Base64.reverseString(strPartTwoVal);

			var encOne=Base64.stringManpDecode(revEncValOne);
			var encTwo=Base64.stringManpDecode(revEncValTwo);

			return 	encOne+encTwo;
		} ,

		//Method which is called for encoding 
		tripleEncoding : function (orgValue)
		{
			var fstEncVal=Base64.encode(orgValue);
			var secEncVal=Base64.finalManEncodeValue(fstEncVal);
			var fnlEncVal=Base64.encode(secEncVal);
			return fnlEncVal;
		},

		//Method which is called for decoding
		tripleDecoding : function (encValue)
		{
			var fstDecVal=Base64.decode(encValue);
			var secDecVal=Base64.finalManDecodeValue(fstDecVal);
			var fnlDecVal=Base64.decode(secDecVal);
			return fnlDecVal;
		}
}
