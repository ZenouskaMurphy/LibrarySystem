<html>
<head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%"; height="10%";  />
				<ul>
					<li> <a href = "/LibrarySystem/jsp/Catalogue.jsp" class = "Catalogue"> Catalogue </a> </li>
				</ul>
		</h1>
	</div>
	
		<link rel = "stylesheet" href = "/LibrarySystem/css/Member_StaffLogin.css">     <!--Link to external css sheet-->
	</head>
	
	<body>
		<div class = "Logo">
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="35%"; height="30%";  />
		</div>
			<form method =  "POST" action = "/LibrarySystem/MemberLoginServlet" name = "MemberLoginForm" >
				<div class = "Login">
					<table class = "Logintable">
						<h3> Member Login</h3>
							<tr>
								<td> Username: </td>
								<td> <input type = "text" name = "usernamefield" placeholder = "Username" required> </td>
							</tr>
							<tr>
								<td> Password: </td>
								<td> <input type = "password"  name = "passwordfield" placeholder = "Password" required> </td>
							</tr>
						</table>

						<input type = "submit" value = "Login" class = "button">
						<div class = "UnsuccessfulLogin">
							<p>${Error1} </p>
							<p>${Error2} </p>
							<p>${Timeout} </p>
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