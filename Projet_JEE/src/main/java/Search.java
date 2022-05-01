

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import tst.Moodle;
import tst.Produit;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session2=request.getSession();
		if(session2.getAttribute("email").equals("faux")) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		try{  
			HttpSession session=request.getSession();
			String client =(String) session.getAttribute("email");
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/projet2","root","");

            String nom= request.getParameter("search");
			Statement stmt2=con.createStatement();  
			String sql="select * from produit where client='"+client+"'";
			ResultSet rs=stmt2.executeQuery(sql); 
			List<Produit> b=new ArrayList<Produit>() ;
			
			 while(rs.next()) {
				 
				 
				 b.add(new Produit(rs.getString("nom"),Integer.parseInt(rs.getString("prix"))));
				 
			     
				 }
			 
			 
			 Moodle produits=new Moodle(b) ;
			
			 Moodle produits2=new Moodle(produits.Search(nom)); 
			 
		 
			con.close();  
	
			request.setAttribute("produitsearch", produits2);
	
			request.getRequestDispatcher("vue.jsp").forward(request, response);
			request.setAttribute("produitsearch", produits2);
			

			}catch(Exception e){ e.printStackTrace();} 
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
