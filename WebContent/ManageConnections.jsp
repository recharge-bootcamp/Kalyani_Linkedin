<html>
<head>
Connections
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> </link>
<script>
	function existing() {
		document.getElementById("action_details").value="existing_conn";
	}
	function recommendations() {
		document.getElementById("action_details").value="recommend_conn";
	}
	function invitations() {
		document.getElementById("action_details").value="invitation_conn";
	}
</script>
</head>
<body>
<form>
    <input type="hidden" name="id" id="id" value=<%= session.getAttribute("id") %> >
    <!-- <input type="hidden" name="id" id="id" value=9> -->
    <input type="hidden" id="action_details" name="action_details">
    <input type="submit" value="Existing" onClick="existing()" formaction="ControllerServlet" formmethod="post">
    <input type="submit" value="Recommendations" onClick="recommendations()" formaction="ControllerServlet" formmethod="post">
    <input type="submit" value="Invitations" onClick="invitations()" formaction="ControllerServlet" formmethod="post">
</form> 
</body>

</html>