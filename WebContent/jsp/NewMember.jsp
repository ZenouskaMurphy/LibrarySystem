<html>
	<head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%"; height="10%";  />
				<ul>
					<li> <a href = "/LibrarySystem/jsp/LibraryStartPage.jsp"> Home </a> </li>
					<li> <a href = "/LibrarySystem/jsp/Catalogue.jsp" >  Catalogue </a> </li>
					<li> <a href = "/LibrarySystem/jsp/MemberLogin.jsp" >  Member Login </a> </li>
				</ul>
		</h1>
	</div>
 	
	<link rel = "stylesheet" href="/LibrarySystem/css/NewMember.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
	
	<body>
		<form method = "POST" action = "/LibrarySystem/NewMemberServlet" name = "RegisterForm" >
			<div class = "Register">
				<table class = "Form">
					<h2> Register Form</h2>
						<tr>
						<td> First Name: </td>
						<td><input type = "text" name = "FirstName" placeholder = "First Name" required> </td>
						</tr>
						<tr>
						<td> Surname: </td>
						<td><input type = "text" name = "Surname" placeholder = "Second Name" required> </td>
						</tr>
						<tr>
						<td> New Username: </td>
						<td><input type = "text" name = "DesiredName" placeholder = "Username" required> </td>
						</tr>
						<tr>
						<td> New Password: </td>
						<td><input type = "password" name = "password" id = "password" placeholder = "Password" required /> </td>
						</tr>
					</table>
						<span id = "passstrength"></span>
					
					<script>
					$('#password').keyup(function(e) {
   					var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
    				var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
    				var enoughRegex = new RegExp("(?=.{6,}).*", "g");
    				if (false == enoughRegex.test($(this).val())) {
           				$('#passstrength').html('More Characters Required :(');
    			  } else if (strongRegex.test($(this).val())) {
           				$('#passstrength').className = 'Ok';
            			$('#passstrength').html('Strong :D');
    			} else if (mediumRegex.test($(this).val())) {
           				$('#passstrength').className = 'alert';
           			 	$('#passstrength').html('Medium :)');
    			} else {
           			 	$('#passstrength').className = 'error';
           		 		$('#passstrength').html('Weak :/');
    			}
   				 return true;
		});	
					</script>
						<input type = "submit" value = "Register Now" class = "button">
						<div class = "UnsuccesfuRegister">
							<p>${Error1} </p>
							<p>${Error2} </p>
							<p>${SuccessMessage}</p>
						</div>
			</div>
		</form>
	
	<div class = "footer" >
		<pre> 
  Imaginary Library, 988 Jim Rosa Lane, Dublin 11			 T: (353) 1 7662881				 Email: imaginary_library@yahoo.co.uk
		</pre>
	</div>			 
				
	</body>
</html>