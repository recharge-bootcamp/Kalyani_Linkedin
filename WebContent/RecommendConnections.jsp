<%@page import="com.ppbootcamp.pronetwork.entity.ConnectionBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
Your ProNetwork Connections
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
</head>
<body>
<form action="ControllerServlet" method="post" >

<% 
ArrayList<ConnectionBean>  connBeanList = (ArrayList<ConnectionBean>)request.getAttribute("connBeanList");
%>

<div id="Connections">
<% if(connBeanList!=null && connBeanList.size()>0) {
	
	String propName=null;
	String chkpropName = null;
	Integer index = 1;
	String propNameIndex; 
	for (ConnectionBean connectionBean: connBeanList) { 
		propName="connID";
		chkpropName="chkConnID";
		chkpropName = chkpropName.concat(index.toString());  
		propNameIndex = propName.concat(index.toString());  
		index++; %>
	<label><input name="connlist" type="checkbox" value=<%= connectionBean.getId() %>><%= connectionBean.getName() %></label>
	<input type="hidden" id=<%= propNameIndex %> value=<%= connectionBean.getId() %> >
<% } } %>
<input type="hidden" name="id" id="id" value=<%= session.getAttribute("id") %> >
<!-- <input type="text" id="id" name="id" value="9" > -->
<input type="hidden" id="action_details" name="action_details" value="sendinvitations">
<input type="submit" value="sendinvitations" >
</div>

</form>
</body></html>