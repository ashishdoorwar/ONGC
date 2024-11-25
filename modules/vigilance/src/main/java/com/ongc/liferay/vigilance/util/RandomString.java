package com.ongc.liferay.vigilance.util;

import java.util.Random;

public class RandomString {
	private static final String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static final String strNum = "0123456789";
	private static final String uid = "xyz";
	
    public static String getRandomString(int length, String uid) {
        boolean flag = false;
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            String charCheck =""+charset.charAt(pos);
            if(i == 0 || i==(length-1)) {
                try {
                    Integer.parseInt(charCheck);
                    i--;
                } catch (Exception e) {
                    sb.append(charCheck);
                }
            }
            else {
                sb.append(charCheck);
            }
        }
        for(int i=0;i<charset.length();i++) {
            if(sb.toString().indexOf(charset.charAt(i))!=-1) {
                flag = true;
            } 
        }
        if(flag && sb.toString().indexOf(uid)==-1)
            return sb.toString();
        else
            return "";
    }
	
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	
    	String pass = getRandomString(8,"sanjay");
    	//system.out.println("Password is="+ pass);

	}

}
