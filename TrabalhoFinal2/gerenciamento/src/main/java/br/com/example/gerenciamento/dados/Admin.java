package br.com.example.gerenciamento.dados;

public class Admin extends Usuario{

	private int id_admin;
	
	public Admin() {}
	
	public Admin(int id_admin){
		this.id_admin = id_admin;
		
	}
	public Admin(String cpf, String pnome, String unome, String email, String senha, String status, String funcao) {
		super(cpf, pnome, unome, email, senha, status, funcao);
	}
	public Admin(String cpf, String pnome, String unome, String email, String senha, String status, String funcao, int id_admin) {
		super(cpf, pnome, unome, email, senha, status, funcao);
		this.id_admin = id_admin;
	}

	public int getId_admin() {
		return id_admin;
	}

	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}

	@Override
	public String toString() {
		return "Admin [id_admin=" + id_admin + "]";
	}

}