

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logmap")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String u=request.getParameter("uid");
		String p=request.getParameter("pass");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			PreparedStatement ps=con.prepareStatement("select * from user where uid=? and password=?");
			ps.setString(1, u);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				HttpSession s=request.getSession();
				s.setAttribute("ukey", u);
				//s.setMaxInactiveInterval(10);
				
				
				response.sendRedirect("home.html");
			}
			else
			{
				response.sendRedirect("login.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}










