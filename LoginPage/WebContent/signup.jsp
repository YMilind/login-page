<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creating an account</title>
</head>
<body>

<form action="signup_result" method="post">
	Login:<br>
	<input type="text" name="username">
	<br>
	Password:<br>
	<input type="password" name="password">
	<br>
	Confirm password:<br>
	<input type="password" name="password2">
	<br>
	<input type="submit" value="Sign up">
</form>
<br><br>
<a href='index.jsp'>Back</a>

</body>
</html>