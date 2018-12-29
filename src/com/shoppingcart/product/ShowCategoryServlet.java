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

@WebServlet("/shop")
public class ShowCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
				PreparedStatement ps=con.prepareStatement("select distinct category from product");
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					do
					{
						String cat=rs.getString(1);
						out.println("<a href=showpro?ct="+rs.getString(1)+">"+rs.getString(1)+"</a>");
					}while(rs.next());
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









