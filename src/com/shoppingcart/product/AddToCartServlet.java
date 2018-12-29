package com.shoppingcart.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/atc")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid=request.getParameter("pid");
		HttpSession s=request.getSession();
		ArrayList ar= null;

		ar=(ArrayList)s.getAttribute("items");
		
		
		if(ar==null)
		{
			ar=new ArrayList();
			ar.add(pid);
			s.setAttribute("items", ar);
			System.out.println(ar.size());

		}
		else
		{
			ar.add(pid);
			System.out.println(ar.size());

		}
		
		response.sendRedirect("home.html");
	}

}









