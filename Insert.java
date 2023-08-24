import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Insert implements Servlet {

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
		String Query="INSERT INTO BOOK VALUES(?,?,?,?)";
		int bookId=Integer.parseInt(req.getParameter("bookId"));
		String bookName=req.getParameter("bookName");
		String bookAuthor=req.getParameter("bookAuthor");
		int bookPrice=Integer.parseInt(req.getParameter("bookPrice"));
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement pst=con.prepareStatement(Query);
			pst.setInt(1, bookId);
			pst.setString(2, bookName);
			pst.setString(3, bookAuthor);
			pst.setInt(4, bookPrice);
			int rows=pst.executeUpdate();
			if(rows>0)
			{
				out.println("INSERTED SUCCESSFULLY"+"\n");
				out.println("Book ID is "+bookId);
				out.println("Book Name is "+bookName);
				out.println("Book Author is "+bookAuthor);
				out.println("Book Price is "+bookPrice);
			}
			else
				out.println("PLEASE TRY AGAIN");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<a href='index.html'> HOME</a>");
	
	}

}
