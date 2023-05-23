package dados;

public class Responsavel extends Usuario {
	private int id_responsavel;
	
	public Responsavel() {}
	
	public Responsavel(int id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
	public Responsavel(String cpf, String pnome, String unome, String email, String senha, String status) {
		super(cpf, pnome, unome, email, senha, status);
	}
	
	public Responsavel(String cpf, String pnome, String unome, String email, String senha,  String status, int id_responsavel) {
		super(cpf, pnome, unome, email, senha, status);
		this.id_responsavel = id_responsavel;
	}

	public int getId_responsavel() {
		return id_responsavel;
	}

	public void setId_responsavel(int id_responsavel) {
		this.id_responsavel = id_responsavel;
	}

	@Override
	public String toString() {
		return "Responsavel [id_responsavel=" + id_responsavel + "]";
	}
}
