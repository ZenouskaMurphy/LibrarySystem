package assignment1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.*;

	@WebServlet("/StaffLoginServlet")
	public class StaffLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
 	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    String username = "ee_user";
    String password = "ee_pass";
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	res.setContentType("text/html");
    	PrintWriter out = res.getWriter();
    	
    	String IDField = req.getParameter("idfield"); //User Field
    	String PasswordField = req.getParameter("passwordfield"); //User Field
      		         
	    	// Now we add our database code!
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
	     		rs = stmt.executeQuery("Select * from ILStaffDatabase");
	     		
 		while (rs.next()) {
 		 		String StaffIDDatabase = rs.getString("StaffID");
 		     	String PasswordDatabase = rs.getString("PASSWORD");
 		 		if (IDField.equals(StaffIDDatabase) && PasswordField.equals(PasswordDatabase)) 
 		 		{  
 		 			HttpSession session = req.getSession(); 
 		 			String StaffNames = rs.getString("FirstName") + " " + rs.getString("Surname");
 		 			session.setAttribute("StaffNames", StaffNames);
 		 			session.setMaxInactiveInterval(10 *60); // 10 minutes
 		 			res.sendRedirect("/LibrarySystem/jsp/StaffAccount.jsp");

 		 		}
 		 		
 		 		}
 					String ErrorMessage1 = ("Invalid Username or Password Entered");
 					req.setAttribute("Error1", ErrorMessage1);
 					String ErrorMessage2 = ("Please Try Again");
 					req.setAttribute("Error2", ErrorMessage2);
 					
 					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/StaffLogin.jsp");
 		 			requestDispatcher.forward(req, res);
	}
	    	catch (Exception e) {
	     		out.println("An error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
	    	}   
	     	
	    	finally {
	    		try	 {    
	    			if (rs != null) rs.close();
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
