package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class WelcomeServlet extends HttpServlet
{
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String tno=request.getParameter("tno");
		pw.println("tno="+tno);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("select* from train where trainno=?");
			ps.setString(1,tno);
			ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
            	pw.println("Available="+rs.getInt(2));
            }
            else
            {
            	pw.println("sorry! No train available!");
            	RequestDispatcher rd1=request.getRequestDispatcher("input2.html");
            	rd1.include(request,response);
            }
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}
