package modelos;

import java.time.LocalDate;

public class Compromisso {
	private int id;
	private String descricao;
	private LocalDate data;
	private Contato contato;
	private char status;
	
	
	public Compromisso() {
		
	}

	public Compromisso(int id, String descricao, LocalDate data, Contato contato) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.contato = contato;
		this.status = 'A';
	}
	
	public Compromisso(String descricao, LocalDate data, Contato contato) {
		this.descricao = descricao;
		this.data = data;
		this.contato = contato;
		this.status = 'A';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Compromisso [id=" + id + ", descricao=" + descricao + ", data=" + data + ", contato=" + contato + "]";
	}

}
