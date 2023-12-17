import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

public class LoginController extends HttpServlet{


	private static final long serialVersionUID = 1L;
		
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		java.sql.Connection con;
		PrintWriter out = res.getWriter();
		String username = null;
		String password = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem2prj", "root", "root");
			System.out.println(con);
			
			
			if(con != null) {
				username = req.getParameter("userid");
				System.out.println("username :" + username);
				password = req.getParameter("password");
				System.out.println("password :" + password);
				String sqlQuerry = "SELECT * FROM client_details WHERE userid = ? AND password = ? ";
				PreparedStatement ps = con.prepareStatement(sqlQuerry);
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					String dbuserid = rs.getString("userid");	
					String dbpass = rs.getString("password");	
					System.out.println("dbuserid :" + dbuserid);
					System.out.println("dbpass :" +dbpass);
					if(dbuserid.equals(username) && dbpass.equals(password)) {
						res.setContentType("text/html");
						out.print("<H1>login success</H1>");
						RequestDispatcher rd = req.getRequestDispatcher("/firstpage.html");
						rd.forward(req, res);
					}
					}else {
						res.setContentType("text/html");
						out.print("<b>wrong credentia</b>");
						RequestDispatcher rd = req.getRequestDispatcher("/loginpage.html");
						rd.include(req, res);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
