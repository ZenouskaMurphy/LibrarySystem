<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%" height="10%"  />
		</h1>
	</div>
		<link rel = "stylesheet" href = "/LibrarySystem/css/StaffChangeExpiryDates.css">     <!--Link to external css sheet-->
	</head>
	
<body>
		<form method =  "POST" action = "/LibrarySystem/StaffChangeExpiryDatesServlet" name = "StaffAssignBooksForm" >	
			<div class = "Table">
				<table class = "ChangingExpiryDates">
					<tr>
						<td><label for = "Users"> Select User to Query: </label> </td>
						<td><select name = "UserOptions" required>
							<option disabled selected value> --Select an Option -- </option>
							<option value = "generic1"> generic1 </option>
							<option value = "basic2"> basic2 </option>
							<option value = "common3"> common3 </option>
							<option value = "typical4"> typical4 </option>
							<option value = "simple5"> simple5 </option>
							<option value = "general6"> general6 </option>	
							</select>
					<tr>
						<td><label for = "Label"> Select Book to Query: </label> </td>
						<td><select name = "BookOptions" required>
							<option disabled selected value> --Select an Option -- </option>
							<option value = "Introduction to Electronics"> Introduction to Electronics </option>
							<option value = "Control Systems Analysis"> Control Systems Analysis </option>
							<option value = "Numerical Problem Solving"> Numerical Problem Solving </option>
							<option value = "Data Communications"> Data Communications </option>
							<option value = "Electromagnetism"> Electromagnetism </option>
							<option value = "Web Application Development"> Web Application Development </option>
							<option value = "Internet of Things"> Internet of Things </option>
							<option value = "Bioelectronics"> Bioelectronics </option>
							<option value = "Digital Filters"> Digital Filters </option>
							</select> </td>
					</tr>
					<tr>
						<td><label for = "BorrowDate"> Select Borrow Date to Query: </label> </td>
						<td> <input type = "text" name = "BorrowDate" placeholder = "dd/mm/yyyy" pattern = "^(?:(?:31(\/)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\1|(?:(?:29|30)(\/)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/)(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" required> </td>		
					</tr>
					<tr>
						<td><label for = " Current ExpiryDate"> Select Current ExpiryDate to Query: </label> </td>
						<td> <input type = "text" name = "OldExpiryDate" placeholder = "dd/mm/yyyy" pattern = "^(?:(?:31(\/)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\1|(?:(?:29|30)(\/)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/)(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" required> </td>		
					</tr>
					<tr>
						<td> New Expiry Date: </td>
						<td> <input type = "text" name = "NewExpiryDate" placeholder = "dd/mm/yyyy" pattern = "^(?:(?:31(\/)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\1|(?:(?:29|30)(\/)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/)(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" required> </td>	
					</tr>
				</table>
						<input type = "submit" value = "Change Expiry Date" class = "button"> 
						<div class = "UnsuccessfulorSuccessfulChanging">
							<p>${ErrorMessage1}</p>
							<p>${ErrorMessage2}</p>
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