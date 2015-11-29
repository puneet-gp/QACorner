<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password | Recovery</title>
</head>
<body>
	<form action="password_recovery" method="POST">
	<input type="hidden" name="email_type" value="password_recovery"/>
	Email Id : <input type="email" name="email_to"/> <input type="submit" value= "Send me Password" />
	
	</form>
</body>
</html>