package org.btm.IncludeApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException 
{
	String pname=req.getParameter("nm");
	String pqty=req.getParameter("pq");
	
	
	//adding req obj into SCOPE//
	req.setAttribute("prdnm", pname);
	req.setAttribute("prdqt", pqty);
	
	RequestDispatcher rd=req.getRequestDispatcher("cc");
	rd.forward(req, resp);
	double totalsum=(double)req.getAttribute("tsum");
	PrintWriter out=resp.getWriter();
	out.println("<html></body bgcolor='blue'>"
			+ "<h1>the price of "+pname+"  is "+totalsum+"</h1>"
					+ "</html></body>");
	out.flush();
	out.close();
}
}
