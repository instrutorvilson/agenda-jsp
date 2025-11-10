package controles;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CompromissoDao;
import dao.ContatoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Compromisso;
import modelos.Contato;

@WebServlet("/compromisso")
public class CompromissoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Compromisso> compromissos;
    private List<Contato> contatos;

    public CompromissoServlet() {
        super();       
    }
    
    @Override
    public void init() throws ServletException {
        compromissos = new ArrayList<>();
        getServletContext().setAttribute("compromissos", compromissos);
    }	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("compromissos", compromissos);
		getServletContext().setAttribute("contatos", new ContatoDao().getAll());
		
		String acao = (String) request.getParameter("acao");		
		if(acao != null ) {
			System.out.println(request.getParameter("id"));
			Compromisso compromisso = getCompromisso(Integer.parseInt((String) request.getParameter("id")));
			
			if(acao.equals("finalizar")) {				
				compromisso.setStatus('F');
			}
			if(acao.equals("cancelar")) {
				compromisso.setStatus('C');
			}			
		}
		
		request.getRequestDispatcher("listar_compromissos.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contatos = (List<Contato>) getServletContext().getAttribute("contatos");
		
		
        int idContato = Integer.parseInt(request.getParameter("idContato"));
        
        String descricao = request.getParameter("descricao");
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        Contato contato = new ContatoDao().getById(idContato);
        Compromisso compromisso = new Compromisso(descricao, data, contato);
     
        new CompromissoDao().salvar(compromisso);
        response.sendRedirect("listar_compromissos.jsp");
	}
	
	private Compromisso getCompromisso(int id) {
		for(Compromisso c : compromissos) {
			if(c.getId() == id)
				return c;
		}
		return null;
	}

}
