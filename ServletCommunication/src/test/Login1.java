package test;
import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login1 extends HttpServlet
{
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String uname=request.getParameter("username");
		String pword=request.getParameter("password");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select* from registration where uname=? and pword=?");
			ps.setString(1,uname);
			ps.setString(2,pword);
			ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
            	RequestDispatcher rd=request.getRequestDispatcher("input2.html");
            	rd.include(request,response);
            }
            else
            {
            	pw.println("sorry! username or password error!");
            	RequestDispatcher rd=request.getRequestDispatcher("input.html");
            	rd.include(request,response);
            }
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}
