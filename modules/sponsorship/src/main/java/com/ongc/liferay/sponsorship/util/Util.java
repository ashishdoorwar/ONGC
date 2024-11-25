package com.ongc.liferay.sponsorship.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date convertStringToDate(String dt) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
		return sf.parse(dt);
	}
	
	public static String convertDateToString(Date dt) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
		return sf.format(dt);
	}

	public static void main(String args[]) {
		Date dt = new Date();
		//String sdt = "03-Aug-2020";
		String sdt = "11-Aug-2020";
		try {
			//System.out.println(dt);
			Date currentDt=convertStringToDate(convertDateToString(dt));
			Date inputDt=convertStringToDate(sdt);
//			System.out.println("currentDt:"+currentDt);
//			System.out.println("inputDt:"+inputDt);
			if (inputDt.after(currentDt)) {
//				System.out.println("Recived date should be lessthan or equal current date!");
			}
			

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
