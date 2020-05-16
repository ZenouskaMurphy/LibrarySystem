<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<div class = "header">
		<h1> Imaginary Library
			<img src = "/LibrarySystem/images/ImaginaryLibraryLogo.png" alt = "logo" width="5%"; height="10%";  />
			<ul>
					<li> <a href = "/LibrarySystem/jsp/LibraryStartPage.jsp"> Home</a> </li>
			</ul>
		</h1>
	</div>
			<link rel = "stylesheet" href="/LibrarySystem/css/Catalogue.css">
</head>


<body>
		<h2> Welcome to the Imaginary Library Catalogue</h2>
			<form method =  "GET" action = "/LibrarySystem/CatalogueServlet" name = "CatalogueForm">	
				<div class = "SearchCatalogue">
					<h3> You can search for books below by Author and/or title </h3>
					<h4> Please Use Capitals At Beginning of Search</h4>
						<table>
							<tr>
								<td> <input type = "text" name = "search" placeholder = "Search..."> </td>
								<td> <input type = "submit" value = "GO" class = "button"></td>
							</tr>
						</table>
				</div>
			</form>
			
			<div class = "NoRecord">
				<p>${Error1} </p>
				<p>${Error2} </p>
			</div>
			
			<table class = "CatalogueTable">
			<tr>
				<th> Author </th>
				<th> Title </th>
				<th> Publish Year </th>
				<th> BOOK ID </th>
			</tr>
				<c:forEach items="${Catalogue}" var="info" >
				<tr> 
 					<td><c:out value = "${info.author}"/></td> 
 					<td><c:out value = "${info.title}"/> </td> 
 					<td><c:out value = "${info.publishyear}"/></td> 
 					<td><c:out value = "${info.bookid}"/></td>
 				</tr>	
			</c:forEach>
			<tr>
			</tr>
			</table>


		<div class = "footer" >
					<pre> 
  Imaginary Library, 988 Jim Rosa Lane, Dublin 11			 T: (353) 1 7662881				 Email: imaginary_library@yahoo.co.uk
					</pre>
		</div>
</body>
</html>
		