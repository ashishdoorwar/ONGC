<%@ include file="/init.jsp" %>
 <portlet:renderURL var="facilitationSummery" > 
 	<portlet:param name="mvcPath" value="/facilitation/facilitation.jsp"/>
 </portlet:renderURL>
  <portlet:renderURL var="facilitationPostOffer" > 
 	<portlet:param name="mvcPath" value="/facilitation/facilitationPostForm.jsp"/>
 </portlet:renderURL>
  <portlet:renderURL var="facilitationSubscribe" > 
 	<portlet:param name="mvcPath" value="/facilitation/subscribeForm.jsp"/>
 </portlet:renderURL>
<div class="container">
<div class="row">
<div class="col-lg-12">
 <liferay-ui:error key="error" message="Illegal argument found" /> 
<p dir="ltr">This new feature 'Facilitation Center' empowers ONGCians to rent, sell, share goods and services with colleagues, as it has often been seen that during and even after the transfer season, ONGCians have several products or services they would like to sell or share with other ONGCians.</p>
<p dir="ltr">To begin with, ONGCians will be able to offer these on ONGCReports in four categories viz. Accommodation, Car Pooling, House-hold Goods and Vehicles. The user has to go to the 'Post Offer' field on the top right and fill up the fields and Submit. This will show up in the 'View Offers' page, which can be searched under the appropriate Location and Category.</p>
<p dir="ltr">Benchmarking smart purging practices, offers posted on Facilitation Center (except Matrimony) more than one month ago will need to be deleted w.e.f. 15 July 2015, as the posts usually outlive their relevance. If users want an extended shelf life, the offers can be reposted by the concerned user(s).</p>
<div dir="ltr">
<a href="<%= facilitationSummery %>" title="View Offers"  class="btn btn-primary">View Offers</a> 
<a href="<%= facilitationPostOffer %>" title="View Offers"  class="btn btn-primary">Post Offers</a> 
<a href="<%= facilitationSubscribe %>" title="Subscribe"  class="btn btn-primary">Subscribe</a></div>
</div>
</div>
</div>


