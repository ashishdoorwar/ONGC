<%@ include file="/init.jsp"%>
 <portlet:renderURL var="covidSewa" > 
 	<portlet:param name="mvcPath" value="/covid/covidSewa.jsp"/>
 </portlet:renderURL>
  <portlet:renderURL var="oxygenBed" > 
 	<portlet:param name="mvcPath" value="/covid/covidOxygenBed.jsp"/>
 </portlet:renderURL>
  <portlet:renderURL var="covidPlasma" > 
 	<portlet:param name="mvcPath" value="/covid/covidplasma.jsp"/>
 </portlet:renderURL>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
		
 <liferay-ui:error key="error" message="Illegal argument found" /> 
			<a href="<%= covidSewa %>" title="Please Volunteer for COVID SEWA"  class="btn btn-sm btn-primary">Please Volunteer for COVID SEWA</a> 
			<a href="<%= themeDisplay.getURLPortal() %>/documents/59362/0/helpdesk270521.pdf" title="24x7 Covid Helpline numbers list"  class="btn btn-sm btn-primary">24x7 Covid Helpline numbers list</a> 
			<a href="<%= oxygenBed %>" title="Log Your Request for Beds/Oxygen"  class="btn btn-sm btn-primary">Log Your Request for Beds/Oxygen</a>		
			<a href="<%= covidPlasma %>" title="Log Your Request for Covid Plasma"  class="btn btn-sm btn-primary">Donate Covid Plasma</a>
		</div>
		</div>		
		</div>
	</div>
</div>