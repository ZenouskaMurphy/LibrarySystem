<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>   

<html>
   <head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%" height="10%"  />
				<ul>
					<li> <a href = "/LibrarySystem/jsp/Catalogue.jsp"> Catalogue </a> </li>
					<li> <a href = "/LibrarySystem/LogoutServlet"> Logout </a> </li>
				</ul>
		</h1>
	</div>
	
		<link rel = "stylesheet" href = "/LibrarySystem/css/MemberAccount.css">     <!--Link to external css sheet-->
	</head>
	
	<body>
	<div class = "MemberIntroduction">
		<h2> My Library Member Account </h2>
			<div class = "WelcomeInformation">
				<p> Welcome Back: ${MemberNames} </p>
			</div>
			<div class = "WelcomeInformation">
				<p> Current Time: <%= (new java.util.Date()) %> </p>
			</div>
			<div>
	</div>
	
	<table class = "Reservations">
			<tr>
				<th> Past/Current Reservations </th>
				<th> Borrow Date </th>
				<th> Expiry Date </th>
			</tr>
			 
			<c:forEach items="${MemberAccount}" var="info" >
			<tr> 
 				<td><c:out value = "${info.pastCurrentReservations}"/></td> 
 				<td><c:out value = "${info.borrowDate}"/> </td> 
 				<td><c:out value = "${info.expiryDate}"/></td> 
 			</tr>	
			</c:forEach>
		</table>
	
	
		<div class = "footer" >
			<pre> 
  Imaginary Library, 988 Jim Rosa Lane, Dublin 11			 T: (353) 1 7662881				 Email: imaginary_library@yahoo.co.uk
			</pre>
		</div>
	
	</body>
</html>