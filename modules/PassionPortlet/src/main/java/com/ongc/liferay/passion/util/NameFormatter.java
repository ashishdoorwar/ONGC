package com.ongc.liferay.passion.util;

public class NameFormatter {
	public String InitialCaps(String name){
		String s1=name;
		//log.info("Name:::"+s1);
		//system.out.println("Name::"+s1);
		if(s1==null)
			return s1;
		String[] s2=s1.split(" ");
		String s3="";
		for(int i=0;i<s2.length;i++){
			String m=s2[i];
			//system.out.println("m"+m);
			m=m.substring(0,1).toUpperCase().concat(m.substring(1, m.length()).toLowerCase());
			//system.out.println("m1"+m);
			s3=s3.concat(" ").concat(m);
			//system.out.println("m2"+s3);
		}
		
		s3=s3.trim();
		//system.out.println("s3"+s3);
		return s3;
	}
	
	
	
	public static void main(String[] args) {
		NameFormatter nf=new NameFormatter();
		nf.InitialCaps("Dr.MONOJIT CHAKRABORTY");
		//nf.InitialCaps("ashutosh sakalle");
		//nf.InitialCaps(null);
		//nf.InitialCaps("LoKeSh rAj");*/
		
	}

}
