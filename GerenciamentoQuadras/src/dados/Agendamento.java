package dados;


import java.sql.Time;
import java.util.Date;
import java.util.List;


public class Agendamento {
	private int id_agendamento;
	private Time horario_inicio;
	private Time horario_fim;
	private Date data;
	private String status;
	private Usuario usuario;
	private Quadra quadra;
	private List<Equipamento> equipamentos;
	
	
	public Agendamento() {}
	
	public Agendamento(int id_agendamento, Time horario_inicio, Time horario_fim, Date data, String status, Usuario usuario, Quadra quadra, List<Equipamento> equipamentos) {
		this.id_agendamento = id_agendamento;
		this.horario_inicio = horario_inicio;
		this.horario_fim = horario_fim;
		this.data = data;
		this.status = status;
		this.usuario = usuario;
		this.quadra = quadra;
		this.equipamentos = equipamentos;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public int getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(int id_agendamento) {
		this.id_agendamento = id_agendamento;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	
	
}
