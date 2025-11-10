<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,modelos.Contato"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de compromissos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
		      rel="stylesheet" 
	          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
	          crossorigin="anonymous">
</head>
<body>
    <%
       if(session.getAttribute("userLogado") == null){
    	   session.setAttribute("page", "cadastro_compromissos.jsp");
    	   response.sendRedirect("login.jsp");
       }
    %>
    <div class="container">
       <header>
	        <h2 class="text-center mt-5">Novo Compromisso</h2>
	         <%@ include file="menu.jsp" %>
	     </header>
        
		<form action="compromisso" method="post">
		  <label>Contato:</label>
		  <select name="idContato" class="form-select">
		    <%
		     List<Contato> contatos = (List<Contato>) getServletContext().getAttribute("contatos");
		     if(contatos != null){		       
		        for (Contato c : contatos) {
		    %>
		      <option value="<%= c.getId() %>"><%= c.getNome() %></option>
		    <%
		      }}
		    %>
		  </select><br>
		
		  <label>Descrição:</label>
		  <input class="form-control" type="text" name="descricao" /><br>
		
		  <label>Data:</label>
		  <input class="form-control" type="date" name="data" /><br>
		
		  <button class="btn btn-primary" type="submit">Salvar</button>
		</form>
    </div>
</body>
</html>