<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome | Profile </title>
</head>
<body>
 <aside style="float: right"><jsp:include page="/logout"></jsp:include></aside>
 
 <h3>Hi ${sessionScope.logged_in_user.firstName}&nbsp;${sessionScope.logged_in_user.lastName}, Please take a moment to fill your personal details : </h3>
 
 <fieldset>
 	<legend>Personal Details : </legend>
 	<input type="text" value="${sessionScope.logged_in_user.firstName}" name="profile_fname" placeholder="First Name"/><br/><br/>
 	<input type="text" value="${sessionScope.logged_in_user.lastName}" name="profile_lname" placeholder="Last Name"/><br/><br/>
 	<input type="text" name="school_name" placeholder="School"/><br/><br/>
 	<input type="text" name="class_name" placeholder="Class"/><br/><br/>
 	<input type="text" name="screen_name" placeholder="Screen Name"/><br/><br/>
 	<input type="text" name="" placeholder="Email" value="${sessionScope.logged_in_user.emailId}" readonly="readonly"/><br/><br/>
 	<!-- TODO - Change it to calendar -->
 	<input type="text" name="dob" placeholder="Birthday"/><br/><br/>
 	<select name="gender">
 		<option value="Male">Male</option>
 		<option value="Female">Female</option>
 	</select>
 	<select name="state">
 		
 	</select>
 </fieldset>
</body>
</html>