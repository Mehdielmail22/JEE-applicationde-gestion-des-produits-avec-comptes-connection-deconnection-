<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="tst.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body class="container">
<br>
<%
HttpSession session2=request.getSession();

Produit a=(Produit)request.getAttribute("produit");
out.println("<h4>"+session.getAttribute("email")+"</h4>"); 
out.println("<h1>"+a.getNom()+"</h1>"); %>

<br>
<br>
<form method="get" action="Edit">
  <div class="form-group">
    <label for="formGroupExampleInput">Nom</label>
    <input type="text" class="form-control" name="nom2"id="formGroupExampleInput" name placeholder="Example input">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Prix</label>
    <input type="text" class="form-control" name="prix2" id="formGroupExampleInput2" placeholder="Another input">
    
  </div>
  <%HttpSession session5=request.getSession(); %>
  <br><%
  out.println( "<button type='submit' name='nom1'  value="+session5.getAttribute("nom1")+" class='btn btn-primary'>Edit</button>");
    		 %>
</form>
</body>
</html>