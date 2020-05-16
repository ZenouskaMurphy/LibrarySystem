<%@ page import="java.util.*" %>  
<html>
	<head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%" height="10%" />
				<ul>
					<li> <a href = "/LibrarySystem/LogoutServlet"> Logout </a> </li>
				</ul>
		</h1>
	</div>
		<link rel = "stylesheet" href = "/LibrarySystem/css/StaffAccount.css">     <!--Link to external css sheet-->
	</head>

<body>
	<div class = "StaffIntroduction">
		<h2> My Library Staff Account </h2>
			<div class = "WelcomeMessage">
						<p> Welcome Back: ${StaffNames} </p>
			</div>
			<div class = "WelcomeMessage">
					<p> Current Time: <%= (new java.util.Date())%> </p>
			</div>
	</div>
	
	<form method = "POST" action = "/LibrarySystem/StaffAccountServlet" name = "StaffAccountForm" >
		<div class = "Options">
			<table class = "StaffAccountTable">
				<tr>
					<td><label for = "Label"> What would you like to do ? </label></td>
					<td><select name = "StaffOptions" required>
					<option disabled selected value> --Select an Option -- </option>
					<option value = "AssignBooks"> Assign Books To Users </option>
					<option value = "ExpiryDates"> Change Expiry Dates for Users </option>
					</select></td>
				</tr>
			</table>
					<input type = "submit" value = "Submit" class = "button">
					<div class = "SuccessMessage">
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