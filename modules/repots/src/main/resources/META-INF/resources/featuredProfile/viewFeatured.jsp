
<%@page import="com.ongc.liferay.connection.DatasourceConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ include file="/init.jsp" %>

	<TABLE id="featured"
		class="dataTables table table-bordered table-striped ">
		<TR>
			<TH>ID</TH>
			<TH>CPF NO</TH>
			<TH>EMPLOYEE NAME</TH>
			<TH>HIGHLIGHT 1</TH>
			<TH>HIGHLIGHT 2</TH>
			<TH>HIGHLIGHT 3</TH>
			<TH>HIGHLIGHT 4</TH>
			<TH>HIGHLIGHT 5</TH>
			<TH>ABOUT</TH>
			<TH>PASSION</TH>
			<TH>OTHERS</TH>
			<TH>PHOTO</TH>
			<TH>UPDATED ON</TH>

		</TR>
		<% 
              Connection conn=null;
               Statement statement=null;
               ResultSet resultset=null;
             
                
   try{
             conn = DatasourceConnection.getConnection();
             statement = conn.createStatement();

           

             resultset = 
                statement.executeQuery("select FP.F_ID,FP.CPFNUM,oum.emp_name,FP.HIGH1,FP.HIGH2,FP.HIGH3,FP.HIGH4,FP.HIGH5,FP.ABOUT,FP.PASSION,FP.OTHERS,FP.PHOTO,FP.UPDATED_ON from FEATURED_PROFILE FP,ongc_user_master OUM WHERE oum.cpf_number=fp.cpfnum and status='N' order by FP.F_ID desc") ; 

            while(resultset.next()){
        %>



		<TR>
			<TD><%= resultset.getString(1) %></TD>
			<TD><%= resultset.getString(2) %></TD>
			<TD><%= resultset.getString(3) %></TD>
			<TD><%= resultset.getString(4) %></TD>
			<TD><%= resultset.getString(5) %></TD>
			<TD><%= resultset.getString(6) %></TD>
			<TD><%= resultset.getString(7) %></TD>
			<TD><%= resultset.getString(8) %></TD>
			<TD><%= resultset.getString(9) %></TD>
			<TD><%= resultset.getString(10) %></TD>
			<TD><%= resultset.getString(11) %></TD>
			<TD><img class="img-thumbnail" style="max-height: 50px;max-width:50px;" src="<%= themeDisplay.getURLPortal() %>/o/blade/servletProfileRepots/imageRepots?cpfno=<%= resultset.getString(1) %>" class="thImage" /></TD>
			<TD><%= resultset.getString(13) %></TD>
		</TR>


		<% 
           } 
           }catch(Exception e)
           {
            System.out.println("Exception in view profile data");
           }
          finally {
		DatasourceConnection.closeConnection(conn, statement, resultset);
	}
       %>
	</TABLE>
	<script>

	$(document).ready(function() {
		 var table = $('#featured').DataTable( {
			 lengthChange: false,bFilter: false, bInfo: false,pageLength: 100
		    });
		}); 
	</script>
