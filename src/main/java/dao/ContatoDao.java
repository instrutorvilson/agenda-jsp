package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import modelos.Contato;
import utils.ConectaDB;

public class ContatoDao {
	private Connection con = ConectaDB.getConexao();
	
	public Contato salvar(Contato  contato) {
		String sql = "insert into tb_contatos(nome, email, senha)values(?, ?, ?)";
		String hash = BCrypt.hashpw(contato.getSenha(), BCrypt.gensalt());
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, contato.getNome());
			stm.setString(2, contato.getEmail());
			stm.setString(3, hash);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return contato;
	}
	
	public List<Contato> getAll(){
		List<Contato> contatos = new ArrayList<Contato>();
		try {
			PreparedStatement stm = con.prepareStatement("select * from tb_contatos");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Contato ct = new Contato();
				ct.setId(rs.getInt("id"));
				ct.setNome(rs.getString("nome"));
				ct.setEmail(rs.getString("email"));
				
				contatos.add(ct);				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return contatos;
	}
	
	public void deletar(Contato contato) {
		//
	}
	
	public Contato getById(int id) {
		Contato contato = null;
		try {
			PreparedStatement stm = con.prepareStatement("select * from tb_contatos where id = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));			
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return contato;
	}
	
	public void alterar(Contato contato) {
		String sql = "update tb_contatos set nome = ?, email = ? where id = ?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, contato.getNome());
			stm.setString(2, contato.getEmail());
			stm.setInt(3, contato.getId());
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Contato login(String email, String senha) {
		Contato contato = null;
		try {
			PreparedStatement stm = con.prepareStatement("select * from tb_contatos where email = ?");
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				
				if(!BCrypt.checkpw(senha, contato.getSenha())) {
					throw new RuntimeException("usuário ou senha não confere");
				}				
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return contato;
	}

}
