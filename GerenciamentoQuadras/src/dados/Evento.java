package dados;


import java.sql.Time;
import java.util.Date;
import java.util.List;


public class Evento {
	private int id_evento;
	private Date data;
	private Time horario_inicio;
	private Time horario_fim;
	private String status;
	private String nome;
	private Usuario usuario;
	private List<Quadra> quadras;
	
	public Evento() {}

	public Evento(int id_evento, Date data, Time horario_inicio, Time horario_fim, String status, String nome, Usuario usuario, List <Quadra> quadras) {
		this.id_evento = id_evento;
		this.data = data;
		this.horario_inicio = horario_inicio;
		this.horario_fim = horario_fim;
		this.status = status;
		this.nome = nome;
		this.usuario = usuario;
		this.quadras = quadras;
	}

	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(Time horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public Time getHorario_fim() {
		return horario_fim;
	}

	public void setHorario_fim(Time horario_fim) {
		this.horario_fim = horario_fim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
