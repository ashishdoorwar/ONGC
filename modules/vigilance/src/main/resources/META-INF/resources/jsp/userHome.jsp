<%@ include file="/init.jsp" %>
<%@page import="java.util.List"%>

<% String loginId=(String)request.getAttribute("loginId"); %>
   <header>
   <%@ include file="/admintemplate/header.jsp" %>
    </header>
    <!--header end-->
       
    <!--Start Main Contant section-->
      <div class="container" id="skip-main-content">
       <div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
      <%@ include file="/admintemplate/menu.jsp" %>
      	</div>
           
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-9">
 <%@ include file="/admintemplate/content.jsp" %> 
       </div>
   </div>
       </div>
           <footer>
<%@ include file="/admintemplate/footer.jsp" %>           
         </footer>
   
       
