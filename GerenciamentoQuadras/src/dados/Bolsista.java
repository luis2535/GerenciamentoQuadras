package dados;

public class Bolsista extends Usuario{
	private int id_bolsista;
	private String curso;
	
	public Bolsista() {}
	
	public Bolsista(int id_bolsista, String curso) {
		this.id_bolsista = id_bolsista;
		this.curso = curso;
	}
	
	public Bolsista(String cpf, String pnome, String unome, String email, String senha) {
		super(cpf, pnome, unome, email, senha);
	}
	
	public Bolsista(String cpf, String pnome, String unome, String email, String senha, int id_bolsista, String curso) {
		super(cpf, pnome, unome, email, senha);
		this.id_bolsista = id_bolsista;
		this.curso = curso;
	}

	public int getId_bolsista() {
		return id_bolsista;
	}

	public void setId_bolsista(int id_bolsista) {
		this.id_bolsista = id_bolsista;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	@Override
	public String toString() {
	    return "cpf='" + getCpf() + "', pnome='" + getPnome() + "', unome='" + getUnome() + "', email='" + getEmail() + "', senha='" + getSenha() + "', id_bolsista=" + id_bolsista + ", curso='" + curso + "'}";
	}


}
