package in.sp.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@WebServlet("/Regisform")
public class RegisterForm extends HttpServlet {
	
	protected void dopost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();	
		String myname = req.getParameter("name1");
		String myemail = req.getParameter("email1");
		String mypass = req.getParameter("pass1");
		String mygender  = req.getParameter("gender1");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Register","root","Pass@123");
			PreparedStatement ps = con.prepareStatement("insert into register value(?,?,?,?,?)");
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypass);
			ps.setString(4, mygender);
			
			
			int i = ps.executeUpdate();
			 if(i > 0)
			 {
				 resp.setContentType("text/html");
				 out.print("<3 style = 'color:green'> User register successfull</3>");
				 
				 RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				 rd.include(req, resp);
			 }
			 else
			 {
				 resp.setContentType("text/html");
				 out.print("<3 style = 'color:green'> User register not successfull</3>");
				 
				 RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				 rd.include(req, resp);
			 }
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

}
