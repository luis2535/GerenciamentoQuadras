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
BlocoController {

	private final Sistema sistema;
	
	BlocoController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/bloco/{id_bloco}")
	public Bloco selecionarBloco(@PathVariable int id_bloco) throws SelectException {
		return sistema.buscaBloco(id_bloco);
	}
	
	@PostMapping("/bloco")
	public void adicionaBloco(@RequestBody Bloco bloco) throws SelectException, InsertException, UpdateException {
		sistema.insereBloco(bloco);
	}
	@PutMapping("/bloco")
	public void atualizaBloco(@RequestBody Bloco bloco) throws UpdateException {
		sistema.atualizaBloco(bloco);	
	}
	@DeleteMapping("/bloco")
	public void deletaBloco(@RequestBody Bloco bloco) throws DeleteException {
		sistema.deletaBloco(bloco);
	}
	@GetMapping("/bloco")
	public List<Bloco> selecionaBlocos() throws SelectException{
		return sistema.buscaBlocos();		
	}
	
	
}