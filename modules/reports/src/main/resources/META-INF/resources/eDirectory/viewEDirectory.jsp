<%@ include file="/init.jsp"%>
<style>
	/*#e-directorymnu .dropdown-submenu {
    position: relative;
}

#e-directorymnu .dropdown-submenu>.dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
    margin-left: 1px;
    -webkit-border-radius: 0 6px 6px 6px;
    -moz-border-radius: 0 6px 6px;
    border-radius: 0 6px 6px 6px;
	border: solid 1px #9e4444;
}

#e-directorymnu .dropdown-submenu:hover>.dropdown-menu {
    display: block;
}

#e-directorymnu .dropdown-submenu>a:after {
    display: block;
    content: " ";
    float: right;
    width: 0;
    height: 0;
    border-color: transparent;
    border-style: solid;
    border-width: 5px 0 5px 5px;
    border-left-color: #ccc;
    margin-top: 5px;
    margin-right: -10px;
}

#e-directorymnu .dropdown-submenu:hover>a:after {
    border-left-color: #fff;
}

#e-directorymnu .dropdown-submenu.pull-left {
    float: none;
}

#e-directorymnu .dropdown-submenu.pull-left>.dropdown-menu {
    left: -100%;
    margin-left: 10px;
    -webkit-border-radius: 6px 0 6px 6px;
    -moz-border-radius: 6px 0 6px 6px;
    border-radius: 6px 0 6px 6px;
}
		.navbar-nav>li>.dropdown-menu{ border-top:1px;}		
#e-directorymnu .navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:focus, .navbar-default .navbar-nav>.active>a:hover{background-color: #dcb2b5;color: #000;}
#e-directorymnu .navbar-default .navbar-nav>li>a:focus, .navbar-default .navbar-nav>li>a:hover{background-color: #e7e7e7;}
#e-directorymnu .nav>li>a{padding: 5px 15px; border-right: 1px solid #e0e0e0; color:#fff;}
		#e-directorymnu .dropdown-menu>li:last-child>a{border-bottom: 0;}
#e-directorymnu .navbar-default{background-color: #770606; border-radius:0px;}

#e-directorymnu .navbar{ min-height:30px;}
#e-directorymnu .dropdown-menu{z-index: 1; padding-top: 0px; }
#e-directorymnu .dropdown-menu>li>a{border-bottom: 1px solid #9e4444;padding: 6px 20px; }
	

@media screen and (max-width: 1500px) and (min-width: 980px) {
#e-directorymnu .navbar-nav {
  width: 100%;
  text-align: center;
}
#e-directorymnu .navbar-nav > li {
  float: none;
  display: inline-block;
}
	
}




*/
	</style>

<!--<%@ include file="/eDirectory/breadcrumb.jsp"%>-->
<%@ include file="/eDirectory/edirectoryMenu.jsp"%>
<div class="row">
	<div class="col-md-8 ">
		<img src="<%=request.getContextPath()%>/images/map.PNG">
	</div>
	<div class="col-md-4 ">
	<%@ include file="/eDirectory/rightTab.jsp"%>
	</div>
</div>

