<%@ include file="/init.jsp"%>
<portlet:renderURL var="diasporaForm">
	<portlet:param name="mvcPath" value="/diaspora/diasporaForm.jsp" />
</portlet:renderURL>
<portlet:renderURL var="diasporaSummery">
	<portlet:param name="mvcPath" value="/diaspora/diasporaSummery.jsp" />
</portlet:renderURL>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
		
 <liferay-ui:error key="error" message="Illegal argument found" /> 
			<p>One of the several progressive elements of the broader
				organizational family of ONGC is that many of the wards of ONGCians
				are in foreign countries, either studying there or settled in jobs.
				To leverage this collective opportunity of such overseas presence on
				the web-based platform, ONGCReports launches ONGC Diaspora. ONGCians
				– whose wards are aspiring for overseas studies – can Find and Seek
				Advice from ONGC family members in the countries of interest.</p>
			<p>ONGCians can search for students at various universities
				across the world and connect, seeking information and advice from
				them (View Diaspora). This will empower ONGCReports users to make
				informed decisions for their wards. To populate the other end of the
				database, ONGC Diaspora is seeking data from the wards of ONGCians
				settled abroad (Register as a Diaspora), so that they can act as
				anchors to advise the younger generation. ONGCians , whose wards are
				desirous of searching academic and related opportunities outside
				India, can 'Search' (under View Diaspora) this platform for useful
				information from their colleagues' wards who have moved there
				earlier.</p>
			<p>Let us start populating this platform; Click on the 'Register
				as a Diaspora'</p>
			<p>
				<a href="<%=diasporaSummery%>" class="btn btn-primary">View
					Diaspora</a> &nbsp; <a href="<%= diasporaForm %>"
					class="btn btn-primary">Register as a Diaspora</a>
			</p>
		</div>
	</div>
</div>