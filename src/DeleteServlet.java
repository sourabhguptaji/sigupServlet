

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ds")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s=request.getSession();
		String id=(String)s.getAttribute("ukey");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");	
			PreparedStatement ps=con.prepareStatement("delete from user where uid=?");
			ps.setString(1, id);
			int x=0;
			x=ps.executeUpdate();
			if(x==0)
			{
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









