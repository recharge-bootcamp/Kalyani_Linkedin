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


<% 
String connIDStr="";
if(connBeanList!=null && connBeanList.size()>0) { %>
<form action="ControllerServlet" method="post" >
<div id="Connections">
<% 
	String propName="";
	String chkpropName="";
	Integer index = 1;
	for (ConnectionBean connectionBean: connBeanList) { 
		propName="connid";
		chkpropName="conninv";
		propName= propName.concat(index.toString());
		chkpropName = chkpropName.concat(index.toString());
		index++;  %>
		<label><%= connectionBean.getName() %></label>
		<input type="hidden" name=<%= propName%> value=<%= connectionBean.getId() %>> 
		<input type="radio" name=<%= chkpropName %> value="Y">Accept
	    <input type="radio" name=<%= chkpropName %> value="N">Decline
		<br>
<% } %>

</div>
<input type="hidden" name="id" id="id" value=<%= session.getAttribute("id") %> >
<input type="hidden" name="size" value=<%= index-1 %> >

<!--  <input type="hidden" id="id" name="id" value="9" > -->
<input type="hidden" id="action_details" name="action_details" value="updateinvitations">
<input type="submit" value="Update" >
</form>
<%  } %>
</body></html>