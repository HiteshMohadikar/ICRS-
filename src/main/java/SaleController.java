import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig
public class SaleController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		java.sql.Connection con;
		PrintWriter out = res.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem2prj", "root", "root");
			System.out.println(con);
			
			String pname = req.getParameter("pname");
			System.out.println(pname);
			String catagory = req.getParameter("catagory");
			System.out.println(catagory);
			String quantity = req.getParameter("quantity");
			System.out.println(quantity);
			String expiry = req.getParameter("expiry");
			System.out.println(expiry);
			String price = req.getParameter("price");
			System.out.println(price);
			InputStream inputStream = req.getPart("photo").getInputStream();
			System.out.println(inputStream);
			String condition = req.getParameter("condition");
			System.out.println(condition);
			String desc = req.getParameter("desc");
			System.out.println(desc);
			
			if(con != null) {
				String querry = "INSERT INTO product_data (pname, catagory, quantity, expiry, price , photo , pcondition , pdesc) VALUES (? ,? ,? ,? ,? ,? ,? ,? )";
				PreparedStatement ps = con.prepareStatement(querry);
				ps.setString(1, pname);
				ps.setString(2, catagory);
				ps.setString(3, quantity);
				ps.setString(4, expiry);
				ps.setString(5, price);
				ps.setBlob(6, inputStream);
				ps.setString(7, condition);
				ps.setString(8, desc);
				ps.executeUpdate();
				inputStream.close();
				
				res.setContentType("text/html");
				out.println("<html><body>");
				out.print("<h1>product " + pname + " is updated successfully</h1>");
				out.print("<a href=\"firstpage.html\"><h1><b>GO BACK<b></h1></a");
				out.println("</html></body>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
