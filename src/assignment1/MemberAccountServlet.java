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

@WebServlet("/MemberAccountServlet")
public class MemberAccountServlet extends HttpServlet {
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
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/MemberLogin.jsp");   
				    rd.forward(req, res);
				}
				String MemberNames = (String) session.getAttribute("MemberNames");
				String UsernameField = (String) session.getAttribute("UsernameField");
	     	    stmt = con.createStatement();
	     		rs = stmt.executeQuery("Select * from ILBookKeeping where MEMBERNAME='"+UsernameField+"'");
	     		List<MemberAccountInfo> members = new ArrayList<MemberAccountInfo>();
	     		
	     		while (rs.next()) {
	     				MemberAccountInfo m = new MemberAccountInfo();
	     				m.setMembername(rs.getString(1));
	     				m.setPastCurrentReservations(rs.getString(2));
	     				m.setBorrowDate(rs.getString(3));
	     				m.setExpiryDate(rs.getString(4));
	     				members.add(m);	
	     		}
	     		  req.setAttribute("MemberAccount", members);   
	     	      RequestDispatcher rd = req.getRequestDispatcher("/jsp/MemberAccount.jsp");   
	     	      rd.forward(req, res);
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