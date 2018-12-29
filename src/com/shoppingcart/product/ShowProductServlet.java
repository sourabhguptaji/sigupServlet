package com.shoppingcart.product;

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

@WebServlet("/showpro")
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			String cate=request.getParameter("ct");
			
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);

			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
				PreparedStatement ps=con.prepareStatement("select *  from product where category=?");
				ps.setString(1, cate);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.println("<table align=center border=1>");
					out.println("<tr>");
					out.println("<td>PID</td>");
					out.println("<td>PNAME</td>");
					out.println("<td>PRICE</td>");
					out.println("<td>BRAND</td>");
					out.println("<td>COLOR</td>");
					out.println("<td></td>");					
					out.println("</tr>");

					do
					{
						out.println("<tr>");
						out.println("<td>"+rs.getString(1)+"</td>");
						out.println("<td>"+rs.getString(2)+"</td>");
						out.println("<td>"+rs.getInt(3)+"</td>");
						out.println("<td>"+rs.getString(4)+"</td>");
						out.println("<td>"+rs.getString(5)+"</td>");
						out.println("<td><a href=atc?pid="+rs.getString(1)+">ADD TO CART</a></td>");

						out.println("</tr>");


					}while(rs.next());
					out.println("</table>");
				}
				else
				{
					out.println("No Product Exists");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
	}

}









