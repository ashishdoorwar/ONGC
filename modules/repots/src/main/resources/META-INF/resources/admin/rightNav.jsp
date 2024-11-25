<portlet:renderURL var="addOrderCircular">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
</portlet:renderURL>
<portlet:renderURL var="editOrderCircular">
<portlet:param name="mvcRenderCommandName"
				value="editOrderCircular" />
</portlet:renderURL>
<portlet:renderURL var="userLog">
<portlet:param name="mvcRenderCommandName"
				value="userLog" />
</portlet:renderURL>
<div id="left_link">
	<ul class="list-group mb0 m-menu-rs ">
		<li class="list-group-item"><a href="${addOrderCircular}">Add Order and Circular</a></li>
		<li class="list-group-item"><a href="${editOrderCircular}">Edit Order and
				Circular</a></li>

		<li class="list-group-item"><a href="${userLog}">User Log</a></li>


	</ul>
</div>