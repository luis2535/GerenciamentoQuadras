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
EventoController {

	private final Sistema sistema;
	
	EventoController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/evento/{id_evento}")
	public Evento selecionarEvento(@PathVariable int id_evento) throws SelectException {
		return sistema.buscaEvento(id_evento);
	}
	
	@PostMapping("/evento/{cpf}")
	public void adicionaEvento(@RequestBody Evento evento, @PathVariable String cpf) throws SelectException, InsertException {
		Admin admin = sistema.buscaServidor(cpf);
		System.out.println(evento);
		List<Quadra> quadras = sistema.buscaQuadras();
		evento.setQuadras(quadras);
		evento.setServidor(admin);
		sistema.insereEvento(evento);
	}
	@PutMapping("/evento")
	public void atualizaEvento(@RequestBody Evento evento) throws UpdateException {
		sistema.atualizaEvento(evento);	
	}
	@DeleteMapping("/evento")
	public void deletaEvento(@RequestBody Evento evento) throws DeleteException {
		sistema.deleteEvento(evento);
	}
	@GetMapping("/evento")
	public List<Evento> selecionaEventos() throws SelectException{
		return sistema.buscaEventos();		
	}
	
	
}