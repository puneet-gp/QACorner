<%@page language="java"%>
<!DOCTYPE html>
<html>
<body>
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="js/qacorner.js"></script>
<script type="text/javascript">
	var gloabalVar;
	$(function(){
		gloabalVar = new QACorner();
	});
</script>
<form method="POST" action="account">
<fieldset>
	<legend> Login </legend>
	<input type="text" placeholder="Email ID" name="std_email" required="required"/>
	<input type="password" placeholder="Password" name="std_password" required="required"/>
	<aside style="color: red; float: right;">${login_error }</aside>
	<input type="submit" name="login" value= "Login"/> <br/> <a href="passwordrecovery.jsp">Forgot Password !</a>
</fieldset>	
</form>
<form method="POST" action="account">
<fieldset>
	<legend> Join </legend>
	 <input placeholder="First Name" required="required" type="text" name="reg_fname"/><br/><br/>
	 <input type="text" required="required" placeholder="Last Name" name="reg_lname"/><br/><br/>
	<input type="email" id="reg_email_id" required="required" placeholder="Email ID" name="reg_email_id"/><aside style="color: red; float: right;">${email_id_error}</aside><br/><br/>	
	<input type="password" required="required" placeholder="Password" name="reg_password"/><br/><br/>
	<input type="password" required="required" placeholder="Confirm Password" name="reg_re_password"/><br/><br/>
	<input type="submit" name="join" value= "Join!"/>
</fieldset>	

</form>

</body>
</html>