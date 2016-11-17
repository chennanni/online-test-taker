<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Lets Test</title>
		<!-- jQuery -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<%
	if (request.getAttribute("email") == null) request.setAttribute("email", "Visitor");
%>

	<h1>This is Main Page...</h1>
	<h3>
		Welcome, ${email}
	</h3>
	
	<div>
		<form role="form" method="post" action="FindTest">
			<button class="btn btn-primary btn-lg" type="submit" name="testType" value="Java Test">Java Test</button>
			<button class="btn btn-primary btn-lg" type="submit" name="testType" value="HTML Test">HTML Test</button>
			<button class="btn btn-primary btn-lg" type="submit" name="testType" value="Spring Test">Spring Test</button>
		</form>
	</div>
	
</body>
</html>