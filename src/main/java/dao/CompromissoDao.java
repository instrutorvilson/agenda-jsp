package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelos.Compromisso;
import utils.ConectaDB;

public class CompromissoDao {
     private Connection con = ConectaDB.getConexao();
	
	public Compromisso salvar(Compromisso  compromisso) {
		System.out.println("aqu");
		String sql = "insert into tb_compromissos(descricao, data, idcontato)values(?, ?, ?)";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, compromisso.getDescricao());
			stm.setDate(2, Date.valueOf(compromisso.getData()));
			stm.setInt(3, compromisso.getContato().getId());
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return compromisso;
	}
}
