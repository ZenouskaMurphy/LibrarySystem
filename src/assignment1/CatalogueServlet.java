package assignment1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.*;


@WebServlet("/CatalogueServlet")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    String username = "ee_user";
    String password = "ee_pass";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
   
    String Searchbar = req.getParameter("search");
    
 	try {
          		System.out.println("\nConnecting to the SSD Database......");
          		Class.forName("oracle.jdbc.driver.OracleDriver");
          		con = DriverManager.getConnection(JDBCUrl, username, password);
      	}
 	
      	catch (Exception e) {
          		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
      	}   
 	
 	try {
 	    stmt = con.createStatement();
 		rs = stmt.executeQuery("Select * from ILCatalogue where Author like '%"+Searchbar+"%' or Title like '%"+Searchbar+"%'");
 		List<CatalogueInfo> books = new ArrayList<CatalogueInfo>();
 		
		if (rs.next()== true) {
			    System.out.print("I found a record");
				CatalogueInfo c = new CatalogueInfo();
				c.setAuthor(rs.getString(1));
				c.setTitle(rs.getString(2));
				c.setPublishyear(rs.getInt(3));
				c.setBookid(rs.getInt(4));
				books.add(c);
				req.setAttribute("Catalogue", books);   
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/Catalogue.jsp");   
				rd.forward(req, res);
		}	
 		
 		if (rs.next() == false) {
		 			System.out.println("I didnt find a record");
		 			String ErrorMessage1 = ("Record Not Found");
		 			req.setAttribute("Error1", ErrorMessage1);
		 			String ErrorMessage2 = ("Please Try Something Else!");
		 			req.setAttribute("Error2", ErrorMessage2);
		 			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/Catalogue.jsp");
		 			requestDispatcher.forward(req, res);
		 }	
 	}
  
 		catch (Exception e) {
 	 		out.println("An error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
 	 		e.printStackTrace();
 		}   
 		
 		finally {
 			try	 {    
 				if (rs != null) 
 				rs.close();
 				if (stmt != null) stmt.close();
 				if (con != null) con.close();
 			}
 	 
 			catch (Exception ex) {
 				out.println("An error occurred while closing down connection/statement"); 
 			}
 		}
          
         out.close();
       }     
    
  }
