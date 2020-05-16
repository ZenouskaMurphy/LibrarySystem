package assignment1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.*;

@WebServlet("/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
    Statement stmt = null;
    Statement stmt2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    String username = "ee_user";
    String password = "ee_pass";
   
      
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	
	String UsernameField = req.getParameter("usernamefield"); 
	String PasswordField = req.getParameter("passwordfield"); 

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
	     rs = stmt.executeQuery("Select * from ILMemberDatabase");
	     		
 		while (rs.next()) {
 		 		String UsernameDatabase = rs.getString("username");	
 		     	String PasswordDatabase = rs.getString("password"); 
 		 		if (UsernameField.equals(UsernameDatabase) && PasswordField.equals(PasswordDatabase)) 
 		 		{ 
 		 			HttpSession session = req.getSession(); 
 		 			String MemberNames = rs.getString("FirstName") + " " + rs.getString("Surname");
 		 			session.setAttribute("MemberNames", MemberNames);
 		 			session.setAttribute("UsernameField",UsernameField);
 		 			session.setMaxInactiveInterval(10 * 60); // 10 Minutes
 		 			ServletContext servletContext = getServletContext();
 		 			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/MemberAccountServlet");
 		 			requestDispatcher.forward(req, res);
 		 		}	
 		 	}
 					String ErrorMessage1 = ("Invalid Username or Password Entered");
 					req.setAttribute("Error1", ErrorMessage1);
 					String ErrorMessage2 = ("Please Try Again!");
 					req.setAttribute("Error2", ErrorMessage2);
 					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/MemberLogin.jsp");
 		 			requestDispatcher.forward(req, res);
	}
 		
	catch (Exception e) {
		e.printStackTrace();
 		out.println("An error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
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