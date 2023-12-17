import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		Connection con;
		PrintWriter out = res.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem2prj", "root", "root");
			System.out.println(con);
			String sname = req.getParameter("sname");
			System.out.println(sname);
			String scatagory = req.getParameter("scatagory");
			System.out.println(scatagory);
			String scondition = req.getParameter("scondition");
			System.out.println(scondition);
			String sdesc = req.getParameter("sdesc");
			System.out.println(sdesc);
			String saddress = req.getParameter("saddress");
			System.out.println(saddress);
			String scontact = req.getParameter("scontact");
			System.out.println(scontact);
			
			if(con != null) {
				String querry = "INSERT INTO service_details (sname, scatagory , scondition, sdesc, saddress , scontact) VALUES (? ,? ,? ,? ,? ,? )";
				PreparedStatement ps = con.prepareStatement(querry);
				ps.setString(1, sname);
				ps.setString(2, scatagory);
				ps.setString(3, scondition);
				ps.setString(4, sdesc);
				ps.setString(5, saddress);
				ps.setString(6, scontact);
				ps.executeUpdate();
				res.setContentType("text/html");
				out.println("<html><body>");
				out.print("<h1>product " + sname + " is updated successfully</h1>");
				out.print("<a href=\"firstpage.html\"><h1><b>GO BACK<b></h1></a");
				out.println("</html></body>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
