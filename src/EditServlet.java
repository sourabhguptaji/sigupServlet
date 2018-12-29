

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/es")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		RequestDispatcher rd=request.getRequestDispatcher("home.html");
		rd.include(request, response);

		HttpSession s=request.getSession();
		String id=(String)s.getAttribute("ukey");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
			PreparedStatement ps=con.prepareStatement("select * from user where uid=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				String password=rs.getString(2);
				String name=rs.getString(3);
				int age=rs.getInt(4);
				String city=rs.getString(5);
				
				out.println("<form action=update>");
				out.println("<table align=center border=1>");
				out.println("<tr>");
					out.println("<td>ID</td>");
					out.println("<td>"+id+"</td>");
				out.println("</tr>");

				out.println("<tr>");
					out.println("<td>PASSWORD</td>");
					out.println("<td><input type=text name=np value="+password+"></td>");
				out.println("</tr>");

				out.println("<tr>");
					out.println("<td>NAME</td>");
					out.println("<td><input type=text name=nn value="+name+"></td>");
				out.println("</tr>");

			out.println("<tr>");
				out.println("<td>AGE</td>");
				out.println("<td><input type=text name=na value="+age+"></td>");
			out.println("</tr>");

			out.println("<tr>");
				out.println("<td>CITY</td>");
				out.println("<td><input type=text name=nc value="+city+"></td>");
			out.println("</tr>");

				
			out.println("<tr>");
				out.println("<td></td>");
				out.println("<td><input type=submit value=SAVE></td>");
			out.println("</tr>");

				out.println("</table>");
				out.println("</form>");
				
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







