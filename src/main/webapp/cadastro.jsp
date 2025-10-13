<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Novo contato</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
		      rel="stylesheet" 
	          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
	          crossorigin="anonymous">
</head>
<body>
    <div class="container">
	    <header>
	         <h1>Agenda de contatos</h1>
	         <%@ include file="menu.jsp" %>
	     </header>
         <main>
            <h2 class="text-center m-4">Novo contato</h2>
	       <form action="ContatoServlet"  method="post">
	           <label>Informe nome</label>
	           <input class="form-control" type="text" placeholder="EX: ana" name="nome" required="required"/>
	           
	           <label>Informe email</label>
	           <input class="form-control" type="email" placeholder="EX: ana@gmail.com" name="email" required="required" />
	           
	           <button class="btn btn-primary mt-3">Salvar</button>
	       </form>
         </main>
        <footer>
          <%@ include file="footer.jsp" %>
        </footer>
    </div>
</body>
</html>