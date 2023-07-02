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
BolsistaController {

	private final Sistema sistema;
	
	BolsistaController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/isbolsista/{cpf}")
	public boolean isBolsista(@PathVariable String cpf) throws SelectException{
		return sistema.boolAdmin(cpf);
	}
	@GetMapping("/bolsista/{cpf}")
	public Bolsista selecionarBolsista(@PathVariable String cpf) throws SelectException {
		return sistema.buscaBolsista(cpf);
	}
	
	@PostMapping("/bolsista")
	public void adicionaBolsista(@RequestBody Bolsista bolsista) throws SelectException, InsertException, UpdateException {
		sistema.insereBolsista(bolsista);
	}
	@PutMapping("/bolsista")
	public void atualizaBolsista(@RequestBody Bolsista bolsista) throws UpdateException {
		sistema.atualizaBolsista(bolsista);	
	}
	@DeleteMapping("/bolsista")
	public void deletaBolsista(@RequestBody Bolsista bolsista) throws DeleteException {
		sistema.deletaBolsista(bolsista);
	}
	@GetMapping("/bolsista")
	public List<Bolsista> selecionaBolsistas() throws SelectException{
		return sistema.buscaBolsistas();		
	}
	
	
}