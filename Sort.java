import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Sort implements Servlet {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306/madam";
		String username="root",password="root";
		String Query="SELECT * FROM BOOK ORDER BY BOOKPRICE";
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement pst=con.prepareStatement(Query);
			ResultSet rss=pst.executeQuery();
			
		   	out.print(" <table border=1 align=center cellpadding=10>\r\n"
        			+ "             <tr bgcolor=pink> <th>Book ID</th> <th>Book Name</th>  <th>Book Author</th> <th>Book Price</th> </tr>\r\n");
			 while(rss.next())
		        {
		     out.println("<tr bgcolor=orange> <td> "+rss.getInt(1)+" </td>  <td> "+rss.getString(2)+"  </td>  <td> "+rss.getString(3)+"  </td>  <td> "+rss.getInt(4)+"  </td>  </tr>\r\n");
		        	
		        	
		        }
			 out.println("                                                         </table>      ");
		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<a href='index.html'> HOME</a>");
		
		
	}

}
