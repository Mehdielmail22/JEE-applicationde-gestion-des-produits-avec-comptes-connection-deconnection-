<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="tst.*"%>
   <%@page import="java.util.List" %>
   <%@page import="java.sql.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body >

<nav class="navbar navbar-light bg-light justify-content-between">
  <a class="navbar-brand">Produits</a>
  <%HttpSession session1=request.getSession();
  out.println(session.getAttribute("email"));
  %>
  <form class="form-inline" action="Search" method="get">
    <input class="form-control mr-sm-2" type="search" placeholder="Search" name="search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
  </form>
  <form action="Deconextion" method="get" class="container">
    <button type="submit" name="dec" value="vrai" class="btn btn-primary">Deconextion</button>
</form>
</nav>
<br>


<%			HttpSession session2=request.getSession();
if(session2.getAttribute("email").equals("faux")) {
	request.getRequestDispatcher("error.jsp").forward(request, response);
} %>
<form action="controlleur" method="get" class="container">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Nom de produit</label>
    <input type="text" class="form-control" id="nom" name="nom">

  <div class="form-group">
    <label for="exampleInputPassword1">Prix</label>
    <input type="text" class="form-control"  name="prix">
  </div>

<br>



  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>



<table class="table" class="container">
  <thead>
    <tr>
      <th scope="col">Nom</th>
      <th scope="col">Prix</th>
    </tr>

    <%
    if(request.getAttribute("produits")==null&&request.getAttribute("produitsearch")==null){
		HttpSession session4=request.getSession();
		String client =(String) session4.getAttribute("email");
    	try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/projet2","root","");  
    		//here sonoo is database name, root is username and password


    		Statement stmt2=con.createStatement();  
    		String sql2="select * from produit where client='"+client+"'";
    		ResultSet rs=stmt2.executeQuery(sql2);
    	   
    		
    		 while(rs.next()) {
    			 
    			 
    	
    			 out.println("<tr><td>"+rs.getString("nom")+"</td><td>"+Integer.parseInt(rs.getString("prix"))+"</td><td><form action='controlleur'  method='get'><button type='submit' name='delete' value='"+rs.getString("nom")+"'  class='btn btn-danger'>Delete</button></form> </td><td><form action='Edit'  method='get'><button type='submit' name='edit' value='"+rs.getString("nom")+"' class='btn btn-success' >Edit</button></form></td></tr>");
    			 
    		     
    			 }
    		 
    		 
    
    		 
    	 
    		con.close();  
    	}
    	catch(Exception e){ e.printStackTrace();} 
    }
    if(request.getAttribute("produits")!=null){
    Moodle c=(Moodle)request.getAttribute("produits");
    List<Produit> d=c.getProduits();
    int e=d.size();
    for(int i=0;i<e;i++){
    	out.println("<tr><td>"+d.get(i).getNom()+"</td><td>"+d.get(i).getPrix()+"</td><td><form action='controlleur'  method='get'><button type='submit' name='delete' value='"+d.get(i).getNom()+"'  class='btn btn-danger'>Delete</button></form> </td><td><form action='Edit'  method='get'><button type='submit' name='edit' value='"+d.get(i).getNom()+"' class='btn btn-success' >Edit</button></form></td></tr>");
    }
    }
    	 else if(request.getAttribute("produitsearch")!=null){
    	    Moodle f=(Moodle)request.getAttribute("produitsearch");
    	    List<Produit> n=f.getProduits();
    	    int e=n.size();
    	    for(int i=0;i<e;i++){
    	    	out.println("<tr><td>"+n.get(i).getNom()+"</td><td>"+n.get(i).getPrix()+"</td><td><form action='controlleur'  method='get'><button type='submit' name='delete' value='"+n.get(i).getNom()+"'  class='btn btn-danger'>Delete</button></form> </td><td><form action='Edit'  method='get'><button type='submit' name='edit' value='"+n.get(i).getNom()+"' class='btn btn-success' >Edit</button></form></td></tr>");
    	    }
    		
    	}
    	
    	%>
    
  </thead>
  <tbody>


    <tr>
      </tbody>
</table>



</body>
</html>