import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
			Connection con ;
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			out.println("<html><body>");
			out.println("<head><style>");
			out.println("<meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Styled Table</title>\r\n"
					+ "    <style>\r\n"
					+ "        table {\r\n"
					+ "            border-collapse: collapse;\r\n"
					+ "            width: 100%;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        th, td {\r\n"
					+ "            border: 1px solid #dddddd;\r\n"
					+ "            text-align: left;\r\n"
					+ "            padding: 8px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        th {\r\n"
					+ "            background-color: #f2f2f2;\r\n"
					+ "        }");
			out.println("</style></head>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem2prj", "root", "root");
			System.out.println(con);
			
			if(con != null) {
			String querry = "SELECT * FROM product_data";
			PreparedStatement ps = con.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();
			out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>pid</th><th>pname</th><th>catagory</th><th>quantity</th><th>expiry</th><th>price</th><th>photo</th><th>pcondition</th><th>pdescription</th><tr>");
			while(rs.next()) {
				int pid = rs.getInt("pid");
				String pname = rs.getString("pname");
				String catagory = rs.getString("catagory");
				String quantity = rs.getString("quantity");
				String expiry = rs.getString("expiry");
				int price = rs.getInt("price");
				
//				byte[] imageBytes = rs.getBytes("photo");
//                res.setContentType("image/jpeg");
//                try (OutputStream outputStream = res.getOutputStream()) {
//                    outputStream.write(imageBytes);
//                }
				Blob photo = rs.getBlob("photo");
				//byte [] photo = Base64.getDecoder().decode("photo");
                String pcondition = rs.getString("pcondition");
                String pdescription = rs.getString("pdesc");
                out.print("<form action=&quot;BuyProductController&quot; method=&quot;post&quot;>");
                out.println("<tr><td>" + pid + "</td><td>" + pname + "</td><td>" + catagory + "</td><td>" + quantity + "</td><td>" + expiry + "</td><td>" + price + "</td><td>" + photo + "</td><td>" + pcondition + "</td><td>" + pdescription + "</td><td>" + "<a href=\"payment.html\"><h1><b>BUY<b></h1></a>"+ "</td></tr>");   
            } 
			out.print("</form>");
            out.println("</table>");  
            out.println("</html></body>");  
            con.close(); 
			
			} else {
				out.print("CONNECTION IS NOT INVOKED");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
