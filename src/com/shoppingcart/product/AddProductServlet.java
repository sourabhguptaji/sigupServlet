package com.shoppingcart.product;


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

@WebServlet("/ap")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out=response.getWriter();

	String proid=request.getParameter("pid");
	String proname=request.getParameter("pnm");
	String x=request.getParameter("prc");
	int proprice=Integer.parseInt(x);
	String probrand=request.getParameter("pb");
	String procolor=request.getParameter("pcol");
	String procategory=request.getParameter("pcat");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");	
			PreparedStatement ps=con.prepareStatement("insert into product values(?,?,?,?,?,?)");
			ps.setString(1, proid);
			ps.setString(2, proname);
			ps.setInt(3, proprice);
			ps.setString(4, probrand);
			ps.setString(5, procolor);
			ps.setString(6, procategory);
			
			
			
			int i=0;
			i=ps.executeUpdate();
			if(i==0)
			{
				out.println("Product Not Inserted");
			}
			else
			{
					response.sendRedirect("addpro.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	

}







