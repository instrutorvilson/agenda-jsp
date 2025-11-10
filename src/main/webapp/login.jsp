<%@page import="dao.ContatoDao"%>
<%@page import="modelos.Contato"%>
<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
		      rel="stylesheet" 
	          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
	          crossorigin="anonymous">
</head>
<body>
	<form action="login.jsp" method="post" class="container">
	    <h2 class="text-center m-3">Login</h2>
		<label class="label-control">Informe usuário</label> 
		<input class="form-control" type="text"placeholder="Ex: ana@gmail.com" name="username" /> 
		<label>Informe senha</label> 
		<input class="form-control" type="password" name="senha" />
		
		<button class="btn btn-primary mt-3">Logar</button>
	</form>
	<%
	   String username = request.getParameter("username");
	   if(username != null){
		   if(username.isEmpty()){
			   JOptionPane.showMessageDialog(null, "O username deve ser informado");
			   return;
		   }
		   
		   String senha = request.getParameter("senha");
		   if(senha.isEmpty()){
			   JOptionPane.showMessageDialog(null, "A senha deve ser informada");
			   return; 
		   }
		   try{
		     Contato ct = new ContatoDao().login(username, senha);
		     //cria a sessão
		     session.setAttribute("userLogado", ct);	
		     String pagina = (String) session.getAttribute("page");		    
			    if (pagina != null) {
			        session.removeAttribute("page"); 
			        response.sendRedirect(pagina);
			    } else {
			        response.sendRedirect("index.jsp");
			    }
		   }catch(RuntimeException ex){
			   System.err.print(ex.getMessage());
		   }		   
		  
	   }
	   
	  
	%>

</body>
</html>