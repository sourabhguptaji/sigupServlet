

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out=response.getWriter();

		HttpSession s=request.getSession();
		String id=(String)s.getAttribute("ukey");

		
	String newpass=request.getParameter("np");
	String newname=request.getParameter("nn");
	String a=request.getParameter("na");
	int newage=Integer.parseInt(a);
	String newcity=request.getParameter("nc");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");	
			PreparedStatement ps=con.prepareStatement("update user set password=?, name=?, age=? , city=? where uid=?");
			ps.setString(1, newpass);
			ps.setString(2, newname);
			ps.setInt(3, newage);
			ps.setString(4, newcity);
			ps.setString(5, id);
			
			int x=0;
			x=ps.executeUpdate();
			if(x==0)
			{
				out.println("Data Not Updated");
			}
			else
			{
					response.sendRedirect("home.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	

}







