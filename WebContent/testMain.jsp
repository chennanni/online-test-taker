<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="org.learn.data.*" %>
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

	<h1>This is a Test Page!</h1>
	<h3>
<%
org.learn.data.Test test = (org.learn.data.Test) request.getAttribute("test");
session.setAttribute("test",test);
%>
		Test Name: <%=test.getName() %>
	</h3>
	
	<form role="form" method="post" action="BeginTest">
		<button class="btn btn-info" type="submit">Begin</button>
	</form>
	
</body>
</html>