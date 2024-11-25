package com.ongc.liferay.sponsorship.util;


public class TriplerDecBase64 
{
	private static String reverseString(String theString)
	{
		 String newString = ""; 
		 int counter = theString.length(); 

		 for (int i=counter; i > 0 ; i--) 
		 { 
			 newString += theString.substring(i-1, i); 
		 }
		 return newString;
	}
	private static String stringManpDecode(String strValue)
	{	  
		String strRepVal="";
		String capsAlpha="ZYXWVUTSRQPONMLKJIHGFEDCBA";		 
		String smalAlpha="zyxwvutsrqponmlkjihgfedcba";
		String repCapsAlpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String repSmalAlpha="abcdefghijklmnopqrstuvwxyz";
		String digVal="0@87$!*3^1#";
		String repDigVal="0123456789=";

		for(int i=0; i<strValue.length(); i++)
		{
			char chVl=strValue.charAt(i);
			char[] nChv={chVl};
			String chVal=new String(nChv);
			if(capsAlpha.indexOf(chVal)!=-1)
			{
				int iPos=capsAlpha.indexOf(chVal);
				strRepVal=strRepVal+repSmalAlpha.charAt(iPos);
			}
			else if(smalAlpha.indexOf(chVal)!=-1)
			{
				int iPos=smalAlpha.indexOf(chVal);
				strRepVal=strRepVal+repCapsAlpha.charAt(iPos);
			}
			else if(digVal.indexOf(chVal)!=-1)
			{
				int iPos=digVal.indexOf(chVal);
				strRepVal=strRepVal+repDigVal.charAt(iPos);
			}
			else
			{
				strRepVal=strRepVal+chVal;
			}
		}
		return strRepVal;
	}
	private static String finalManDecodeValue(String strEncodeVal)
	{
		int x=strEncodeVal.length();
		int z=x%2;
		int s=(x-z)/2;
		String strPartOneVal=strEncodeVal.substring(0, s);
		String strPartTwoVal=strEncodeVal.substring(s, x);
		
		String revEncValOne=reverseString(strPartOneVal);
		String revEncValTwo=reverseString(strPartTwoVal);

		String encOne=stringManpDecode(revEncValOne);
		String encTwo=stringManpDecode(revEncValTwo);

		return 	encOne+encTwo;
	} 
	public static String tripleDecoding(String encValue)
	{  //encValue= decryptValue(encValue);
		String fstDecVal=Base64Decoder.decode(encValue);
		String secDecVal=finalManDecodeValue(fstDecVal);
		String fnlDecVal=Base64Decoder.decode(secDecVal);
		return fnlDecVal;
	}
	
	
	/* Add new Code By Parvesh*/
	private static String rnval="XdfJgaDhilm8nMYoApIqZVsU9KtuvLcWxyCz0k1R2O3HbS4w5T6j7BEFeGNPrQ";
	private static int scode=0;
	public static String pass="admin";
	public static int parsePass(String pss){
		pass=pss;
		int num=-1;
		for(int i=0;i<pss.length();i++){
			
			char c=pss.charAt(i);
			int b=rnval.indexOf(c)*(i+3);
			if(num==-1)num=0;
			num+=b;
			
		}
		while(num>30){
			int x=num%10;
			num=(num/10)+x;
		}
		scode=num;
		////system.out.println("scode ==  "+scode);
		return num;
	}
	public static String decrypt(String val){
		String orgVal="";
		for(int i=0;i<val.length();i++){
			char c=val.charAt(i);
			if(c!='='){
			int ndx=rnval.indexOf(c);
		
			ndx+=scode;
			if(ndx>rnval.length()-1)
				ndx-=rnval.length();
			c=rnval.charAt(ndx);
			}
			orgVal=orgVal+c;
		}
		return orgVal;
	}
	public static String decryptValue(String val){
		val=decrypt(val);
		
		for(int x=pass.length()-1;x>=0;x--){
			String orgVal="";
			char bh=pass.charAt(x);
			int sh=rnval.indexOf(bh);
		for(int i=0;i<val.length();i++){
			char c=val.charAt(i);
			if(c!='='){
			int ndx=rnval.indexOf(c);
		
			ndx+=sh;
			if(ndx>rnval.length()-1)
				ndx-=rnval.length();
			c=rnval.charAt(ndx);
			}
			orgVal=orgVal+c;
			
		}
		val=orgVal;
		}
		return val;
	}

}
