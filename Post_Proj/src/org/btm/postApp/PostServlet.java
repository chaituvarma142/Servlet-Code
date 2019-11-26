package org.btm.postApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException 
{
	String sid=req.getParameter("i");
	int id=Integer.parseInt(sid);//converting String to integer
	String name=req.getParameter("nm");
	String branch=req.getParameter("br");
	String perc=req.getParameter("per");
	double dperc=Double.parseDouble(perc);
	PrintWriter out=resp.getWriter();
	out.println("<html><body bgcolor='cyan'>"
			+ "<h1>welcome to portal:"+name+" from "+branch+"</h1>"
					+ "</body></html>");
	out.flush();
	out.close();
	Connection con=null;
	PreparedStatement pstmt=null;
	String qry="insert into btm.studentinfo values(?,?,?,?)";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tiger");
		pstmt=con.prepareStatement(qry);
		//setting values for placeholder
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, branch);
		pstmt.setDouble(4, dperc);
		//executing the sql queries
		pstmt.executeUpdate();
		
		
	} catch (ClassNotFoundException | SQLException e) 
	{
		
	}
	finally {
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
