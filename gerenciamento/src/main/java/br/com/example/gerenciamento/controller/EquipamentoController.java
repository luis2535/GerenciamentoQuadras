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
EquipamentoController {

	private final Sistema sistema;
	
	EquipamentoController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/equipamento/{id_equipamento}")
	public Equipamento selecionarEquipamento(@PathVariable int id_equipamento) throws SelectException {
		return sistema.buscaEquipamento(id_equipamento);
	}
	
	@PostMapping("/equipamento")
	public void adicionaEquipamento(@RequestBody Equipamento equipamento) throws SelectException, InsertException {
		sistema.insereEquipamento(equipamento);
	}
	@PutMapping("/equipamento")
	public void atualizaEquipamento(@RequestBody Equipamento equipamento) throws UpdateException {
		sistema.atualizaEquipamento(equipamento);	
	}
	@DeleteMapping("/equipamento")
	public void deletaEquipamento(@RequestBody Equipamento equipamento) throws DeleteException {
		sistema.deletaEquipamento(equipamento);
	}
	@GetMapping("/equipamento")
	public List<Equipamento> selecionaEquipamentos() throws SelectException{
		return sistema.buscaEquipamentos();		
	}
	
	
}