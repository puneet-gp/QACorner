<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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