

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connexion2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion2() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projet2","root",""); 
			Statement stmt2=con.createStatement();  
			ResultSet rs=stmt2.executeQuery("select * from client");
			int t=0;
			while(rs.next()) {
				if(rs.getString("email").equals(session.getAttribute("email"))&&rs.getString("password").equals(session.getAttribute("password"))) {
					t++;
					
				}
			}
		
			if(t==0) {
				request.setAttribute("result", "l'email ou le password est incorrect");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(t!=0) {
				request.setAttribute("result", "l'email et le password sont correct");
				request.getRequestDispatcher("vue.jsp").forward(request, response);
			}
		
			con.close();  
		


		}catch(Exception e){ e.printStackTrace();} 
	

		
	}


}
