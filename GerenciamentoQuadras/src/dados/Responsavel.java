package dados;

public class Responsavel extends Usuario {
	private int id_responsavel;
	
	public Responsavel() {}
	
	public Responsavel(int id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
	public Responsavel(long cpf, String pnome, String unome, String email, String senha) {
		super(cpf, pnome, unome, email, senha);
	}
	
	public Responsavel(long cpf, String pnome, String unome, String email, String senha, int id_responsavel) {
		super(cpf, pnome, unome, email, senha);
		this.id_responsavel = id_responsavel;
	}

	public int getId_responsavel() {
		return id_responsavel;
	}

	public void setId_responsavel(int id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
}
