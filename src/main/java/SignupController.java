import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		Connection con;
		PrintWriter out = res.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem2prj", "root", "root");
			System.out.println(con);
			
			if(con != null) {
				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				String email = req.getParameter("email");
				String number = req.getParameter("number");
				String userid = req.getParameter("userid");
				String password = req.getParameter("password");
				
				String sqlquerry = "INSERT INTO client_details (fname , lname , email , number , userid , password) VALUES(?,?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(sqlquerry);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, email);
				ps.setString(4, number);
				ps.setString(5, userid);
				ps.setString(6, password);
				
				ps.executeUpdate();
				
				if(ps != null) {
					res.setContentType("text/html");
					out.print("RECORD INSERTED SUCCESSFULLY");
					RequestDispatcher rd = req.getRequestDispatcher("/index.html");
					rd.forward(req, res);
				}else {
					res.setContentType("text/html");
					out.print("SOMETHING WRONG");
					RequestDispatcher rd = req.getRequestDispatcher("/signup.html");
					rd.include(req, res);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
