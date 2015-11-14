<%@page language="java"%>

<html>
<body>
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	$(function(){
		var stateDropDown = $("#state")[0];
		var cityDropDown = $("#city")[0];
		var regEmailId = $("#reg_email_id")[0];
		var regScreenName = $("#reg_screen_name")[0];
		$.ajax({
			url: "baseservlet?is=true",
			method: "GET",
			success: function(data, textStatus, jqXHR) {
				if(data instanceof Array) {
					for(st in data) {
						stateDropDown.innerHTML+= "<option value='"+ data[st] +"'>" + data[st] + "</option>";
						if(st === '0') {
							$.ajax({
								url : "baseservlet?s=" + data[st],
								method : "GET",
								success: function(data, textStatus, jqXHR) {
									if(data instanceof Array) {
										cityDropDown.innerHTML = "";
										for(st in data) {
											cityDropDown.innerHTML+= "<option value='"+ data[st] +"'>" + data[st] + "</option>";
										}
									}			
								}
							});						
						}
					}
				}
				else {
					console.error("Couldn't populate state dropdown !!!"); 			
				}		
			}
		});
		$(stateDropDown).on("change", function(e) {
			console.log("change");
			var selectedValue = $(stateDropDown).val();
			$.ajax({
				url: "baseservlet?s=" + selectedValue,
				method: "GET",
				success: function(data, textStatus, jqXHR) {
					if(data instanceof Array) {
						cityDropDown.innerHTML = "";
						for(st in data) {
							cityDropDown.innerHTML+= "<option value='"+ data[st] +"'>" + data[st] + "</option>";
						}	
					}			
				}
			});
		});
		$(regEmailId).on("blur", function(e) {
			var emailId = $(regEmailId).val();
			if(emailId !== '') {
				$.ajax({
					url: "baseservlet?ce=" + emailId,
					method: "GET",
					success: function(data, textStatus, jqXHR) {
						console.log("Email id does not exist : " + emailId);			
					},
					failure: function(data, c, e){
						console.log("Email Id exist : " + emailId);
					}	
				});
			}
		});

		$(regScreenName).on("blur", function(e){
			var screenName = $(regScreenName).val();
			if(screenName !== '') {
				$.ajax({
					url: "baseservlet?csn="	+ screenName,
					method: "GET",
					success: function(data, textStatus, jqXHR) {
						console.log("Screen name does not exist : " + screenName);
					},
					error: function(data) {
						console.log("Screen name exist : " + screenName);
					}
				});		
			}	
		});	
	});
</script>
<form method="POST" action="account">
<fieldset>
	<legend> Login </legend>
	Email ID : <input type="text" name="std_email" required="required"/>
	Password : <input type="password" name="std_password" required="required"/>
	<input type="submit" name="login" value= "Login"/> <br/> <a href="Recovery.jsp">Forgot Password !</a>
</fieldset>	
<fieldset>
	<legend> Join </legend>
	First Name : <input type="text" name="reg_fname"/><br/><br/>
	Last Name : <input type="text" name="reg_lname"/><br/><br/>
	Email Id : <input type="text" id="reg_email_id" name="reg_email_id"/><br/><br/>	
	Password : <input type="password" name="reg_password"/><br/>
	Re-Type Password : <input type="password" name="reg_re_password"/><br/>
	School : <input type="text" name="reg_school"/><br/><br/>
	Class : <input type="text" name="reg_class"/><br/><br/>
	Screen Name : <input type="text" id="reg_screen_name" name="reg_screen_name"/><br/><br/>
	DOB : <input type="calendar" name="reg_dob"/><br/><br/>
	State : <select id="state" name="state">
		
	</select><br/>
	City : <select id="city" name="city">
		
	</select><br/>
	Sex : <input type="radio" name="gender" value="Male"/> Male 
	<input type="radio" name="gender" value="Female"/> Female 
	<br/>
	<input type="submit" name="join" value= "Join!"/>
</fieldset>	

</form>

</body>
</html>