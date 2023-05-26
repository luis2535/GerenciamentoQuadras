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
QuadraController {

	private final Sistema sistema;
	
	QuadraController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/quadra/{id_quadra}")
	public Quadra selecionarQuadra(@PathVariable int id_quadra) throws SelectException {
		return sistema.buscaQuadra(id_quadra);
	}
	
	@PostMapping("/quadra")
	public void adicionaQuadra(@RequestBody Quadra quadra) throws SelectException, InsertException {
		sistema.insereQuadra(quadra);
	}
	@PutMapping("/quadra")
	public void atualizaQuadra(@RequestBody Quadra quadra) throws UpdateException {
		sistema.atualizaQuadra(quadra);	
	}
	@DeleteMapping("/quadra")
	public void deletaQuadra(@RequestBody Quadra quadra) throws DeleteException {
		sistema.deletaQuadra(quadra);
	}
	@GetMapping("/quadra")
	public List<Quadra> selecionaQuadras() throws SelectException{
		return sistema.buscaQuadras();		
	}
	
	
}