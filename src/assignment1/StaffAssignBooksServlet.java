package assignment1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import oracle.jdbc.driver.*;

@WebServlet("/StaffAssignBooksServlet")
public class StaffAssignBooksServlet extends HttpServlet {
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
	
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String UserOptions = req.getParameter("UserOptions"); 	        
	String BookOptions = req.getParameter("BookOptions"); 			
	String BorrowDate = formatter.format(date);
	String ExpiryDate = req.getParameter("ExpiryDate");			
	
	try {
         		System.out.println("\nConnecting to the SSD Database......");
         		Class.forName("oracle.jdbc.driver.OracleDriver");
         		con = DriverManager.getConnection(JDBCUrl, username, password);
     	}
	
     	catch (Exception e) {
         		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
     	}   

	
	try {
		HttpSession session = req.getSession(false);
		if(session == null) {
			String Timeout = ("You have been signed out due to inactivity");
			req.setAttribute("Timeout", Timeout);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/StaffLogin.jsp");   
		    rd.forward(req, res);
		}
		
	     PreparedStatement pstmt = con.prepareStatement("Insert into ILBookKeeping (MEMBERNAME, PASTCURRENTRESERVATIONS, BORROWDATE, EXPIRYDATE) VALUES (?, ?, ?, ?)");
	     pstmt.clearParameters();
	     pstmt.setString(1, UserOptions);
	     pstmt.setString(2, BookOptions);
	     pstmt.setString(3, BorrowDate);
	     pstmt.setString(4, ExpiryDate);
	     pstmt.executeUpdate();
	     
	     String SuccessMessage = ("You have Successfully Assigned a Book!");
		 req.setAttribute("SuccessMessage", SuccessMessage);				  
	     RequestDispatcher rd = req.getRequestDispatcher("/jsp/StaffAccount.jsp");   
	     rd.forward(req, res);
	}
 
	catch (Exception e) {
 		String ErrorMessage1 = ("There was an error");
 		req.setAttribute("ErrorMessage1", ErrorMessage1);
 		String ErrorMessage2 = ("Please Try Again");
 		req.setAttribute("ErrorMessage2", ErrorMessage2);
 		RequestDispatcher rd = req.getRequestDispatcher("/jsp/StaffAssignBooks.jsp");   
	    rd.forward(req, res);
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