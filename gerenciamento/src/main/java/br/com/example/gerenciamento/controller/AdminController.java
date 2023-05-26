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
AdminController {

	private final Sistema sistema;
	
	AdminController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/admin/{cpf}")
	public Admin selecionarAdmin(@PathVariable String cpf) throws SelectException {
		return sistema.buscaServidor(cpf);
	}
	
	@PostMapping("/admin")
	public void adicionaAdmin(@RequestBody Admin servidor) throws SelectException, InsertException, UpdateException {
		sistema.insereServidor(servidor);
	}
	@PutMapping("/admin")
	public void atualizaAdmin(@RequestBody Admin servidor) throws UpdateException {
		sistema.atualizaServidor(servidor);	
	}
	@DeleteMapping("/admin")
	public void deletaAdmin(@RequestBody Admin servidor) throws DeleteException {
		sistema.deletaServidor(servidor);
	}
	@GetMapping("/admin")
	public List<Admin> selecionaAdmins() throws SelectException{
		return sistema.buscaServidores();		
	}
	
	
}