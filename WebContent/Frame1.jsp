<!DOCTYPE html>
<html>
<head>

</head>
<body>
<form action="ControllerServlet" method="pst">
<input type="hidden" id="id" name="id" value=<%= session.getAttribute("id") %> >
<input type="text" id="mailid" name="mailid" value=<%= session.getAttribute("mailid") %> >
<input type="hidden" id="action_details" name="action_details" value="personInfo" target="right">
<input type="submit" value="Profile" >
</form>
</body>
</html>