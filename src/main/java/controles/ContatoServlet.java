package controles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ContatoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Contato;
import utils.ConectaDB;

@WebServlet("/ContatoServlet")
public class ContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Contato> contatos = new ArrayList<>();

	public ContatoServlet() {
	}

	@Override
    public void init() throws ServletException {
        contatos = new ArrayList<>();
        getServletContext().setAttribute("contatos", contatos);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if (acao != null) {
			String idContato = request.getParameter("id");
			Contato ct = getById(Integer.parseInt(idContato));
			if(acao.equals("editar")) {				
				request.setAttribute("contato", ct);
				RequestDispatcher rd = request.getRequestDispatcher("edicao.jsp");
				rd.forward(request, response);
			}
			else {
				contatos.remove(ct);
				response.sendRedirect("ContatoServlet");
			}
		} else {
			contatos = new ContatoDao().getAll();
			request.setAttribute("contatos", contatos);
			RequestDispatcher rd = request.getRequestDispatcher("consulta.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if (acao != null) {
			try {
				ContatoDao cdao = new ContatoDao();
				Contato contato = cdao.getById(Integer.parseInt(request.getParameter("id")));			
				contato.setNome(request.getParameter("nome"));
				contato.setEmail(request.getParameter("email"));	
				contato.setSenha(request.getParameter("senha"));
				cdao.alterar(contato);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			/*Contato contato = new Contato();
			contato.setId(contatos.size() + 1);
			contato.setNome(request.getParameter("nome"));
			contato.setEmail(request.getParameter("email"));
			contatos.add(contato);	*/
			/*if(ConectaDB.getConexao() != null) {
				JOptionPane.showMessageDialog(null, "conectado");
			}*/
			
			try {
				Contato contato = new Contato();
				contato.setNome(request.getParameter("nome"));
				contato.setEmail(request.getParameter("email"));
				contato.setSenha(request.getParameter("senha"));
				new ContatoDao().salvar(contato);
			}catch (RuntimeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		response.sendRedirect("ContatoServlet");
	}

	private Contato getById(int id) {
		for (Contato ct : contatos) {
			if (ct.getId() == id) {
				return ct;
			}
		}
		return null;
	}

}
