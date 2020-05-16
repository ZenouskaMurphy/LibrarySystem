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
import oracle.jdbc.driver.*;
import java.util.*;

@WebServlet("/StaffChangeExpiryDatesServlet")
public class StaffChangeExpiryDatesServlet extends HttpServlet {
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
	
	 String UserOptions = req.getParameter("UserOptions"); 	        
	 String BookOptions = req.getParameter("BookOptions"); 			
	 String BorrowDate = req.getParameter("BorrowDate");		
	 String OldExpiryDate = req.getParameter("OldExpiryDate");			
	 String NewExpiryDate = req.getParameter("NewExpiryDate");
	
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
		 stmt = con.createStatement();
	     rs = stmt.executeQuery("Select * from ILBookKeeping");
	     
	     while(rs.next()) {
	    	 	String UsernameDatabase = rs.getString("MEMBERNAME");	
	    	 	String PastCurrentReservationsDatabase = rs.getString("PASTCURRENTRESERVATIONS"); 
	    	 	String BorrowDateDatabase = rs.getString("BORROWDATE");
	    	 	String ExpiryDateDatabase = rs.getString("EXPIRYDATE");
	 		if (UserOptions.equals(UsernameDatabase) && BookOptions.equals(PastCurrentReservationsDatabase) && BorrowDate.equals(BorrowDateDatabase) && OldExpiryDate.equals(ExpiryDateDatabase)) 
	 		{ 
	 			PreparedStatement pstmt = con.prepareStatement("Update ILBookKeeping SET EXPIRYDATE = '"+NewExpiryDate+"' WHERE MEMBERNAME = '"+UserOptions+"' and  PASTCURRENTRESERVATIONS = '"+BookOptions+"' and BORROWDATE = '"+BorrowDate+"' and EXPIRYDATE = '"+OldExpiryDate+"'");
	 			pstmt.executeUpdate();
	     	
	 			String SuccessMessage = ("You have Successfully Changed An Expiry Date!");
	 			req.setAttribute("SuccessMessage", SuccessMessage);
	 			RequestDispatcher rd = req.getRequestDispatcher("/jsp/StaffAccount.jsp");   
	 			rd.forward(req, res);
	 		}
	     }
	     	String ErrorMessage1 = ("Record Not Found");
	 		req.setAttribute("ErrorMessage1", ErrorMessage1);
	 		String ErrorMessage2 = ("Please Try Again!");
	 		req.setAttribute("ErrorMessage2", ErrorMessage2);
	 		RequestDispatcher rd = req.getRequestDispatcher("/jsp/StaffChangeExpiryDates.jsp");   
		    rd.forward(req, res);
	}
 
	catch (Exception e) {
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

