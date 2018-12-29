package com.shoppingcart.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/vc")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		int sum=0;
		HttpSession s=request.getSession();
		ArrayList ar= null;

		ar=(ArrayList)s.getAttribute("items");

		RequestDispatcher rd=request.getRequestDispatcher("home.html");
		rd.include(request, response);

		if(ar==null)
		{
			response.sendRedirect("home.html");
		}
		else
		{
			out.println("<h1 align=center>VIEW CART MODULE</h1>");
			out.println("<hr>");
			out.println("<table align=center border=1>");
			out.println("<tr>");
			out.println("<td>Product ID</td>");
			out.println("<td>NAME</td>");
			out.println("<td>PRICE</td>");
			out.println("<td>COLOR</td>");
			out.println("<td></td>");
			out.println("</tr>");

				for(Object o:ar)
				{
					String pid=(String)o;
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
						PreparedStatement ps=con.prepareStatement("select * from product where pid=?");
						ps.setString(1, pid);
						ResultSet rs=ps.executeQuery();


						if(rs.next())
						{
							sum=rs.getInt(3)+sum;
							out.println("<tr>");
							out.println("<td>"+rs.getString(1)+"</td>");
							out.println("<td>"+rs.getString(2)+"</td>");
							out.println("<td>"+rs.getInt(3)+"</td>");
							out.println("<td>"+rs.getString(5)+"</td>");
							out.println("<td><a href=rs?pid="+rs.getString(1)+">Remove from Cart</a></td>");
							out.println("</tr>");

						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					

				}
				out.println("<tr>");
				out.println("<td></td>");
				out.println("<td>TOTAL</td>");
				out.println("<td>"+sum+"</td>");
				out.println("<td></td>");
				out.println("<td></td>");

				out.println("</tr>");

				out.println("</table>");
		}
	}

}









