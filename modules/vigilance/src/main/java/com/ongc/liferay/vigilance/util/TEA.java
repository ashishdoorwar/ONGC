package com.ongc.liferay.vigilance.util;

import com.liferay.portal.kernel.util.Base64;

public class TEA {

	private TEA() {
	} // TEA()
 
	static final public String encrypt(String p_data, String p_key) {
		// convert data & key into byte array
		byte bufData[] = p_data.getBytes();	
		byte bufKey[] = p_key.getBytes();
		// find their lengths
		int kl = bufKey.length;
		int dl = bufData.length;
		// encrypt using XOR
		byte buf[] = new byte[dl];
		for (int i=0;i<dl;i++) {
			buf[i] = (byte) (bufData[i] ^ bufKey[i%kl]);
		} // for (int i=0;i<dl;i++)
		// packup... mark buf data & key for garbage collection
		bufData = null; bufKey = null;
		// perform BASE64 encoding to get the string
		
		return Base64.encode(buf);
	} // encrypt()
	static final public String decrypt(String p_data, String p_key) {
		// convert data & key into byte array
		
		byte bufKey[] = p_key.getBytes();
		byte bufData[] = 	Base64.decode(p_data);	
		int kl = bufKey.length;
		int dl = bufData.length;
		// encrypt using XOR
		byte buf[] = new byte[dl];
		for (int i=0;i<dl;i++) {
			buf[i] = (byte) (bufData[i] ^ bufKey[i%kl]);
		}
		p_data=new String(buf);
		return p_data;
		
	} // encrypt()
	
	public static void main(String[] args) {
 //superadmin   test@1234
		//mm_anand    USyzL16!0
 System.out.println(decrypt("OD4mGyJQWEVd","mm_anand"));
	}
}
