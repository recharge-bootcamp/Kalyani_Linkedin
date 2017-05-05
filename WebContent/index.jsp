<html>
<head>
Welcome to ProNetwork
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
</head>

<body>
<h1>Already an user, Sign in</h1> <br>
<div id="newUserLayout"> 
<form action="ControllerServlet" method="post">
<input type="hidden" name="action_details" value="loginauth" ><br>
Mail Id: <input type="email" name="mailid" required><br><br>
Password: <input type="password" name="password" required><br><br>
<input type="submit" value="login"><br>
</div>
<h1><a href="NewUser.jsp"> New User!Sign Up</a></h1><br>
</form> 
</body>

</html>