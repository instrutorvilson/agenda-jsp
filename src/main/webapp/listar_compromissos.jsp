<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, modelos.Compromisso"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
		      rel="stylesheet" 
	          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
	          crossorigin="anonymous">
</head>
<body>
  <div class="container">
      <header>
	         <h1 class="text-center">Compromissos</h1>
	         <%@ include file="menu.jsp" %>
	  </header>
      <table class="table table-striped container">
		  <thead>
		    <tr>
		      <th>Contato</th>
		      <th>Descrição</th>
		      <th>Data</th>
		      <th>Status</th>
		      <th>Ação</th>
		    </tr>
		  </thead>
		  <tbody>
			    <%
			      List<Compromisso> compromissos = (List<Compromisso>) getServletContext().getAttribute("compromissos");
			      if (compromissos != null && !compromissos.isEmpty()) {
			        for (Compromisso c : compromissos) {
					    %>
					      <tr>
					        <td><%= c.getContato().getNome() %></td>
					        <td><%= c.getDescricao() %></td>
					        <td><%= c.getData() %></td>
					        <td><%= c.getStatus() %></td>
					        <td><a href="<%= request.getContextPath() %>/compromisso?acao=cancelar&id=<%= c.getId() %>">Cancelar</a></td>
					      </tr>
					    <%
					        }
					      } else {
					    %>
					      <tr><td colspan="3">Nenhum compromisso cadastrado</td></tr>
					    <%
				      }
			    %>
	      </tbody>
	</table>
	<hr />
	<a href="cadastro_compromissos.jsp">Novo</a>
      
  </div>

</body>
</html>