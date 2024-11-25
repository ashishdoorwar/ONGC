package com.ongc.liferay.vigilance.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockPrice extends Thread {

	private static String price="236";
	private static String date="STOP";
	private static StockPrice object;
	private static int k=0;
	private static String status="STOP";
	
	public static String getPrice(){
		return price;
	}
public static String getDate(){
		return date;
	}
	public static String getStatus(){
		return status;
	}
	static{
	//	object=new StockPrice();
	//	object.setDaemon(true);
	//	object.start();
	}
	
	
	@Override
	public void run() {
		while(true){
			try{
				sleep(60*1000);
				URL bse = new URL("http://www.bseindia.com/BSEGraph/Graphs/GetStockReachVolPriceData.aspx?index=500312&Flage=0");
		        URLConnection yc = bse.openConnection();
		        BufferedReader in = new BufferedReader(
		                                new InputStreamReader(
		                                yc.getInputStream()));
		      
		      StringBuffer sb=new StringBuffer(); 
		        String inputLine;
		 String st="false";
		        while ((inputLine = in.readLine()) != null) {
		        st="true";
		           sb.append(inputLine);
		           }
		        in.close();		
			if(sb.toString().length()>20){
				int x=sb.indexOf("#@#");
				String s1=sb.substring(0,x);
				String s[]=s1.split(",");
				date=  parseDate(s[0], s[6]);
				price=s[5];
				status=getStatus(s[1], s[5]);
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
private String parseDate(String s1, String s2){
	StringBuffer sb=new StringBuffer();
	String d[]=s1.split("/");
	sb.append(d[2]+" ").append(getMonth(d[1])).append(" "+d[0]);
	sb.append(" ").append(getTime(s2));
	return sb.toString();
}	

private String getTime(String tm){
	String t[]=tm.split(":");
	String ab="am";
	int x=Integer.parseInt(t[0]);
	if(x>=12) {ab="pm";
	if(x>12) x=x-12;
	}
	
	StringBuffer sb=new StringBuffer();
	sb.append(x).append(":").append(t[1]).append(ab);
	
	
	return sb.toString();
}

private String getStatus(String pre, String curr){
	float p=Float.parseFloat(pre);
	float c=Float.parseFloat(curr);
	
	if(c>=p)
	return "UP";
	else
		return "DOWN";
}


private String getMonth(String num){
	if(num.equals("1"))
		return "Jan";
	else if(num.equals("1"))
		return "Jan";
	else if(num.equals("2"))
		return "Feb";
	else if(num.equals("3"))
		return "Mar";
	else if(num.equals("4"))
		return "Apr";
	else if(num.equals("5"))
		return "May";
	else if(num.equals("6"))
		return "Jun";
	else if(num.equals("7"))
		return "Jul";
	else if(num.equals("8"))
		return "Aug";
	else if(num.equals("9"))
		return "Sep";
	else if(num.equals("10"))
		return "Oct";
	else if(num.equals("11"))
		return "Nov";
	else if(num.equals("12"))
		return "Dec";
	
	else
		return "";
}
	
}
