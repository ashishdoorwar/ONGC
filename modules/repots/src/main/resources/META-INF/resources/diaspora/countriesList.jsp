<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
 <% 
	try{     
		 String s[]=null;
         Connection conn=null;
		 conn = DatasourceConnection.getConnection();
		 Statement st=conn.createStatement(); 
		 ResultSet rs = st.executeQuery("select countries from countries");
	     List li = new ArrayList();
			while(rs.next()) 
 			{ 	
 			    li.add(rs.getString(1));
 			}  
			String[] str = new String[li.size()];			
			Iterator it = li.iterator();
			int i = 0;
			while(it.hasNext())
			{
				String p = (String)it.next();	
				str[i] = p;
				i++;
			}
				String query = (String)request.getParameter("q");
				
				int cnt=1;
				StringBuffer xmlBuffer = new StringBuffer();
				xmlBuffer.append("<ul class='autocomplete'>");
				for(int j=0;j<str.length;j++)
				{
					if(str[j].toUpperCase().startsWith(query.toUpperCase()))
					{
						xmlBuffer.append("<li>"+str[j]+"</li>");
						if(cnt>=5)
							break;
						cnt++;
					}
				}
				xmlBuffer.append("</ul>");
				out.print(xmlBuffer.toString());
 		rs.close(); 
 		st.close(); 
		conn.close();

		    } 
		catch(Exception e){ 
 			e.printStackTrace(); 
 		}
 %>