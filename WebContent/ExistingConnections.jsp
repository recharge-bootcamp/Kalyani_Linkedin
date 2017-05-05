<%@page import="com.ppbootcamp.pronetwork.entity.ConnectionBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
Your ProNetwork Connections
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
</head>
<body>
<% 
ArrayList<ConnectionBean>  connBeanList = (ArrayList<ConnectionBean>)request.getAttribute("connBeanList");
%>

<div id="Connections">
<% if(connBeanList!=null && connBeanList.size()>0) {
	for (ConnectionBean connectionBean: connBeanList) { %>
	
<h3><%= connectionBean.getName() %></h3>
<% } } %>
</div>
</body></html>