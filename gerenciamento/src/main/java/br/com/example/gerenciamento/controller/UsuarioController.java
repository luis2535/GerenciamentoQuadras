package br.com.example.gerenciamento.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.example.gerenciamento.negocios.Sistema;
import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

@RestController
@RequestMapping("/api")
public class
UsuarioController {

	private final Sistema sistema;
	
	UsuarioController(Sistema sistema){
		this.sistema = sistema;
	}
	@GetMapping("/usuario/login")
	public Usuario loginUsuario(@RequestParam String cpf, @RequestParam String senha) throws SelectException {
	    Usuario user = sistema.buscaUsuario(cpf);
	    if (user != null && user.getSenha().equals(senha)) {
	        return user;
	    }
	    Usuario nulo = new Usuario();
	    return nulo;
	}

	@GetMapping("/usuario/{cpf}")
	public Usuario selecionarUsuario(@PathVariable String cpf) throws SelectException {
		return sistema.buscaUsuario(cpf);
	}
	
	@PostMapping("/usuario")
	public void adicionaUsuario(@RequestBody Usuario usuario) throws SelectException, InsertException {
		sistema.insereUsuario(usuario);
	}
	@PutMapping("/usuario")
	public void atualizaUsuario(@RequestBody Usuario usuario) throws UpdateException {
		sistema.atualizaUsuario(usuario);	
	}
	@DeleteMapping("/usuario")
	public void deletaUsuario(@RequestBody Usuario usuario) throws DeleteException {
		sistema.deletaUsuario(usuario);
	}
	@GetMapping("/usuario")
	public List<Usuario> selecionaUsuarios() throws SelectException{
		return sistema.buscaUsuarios();		
	}
	
	
}