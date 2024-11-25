package com.ongc.liferay.vigilance.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Properties;

public class VigilanceHitCountUtils {
private static int hitCount;
private static Properties count=new Properties();
public static int getHitCount() {
	return hitCount;
}

public static int getNextHitCount() {
	return ++hitCount;
}

public static void initializeCount(String path){
	try{
	InputStream input=new FileInputStream(path);
	
	count.load(input);
	String hc=count.getProperty("hitcount");
	if(hc==null)
		hc="1";
	hitCount = Integer.parseInt(hc);
	//system.out.println("Count successfully initialized......"+hitCount);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
}

public static void saveCount(String path){
	try{
		OutputStream out=new FileOutputStream(path);
		count=new Properties();
		count.setProperty("hitcount", ""+hitCount);
		count.store(out,"");
		//system.out.println("Count successfully istored in properties file ......"+hitCount);
	}
	catch(Exception ex){
		ex.printStackTrace();
		
	}
}

}
