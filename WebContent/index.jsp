<!DOCTYPE html>
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

		<script>
			function showStatus() {
				<%
				if (request.getAttribute("message") != null){
				%>
					$("#status").show();
				<%
				}
				%>
			}
		</script>
	</head>
	<body>
		
		<!-- TODO: add js to validate the input and two passwords match -->
		<div class="container">
			<h1>Welcome to Test Center!</h1>
		
			<form role="form" method="post" action="Signup">
				<h2>Sign Up</h2>
				<div class="form-group">
					<label for="email">Email address:</label>
    				<input type="email" class="form-control" name="email">
				</div>
				<div class="form-group">
					<label for=password>Password:</label>
    				<input type="password" class="form-control" name="password">
				</div>
				<div class="form-group">
					<label for=passwordAgain>Password Again:</label>
    				<input type="password" class="form-control" name="passwordAgain">
				</div>
				<button type="submit" class="btn btn-primary" onclick="showStatus()">Submit</button>
			</form>
			
			<form role="form" method="post" action="Login">
				<h2>Login</h2>
				<div class="form-group">
					<label for="email">Email address:</label>
    				<input type="email" class="form-control" name="email">
				</div>
				<div class="form-group">
					<label for=password>Password:</label>
    				<input type="password" class="form-control" name="password">
				</div>
				<button type="submit" class="btn btn-primary" onclick="showStatus()">Submit</button>		
			</form>

			<br/>
			<div id="status" class="alert alert-danger" style="display:none;">${message}</div>
			<br/>
			<br/>
			
			<form method="post" action="mainPage.jsp">
				<button type="submit" class="btn btn-info">I'm a Visitor</button>
			</form>
			
		</div>
		
	</body>
</html>