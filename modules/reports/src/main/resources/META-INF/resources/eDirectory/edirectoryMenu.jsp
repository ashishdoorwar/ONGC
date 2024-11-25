<div id="e-directorymenu">
<nav class="navbar navbar-expand-md bg-dark navbar-dark mb-4 mt-2" >
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
       <li class="nav-item active">
        <portlet:renderURL var="home">
        		<portlet:param name="mvcPath" value="/eDirectory/viewEDirectory.jsp" />
		</portlet:renderURL>
        <a class="nav-link "  style="color:white" href="<%=home%>">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" style="color:white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Organization
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="DGH" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">DGH</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="MOPNG" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">MOPNG</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="OIDB" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">OIDB</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="OISD" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">OISD</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="OVL" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">OVL</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="PCRA" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">PCRA</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="Petrotech" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">Petrotech</a>
          
          
          <portlet:renderURL var="searchOrganization">
			<portlet:param name="mvcRenderCommandName"
				value="search_organization" />
				<portlet:param name="redirect" value="/eDirectory/searchOrgResult.jsp" />
			<portlet:param name="organizationName" value="PLL" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchOrganization%>">PLL</a>
          
          
         
      </li>
     
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" style="color:white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Location
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <portlet:renderURL var="searchLocation">
        		<portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Agartala" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Agartala</a>
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Ahmedabad" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Ahmedabad</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Ankleshwar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Ankleshwar</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Bokaro" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Bokaro</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Cambay" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Cambay</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Chennai" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Chennai</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Dehradun" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Dehradun</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Delhi" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Delhi</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Goa" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Goa</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Hazira" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Hazira</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Jodhpur" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Jodhpur</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Jorhat" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Jorhat</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Kakinada" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Kakinada</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Karaikal" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Karaikal</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Kolkatta" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Kolkatta</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Mehsana" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Mehsana</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Mumbai" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Mumbai</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="West. Offshore" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">West. Offshore</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Nazira" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Nazira</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Panvel" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Panvel</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Rajahmundry" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Rajahmundry</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Silchar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Silchar</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Sivasagar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Sivasagar</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Uran" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Uran</a>
          
         
         
          <portlet:renderURL var="searchLocation">
          <portlet:param name="mvcPath" value="/eDirectory/searchLocationForm.jsp" />
			<portlet:param name="locationName" value="Vadodara" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchLocation%>">Vadodara</a>
          
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" style="color:white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Workplaces
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Drilling Rig" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Drilling Rig</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Institutes" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Institutes</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Offices" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Offices</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Onshore Prod" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Onshore Prod</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Plants" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Plants</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Stores" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Stores</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Workover Rig" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Workover Rig</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Workshop" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Workshop</a>
          
          
           <portlet:renderURL var="searchWorkplace">
        		<portlet:param name="mvcPath" value="/eDirectory/searchWorkPlaceForm.jsp" />
			<portlet:param name="workplaceName" value="Others" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchWorkplace%>">Others</a>
          
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" style="color:white" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          ONGC Utilities
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Association" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Association</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Civil" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Civil</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Colony" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Colony</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Control Room" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Control Room</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Corp. Comm." />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Corp. Comm.</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Dispensary" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Dispensary</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Electrical" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Electrical</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Fire Control" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Fire Control</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Guest Houses" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Guest Houses</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Hospitality Nos." />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Hospitality Nos.</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Hospitals" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Hospitals</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Infocom" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Infocom</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Logistics" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Logistics</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="School" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">School</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Security" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Security</a>
         
         
          <portlet:renderURL var="searchUtility">
        		<portlet:param name="mvcPath" value="/eDirectory/searchUtilityForm.jsp" />
			<portlet:param name="utilityName" value="Water Supply" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchUtility%>">Water Supply</a>
         
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" style="color:white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Directory Assistance
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <portlet:renderURL var="searchDirAssistance">
			<portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
				<portlet:param name="redirect" value="/eDirectory/searchDirAssistanceResult.jsp" />
				<portlet:param name="directoryAssistanceName" value="Agartala" />
				</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance %>">Agartala</a>
          
          
          <portlet:renderURL var="searchDirAssistance">
			<portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
				<portlet:param name="redirect" value="/eDirectory/searchDirAssistanceResult.jsp" />
				<portlet:param name="directoryAssistanceName" value="Ahmedabad" />
				</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance %>">Ahmedabad</a>
          
          
           
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Ankleshwar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Ankleshwar</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Bokaro" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Bokaro</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Cambay" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Cambay</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Chennai" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Chennai</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Dehradun" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Dehradun</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Delhi" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Delhi</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Goa" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Goa</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Hazira" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Hazira</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Jodhpur" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Jodhpur</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Jorhat" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Jorhat</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Kakinada" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Kakinada</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Karaikal" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Karaikal</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Kolkatta" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Kolkatta</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Mehsana" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Mehsana</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Mumbai" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Mumbai</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="West. Offshore" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">West. Offshore</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Nazira" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Nazira</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Panvel" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Panvel</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Rajahmundry" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Rajahmundry</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Silchar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Silchar</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Sivasagar" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Sivasagar</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Uran" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Uran</a>
          
         
         
          <portlet:renderURL var="searchDirAssistance">
          <portlet:param name="mvcRenderCommandName"
				value="search_dir_assistance" />
          <portlet:param name="mvcPath" value="/eDirectory/searchDirAssistanceResult.jsp" />
			<portlet:param name="locationName" value="Vadodara" />
		</portlet:renderURL>
          <a class="dropdown-item" href="<%=searchDirAssistance%>">Vadodara</a>
        
        </div>
      </li>
    </ul>
  </div>
</nav>
</div>