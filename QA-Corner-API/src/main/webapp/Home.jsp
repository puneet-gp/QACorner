<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome : ${requestScope.student_info.screenName} </title>
</head>
<body>
	First Name : <b>${requestScope.student_info.firstName}</b><br/><br/>
	Last Name : <b>${requestScope.student_info.lastName }</b><br/><br/>
	Password : <b>${requestScope.student_info.password }</b><br/><br/>
	School : <b>${requestScope.student_info.schoolName }</b><br/><br/>
	Class :<b>${requestScope.student_info.className }</b><br/><br/>
	ScreenName : <b>${requestScope.student_info.screenName }</b><br/><br/>
	Birth Day : <b>${requestScope.student_info.dob }</b><br/><br/>
	You are : <b>${requestScope.student_info.gender }</b><br/><br/>
	Joined on : <b>${requestScope.student_info.registrationDate }</b><br/><br/>
	City : <b>${requestScope.student_info.city }</b><br/><br/>
	State : <b>${requestScope.student_info.state }</b><br/><br/>
	Student ID : <b>${requestScope.student_info.studentId }</b><br/><br/>
	Email ID : <b>${requestScope.student_info.emailId }</b><br/><br/>
</body>
</html>