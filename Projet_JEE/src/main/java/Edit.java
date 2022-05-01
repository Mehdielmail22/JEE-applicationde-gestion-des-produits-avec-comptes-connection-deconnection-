

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tst.*;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom=request.getParameter("edit");
		HttpSession session2=request.getSession();
		if(session2.getAttribute("email").equals("faux")) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		HttpSession session=request.getSession();
		session.setAttribute("nom1", nom);
		try {

			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/projet2","root",""); 
			Statement stmt=con.createStatement();
			String sql="select * from produit where nom='"+nom+"'";
			ResultSet rs=stmt.executeQuery(sql); 
			Produit produit=new Produit(nom);
			request.setAttribute("produit", produit);
			if(request.getParameter("nom2")!=null&&request.getParameter("prix2")!=null) {
				String sql2="UPDATE produit SET nom='"+request.getParameter("nom2")+"',prix='"+Integer.parseInt(request.getParameter("prix2"))+"' WHERE nom='"+request.getParameter("nom1")+"'";
				stmt.executeUpdate(sql2);
				request.getRequestDispatcher("vue.jsp").forward(request, response);
			}
			request.getRequestDispatcher("edit.jsp").forward(request, response);

		}
		catch(Exception e){ e.printStackTrace();} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
