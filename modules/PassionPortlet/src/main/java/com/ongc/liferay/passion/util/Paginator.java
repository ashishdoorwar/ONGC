package com.ongc.liferay.passion.util;

import java.util.List;

public class Paginator {

	public int SHOW_ROWS=10;
	private int size;
	private List<Object> list;

	private int startWith;
	private int index;


	public Paginator(List<Object> list,int noOfRows,String startWith){
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

	public String createIndex() {
		StringBuffer sb = new StringBuffer();
		if (size > SHOW_ROWS) {
			int max=startWith+SHOW_ROWS; 
			if(max>size) max=size;
			

			if (startWith > 0){
				sb.append("<li><a href='javascript:callPostPage("+(startWith-SHOW_ROWS)+")'>");
				sb.append("<img src='/wps/PA_ONGC_Passion/images/prv-bu.jpg' alt='' />");
				sb.append("</a></li>");
				//if(startWith > 1)
				//sb.append("<li><span class='pagination_dots'>...</span></li>");
			}
			else{
				//sb.append("<span class='prev'>");
				sb.append("<li><img src='/wps/PA_ONGC_Passion/images/prv-bu.jpg' alt='' /></li>");
				//sb.append("</span>");
			}
			int x = 0;
			int y = 1;

			if(size<=5*SHOW_ROWS)
			{
				while (x < size) {
					if(x!=startWith){
						sb.append(" <li><a href='javascript:callPostPage("+x+")' class='page'>"+y+" </a></li>");
					}
					else{
						sb.append(" <li><a style='color:grey'>"+y+"</a></li>");
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
						sb.append(" <li><a href='javascript:callPostPage("+x+")'>"+y+" </li>");
					}
					else{
						sb.append(" <li><a>"+y+" </a></li>");
					}
					y++;
					x += SHOW_ROWS;
				}
			}
			if (index< size){
				sb.append("<li><a href='javascript:callPostPage("+(startWith + SHOW_ROWS)+")'>");
				sb.append("<img src='/wps/PA_ONGC_Passion/images/nxt-bu.jpg' alt='' />");
				sb.append("</a></li>");
			}
			else{
				//sb.append("<span class='prev'>");
				sb.append("<li><img src='/wps/PA_ONGC_Passion/images/nxt-bu.jpg' alt='' /></li>");
				//sb.append("</span>");
			}

			}

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
