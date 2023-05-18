package dados;

public class Servidor extends Usuario{

	private int id_servidor;
	private String funcao;
	
	public Servidor() {}
	
	public Servidor(int id_servidor, String funcao){
		this.id_servidor = id_servidor;
		this.funcao = funcao;
	}
	public Servidor(long cpf, String pnome, String unome, String email, String senha) {
		super(cpf, pnome, unome, email, senha);
	}
	public Servidor(long cpf, String pnome, String unome, String email, String senha, int id_servidor, String funcao) {
		super(cpf, pnome, unome, email, senha);
		this.id_servidor = id_servidor;
		this.funcao = funcao;
	}

	public int getId_servidor() {
		return id_servidor;
	}

	public void setId_servidor(int id_servidor) {
		this.id_servidor = id_servidor;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
}
