<%@page language="java"%>

<html>
<body>
<form action="AccountServlet">
<fieldset>
	<legend> Login </legend>
	Email ID : <input type="text" name="std_email" />
	Password : <input type="text" name="std_password"/>
	<input type="submit" value= "Login"/> <br/> <a href="Recovery.jsp">Forgot Password !</a>
</fieldset>	
<fieldset>
	<legend> Join </legend>
	First Name : <input type="text" name="reg_fname"/><br/>
	Last Name : <input type="text" name="reg_lname"/><br/>
	Password : <input type="password" name="reg_password"/><br/>
	Re-Type Password : <input type="password" name="reg_re_password"/><br/>
	School : <input type="text" name="reg_school"/><br/>
	Class : <input type="text" name="reg_class"/><br/>
	Screen Name : <input type="text" name="reg_screen_name"/><br/>
	DOB : <input type="calendar" name="reg_dob"/><br/>
	State : <select name="state">
		<option>Uttar Pradesh</option>
	</select><br/>
	City : <select name="city">
		<option>Kanpur</option>
	</select><br/>
	Sex : <input type="radio" name="gender" value="Male"/> Male 
	<input type="radio" name="gender" value="Female"/> Female 
	<br/>
	<input type="submit" value= "Join!"/>
</fieldset>	

</form>

</body>
</html>
