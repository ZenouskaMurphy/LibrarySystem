package assignment1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NewMemberServlet")
public class NewMemberServlet extends HttpServlet {
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
	
	String FirstName = req.getParameter("FirstName");
	String Surname = req.getParameter("Surname");
	String Username = req.getParameter("DesiredName");
	String Password = req.getParameter("password");
	
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
	 		 		String UsernameDatabase = rs.getString("USERNAME");	 
	 		 		if (Username.equals(UsernameDatabase))
	 		 			{
	 		 			String ErrorMessage1 = ("This Username is Taken");
	 					req.setAttribute("Error1", ErrorMessage1);
	 					String ErrorMessage2 = ("Please Pick Another One!");
	 					req.setAttribute("Error2", ErrorMessage2);
	 					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/NewMember.jsp");
	 		 			requestDispatcher.forward(req, res);
	 		 			}
	 			}
		}
	
	catch (Exception e) {
		e.printStackTrace();
	}

	try {
	     PreparedStatement pstmt = con.prepareStatement("Insert into ILMemberDatabase (FIRSTNAME, SURNAME, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)");
	     pstmt.clearParameters();
	     pstmt.setString(1, FirstName);
	     pstmt.setString(2, Surname);
	     pstmt.setString(3, Username);
	     pstmt.setString(4, Password);
	     pstmt.executeUpdate();
	     
	     String SuccessMessage = ("You have Successfully Registered!");
		 req.setAttribute("SuccessMessage", SuccessMessage);				  
	     RequestDispatcher rd = req.getRequestDispatcher("/jsp/NewMember.jsp");   
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


