
<nav>
	<a href="index.jsp">Home</a> 
	<a href="cadastro.jsp">Cadastro</a> 
	<a href="consulta.jsp">Consulta</a>
	<a href="compromisso">Compromissos</a>
	<%
       if(session.getAttribute("userLogado") != null){
    	  out.print("<a href='logout.jsp'>Logout</a>");
       }
       else{
    	   out.print("<a href='login.jsp'>Logar</a>");
       }
     %>
</nav>
