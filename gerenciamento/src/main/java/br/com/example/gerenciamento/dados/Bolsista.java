package br.com.example.gerenciamento.dados;

public class Bolsista extends Usuario{
	private int id_bolsista;
	
	public Bolsista() {}
	
	public Bolsista(int id_bolsista) {
		this.id_bolsista = id_bolsista;
	}
	
	public Bolsista(String cpf, String pnome, String unome, String email, String senha, String status, String funcao) {
		super(cpf, pnome, unome, email, senha, status, funcao);
	}
	
	public Bolsista(String cpf, String pnome, String unome, String email, String senha, String status, String funcao, int id_bolsista) {
		super(cpf, pnome, unome, email, senha, status, funcao);
		this.id_bolsista = id_bolsista;
	}

	public int getId_bolsista() {
		return id_bolsista;
	}

	public void setId_bolsista(int id_bolsista) {
		this.id_bolsista = id_bolsista;
	}

	
	@Override
	public String toString() {
	    return "cpf='" + getCpf() + "', pnome='" + getPnome() + "', unome='" + getUnome() + "', email='" + getEmail() + "', senha='" + getSenha() + "', id_bolsista=" + id_bolsista + "'}";
	}


}
