package com.ongc.liferay.vigilance.util;

public class PasswordValidator {
	
	private static PasswordValidator instance = null;
	
	public static PasswordValidator getInstance(){
		if(instance==null){
			instance = new PasswordValidator();
		}
		return instance;
	}

	public String getMessage(int i) {
		String message = null;
		 switch (i) {
         case 1:  message = "Password should be minimum 8 character long";
                  break;
         case 2:  message = "Password should not contain more then 14 character";
                  break;
         case 3:  message = "Password should contain at least one character in upper case";
                  break;
         case 4:  message = "Password should contain at least one character in lower case";
                  break;
         case 5:  message = "Password should have alphabet in first position";
                  break;
         case 6:  message = "Password should contain at least one special character !,@,$,#";
                  break;
         case 7:  message = "Password should contain at least one number";
                  break;
         case 8:  message = "Password should not contain more than 2 consecutive identical character from any position";
                  break;
         case 9:  message = "Password should not contain Username";
                  break;
         case 10:  message = "Enter Password";
         		  break;
         default: message = "Password match";
                  break;
		 }
		return message;
	}

	public int validatePassword(String password ,String userid){
		if (password.trim().equals("") || password.length() ==0 ) {
			return 10;
		}else if (password.length() < 8) {
			return 1;
		} else if (password.length() > 14) {
			return 2;
		} else if (!checkUpper(password)) {
			return 3;
		} else if (!checlLower(password)) {
			return 4;
		} else if (password.charAt(0) >= '0' &&  password.charAt(0) <= '9' ? true : false) {
			return 5;
		} else if (!(password.indexOf("!") > -1 || password.indexOf("@") > -1 || password.indexOf("#") > -1 || password.indexOf("$") > -1)) {
			return 6;
		} else if (!password.matches(".*\\d.*")) {
			return 7;
		} else if (checkIdeticalChar(password)) {
			return 8;
		} else if (userid.equalsIgnoreCase(password)) {
			return 9;
		}
		return 0;
	}
	
	public boolean checkUpper(String password){
		boolean flag = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i); 
			 if (c >= 'A' && c <= 'Z'){  
				 flag = true;
				 break;
			  } else {
				  flag = false;
			  }
		}
		return flag;
	}
	
	public boolean checlLower(String password){
		boolean flag = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i); 
			 if (c >= 'A' && c <= 'Z'){ 
				 flag = false;
			  } else {
				  flag = true;
				  break; 
			  }
		}
		return flag;
	}
	
	 public boolean checkIdeticalChar(String password){
		 boolean flag = false;
		 for(int i=0,j=2;j<password.length();i++,j++){
				String threeChar=password.substring(i, j);
				String substring = password.substring(j);
				if(substring.contains(threeChar)){
					flag = true;
					break;
				}
		}
		return flag;
	 }
	 
}
