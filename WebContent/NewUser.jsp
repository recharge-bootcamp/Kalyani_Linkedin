<html>
<head>
<title> 
New User Page
</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
</head>
<body>

<script>
	function updateEducationDetails() {
		var eyear = document.getElementById("year").value;
		var college = document.getElementById("institution").value;
		var degree = document.getElementById("degree").value;
		if(eyear === undefined || eyear == null || eyear.length <= 0) {
			alert("Please specify year of education")
		}
		else if(college === undefined || college == null || college.length <= 0)  {
			alert("Please specify name of institution")
		}
		else if(degree === undefined || degree == null || degree.length <= 0) {
			alert("Please specify the degree")
		}
		else {
			eyear = eyear.toString();
			var ed_details = document.getElementById("educationdetails").value;
			var new_ed_details;
			var s1 = ":"
			if(ed_details === undefined || ed_details == null || ed_details.length <= 0) {
				new_ed_details = college.concat(s1,degree,s1,eyear);
			}
			else {
					new_ed_details = ed_details.concat(",",college,":",degree,":",eyear)
			}
			document.getElementById("year").value="";	
			document.getElementById("institution").value = "";
			document.getElementById("degree").value ="";
			alert(new_ed_details);
			document.getElementById("educationdetails").value = new_ed_details;
		}
	}
	
	function updateEmploymentDetails() {
		alert("reached here...1");
		var company = document.getElementById("company").value;
		var syear = document.getElementById("syear").value;
		var eyear = document.getElementById("eyear").value;
		alert("reached here...2");
		if(eyear === undefined || eyear == null || eyear.length <= 0) {
			alert("Please specify the end date of the tenure")
		}
		else if(company === undefined || company == null || company.length <= 0)  {
			alert("Please specify name of company")
		}
		else if(syear === undefined || syear == null || syear.length <= 0) {
			alert("Please specify the start date of the tenure")
		}

		else if(syear>eyear) {
			alert("Please recheck the tenure dates")
		}
		else {
			alert("reached the second point");
			eyear = eyear.toString();
			syear = syear.toString();
			var emp_details = document.getElementById("employmentdetails").value;
			var new_emp_details;
			var s1 = ":"
			if(emp_details === undefined || emp_details == null || emp_details.length <= 0) {
				alert("reached third point");
				new_emp_details = company.concat(s1,syear,s1,eyear);
			}
			else {
					new_emp_details = emp_details.concat(",",company,s1,syear,s1,eyear)
			}
			document.getElementById("company").value = "";
			document.getElementById("syear").value = "01-1980";
			document.getElementById("eyear").value = "01-2017";
			
			alert(new_emp_details);
			document.getElementById("employmentdetails").value = new_emp_details;
		}
	}	
</script>

<h1 align="middle"> Welcome to ProNetwork!!</h1>
<form action="ControllerServlet" method="post">
<div id="personal_details">
<h2>Personal Details: </h2><br>
  Full name: <input type="text" name="name" required><br><br>
  Mail ID: <input type="email" name="mailid" required><br><br>
  password: <input type="password" name="password" required><br><br>
</div>

<div id="education_details" >
<h2>Education Details: </h2><br>
  education year: <input type="text" name="year" id="year"><br><br>
  institution: <input type="text" name="institution" id="institution"><br><br>
  degree: <input type="text" name="degree" id="degree"><br><br>
  <input type="hidden" name="educationdetails" id="educationdetails">
  <input type="button" name="add" value="Add More" onClick="updateEducationDetails()">
</div>

<div id="employment_details" >
<h2>Employment Details: </h2><br>
  company: <input type="text" name="company" id="company"><br><br>
  start year: <input type="month" name="syear" id="syear" ><br><br>
  end year:   <input type="month" name="eyear" id="eyear" ><br><br>
  <input type="hidden" name="employmentdetails" id="employmentdetails">
  <input type="button" name="add1" value="Add More" onClick="updateEmploymentDetails()">
</div>

<div>
<h2>Skills:</h2>
	Skills: <input type="text" name="skills" id="skills"><br><br>
</div>
  <input type="hidden" name="action_details" value="newuseradd" ><br>  
  <input type="submit" value="Submit">
</form>
</body>



</html>