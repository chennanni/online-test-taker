<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.learn.data.*" %>
<%@ page import="java.util.List" %>
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
	
	<script>
		function checkAnswer() {
			if (${answer} != $('input[name=selectedOption]:checked').val()){
				alert("Wrong Answer!");
			} else {
				alert("Right Answer!");
			}
		}
	</script>
</head>
<body>
<%
	Question q = null;
	List<Option> options = null;
	if (request.getAttribute("question") == null) {
%>		
		<jsp:forward page="testEnd.jsp" />
<%
	} else {
		q = (Question)request.getAttribute("question");
		@SuppressWarnings("unchecked") List<Option> copyOfOptions = (List<Option>) request.getAttribute("options");
		options = copyOfOptions;
		session.setAttribute("qId",q.getNextQuestionId());
	}
%>

	<h1>Testing</h1>
	<form role="form">
	<div class="form-group" id="Questions">
		<div class="well">
			<%=q.getDescription() %>
		</div>
		
		<div class="list-group-item">
<%
	for (int i=0; i<options.size(); i++) {
%>
			<div class="radio">
			  <label>
				  <input type="radio" name="selectedOption" id="selectedOption" value="<%=options.get(i).getDescription()%>">
				  Answer: <%=options.get(i).getDescription()%>
			  </label>
			</div>
<%
	}
%>
		</div>
	</div>
	</form>
		
	<button class="btn btn-default" onclick="checkAnswer()">Check Answer</button>
	
	<form role="form" method="post" action="NextQuestion">
		<button class="btn btn-default" type="submit">Next</button>
	</form>
</body>
</html>