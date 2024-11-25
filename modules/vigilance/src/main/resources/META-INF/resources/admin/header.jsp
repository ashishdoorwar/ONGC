<portlet:renderURL var="adminHome">
	<portlet:param name="mvcRenderCommandName" value="adminHome" />
</portlet:renderURL>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
	href='<%=request.getContextPath() %>/WEB-INF/jsp/admintemplate/.Vigilance/css/style.css' type="text/css">
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
	<a title="Vigilance Logo" href="<%=adminHome%>" class="d-flex">
		<img src='<%=request.getContextPath()%>/images/logo.png'
		alt="logo" />
	</a>
	</div>
	
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
	<%-- <li class="list-inline-item"><img src='<%=request.getContextPath()%>/images/lastUpdated.png' class="webIcon mr-2" />Website Last Updated On 16 February 2022 05:31:00 pm</li> --%>
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

<div class="modal fade" id="myModalPop" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Screen Reader Access</h4>
        </div>
        <div class="modal-body">
   		

<div class="table-responsive" dir="ltr">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>
					Screen Reader</th>
				<th>
					Website</th>
				<th>
					Free / Commercial</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					Non Visual Desktop Access (NVDA)</td>
				<td>
					<a href="http://www.nvda-project.org/" title="External website that opens in a new window" target="_blank">http://www.nvda-project.org/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					System Access To Go</td>
				<td>
					<a href="http://www.satogo.com/" title="External website that opens in a new window" target="_blank">http://www.satogo.com/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Thunder</td>
				<td>
					<a href="http://www.screenreader.net/index.php?pageid=11" title="External website that opens in a new window" target="_blank">http://www.screenreader.net/index.php?pageid=11<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					WebAnywhere</td>
				<td>
					<a href="http://webanywhere.cs.washington.edu/wa.php" title="External website that opens in a new window" target="_blank">http://webanywhere.cs.washington.edu/wa.php<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Hal</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=5" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=5<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					JAWS</td>
				<td>
					<a href="http://www.freedomscientific.com/jaws-hq.asp" title="External website that opens in a new window" target="_blank">http://www.freedomscientific.com/jaws-hq.asp<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Supernova</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=1" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=1<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Window-Eyes</td>
				<td>
					<a href="http://www.gwmicro.com/Window-Eyes/" title="External website that opens in a new window" target="_blank">http://www.gwmicro.com/Window-Eyes/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
		</tbody>
	</table>
</div>
						</div>
        </div>
      </div>
    </div> 
<%-- <portlet:renderURL var="adminHome">
	<portlet:param name="mvcRenderCommandName" value="adminHome" />
</portlet:renderURL>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
	href='<%=request.getContextPath() %>/WEB-INF/jsp/admintemplate/.Vigilance/css/style.css' type="text/css">
</head>
  <body>
<!-- header start-->
     <div class="container">
	 <div class="row">
	 <div class="col-md-6">
          <div class="logo-section">
          <a href="<%=adminHome%>">
          	<img src='<%=request.getContextPath() %>/images/logo.png'  alt="logo" /></a>
             </div>
			 </div>
			 <div class="col-md-6 text-right">
			 <div class="right-acces">
			 <ul class="list-inline ft14 mn">
<li class=""><a href="#skip-main-content" title="Skip to main content">Skip to main content</a></li>
<li class=""><a data-toggle="modal" data-target="#myModalPop" href="#" title="Screen reader access">Screen reader access</a></li>
</ul>
</div>
<div class="right-acces">
<ul class="list-inline ft14 mn font-set-option">
<li class="ft-control fnt-inc-op">
<a href="javascript:void(0);" title="Click to increase font" class="inc">+A</a>
<a href="javascript:void(0);" title="Click to increase font" class="inc2">+A</a>
<a href="javascript:void(0);" title="Click to normal font" class="nml">A</a>
<a href="javascript:void(0);" title="Click to decrease font" class="dec">-A</a>
<a href="javascript:void(0);" title="Click to decrease font" class="dec2">-A</a>
</li>

<li class="ft-control"><span id="dark-theme" class="black-bg clr-white" title="Select Dark Theme">A</span><span id="light-theme" class="light-theme" title="Select Normal Theme">A</span></li>
<li><a href="https://india.gov.in/" title="National Portal" target="_blank"><img src="<%=request.getContextPath() %>/images/ongc_indiagov.jpg" alt="National Portal" /></a></li>
</ul>
</div>

</div>
			 </div>
        </div>
    
    <!--header end-->  </body>
</html>

<div class="modal fade" id="myModalPop" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Screen Reader Access</h4>
        </div>
        <div class="modal-body">
   		

<div class="table-responsive" dir="ltr">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>
					Screen Reader</th>
				<th>
					Website</th>
				<th>
					Free / Commercial</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					Non Visual Desktop Access (NVDA)</td>
				<td>
					<a href="http://www.nvda-project.org/" title="External website that opens in a new window" target="_blank">http://www.nvda-project.org/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					System Access To Go</td>
				<td>
					<a href="http://www.satogo.com/" title="External website that opens in a new window" target="_blank">http://www.satogo.com/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Thunder</td>
				<td>
					<a href="http://www.screenreader.net/index.php?pageid=11" title="External website that opens in a new window" target="_blank">http://www.screenreader.net/index.php?pageid=11<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					WebAnywhere</td>
				<td>
					<a href="http://webanywhere.cs.washington.edu/wa.php" title="External website that opens in a new window" target="_blank">http://webanywhere.cs.washington.edu/wa.php<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Free</td>
			</tr>
			<tr>
				<td>
					Hal</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=5" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=5<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					JAWS</td>
				<td>
					<a href="http://www.freedomscientific.com/jaws-hq.asp" title="External website that opens in a new window" target="_blank">http://www.freedomscientific.com/jaws-hq.asp<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Supernova</td>
				<td>
					<a href="http://www.yourdolphin.co.uk/productdetail.asp?id=1" title="External website that opens in a new window" target="_blank">http://www.yourdolphin.co.uk/productdetail.asp?id=1<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
			<tr>
				<td>
					Window-Eyes</td>
				<td>
					<a href="http://www.gwmicro.com/Window-Eyes/" title="External website that opens in a new window" target="_blank">http://www.gwmicro.com/Window-Eyes/<span class="hidethis"> (External website that opens in a new window)</span></a></td>
				<td>
					Commercial</td>
			</tr>
		</tbody>
	</table>
</div>
						</div>
        </div>
       
      </div>
      
    </div> --%> 