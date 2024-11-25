<%@ include file="/init.jsp" %>

   <header>
   <%@ include file="/admin/header.jsp" %>
    </header>
    <!--header end-->
       
    <!--Start Main Contant section-->
      <div class="container" id="skip-main-content">
       <div class="row">
    <!--left section start-->
    
      <div class="col-md-3">
      <%@ include file="/admin/menu.jsp" %>
      	</div>
           
       <!--left section end-->
       
       <!--right section start-->

      <div class="col-md-9">
      <%@ include file="/admin/content.jsp" %> 
       </div>
   </div>
       </div>
           <footer>
           <%@ include file="/admin/footer.jsp" %>
         </footer>
    <!--End Footer-->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.js"></script> 
 <script>
   $(document).ready(function () {
                
                $('.input-daterange').datepicker({
                    todayBtn: "linked"
                });
        
            });
 </script>
       
