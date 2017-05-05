<%@page import="com.ppbootcamp.pronetwork.entity.PersonBean"%>
<%@page import="com.ppbootcamp.pronetwork.entity.EmploymentBean"%>
<%@page import="com.ppbootcamp.pronetwork.entity.EducationBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
Welcome to ProNetwork
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
</head>
<body>
<% 
PersonBean personBean=(PersonBean)request.getAttribute("bean");
%>
<div id="PersonalDetails">
<h3>Name: <%= personBean.getName() %></h3>
<h3>Mail ID: <%= personBean.getMailid() %></h3> 
</div>

<% 
ArrayList<EmploymentBean> employmentBeanList = personBean.getEmploymentBean();

if(employmentBeanList!=null && employmentBeanList.size()>0) { %>
<h3>Employment Details:</h3>
<% 
	for (EmploymentBean employmentBean: employmentBeanList) {
%>	
	<h4>Company :  <%= employmentBean.getCompany() %></h4>
	<h4>Start Year :<%= employmentBean.getSyear() %></h4>
	<h4>End Year : <%= employmentBean.getEyear() %></h4><br>	
<%	}
}
%>
<% 
ArrayList<EducationBean> educationBeanList = personBean.getEducationBean();

if(educationBeanList!=null && educationBeanList.size()>0) { %>
<h3>Education Details </h3>
<% 
	for (EducationBean educationBean: educationBeanList) {
%>	
	<h4>Company :  <%= educationBean.getInstitution() %> </h4>
	<h4>Degree : <%= educationBean.getDegree() %></h4>
	<h4>Year : <%= educationBean.getYear() %></h4>	<br>
<%	}
}
%>
<h3>Skills : <%= personBean.getSkills() %></h3>
</body>
</html>