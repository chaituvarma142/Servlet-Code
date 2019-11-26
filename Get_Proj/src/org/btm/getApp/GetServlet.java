package org.btm.getApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetServlet extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException
{
	String sid=req.getParameter("i");
	int id=Integer.parseInt(sid);
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	String sql="select * from btm.studentinfo where id=?";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
		pstmt=con.prepareStatement(sql);
		//setting values for place holder
		pstmt.setInt(1, id);
		rs=pstmt.executeQuery();
		PrintWriter out=resp.getWriter();
		if (rs.next())
		{

		out.println("<html><body bgcolor='pink'>"
				+ "<h1>Welcome to portal Name of Student is"+rs.getString(2)+"and branch is "+rs.getString(3)+"</h1>"
						+ "</body></html>");
		out.flush();
		}
		else
		{
			out.println("<html><body color='yellow'>"
					+ "<h1>the entered id doesnot match the studentinformation <!!> Sorry try again----></h1>"
					+ "</body></html>");
			out.flush();
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
