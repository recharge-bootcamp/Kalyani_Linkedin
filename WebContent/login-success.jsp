<%@page import="com.ppbootcamp.pronetwork.entity.LoginBean"%>

<p>You are successfully logged in!</p>
<%
LoginBean bean=(LoginBean)request.getAttribute("bean");
out.print("Welcome, "+bean.getMailid());
%>