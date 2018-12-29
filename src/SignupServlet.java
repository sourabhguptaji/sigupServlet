

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

@WebServlet("/sup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out=response.getWriter();

	String u=request.getParameter("uid");
	String p=request.getParameter("pass");
	String n=request.getParameter("nm");
	String a=request.getParameter("ag");
	int age=Integer.parseInt(a);
	String c=request.getParameter("ct");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");	
			PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?,?,?)");
			ps.setString(1, u);
			ps.setString(2, p);
			ps.setString(3, n);
			ps.setInt(4, age);
			ps.setString(5, c);
			int x=0;
			x=ps.executeUpdate();
			if(x==0)
			{
				out.println("Data Not Inserted");
			}
			else
			{
					response.sendRedirect("index.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	

}







