package org.btm.forwardApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException 
{
	
	String pnmae=(String)req.getAttribute("prdnm");
	String pqty=(String)req.getAttribute("prdqt");
	int qty=Integer.parseInt(pqty);
	double price=35000;
	double totalsum=(price*qty); //BUSINESS LOGIC//
	
	PrintWriter out=resp.getWriter();
	out.println("<html></body bgcolor='blue'>"
			+ "<h1>the price of "+pnmae+"  is "+totalsum+"</h1>"
					+ "</html></body>");
	Connection con=null;
	PreparedStatement pstmt=null;
	String sql="insert into btm.product values(?,?,?)";
	try {
		getClass().forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
		pstmt=con.prepareStatement(sql);
	//setting values for placeholders
		pstmt.setString(1, pnmae);
		pstmt.setInt(2, qty);
		pstmt.setDouble(3, totalsum);
		pstmt.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally 
	{
	if(pstmt!=null)
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
