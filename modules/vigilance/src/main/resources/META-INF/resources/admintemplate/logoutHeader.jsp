<%@ include file="/init.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
	href='<%=request.getContextPath() %>/css/style.css' type="text/css">
</head>
  <body>
<!-- header start-->
    
<div class="topSecBg">
	<div class="container-fluid">	
	<div class="row">
	<div class="col-md-5 col-sm-12">
	<div id="logo-wrapper">
	<div class="logoSection">
	<div class="logo d-flex" aria-hidden="true">
	<a title="Bandhan" href="#" class="d-flex">
		<img src='<%=request.getContextPath()%>/images/logo.png'
		alt="logo" />
	</div>
	</a>
	<div class="aajadiLogo"><img src='<%=request.getContextPath()%>/images/azadiLogo.png'
		alt="logo" /></div>
	</div>
	</div>
	</div>
	<div class="col-md-7 col-sm-12">
	<div id="barTop-wrapper">
	<div class="barTop">
	<div class="linktop_div" >    
	<ul class="list-unstyled list-inline mb-0">
	<li class="list-inline-item"><img src='<%=request.getContextPath()%>/images/lastUpdated.png' class="webIcon mr-2" />Website Last Updated On 16 February 2022 05:31:00 pm</li>
	<li class="list-inline-item"><a href="#skip-main-content" class="" title="Skip to main content" >Skip to main content</a></li>
	<li class="hidden-xs list-inline-item" >
	<a href="javascript:void(0);" data-toggle="modal" data-target="#myModalPop" class="" title="Screen Reader Access" >Screen Reader Access</a>
	</li>
	<li class="list-inline-item">
	<div class="dropdown"><a href="#" class="fontImg" class="dropdown-toggle" data-toggle="dropdown">
	<img class="access-light-theme" src='<%=request.getContextPath()%>/images/accessibility.png' alt="Accessibility Options">
	<img class="access-dark-theme" src='<%=request.getContextPath()%>/images/accessibility-white.png' alt="Accessibility Options">
	</a>
	
	<ul class="ft-con-ul dropdown-menu">
	<li class="ft-control hidden-xs hidden-sm font-set-option">
	<a tabindex="0" href="javascript:void(0);" title="Click to increase font" class="inc" aria-label="Click to increase font"><span>+A</span> Increase Font</a>
	<a href="javascript:void(0);" title="Click to increase font" class="inc2" aria-hidden="true" aria-label="Click to increase font"><span>+A</span> Increase Font</a>
	<a tabindex="0" href="javascript:void(0);" title="Click to normal font" class="nml" aria-label="Click to normal font"><span>A</span> Normal Font</a>
	<a tabindex="0" href="javascript:void(0);" title="Click to decrease font" class="dec" aria-label="Click to decrease font"><span>-A</span> Decrease Font</a>
	<a href="javascript:void(0);" title="Click to decrease font" class="dec2" aria-hidden="true" aria-label="Click to decrease font"><span>-A</span> Decrease Font</a></li>
	<li class="ft-control"><a tabindex="0" href="javascript:void(0);" id="dark-theme" aria-label="Select Dark Theme" class="clr-white" title="Select Dark Theme"><span class="black-bg ">A</span> Dark Theme</a>
	<a tabindex="0" aria-selected="true" href="javascript:void(0);" id="light-theme" class="light-theme" aria-label="Select Normal Theme" title="Select Normal Theme"><span>A</span> Normal Theme</a></li>
	</ul>
	</div>
	</li>
	
	</ul>
	</div>

	</div>	
	</div>
	
	</div>
	
	</div>
	
	
	
	
	
	</div>
	</div>
    
    <!--header end-->  </body>
</html>