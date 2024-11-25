package com.ongc.liferay.vigilance.util;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class ComplaintPaginator {


	public int SHOW_ROWS=3;
	private int size;
	private List<Object> list;
	
	private int startWith;
	private int index;
	
   
	public ComplaintPaginator(List<Object> list,int noOfRows,String startWith){
		
		if(list==null)
			this.size=0;
		else
		 this.size=list.size();
		
		 this.list=list;
	
		this.SHOW_ROWS=noOfRows;
	
		
		if(startWith==null) startWith="0";
	this.index=	this.startWith=Integer.parseInt(startWith);
	}
	public boolean isNext(){
		
		return index<size && index<(startWith + SHOW_ROWS);
	}
	public Object next(){
		return list.get(index++);
	}
	public int getIndex(){return index;}

	public String createIndex(String jsMethodName) {
	System.out.println(jsMethodName+"=======================================>");
		
		//suri="http://125.21.240.248/africa/mobileAction.web";
		
		

		StringBuffer sb = new StringBuffer();
		if (size > SHOW_ROWS) {
			int max=startWith+SHOW_ROWS; 
			if(max>size) max=size;
				sb.append("<div class='pagination2'> "); 
			   sb.append("<div class='wapper'>");
			  
		   
			if (startWith > 0){
				
	sb.append("<a href='javascript:"+jsMethodName+"(0)' class='first-p fa fa-fast-backward'>");
				
				sb.append("</a>");
			
				
				sb.append("<a href='javascript:"+jsMethodName+"("+(startWith-SHOW_ROWS)+")' class='prev glyphicon glyphicon-chevron-left'>");
				
				sb.append("</a>");
				//if(startWith > 1)
				//sb.append("<li><span class='pagination_dots'>...</span></li>");
			}
			else{
				sb.append("<span class='first-p fa fa-fast-backward'>");
				
				sb.append("</span>");
				sb.append("<span class='prev glyphicon glyphicon-chevron-left'>");
			
				sb.append("</span>");
			}
			int x = 0;
			int y = 1;
		
			if(size<=5*SHOW_ROWS)
			{
			while (x < size) {
				
				
				
				if(x!=startWith){
					sb.append(" <a href='javascript:"+jsMethodName+"("+x+")' class='page'>"+y+" </a>");
					
		
				}
				else{
					sb.append(" <span class='page'>"+y+"</span>");
						
				
				}
				y++;				
				x += SHOW_ROWS;
			}
			}else{
				int page=(startWith/SHOW_ROWS)+1;
				int tot=size/SHOW_ROWS;
				int ab=size%SHOW_ROWS;
				
				int fr=2;
				if(page<3)
					fr=5-page;
				if(ab>0)
					tot+=1;
				int end=page+fr;
				if(end>tot)
					end=tot;
				y=end-4;
				x=(y-1)*SHOW_ROWS;
				while (x < size&& y<=end) {
					
					
					
					if(x!=startWith){
						sb.append(" <a href='javascript:"+jsMethodName+"("+x+")' class='page'>"+y+" </a>");
						
					}
					else{
						sb.append(" <span class='page'>"+y+" </span>");
						
					}
					
					y++;
					
					
					x += SHOW_ROWS;
				}
			}
			if (index< size){
				
				int lst=size%SHOW_ROWS;
				lst=size-lst;
	
			
				
				sb.append("<a href='javascript:"+jsMethodName+"("+(startWith + SHOW_ROWS)+")' class='prev glyphicon glyphicon-chevron-right'>");
				
				sb.append("</a>");
				
				
				sb.append("<a href='javascript:"+jsMethodName+"("+(lst)+")' class='last-p fa fa-fast-forward'>");
							
							sb.append("</a>");
				}
			else{

				
				sb.append("<span class='prev glyphicon glyphicon-chevron-right'>");
				
				sb.append("</span>");
				
sb.append("<span class='last-p fa fa-fast-forward'>");
				
				sb.append("</span>");
				
			}
			 
			   sb.append("</div>");
			   sb.append("</div>");}
		
		return sb.toString();
		}
	private int getMaxPage(){
		int xy=0;
		while(xy+SHOW_ROWS<size){
			xy+=SHOW_ROWS;
		}
		return xy;
	}
	

}
