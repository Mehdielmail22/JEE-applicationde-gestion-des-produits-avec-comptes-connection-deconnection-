

import java.io.IOException;
import java.io.PrintWriter;
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

import tst.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
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

		//here sonoo is database name, root is username and password
		if(request.getParameter("delete")!=null) {
			String nom=request.getParameter("delete");
			String sql="DELETE FROM produit WHERE nom='"+nom+"'";
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate(sql); 
			request.getRequestDispatcher("vue.jsp").forward(request, response);
		}
		//if(request.getParameter("edit")!=null) {
		//	String nom1=request.getParameter("edit");
		//	String sql="UPDATE produit SET nom='"+nom+"',prix='"+prix+"' WHERE nom='"+nom1+"'";
		//	Statement stmt1 = con.createStatement();
		//	stmt1.executeUpdate(sql); 
		//	request.getRequestDispatcher("vue.jsp").forward(request, response);
		//}
		if(request.getParameter("nom")!=""&&request.getParameter("prix")!="") {
			String nom=request.getParameter("nom");
			String a=request.getParameter("prix");
			
			
			String sql="INSERT INTO produit (nom, prix, client) VALUES ('"+nom+"','"+a+"','"+client+"')";
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate(sql); 
		}

		Statement stmt2=con.createStatement(); 
		String sql2="select * from produit where client='"+client+"'";
		ResultSet rs=stmt2.executeQuery(sql2); 
		List<Produit> b=new ArrayList<Produit>() ;
		
		 while(rs.next()) {
			 
			 
			 b.add(new Produit(rs.getString("nom"),Integer.parseInt(rs.getString("prix"))));
			 }
		 
		 
		 Moodle produits=new Moodle(b) ;
		 
	 
		con.close();  
		String f="hy babe";
		request.setAttribute("nom", f);
		request.setAttribute("produits", produits);

		request.getRequestDispatcher("vue.jsp").forward(request, response);

		request.setAttribute("nom", f);
		request.setAttribute("produits", produits);
		}catch(Exception e){ e.printStackTrace();} 
	}




}
