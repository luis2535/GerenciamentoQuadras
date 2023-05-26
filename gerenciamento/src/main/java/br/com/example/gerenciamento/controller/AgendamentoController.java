package br.com.example.gerenciamento.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.example.gerenciamento.negocios.Sistema;
import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

@RestController
@RequestMapping("/api")
public class
AgendamentoController {

	private final Sistema sistema;
	
	AgendamentoController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/agendamento/{id_agendamento}")
	public Agendamento selecionarAgendamento(@PathVariable int id_agendamento) throws SelectException {
		return sistema.buscaAgendamento(id_agendamento);
	}
	
	@PostMapping("/agendamento")
	public void adicionaAgendamento(@RequestBody Reserva reserva) throws SelectException, InsertException {
		sistema.insereAgendamento(reserva);
	}
	@PutMapping("/agendamento")
	public void atualizaAgendamento(@RequestBody Agendamento agendamento) throws UpdateException {
		sistema.atualizaAgendamento(agendamento);	
	}
	@DeleteMapping("/agendamento")
	public void deletaAgendamento(@RequestBody Reserva reserva) throws DeleteException {
		sistema.deletaAgendamento(reserva);
	}
	@GetMapping("/agendamento")
	public List<Agendamento> selecionaAgendamentos() throws SelectException{
		return sistema.buscaAgendamentos();		
	}
	
	
}